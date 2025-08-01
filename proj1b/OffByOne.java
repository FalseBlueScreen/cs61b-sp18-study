public class OffByOne implements CharacterComparator{
    public boolean equalChars(char x, char y){
        return x + 1 == y || y + 1 == x;
    }
}
