import java.util.Collections;
import java.util.List;

public class Rescate implements Runnable {

    private final List<Pasajero> colaPrioridad;

    public Rescate(List<Pasajero> pasajerosOrdenadosPorPrioridad) {

        this.colaPrioridad = Collections.synchronizedList(pasajerosOrdenadosPorPrioridad);
    }

    @Override
    public void run() {
        Balsa acasta = new Balsa("Acasta", 1, 500, colaPrioridad);        // 0.5 s -> 500 ms
        Balsa banff  = new Balsa("Banff", 2, 1000, colaPrioridad);        // 1 s -> 1000 ms
        Balsa cadiz  = new Balsa("Cadiz", 3, 2000, colaPrioridad);        // 2 s -> 2000 ms
        Balsa deimos = new Balsa("Deimos", 4, 4000, colaPrioridad);       // 4 s -> 4000 ms
        Balsa exped  = new Balsa("Expedición", 5, 8000, colaPrioridad);   // 8 s -> 8000 ms

        acasta.start();
        banff.start();
        cadiz.start();
        deimos.start();
        exped.start();

        try {
            acasta.join();
            banff.join();
            cadiz.join();
            deimos.join();
            exped.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("ERROR EN EL RESCATE");
        }

        System.out.println("SE HAN RESCATADO A TODOS LOS PASAJEROS");
    }
}
