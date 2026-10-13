import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class NextPrimeTest {

    @Test
    public void negativeReturnsNegative() {
        assertEquals(-1, Primes.nextPrime(-1));
        assertEquals(-1, Primes.nextPrime(-100));
    }

    @Test
    public void zeroReturnsTwo() {
        assertEquals(2, Primes.nextPrime(0));
    }

    @Test
    public void oneReturnsTwo() {
        assertEquals(2, Primes.nextPrime(1));
    }

    @Test
    public void twoReturnsItself() {
        assertEquals(2, Primes.nextPrime(2));
    }

    @Test
    public void oddPrimeReturnsItself() {
        assertEquals(3, Primes.nextPrime(3));
    }

    @Test
    public void evenCompositeFourReturnsFive() {
        assertEquals(5, Primes.nextPrime(4));
    }

    @Test
    public void nineReturnsEleven() {
        assertEquals(11, Primes.nextPrime(9));
    }

    @Test
    public void twentyFiveReturnsTwentyNine() {
        assertEquals(29, Primes.nextPrime(25));
    }

    @Test
    public void thirtyFiveReturnsThirtySeven() {
        assertEquals(37, Primes.nextPrime(35));
    }

    @Test
    public void oneHundredNineteenReturnsOneHundredTwentySeven() {
        assertEquals(127, Primes.nextPrime(119));
    }
}
