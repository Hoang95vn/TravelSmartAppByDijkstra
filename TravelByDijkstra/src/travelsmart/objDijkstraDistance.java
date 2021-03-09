/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package travelsmart;

/**
 *
 * @author dato
 */
public class objDijkstraDistance {
    objLine line;
    objDijkstraNode Start;
    objDijkstraNode End;
    Double Length;

    public objDijkstraDistance(objLine l, objDijkstraNode start, objDijkstraNode end) {
        this.line = l;
        this.Start = start;
        this.End = end;
        this.Length = l.Length;
    }

    objDijkstraNode getOtherNode(objDijkstraNode Smallest) {
        if(this.Start == Smallest){
            return this.End;
        }else if(this.End == Smallest) return this.Start;
        else return null;
    }
    
    
}
