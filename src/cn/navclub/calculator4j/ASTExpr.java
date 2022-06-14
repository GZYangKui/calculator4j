package cn.navclub.calculator4j;

import cn.navclub.calculator4j.config.TokenKind;
import cn.navclub.calculator4j.config.TokenProvider;
import cn.navclub.calculator4j.model.ASTNode;

/**
 * 递归下降分析实现简单四则运算
 *
 * @author GZYangKui
 */
public class ASTExpr {
    private final TokenProvider provider;

    public ASTExpr(TokenProvider provider) {
        this.provider = provider;
    }

    public ASTNode astNode() {
        this.provider.nextToken();
        var root = this.Add();
        var next = root;
        var loop = root != null;
        while (loop) {
            var temp = this.provider.getPresent();
            this.provider.nextToken();
            var node = this.Add();
            next.setNext(node);
            loop = (node != null);
            if (loop) {
                node.setOperator(temp.getKind());
            }
            next = node;
        }
        return root;
    }

    //Add —> Mul | Add + Mul | Add - Mul
    private ASTNode Add() {

        var astNode = Mul();

        var token = this.provider.getPresent();

        if (token.getKind() == TokenKind.ADD || token.getKind() == TokenKind.SUB) {
            var temp = new ASTNode(token.getKind(), null);
            temp.setLeft(astNode);
            this.provider.nextToken();
            temp.setRight(Mul());
            astNode = temp;
        }
        return astNode;
    }


    //Mul —> Pri | Pri * Mul| Pri / Mul
    private ASTNode Mul() {
        ASTNode astNode = Pri();
        var kind = this.provider.getPresent().getKind();
        if (kind == TokenKind.MUL || kind == TokenKind.DIV) {
            var temp = new ASTNode(kind, null);
            this.provider.nextToken();
            temp.setLeft(astNode);
            temp.setRight(Mul());
            astNode = temp;
        }
        return astNode;
    }

    // Pri ——> num | (Add)
    private ASTNode Pri() {
        var token = this.provider.getPresent();
        final ASTNode astNode;
        if (token.getKind() == TokenKind.NUM) {
            astNode = new ASTNode(token.getKind(), token.getValue());
            this.provider.nextToken();
        } else if (token.getKind() == TokenKind.LBRACE) {
            this.provider.nextToken();
            astNode = Add();
            this.provider.nextToken();
        } else {
            astNode = null;
        }
        return astNode;
    }

}
