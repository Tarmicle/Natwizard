package model;

import java.util.List;

public class IPv4Subnet {
    public final int availableHosts;
    private final int mask;
    IPv4Address[] hosts;

    public IPv4Subnet(IPv4Address network, int numOfHosts) {
        if ((numOfHosts & (numOfHosts - 1)) != 0) {
            availableHosts = (int) Math.pow(2, 1 + log2(numOfHosts));
        } else availableHosts = (int) Math.pow(2, log2(numOfHosts));
        mask = 0;
    }

    public IPv4Subnet(IPv4Address... iPv4Addresses) {
        IPv4Address max = new IPv4Address("0.0.0.0");
        IPv4Address min = new IPv4Address("255.255.255.255");
        for (IPv4Address a : iPv4Addresses) {
            if (a.binaryValue > max.binaryValue) {
                max = a;
            }
            if (a.binaryValue < min.binaryValue) {
                min = a;
            }
        }
        int nOfHost = (int) (max.binaryValue ^ min.binaryValue);
        mask = 32 - AlgorithmUtils.findMostSignificantBit32(nOfHost);
        hosts = iPv4Addresses;
        availableHosts = (int) Math.pow(2, mask);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("Hosts: ");
        for (IPv4Address host : hosts) {
            str.append(host).append(" ");
        }
        return str.toString();
    }

    private static int log2(int x) {
        return (int) (Math.log(x) / Math.log(2));
    }

    public int getMask(){
        return mask;
    }
}
