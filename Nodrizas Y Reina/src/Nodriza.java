import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class Nodriza extends Obrera {

    public Nodriza(int idNodriza) {
        this.idNodriza = idNodriza;
        this.disponible = true;

        try {
            this.socketServidor = new ServerSocket(3010 + idNodriza);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private int idNodriza;
    public int getIdNodriza() {
        return idNodriza;
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
        while(true) {
            try {
                System.out.print("-".repeat(80) + "\n");

                System.out.println("Nodriza " + idNodriza + " - " + "Esperando a un Zángano");

                Socket socketCliente = socketServidor.accept();

                OutputStream outputStream = socketCliente.getOutputStream();
                DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

                System.out.println("Nodriza " + idNodriza + " - Zángano encontrdo");

                System.out.println("Nodriza " + idNodriza + " - Cogiendo miel");
                do{
                    boolean flag = Main.gestionarMiel(-1);

                    if(flag == false){

                    } else {
                        break;
                    }

                    Thread.sleep(1000);
                }while(true);

                int tiempoParaLaTarea = new Random().nextInt(3, 6);

                System.out.println("Nodriza " + idNodriza + " - Alimentando a un zángano. Tiempo de espera estimado: " + tiempoParaLaTarea + "s");

                Thread.sleep(tiempoParaLaTarea * 1000);

                dataOutputStream.writeBoolean(true);

                System.out.println("Nodriza " + idNodriza + " - Zángano alimentado");

                System.out.println("Nodriza " + idNodriza + " - Descansando hasta que llegue un nuevo zángano");

                setDisponible(true);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
