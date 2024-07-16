import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {

    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    @Test
    public void MyTestOffByOne() {
        assertTrue(offByOne.equalChars('c','d'));
        assertTrue(offByOne.equalChars('%','&'));
        assertFalse(offByOne.equalChars('0','4'));
    }

    // Your tests go here.
}
