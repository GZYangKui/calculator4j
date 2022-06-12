package cn.navclub.calculator4j;

import cn.navclub.calculator4j.config.TokenKind;
import cn.navclub.calculator4j.model.Token;

public class Main {
    public static void main(String[] args) {
        String expr = "2+4+6*8";
        TokenProvider provider = TokenProvider.newProvider(expr);
        while (true) {
            Token token = provider.token();
            if (token.getKind() == TokenKind.EOF){
                break;
            }
            System.out.println(token.getValue());
        }
    }
}
