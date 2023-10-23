package gameOfWar;

import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;

public class GameOfWar extends Application {

    private final BorderPane root = new BorderPane();
    private final GridPane topPane = new GridPane();
    private final GridPane cardPane = new GridPane();
    
    private final Label lblScore = new Label("Score:");
    private final TextField tfLeftScore = new TextField("0");
    private final TextField tfRightScore = new TextField("0");
    
    private final Label lblLeftCard = new Label();
    private final Label lblRightCard = new Label();
    private final Label lblDeck = new Label();
    
    private final Label lblLeftOutput = new Label();
    private final Label lblRightOutput = new Label();
    
    private int rightValue = 0;
    private int leftValue = 0;
    private boolean rightsTurn = true;
    
    private static final byte MAX_DRAWS = 52;
    private byte cardsDrawn = 0;
    
    private Deck deck;
    
    Button btnReset = new Button("Reset");
    
    @Override
    public void start(Stage primaryStage) throws CardException {
        
        try{
            deck = new Deck();
        }
        catch(NumberFormatException exception){
            throw new CardException();
        }
        
        btnReset.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent t) {
                rightValue = 0;
                leftValue = 0;
                Score.resetScores();
                tfRightScore.setText("0");
                tfLeftScore.setText("0");
                rightsTurn = true;
                try {
                    resetCardImages();
                } 
                catch(CardException exception){
                    alertPopUp(exception.toString());
                }
                lblDeck.setDisable(false);
                cardsDrawn = 0;
            }            
        });
        
        lblDeck.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent t) {
                
                if(cardsDrawn >= MAX_DRAWS){
                    gameOver();
                    lblDeck.setDisable(true);
                    return;
                }
                
                PlayingCard imgCard = (PlayingCard) deck.dealCard();
                
                if(rightsTurn){
                    rightValue = imgCard.getCardValue();
                    lblRightCard.setGraphic(imgCard);
                    try {
                        lblLeftCard.setGraphic(new TarotCard());
                    }
                    catch(CardException exception){
                        alertPopUp(exception.toString());
                    }

                }
                else {
                    leftValue = imgCard.getCardValue();
                    lblLeftCard.setGraphic(imgCard);
                }
                
                if(rightValue > leftValue && !rightsTurn){
                    Score.addToRightScore(rightValue);
                    tfRightScore.setText(String.valueOf(Score.getRightScore()));
                }
                else if(leftValue > rightValue && !rightsTurn){
                    Score.addToLeftScore(leftValue);
                    tfLeftScore.setText(String.valueOf(Score.getLeftScore()));
                }
                rightsTurn = !rightsTurn;
                cardsDrawn++;
            }
        });
        
        Font lblFont = new Font("Verdana", 18);
        lblScore.setFont(lblFont);
        lblScore.setTextFill(Color.rgb(255,0,0));
        
        tfLeftScore.setPrefWidth(50);
        tfLeftScore.setDisable(true);
        tfRightScore.setPrefWidth(50);
        tfRightScore.setDisable(true);
        
        topPane.add(lblScore, 0, 0, 4, 1);
        topPane.add(new Label("Left:"), 0, 1);
        topPane.add(tfLeftScore, 1, 1);
        topPane.add(new Label("Right:"), 2, 1);
        topPane.add(tfRightScore, 3, 1);
        topPane.setHgap(20);
        topPane.setVgap(10);
        root.setTop(topPane);
        
        cardPane.setHgap(20.0);
        cardPane.add(lblLeftCard, 0, 0);
        cardPane.add(lblDeck, 1, 0);
        cardPane.add(lblRightCard, 2, 0);
        cardPane.setAlignment(Pos.CENTER);
        root.setCenter(cardPane);
        
        cardPane.add(lblLeftOutput, 0, 1);
        cardPane.add(lblRightOutput, 2, 1);
        
        root.setBottom(btnReset);
        
        resetCardImages();
        
        Scene scene = new Scene(root, 300, 250);
        primaryStage.setTitle("Game of War");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public void resetCardImages() throws CardException {
        try{
            Card imgLeftCard = new TarotCard("file:img\\maj_01.jpg"){};
            Card imgRightCard = new TarotCard("file:img\\maj_02.jpg"){};
            Card imgDeck = new TarotCard("file:img\\maj_03.jpg"){};     
            lblLeftCard.setGraphic(imgLeftCard);
            lblRightCard.setGraphic(imgRightCard);
            lblDeck.setGraphic(imgDeck);
        }
        catch(CardException exception){
            alertPopUp(exception.toString());
        }
        
        lblLeftOutput.setText("");
        lblRightOutput.setText("");
        lblScore.setText("Score");
    }
    
    private void gameOver(){
        if(Score.getLeftScore() > Score.getRightScore()){
            lblLeftOutput.setText("Left Wins!");
            lblRightOutput.setText("Game Over!");
        }
        else if(Score.getRightScore() > Score.getLeftScore()){
            lblLeftOutput.setText("Game Over!");
            lblRightOutput.setText("Right Wins!");
        }
        else{
            lblLeftOutput.setText("Tie!");
            lblRightOutput.setText("Tie!");
        }
        lblScore.setText("Click Reset to play again!");
    }
    
    private static void alertPopUp(String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("CardException Error");
        alert.getDialogPane().setExpandableContent(new ScrollPane(new TextArea(message)));
        alert.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
