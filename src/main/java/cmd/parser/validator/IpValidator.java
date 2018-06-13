package cmd.parser.validator;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;
import org.apache.commons.validator.routines.InetAddressValidator;

public class IpValidator implements IParameterValidator {
    @Override
    public void validate(String name, String value) throws ParameterException {
        if (!InetAddressValidator.getInstance().isValidInet4Address(value)) {
            throw new ParameterException("Parameter " + name + " is not a valid ip4 address (found " + value + ")");
        }
    }
}
