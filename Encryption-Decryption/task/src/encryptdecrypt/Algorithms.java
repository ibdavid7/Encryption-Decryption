package encryptdecrypt;

//Strategy pattern - Strategy classes

enum EncDecMode{
    ENC,
    DEC
}

interface Encryption {

    String encrypt(String s, int key);
}

interface Decryption {

    String decrypt(String s, int key);
}

abstract class Algorithm implements Encryption, Decryption {
    public static Algorithm getInstance(String algType) {
        switch (algType) {
            case "shift":
                return new ShiftAlgorithm();
            case "unicode":
                return new UnicodeAlgorithm();
            default:
                return null;
        }
    }
}

class UnicodeAlgorithm extends Algorithm {

    @Override
    public String encrypt(String s, int key) {
        if (s.length() == 0) {
            return s;
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                char ch = s.charAt(i);
                if (ch < 32 || ch > 127) {
                    sb.append(ch);
                } else {
                    sb.append((char) (32 + (ch + key - 32) % 96));
                }
            }
            return sb.toString();
        }
    }

    @Override
    public String decrypt(String s, int key) {
        if (s.length() == 0) {
            return s;
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                char ch = s.charAt(i);
                if (ch < 32 || ch > 127) {
                    sb.append(ch);
                } else {
                    sb.append((ch - key) >= 32 ? (char) (ch - key) : (char) (128 - Math.abs(ch - key) % 96));
                }
            }
            return sb.toString();
        }
    }
}

class ShiftAlgorithm extends Algorithm {

    @Override
    public String encrypt(String s, int key) {
        if (s.length() == 0) {
            return s;
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                char ch = s.charAt(i);
                if (ch >= 65 && ch <= 90) {
                    sb.append((char) (65 + (ch + key - 65) % 26));
                } else if (ch >= 97 && ch <= 122) {
                    sb.append((char) (97 + (ch + key - 97) % 26));
                } else {
                    sb.append(ch);
                }
            }
            return sb.toString();
        }
    }

    @Override
    public String decrypt(String s, int key) {
        if (s.length() == 0) {
            return s;
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                char ch = s.charAt(i);
                if (ch >= 65 && ch <= 90) {
                    sb.append((ch - key) >= 65 ? (char) (ch - key) : (char) (91 - Math.abs(ch - key - 65) % 26));
                } else if (ch >= 97 && ch <= 122) {
                    sb.append((ch - key) >= 97 ? (char) (ch - key) : (char) (123 - Math.abs(ch - key - 97) % 26));
                } else {
                    sb.append(ch);
                }
            }
            return sb.toString();
        }
    }
}

