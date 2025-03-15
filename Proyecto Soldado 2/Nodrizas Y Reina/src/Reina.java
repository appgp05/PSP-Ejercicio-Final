import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class Reina extends Thread {

    public Reina() {
        try {
            this.socketServidor = new ServerSocket(puertoServidor);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String contrasena = "coa17";

    private int puertoServidor = 3001;

    public int getPuertoServidor() {
        return puertoServidor;
    }

    private ServerSocket socketServidor;

    @Override
    public void run() {
        while(true){
            try {
                System.out.println("Reina - Esperando a una abeja");

                Socket socketCliente = socketServidor.accept();

                InputStream inputStream = socketCliente.getInputStream();
                DataInputStream dataInputStream = new DataInputStream(inputStream);

                OutputStream outputStream = socketCliente.getOutputStream();
                DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

                System.out.println("Reina - Ha aparecido una abeja");

                String tipoAbeja = dataInputStream.readUTF();
                System.out.println("Reina - Tipo de abeja: " + tipoAbeja);

                switch (tipoAbeja) {
                    case "Limpiadora":

                        System.out.println("Reina - Pensando la nueva zona de limpieza");
                        String nuevaZona = decirNuevaZona();

                        System.out.println("Reina - Nueva zona: " + nuevaZona);

                        System.out.println("Reina - Indicando la nueva zona a la abeja");
                        dataOutputStream.writeUTF(nuevaZona);

                        break;

                    case "Zangano":
                        System.out.println("Diciendo la contraseña");

                        dataOutputStream.writeUTF(contrasena);

                        break;

                    case "Recolectora":
                        System.out.println("Diciendo la contraseña");

                        dataOutputStream.writeUTF(contrasena);

                        break;

                    case "Soldado":
                        System.out.println("Diciendo la contraseña");

                        dataOutputStream.writeUTF(contrasena);

                        break;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public String decirNuevaZona() {
        String nuevaZona = "Zona " + new Random().nextInt(1, 11);
        return nuevaZona;
    }
}