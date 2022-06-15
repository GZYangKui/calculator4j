package cn.navclub.mexpr.parser;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class ExprParserTest {
    @Test
    public void testAdd() {
        var expr = "10+20";
        var sum = new ASTParser(expr).execute();
        Assertions.assertEquals(sum, 30.0);
    }

    @Test
    public void testSub() {
        var expr = "10-20";
        var sub = new ASTParser(expr).execute();
        Assertions.assertEquals(sub, -10.0);
    }

    @Test
    public void testMul() {
        var expr = "10*20";
        var mul = new ASTParser(expr).execute();
        Assertions.assertEquals(mul, 200);
    }

    @Test
    public void testDiv() {
        var expr = "10/20";
        var div = new ASTParser(expr).execute();
        Assertions.assertEquals(div, 0.5);
    }

    @Test
    public void testComplex() {
        var expr = "3+2-3*(10/20)*10";
        var rs = new ASTParser(expr).execute();
        Assertions.assertEquals(rs, -10);
    }

    @Test
    public void testNest(){
        var expr = "3+2-5*(((1+1)*6*(6+3))*10)+5";
        var rs = new ASTParser(expr).execute();
        Assertions.assertEquals(rs,-5400);
    }
}
