import java.util.Random;

public class Reina{
    public Object ordenarAAbeja(String tipoAbeja){
        Object objeto = null;

        switch (tipoAbeja){
            case "Zangano":

                break;
            case "Limpiadora":
                objeto = tratarLimpiadora();
                break;
            case "Nodriza":

                break;
            case "Recolectora":

                break;
        }
        return objeto;
    }

    public int tratarLimpiadora(){
        return new Random().nextInt(1, 10);
    }
}
