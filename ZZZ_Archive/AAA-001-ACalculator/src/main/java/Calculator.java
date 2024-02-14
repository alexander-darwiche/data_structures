import java.util.stream.DoubleStream;

public class Calculator {
    public static void main(String[] args) {

    }
    static double add(double... operands) {
        return DoubleStream.of(operands)
                .sum();
    }

    static double multiply(double... operands) {
        return DoubleStream.of(operands)
                .reduce(1, (a, b) -> a * b);
    }

    static double divide(double... operands){
        return DoubleStream.of(operands)
                .reduce(1, (a,b) -> a / b);
    }
}