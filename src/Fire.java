public class Fire extends Pokemon{
    public Fire(String n, int h, int m){
        super(n, h, m);
    }

    @Override
    public String getAttackMenu(int atkType) {
        if(atkType == 1){
            return super.getAttackMenu(atkType);
        }else{
            String specialMenu = "1. Ember\n2. Fire Blast\n3. Fire Punch";
            return specialMenu;
        }
        
    }

    @Override
    public int getNumAttackMenuItems(int atkType) {
        return 3;
    }

    @Override
    public String getAttackString(int atkType, int move) {
        if(atkType == 1){
            return super.getAttackString(atkType,move);
        }else if (atkType == 2){
            if(move == 1){
                return "GOT ENCAPSULATED";
            }else if (move == 2){
                return "GOT TOASTED";
            }else if (move == 3){
                return "GOT HEAT PRESSED";
            }else{
                return "OFFED";
            }
        }
        return null;
    }

    /**
     * For basic attacks (if attack type is equal to 1)...
     * 1 - slam (0 - 5 dmg)
     * 2 - tackle (2 - 3 dmg)
     * 3 - punch (1 - 4 dmg)
     *
     * For special attacks (if attack type is equal to 2)...
     *
     *
     * */
    @Override
    public int getAttackDamage(int atkType, int move){
        if(atkType == 1){
            return super.getAttackDamage(atkType,move);
        }else if (atkType == 2){
            if(move == 1){
                int specialDamage = (int)(Math.random()*3)+0;
                return specialDamage;
            }else if (move == 2){
                int specialDamage = (int) (Math.random() * 4) + 1;
                return specialDamage;
            }else if (move == 3){
                int specialDamage = (int) (Math.random() * 3) + 1;
                return specialDamage;
            }
        }
        return 1;
    }

    /**
     * getType() is located in the Pokemon class
     * 0 - fire
     * 1 - water
     * 2 - grass
     * */
    @Override
    public double getAttackMultiplier(Pokemon p, int atkType){
        if (atkType == 1){
            super.getAttackMultiplier(p, atkType);
        } else if (atkType == 2){
            if(getType() == 0){
                double damage = battleTable[0][0];
                return damage;
            }
            else if(getType() == 1){
                double damage = battleTable[0][1];
                return damage;
            }
            else{//getType () == 2
                double damage = battleTable[0][2];
                return damage;
            }
        }

        return 1;

    }
}
