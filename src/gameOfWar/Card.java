package gameOfWar;

import javafx.scene.image.*;
import javafx.scene.control.*;

public abstract class Card extends Label {
    protected Image imgCard;
    protected String imagePath;
    
    public Card() throws CardException {
        this("file:img\\155.gif");
    }
    
    public Card(String cardPath) throws CardException {
        setImage(cardPath);
    }
    
    protected abstract void createCard(String imgPath) throws CardException;
    
    private void setImage(String imgPath) throws CardException {
        createCard(imgPath);
    }
    
    public Image getCardImage(){
        return imgCard;
    }
}
