# Math-expr-parser

> Mathematical expression calculation based on recursive descent analysis

## Procedural grammar

```
Add ——> Mul | Add + Mul | Add - Mul
Mul ——> Pri | Pri * Mul | Pri / Mul
Pri ——> num | (Add)
```

> Note: Currently only four operations('+'、'-'、'*'、'/') are supported

## Example

```java
import cn.navclub.mexpr.parser.ASTParser;
import cn.navclub.mexpr.parser.model.ASTNode;

public class Test {
    public static void main(String[] args) {
        String expr = "3+2-5*(((1+1)*6*(6+3))*10)+5";
        //Instance math expr parser
        ASTParser parser = new ASTParser(expr);
        //Get math expr abstract syntax tree
        ASTNode node = parser.astNode();
        //Execute abstract syntax tree
        double rs = parser.execute();
        //Output execute result
        System.out.printf("Expr execute result:%d\n",rs);
    }

}
```
+ Execute example code terminal will output:
```shell
  Expr execute result:-5400
```