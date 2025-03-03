
public class Devide implements Expression
{
    private final Expression leftExpression;
    private final Expression rightExpression;
    
    public Devide(Expression leftExpression, Expression rightExpression){
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }
    @Override
    public int interpret() {
        // Kiểm tra chia cho 0
        if (rightExpression.interpret() == 0) {
            throw new ArithmeticException("Division by zero is not allowed.");
        }
        return leftExpression.interpret() / rightExpression.interpret();
    }
}
