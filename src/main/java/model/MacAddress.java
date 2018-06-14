package model;

import model.exception.InvalidMacAddressException;

public class MacAddress {
    byte[] address = new byte[4];

    public MacAddress(byte i1, byte i2, byte i3, byte i4, byte i5, byte i6) {
        address[0] = i1;
        address[1] = i2;
        address[2] = i3;
        address[3] = i4;
        address[4] = i5;
        address[5] = i6;
    }

    public MacAddress(int... macAddress) throws InvalidMacAddressException {
        if (macAddress.length != 6) throw new InvalidMacAddressException("Mac address should have 6 bytes");
        for (int macByte : macAddress) if (macByte > 255) throw new InvalidMacAddressException("Invalid Mac Address");
    }

}
