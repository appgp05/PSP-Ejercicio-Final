package Abejas.TiposObreras;

import Abejas.Obrera;

import java.io.*;
import java.net.Socket;
import java.util.Random;

public class Limpiadora extends Obrera {

    @Override
    public void run() {
        main();
    }

    public void main(){
        while(true){
            int nuevaZona = -1;
            int tiempoParaLaTarea = 0;
            if(nuevaZona == -1) {
                tiempoParaLaTarea = new Random().nextInt(3, 11);
            }

            try {
                System.out.println("Empezando");
                Thread.sleep(tiempoParaLaTarea * 1000);
                System.out.println("Terminando");

                Socket socket = new Socket("127.0.0.1", 3000);

                OutputStream outputStream = socket.getOutputStream();
                DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

                InputStream inputStream = socket.getInputStream();
                DataInputStream dataInputStream = new DataInputStream(inputStream);

                dataOutputStream.writeUTF("Limpiadora");
                nuevaZona = dataInputStream.read();

                System.out.println("Limpiando la zona " + nuevaZona);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            System.out.println(tiempoParaLaTarea);
        }
    }
}
