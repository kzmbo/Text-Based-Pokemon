public class HpDown extends PokemonDecorator{
/**
  Description: lowering down the hp 
  @param Pokemon p - is the pokemon we are lowering down the hp
  */
  public HpDown(Pokemon p){
    super(p," -HP",-(int)(Math.random()*2)+1);;
  }

}//end of class
