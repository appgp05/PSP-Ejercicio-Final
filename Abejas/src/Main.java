import Abejas.TiposObreras.Limpiadora;
import Abejas.TiposObreras.Recolectora;
import Abejas.Zangano;

public class Main {
    public static void main(String[] args) {
        Limpiadora limpiadora1 = new Limpiadora(1);
        Limpiadora limpiadora2 = new Limpiadora(2);
        Limpiadora limpiadora3 = new Limpiadora(3);
        Limpiadora limpiadora4 = new Limpiadora(4);
        Limpiadora limpiadora5 = new Limpiadora(5);

        Zangano zangano1 = new Zangano(1);
        Zangano zangano2 = new Zangano(2);
        Zangano zangano3 = new Zangano(3);
        Zangano zangano4 = new Zangano(4);
        Zangano zangano5 = new Zangano(5);

        Recolectora recolectora1 = new Recolectora(1);
        Recolectora recolectora2 = new Recolectora(2);
        Recolectora recolectora3 = new Recolectora(3);
        Recolectora recolectora4 = new Recolectora(4);
        Recolectora recolectora5 = new Recolectora(5);

        limpiadora1.start();
        limpiadora2.start();
        limpiadora3.start();
        limpiadora4.start();
        limpiadora5.start();

//        zangano1.start();
//        zangano2.start();
//        zangano3.start();
//        zangano4.start();
//        zangano5.start();

//        recolectora1.start();
//        recolectora2.start();
//        recolectora3.start();
//        recolectora4.start();
//        recolectora5.start();
    }
}