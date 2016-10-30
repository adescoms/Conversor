package es.ae.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import es.ae.utils.Constants;

public class Reader {

    public String readFile(String filePath) throws Exception {

        String content = "";

        try {
            content = new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            throw new Exception("Error leyendo fichero " + filePath);
        }
        return content;
    }

    public String getExtension(String filePath) {

        String extension = "";

        int index = filePath.lastIndexOf(Constants.EXTENSION_SEPARATOR);
        if (index > 0) {
            extension = filePath.substring(index + 1);
        }

        return extension;
    }

}
