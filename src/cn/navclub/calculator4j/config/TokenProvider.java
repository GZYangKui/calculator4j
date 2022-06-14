package cn.navclub.calculator4j.config;

import cn.navclub.calculator4j.model.Token;

import java.util.Arrays;
import java.util.Collection;

public class TokenProvider {
    private final static char C_EOF = 0xff;

    //非数字首符集
    private final static TokenKind[] NA_NUM = {
            TokenKind.ADD,
            TokenKind.MUL,
            TokenKind.DIV,
            TokenKind.SUB,
            TokenKind.LBRACE,
            TokenKind.RBRACE
    };

    private final char[] array;

    private int pos;
    private char cc;
    private Token present;

    private TokenProvider(String expr) {
        this.pos = 0;
        this.cc = '\0';
        this.present = null;
        this.array = expr.toCharArray();
    }


    public Token nextToken() {
        if (cc == '\0') {
            cc = nextChar();
        }
        final Token token;
        if (cc == C_EOF) {
            token = new Token(TokenKind.EOF);
        } else if (isNum(cc)) {
            int count = 0;
            char[] arr = new char[Token.MAX_CHAR_LEN];
            do {
                arr[count] = cc;
                cc = nextChar();
                count++;
            } while (isNum(cc) && count < Token.MAX_CHAR_LEN);
            token = new Token(TokenKind.NUM, Arrays.copyOf(arr, count));
        } else if (!Character.isAlphabetic(cc)) {
            token = new Token(this.checkNNum(cc));
        } else {
            token = new Token(TokenKind.NA);
        }
        if (token.getKind() == TokenKind.NA) {
            throw new ParserException(String.format("Illegal character '0x%x'", (short) cc));
        }
        //非数字类型获取下一个字符
        if (token.getKind() != TokenKind.NUM) {
            cc = nextChar();
        }
        return (this.present = token);
    }

    public void expect(Token token, TokenKind kind) {
        if (token == null) {
            token = this.present;
        }
        if (token.getKind() == kind) {
            return;
        }
        throw new ParserException(String.format("Expect character '%c'", kind.value));
    }

    /**
     * 运算符首符集
     */
    public boolean operatorPrefix(TokenKind kind) {
        for (int i = 0; i < NA_NUM.length - 2; i++) {
            if (kind == NA_NUM[i]) {
                return true;
            }
        }
        return false;
    }

    private boolean isNum(char c) {
        return Character.isDigit(c) || c == '.';
    }

    private TokenKind checkNNum(char c) {
        for (TokenKind kind : NA_NUM) {
            if (kind.value == c) {
                return kind;
            }
        }
        return TokenKind.NA;
    }

    private char nextChar() {
        if (pos >= this.array.length) {
            return C_EOF;
        }
        char c = this.array[pos++];
        if (c == ' ' || c == '\r' || c == '\t') {
            this.nextChar();
        }
        return c;
    }

    public Token getPresent() {
        return present;
    }


    public static TokenProvider newProvider(String expr) {
        return new TokenProvider(expr);
    }
}
