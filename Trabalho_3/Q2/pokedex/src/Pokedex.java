import java.io.*;
import java.util.ArrayList;

public class Pokedex implements Serializable {
    private ArrayList<Pokemon> pokedex;

    public Pokedex() {
        pokedex = new ArrayList<Pokemon>(151);
        String fileName = "pokedex.txt";
        String line = null;

        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                String [] splitted = line.split(" ");

                if(splitted.length == 8) {
                    String dexNumber = splitted[0];
                    String name = splitted[1];
                    String type_1 = splitted[2];
                    String type_2 = splitted[3];
                    String hp = splitted[4];
                    String attack = splitted[5];
                    String defense = splitted[6];
                    String speed = splitted[7];

                    Pokemon pokemon = new Pokemon(dexNumber, name, type_1, type_2,
                            Integer.parseInt(hp), Integer.parseInt(attack), Integer.parseInt(defense),
                            Integer.parseInt(speed));

                    pokedex.add(pokemon);
                } else {
                    String dexNumber = splitted[0];
                    String name = splitted[1];
                    String type_1 = splitted[2];
                    String type_2 = null;
                    String hp = splitted[3];
                    String attack = splitted[4];
                    String defense = splitted[5];
                    String speed = splitted[6];

                    Pokemon pokemon = new Pokemon(dexNumber, name, type_1, type_2,
                            Integer.parseInt(hp), Integer.parseInt(attack), Integer.parseInt(defense),
                            Integer.parseInt(speed));

                    pokedex.add(pokemon);
                }
            }

            bufferedReader.close();
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to open file '" + fileName + "'");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error reading file '" + fileName + "'");
            e.printStackTrace();
        }
    }

    public ArrayList<Pokemon> getAllPokemon() {
        return this.pokedex;
    }

    public Pokemon getByName(String name) {
        for(int i = 0; i < pokedex.size(); i++) {
            if(pokedex.get(i).getName().equals(name)) {
                return pokedex.get(i);
            }
        }

        return null;
    }
}