package bajada;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainBajada {
    public static void main(String[] args) {
        FTPClient cliente=new FTPClient();
        //login
        String serverFTP = "127.0.0.1";
        String usuario = "administrador";
        String clave = "1234";
        String archivoRemoto = "alcazar.jpg";
        String archivo = "descargado.jpg";

        try {
            cliente.connect(serverFTP);
            cliente.login(usuario, clave);
            cliente.enterLocalPassiveMode();
            cliente.setFileType(FTP.BINARY_FILE_TYPE);

            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(archivo));
            //bien
            if (cliente.retrieveFile(archivoRemoto, out)) {
                System.out.println("Archivo descargado correctamente.");
            //mal
            } else {
                System.out.println("Error al descargar el archivo.");
            }
            //fin de la descarga
            out.close();
            cliente.logout();
            cliente.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

