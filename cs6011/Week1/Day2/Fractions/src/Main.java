public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Fraction test1 = new Fraction( 2, 3);
        Fraction test2 = new Fraction(4, 5);
        Fraction test3 = new Fraction();
        test3 = test1.DividedBy(test2);


        //Fraction test4 = new Fraction();

        System.out.println("test1 fraction numerator is: ");
        System.out.println(test1.numerator);
        System.out.println("test1 fraction denominator is: ");
        System.out.println(test1.denominator);

        System.out.println("test2 fraction numerator is: ");
        System.out.println(test2.numerator);
        System.out.println("test2 fraction denominator is: ");
        System.out.println(test2.denominator);

        System.out.println("test3 fraction numerator is: ");
        System.out.println(test3.numerator);
        System.out.println("test3 fraction denominator is: ");
        System.out.println(test3.denominator);

        System.out.println(test2.toString());

        double result = test2.toDouble();

        System.out.println(result);

    }
}