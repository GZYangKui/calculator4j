package cn.navclub.mexpr.parser.executor;

import cn.navclub.mexpr.parser.config.TokenKind;
import cn.navclub.mexpr.parser.model.ASTNode;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ASTExecutor {

    public double execute(ASTNode root) {
        var value = this.binTraversing(root);
        var next = root.getNext();
        while (next != null) {
            var temp = this.binTraversing(next);
            value = this.calculate(next.getOperator(), value, temp);
            next = next.getNext();
        }
        return value;
    }


    /**
     * 遍历二叉树并计算该二叉树对应的值
     */
    private double binTraversing(ASTNode node) {
        if (node.getKind() == TokenKind.NUM) {
            return node.toDValue();
        }
        var kind = node.getKind();
        var left = node.getLeft();
        var right = node.getRight();
        final double a;
        final double b;
        if (left.getKind() != TokenKind.NUM) {
            a = binTraversing(left);
        } else {
            a = left.toDValue();
        }
        if (right.getKind() != TokenKind.NUM) {
            b = binTraversing(right);
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
