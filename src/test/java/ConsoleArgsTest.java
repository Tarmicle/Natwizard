import model.AlgorithmUtils;
import model.IPv4Address;
import model.IPv4Subnet;
import model.MacAddress;
import model.exception.InvalidMacAddressException;
import model.osi.Layer2.EthPack_802_3;
import org.junit.Assert;
import org.junit.Test;

public class ConsoleArgsTest {

    @Test
    public void stringToIntArrayTest() {
        String value = "192.168.1.2";
        int test[] = IPv4Address.stringToIntArray(value);
        Assert.assertEquals(test[0], 192);
        Assert.assertEquals(test[1], 168);
        Assert.assertEquals(test[2], 1);
        Assert.assertEquals(test[3], 2);
    }

    @Test
    public void ipAddressValue() {
        Assert.assertEquals(3_232_235_778L, new IPv4Address("192.168.1.2").binaryValue);
        Assert.assertEquals(4_294_967_295L, new IPv4Address("255.255.255.255").binaryValue);
        Assert.assertEquals(0L, new IPv4Address("0.0.0.0").binaryValue);
    }

    @Test
    public void ipSubnetByIPs() {
        Assert.assertEquals(4, new IPv4Subnet(new IPv4Address("192.168.1.1"), new IPv4Address("192.168.1.2")).availableHosts);
    }

    @Test
    public void testMostSignificantBit() {
        for (int i = 0; i <= 32; i++)
            Assert.assertEquals(i, AlgorithmUtils.findMostSignificantBit32((long) Math.pow(2, i) - 1));
    }

    @Test
    public void testSubnet() {
        IPv4Subnet subnet = new IPv4Subnet(new IPv4Address("102.168.1.20"), new IPv4Address("102.168.1.3"));
    }

    @Test
    public void macAddressErrorCheck() {
        try {
            EthPack_802_3.builder().setDestinationMAC(new MacAddress(0x02, 0x03, 0x04, 0x05, 0x06));
        } catch (InvalidMacAddressException e) {
            Assert.assertEquals(true, true);
            return;
        }
        Assert.assertEquals(false, true);
    }

    @Test
    public void subnetGenerationTest() {
        IPv4Subnet subnet = new IPv4Subnet(new IPv4Address("192.168.1.1"), new IPv4Address("192.168.1.2"));
        Assert.assertEquals(4, subnet.availableHosts);
        Assert.assertEquals("255.255.255.252", subnet.getSubnetMask().toString());
        Assert.assertEquals("192.168.1.0", subnet.getSubnetIP().toString());

        subnet = new IPv4Subnet(new IPv4Address("192.168.0.1"), new IPv4Address("192.168.1.120"));
        Assert.assertEquals(512, subnet.availableHosts);
        Assert.assertEquals("255.255.254.0", subnet.getSubnetMask().toString());
        Assert.assertEquals("192.168.0.0", subnet.getSubnetIP().toString());

        subnet = new IPv4Subnet(new IPv4Address("192.168.0.1"), 512);
        Assert.assertEquals(512, subnet.availableHosts);
        Assert.assertEquals("255.255.254.0", subnet.getSubnetMask().toString());
        Assert.assertEquals("192.168.0.0", subnet.getSubnetIP().toString());

        subnet = new IPv4Subnet(new IPv4Address("162.162.0.1"), 512);
        Assert.assertEquals(512, subnet.availableHosts);
        Assert.assertEquals("255.255.254.0", subnet.getSubnetMask().toString());
        Assert.assertEquals("162.162.0.0", subnet.getSubnetIP().toString());
    }

}