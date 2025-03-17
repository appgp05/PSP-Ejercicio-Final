package Abejas.TiposObreras;

import Abejas.Obrera;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Random;

public class Recolectora extends Obrera {
    public Recolectora(int id) {
        this.id = id;
    }

    private int id;

    @Override
    public void run() {
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
}
