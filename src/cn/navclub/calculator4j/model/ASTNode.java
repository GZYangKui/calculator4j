package cn.navclub.calculator4j.model;

import cn.navclub.calculator4j.config.TokenKind;

public class ASTNode {
    private char[] value;
    private TokenKind kind;
    private ASTNode next;
    private ASTNode parent;
    private ASTNode left;
    private ASTNode right;

    public ASTNode(TokenKind kind, char[] value) {
        this(kind, value, null);
    }

    public ASTNode(TokenKind kind, char[] value, ASTNode parent) {
        this.kind = kind;
        this.value = value;
        this.parent = parent;
    }

    public char[] getValue() {
        return value;
    }

    public Double toDValue() {
        return Double.parseDouble(new String(this.getValue()));
    }

    public void setValue(char[] value) {
        this.value = value;
    }

    public TokenKind getKind() {
        return kind;
    }

    public void setKind(TokenKind kind) {
        this.kind = kind;
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

    public ASTNode getParent() {
        return parent;
    }

    public void setParent(ASTNode parent) {
        this.parent = parent;
    }

    public void setNext(ASTNode next) {
        this.next = next;
    }
}
