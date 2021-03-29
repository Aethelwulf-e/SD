import java.util.Random;

public class Validator {
    public Validator() {

    }

    public String validar(String cpf) {
        String a = "Elásticos duram mais quando refrigerados.";
        String b = "O tubarão é o único peixe que pode piscar com ambos os olhos.";
        String c = "Existem mais galinhas do que pessoas no mundo.";
        String d = "Um gato possui 32 músculos em cada orelha.";
        String e = "A vida útil de uma libélula é de 1 a 6 meses.";
        String f = "É impossível espirrar de olhos abertos.";
        String g = "Se você manter um peixe dourado em quarto escuro, ele eventualmente ficará branco.";
        String h = "A cadeira elétrica foi inventada por um dentista.";

        Random r = new Random();
        int rndm = r.nextInt(7);
        rndm += 1;

        if(rndm == 1) {
            return a;
        }
        else if(rndm == 2) {
            return b;
        }
        else if(rndm == 3) {
            return c;
        }
        else if(rndm == 4) {
            return d;
        }
        else if(rndm == 5) {
            return e;
        }
        else if(rndm == 6) {
            return f;
        }
        else if(rndm == 7) {
            return g;
        }
        else {
            return h;
        }
    }
}
