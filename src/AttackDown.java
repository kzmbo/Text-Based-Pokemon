public class AttackDown extends PokemonDecorator{

    public AttackDown(Pokemon p){
        super(p, "-ATK", 0);
    }

    @Override
    public int getAttackBonus(int type){
        return -1;
    }
}
