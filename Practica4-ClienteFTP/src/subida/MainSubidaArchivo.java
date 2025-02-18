package subida;

import org.apache.commons.net.ftp.FTPClient;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class MainSubidaArchivo {
    public static void main(String[] args) throws IOException {
        FTPClient cliente = new FTPClient();
        try {
            //Conexión con el servidor FTP
            cliente.connect("192.168.0.118");
            cliente.login("administrador", "1234");

            cliente.setFileType(FTPClient.BINARY_FILE_TYPE);
            cliente.enterLocalPassiveMode();
            //Archivo que vamos a subir
            String archivo = "local.jpg";
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(archivo));

            if (cliente.storeFile("alcazar.jpg", in)) {
                System.out.println("Archivo subido correctamente.");
            } else {
                System.out.println("Error al subir el archivo.");
            }

            in.close();
            cliente.logout();
            cliente.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
