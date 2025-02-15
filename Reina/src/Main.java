import Entidades.Nodriza;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    private static Reina reina;

    private static Socket socketCliente;

    private static ArrayList<Nodriza> nodrizas = new ArrayList();

    public static void main(String[] args) {
        try {
            reina =  new Reina();

            ServerSocket socketServidor = new ServerSocket(3000);

            while(true) {
                System.out.println("-".repeat(20));
                System.out.println("Reina esperando una abeja");
                socketCliente = socketServidor.accept();
                System.out.println("Reina atendiendo a la abeja");

                InputStream inputStream = socketCliente.getInputStream();
                DataInputStream dataInputStream = new DataInputStream(inputStream);

                String tipoAbeja = dataInputStream.readUTF();

                atenderAbeja(tipoAbeja);
                System.out.println("Abeja atendida");

//                System.out.println("Esperando");
//                Thread.sleep(3000);
//                System.out.println("Espera terminada");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void atenderAbeja(String tipoAbeja){
        switch (tipoAbeja){
            case "Limpiadora":
                try {
                    OutputStream outputStream = socketCliente.getOutputStream();
                    DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

                    dataOutputStream.write(manejarLimpiadora(tipoAbeja));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
//            case "Zangano":
//                try {
//                    OutputStream outputStream = socketCliente.getOutputStream();
//                    DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
//
//                    dataOutputStream.writeBoolean(manejarZangano());
//                } catch (Exception e) {
//                    throw new RuntimeException(e);
//                }
//                break;
//            case "Nodriza":
//                try {
//                    InputStream inputStream = socketCliente.getInputStream();
//                    DataInputStream dataInputStream = new DataInputStream(inputStream);
//
//                    int idNodriza = dataInputStream.readInt();
//
//                    manejarNodriza(idNodriza);
//                } catch (Exception e) {
//                    throw new RuntimeException(e);
//                }
//                break;
//            case "Recolectora":
//                OutputStream outputStream = null;
//                try {
//                    outputStream = socketCliente.getOutputStream();
//                    DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
//
//                    dataOutputStream.writeBoolean(manejarRecolectora());
//                } catch (Exception e) {
//                    throw new RuntimeException(e);
//                }
//                break;
        }
    }

    private static int manejarLimpiadora(String tipoAbeja){
        return (int) reina.ordenarAAbeja(tipoAbeja);
    }

//    private static boolean manejarZangano(){
//        return true;
//    }
//
//    private static void manejarNodriza(int id){
//        boolean nodrizaEnColmena = false;
//        for(Nodriza nodriza : nodrizas) {
//            if (nodriza.getId() == id) {
//                nodrizaEnColmena = true;
//                break;
//            }
//        }
//        if(!nodrizaEnColmena){
//            nodrizas.add(new Nodriza(id, true));
//        }
//    }
//
//    private static boolean manejarRecolectora(){
//        return true;
//    }
}