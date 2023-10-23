package gameOfWar;

public class Score {
    
    private static int myLeftScore;
    private static int myRightScore;
    
    public static void addToRightScore(int intScore){
        myRightScore += intScore;
    }
    
    public static void addToRightScore(String stringScore) throws CardException {
        try{
            addToRightScore(Integer.parseInt(stringScore));
        }
        catch(NumberFormatException exception){
            throw new CardException("addToRightScore error " + exception);
        }
    }
    
    public static void addToLeftScore(int intScore){
        myLeftScore += intScore;
    }
    
    public static void addToLeftScore(String stringScore) throws CardException {
        try{
            addToLeftScore(Integer.parseInt(stringScore));
        }
        catch(NumberFormatException exception){
            throw new CardException("addToRightScore error " + exception);
        }
    }
    
    public static int getRightScore(){
        return myRightScore;
    }
    
    public static int getLeftScore(){
        return myLeftScore;
    }
    
    public static void resetScores(){
        myLeftScore = 0;
        myRightScore = 0;
    }
    
}
