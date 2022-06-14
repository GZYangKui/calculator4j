package cn.navclub.mexpr.parser.config;

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
