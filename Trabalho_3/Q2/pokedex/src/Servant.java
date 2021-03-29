import java.rmi.*;
import java.util.ArrayList;
import java.util.Collections;

public class Servant implements InterfaceRemota {
    private Pokedex pokedex;

    public Servant() {
        this.pokedex = new Pokedex();
    }

    public ArrayList<Pokemon> getPokedex() throws RemoteException {
        return pokedex.getAllPokemon();
    }

    public Pokemon Battle(String name_1, String name_2) throws RemoteException {
        Pokemon pokemon_1 = pokedex.getByName(name_1);
        Pokemon pokemon_2 = pokedex.getByName(name_2);

        if(pokemon_1 == null || pokemon_2 == null) {
            return null;
        }

        Pokemon winner = null;

        int hp_1 = pokemon_1.getHP();
        int attack_1 = pokemon_1.getAttack();
        int defense_1 = pokemon_1.getDefense();
        int speed_1 = pokemon_1.getSpeed();

        int hp_2 = pokemon_2.getHP();
        int attack_2 = pokemon_2.getAttack();
        int defense_2 = pokemon_2.getDefense();
        int speed_2 = pokemon_2.getSpeed();

        int damage_1 = 0;
        int damage_2 = 0;

        while(true) {
            if(speed_1 > speed_2) {
                damage_2 = attack_1 - defense_2;

                if(damage_2 <= 0) {
                    damage_2 = attack_1 / 2;
                }

                hp_2 -= damage_2;

                if(hp_2 <= 0) {
                    winner = pokemon_1;
                    break;
                }

                damage_1 = attack_2 - defense_1;

                if(damage_1 <= 0) {
                    damage_1 = attack_2 / 2;
                }

                hp_1 -= damage_1;

                if(hp_1 <= 0) {
                    winner = pokemon_2;
                    break;
                }

            } else {
                damage_1 = attack_2 - defense_1;

                if(damage_1 <= 0) {
                    damage_1 = attack_2 / 2;
                }

                hp_1 -= damage_1;

                if(hp_1 <= 0) {
                    winner = pokemon_2;
                    break;
                }

                damage_2 = attack_1 - defense_2;

                if(damage_2 <= 0) {
                    damage_2 = attack_1 / 2;
                }

                hp_2 -= damage_2;

                if(hp_2 <= 0) {
                    winner = pokemon_1;
                    break;
                }
            }
        }

        return winner;
    }

    public ArrayList<Pokemon> GenerateRandomTeam(int generateNumber) throws RemoteException {
        if(generateNumber > 6 || generateNumber <= 0) {
            return null;
        }

        ArrayList<Pokemon> allPokemons = pokedex.getAllPokemon();
        ArrayList<Pokemon> randomTeam = new ArrayList<Pokemon>(generateNumber);

        Collections.shuffle(allPokemons);

        for(int i = 0; i < generateNumber; i++) {
            randomTeam.add(allPokemons.get(i));
        }

        return randomTeam;
    }

    public Pokemon getPokemonStats(String name) throws RemoteException {
        return pokedex.getByName(name);
    }

	@Override
	public void generateFileTeam(ArrayList<Pokemon> team, String caminho) throws RemoteException {
		String theTeam = "";
		File file = new File(caminho);
		for(int i = 0; i < team.size(); i++) {            
            theTeam += team.get(i).toString() + "\n";
        }
		file.write(theTeam);
	}
}
