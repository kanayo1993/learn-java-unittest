public class Calculator {
    public int add(int a, int b){
        return a + b;
    }

    public int subtract(int a, int b){
        return a - b;
    }

    public int multply (int a, int b){
        return a * b;
    }

    public double divide(int a, int b){
        if(b == 0){
            throw new IllegalArgumentException("分母は０不可");
        }
        return (double) a/b;
    }
}
