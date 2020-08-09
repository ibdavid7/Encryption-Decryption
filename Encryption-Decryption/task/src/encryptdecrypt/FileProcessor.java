package encryptdecrypt;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileProcessor {

    public static String processInput(String inPath) throws Exception {
        try {
            return Files.readString(Paths.get(inPath), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new IOException("Error File cannot be read");
        } catch (Exception e) {
            throw new Exception("Error occurred");
        }
    }

    public static void processOutput(String outPath, String message) throws Exception {
        try (PrintWriter writer = new PrintWriter(outPath, "UTF-8")) {
            writer.println(message);
        } catch (IOException e) {
            throw new IOException("Error File cannot be written to");
        } catch (Exception e) {
            throw new Exception("Error occurred");
        }
    }
}
