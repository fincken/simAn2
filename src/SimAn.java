import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Fincken on 15.10.2015.
 */
public class SimAn {
    ArrayList<Carton> totalBoards = new ArrayList<Carton>();
    ArrayList<Carton> neighbours = new ArrayList<Carton>();
    private Carton currentCarton;
    private int maxEggs;
    private double fTarget;
    private double temperature;
    private double dT;

    public SimAn(int m,int n, int k, double fTarget, double temperature, double dT){
        simulate(m, n, k, fTarget, temperature, dT);
    }


    public void simulate(int m,int n, int k, double fTarget, double temperature, double dT){
        currentCarton = new Carton(m, n, k);
        this.maxEggs = k;
        this.fTarget = fTarget;
        this.temperature = temperature;
        this.dT = dT;
        Evaluation ev = new Evaluation(maxEggs);
        ev.legalCheck(currentCarton);
        if(currentCarton.getFScore()<0)
            simulate(m, n, k, fTarget, temperature, dT);
        System.out.println("Starting carton: \n" + currentCarton);
        System.out.println("Starting score: " + currentCarton.getFScore() + "\n\n");
        while(temperature >0){
            if(currentCarton.getFScore()>this.fTarget){
                totalBoards.add(currentCarton);
                break;
            }
            createNeighborBoards();
            for (Carton c : neighbours){
                ev.legalCheck(c);
            }

            double bestScore = 0;
            int index =0;
            for (int i = 0; i < neighbours.size(); i++) {
                if(neighbours.get(i).getFScore()> bestScore){
                    bestScore = neighbours.get(i).getFScore();
                    index = i;
                }
            }

            double q = (bestScore-currentCarton.getFScore())/currentCarton.getFScore();
            double tempP = Math.exp(-q/temperature);
            if(tempP > 1) {
                tempP = 1;
            }

            double x = new Random().nextDouble();
            if(x > tempP){
                totalBoards.add(currentCarton);
                currentCarton = neighbours.remove(index);
            }
            else {
                createNeighborBoards();
            }
            totalBoards.addAll(neighbours);
            neighbours.clear();


            temperature -= dT;
        }

        double bestScore = 0;
        int index = 0;
        for (int i = 0; i < totalBoards.size(); i++) {
            if(totalBoards.get(i).getFScore()>bestScore){
                bestScore = totalBoards.get(i).getFScore();
                index = i;
            }
        }
        System.out.println("The best board is: \n" + totalBoards.get(index).toString());
        System.out.println("with a score of: " + totalBoards.get(index).getFScore());


    }


    public void createNeighborBoards(){
        for (int i = 0; i < currentCarton.getxMax()*maxEggs; i++) {
            int x = currentCarton.getxMax();
            int y = currentCarton.getyMax();
            Carton neighbour = new Carton(x, y, maxEggs);
            for (int j = 0; j < x; j++) {
                for (int k = 0; k < y; k++) {
                    neighbour.getCarton().get(j).set(k, currentCarton.getNode(k,j).clone());
                }
            }
            int randX = new Random().nextInt(currentCarton.getxMax());
            int randY = new Random().nextInt(currentCarton.getyMax());
            if(neighbour.getNode(randX,randY).isEgg()){
                neighbour.getNode(randX,randY).removeEgg();
            }
            else
                neighbour.getNode(randX,randY).setEgg();
            this.neighbours.add(neighbour);
        }

    }


    public String toString(){
        return currentCarton.toString();
    }

    public static void main(String[] args){
        SimAn sim = new SimAn(5,5,2,0.85,3,0.001);
    }
}
