/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package travelsmart;

/**
 *
 * @author dato
 */
public class objDijkstraNode {
    objPoint point;
    Double Label;

    public objDijkstraNode(objPoint p) {
        this.point = p;
        this.Label = Double.MAX_VALUE;
    }    
}
