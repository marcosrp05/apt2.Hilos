import java.util.ArrayList;
import java.util.List;

public class Balsa extends Thread {

    private final String nombre;
    private final int capacidad;
    private final long tiempoRescateMs;
    private final List<Pasajero> colaCompartida;

    public Balsa(String nombre, int capacidad, long tiempoRescateMs, List<Pasajero> colaCompartida) {
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.tiempoRescateMs = tiempoRescateMs;
        this.colaCompartida = colaCompartida;
    }

    @Override
    public void run() {

        while (true) {
            List<Pasajero> rescatadosEnViaje = new ArrayList<>();


            synchronized (colaCompartida) {
                for (int i = 0; i < capacidad && !colaCompartida.isEmpty(); i++) {
                    rescatadosEnViaje.add(colaCompartida.remove(0));
                }
            }


            if (rescatadosEnViaje.isEmpty()) {

                break;
            }


            if (rescatadosEnViaje.size() == capacidad) {
                System.out.println("La balsa "+nombre+" esta llena, los pasajeros rescatados son:" + idsToString(rescatadosEnViaje));
            } else {

                System.out.println("Es el último viaje de la barca "+nombre+", los pasajeros recatados son:" + idsToString(rescatadosEnViaje));
            }


            try {
                Thread.sleep(tiempoRescateMs);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }

        System.out.println("La balsa "+nombre+" ha terminado");
    }

    private String idsToString(List<Pasajero> lista) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < lista.size(); i++) {
            sb.append(lista.get(i).getId());
            if (i < lista.size() - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    public int getCapacidad() {
        return capacidad;
    }
}