package cmd.parser;

import com.beust.jcommander.JCommander;

public class Parser {
    private static final CommandSubnet cmdSubnet = new CommandSubnet();
    private static final JCommander jCommander = JCommander.newBuilder().programName("NetWizard")
            .addCommand("subnet", cmdSubnet)
            .build();

    public Parser(String... args) {
        // Init commands
        jCommander.parse(args);
    }

    public boolean hasSubnetCommand() {
        if (jCommander.getParsedCommand() == null) return false;
        return jCommander.getParsedCommand().equals("subnet");
    }

    public CommandSubnet getCmdSubnet() throws CommandNotFoundException {
        if (!hasSubnetCommand()) throw new CommandNotFoundException("Command not found exception");
        return cmdSubnet;
    }

    public static void printUsage() {
        jCommander.usage();
    }
}
