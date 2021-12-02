import java.lang.Math;
  /**
  Description: the pokemon class 
  */
public abstract class Pokemon extends Entity implements PokemonDecorator{
  public static final double[][] battleTable = {{1,.5,2},{2,1,.5},{.5,2,1}};

  public Pokemon(String n, int h, int m){
    super(n,h,m);
  }

  public String getAttackTypeMenu(){
    String menu = "1. Basic Attacks\n2. Special Attacks";
    return menu;
  }

  public int getNumAttackTypeMenuItems(){
    return 2;
  }

  public String getAttackMenu(int atkType){
   if(atkType == 1){
     String basicMenu = "1. Slam\n2. Tackle\n3. Punch";
     return basicMenu;
   }
   else{
     return null;
   }
  }

  public int getNumAttackMenuItems(int atkType){
    return 3;
  }

  public String attack(Pokemon p, int atkType, int move){
    int damage = ((int)*this.getAttackDamage(5,move)*this.getAttackMultiplier(p,atkType)) + this.getAttackBonus(atkType);
    return ""+this.getName()+this.getAttackString(atkType,move)+p.getName()+" and deals "+damage+" damage.";
  }

  public String getAttackString(int atkType, int move){
    if(move == 1){
      return "SLAMMED";
    }
    else if(move == 2){
      return "TACKLED";
    }
    else{
      return "PUNCHED";
    }
  }

  public int getAttackDamage(int atkType, int move){
    int damage = 0;
    if(move == 1){
      damage = (int)(Math.random()*5);
    }
    else if(move == 2){
      damage = (int)(Math.random()*3)+2;
    }
    else{
      damage = (int)(Math.random()*4)+1;
    }
    return damage;
  }

  public double getAttackMultiplier(Pokemon p, int atkType){

  }

  public int getAttackBonus(int atkType){

  }

  public int getType(){
    if(this instanceof Fire){
      return 0;
    }
    else if(this instanceof Water){
      return 1;
    }
    else{
      return 2;
    }
  }

  
}//end of the class