package cmd.parser;

public class CommandNotFoundException extends Exception {
    CommandNotFoundException(String s){
        super(s);
    }
}
