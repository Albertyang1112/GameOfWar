package gameOfWar;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TarotCard extends Card{
    
    private byte myValue;
    private String myName;
    
    public TarotCard() throws CardException {
        this("file:img\\maj_00.jpg");
    }
    
    public TarotCard(String path) throws CardException {
        super(path);
    }
    
    private void calcCardValue(){
        String value = imagePath.substring(13,15);
        myValue = Byte.parseByte(value);
    }
    
    private void calcCardName(){
        myName = ("Major Arcana " + myValue);
    }
    
    private byte getCardValue(){
        return myValue;
    }
    
    private String getCardName(){
        return myName;
    }

    @Override
    protected void createCard(String imgPath) throws CardException {
        try{
            imagePath = imgPath;
            imgCard = new Image(imagePath);
            this.setGraphic(new ImageView(imgCard));
            calcCardValue();
            calcCardName(); 
        }
        catch(NumberFormatException exception){
            throw new CardException("createCard error using " + imgPath + " " + exception);
        }
    }
    
}
