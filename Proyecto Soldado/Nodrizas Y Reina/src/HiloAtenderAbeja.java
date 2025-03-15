import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class HiloAtenderAbeja extends Thread {
    private Socket socketCliente;

    public HiloAtenderAbeja(Socket socketCliente) {
        this.socketCliente = socketCliente;
    }

    @Override
    public void run() {
        try {
            InputStream inputStream = socketCliente.getInputStream();
            DataInputStream dataInputStream = new DataInputStream(inputStream);

            String tipoAbeja = dataInputStream.readUTF();
            System.out.println("Tipo de abeja: " + tipoAbeja);

            switch (tipoAbeja) {
                case "Limpiadora":
                    atenderLimpiadora();
                    break;
                case "Zangano":
                    atenderZangano();
                    break;
                case "Recolectora":
                    atenderRecolectora();
                    break;
            }

            System.out.println("Abeja atendida");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void atenderLimpiadora(){
        try {
            OutputStream outputStream = socketCliente.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

            System.out.println("Atendiendo Limpiadora - Enviando con la reina");

            dataOutputStream.writeInt(Main.reina.getPuertoServidor());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void atenderZangano(){
        try {
            InputStream inputStream = socketCliente.getInputStream();
            DataInputStream dataInputStream = new DataInputStream(inputStream);

            OutputStream outputStream = socketCliente.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

            System.out.println("Atendiendo Zangano - Enviando con el soldado");

            dataOutputStream.writeInt(Main.soldado.getIdSoldado());

            System.out.println("Atendiendo Zangano - Mirando si sabe la contrasena");

            boolean conoceLaContrasena = dataInputStream.readBoolean();

            if(!conoceLaContrasena){
                System.out.println("El Zangano no conoce la contrasena");
                return;
            } else {
                System.out.println("El Zangano conoce la contrasena");
            }

            System.out.println("Atendiendo Zangano - Buscando nodriza");
            Nodriza nodriza = Main.buscarNodrizas();
            System.out.println("Atendiendo Zangano - Nodriza encontrada");
            System.out.println("Atendiendo Zangano - Nodriza: " + nodriza.getIdNodriza());

            System.out.println("Atendiendo Zangano - Enviando con la nodriza");

            dataOutputStream.write(nodriza.getIdNodriza());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void atenderRecolectora(){
        try {
            InputStream inputStream = socketCliente.getInputStream();
            DataInputStream dataInputStream = new DataInputStream(inputStream);

            OutputStream outputStream = socketCliente.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

            System.out.println("Atendiendo Zangano - Enviando con el soldado");

            dataOutputStream.writeInt(Main.soldado.getIdSoldado());

            System.out.println("Atendiendo Zangano - Mirando si sabe la contrasena");

            boolean conoceLaContrasena = dataInputStream.readBoolean();

            if(!conoceLaContrasena){
                System.out.println("El Zangano no conoce la contrasena");
                return;
            } else {
                System.out.println("El Zangano conoce la contrasena");
            }

            System.out.println("Atendiendo Recolectora - Dejando la miel");
            Main.gestionarMiel(+1);
            System.out.println("Atendiendo Recolectora - Miel dejada");

            dataOutputStream.writeBoolean(true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
