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

        Zangano zangano1 = new Zangano(1, "coa171");
        Zangano zangano2 = new Zangano(2, "coa17");
        Zangano zangano3 = new Zangano(3, "coa17");
        Zangano zangano4 = new Zangano(4, "coa17");
        Zangano zangano5 = new Zangano(5, "coa17");

        Recolectora recolectora1 = new Recolectora(1, "coa171");
        Recolectora recolectora2 = new Recolectora(2, "coa171");
        Recolectora recolectora3 = new Recolectora(3, "coa17");
        Recolectora recolectora4 = new Recolectora(4, "coa171");
        Recolectora recolectora5 = new Recolectora(5, "coa171");

//        limpiadora1.start();
//        limpiadora2.start();
//        limpiadora3.start();
//        limpiadora4.start();
//        limpiadora5.start();

        zangano1.start();
        zangano2.start();
        zangano3.start();
//        zangano4.start();
//        zangano5.start();

//        recolectora1.start();
//        recolectora2.start();
//        recolectora3.start();
//        recolectora4.start();
//        recolectora5.start();
    }
}