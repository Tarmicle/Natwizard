package model.osi.Layer2;

import model.MacAddress;

import javax.crypto.Mac;
import java.util.ArrayList;
import java.util.List;

public class EthPack_802_3 {
    byte preable[] = new byte[7];
    byte frameDelimiter;
    byte macDestination[] = new byte[6];
    byte macSource[] = new byte[6];
    byte tag802_1[] = new byte[4];
    byte etherTypeOrLen[] = new byte[2];
    ArrayList<Byte> payload = new ArrayList<Byte>(46);
    byte frameCheck[] = new byte[4];
    byte interpacketGap[] = new byte[12];

    public EthPack_802_3(byte macDestination[], byte macSource[], byte typeOrLength) {

    }

    public static EthPack_802_3.Builder builder() {
        return new Builder();
    }


    public static class Builder {
        private Builder() {

        }

        public Builder setDestinationMAC(MacAddress macAddress) {
            return this;
        }
    }

}
