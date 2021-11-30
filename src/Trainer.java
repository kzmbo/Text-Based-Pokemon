import java.awt.*;
import java.util.ArrayList;

public class Trainer extends Entity {
    private int money;
    private int potions;
    private int pokeballs;
    private Point location;
    private ArrayList<Pokemon> pokemon = new ArrayList<Pokemon>();

 
    public Trainer(String n, Pokemon p){
        super(n, 24, 24); //adds the trainer's info into entity.java
        pokemon.add(p); //add the trainer's first pokemon into the arraylist
        this.money = 25;
        this.potions = 3;
        this.pokeballs = 1;
        Map map = Map.getInstance();
    }

    public int getMoney(){
        return this.money;
    }

    public boolean spendMoney(int amount){
        if((this.money - amount) < 0) {
            return false;
        }else {
            this.money -= amount;
            return true;
        }
    }

    public void receiveMoney(int amount){
        this.money += amount;
    }

    public boolean hasPotion(){
        if(this.potions > 0){
            System.out.println("** I have " + potions + " potions. **");
            return true;
        }
        System.out.println("** I have " + potions + " potions. **");
        return false;
    }

    public void receivePotion(){
        this.potions += 1;
    }

    public void usePotion(int pokeIndex){
        getPokemon(pokeIndex).heal();
        this.potions -= 1;
    }

    public boolean hasPokeball(){
        if(this.pokeballs > 0) {
            return true;
        }
        return false;
    }

    public void receivePokeBall(){
        this.pokeballs += 1;
    }

    public boolean catchPokemon(Pokemon p){
        this.pokeballs -= 1;
        if(p.getHp() <= 1){
            pokemon.add(p);
            p.heal();
            map.removeOppAtLoc(getLocation());
            return true;
        }else if (p.getHp() > 1 && p.getHp() < 8){
            int chanceToCatch = (int) (Math.random() * 100) + 1;
            if (chanceToCatch <= 70){
                pokemon.add(p);
                p.heal();
                map.removeOppAtLoc(getLocation());
                return true;
            }
        } else if (p.getHp() >= 9 && p.getHp() < 20){
            int chanceToCatch = (int) (Math.random() * 100) + 1;
            if (chanceToCatch <= 30){
                pokemon.add(p);
                p.heal();
                map.removeOppAtLoc(getLocation());
                return true;
            }
        }
        return false;
    }

    public Point getLocation(){
        return this.location;
    }

    public char goNorth(){
        if(location.y - 1 == -1){
            return map.getCharAtLocation(location);
        }
        location = new Point(location.x, location.y - 1);
        return map.getCharAtLocation(location);
    }

    public char goSouth(){
        if(location.y + 1 == 5){
            return map.getCharAtLocation(location);
        }
        location = new Point(location.x, location.y + 1);
        return map.getCharAtLocation(location);
    }

    public char goEast(){
        if(location.x + 1 == 5){
            return map.getCharAtLocation(location);
        }
        location = new Point(location.x + 1, location.y);
        return map.getCharAtLocation(location);
    }

    public char goWest(){
        if(location.x - 1 == -1){
            //map.displayMap(location);
            return map.getCharAtLocation(location);
        }
        location = new Point(location.x - 1, location.y);
        return map.getCharAtLocation(location);
    }

    public int getNumPokemon(){
        int total = pokemon.size();
        return total;
    }

    public void healAllPokemon(){
        for(Pokemon i : pokemon){
            i.heal();
        }
    }

    public void buffAllPokemon(){
      for(int i = 0; i < pokemon.size(); i++){
        int counter = (int)(Math.random()*2)+1;
        if(counter == 1){
          pokemon.get(i).HpUp();
        }
        else{
          pokemon.get(i).AttackUp;
        }
      }
    }

    public void debuffAllPokemon(){
      for(int i = 0; i < pokemon.size(); i++){
        int counter = (int)(Math.random()*2)+1;
        if(counter == 1){
          pokemon.get(i).HpDown();
        }
        else{
          pokemon.get(i).AttackDown;
        }
      }
    }
    
    public Pokemon getPokemon(int index){
        return pokemon.get(index);
    }

    public String getPokemonList(){
        String listOfPokemon = "";
        int index = 1;
        for (Pokemon i : pokemon){
            listOfPokemon += index + ". " + i.getName() +  " HP: " + i.getHp() + "/" + i.getMaxHp() + "\n";
            index++;
        }
        return listOfPokemon;
    }

    public Pokemon removePokemon(int index){
      return this.pokemon.remove(index - 1);
    }
    public String toString(){
        return (getName() +
                " HP: " + getHp() +"/" + getMaxHp() + ", $" +
                money + ", " +
                potions + " potions, " +
                pokeballs + " pokeballs ");
    }
}