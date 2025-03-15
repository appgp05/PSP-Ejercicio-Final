import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class Soldado extends Thread {
    public Soldado(int idSoldado) {
        this.idSoldado = idSoldado;
        this.disponible = true;

        try {
            this.socketServidor = new ServerSocket(3030 + idSoldado);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String contrasena;

    private int idSoldado;
    public int getIdSoldado() {
        return idSoldado;
    }

    private ServerSocket socketServidor;

    private boolean disponible;
    public boolean isDisponible() {
        return disponible;
    }
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    @Override
    public void run() {
        preguntarContrasena();

        while(true) {
            try {
                System.out.print("-".repeat(80) + "\n");

                System.out.println("Soldado " + idSoldado + " - " + "Esperando a una abeja");

                Socket socketCliente = socketServidor.accept();

                InputStream inputStream = socketCliente.getInputStream();
                DataInputStream dataInputStream = new DataInputStream(inputStream);

                OutputStream outputStream = socketCliente.getOutputStream();
                DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

                System.out.println("Soldado " + idSoldado + " - Abeja encontrada");

                System.out.println("Soldado " + idSoldado + " - Preguntando contraseña");

                String contrasenaAbeja = dataInputStream.readUTF();

                if(contrasenaAbeja .equals(contrasena)) {
                    System.out.println("Soldado " + idSoldado + " - La abeja conoce la contrasena");
                    dataOutputStream.writeBoolean(true);
                } else {
                    System.out.println("Soldado " + idSoldado + " - La abeja no conoce la contrasena");
                    dataOutputStream.writeBoolean(false);
                }

                setDisponible(true);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void preguntarContrasena(){
        try {
            Socket socket = new Socket("127.0.0.1", 3001);

            InputStream inputStream = socket.getInputStream();
            DataInputStream dataInputStream = new DataInputStream(inputStream);

            OutputStream outputStream = socket.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

            dataOutputStream.writeUTF("Soldado");

            String contrasena = dataInputStream.readUTF();

            this.contrasena = contrasena;

            {
                socket.close();
                inputStream.close();
                dataInputStream.close();
                outputStream.close();
                dataOutputStream.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
