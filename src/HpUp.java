public class HpUp extends PokemonDecorator{
  HpUp(Pokemon p){
    super((p.getName()+ " -HP"),(p.getHp()+2),(p.getMaxHp()+2));
  }

}//end of class