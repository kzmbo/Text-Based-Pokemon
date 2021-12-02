public class Water extends Pokemon{
	public Water(String n, int h, int m) {
		super(n,h,m);
	}
	
	 @Override
	 public String getAttackMenu(int atkType) {
		 if(atkType == 1) {
			 String basicMenu = super.getAttackMenu(1);
			 return basicMenu;
		 }
		 else if(atkType == 2) {
			 String specialMenu = "1. Watergun\n2. Bubblebeam\n 3. Waterfall";
			 return specialMenu;
		 }
		 return null;
	 }

	 @Override
	 public int getNumAttackMenuItems(int atkType) {
		 return 3;
	 }

	 @Override
	 public String getAttackString(int atkType, int move) {
	        if(atkType == 1){
	            if(move == 1){
	                return "SLAMMED";
	            }else if (move == 2){
	                return "TACKLED";
	            }else if (move == 3){
	                return "PUNCHED";
	            }else{
	                return "OFFED";
	            }
	        }else if (atkType == 2){
	            if(move == 1){
	                return "WAS SPRAYED BY A TORRENT OF WATER";
	            }else if (move == 2){
	                return "GOT LOST IN THE BUBBLES";
	            }else if (move == 3){
	                return "WAS SQUASHED BY A WATERFALL";
	            }else{
	                return "OFFED";
	            }
	        }
			return null;
	}

	@Override
	public int getAttackDamage(int atkType, int move){
		if(atkType == 1){
			int basicDamage = 0;
			if(move == 1){
				basicDamage = (int)(Math.random() * 5);
				return basicDamage;
			}else if (move == 2){
				basicDamage = (int)(Math.random() * 3) + 2;
				return basicDamage;
			}else if (move == 3){
				basicDamage = (int)(Math.random() * 4) + 1;
				return basicDamage;
			}else{
				return basicDamage;
			}
		}else if (atkType == 2){
			int specialDamage = 0;
			if(move == 1){
				specialDamage = (int)(Math.random()*5)+0;
				return specialDamage;
			}else if (move == 2){
				specialDamage = (int) (Math.random() * 3) + 1;
				return specialDamage;
			}else if (move == 3){
				specialDamage = (int) (Math.random() * 4) + 1;
				return specialDamage;
			}else{
				return specialDamage;
			}
		}
		return 0;
	}

/**
 *Done by Aidan Tristen Angel
 */
	@Override
	public double getAttackMultiplier(Pokemon p, int atkType){
		if(atkType == 1){
			super.getAttackMultiplier(p, atkType);
		} else if (atkType == 2){
			double damage = 0;
			if(getType() == 0){
				damage = Pokemon.battleTable[0][0];
			}
			else if(getType() == 1){
				damage = Pokemon.battleTable[0][1];
			}
			else{//getType () == 2
				damage = Pokemon.battleTable[0][2];
			}
			return damage;
		}
		return 0;
	}
}