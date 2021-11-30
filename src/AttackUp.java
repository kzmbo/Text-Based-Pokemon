public class AttackUp extends PokemonDecorator{
    @Override
    public AttackUp(Pokemon p){
        super(p, "+ATK", p.getHp());
    }

    @Override
    public int getAttackBonus(){
        return (int) (Math.random() * 2) + 1;
    }
}
