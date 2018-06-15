package model;

import model.exception.InvalidMaskException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class IPv4Subnet {
    final static long MAX_32_BIT = (long) Math.pow(2, 32) - 1;


    private final List<IPv4Address> hosts = new ArrayList<>();;

    private IPv4Address subnetIP;
    private final IPv4Mask subnetMask;
    public final int availableHosts;

    public int getAvailableHosts() {
        return availableHosts;
    }

    public IPv4Address getSubnetIP() {
        return subnetIP;
    }

    public IPv4Address getSubnetMask() {
        return subnetMask;
    }

    public IPv4Subnet(IPv4Address network, int numOfHosts) {

        if ((numOfHosts & (numOfHosts - 1)) != 0) {
            availableHosts = (int) Math.pow(2, 1 + log2(numOfHosts));
        } else availableHosts = (int) Math.pow(2, log2(numOfHosts));
        subnetMask = new IPv4Mask(32 - log2(availableHosts));

        hosts.add(network);
        subnetIP = hosts.get(0).newApplyingMask(subnetMask);
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

        subnetMask = new IPv4Mask(32 - AlgorithmUtils.findMostSignificantBit32((int) (max.binaryValue ^ min.binaryValue)));
        availableHosts = (int) Math.pow(2, 32 - subnetMask.slashNotation);

        hosts.addAll(Arrays.asList(iPv4Addresses));
        subnetIP = hosts.get(0).newApplyingMask(subnetMask);
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

    public int getMask() {
        return subnetMask.slashNotation;
    }


}
