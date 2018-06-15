package model;

import model.exception.InvalidMaskException;

public class IPv4Mask extends IPv4Address {
    final static long MAX_32_BIT = (long) Math.pow(2, 32) - 1;
    public final int slashNotation;

    protected IPv4Mask(int slashNotation) throws InvalidMaskException {
        super(slashToBinaryValue(slashNotation));
        this.slashNotation = slashNotation;
    }

    private static long slashToBinaryValue(int slashNotation) throws InvalidMaskException {
        if (slashNotation > 32) throw new InvalidMaskException("Slash notation should have this range [0 - 32]");
        long newMask = MAX_32_BIT;
        newMask <<= (32 - slashNotation);
        newMask &= MAX_32_BIT;
        return newMask;
    }
}
