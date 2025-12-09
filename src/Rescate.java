import java.util.Collections;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Rescate implements Runnable {

    private final List<Pasajero> colaPrioridad;

    public Rescate(List<Pasajero> pasajerosOrdenadosPorPrioridad) {

        this.colaPrioridad = Collections.synchronizedList(pasajerosOrdenadosPorPrioridad);
    }

    @Override
    public void run() {

        Semaphore mutex = new Semaphore(1);   // 🔒 Semáforo para la cola compartida

        Balsa acasta = new Balsa("Acasta", 1, 500, colaPrioridad, mutex);
        Balsa banff  = new Balsa("Banff", 2, 1000, colaPrioridad, mutex);
        Balsa cadiz  = new Balsa("Cadiz", 3, 2000, colaPrioridad, mutex);
        Balsa deimos = new Balsa("Deimos", 4, 4000, colaPrioridad, mutex);
        Balsa exped  = new Balsa("Expedición", 5, 8000, colaPrioridad, mutex);

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