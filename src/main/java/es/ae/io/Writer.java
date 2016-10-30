package es.ae.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import es.ae.utils.Constants;

public class Writer {

    public String writeFile(String filePath, String content) throws Exception {

        Path path = null;

        try {
            path = Files.write(Paths.get(filePath), content.getBytes());

        } catch (IOException e) {
        	throw new Exception("Error escribiendo fichero " + filePath);
        }
        return path.toString();
    }

    public String changeExtension(String filePath, String newExtension) {

        String newFile = "";

        int index = filePath.lastIndexOf(Constants.EXTENSION_SEPARATOR);
        if (index > 0) {
            newFile = filePath.replace(filePath.substring(index), Constants.EXTENSION_SEPARATOR + newExtension);
        }

        return newFile;
    }

}
