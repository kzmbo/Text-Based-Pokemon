import java.util.Random;

public class Main {

    /**
     * Responsible for initializing the game.
     * It does it by asking the user for their username.
     * Secondly, the player choose their starting pokemon
     * Lastly, a loop continues to run until the game is over, or if the user quits
     * */
    public static void main(String arg[]){
        System.out.println("Prof. Oak: Hello there new trainer!");
        System.out.println("What's your name?");
        String name = CheckInput.getString();

        System.out.println("Nice to meet you, " + name);
        System.out.println("Choose your first pokemon:");
        System.out.println("1. Charmander (FIRE)");
        System.out.println("2. Bulbasaur (GRASS)");
        System.out.println("3. Squirtle (WATER)");
        int choice = CheckInput.getIntRange(1, 3);

        Trainer player;
        Pokemon playerStartingPokemon;
        PokemonGenerator generatePokemon = PokemonGenerator.getInstance();
        Map currentMap = Map.getInstance();
        char currentChar = 'a';
        int currentMapIndex = 1;

        if (choice == 1) {
            playerStartingPokemon = generatePokemon.getPokemon("Charmander");
            player = new Trainer(name, playerStartingPokemon);
        } else if (choice == 2) {
            playerStartingPokemon = generatePokemon.getPokemon("Bulbasaur");
            player = new Trainer(name, playerStartingPokemon);
        } else {
            playerStartingPokemon = generatePokemon.getPokemon("Squirtle");
            player = new Trainer(name, playerStartingPokemon);
        }
        System.out.println("I choose you..." + playerStartingPokemon.getName() + "\n");
        System.out.println("\n\nStarting Game...\n\n");
        boolean isGameRunning = true;
        while(isGameRunning){
            System.out.println(player.toString());
            System.out.println("Your Pokemon(s)");
            System.out.println(player.getPokemonList());
            currentMap.displayMap(player.getLocation());
            int playerChoice = mainMenu();
            switch (playerChoice) {
                case 1:
                    currentChar = player.goNorth();
                    break;
                case 2:
                    currentChar = player.goSouth();
                    break;
                case 3:
                    currentChar = player.goEast();
                    break;
                case 4:
                    currentChar = player.goWest();
                    break;
                case 5:
                    isGameRunning = false;
                    break;
                default:
                    System.out.println("Invalid input!");
                    break;
            }//end switch
            currentMap.reveal(player.getLocation());

            //if the game is still running, check what character the player is under.
            if(isGameRunning) {
                if (currentChar == 'n') {
                    System.out.println("No encounter found");
                } else if (currentChar == 'i') {
                    System.out.println("I found an item!");
                    int selectRandomItem = (int) (Math.random() * 4) + 1;
                    if (selectRandomItem <= 2) {
                        System.out.println("It's a potion");
                        player.receivePotion();
                    } else {
                        System.out.println("It's a pokeball");
                        player.receivePokeBall();
                    }
                    currentMap.removeOppAtLoc(player.getLocation());
                } else if (currentChar == 'w') {
                	 Random rand = new Random();
                     int level = rand.nextInt(3);
                     Pokemon wild_pokemon = generatePokemon.generateRandomPokemon(level);
                     System.out.println("A wild " + wild_pokemon.getName() + " has appeared.");
                     trainerAttack(player, wild_pokemon);
                } else if (currentChar == 'p') {
                    System.out.println("There's a stranger in the distance!");
                    System.out.println(randomEvents(player));
                    currentMap.removeOppAtLoc(player.getLocation());
                    if(player.getHp() <= 0){
                        System.out.println(player.getName() + " is dead with HP:" + player.getHp() + "/" + player.getMaxHp());
                        isGameRunning = false;
                    }
                } else if (currentChar == 'c') {
                    System.out.println("Should I head to the Pokemon Hospital or to the PokeCenter?");
                    System.out.println("1. Pokemon Hospital\n2. PokeCenter\n3. Leave City");
                    int x = CheckInput.getIntRange(1, 3);
                    if (x == 1) {
                        System.out.println("Hello there " + player.getName() + "! Lets get your pokemons healed up");
                        player.healAllPokemon();
                        System.out.println("...");
                        System.out.println("Take a look at all your pokemons.");
                        System.out.println(player.getPokemonList());
                        System.out.println("All patched up now! You go on now!");
                    }else if (x == 2){
                        store(player);
                    } else{
                        System.out.println("Leaving city. Come on " + player.getPokemon(0) + ". Let's book it.");
                    }
                    /**
                     * @author Aidan Tristen R. Angel
                     */
                } else if (currentChar == 'f') {
                	System.out.println("Oh Look! Its a Gym!");
                    System.out.println("To go to the next area you have to beat the gym leader that resides within. ");
                    System.out.println( "Be warned though, they use a random pokemon everytime!");
                    System.out.println("Do you want to challenge the gym leader?");
                    boolean leaveMap = CheckInput.getYesNo();
                    if (leaveMap) {
                    	Random rand = new Random();
                    	int level_max = 5;
                    	int level_min = 3;
                        int level = rand.nextInt(level_max - level_min)+ level_min;
                        Pokemon Gym_Pokemon = generatePokemon.generateRandomPokemon(level);
                        System.out.println();
                        System.out.println("The Gym Leader sent out a "+Gym_Pokemon.getName());
                        trainerAttack(player,Gym_Pokemon);
                    	int usablePokemon = player.getNumPokemon();
                    	for (int i = 0; i< player.getNumPokemon(); i++) {
                    		if (player.getPokemon(i).getHp()<=0) {
                    			usablePokemon -=1;
                    		}
                    	}
                    	if (usablePokemon == 0) {
                    		System.out.println("Uh Oh, looks like you lost this time, better heal up and challenge this gym when you are better prepared!");
                    	}
                    	else {
                    		System.out.println("Nice! You Beat the Gym! Time to Move on to the next Area!");
                    		System.out.println("Oh look!, your pokemon got stronger!");
                    		player.buffAllPokemon();
                        if (currentMapIndex == 3) {
                            currentMapIndex = 1;
                        } else {
                            currentMapIndex += 1;
                        }
                        currentMap.generateArea(currentMapIndex);
                        currentMap.setNextMap(true);
                    }
                }
            }//end if statement
        }
        }//end while loop
    }

