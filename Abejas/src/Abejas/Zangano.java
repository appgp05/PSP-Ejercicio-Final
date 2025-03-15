package Abejas;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Random;

public class Zangano extends Thread {
    public Zangano(int id) {
        this.id = id;
    }

    private int id;


    @Override
    public void run() {
        boolean seguirBuscando = true;
        while(seguirBuscando){
            try {
                Socket socket = null;

                System.out.print("-".repeat(80) + "\n");
                int tiempoParaLaTarea = new Random().nextInt(5, 11);

                System.out.println("Zangano " + id + " - Buscando reinas fuera de la colmena. Tiempo de espera estimado: " + tiempoParaLaTarea + "s");

                Thread.sleep(tiempoParaLaTarea * 1000);

                int reinaEncontrada = new Random().nextInt(1, 11);

                if(reinaEncontrada == 10){
                    System.out.println("Zangano " + id + " - Reina encontrada");
                    if(socket != null){
                        socket.close();
                    }
                    break;
                } else {
                    System.out.println("Zangano " + id + " - No se ha encontrado ninguna reina");
                }

                System.out.println("Zangano " + id + " - Entrando a la recepcion de la colmena");

                socket = new Socket("127.0.0.1", 3000);

                OutputStream outputStream = socket.getOutputStream();
                DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

                InputStream inputStream = socket.getInputStream();
                DataInputStream dataInputStream = new DataInputStream(inputStream);

                dataOutputStream.writeUTF("Zangano");

                System.out.println("Zangano " + id + " - Preguntando por una nodriza");

                int nodriza = dataInputStream.read();

                System.out.println("Zangano " + id + " - Nodriza encontrada: " + nodriza);

                socket = new Socket("127.0.0.1", 3010 + nodriza);

                inputStream = socket.getInputStream();
                dataInputStream = new DataInputStream(inputStream);

                System.out.println("Zangano " + id + " - Esperando el alimento");
                boolean haComido = dataInputStream.readBoolean();
                System.out.println("Zangano " + id + " - Comida ingerida");

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
