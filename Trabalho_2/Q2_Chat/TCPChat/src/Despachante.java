public class Despachante {
    Esqueleto esqueleto = null;

    public Despachante() {
        esqueleto = new Esqueleto();
    }

    public String invoke(String msg) {
        return esqueleto.toSend(msg);
    }
}
