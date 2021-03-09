/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package travelsmart;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 *
 * @author dato
 */
public class DijkstraAlgorithm{
    objDijkstraNode Start;
    objDijkstraNode End;
    ArrayList<objDijkstraNode> SolvedP = new ArrayList<objDijkstraNode>();
    ArrayList<objDijkstraNode> UnsolvedP;
    ArrayList<objDijkstraDistance> SolvedL = new ArrayList<objDijkstraDistance>();
    ArrayList<objDijkstraDistance> UnsolvedL;
    ArrayList<objDijkstraDistance> CurrentLine = new ArrayList<objDijkstraDistance>();
    
    public ArrayList<objLine> run(ArrayList<objPoint>pointCollect, ArrayList<objLine>lineCollect, objPoint Start, objPoint End) {
        if(pointCollect != null && lineCollect != null && Start != null && End != null){
            InitVariables(pointCollect,lineCollect,Start,End);   
            RunDijkstra();
            return GetDirection();
        }else return null;
    }

    private ArrayList<objDijkstraDistance> RunDijkstra() {
        while(!UnsolvedP.isEmpty()){
            objDijkstraNode Smallest = FindSmallestLabel();
            //Add node that have smallest label or Start node to SolvedP
            UnsolvedP.remove(Smallest);
            SolvedP.add(Smallest);
            //Move line of smallest from CurrentLine to SolvedL
            MoveLineFromCurrToSolved(Smallest);
            if(Smallest == End) return SolvedL;
            //Search unsovled line of Smallest and add to current line
            AddtoCurrentLine(GetUnsolvedLine(Smallest),Smallest);
        }
        return SolvedL;
    }
    
    private objDijkstraNode FindNode(objPoint p){
        if(UnsolvedP != null){
            for(objDijkstraNode n:UnsolvedP){
                if(n.point.Index == p.Index){
                    return n;
                }
            }
            return null;
        }else return null;
    }

    private void InitVariables(ArrayList<objPoint>pointCollect, ArrayList<objLine>lineCollect, objPoint Start, objPoint End) {
        //Initial array list
        SolvedP = new ArrayList<objDijkstraNode>();
        SolvedL = new ArrayList<objDijkstraDistance>();
        CurrentLine = new ArrayList<objDijkstraDistance>();
        UnsolvedP = new ArrayList<objDijkstraNode>();
        UnsolvedL = new ArrayList<objDijkstraDistance>();
        for(objPoint p: pointCollect){
            UnsolvedP.add(new objDijkstraNode(p));
        }
        for(objLine l: lineCollect){
            UnsolvedL.add(new objDijkstraDistance(l,FindNode(l.Start),FindNode(l.End)));
        }
        this.Start = FindNode(Start);
        this.Start.Label = 0.0;
        this.End = FindNode(End);
    }

    private objDijkstraNode FindSmallestLabel() {
        objDijkstraNode min = UnsolvedP.get(0);
        for(objDijkstraNode n:UnsolvedP){
            if(n.Label<min.Label){
                min = n;
            }
        }
        return min;
    }

    private void AddtoCurrentLine(ArrayList<objDijkstraDistance> tempDistance, objDijkstraNode Smallest) {
        for(objDijkstraDistance d: tempDistance){
            objDijkstraNode otherNode = d.getOtherNode(Smallest);
            if(otherNode != null && (Smallest.Label + d.Length<otherNode.Label)){
                //check have common node?    
                objDijkstraDistance delDistance = null;
                for(objDijkstraDistance curD: CurrentLine){
                    if(curD.Start == otherNode || curD.End == otherNode){
                        //remove old line, add new line have shorter distance
                        delDistance = curD;
                        break;
                    }
                }
                if(delDistance != null){
                    CurrentLine.remove(delDistance);
                }
                CurrentLine.add(d);
                otherNode.Label = Smallest.Label + d.Length;
            }
        }
    }

    private void MoveLineFromCurrToSolved(objDijkstraNode Smallest) {
        objDijkstraDistance tempDistance = null;
        for(objDijkstraDistance d:CurrentLine){
            if(d.Start == Smallest || d.End == Smallest){
                tempDistance = d;
            }
        }
        if(tempDistance != null){
            CurrentLine.remove(tempDistance);
            SolvedL.add(tempDistance);
        
        }
    }

    private ArrayList<objDijkstraDistance> GetUnsolvedLine(objDijkstraNode Smallest) {
        ArrayList<objDijkstraDistance> tempDistance = new ArrayList<objDijkstraDistance>();
        for(objDijkstraDistance d: UnsolvedL){
            if(d.Start == Smallest || d.End == Smallest){
                tempDistance.add(d);
            }
        }
        UnsolvedL.removeAll(tempDistance);
        return tempDistance;
    }

    private ArrayList<objLine> GetDirection() {
        if(SolvedL != null && !SolvedL.isEmpty()){
            ArrayList<objLine> result = new ArrayList<objLine>();
            objDijkstraNode node = End;
            while(node != Start){
                boolean check = false;
                for(objDijkstraDistance d:SolvedL){
                    if(d.Start == node){
                        result.add(d.line);
                        node = d.End;
                        check=true;
                        break;
                    }else if(d.End == node){
                        result.add(d.line);
                        node = d.Start;
                        check = true;
                        break;
                    }
                }
                if(!check) break;
            }
            Collections.reverse(result);
            return result;
        }else return null;
    }
    
}
