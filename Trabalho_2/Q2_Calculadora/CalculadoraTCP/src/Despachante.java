public class Despachante {
    Esqueleto esqueleto = null;

    public Despachante() {
        esqueleto = new Esqueleto();
    }

    public String invoke(String msg) {
        String [] splitted = msg.split(" ");
        if(msg.length() < 3) {
            return "Error";
        } else {
            String r = splitted[0];
            String op1 = splitted[1];
            String op2 = splitted[2];

            if(r.equals("+")) {
                return esqueleto.add(op1, op2);
            } else if(r.equals("-")) {
                return esqueleto.sub(op1, op2);
            } else if(r.equals("*")) {
                return esqueleto.mult(op1, op2);
            } else if(r.equals("/")) {
                return esqueleto.div(op1, op2);
            } else {
                return "Error";
            }
        }
    }
}
