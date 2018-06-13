package cmd.parser;

import cmd.parser.validator.IpValidator;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

@Parameters(commandDescription = "Generate mask")
public class CommandSubnet {
    @Parameter(names = {"--mask", "-M"}, validateWith = IpValidator.class, description = "Set Mask constraint")
    private String mask = "255.255.255.0";

    @Parameter(names = {"--ip", "-I"}, validateWith = IpValidator.class, description = "Set an ip belonging the network")
    private String ip = "192.168.1.1";

    public String getMask() {
        return mask;
    }

    public String getIp() {
        return ip;
    }

}
