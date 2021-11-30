public class AttackDown extends PokemonDecorator{
    @Override
    public AttackDown(Pokemon p){
        super(p, "-ATK", p.getHp());
    }

    @Override
    public int getAttackBonus(int type){
        return -1;
    }
}
