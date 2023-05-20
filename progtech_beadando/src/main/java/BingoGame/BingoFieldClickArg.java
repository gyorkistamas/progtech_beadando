package BingoGame;

public class BingoFieldClickArg {

    public BingoFieldClickArg(int x, int y, String buttonText) {
        this.x = x;
        this.y = y;
        this.buttonText = buttonText;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getButtonText() {
        return buttonText;
    }

    private int x;

    private int y;

    private String buttonText;


}
