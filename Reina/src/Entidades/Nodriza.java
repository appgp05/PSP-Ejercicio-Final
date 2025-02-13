package Entidades;

public class Nodriza {
    private int id;
    private boolean disponible;

    public Nodriza(int id, boolean disponible) {
        this.id = id;
        this.disponible = disponible;
    }

    public int getId() {
        return id;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
}
