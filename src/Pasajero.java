public class Pasajero {
    private final int id;
    private final int prioridad;

    public Pasajero(int id, int prioridad) {
        this.id = id;
        this.prioridad = prioridad;
    }

    public int getId() {
        return id;
    }

    public int getPrioridad() {
        return prioridad;
    }

    @Override
    public String toString() {
        return "Pasajero:"+id+" tiene prioridad "+prioridad;
    }
}
