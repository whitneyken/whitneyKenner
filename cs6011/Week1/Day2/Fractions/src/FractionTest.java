import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
// Test Class
class FractionTest {
    @Test
    public void runAllTests() {
        // Qualify the assertEquals() with "Assertions." to say that it comes from the Assertions library.
        // Assertions library, as can be seen from the import, is: org.junit.jupiter.api.Assertions.
        testTimes();
        testReduce();
        testPlus();
        testMinus();
        testDividedBy();
        testReciprocal();

    }
    @Test
    public void testTimes() {
        Fraction f1 = new Fraction(1,2 );
        Fraction f2 = new Fraction( 1, 3 );
        Fraction f3 = f1.Times( f2 );
        Assertions.assertEquals( f3.toString(), "1/6" );
    }
    @Test
    public void testReduce() {
        Assertions.assertEquals( new Fraction(1,2).toString(), "1/2" );
        Assertions.assertEquals( new Fraction(5,10).toString(), "1/2" );
        Assertions.assertNotEquals( new Fraction(50,100).toString(), "50/100" );


    }

    @Test
    public void testPlus(){
        Assertions.assertEquals(new Fraction( 1, 4).Plus(new Fraction(1, 4)), 1/2);
        Assertions.assertEquals(new Fraction(2, 3).Plus(new Fraction(2,3)), 4/3);
        Assertions.assertNotEquals(new Fraction(1, 4).Plus(new Fraction(1, 4)), 2/4);
    }

    @Test
    public void testMinus(){
        Assertions.assertEquals(new Fraction( 2, 4).Minus(new Fraction(1, 4)), 1/4);
        Assertions.assertEquals(new Fraction(2, 3).Minus(new Fraction(1,3)), 1/3);
        Assertions.assertNotEquals(new Fraction(1, 2).Minus(new Fraction(1, 4)), 3/4);
    }

    @Test
    public void testDividedBy(){
        Assertions.assertEquals(new Fraction( 1, 4).DividedBy(new Fraction(2, 3)), 3/8);
        Assertions.assertEquals(new Fraction(4, 15).DividedBy(new Fraction(2,3)), 2/5);
        Assertions.assertNotEquals(new Fraction(1, 2).DividedBy(new Fraction(1, 4)), 3/4);
    }

    @Test
    public void testReciprocal(){
        Assertions.assertEquals(new Fraction(1, 2), 2/1);
        Assertions.assertEquals(new Fraction(3, 5), 5/3);
        Assertions.assertNotEquals(new Fraction(2, 3), 2/3);
    }


}