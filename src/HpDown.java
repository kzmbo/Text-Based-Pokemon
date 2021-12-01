public class HpDown extends PokemonDecorator{

  public HpDown(Pokemon p){
    super(p,"-HP",-1);
    p.takeDamage(2);
  }

}//end of class