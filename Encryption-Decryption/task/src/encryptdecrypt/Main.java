package encryptdecrypt;

public class Main {
    public static void main(String[] args) throws Exception {
        //1. Process input
        ClientController clientController = new ClientController(args);
        //2. Process encryption
        String contents = clientController.processInput();
        //3. Process output
        clientController.processOutput(contents);

    }
}
