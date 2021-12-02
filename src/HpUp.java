public class HpUp extends PokemonDecorator{
  HpUp(Pokemon p){
    super(p," HP",(int)(Math.random()*2)+1);
  }

}//end of class
