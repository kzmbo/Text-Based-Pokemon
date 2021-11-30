public class HpDown extends PokemonDecorator{

  public HpDown(Pokemon p){
    super(p,"-HP",p.getMaxHp()-2);
  }

}//end of class