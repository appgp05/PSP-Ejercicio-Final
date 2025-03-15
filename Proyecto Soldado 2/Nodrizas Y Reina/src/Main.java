import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

public class Main {
    // Sistema de gestion de miel
    private static final Object lockMiel = new Object();
    private static int mielAlmacenada = 10;

    // Abejas que hay en la colmena
    public static Reina reina = new Reina();
    private static ArrayList<Nodriza> nodrizas = new ArrayList<>();
    private static ArrayList<Soldado> soldados = new ArrayList<>();

    public static void main(String[] args) {
        try {
            despertarReina();
            despertarNodrizas();
            despertarSoldao();

            ServerSocket socketServidor = new ServerSocket(3000);

            while(true) {
                System.out.println("-".repeat(20));

                System.out.println("Esperando una abeja");

                Socket socketCliente = socketServidor.accept();
                HiloAtenderAbeja hiloAtender = new HiloAtenderAbeja(socketCliente);
                hiloAtender.start();

                System.out.println("Atendiendo a la abeja");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    private static void despertarReina(){
        reina.start();
    }

    private static void despertarNodrizas(){
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

        for(Nodriza nodriza : nodrizas){
            nodriza.start();
        }
    }

    private static void despertarSoldao(){
        Soldado soldado1 = new Soldado(1);
        Soldado soldado2 = new Soldado(2);
        Soldado soldado3 = new Soldado(3);
        Soldado soldado4 = new Soldado(4);
        Soldado soldado5 = new Soldado(5);

        soldados.add(soldado1);
        soldados.add(soldado2);
        soldados.add(soldado3);
        soldados.add(soldado4);
        soldados.add(soldado5);

        for(Soldado soldado : soldados){
            soldado.start();
        }
    }

    public static synchronized Nodriza buscarNodrizas(){
        while(true) {
            int nodrizaRandom = new Random().nextInt(0, nodrizas.size());
                if (nodrizas.get(nodrizaRandom).isDisponible()) {
                    nodrizas.get(nodrizaRandom).setDisponible(false);
                    return nodrizas.get(nodrizaRandom);
            }
        }
    }

    public static synchronized Soldado buscarSoldado(){
        while(true) {
            int soldadoRandom = new Random().nextInt(0, soldados.size());
            if (soldados.get(soldadoRandom).isDisponible()) {
                soldados.get(soldadoRandom).setDisponible(false);
                return soldados.get(soldadoRandom);
            }
        }
    }

    public static boolean gestionarMiel(int cantidad){
        synchronized (lockMiel){
            System.out.println("MIEL ANTES DE GESTIONAR: " + mielAlmacenada);
            if(mielAlmacenada + cantidad >= 0){
                mielAlmacenada += cantidad;
                System.out.println("Miel almacenada en la colmena: " + mielAlmacenada);
                lockMiel.notifyAll();
                return true;
            } else {
                lockMiel.notifyAll();
                return false;
            }
        }
    }
}
