import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

// Test Class
class FractionTest {
    @Test
    public void runAllTests() {
        // Qualify the assertEquals() with "Assertions." to say that it comes from the Assertions library.
        // Assertions library, as can be seen from the import, is: org.junit.jupiter.api.Assertions.
        testTimes();
        testPlus();
        testMinus();
        testDividedBy();
        testReciprocal();
        exceptionThrowTest();
        compareToTest();
        collectionsSortTest();

    }

    @Test
    public void testTimes() {
        Fraction f1 = new Fraction(1, 2);
        Fraction f2 = new Fraction(1, 3);
        Fraction f3 = f1.Times(f2);
        Assertions.assertEquals(f3.toString(), "1/6");
    }

    @Test
    public void testPlus() {
        Fraction f1 = new Fraction(1, 2);
        Fraction f2 = new Fraction(1, 3);
        Fraction f3 = f1.Plus(f2);
        Assertions.assertEquals(f3.toString(), "5/6");

//Assertions.assertEquals(true, true)
    }

    @Test
    public void testMinus() {
        Fraction f1 = new Fraction(1, 2);
        Fraction f2 = new Fraction(1, 3);
        Fraction f3 = f1.Minus(f2);
        Assertions.assertEquals(f3.toString(), "1/6");
    }

    @Test
    public void testDividedBy() {
        Fraction f1 = new Fraction(1, 2);
        Fraction f2 = new Fraction(1, 3);
        Fraction f3 = f1.DividedBy(f2);
        Assertions.assertEquals(f3.toString(), "3/2");
    }

    @Test
    public void testReciprocal() {
        Fraction f1 = new Fraction(1, 2);
        Fraction f2 = new Fraction(1, 3);
        Fraction f3 = f1.reciprocal();
        Fraction f4 = f2.reciprocal();
        Assertions.assertEquals(f3.toString(), "2/1");
        Assertions.assertEquals(f4.toString(), "3/1");
    }

    @Test
    public void exceptionThrowTest() {
        try {
            Fraction f4 = new Fraction(10, 0);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.out.println("Caught thrown exception for illegal denominator");
        }
    }

    //This test is going to be to see my comparison to override worked
    @Test
    public void compareToTest() {
        Fraction f1 = new Fraction(1, 4);
        Fraction f2 = new Fraction(1, 9);
        Fraction f3 = new Fraction(1, 4);
        assert (f2.compareTo(f1) < 0);

        assert (f1.compareTo(f2) > 0);

        assert (f1.compareTo(f3) == 0);
    }


    @Test
    public void collectionsSortTest() {
        ArrayList<Fraction> manyFractions = new ArrayList<Fraction>();
        Fraction f1 = new Fraction(3, 8);
        Fraction f2 = new Fraction(1, 8);
        Fraction f3 = new Fraction(4, 8);
        Fraction f4 = new Fraction(7, 8);
        Fraction f5 = new Fraction(2, 8);
        Fraction f6 = new Fraction(6, 8);

        manyFractions.add(f1);
        manyFractions.add(f2);
        manyFractions.add(f3);
        manyFractions.add(f4);
        manyFractions.add(f5);
        manyFractions.add(f6);

        manyFractions.sort(Fraction::compareTo);

        for (Fraction el: manyFractions) {
            System.out.println(el.toString());
        }

    }


}