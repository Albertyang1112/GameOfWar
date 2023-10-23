package gameOfWar;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class PlayingCard extends Card {
    
    private byte myValue;
    private Suit mySuit;
    
    public PlayingCard() throws CardException {
        this("file:img\\155.gif");
    }
    
    public PlayingCard(String path) throws CardException {
        super(path);
    }
    
    @Override
    protected void createCard(String imgPath) throws CardException {
        try{
            imagePath = imgPath;
            imgCard = new Image(imagePath);
            this.setGraphic(new ImageView(imgCard));
            calcCardValue();
            calcCardSuit();
        }
        catch(NumberFormatException exception){
            throw new CardException("createCard error using " + imgPath + " " + exception);
        }
    }
    
    public void setCard(String imgPath) throws CardException{
        createCard(imgPath);
    }
    
    private void calcCardValue(){
        String pathNum = imagePath.substring(9, 12);
        int img = Integer.parseInt(pathNum);
        myValue = (byte)(img - (img / 13 * 13));
        for(int j = 0; j < 4; j++){
            myValue++;
            if(myValue > 14)
                myValue = 2;
        }
    }
    
    private void calcCardSuit(){
        String pathNum = imagePath.substring(9, 12);
        int suit = Integer.parseInt(pathNum);
        if(suit > 100 && suit < 114)
            this.mySuit = Suit.Diamonds;
        else if(suit > 113 && suit < 127)
            this.mySuit = Suit.Spades;
        else if(suit > 126 && suit < 140)
            this.mySuit = Suit.Hearts;
        else if(suit > 139 && suit < 153)
            this.mySuit = Suit.Spades;
    }
    
    public byte getCardValue(){
        return myValue;
    }
    
    public Suit getCardSuit(){
        return mySuit;
    }
}
