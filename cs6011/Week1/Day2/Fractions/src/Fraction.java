public class Fraction {
    long numerator;
    long denominator;

    //This is the default constructor
    public Fraction() {
        numerator = 0;
        denominator = 1;
    }

    //This constructor will set the value of the numerator and denominator to input values
    public Fraction(long n, long d) {
        if ((n < 0 && d < 0) || (n > 0 && d < 0)) {
            n *= -1;
            d *= -1;
        }
        numerator = n;
        denominator = d;
        this.reduce();

    }

    //This is the beginning of my methods
    //This method makes a sum of the 2 fractions and returns a fraction
    public Fraction Plus(Fraction rhs) {
        Fraction tempFrac = new Fraction();
        if (denominator == rhs.denominator) {
            tempFrac.denominator = rhs.denominator;
            tempFrac.numerator = (numerator + rhs.numerator);
            tempFrac.reduce();
            return tempFrac;
        } else {
            tempFrac.denominator = (denominator * rhs.denominator);
            tempFrac.numerator = ((numerator * rhs.denominator) + (rhs.numerator * denominator));
            tempFrac.reduce();
            return tempFrac;
        }

    }

    //Returns a new fraction that is the result of the right hand side (rhs) fraction subtracted from this fraction.
    public Fraction Minus(Fraction rhs) {
        Fraction tempFrac = new Fraction();
        if (denominator == rhs.denominator) {
            tempFrac.denominator = rhs.denominator;
            tempFrac.numerator = (numerator - rhs.numerator);
            tempFrac.reduce();
            return tempFrac;
        } else {
            tempFrac.denominator = (denominator * rhs.denominator);
            tempFrac.numerator = ((numerator * rhs.denominator) - (rhs.numerator * denominator));
            tempFrac.reduce();
            return tempFrac;
        }

    }

    //Returns a new fraction that is the result of this fraction multiplied by the right hand side (rhs) fraction.
    public Fraction Times(Fraction rhs) {
        Fraction tempFrac = new Fraction();
        tempFrac.numerator = (numerator * rhs.numerator);
        tempFrac.denominator = (denominator * rhs.denominator);
        tempFrac.reduce();
        return tempFrac;
    }

    //Returns a new fraction that is the result of this fraction divided by the right hand side (rhs) fraction.
    public Fraction DividedBy(Fraction rhs) {
        Fraction reciprocal = new Fraction();
        Fraction tempFrac = new Fraction();
        reciprocal = rhs.reciprocal();
        tempFrac.numerator = (numerator * reciprocal.numerator);
        tempFrac.denominator = (denominator * reciprocal.denominator);
        tempFrac.reduce();
        return tempFrac;
    }


    //Returns a new fraction that is the reciprocal of this fraction.
    public Fraction reciprocal() {
        Fraction tempFrac = new Fraction();
        long tempD = denominator;
        tempFrac.denominator = numerator;
        tempFrac.numerator = tempD;
        return tempFrac;

    }

    //Returns a string representing this fraction
    //Cannot use the method "toString", because it cannot accept longs, so using valueOf (accepting longs specifically)
    //to convert longs to strings and concatenate with the "/" and the denominator
    public String toString() {
        String stringFraction;
        stringFraction = String.valueOf(numerator);
        stringFraction += "/";
        stringFraction += String.valueOf(denominator);
        return stringFraction;

    }

    //Returns a (double precision) floating point number that is the approximate
    // value of this fraction, printed as a real number.
    public double toDouble() {
        double fracResult = 0.0000000;
        fracResult = ((numerator * 1.000) / (denominator * 1.000));
        return fracResult;
    }

    //Returns the greatest common divisor of this fraction's numerator and denominator
    public long GCD() {
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
    public void reduce() {
        long gcd = this.GCD();
        numerator = (numerator / gcd);
        denominator = (denominator / gcd);
    }

}



