import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    private static Reina reina;

    private static Socket socketCliente;

    public static void main(String[] args) {
        try {
            reina =  new Reina();

            ServerSocket socketServidor = new ServerSocket(3000);

            while(true) {
                System.out.println("Esperando una abeja");
                socketCliente = socketServidor.accept();
                System.out.println("Gestionando la abeja");

                InputStream inputStream = socketCliente.getInputStream();
                DataInputStream dataInputStream = new DataInputStream(inputStream);

                String tipoAbeja = dataInputStream.readUTF();

                manejarAbeja(tipoAbeja);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void manejarAbeja(String tipoAbeja){
        switch (tipoAbeja){
            case "Zangano":

                break;
            case "Limpiadora":

                OutputStream outputStream = null;
                try {
                    outputStream = socketCliente.getOutputStream();
                    DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

                    dataOutputStream.write(manejarLimpiadora(tipoAbeja));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "Nodriza":

                break;
            case "Recolectora":

                break;
        }
    }

    private static void manejarZangano(){

    }
    private static int manejarLimpiadora(String tipoAbeja){
        return (int) reina.ordenarAAbeja(tipoAbeja);
    }
    private static void manejarNodriza(){

    }
    private static void manejarRecolectora(){

    }
}