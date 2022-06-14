package cn.navclub.mexpr.parser.model;

import cn.navclub.mexpr.parser.config.TokenKind;

public class Token {
    public final static int MAX_CHAR_LEN = 10;

    private TokenKind kind;
    private final char[] value;

    public Token(TokenKind kind, char[] value) {
        this.kind = kind;
        this.value = value;
    }

    public Token(TokenKind kind) {
        this(kind, new char[]{kind.value});
    }

    public TokenKind getKind() {
        return kind;
    }

    public void setKind(TokenKind kind) {
        this.kind = kind;
    }

    public char[] getValue() {
        return value;
    }
}
