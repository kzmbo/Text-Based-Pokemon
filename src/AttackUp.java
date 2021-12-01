public class AttackUp extends PokemonDecorator{

    public AttackUp(Pokemon p){
        super(p, "+ATK", 0);
    }

    public int getAttackBonus(){
        return (int) (Math.random() * 2) + 1;
    }
}
