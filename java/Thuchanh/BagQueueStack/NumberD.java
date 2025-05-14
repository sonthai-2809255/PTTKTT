 

public class NumberD implements ExpressionD{

    private final int n;
    public NumberD(int n){
        this.n = n;
    }
     
   
    @Override
    public int interpret() {
        return n;
    }
    

}
