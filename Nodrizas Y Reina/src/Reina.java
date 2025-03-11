import java.util.Random;

public class Reina{

    public Reina() {
        this.disponible = true;
    }

    private boolean disponible;

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public int decirNuevaZona() {
        int nuevaZona = new Random().nextInt(1, 11);
        setDisponible(true);
        return nuevaZona;
    }
}