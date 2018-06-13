

import cmd.parser.CommandSubnet;
import cmd.parser.Parser;
import com.beust.jcommander.ParameterException;
import model.IPv4Address;
import model.IPv4Subnet;

import java.util.ArrayList;
import java.util.List;

public class NetWizard {
    private List<String> parameters = new ArrayList<>();

    public static void main(String args[]) throws Exception {
        try {
            Parser parser = new Parser("subnet", "-M", "255.255.255.240", "-I", "192.168.188.230");
            if (parser.hasSubnetCommand()) {
                CommandSubnet cmd = parser.getCmdSubnet();
                IPv4Address ip = new IPv4Address(cmd.getIp());
                IPv4Address mask = new IPv4Address(cmd.getMask());
                IPv4Subnet res = new IPv4Subnet(ip,22);

            } else Parser.printUsage();
        } catch (ParameterException e) {
            System.out.println(e.getMessage());
            e.usage();
            e.printStackTrace();
        }
    }
}

