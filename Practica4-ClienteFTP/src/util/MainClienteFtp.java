package util;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.IOException;

public class MainClienteFtp {
    public static void main(String[] args) {
        FTPClient cliente=new FTPClient();
        String serverFTP="192.168.0.118";
        String usuario="administrador";
        String clave="1234";
        System.out.println("Nos vamos a conectar a "+serverFTP);
        try {
            cliente.connect(serverFTP);
            System.out.println(cliente.getReplyString());
            int codigo=cliente.getReplyCode();
            System.out.println("Código:"+codigo);
            if (!FTPReply.isPositiveCompletion(codigo)) {
                cliente.disconnect();
                System.out.println("Conexión rechazada");
                System.exit(0);
            }
            if (!cliente.login(usuario,clave)) {
                cliente.disconnect();
                System.out.println("Conexión rechazada");
                System.exit(0);
            }
            cliente.enterLocalPassiveMode();
            FTPFile[] archivos= cliente.listFiles();
            System.out.println("Directorio actual:"+ cliente.printWorkingDirectory());
            System.out.println("lista de ficheros");
            for (FTPFile archivo : archivos) {
                System.out.println("\t" + archivo.getName() + "=>" + archivo.getType());
            }
            if (cliente.logout())
                System.out.println("Logout del servidor");
            else
                System.out.println("Error al hacer logout");

            cliente.disconnect();
            System.out.println("fin de la conexión");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}