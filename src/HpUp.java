public class HpUp extends PokemonDecorator{
  @Override
  public HpUp(Pokemon p){
    super(p,"+HP",(p.getMaxHp()+2));
  }

}//end of class