import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Fincken on 13.10.2015.
 */
public class Carton {
    ArrayList<ArrayList<cartonNode>> carton;
    ArrayList<Character> init;
    private boolean firstRun = true;
    private int xMax;
    private int yMax;
    private int k;
    private double fScore;


    public Carton(int m, int n, int k){
        this.xMax = n;
        this.yMax = m;
        this.k =k;
        this.fScore = 0;
        if(this.firstRun) {
            genStart(m, n);
            createCarton(m, n);
        }
        else{
            this.carton=new ArrayList<>();
        }
    }

    public void genStart(int m, int n){
        int length = m*n;
        ArrayList<Character> board = new ArrayList<Character>();
        final int[] ints = new Random().ints(1, length).distinct().limit(m*k/2).toArray();
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        for (int i : ints){
            numbers.add(i);
        }
        for (int i = 0; i < length; i++) {
            if(numbers.contains(i))
                board.add('0');
            else
                board.add('.');
        }
        this.firstRun = false;
        this.init = board;
    }

    public void createCarton(int m, int n){
        this.carton = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            carton.add(i,new ArrayList<>());
            for (int j = 0; j < n; j++) {
                carton.get(i).add(new cartonNode(init.get(i*n+j), j,i));
            }
        }
    }

    public int getxMax(){
        return this.xMax;
    }

    public int getyMax(){return this.yMax;}

    public cartonNode getNode(int x, int y){
        return carton.get(y).get(x);
    }

    public String toString(){
        String board = "";
        for (int i = 0; i < this.carton.size(); i++) {
            for (int j = 0; j < this.carton.get(i).size(); j++) {
                board += " " + carton.get(i).get(j).toString() + " ";
            }
            board += "\n";
        }
        return board;
    }

    public void setFScore(double fScore){
        this.fScore = fScore;
    }

    public double getFScore(){
        return this.fScore;
    }

    public ArrayList<ArrayList<cartonNode>> getCarton(){
        return this.carton;
    }

}


