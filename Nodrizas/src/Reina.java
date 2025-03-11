import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class Reina extends Thread{
    public Reina() {
        this.disponible = true;

        try {
            this.socketServidor = new ServerSocket(2999);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean disponible;

    private ServerSocket socketServidor;

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    @Override
    public void run() {
        while(true) {
            try {
                System.out.print("-".repeat(80) + "\n");

                System.out.println("Reina - " + "Esperando a una Limpiadora");

                Socket socketCliente = socketServidor.accept();

                OutputStream outputStream = socketCliente.getOutputStream();
                DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

                System.out.println("Reina - Limpiadora encontrda");

                System.out.println("Asignando zona de limpieza");
                String nuevaZona = "Zona " + new Random().nextInt(1, 11);

                dataOutputStream.writeUTF(nuevaZona);


                System.out.println("Zona de limpieza asignada");



                System.out.println("Nodriza - Zángano alimentado");

                System.out.println("Nodriza - Descansando hasta que llegue un nuevo zángano");

                setDisponible(true);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
