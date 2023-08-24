package comp1110.ass2;

public class Game {
    public void Start(){

    }

    public String Over(){
        String currentGame = null;
        if(Marrakech.isGameOver(currentGame)){
            return "Game Over.";
        }
        return null;
    }

}
//player,assam/rug