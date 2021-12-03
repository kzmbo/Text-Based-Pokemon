public class AttackUp extends PokemonDecorator{

    public AttackUp(Pokemon p){
        super(p, "+ATK", 0);
    }

    @Override
    public int getAttackBonus(int type){
        return (int) (Math.random() * 2) + 1;
    }
}
