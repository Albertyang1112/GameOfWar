package gameOfWar;

public class CardException extends Exception{
    
    public CardException(){
        this("Unknown Error");
    }
    
    public CardException(String message){
        super("Card Exception: " + message);
    }
}
