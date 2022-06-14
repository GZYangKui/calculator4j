package cn.navclub.mexpr.parser.model;

import cn.navclub.mexpr.parser.config.TokenKind;

public class ASTNode {
    private ASTNode next;
    private ASTNode left;
    private ASTNode right;
    private TokenKind operator;
    private final char[] value;
    private final TokenKind kind;

    public ASTNode(TokenKind kind, char[] value) {
        this.kind = kind;
        this.value = value;
    }

    public char[] getValue() {
        return value;
    }

    public Double toDValue() {
        return Double.parseDouble(new String(this.getValue()));
    }

    public TokenKind getKind() {
        return kind;
    }


    public ASTNode getLeft() {
        return left;
    }

    public void setLeft(ASTNode left) {
        this.left = left;
    }

    public ASTNode getRight() {
        return right;
    }

    public void setRight(ASTNode right) {
        this.right = right;
    }

    public ASTNode getNext() {
        return next;
    }

    public void setNext(ASTNode next) {
        this.next = next;
    }

    public TokenKind getOperator() {
        return operator;
    }

    public void setOperator(TokenKind operator) {
        this.operator = operator;
    }
}
