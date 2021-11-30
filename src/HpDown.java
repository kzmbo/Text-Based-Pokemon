public class HpDown extends PokemonDecorator{
  HpDown(Pokemon p){
    super((p.getName()+ " -HP"),(p.getHp()-2),(p.getMaxHp()-2));
  }

}//end of class