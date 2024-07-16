import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();
    static OffByOne offByOne = new OffByOne();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test(timeout = 100)
    public void testIsPalindrome(){
        assertTrue(palindrome.isPalindrome(""));
        assertTrue(palindrome.isPalindrome("a"));
        assertFalse(palindrome.isPalindrome("abb"));
        assertFalse(palindrome.isPalindrome("1234567"));
        assertTrue(palindrome.isPalindrome("noon"));
    }

    @Test(timeout = 100)
    public void GeneralTestIsPalindrome() {
        assertTrue(palindrome.isPalindrome("",offByOne));
        assertTrue(palindrome.isPalindrome("a",offByOne));
        assertTrue(palindrome.isPalindrome("a120b",offByOne));
        assertFalse(palindrome.isPalindrome("algdex",offByOne));
    }
}
