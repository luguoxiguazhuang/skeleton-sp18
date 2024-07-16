public class OffByN implements CharacterComparator{
    public int num ;

    public OffByN(int N) {
        num = N;
    }

    @Override
    public boolean equalChars(char x, char y) {
        return (x - y == num) || (y - x == num);
    }
}
