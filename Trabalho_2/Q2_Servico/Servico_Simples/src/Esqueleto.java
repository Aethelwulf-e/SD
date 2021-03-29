public class Esqueleto {
    Validator validator = null;

    public Esqueleto() {
        validator = new Validator();
    }

    public String toSend(String msg) {
        String s = validator.validar(msg);
        return s;
    }
}
