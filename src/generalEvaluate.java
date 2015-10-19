/**
 * Created by henrikmnm on 19.10.2015.
 *
 * Interface that the evaluation class shall implement. This is used to evaluate and give score to solutions that
 * the algorithm generates, and helps the algorithm in choosing the best solution.
 */
public interface generalEvaluate {

    void calculateScore(Node node);

}
