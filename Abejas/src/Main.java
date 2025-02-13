import Abejas.TiposObreras.Limpiadora;
import Abejas.TiposObreras.Nodriza;
import Abejas.TiposObreras.Recolectora;
import Abejas.Zangano;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Limpiadora limpiadora1 = new Limpiadora();
        Limpiadora limpiadora2 = new Limpiadora();
        Limpiadora limpiadora3 = new Limpiadora();

        Zangano zangano1 = new Zangano();
        Zangano zangano2 = new Zangano();
        Zangano zangano3 = new Zangano();

        Nodriza nodriza1 = new Nodriza(1);
        Nodriza nodriza2 = new Nodriza(2);
        Nodriza nodriza3 = new Nodriza(3);
        Nodriza nodriza4 = new Nodriza(4);
        Nodriza nodriza5 = new Nodriza(5);

        Recolectora recolectora1 = new Recolectora();
        Recolectora recolectora2 = new Recolectora();
        Recolectora recolectora3 = new Recolectora();

//        limpiadora1.start();
//        limpiadora2.start();
//        limpiadora3.start();

//        zangano1.start();
//        zangano2.start();
//        zangano3.start();

        nodriza1.start();
//        nodriza2.start();
//        nodriza3.start();
//        nodriza4.start();
//        nodriza5.start();

//        recolectora1.start();
//        recolectora2.start();
//        recolectora3.start();

    }
}