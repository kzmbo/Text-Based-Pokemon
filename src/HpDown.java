public class HpDown extends PokemonDecorator{
  HpDown(Pokemon p){
    super(p," -HP",-(int)(Math.random()*2)+1);;
  }

}//end of class
