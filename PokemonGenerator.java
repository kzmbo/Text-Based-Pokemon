import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Random;

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
	Pattern p = Pattern.compile("[a-zA-Z]+,[a-zA-Z]+", );
	
	
    public PokemonGenerator () {
    	try{
    		
    		
    		File PokemonList = new File("PokemonList.txt");
    		Scanner filescan = new Scanner(PokemonList);
    		
    		While(filescan.hasNextLine()){
    			String currentline = filescan.nextLine();
    			match = p.matcher(currentline);
    			
    			if(match.find()) {
    				PokemonandTypes.put(group(1), group(2));
    			}
    			
    		}
    		
    	}
    }
    
    public Pokemon generateRandomPokemon(int level) {
    	Random rand = new Random();
    	int Pokemon = rand.nextInt(PokemonandTypes.size());
    	PokemonandTypes chosen_pokemon = PokemonandTypes.get(Pokemon);
    	
    	return null;
    }
    
    public Pokemon getPokemon(String name) {
    	return null;
    }

    public Pokemon addRandomBuff(Pokemon p){
        return null;
    }

    public Pokemon addRandomDebuff(Pokemon p){
        return null;
    }
}
