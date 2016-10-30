package es.ae;

import es.ae.convert.IConvert;
import es.ae.convert.ToShellJSON;
import es.ae.convert.ToStrictJSON;
import es.ae.io.Reader;
import es.ae.io.Writer;
import es.ae.utils.Constants;

public class App {

    public static void main(String[] args) {

        if (args.length == Constants.REQUIRED_ARGS) {

            try {
				manager(args[0]);
            System.exit(Constants.EXIT_OK);
				
			} catch (Exception e) {
				System.err.println(e.getMessage());
	            System.exit(Constants.EXIT_KO);
			}


        } else {
            System.err.println("Tiene que introducir la ruta del fichero");
            System.exit(Constants.EXIT_KO);
        }
    }

    private static void manager(String filePath) throws Exception {

        if (null != filePath && !"".equalsIgnoreCase(filePath)) {

            Reader reader = new Reader();

            String doc = reader.readFile(filePath);
            String ext = reader.getExtension(filePath);

            String newDoc = "", newFile = "";
            Writer writer = new Writer();
            IConvert convert;

            if (Constants.EXTENSION_BSON.equalsIgnoreCase(ext)) {
                convert = new ToStrictJSON();
                newDoc = convert.parse(doc);
                newFile = writer.changeExtension(filePath, Constants.EXTENSION_JSON);

            } else if (Constants.EXTENSION_JSON.equalsIgnoreCase(ext)) {
                convert = new ToShellJSON();
                newDoc = convert.parse(doc);
                newFile = writer.changeExtension(filePath, Constants.EXTENSION_BSON);
            }

            String path = writer.writeFile(newFile, newDoc);
            System.out.println("Nuevo fichero generado: " + path);

        } else {
            throw new Exception("No existe el fichero");
        }
    }
}
