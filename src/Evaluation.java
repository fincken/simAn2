import java.util.ArrayList;

/**
 * Created by Fincken on 14.10.2015.
 */
public class Evaluation {
    private int k;

    public Evaluation(int k){
        this.k = k;
    }

    //Method for checking if there are more than k eggs in a row, and return appropriate score
    public void legalCheck(Carton carton){
        double totalScore = 0;
        ArrayList<ArrayList<cartonNode>> board = carton.getCarton();

        // Checks horizontally if there are more than k eggs in a row
        for (ArrayList<cartonNode> list: board) {
            int hCount = 0;
            for(cartonNode node: list){
                if(node.isEgg())
                    hCount += 1;
            }
            if(hCount<=k){
                totalScore += 50 + hCount*200;
            }
            else
                totalScore-= hCount*1000;
        }

        //Checks vertically if there are more than k eggs in a row
        for (int i = 0; i < board.size(); i++) {
            int vCount = 0;
            for (int j = 0; j < board.get(i).size(); j++) {
                if (board.get(j).get(i).isEgg())
                    vCount += 1;
            }
            if(vCount<=k)
                totalScore += 50 + vCount*200;
            else
                totalScore -= vCount*1000;

        }

        //the next 4 loops checks diagonally both ways for more than k eggs in a row
        for (int i = 0; i < board.get(0).size(); i++) {
            int d1Count = 0;
            int xCount = i;
            int yCount = board.size()-1;
            while(yCount>=i){
                if(board.get(yCount).get(xCount).isEgg())
                    d1Count += 1;
                xCount++;
                yCount--;
            }
            if(d1Count<=k)
                totalScore += 50 + d1Count*200;
            else
                totalScore -= d1Count*1000;
        }

        for (int i = board.size()-2,j=0 ; i >=0 ; i--,j++) {
            int d2Count = 0;
            int xCount = i;
            int yCount = 0;
            while(yCount<board.size()-1-j) {
                if (board.get(yCount).get(xCount).isEgg())
                    d2Count += 1;
                xCount--;
                yCount++;
            }
            if(d2Count<=k)
                totalScore += 50 + d2Count*200;
            else
                totalScore -= d2Count*1000;
        }
        for (int i = 0; i < board.size() ; i++) {
            int d3Count = 0;
            int xCount = i;
            int yCount = 0;
            while(yCount<board.size()-i){
                if(board.get(yCount).get(xCount).isEgg())
                    d3Count += 1;
                xCount++;
                yCount++;
            }
            if(d3Count <= k)
                totalScore += 50 + d3Count*200;
            else
                totalScore -= d3Count*1000;
        }

        for (int i = board.size()-2,j=1; i >=0 ; i--,j++) {
            int d4Count = 0;
            int xCount = i;
            int yCount = board.size()-1;
            while(yCount>=j) {
                if (board.get(yCount).get(xCount).isEgg())
                    d4Count += 1;
                xCount--;
                yCount--;
            }
            if(d4Count<=k)
                totalScore += 50 + d4Count*200;
            else
                totalScore -= d4Count*1000;
        }
    carton.setFScore(totalScore);
    }



}

