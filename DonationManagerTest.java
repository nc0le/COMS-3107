import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class DonationManagerTest {
    // fundManager is null returns IllegalStateException
    @Test 
    public void nullFundManager() {
        FundManager fm = null;
        UserManager um = new UserManager() {
            @Override 
            public boolean isValidUser(String name) {
                return true;
            }
            @Override 
            public double getBalance(String name) {
                return 2000.0;
            }
        };
        DonationManager dm = new DonationManager(fm, um);

        assertThrows(IllegalStateException.class, 
            () -> dm.donate("user", "fund", 100.0));
 
    }

    // userManager is null returns IllegalStateException
    @Test 
    public void nullUserManager() {
        FundManager fm = new FundManager() {
            @Override 
            public boolean isValidFund(String name) {
                return true;
            }
            @Override 
            public double getFundTarget(String name) {
                return 100.0;
            }
            @Override 
            public double getFundBalance(String name) {
                return 60.0;
            }
        };
        UserManager um = null;
        DonationManager dm = new DonationManager(fm, um);

        assertThrows(IllegalStateException.class, 
            () -> dm.donate("user", "fund", 10.0));
    }

    // user is null returns IllegalArgumentException
    @Test 
    public void nullUser() {
        FundManager fm = new FundManager() {
            @Override 
            public boolean isValidFund(String name) {
                return true;
            }
            @Override 
            public double getFundTarget(String name) {
                return 100.0;
            }
            @Override 
            public double getFundBalance(String name) {
                return 60.0;
            }
        };
        UserManager um = new UserManager() {
            @Override 
            public boolean isValidUser(String name) {
                return true;
            }
            @Override 
            public double getBalance(String name) {
                return 2000.0;
            }
        };
        DonationManager dm = new DonationManager(fm, um);

        assertThrows(IllegalArgumentException.class, 
            () -> dm.donate(null, "fund", 10.0));
    }

    // user is empty returns IllegalArgumentException
    @Test 
    public void emptyUser() {
        FundManager fm = new FundManager() {
            @Override 
            public boolean isValidFund(String name) {
                return true;
            }
            @Override 
            public double getFundTarget(String name) {
                return 100.0;
            }
            @Override 
            public double getFundBalance(String name) {
                return 60.0;
            }
        };
        UserManager um = new UserManager() {
            @Override 
            public boolean isValidUser(String name) {
                return true;
            }
            @Override 
            public double getBalance(String name) {
                return 2000.0;
            }
        };
        DonationManager dm = new DonationManager(fm, um);

        assertThrows(IllegalArgumentException.class, 
            () -> dm.donate("", "fund", 10.0));
    }

    // fund is null returns IllegalArgumentException
    @Test
    public void nullFund() {
        FundManager fm = new FundManager() {
            @Override 
            public boolean isValidFund(String name) {
                return true;
            }
            @Override 
            public double getFundTarget(String name) {
                return 100.0;
            }
            @Override 
            public double getFundBalance(String name) {
                return 60.0;
            }
        };
        UserManager um = new UserManager() {
            @Override 
            public boolean isValidUser(String name) {
                return true;
            }
            @Override 
            public double getBalance(String name) {
                return 2000.0;
            }
        };
        DonationManager dm = new DonationManager(fm, um);

        assertThrows(IllegalArgumentException.class, 
            () -> dm.donate("user", null, 10.0));
    }

    // fund is empty returns IllegalArgumentException
    @Test 
    public void emptyFund() {
        FundManager fm = new FundManager() {
            @Override 
            public boolean isValidFund(String name) {
                return true;
            }
            @Override 
            public double getFundTarget(String name) {
                return 100.0;
            }
            @Override 
            public double getFundBalance(String name) {
                return 60.0;
            }
        };
        UserManager um = new UserManager() {
            @Override 
            public boolean isValidUser(String name) {
                return true;
            }
            @Override 
            public double getBalance(String name) {
                return 2000.0;
            }
        };
        DonationManager dm = new DonationManager(fm, um);

        assertThrows(IllegalArgumentException.class, 
            () -> dm.donate("user", "", 10.0));
    }

    // donation is negative returns IllegalArgumentException
    @Test 
    public void negativeDonation() {
        FundManager fm = new FundManager() {
            @Override 
            public boolean isValidFund(String name) {
                return true;
            }
            @Override 
            public double getFundTarget(String name) {
                return 100.0;
            }
            @Override 
            public double getFundBalance(String name) {
                return 60.0;
            }
        };
        UserManager um = new UserManager() {
            @Override 
            public boolean isValidUser(String name) {
                return true;
            }
            @Override 
            public double getBalance(String name) {
                return 2000.0;
            }
        };
        DonationManager dm = new DonationManager(fm, um);

        assertThrows(IllegalArgumentException.class, 
            () -> dm.donate("user", "fund", -10.0));
    }

    // donation is zero returns IllegalArgumentException
    @Test 
    public void zeroDonation() {
        FundManager fm = new FundManager() {
            @Override 
            public boolean isValidFund(String name) {
                return true;
            }
            @Override 
            public double getFundTarget(String name) {
                return 100.0;
            }
            @Override 
            public double getFundBalance(String name) {
                return 60.0;
            }
        };
        UserManager um = new UserManager() {
            @Override 
            public boolean isValidUser(String name) {
                return true;
            }
            @Override 
            public double getBalance(String name) {
                return 2000.0;
            }
        };
        DonationManager dm = new DonationManager(fm, um);

        assertThrows(IllegalArgumentException.class, 
            () -> dm.donate("user", "fund", 0.0));
    }

    // invalid userManager returns InvalidUserException
    @Test 
    public void invalidUserManager() {
        FundManager fm = new FundManager() {
            @Override 
            public boolean isValidFund(String name) {
                return true;
            }
            @Override 
            public double getFundTarget(String name) {
                return 100.0;
            }
            @Override 
            public double getFundBalance(String name) {
                return 60.0;
            }
        };
        UserManager um = new UserManager() {
            @Override 
            public boolean isValidUser(String name) {
                return false;
            }
            @Override 
            public double getBalance(String name) {
                return 2000.0;
            }
        };
        DonationManager dm = new DonationManager(fm, um);

        assertThrows(InvalidUserException.class, 
            () -> dm.donate("user", "fund", 10.0));
    }

    // invalid fundManager returns InvalidFundException
    @Test 
    public void invalidFundManager() {
        FundManager fm = new FundManager() {
            @Override 
            public boolean isValidFund(String name) {
                return false;
            }
            @Override 
            public double getFundTarget(String name) {
                return 100.0;
            }
            @Override 
            public double getFundBalance(String name) {
                return 60.0;
            }
        };
        UserManager um = new UserManager() {
            @Override 
            public boolean isValidUser(String name) {
                return true;
            }
            @Override 
            public double getBalance(String name) {
                return 2000.0;
            }
        };
        DonationManager dm = new DonationManager(fm, um);

        assertThrows(InvalidFundException.class, 
            () -> dm.donate("user", "fund", 10.0));
    }

    // negative fund target returns IllegalStateException
    @Test 
    public void negativeFundTarget() {
        FundManager fm = new FundManager() {
            @Override 
            public boolean isValidFund(String name) {
                return true;
            }
            @Override 
            public double getFundTarget(String name) {
                return -100.0;
            }
            @Override 
            public double getFundBalance(String name) {
                return 60.0;
            }
        };
        UserManager um = new UserManager() {
            @Override 
            public boolean isValidUser(String name) {
                return true;
            }
            @Override 
            public double getBalance(String name) {
                return 2000.0;
            }
        };
        DonationManager dm = new DonationManager(fm, um);

        assertThrows(IllegalStateException.class, 
            () -> dm.donate("user", "fund", 10.0));
    }

    // user's balance less than donation returns InsufficientBalanceException
    @Test 
    public void userBalanceLessThanDonation() {
        FundManager fm = new FundManager() {
            @Override 
            public boolean isValidFund(String name) {
                return true;
            }
            @Override 
            public double getFundTarget(String name) {
                return 100.0;
            }
            @Override 
            public double getFundBalance(String name) {
                return 60.0;
            }
        };
        UserManager um = new UserManager() {
            @Override 
            public boolean isValidUser(String name) {
                return true;
            }
            @Override 
            public double getBalance(String name) {
                return 5.0;
            }
        };
        DonationManager dm = new DonationManager(fm, um);

        assertThrows(InsufficientBalanceException.class, 
            () -> dm.donate("user", "fund", 10.0));
    }

    // donation greater than amount to achieve fund target returns amount to achieve target
    @Test 
    public void caseOneDonationGreater() {
        FundManager fm = new FundManager() {
            @Override 
            public boolean isValidFund(String name) {
                return true;
            }
            @Override 
            public double getFundTarget(String name) {
                return 100.0;
            }
            @Override 
            public double getFundBalance(String name) {
                return 60.0;
            }
        };
        UserManager um = new UserManager() {
            @Override 
            public boolean isValidUser(String name) {
                return true;
            }
            @Override 
            public double getBalance(String name) {
                return 2000.0;
            }
        };
        DonationManager dm = new DonationManager(fm, um);
        double result = dm.donate("user", "fund", 50.0);

        assertEquals(40.0, result, 0.001);
    }

    // donation less than amount to achieve fund target returns donation amount
    @Test 
    public void caseOneFundTargetGreater() {
        FundManager fm = new FundManager() {
            @Override 
            public boolean isValidFund(String name) {
                return true;
            }
            @Override 
            public double getFundTarget(String name) {
                return 100.0;
            }
            @Override 
            public double getFundBalance(String name) {
                return 60.0;
            }
        };
        UserManager um = new UserManager() {
            @Override 
            public boolean isValidUser(String name) {
                return true;
            }
            @Override 
            public double getBalance(String name) {
                return 2000.0;
            }
        };
        DonationManager dm = new DonationManager(fm, um);
        double result = dm.donate("user", "fund", 30.0);

        assertEquals(30.0, result, 0.001);
    }

    // (donation is > 2000) and (amount to achieve fund target is >= 4000)
    // and (donation times 2 is less than amount to achieve fund target)
    // returns donation times 2
    @Test
    public void matchedBelowGap() {
        FundManager fm = new FundManager() {
            @Override
            public boolean isValidFund(String name) {
                return true;
            }

            @Override
            public double getFundTarget(String name) {
                return 15000.0;
            }

            @Override
            public double getFundBalance(String name) {
                return 3000.0;
            }
        };
        UserManager um = new UserManager() {
            @Override
            public boolean isValidUser(String name) {
                return true;
            }

            @Override
            public double getBalance(String name) {
                return 20000.0;
            }
        };
        DonationManager manager = new DonationManager(fm, um);

        assertEquals(8000.0, manager.donate("Nicole", "Library", 4000.0), 0.000001);
    }

    // (donation is > 2000) and (amount to achieve fund target is >= 4000)
    // and (donation times 2 is greater than amount to achieve fund target)
    // returns amount to achieve fund target
    @Test
    public void matchedAboveGap() {
        FundManager fm = new FundManager() {
            @Override
            public boolean isValidFund(String name) {
                return true;
            }

            @Override
            public double getFundTarget(String name) {
                return 15000.0;
            }

            @Override
            public double getFundBalance(String name) {
                return 3000.0;
            }
        };
        UserManager um = new UserManager() {
            @Override
            public boolean isValidUser(String name) {
                return true;
            }

            @Override
            public double getBalance(String name) {
                return 20000.0;
            }
        };
        DonationManager manager = new DonationManager(fm, um);

        assertEquals(12000.0, manager.donate("Nicole", "Library", 10000.0), 0.000001);
    }

    // fund balance equal to its target returns zero
    @Test
    public void targetReached() {
        FundManager fm = new FundManager() {
            @Override
            public boolean isValidFund(String name) {
                return true;
            }

            @Override
            public double getFundTarget(String name) {
                return 100.0;
            }

            @Override
            public double getFundBalance(String name) {
                return 100.0;
            }
        };
        UserManager um = new UserManager() {
            @Override
            public boolean isValidUser(String name) {
                return true;
            }

            @Override
            public double getBalance(String name) {
                return 20000.0;
            }
        };
        DonationManager manager = new DonationManager(fm, um);

        assertEquals(0.0, manager.donate("Nicole", "Library", 10.0), 0.000001);
    }

    // fund balance above its target returns zero
    @Test
    public void targetExceeded() {
        FundManager fm = new FundManager() {
            @Override
            public boolean isValidFund(String name) {
                return true;
            }

            @Override
            public double getFundTarget(String name) {
                return 100.0;
            }

            @Override
            public double getFundBalance(String name) {
                return 120.0;
            }
        };
        UserManager um = new UserManager() {
            @Override
            public boolean isValidUser(String name) {
                return true;
            }

            @Override
            public double getBalance(String name) {
                return 20000.0;
            }
        };
        DonationManager manager = new DonationManager(fm, um);

        assertEquals(0.0, manager.donate("Nicole", "Library", 10.0), 0.000001);
    }

}
