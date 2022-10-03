import java.lang.Comparable;


public class Fraction implements Comparable<Fraction>{
    long numerator;
    long denominator;

    //This is the default constructor
    public Fraction() {
        numerator = 0;
        denominator = 1;
    }

    //This constructor will set the value of the numerator and denominator to input values

    //    Update your Fraction class (From Tuesday) so that it throws an exception if someone tries
//    to create a fraction with a 0 denominator.
//
//    Add a test that uses try/catch to make SURE that creating a fraction like 10/0 throws an exception.
    public Fraction(long n, long d) throws IllegalArgumentException{
        if (d == 0){
            throw new IllegalArgumentException("Argument denominator is 0");
        }
        if (d < 0) {
            numerator *= -1;
            denominator *= -1;
        }
        numerator = n;
        denominator = d;
        this.reduce();

    }


    //This is the beginning of my methods
    //This method makes a sum of the 2 fractions and returns a fraction
    public Fraction Plus(Fraction rhs) {
        long tempN = ((numerator * rhs.denominator) + (rhs.numerator * denominator));
        long tempD = (denominator * rhs.denominator);
        Fraction tempFrac = new Fraction(tempN, tempD);
        return tempFrac;

    }

    //Returns a new fraction that is the result of the right hand side (rhs) fraction subtracted from this fraction.
    public Fraction Minus(Fraction rhs) {
        long tempN = ((numerator * rhs.denominator) - (rhs.numerator * denominator));
        long tempD = (denominator * rhs.denominator);
        Fraction tempFrac = new Fraction(tempN, tempD);
        return tempFrac;
    }

    //Returns a new fraction that is the result of this fraction multiplied by the right hand side (rhs) fraction.
    public Fraction Times(Fraction rhs) {
        long tempN = (numerator * rhs.numerator);
        long tempD = (denominator * rhs.denominator);
        Fraction tempFrac = new Fraction(tempN, tempD);
        return tempFrac;
    }

    //Returns a new fraction that is the result of this fraction divided by the right hand side (rhs) fraction.
    public Fraction DividedBy(Fraction rhs) {
        Fraction reciprocal = new Fraction();
        reciprocal = rhs.reciprocal();
        long tempN = (numerator * reciprocal.numerator);
        long tempD = (denominator * reciprocal.denominator);
        Fraction tempFrac = new Fraction(tempN, tempD);
        return tempFrac;
    }


    //Returns a new fraction that is the reciprocal of this fraction.
    public Fraction reciprocal() {
        Fraction tempFrac = new Fraction(denominator, numerator);
        return tempFrac;
    }

    //Returns a string representing this fraction
    //Cannot use the method "toString", because it cannot accept longs, so using valueOf (accepting longs specifically)
    //to convert longs to strings and concatenate with the "/" and the denominator
    public String toString() {
        String stringFraction = (numerator + "/" + denominator);
        return stringFraction;

    }

    //Returns a (double precision) floating point number that is the approximate
    // value of this fraction, printed as a real number.
    public double toDouble() {
        double fracResult;
        fracResult = ((numerator * 1.000) / (denominator));
        return fracResult;
    }

    //Returns the greatest common divisor of this fraction's numerator and denominator
    private long GCD() {
        long gcd = numerator;
        long remainder = denominator;
        while (remainder != 0) {
            long temp = remainder;
            remainder = gcd % remainder;
            gcd = temp;
        }
        return gcd;
    }

    //Changes this fraction to its reduced form.
    private void reduce() {
        long gcd = this.GCD();
        numerator = (numerator / gcd);
        denominator = (denominator / gcd);
    }

    @Override
    public int compareTo(Fraction o) {
        double comparison = this.toDouble() - o.toDouble();
        if (comparison > 0){
            return 1;
        } else if (comparison < 0) {
            return -1;
        }else {
            return 0;
        }
    }
}



