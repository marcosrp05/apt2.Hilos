import java.util.*;

public class Barco {

    private final List<Pasajero> pasajeros = new ArrayList<>();

    public Barco() {
        generarPasajerosAleatorios();
    }

    private void generarPasajerosAleatorios() {
        int id = 1;
        Random rand = new Random();

        // Contadores de cada categoría
        int ninos = 100;
        int adultos = 150;
        int ancianos = 80;
        int tripulacion = 22;

        while (ninos + adultos + ancianos + tripulacion > 0) {
            int categoria = rand.nextInt(4) + 1; // elige 1 a 4 aleatoriamente

            switch (categoria) {
                case 1:
                    if (ninos > 0) {
                        pasajeros.add(new Pasajero(id++, 1));
                        ninos--;
                    }
                    break;
                case 2:
                    if (adultos > 0) {
                        pasajeros.add(new Pasajero(id++, 2));
                        adultos--;
                    }
                    break;
                case 3:
                    if (ancianos > 0) {
                        pasajeros.add(new Pasajero(id++, 3));
                        ancianos--;
                    }
                    break;
                case 4:
                    if (tripulacion > 0) {
                        pasajeros.add(new Pasajero(id++, 4));
                        tripulacion--;
                    }
                    break;
            }
        }

        // Mezclar toda la lista para que el orden sea aleatorio
        Collections.shuffle(pasajeros);
    }

    public List<Pasajero> getPasajeros() {
        return pasajeros;
    }

    public boolean hayPasajeros() {
        return !pasajeros.isEmpty();
    }
}
