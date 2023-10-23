package gameOfWar;

import java.util.ArrayList;
import java.util.Collections;

public class Deck extends ArrayList<Card> {
    
    private int myIndex;
    private static final int LAST_CARD = 52;
    
    public Deck() throws CardException {
        this("file:img\\");
    }
    
    public Deck(String path) throws CardException {
        loadCards(path);
    }
    
    private void shuffleDeck(){
        Collections.shuffle(this);
    }
    
    private void loadCards(String path) throws CardException {
        for(int i = 101; i < 153; i++){
            this.add(new PlayingCard(path + i + ".gif") {});
        }
        shuffleDeck();
    }
    
    public Card dealCard(){
        if(myIndex >= LAST_CARD){
            shuffleDeck();
            myIndex = 0;
        }
        myIndex++;
        return (Card) this.get(myIndex - 1);
    }
}
