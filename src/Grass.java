public class Grass extends Pokemon{
  public Grass(String n, int h, int m){
    super(n,h,m);
  }

  @Override
  public String getAttackMenu(int atkType){
    if(atkType == 1){
      super.getAttackMenu(atkType);
      return null;
    }
    else{
      String specialMenu = "1. Vine Whip\n2. Razor Leaf\n3. Solar Beam";
      return specialMenu;
    }
  }

  @Override
  public int getNumAttackMenuItems(int atkType){
    return 3;
  }

  @Override
  public String getAttackString(int atkType, int move){
    if(atkType == 1){
      if(move == 1){
       super.getAttackString(atkType,move);
    }
    else{
      if(move == 1){
        return "GOT WHIPPED";
      }
      else if (move == 2){
        return "GOT SLICED";
      }
      else{
        return "GOT BURNED BY THE SUN";
      }
    }
  }

  public int getAttackDamage(int atkType, int move){
    int damage = 0;
    if(atkType == 1){
      super.getAttackDamage(atkType,move);
      return null
    }
    else{
      if(move == 1){
        damage = (int)(Math.random()*3);
      }
      else if(move == 2){
        damage = (int)(Math.random()*4)+2;
      }
      else{
        damage = (int)(Math.random()*5);
      }
      return damage;
    }
  }

  public int getAttackMultiplier(Pokemon p, int atkType){
    int damageMult = (int)(Math.random()*3)+1;
    double mult = 0;
    if(getType() == 0){
      mult = Pokemon.battleTable[2][0];
    }
    else if(getType() == 1){
      mult = Pokemon.battleTable[2][1];
    }
    else{
      mult = Pokemon.battleTable[2][2];
    }
    damageMult *= mult;
    int newDamage = Math.round(damageMult);

    return newDamage; 
  }


}//end of class
