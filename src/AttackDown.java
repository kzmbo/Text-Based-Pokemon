public class AttackDown extends PokemonDecorator{

    public AttackDown(Pokemon p){
        super(p, "-ATK", p.getHp());
    }

    @Override
    public int getAttackBonus(int type){
        return -1;
    }
}
