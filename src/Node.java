/**
 * Created by henrikmnm on 19.10.2015.
 *
 * Interface that the nodes of the algorithm shall implement. Whichever task the solution shall be implemented on
 * the nodes that the programmer chooses to use. The node interface helps the programmer in the representation of
 * states in the search algorithm.
 */
public interface Node {

    void setScore(double score);

    double returnScore();



}
