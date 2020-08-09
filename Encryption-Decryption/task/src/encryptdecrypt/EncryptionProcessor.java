package encryptdecrypt;

//Strategy pattern - Context class

public class EncryptionProcessor {
    private Algorithm algorithm;
    private EncDecMode mode;

    public EncryptionProcessor(Algorithm algorithm, EncDecMode mode) {
        this.algorithm = algorithm;
        this.mode = mode;
    }

    public String process(String message, int key) {
        switch (mode) {
            case DEC:
                return this.algorithm.decrypt(message, key);
            case ENC:
                return this.algorithm.encrypt(message, key);
            default:
                return null;
        }
    }
}
