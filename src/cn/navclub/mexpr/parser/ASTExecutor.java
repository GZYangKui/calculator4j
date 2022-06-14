package cn.navclub.mexpr.parser;

import cn.navclub.mexpr.parser.config.TokenKind;
import cn.navclub.mexpr.parser.model.ASTNode;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ASTExecutor {
    private final ASTNode root;

    public ASTExecutor(ASTNode root) {
        this.root = root;
    }

    public double execute() {
        var value = this.binTreeTrav(root);
        var next = root.getNext();
        while (next != null) {
            var temp = this.binTreeTrav(next);
            value = this.calculate(next.getOperator(), value, temp);
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
        if (left.getKind() != TokenKind.NUM) {
            a = binTreeTrav(left);
        } else {
            a = left.toDValue();
        }
        if (right.getKind() != TokenKind.NUM) {
            b = binTreeTrav(right);
        } else {
            b = right.toDValue();
        }
        return calculate(kind, a, b);
    }


    private double calculate(TokenKind kind, double a, double b) {
        var aa = new BigDecimal(Double.toString(a));
        var bb = new BigDecimal(Double.toString(b));

        if (kind == TokenKind.ADD) {
            return aa.add(bb).doubleValue();
        } else if (kind == TokenKind.SUB) {
            return aa.subtract(bb).doubleValue();
        } else if (kind == TokenKind.MUL) {
            return aa.multiply(bb).doubleValue();
        } else {
            return aa.divide(bb, RoundingMode.CEILING).doubleValue();
        }
    }
}
