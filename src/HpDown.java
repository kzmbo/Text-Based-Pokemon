public class HpDown extends PokemonDecorator{
  @Override
  public HpDown(Pokemon p){
    super(p,"-HP",p.getMaxHp()-2);
  }

}//end of class