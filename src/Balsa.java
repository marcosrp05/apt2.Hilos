import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Balsa extends Thread {

    private final String nombre;
    private final int capacidad;
    private final long tiempoRescateMs;

    private final List<Pasajero> colaCompartida;
    private final Semaphore mutex;

    public Balsa(String nombre, int capacidad, long tiempoRescateMs,
                 List<Pasajero> colaCompartida, Semaphore mutex) {

        this.nombre = nombre;
        this.capacidad = capacidad;
        this.tiempoRescateMs = tiempoRescateMs;
        this.colaCompartida = colaCompartida;
        this.mutex = mutex;
    }

    @Override
    public void run() {

        while (true) {

            List<Pasajero> rescatadosEnViaje = new ArrayList<>();

            try {
                mutex.acquire(); // 🔒 Bloqueo de la cola con semáforo

                for (int i = 0; i < capacidad && !colaCompartida.isEmpty(); i++) {
                    rescatadosEnViaje.add(colaCompartida.remove(0));
                }

                mutex.release(); // 🔓 Libera acceso

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }

            if (rescatadosEnViaje.isEmpty()) break;

            if (rescatadosEnViaje.size() == capacidad) {
                System.out.println("La balsa " + nombre + " está llena. Pasajeros: " +
                        idsToString(rescatadosEnViaje));
            } else {
                System.out.println("Último viaje de la balsa " + nombre + ". Pasajeros: " +
                        idsToString(rescatadosEnViaje));
            }

            try {
                Thread.sleep(tiempoRescateMs);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }

        System.out.println("La balsa " + nombre + " ha terminado");
    }

    private String idsToString(List<Pasajero> lista) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < lista.size(); i++) {
            sb.append(lista.get(i).getId());
            if (i < lista.size() - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}
