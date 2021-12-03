import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PokemonGenerator {

	HashMap<String,String> pokemon = new HashMap<String,String>();
	private static PokemonGenerator instance = null;

    public PokemonGenerator () {
    	try{
    		File PokemonList = new File("./src/PokemonList.txt");
    		Scanner fileScan = new Scanner(PokemonList);
			Pattern p = Pattern.compile("(\\w+|\\W)\\W(\\w+|\\W)");
			Matcher match;

    		while(fileScan.hasNextLine()){
    			String currentLine = fileScan.nextLine();
    			match = p.matcher(currentLine);

    			if(match.find()) {
					pokemon.put(match.group(1), match.group(2));
				}
    		}
    	} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static PokemonGenerator getInstance(){
		if ( instance == null ) {
			instance = new PokemonGenerator();
		}
		return instance;
	}
    
    public Pokemon generateRandomPokemon(int level) {
		int randomNum = (int)(Math.random() * pokemon.size()-1);
		ArrayList<String> temp = new ArrayList<String>(pokemon.keySet());
		String pokemonName = temp.get(randomNum);
		String pokemonType = pokemon.get(pokemonName);
		if (pokemonType.equals("Fire")){
			Pokemon p = new Fire(pokemonName, 20, 20);
			if (level >= 3){
				p = new AttackUp( new HpUp(p) );
			}
			return p;
		} else if (pokemonType.equals("Water")){
			Pokemon p = new Water(pokemonName, 20, 20);
			if (level >= 3){
				p = new AttackUp( new HpUp(p) );
				System.out.println(p.toString());
			}
			return p;
		}else if(pokemonType.equals("Grass")){
			Pokemon p = new Grass(pokemonName, 20, 20);
			if (level >= 3){
				p = new AttackUp( new HpUp(p) );
			}
			return p;
		}
		return null;
    }
    
    public Pokemon getPokemon(String name) {
		try{
			String pokemonType = pokemon.get(name);
			if (pokemonType.equals("Fire")){
				return new Fire(name, 20, 20);
			} else if (pokemonType.equals("Water")){
				return new Water(name, 20, 20);
			}else if(pokemonType.equals("Grass")){
				return new Grass(name, 20, 20);
			}
			return null;
		} catch (Exception e){
			System.out.println("Invalid Pokemon! Spelling error?");
			return null;
		}
    }

    public Pokemon addRandomBuff(Pokemon p){
        Random rand = new Random();
		int x = rand.nextInt(20);
		if(x < 11){
			Pokemon temp = new HpUp(p);
			return temp;
		} else if (x >= 11 && x < 20){
			Pokemon temp = new AttackUp(p);
			return temp;
		}
		return null;
    }

    public Pokemon addRandomDebuff(Pokemon p){
		Random rand = new Random();
		int x = rand.nextInt(20);
		if(x < 11){
			p = new HpDown(p);
		} else if (x >= 11 && x < 20){
			p = new AttackDown(p);
		}
		return p;
    }
}
