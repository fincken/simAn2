import java.util.ArrayList;

/**
 * Created by Fincken on 14.10.2015.
 */
public class cartonNode {
    private boolean isEgg;
    private int x;
    private int y;
    private char cartonChar;

    public cartonNode(char isEgg, int x, int y){
        this.x = x;
        this.y = y;
        this.cartonChar = isEgg;
        switch (isEgg){
            case '.': this.isEgg = false;
                break;
            case '0': this.isEgg = true;
                break;
        }
    }

    public boolean isEgg() {
        return isEgg;
    }

    public void setEgg() {
        this.isEgg = true;
        this.cartonChar = '0';

    }
    public void removeEgg(){
        this.isEgg = false;
        this.cartonChar = '.';
    }

    public String toString(){
        return this.cartonChar + "";
    }

    public cartonNode clone(){
        char charClone = cartonChar;
        int x = this.x;
        int y = this.y;
        cartonNode clone = new cartonNode(charClone, x, y);
        return  clone;

    }
}
