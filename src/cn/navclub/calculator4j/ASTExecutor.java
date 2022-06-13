package cn.navclub.calculator4j;

import cn.navclub.calculator4j.config.TokenKind;
import cn.navclub.calculator4j.model.ASTNode;

public class ASTExecutor {
    private final ASTNode root;

    public ASTExecutor(ASTNode root) {
        this.root = root;
    }

    public double execute() {
        ASTNode next = this.root;
        double value = 0;
        while (next != null) {
            value = this.binTreeTrav(next);
            System.out.println(value);
            next = next.getNext();
        }
        return value;
    }


    /**
     * 二叉树遍历
     */
    private double binTreeTrav(ASTNode node) {
        var kind = node.getKind();
        var left = node.getLeft();
        var right = node.getRight();
        final double a;
        final double b;
        if (left != null && left.getKind() != TokenKind.NUM) {
            a = binTreeTrav(left);
        } else {
            assert left != null;
            a = left.toDValue();
        }
        if (right != null && right.getKind() != TokenKind.NUM) {
            b = binTreeTrav(right);
        } else {
            assert right != null;
            b = right.toDValue();
        }
        return calculate(kind, a, b);
    }


    private double calculate(TokenKind kind, double a, double b) {
        if (kind == TokenKind.ADD) {
            return a + b;
        } else if (kind == TokenKind.SUB) {
            return a - b;
        } else if (kind == TokenKind.MUL) {
            return a * b;
        } else {
            return a / b;
        }
    }
}
