package model;

public class IPv4Address {
    final int[] tern;
    public final long binaryValue;

    public static enum ENDIAN {BIG, LITTLE}

    private IPv4Address(int i, int i1, int i2, int i3) {
        tern = new int[4];
        tern[0] = i;
        tern[1] = i1;
        tern[2] = i2;
        tern[3] = i3;
        binaryValue = (((long) (tern[0] << 8) + tern[1] << 8) + tern[2] << 8) + tern[3];
    }

    private IPv4Address(final int[] i) {
        this(i[0], i[1], i[2], i[3]);
    }

    public IPv4Address(long binaryValue) {
        this.binaryValue = binaryValue;
        tern = new int[4];
        tern[0] = (int) ((binaryValue & 0xFF000000) >> 24);
        tern[1] = (int) ((binaryValue & 0x00FF0000) >> 16);
        tern[2] = (int) ((binaryValue & 0x0000FF00) >> 8);
        tern[3] = (int) (binaryValue & 0x000000FF);
    }

    public IPv4Address(String value) {
        this(stringToIntArray(value));
    }


    public static int[] stringToIntArray(String value) {
        int tern[] = new int[4];
        String[] tmp = (value + ".").split("\\.");
        for (int i = 0; i < 4; i++)
            tern[i] = Integer.valueOf(tmp[i]);
        return tern;
    }


    public int getDecimalByte(int index, ENDIAN bigOrLittleEndian) {
        if (index >= 0 && index < 4)
            return bigOrLittleEndian == ENDIAN.BIG ? tern[index] : tern[3 - index];
        return -1;
    }

    public int getDecimalByte(int index) {
        return getDecimalByte(index, ENDIAN.BIG);
    }

    public IPv4Address newApplyingMask(IPv4Address mask) {
        return new IPv4Address(this.tern[0] & mask.tern[0], this.tern[1] & mask.tern[1], this.tern[2] & mask.tern[2], this.tern[3] & mask.tern[3]);
    }

    @Override
    public String toString() {
        return tern[0] + "." + tern[1] + "." + tern[2] + "." + tern[3];
    }
}
