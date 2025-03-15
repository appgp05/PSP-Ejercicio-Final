package Abejas;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Random;

public class Zangano extends Thread {
    public Zangano(int id, String contrasena) {
        this.id = id;
        this.contrasena = contrasena;
    }

    private int id;

    private String contrasena;

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

                System.out.println("Zangano " + id + " - Preguntando por el soldado");

                int soldado = dataInputStream.readInt();
                System.out.println("asdasd" + soldado);
                Socket socketHablarSoldado = new Socket("127.0.0.1", 3030 + soldado);

                OutputStream outputStreamHablarSoldado = socketHablarSoldado.getOutputStream();
                DataOutputStream dataOutputStreamHablarSoldado = new DataOutputStream(outputStreamHablarSoldado);

                InputStream inputStreamHablarSoldado = socketHablarSoldado.getInputStream();
                DataInputStream dataInputStreamHablarSoldado = new DataInputStream(inputStreamHablarSoldado);

                System.out.println("Zangano " + id + " - Diciendo la contrasena al soldado");

                dataOutputStreamHablarSoldado.writeUTF(contrasena);

                System.out.println("Esperando la respuesta del soldado");

                boolean conoceContrasena = dataInputStreamHablarSoldado.readBoolean();

                if(!conoceContrasena){
                    System.out.println("Zangano " + id + " - No conozco la contrasena");
                    dataOutputStream.writeBoolean(conoceContrasena);
                    break;
                } else {
                    System.out.println("Zangano " + id + " - Conozco la contrasena");
                    dataOutputStream.writeBoolean(conoceContrasena);
                }

                System.out.println("Zangano " + id + " - Preguntando por una nodriza");

                int nodriza = dataInputStream.read();

                System.out.println("Zangano " + id + " - Nodriza encontrada: " + nodriza);

                socket = new Socket("127.0.0.1", 3010 + nodriza);

                inputStream = socket.getInputStream();
                dataInputStream = new DataInputStream(inputStream);

                System.out.println("Zangano " + id + " - Esperando el alimento");
                boolean mielConsumida = dataInputStream.readBoolean();
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
