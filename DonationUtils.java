import java.util.ArrayList;
//import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class DonationUtils {

    public static ArrayList<TotalDonation> aggregateDonations(ArrayList<Donation> donations) {
        ArrayList<TotalDonation> result = new ArrayList<>();
        Map<String, TotalDonation> donationMap = new HashMap<>();

        if (donations == null) {
            return result;
        }
        
        for (Donation donation : donations) {
            if (donation == null) {
                return new ArrayList<>();
            }

            String donorName = donation.getUser();
            if (donorName == null){
                return new ArrayList<>();
            }

            double amount = donation.getAmount();
            if (amount < 0) {
               return new ArrayList<>();
            }

            if (donationMap.containsKey(donorName)) {
                TotalDonation user = donationMap.get(donorName);
                user.setAmount(user.getAmount() + amount);
                user.setCount(user.getCount() + 1);
            } else {
                donationMap.put(donorName, new TotalDonation(donorName, amount, 1));
            }
        }

        result.addAll(donationMap.values());


        return result;
    }

    public static void main(String[] args) {
        // test 1
        ArrayList<Donation> donations1 = new ArrayList<>();
        donations1.add(new Donation("Peppa", 200.50));
        donations1.add(new Donation("Snoopy", 12.0));
        donations1.add(new Donation("Peppa", 82.25));
        System.out.println("Test 1:");
        System.out.println(aggregateDonations(donations1));

        // test 2
        System.out.println("Test 2:");
        System.out.println(aggregateDonations(null));

        // test 3
        ArrayList<Donation> donations2 = new ArrayList<>();
        donations2.add(new Donation("Peppa", 200.50));
        donations2.add(new Donation("Snoopy", 12.0));
        donations2.add(null);
        System.out.println("Test 3:");
        System.out.println(aggregateDonations(donations2));

        // test 4
        ArrayList<Donation> donations3 = new ArrayList<>();
        donations3.add(new Donation("Peppa", 200.50));
        donations3.add(new Donation("Snoopy", 12.0));
        donations3.add(new Donation(null, 82.25));
        System.out.println(aggregateDonations(donations3));

        // test 5 
        ArrayList<Donation> donations4 = new ArrayList<>();
        donations4.add(new Donation("Peppa", 200.50));
        donations4.add(new Donation("Snoopy", -12.0));
        donations4.add(new Donation(null, 82.25));
        System.out.println("Test 4:");
        System.out.println(aggregateDonations(donations4));

    }
}