    /**
     * Responsible for stating the main menu
     * Remember! N.E.S.W = Never Eat Sour Watermelon (Clockwise)
     * */
    public static int mainMenu() {
        System.out.println();
        System.out.println("Main Menu:");
        System.out.println("1. Go North");
        System.out.println("2. Go South");
        System.out.println("3. Go East");
        System.out.println("4. Go West");
        System.out.println("5. QUIT GAME");
        int choice = CheckInput.getIntRange(1, 5);
        switch (choice) {
            case 1:
                System.out.println("Heading North!\n");
                return 1;
            case 2:
                System.out.println("Heading South!\n");
                return 2;
            case 3:
                System.out.println("Heading East!\n");
                return 3;
            case 4:
                System.out.println("Heading West!\n");
                return 4;
            case 5:
                System.out.println("Game Over!\n");
                return 5;
            default:
                System.out.println("Invalid input!\n");
                break;
        }
        return 0;
    }

    public static String randomEvents (Trainer player){
        int selectEncounter = (int) (Math.random() * 12) + 1;
        String event = "";
        if( selectEncounter ==  1){
            event = "Hello there! the Poke Center is having a giveaway promotion! Here's a potion!";
            player.receivePotion();
        } else if (selectEncounter == 2) {
            event = "Hello fellow trainer! Here, take this pokeball!";
            player.receivePokeBall();
        } else if (selectEncounter == 3) {
            event = "Hello! I went to the gaming corner and got a ton of money! Here let me give you $10";
            player.receiveMoney(10);
        } else if (selectEncounter == 4) {
            event = "Ahhhhhh!! The stranger assaulted me for no reason";
            player.takeDamage(10);
        } else if (selectEncounter == 5) {
            event = "That's not a stranger! That's a herd of Tauros! they knocked you around.";
            player.takeDamage(5);
        } else if (selectEncounter == 6) {
            event = "As you chat with the stranger, you hear in the distance 'TEAM ROCKET IS BLASTING OFF AGAIN!' those people never learn huh?";
        } else if (selectEncounter == 7) {
            event = "It wasn't a stranger, it was a shiny pokemon! sadly it ran away before you could get close to catch it";
        } else if (selectEncounter == 8) {
            event = "You approach the stranger, he seems to be asleep, there is a Jigglypuff on his lap holding a mic, best you walk away now.";
        } else if (selectEncounter == 9) {
            event = "Its Professor Oak! He hands you some pokeballs to catch more pokemon.";
            player.receivePokeBall();
            player.receivePokeBall();
            player.receivePokeBall();
        } else if (selectEncounter == 10) {
            event = "It turns out to the stranger was in fact an angry pokemon!";
            player.takeDamage(5);
        } else if (selectEncounter == 11) {
            event = "It looks like the stranger is in a pokemon battle, you decide to watch, after the fight he notices you and gives you a potion for cheering her on.";
            player.receivePotion();
        } else if (selectEncounter == 12) {
            event = "It turns out its the Pokemon League Champion! They tell you they are on a stroll and ask you not to tell anyone they were there.";
        }
        return event;
    }

