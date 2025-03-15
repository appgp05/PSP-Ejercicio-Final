package Abejas.TiposObreras;

import Abejas.Obrera;

import java.io.*;
import java.net.Socket;
import java.util.Random;

public class Recolectora extends Obrera {
    public Recolectora(int id, String contrasena) {
        this.id = id;
        this.contrasena = contrasena;
    }

    private int id;

    private String contrasena;

    @Override
    public void run() {
        preguntarContrasenaReina();

        System.out.println("Recolectora" + id + " - contrasena: " + contrasena);

        while(true){
            try {
                System.out.print("-".repeat(80) + "\n");

                int tiempoParaLaTarea = new Random().nextInt(4, 8);

                System.out.println("Recolectora" + id + " - Preparando miel. Tiempo de espera estimado: " + tiempoParaLaTarea + "s");

                Thread.sleep(tiempoParaLaTarea * 1000);

                System.out.println("Recolectora" + id + " - Miel preparada");

                System.out.println("Recolectora" + id + " - Entrando a la recepcion de la colmena");

                Socket socket = new Socket("127.0.0.1", 3000);

                InputStream inputStream = socket.getInputStream();
                DataInputStream dataInputStream = new DataInputStream(inputStream);

                OutputStream outputStream = socket.getOutputStream();
                DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

                dataOutputStream.writeUTF("Recolectora");

                System.out.println("Recolectora " + id + " - Preguntando por el soldado");

                int soldado = dataInputStream.readInt();
                System.out.println("asdasd" + soldado);
                Socket socketHablarSoldado = new Socket("127.0.0.1", 3030 + soldado);

                OutputStream outputStreamHablarSoldado = socketHablarSoldado.getOutputStream();
                DataOutputStream dataOutputStreamHablarSoldado = new DataOutputStream(outputStreamHablarSoldado);

                InputStream inputStreamHablarSoldado = socketHablarSoldado.getInputStream();
                DataInputStream dataInputStreamHablarSoldado = new DataInputStream(inputStreamHablarSoldado);

                System.out.println("Recolectora " + id + " - Diciendo la contrasena al soldado");

                dataOutputStreamHablarSoldado.writeUTF(contrasena);

                System.out.println("Esperando la respuesta del soldado");

                boolean conoceContrasena = dataInputStreamHablarSoldado.readBoolean();

                if(!conoceContrasena){
                    System.out.println("Recolectora " + id + " - No conozco la contrasena");
                    dataOutputStream.writeBoolean(conoceContrasena);
                    break;
                } else {
                    System.out.println("Recolectora " + id + " - Conozco la contrasena");
                    dataOutputStream.writeBoolean(conoceContrasena);
                }

                System.out.println("Recolectora" + id + " - Dejando la miel en la colmena");
                boolean mielDejada = dataInputStream.readBoolean();
                System.out.println("Recolectora" + id + " - Miel dejada");

                int tiempoDeDescanso = new Random().nextInt(2, 5);

                System.out.println("Recolectora" + id + " - Descansando. TIempo de espera estimado: " + tiempoDeDescanso + "s");
                Thread.sleep(tiempoDeDescanso * 1000);
                System.out.println("Recolectora" + id + " - Descanso terminado");

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

    private void preguntarContrasenaReina(){
        try {
            Socket socket = new Socket("127.0.0.1", 3001);

            InputStream inputStream = socket.getInputStream();
            DataInputStream dataInputStream = new DataInputStream(inputStream);

            OutputStream outputStream = socket.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

            dataOutputStream.writeUTF("Recolectora");

            this.contrasena = dataInputStream.readUTF();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}