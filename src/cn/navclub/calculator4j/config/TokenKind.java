package cn.navclub.calculator4j.config;

public enum TokenKind {
    NA(' '),
    EOF(' '),
    NUM(' '),
    ADD('+'),
    SUB('-'),
    MUL('*'),
    DIV('/'),
    LBRACE('('),
    RBRACE(')');

    public final char value;

    TokenKind(char value) {
        this.value = value;
    }

}
