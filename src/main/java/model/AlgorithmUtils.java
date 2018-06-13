package model;

public class AlgorithmUtils {
    /* Return 0 to 8 value
     * O = no 1 bit fond
     */
    public static int findMostSignificantBit32(long number) {
        for (int i = 31; i >= 0; i--) {
            if ((number >> i & 1) == 1) return i + 1;
        }
        return 0;
    }
}
