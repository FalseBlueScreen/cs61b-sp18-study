import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {
    CharacterComparator offBy5 = new OffByN(5);
    CharacterComparator offBy6 = new OffByN(6);

    @Test
    public void testOffByN() {
        assertTrue(offBy5.equalChars('a', 'f'));
        assertTrue(offBy5.equalChars('f', 'a'));
        assertFalse(offBy5.equalChars('f', 'h'));
        assertFalse(offBy5.equalChars('a', 'g'));

        assertFalse(offBy6.equalChars('a', 'f'));
        assertTrue(offBy6.equalChars('a', 'g'));
    }
}
