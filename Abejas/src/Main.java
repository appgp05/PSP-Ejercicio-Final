import Abejas.TiposObreras.Limpiadora;
import Abejas.TiposObreras.Nodriza;
import Abejas.TiposObreras.Recolectora;
import Abejas.Zangano;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Zangano zangano1 = new Zangano();
        Zangano zangano2 = new Zangano();
        Zangano zangano3 = new Zangano();

        Limpiadora limpiadora1 = new Limpiadora();
        Limpiadora limpiadora2 = new Limpiadora();
        Limpiadora limpiadora3 = new Limpiadora();

        Nodriza nodriza1 = new Nodriza();
        Nodriza nodriza2 = new Nodriza();
        Nodriza nodriza3 = new Nodriza();

        Recolectora recolectora1 = new Recolectora();
        Recolectora recolectora2 = new Recolectora();
        Recolectora recolectora3 = new Recolectora();

        limpiadora1.start();
//        limpiadora2.start();
//        limpiadora3.start();

//        recolectora1.start();
//        recolectora2.start();
//        recolectora3.start();

    }
}