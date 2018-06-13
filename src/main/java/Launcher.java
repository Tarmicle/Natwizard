import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.validator.routines.InetAddressValidator;

public class Launcher {
    public static void main(String args[]){
        System.out.println("Hello");

        if(InetAddressValidator.getInstance().isValidInet4Address("192.168.1.1")){
            System.out.println("is Valid");
        }
    }
}