    /**
     * Responsible for running the battle.
     * the basic menu
     * 1 - fight
     * 2 - use potions
     * 3 - throw pokeball
     * 4 - run away
     *
     * Under the fight menu, it has...
     * 1 - Basic Attack
     * 2 - Special Attack
     *
     * Under the basic attack, it has
     * 1 - slam
     * 2 - tackle
     * 3 - punch
     *
     * Under the special attack, check the type of pokemon to see what they offer!
     * */
        public static void trainerAttack(Trainer t, Pokemon wild){
            PokemonGenerator pokemonGenerator = PokemonGenerator.getInstance();
            Pokemon wildPokemon = wild;
            int pokemonChosen = 0;
            String name = wildPokemon.getName();
            System.out.println();
            

            boolean isTrainerAttacking = true;
            while (isTrainerAttacking ){
                System.out.println(wildPokemon.getName() + " HP: " + wildPokemon.getHp() + "/" + wildPokemon.getMaxHp());
                System.out.println(t.toString());
                System.out.println("What do you want to do?");
                System.out.println("1. Fight\n2. Use Potion\n3. Throw Poke Ball\n4. Run Away");
                int choice = CheckInput.getIntRange(1, 4);
                if( choice ==  1){
                    Random random = new Random();
                    int check = 0;
                    for (int i = 0; i < t.getNumPokemon(); i++){
                        if(t.getPokemon(i).getHp() <= 0){
                            check++;
                        }
                    }
                    if(check == t.getNumPokemon()){
                        System.out.println("\nShoot. All of my pokemons are dead! Gotta heal em up");
                        break;
                    }
                    System.out.println("Choose a Pokemon: ");
                    System.out.println(t.getPokemonList());
                    pokemonChosen = CheckInput.getIntRange(1,t.getNumPokemon());
                    Pokemon attackingPokemon = t.getPokemon(pokemonChosen - 1);

                    if(t.getPokemon(pokemonChosen - 1).getHp() <= 0){
                        System.out.println("Darn! " + t.getPokemon(pokemonChosen - 1).getName() + " is downed! Can't fight");
                        break;
                    }
                    System.out.println(attackingPokemon.getName() +" I CHOSE YOU!!!\n");

                    if (wildPokemon.getHp() <= 0){
                        isTrainerAttacking = false;
                    }
                    //Trainer's Pokemon Attack
                    System.out.println(attackingPokemon.getAttackTypeMenu());
                    int atkType = CheckInput.getIntRange(1, 2);
                    System.out.println(attackingPokemon.getAttackMenu(atkType));
                    int move = CheckInput.getIntRange(1, 3);
                    System.out.println("==================================================================");
                    System.out.println("Wild " + attackingPokemon.attack(wildPokemon, atkType, move));

                    int wildPokemonDebuff = random.nextInt(100);
                    if (wildPokemonDebuff <= 25){
                        wildPokemon = pokemonGenerator.addRandomDebuff(wildPokemon);
                        System.out.println("\nNice one, " + attackingPokemon.getName() + ". " + wildPokemon.getName() + " is weaken.");
                    }

                    //Wild Pokemon Attack
                    int wildAttackType = (int) (Math.random() * wildPokemon.getNumAttackTypeMenuItems()) + 1;
                    int wildMove = (int) (Math.random() * wildPokemon.getNumAttackMenuItems(wildAttackType)) + 1;
                    System.out.println("----------------------------------------------------------------");
                    System.out.println("Your " + wild.attack(attackingPokemon, wildAttackType, wildMove));
                    int trainerPokemonDebuff = random.nextInt(100);
                    if (trainerPokemonDebuff <= 10){
                        attackingPokemon = pokemonGenerator.addRandomDebuff(attackingPokemon);
                        System.out.println("Shucks. " + wildPokemon.getName() + " has weaken our " + attackingPokemon.getName() + ". We must attack carefully.");
                    }
                    System.out.println("==================================================================\n");
                } else if (choice == 2) {
                    System.out.println("==================================================================\n");
                    if (t.hasPotion()){
                        System.out.println("Choose a Pokemon to heal: ");
                        System.out.println(t.getPokemonList());
                        System.out.println("Exit. 0");
                        System.out.println("==================================================================\n");

                        pokemonChosen = CheckInput.getIntRange(0,t.getNumPokemon());
                        Pokemon temp = t.getPokemon(pokemonChosen-1);
                        if (pokemonChosen == 0){
                            System.out.println("Not now. Let save the potion for next time.\n");
                        }else{
                            t.usePotion(pokemonChosen - 1);
                            temp = pokemonGenerator.addRandomBuff(temp);
                            t.removePokemon(pokemonChosen);
                            temp.takeDamage(temp.getMaxHp());
                            t.catchPokemon(temp);
                            t.receivePokeBall();
                        }
                    }else{
                        System.out.println("Shoot! Outta potions. Must fight carefully!\n");
                    }
                } else if (choice == 3) {
                    if (t.hasPokeball()){
                        if(t.getNumPokemon() > 7){
                            System.out.println("Choose a pokemon to release.");
                            System.out.println(t.getPokemonList());
                            int remove = CheckInput.getIntRange(1, t.getNumPokemon());
                            t.catchPokemon(wildPokemon);
                            System.out.println("Sorry, " + t.getPokemon(remove - 1).getName() + ". I have to let you free. See you around.");
                            System.out.println(t.getPokemon(remove - 1).getName() + " has been removed from your collection.");
                            t.removePokemon(remove);


                            System.out.println("You caught " + wildPokemon.getName());
                            System.out.println("--" + wildPokemon.getName() + "-- is healed");
                            System.out.println(t.toString());
                            System.out.println(t.getPokemonList());
                            isTrainerAttacking = false;
                        }
                        /**
                         * @author Aidan Tristen R. Angel
                         */
                        else if(name.contains("+ATK")){
                        	System.out.println("Hey! You can't catch this pokemon!");
                        	System.out.println("");
                        }
                        else if(name.contains("+HP")){
                        	System.out.println("Hey! You can't catch this pokemon!");
                        	System.out.println("");
                        }
                        else {
                            if (t.catchPokemon(wildPokemon)){
                                System.out.println("You caught " + wildPokemon.getName());
                                System.out.println("--" + wildPokemon.getName() + "-- is healed");
                                System.out.println(t.toString());
                                System.out.println(t.getPokemonList());
                                isTrainerAttacking = false;
                            } else {
                                System.out.println("Oh no! " + wildPokemon.getName() + " has escaped!");
                            }
                        }
                    }else{
                        System.out.println("You kidding me!! Outta pokeballs. I gotta bail outta this one.");
                        isTrainerAttacking = false;
                    }
                }
                else if(name.contains("+ATK")){
                	System.out.println("Hey! No running from this match!");
                	System.out.println("");
                }
                else if(name.contains("+HP")){
                	System.out.println("Hey! No running from this match!");
                	System.out.println("");
                }
                else {
                    System.out.println("Shucks! Let's book it!");
                    System.out.println(wildPokemon.getName() + " is running away as well!");
                    int randomDirection = (int) (Math.random() * 2) + 1;
                    if(randomDirection == 1){
                        if(t.getLocation().x + 1 == 5){
                            t.goWest();
                        }else{
                            t.goEast();
                        }
                    } else {
                        if(t.getLocation().y - 1 == -1){
                            t.goSouth();
                        }else{
                            t.goNorth();
                        }
                    }
                    isTrainerAttacking = false;
                }
            }
            //end while loop
        }//end of the trainer attack method

