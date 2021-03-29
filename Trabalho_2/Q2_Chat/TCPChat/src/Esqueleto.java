import java.util.Scanner;

public class Esqueleto {
    public Esqueleto() {

    }

    public String toSend(String msg) {
        System.out.println(msg);
        Scanner sc = new Scanner(System.in);
        System.out.print(">> ");
        String s = sc.nextLine();
        return "[S]: " + s;
    }
}
