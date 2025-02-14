package Abejas.TiposObreras;

import Abejas.Obrera;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class Nodriza extends Obrera {

    public Nodriza(int id) {
        this.id = id;
    }

    private int id;

    @Override
    public void run() {
        while(true){
            try {
                System.out.print("-".repeat(80) + "\n");
                System.out.println("Esperando a un zángano");

                ServerSocket socketServidor = new ServerSocket(3000 + id);

                Socket socketCliente = socketServidor.accept();



                Socket socket = new Socket("127.0.0.1", 3000);

                OutputStream outputStream = socket.getOutputStream();
                DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

                InputStream inputStream = socket.getInputStream();
                DataInputStream dataInputStream = new DataInputStream(inputStream);

                dataOutputStream.writeUTF("Nodriza");
                dataOutputStream.write(id);

                System.out.println("Esperando a un zángano");
                int zangano = dataInputStream.read();
                System.out.println("Zángano: " + zangano);

                int tiempoParaLaTarea = new Random().nextInt(3, 6);

                System.out.println("Empezando la alimentación del zángano " +  zangano + ". Tiempo de espera estimado: " + tiempoParaLaTarea + "s");

                Thread.sleep(tiempoParaLaTarea * 1000);

                System.out.println("Zángano alimentado");

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

//    @Override
//    public void run() {
//        while(true){
//            try {
//                System.out.print("-".repeat(80) + "\n");
//                System.out.println("Entrando a la colmena");
//
//                Socket socket = new Socket("127.0.0.1", 3000);
//
//                OutputStream outputStream = socket.getOutputStream();
//                DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
//
//                InputStream inputStream = socket.getInputStream();
//                DataInputStream dataInputStream = new DataInputStream(inputStream);
//
//                dataOutputStream.writeUTF("Nodriza");
//                dataOutputStream.write(id);
//
//                System.out.println("Esperando a un zángano");
//                int zangano = dataInputStream.read();
//                System.out.println("Zángano: " + zangano);
//
//                int tiempoParaLaTarea = new Random().nextInt(3, 6);
//
//                System.out.println("Empezando la alimentación del zángano " +  zangano + ". Tiempo de espera estimado: " + tiempoParaLaTarea + "s");
//
//                Thread.sleep(tiempoParaLaTarea * 1000);
//
//                System.out.println("Zángano alimentado");
//
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }
}
