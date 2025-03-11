//import java.io.DataOutputStream;
//import java.io.IOException;
//import java.io.OutputStream;
//import java.net.ServerSocket;
//import java.net.Socket;
//import java.util.Random;
//
//public class Reina extends Thread{
//    public Reina(){
//        this.idReina = idReina;
//
//        try {
//            this.socketServidor = new ServerSocket(3100 + idReina);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    private int idReina;
//
//    private ServerSocket socketServidor;
//
//    public int getIdReina() {
//        return idReina;
//    }
//
//    @Override
//    public void run() {
//        while(isDisponible()) {
//            try {
//                System.out.print("-".repeat(80) + "\n");
//
//                System.out.println("Nodriza " + idNodriza + " - " + "Esperando a un Zángano");
//
//                Socket socketCliente = socketServidor.accept();
//
//                OutputStream outputStream = socketCliente.getOutputStream();
//                DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
//
//                System.out.println("Nodriza " + idNodriza + " - Zángano encontrdo");
//
//                System.out.println("Cogiendo miel");
//                while(!Main.gestionarMiel(-1)){
//                    Thread.sleep(1000);
//                }
//
//                int tiempoParaLaTarea = new Random().nextInt(3, 6);
//
//                System.out.println("Nodriza " + idNodriza + " - Alimentando a un zángano. Tiempo de espera estimado: " + tiempoParaLaTarea + "s");
//
//                Thread.sleep(tiempoParaLaTarea * 1000);
//
//                dataOutputStream.writeBoolean(true);
//
//                System.out.println("Nodriza " + idNodriza + " - Zángano alimentado");
//
//                System.out.println("Nodriza " + idNodriza + " - Descansando hasta que llegue un nuevo zángano");
//
//                setDisponible(true);
////                interrupt();
////                break;
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }
//}
