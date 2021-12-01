import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.HashMap;


public class PokemonGenerator extends Trainer {
    /**
     * Constructor for a trainer
     * takes in a name that the user gave, their starting pokemon, and a map.
     * Starts off the game with $25, 3 potions, and 1 pokeball
     *
     * @param n
     * @param p
     */
	
	HashMap<String,String> PokemonandTypes = new HashMap<String,String>();
	
	File PokemonList = new File("PokemonList.txt");
	Scanner filescan = new Scanner(PokemonList);
	
    public PokemonGenerator() {
        super("", null);
    }
    public Pokemon generateRandomPokemon() {
    	return null;
    }
    
    public Pokemon getPokemon() {
    	return null;
    }

    public Pokemon addRandomBuff(Pokemon p){
        return null;
    }

    public Pokemon addRandomDebuff(Pokemon p){
        return null;
    }
}
