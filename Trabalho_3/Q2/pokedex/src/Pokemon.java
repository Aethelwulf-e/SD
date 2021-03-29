import java.io.Serializable;

public class Pokemon implements Serializable {
    private String pokedexNumber;
    private String name;
    private String type_1;
    private String type_2;
    private int HP;
    private int attack;
    private int defense;
    private int speed;

    public Pokemon(String pokedexNumber, String name, String type_1,
                   String type_2, int HP, int attack, int defense, int speed) {
        this.pokedexNumber = pokedexNumber;
        this.name = name;
        this.type_1 = type_1;
        this.type_2 = type_2;
        this.HP = HP;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
    }

    public String getPokedexNumber() {
        return pokedexNumber;
    }

    public String getName() {
        return name;
    }

    public String getType_1() {
        return type_1;
    }

    public String getType_2() {
        return type_2;
    }

    public int getHP() {
        return HP;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getSpeed() {
        return speed;
    }

    public String toString() {
        String showDexNum = "Pokedex Number: #" + pokedexNumber + "\n";
        String showName = "Name: " + name + "\n";
        String showType_1 = "Type: " + type_1 + "\n";
        String showType_2 = "";

        if(type_2 != null) {
            showType_2 = "Type: " + type_2 + "\n";
        }

        String showHP = "HP: " + HP + "\n";
        String showAttack = "Attack: " + attack + "\n";
        String showDefense = "Defense: " + defense + "\n";
        String showSpeed = "Speed: " + speed + "\n";

        return showDexNum + showName + showType_1 + showType_2 +
                showHP + showAttack + showDefense + showSpeed;
    }
}
