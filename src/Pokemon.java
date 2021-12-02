import java.lang.Math;
/**
 Description: the pokemon class
 */
public abstract class Pokemon extends Entity{
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
    }else{
      return null;
    }
  }

  public int getNumAttackMenuItems(int atkType){
    return 3;
  }

  public String attack(Pokemon p, int atkType, int move){
    int damage = (int) (getAttackDamage(atkType, move) * getAttackMultiplier(p,atkType)) + getAttackBonus(atkType);
    p.takeDamage(damage);

    String attackString = p.getName() + " " + getAttackString(atkType,move) + " and takes " + damage + " damage.";
    return attackString;
  }

  public String getAttackString(int atkType, int move){
    if(move == 1){
      return "is SLAMMED";
    }
    else if(move == 2){
      return "is TACKLED";
    }
    else{
      return "is PUNCHED";
    }
  }

  public int getAttackDamage(int atkType, int move){
    if(move == 1){
      int damage = (int)(Math.random()*5);
      return damage;
    } else if(move == 2){
      int damage = (int)(Math.random()*3)+2;
      return damage;
    } else{
      int damage = (int)(Math.random()*4)+1;
      return damage;
    }
  }

  public double getAttackMultiplier(Pokemon p, int atkType){
      return 1;
  }

  public int getAttackBonus(int atkType){
    return 0;
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