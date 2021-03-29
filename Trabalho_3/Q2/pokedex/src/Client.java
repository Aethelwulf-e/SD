import java.rmi.Naming;
import java.util.ArrayList;
import java.util.Scanner;

public class Client {
    private static Scanner reader;
    private static InterfaceRemota refInterface = null;

    public static void main(String args[]) {
        if(System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }

        boolean c = true;
        String option;
        reader = new Scanner(System.in);

        try {
            refInterface = (InterfaceRemota) Naming.lookup("//localhost/Pokedex");

            while(c) {
                System.out.println("1 - Mostrar Pokedex");
                System.out.println("2 - Batalha Pokemon");
                System.out.println("3 - Ver Status de um Pokemon");
                System.out.println("4 - Gerar time aleatório");
                System.out.println("0 - Sair");
                System.out.print(">> ");
                option = reader.nextLine();

                switch(option) {
                    case "1":
                    {
                        ArrayList<Pokemon> pokemons = refInterface.getPokedex();
                        for(int i = 0; i < pokemons.size(); i++) {
                            System.out.println(pokemons.get(i).toString());
                        }
                        break;
                    }

                    case "2":
                    {
                        String name_1;
                        String name_2;

                        System.out.print("Entre com o nome do primeiro pokemon: ");
                        name_1 = reader.nextLine();
                        System.out.print("Entre com o nome do segundo pokemon: ");
                        name_2 = reader.nextLine();

                        Pokemon winner = refInterface.Battle(name_1, name_2);

                        if(winner == null) {
                            System.out.println("Error");
                        } else {
                            System.out.println("Vencedor:");
                            System.out.println((refInterface.getPokemonStats(winner.getName())).toString());
                        }

                        break;
                    }

                    case "3":
                    {
                        String name;

                        System.out.print("Entre com o nome do pokemon: ");
                        name = reader.nextLine();

                        Pokemon pokemon = refInterface.getPokemonStats(name);

                        if(pokemon == null) {
                            System.out.println("Error");
                        } else {
                            System.out.println(pokemon.toString());
                        }

                        break;
                    }

                    case "4":
                    {
                        int numberOfPokemons;

                        System.out.println("Um time pode ter de 1 a 6 pokemons!");
                        System.out.print("Entre com o numero de pokemons do time: ");

                        numberOfPokemons = Integer.parseInt(reader.nextLine());

                        ArrayList<Pokemon> team = refInterface.GenerateRandomTeam(numberOfPokemons);

                        if(team == null) {
                            System.out.println("Erro com o numero de pokemons");
                        } else {
                            for(int i = 0; i < team.size(); i++) {
                                Pokemon pokemon = refInterface.getPokemonStats(team.get(i).getName());
                                System.out.println(pokemon.toString());
                            }
                        }
                        
                        System.out.println("Desejas salvar o time gerado em arquivo? (sim | não)");
                        
                        loop: while(true) {
                        	switch(reader.nextLine()) {
	                        	case "sim": {
	                        		String caminho = new String();
	                        		System.out.println("Digite abaixo o caminho aonde o arquivo será salvo.");
	                        		System.out.println("Caminho: ");
	                        		caminho = reader.nextLine();
	                        		refInterface.generateFileTeam(team, caminho);
	                        		break loop;
	                        	}
	                        	case "não": {
	                        		System.out.println("OK.");
	                        		break loop;
	                        	}
	                        	default: {
	                        		System.out.println("Opção inválida. Tente novamente.");
	                        	}
                        	}
                        }
                        
                        break;
                    }

                    case "0":
                    {
                        c = false;
                        break;
                    }

                    default:
                    {
                        System.out.println("Opcao Invalida...");
                        break;
                    }
                }
            }

            reader.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
