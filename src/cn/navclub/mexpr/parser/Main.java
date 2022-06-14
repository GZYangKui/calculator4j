package cn.navclub.mexpr.parser;

import cn.navclub.mexpr.parser.config.TokenProvider;
import cn.navclub.mexpr.parser.executor.ASTExecutor;
import cn.navclub.mexpr.parser.expr.ASTExpr;

public class Main {
    public static void main(String[] args) {
        var expr = "0.0000005+0.100005";
        var provider = TokenProvider.newProvider(expr);
        var astExpr = new ASTExpr(provider);
        var root = astExpr.astNode();
        var executor = new ASTExecutor(root);
        var rs = executor.execute();
        System.out.printf("计算结果:%.10f\n",rs);
    }
}
