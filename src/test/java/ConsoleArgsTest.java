import model.AlgorithmUtils;
import model.IPv4Address;
import model.IPv4Subnet;
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
        System.out.println(new IPv4Subnet(new IPv4Address("192.168.1.1"), new IPv4Address("192.168.1.2")).availableHosts);
    }

    @Test
    public void testMostSignificantBit() {
        for (int i = 0; i <= 32; i++)
            Assert.assertEquals(i, AlgorithmUtils.findMostSignificantBit32((long) Math.pow(2, i) - 1));
    }

    @Test
    public void testSubnet() {
        IPv4Subnet subnet = new IPv4Subnet(new IPv4Address("102.168.1.20"), new IPv4Address("102.168.1.3"));
        System.out.println(subnet.getMask());
    }

}