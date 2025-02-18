package bajada;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainBajadaAnonimo {
    public static void main(String[] args) {
        FTPClient cliente = new FTPClient();
        String serverFTP = "127.0.0.1";
        String usuario = "anonymous";
        String clave = " ";
        String archivoRemoto = "alcazar.jpg";
        String archivoLocal = "descargado.jpg";

        try {
            //Conexión al servidor
            cliente.connect(serverFTP);
            System.out.println(cliente.getReplyString());

            if (!cliente.login(usuario, clave)) {
                cliente.disconnect();
                System.out.println("Acceso anónimo rechazado.");
                System.exit(0);
            }

            cliente.enterLocalPassiveMode();
            cliente.setFileType(FTP.BINARY_FILE_TYPE);
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(archivoLocal));
            if (cliente.retrieveFile(archivoRemoto, out)) {
                System.out.println("Archivo descargado correctamente.");
            } else {
                System.out.println("Error al descargar el archivo.");
            }

            out.close();
            cliente.logout();
            cliente.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
