package Abejas.TiposObreras;

import Abejas.Obrera;
import com.sun.tools.javac.Main;

import java.io.*;
import java.net.Socket;
import java.util.Random;

public class Limpiadora extends Obrera {
    public Limpiadora(int id) {
        this.id = id;
    }

    private int id;

    @Override
    public void run() {
        while(true){
            try {
                System.out.print("-".repeat(80) + "\n");
                System.out.println("Limpiadora " + id + " - Entrando a la recepcion de la colmena");

                Socket socket = new Socket("127.0.0.1", 3000);

                InputStream inputStream = socket.getInputStream();
                DataInputStream dataInputStream = new DataInputStream(inputStream);

                OutputStream outputStream = socket.getOutputStream();
                DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

                dataOutputStream.writeUTF("Limpiadora");

                System.out.println("Limpiadora " + id + " - Preguntando por la abeja reina");

                int reina = dataInputStream.readInt();

                socket = new Socket("127.0.0.1", reina);

                inputStream = socket.getInputStream();
                dataInputStream = new DataInputStream(inputStream);

                outputStream = socket.getOutputStream();
                dataOutputStream = new DataOutputStream(outputStream);

                dataOutputStream.writeUTF("Limpiadora");

                System.out.println("Limpiadora " + id + " - Preguntando a la reina por la nueva zona");
                String nuevaZona = dataInputStream.readUTF();
                System.out.println("Limpiadora " + id + " - Nueva zona: " + nuevaZona);

                int tiempoParaLaTarea = new Random().nextInt(3, 11);

                System.out.println("Limpiadora " + id + " - Empezando la limpieza de la zona " +  nuevaZona + ". Tiempo de espera estimado: " + tiempoParaLaTarea + "s");
                Thread.sleep(tiempoParaLaTarea * 1000);
                System.out.println("Limpiadora " + id + " - Nueva zona limpiada");

                {
                    socket.close();
                    inputStream.close();
                    dataInputStream.close();
                    outputStream.close();
                    dataOutputStream.close();
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
