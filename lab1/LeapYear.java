import javax.net.ssl.TrustManager;
import javax.swing.plaf.TreeUI;

/** Class that determines whether or not a year is a leap year.
 *  @author ZHOU LIWEI
 */
public class LeapYear {

    /** Calls isLeapYear to print correct statement.
     *  @param  year to be analyzed
     */

     /** This method takes a integer year and return boolean num on wether it's a leap year.
      *  The rule is that a leap year can either be divisible by 400 or divisible by 4 but not by 100.   
      */
    public static boolean isLeapYear(int year){
        if (year % 400 == 0){
            return True;
        }
        else if (year % 4 == 0){
            if (year % 100 != 0){
                return True;
            }
        }
        return False;
    }

    private static void checkLeapYear(int year) {
        if (isLeapYear(year)) {
            System.out.printf("%d is a leap year.\n", year);
        } else {
            System.out.printf("%d is not a leap year.\n", year);
        }
    }

    /** Must be provided an integer as a command line argument ARGS. */
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please enter command line arguments.");
            System.out.println("e.g. java Year 2000");
        }
        for (int i = 0; i < args.length; i++) {
            try {
                int year = Integer.parseInt(args[i]);
                checkLeapYear(year);
            } catch (NumberFormatException e) {
                System.out.printf("%s is not a valid number.\n", args[i]);
            }
        }
    }
}

