import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testisPalindrome(){
        String a = "racecar";
        String b = "cancer";
        String c = "";
        String d = "a";
        String e = "aabbbbaa";
        String f = "ccdcc";

        assertTrue(palindrome.isPalindrome(a));
        assertFalse(palindrome.isPalindrome(b));
        assertTrue(palindrome.isPalindrome(c));
        assertTrue(palindrome.isPalindrome(d));
        assertTrue(palindrome.isPalindrome(e));
        assertTrue(palindrome.isPalindrome(f));
    }

    @Test
    public void testisPalindromeOBO(){
        CharacterComparator obo = new OffByOne();
        assertFalse(palindrome.isPalindrome("abc", obo));
        assertTrue(palindrome.isPalindrome("abb", obo));
        assertTrue(palindrome.isPalindrome("abcb", obo));
    }

    @Test
    public void testisPalindromeOBN(){
        CharacterComparator obn = new OffByN(5);
        assertTrue(palindrome.isPalindrome("abcgf", obn));
    }
}
