package model.exception;

public class InvalidMacAddressException extends Exception {

    public InvalidMacAddressException(String invalid_mac_address) {
        super(invalid_mac_address);
    }
}
