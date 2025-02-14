import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Main {
    private static ArrayList<Nodriza> nodrizas = new ArrayList<>();
    public static void main(String[] args) {

        Nodriza nodriza1 = new Nodriza(1);
        Nodriza nodriza2 = new Nodriza(2);
        Nodriza nodriza3 = new Nodriza(3);
        Nodriza nodriza4 = new Nodriza(4);
        Nodriza nodriza5 = new Nodriza(5);

        nodrizas.add(nodriza1);
        nodrizas.add(nodriza2);
        nodrizas.add(nodriza3);
        nodrizas.add(nodriza4);
        nodrizas.add(nodriza5);

        try {
            ServerSocket socketServidor = new ServerSocket(3001);


            while(true) {
                Socket socketCliente = socketServidor.accept();
                OutputStream outputStream = socketCliente.getOutputStream();
                DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
                System.out.println("-".repeat(20));
                System.out.println("Buscando nodriza");
                Nodriza nodriza = buscarNodrizas();
                System.out.println("Nodriza encontrada");
                System.out.println("Nodriza: " + nodriza.getIdNodriza());
                nodriza.setDisponible(false);
                nodriza.start();
                dataOutputStream.write(nodriza.getIdNodriza());
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Nodriza buscarNodrizas(){
        while(true) {
            for (Nodriza nodriza : nodrizas) {
                if (nodriza.isDisponible()) {
                    return nodriza;
                }
            }
        }
    }
}
