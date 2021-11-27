public abstract class PokemonDecorator extends Pokemon{
    private Pokemon pokemon;

    public PokemonDecorator(Pokemon p, String extraName, int extraHp){
        super(p.getName(), p.getHp(), p.getHp() + 10);
        this.pokemon = p;
    }

    @Override
    public String getAttackMenu(int atkType){
        return pokemon.getAttackMenu(atkType);
    }

    @Override
    public int getNumAttackMenuItems(int atkType){
        return pokemon.getNumAttackMenuItems(atkType);
    }

    @Override
    public String getAttackString(int atkType, int move){
        return pokemon.getAttackString(atkType, move);
    }

    @Override
    public int getAttackDamage(int atkType, int move){
        return pokemon.getAttackDamage(atkType, move);
    }

    @Override
    public int getAttackMultiplier(Pokemon p, int type){
        return pokemon.getAttackMultiplier(p, type);
    }

    //no clue what this method does.
//    public int getAttackBonus(int type){
//        return
//    }
}
