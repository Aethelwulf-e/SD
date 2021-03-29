public class Esqueleto {
    Calculadora calculadora = null;

    public Esqueleto() {
        calculadora = new Calculadora();
    }

    public String add(String op1, String op2) {
        double a = Double.parseDouble(op1);
        double b = Double.parseDouble(op2);
        return String.valueOf(calculadora.add(a, b));
    }

    public String sub(String op1, String op2) {
        double a = Double.parseDouble(op1);
        double b = Double.parseDouble(op2);
        return String.valueOf(calculadora.sub(a, b));
    }

    public String mult(String op1, String op2) {
        double a = Double.parseDouble(op1);
        double b = Double.parseDouble(op2);
        return String.valueOf(calculadora.mult(a, b));
    }

    public String div(String op1, String op2) {
        double a = Double.parseDouble(op1);
        double b = Double.parseDouble(op2);
        return String.valueOf(calculadora.div(a, b));
    }
}
