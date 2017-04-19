import java.util.Map;
import java.util.HashMap;
/**
 * Class SubTable just holds the given Substitution table used in decryption.
 *SubTable has only a single field called key which is statically initialized with
 *the given substition table from the project specifications.
 */
public class SubTable{
    public static final Map<Character, Character> key = new HashMap<Character, Character>();
    static{
        key.put('J', 'A'); key.put('O', 'B'); key.put('F', 'C'); key.put('X', 'D');
        key.put('I', 'E'); key.put('L', 'F'); key.put('M', 'G'); key.put('R', 'H');
        key.put('P', 'I'); key.put('Q', 'J'); key.put('Y', 'K'); key.put('T', 'L');
        key.put('G', 'M'); key.put('A', 'N'); key.put('K', 'O'); key.put('H', 'P');
        key.put('C', 'Q'); key.put('D', 'R'); key.put('B', 'S'); key.put('W', 'T');
        key.put('V', 'U'); key.put('U', 'V'); key.put('Z', 'W'); key.put('N', 'X');
        key.put('E', 'Y'); key.put('S', 'Z');
    }
}
