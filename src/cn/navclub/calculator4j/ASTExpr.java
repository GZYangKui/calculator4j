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
        ASTNode next = root;
        var loop = root != null;
        while (loop) {
            ASTNode node = this.Add();
            next.setNext(node);
            loop = (node != null);
            if (loop) {
                node.setParent(next);
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
            this.provider.nextToken();
        }

        return astNode;
    }


    //Mul —> Pri | Mul * Pri | Mul / Pri
    private ASTNode Mul() {
        ASTNode astNode = Pri();
        var token = this.provider.getPresent();
        if (token.getKind() == TokenKind.MUL || token.getKind() == TokenKind.DIV) {
            var temp = new ASTNode(token.getKind(), null);
            this.provider.nextToken();
            temp.setLeft(Mul());
            temp.setRight(astNode);
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
        } else {
            astNode = null;
        }
        return astNode;
    }

}