    /**
     * Responsible for hosting the shop
     * It displays the player's stats so that they can make the right choice.
     * -Pokeballs are $3
     * -Potions are $5
     * */
        public static void store (Trainer t){
        	final int priceOfPokeball = 3;
        	final int priceOfPotion = 5;
            boolean isTrainerShopping = true;
            while(isTrainerShopping){
                System.out.println("Welcome to the Pokemon Center!");
                System.out.println("What can I help you with?");
                System.out.println(t.toString());
                System.out.println("1. Buy Potion - $5");
                System.out.println("2. Buy Poke Ball - $3");
                System.out.println("3. EXIT SHOP");
                int storeChoice = CheckInput.getIntRange(1, 3);
                switch (storeChoice) {
                    case 1:
                        if (t.spendMoney(priceOfPotion)){
                            t.receivePotion();
                            System.out.println("Purchased a potion!");
                        } else {
                            System.out.println("Insufficient funds");
                        }
                        break;
                    case 2:
                        if (t.spendMoney(priceOfPokeball)){
                            t.receivePokeBall();
                            System.out.println("Purchased a pokeball");
                        } else {
                            System.out.println("Insufficient funds");
                        }
                        break;
                    case 3:
                        System.out.println("*EXITING SHOP*");
                        System.out.println("Thank you! Come again next time!");
                        isTrainerShopping = false;
                        break;
                    default:
                        System.out.println("Invalid input!");
                        break;
                }
                //end switch
            }
            //end loop
        }
        //end method
    }
