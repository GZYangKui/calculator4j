package cn.navclub.calculator4j.model;

import cn.navclub.calculator4j.config.TokenKind;

public class ASTNode {
    private Double value;
    private TokenKind kind;
    private final ASTNode[] kinds;

    public ASTNode(TokenKind kind, Double value) {
        this.kind = kind;
        this.value = value;
        this.kinds = new ASTNode[2];
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public TokenKind getKind() {
        return kind;
    }

    public void setKind(TokenKind kind) {
        this.kind = kind;
    }

    public ASTNode[] getKinds() {
        return kinds;
    }
}
