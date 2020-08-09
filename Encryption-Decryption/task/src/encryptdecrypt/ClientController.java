package encryptdecrypt;

import kotlin.jvm.functions.Function3;

import java.util.HashMap;
import java.util.Map;

class ClientController {

    EncDecMode mode;
    String data;
    int key;
    String in;
    String out;
    Algorithm alg;
    String message;

    //Constructor + processes args input
    public ClientController(String... args) throws Exception {
        Map<String, String> map = new HashMap<>();

        for (int i = 0; i < args.length; i += 2) {
            map.put(args[i].substring(1), args[i + 1]);
        }

        Function3<String, Map, String, String> f = (s, map1, deflt) -> map.getOrDefault(s, deflt);

        this.mode = f.invoke("mode", map, "enc") == "enc" ? EncDecMode.ENC : EncDecMode.DEC;
        this.data = f.invoke("data", map, "");
        this.key = Integer.parseInt(f.invoke("key", map, "0"));
        this.in = ".\\" + f.invoke("in", map, "");
        this.out = ".\\" + f.invoke("out", map, "");

        this.alg = Algorithm.getInstance(f.invoke("alg", map, "shift"));

        if (this.in.length() > 3) {
            message = FileProcessor.processInput(in);
        } else {
            message = data;
        }
    }

    //Processor
    public String processInput() {
        EncryptionProcessor encryptionProcessor = new EncryptionProcessor(this.alg, this.mode);
        return encryptionProcessor.process(this.message, this.key);
    }

    public void processOutput(String s) throws Exception {
        if (this.out.length() > 3) {
            FileProcessor.processOutput(this.out, s);
        } else {
            System.out.println(s);
        }
    }
}
