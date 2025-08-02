import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {
    CharacterComparator OffBy5 = new OffByN(5);
    CharacterComparator OffBy6 = new OffByN(6);

    @Test
    public void testOffByN(){
        assertTrue(OffBy5.equalChars('a', 'f'));
        assertTrue(OffBy5.equalChars('f', 'a'));
        assertFalse(OffBy5.equalChars('f','h'));
        assertFalse(OffBy5.equalChars('a','g'));

        assertFalse(OffBy6.equalChars('a', 'f'));
        assertTrue(OffBy6.equalChars('a','g'));
    }
}
