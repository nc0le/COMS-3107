import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.*;

public class AggregateDonationsTest {
    /* 1. Each Donation in the input ArrayList has a distinct user */
    @Test
    public void testDistinctUser() {
        ArrayList<Donation> donations = new ArrayList<>();
        donations.add(new Donation("Peppa", 200.50));
        donations.add(new Donation("Snoopy", 12.0));
        ArrayList<TotalDonation> actual = DonationUtils.aggregateDonations(donations);

        ArrayList<TotalDonation> expected = new ArrayList<>();
        expected.add(new TotalDonation("Peppa", 200.50, 1));
        expected.add(new TotalDonation("Snoopy", 12.0, 1));

        assertEquals(expected.size(), actual.size());
        assertEquals(new HashSet<>(expected), new HashSet<>(actual));
    }

    /* 2. Some Donations in the input ArrayList have the same user */
    @Test
    public void testSameUser() {
        ArrayList<Donation> donations = new ArrayList<>();
        donations.add(new Donation("Peppa", 200.50));
        donations.add(new Donation("Snoopy", 12.0));
        donations.add(new Donation("Peppa", 100.0));
        ArrayList<TotalDonation> actual = DonationUtils.aggregateDonations(donations);

        ArrayList<TotalDonation> expected = new ArrayList<>();
        expected.add(new TotalDonation("Peppa", 300.50, 2));
        expected.add(new TotalDonation("Snoopy", 12.0, 1));

        assertEquals(expected.size(), actual.size());
        assertEquals(new HashSet<>(expected), new HashSet<>(actual));
    }

    /* 3. Input ArrayList is null */
    @Test
    public void testNullInput() {
        ArrayList<TotalDonation> actual = DonationUtils.aggregateDonations(null);
        ArrayList<TotalDonation> expected = new ArrayList<>();
        assertEquals(expected, actual);
    }

    /* 4. Input ArrayList contains null Donation object */
    @Test
    public void testNullDonation() {
        ArrayList<Donation> donations = new ArrayList<>();
        donations.add(new Donation("Peppa", 200.50));
        donations.add(null);
        ArrayList<TotalDonation> actual = DonationUtils.aggregateDonations(donations);

        ArrayList<TotalDonation> expected = new ArrayList<>();

        assertEquals(expected, actual);

    }

    /* 5. Input ArrayList contains a Donation object with a null user */
    @Test
    public void testNullUser() {
        ArrayList<Donation> donations = new ArrayList<>();
        donations.add(new Donation(null, 200.50));
        ArrayList<TotalDonation> actual = DonationUtils.aggregateDonations(donations);

        ArrayList<TotalDonation> expected = new ArrayList<>();

        assertEquals(expected, actual);

    }

    /* 5. Input ArrayList contains a Donation object with a negative 
       donation amount */
    @Test
    public void testNegativeDonation() {
        ArrayList<Donation> donations = new ArrayList<>();
        donations.add(new Donation("Peppa", -200.50));
        ArrayList<TotalDonation> actual = DonationUtils.aggregateDonations(donations);

        ArrayList<TotalDonation> expected = new ArrayList<>();

        assertEquals(expected, actual);
    }
}


