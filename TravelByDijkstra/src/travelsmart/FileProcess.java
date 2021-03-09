/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package travelsmart;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dato
 */
class FileProcess {
    
    static String eol = System.getProperty("line.separator");//Break line character
    
    //Pass a File Name then return a Formatter for Writting data into file text
    public static Formatter Writer(String FileName){
        try {
            return new Formatter(FileName);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            System.exit(1);
            return null;
        }
    }    
    //Pass a File Name to this method, then will return Scanner for reading data from that file
    public static Scanner Reader(String FileName){
        try {
            return new Scanner(new File(FileName));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            System.exit(1);
            return null;
        }
    } 
    
    public static void AppendFile(String FileName, String content){
        try {
            // Create file 
            FileWriter fstream = new FileWriter(FileName,true);
            BufferedWriter out = new BufferedWriter(fstream);
            out.write(content);
            //Close the output stream
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(FileProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Get all lines from text file
    static ArrayList<objLine> getLines(ArrayList<objPoint> PointCollection) {
        Scanner Input = Reader(Config.LineDataUrl);
        ArrayList<objLine> result = new ArrayList<objLine>();

        while(Input.hasNext()){
            String line = Input.nextLine();
            String[] arr = line.split(",");
            //arr[0]:index, arr[1]:Index of Start point, arr[2]:Index of End point, arr[3]:length of line
            result.add(new objLine(Integer.parseInt(arr[0]),getPointbyIndex(PointCollection,Integer.parseInt(arr[1])),getPointbyIndex(PointCollection,Integer.parseInt(arr[2])),Double.valueOf(arr[3])));
        }
        Input.close();
        return result;
    }

    //Get all points from text file
    static ArrayList<objPoint> getPoints() {
        Scanner Input = Reader(Config.PointDataUrl);
        ArrayList<objPoint> result = new ArrayList<objPoint>();

        while(Input.hasNext()){
            String line = Input.nextLine();
            result.add(new objPoint(line));
        }
        Input.close();
        return result;
    }
    
    static objPoint getPointbyIndex(ArrayList<objPoint>PointCollection, int index){
        for(objPoint p:PointCollection){
            if(p.Index == index){
                return p;
            }
        }
        return null;
    }
    
    //Add , edit , delete methods
    static void addPoint(objPoint newPoint){
        AppendFile(Config.PointDataUrl, newPoint.toString()+eol);
    }
    
    static void addLine(objLine newLine){
        AppendFile(Config.LineDataUrl, newLine.toString()+eol);
    }
    
    static void editPoint(objPoint editPoint){
        //Reading lines from User file
        Scanner input = Reader(Config.PointDataUrl);
        ArrayList<String> lines = new ArrayList<String>();

        while(input.hasNext()){
            String l = input.nextLine();
            objPoint p = new objPoint(l);
            if(p.Index==editPoint.Index){
                lines.add(editPoint.toString());
            }else{
                lines.add(l);
            }
        }
        input.close();

        //Update User file
        Formatter output = Writer(Config.PointDataUrl);
        for(String l:lines){
            output.format(l+eol);
            output.flush();
        }
        output.close();
    }
    
    static void editLine(objLine editLine){
        //Reading lines from User file
        Scanner input = Reader(Config.LineDataUrl);
        ArrayList<String> lines = new ArrayList<String>();

        while(input.hasNext()){
            String l = input.nextLine();
            String[] arr = l.split(",");
            
            if(Integer.parseInt(arr[0])==editLine.Index){
                lines.add(editLine.toString());
            }else{
                lines.add(l);
            }
        }
        input.close();

        //Update User file
        Formatter output = Writer(Config.LineDataUrl);
        for(String l:lines){
            output.format(l+eol);
            output.flush();
        }
        output.close();
    }    
    
    static void delPoint(int PointIndex){
        //Delete all lines of this point
        delAllLineOfPoint(PointIndex);
        
        //Reading lines from User file
        Scanner input = Reader(Config.PointDataUrl);
        ArrayList<String> lines = new ArrayList<String>();

        while(input.hasNext()){
            String l = input.nextLine();
            objPoint p = new objPoint(l);
            if(p.Index==PointIndex){
                
            }else{
                lines.add(l);
            }
        }
        input.close();

        //Update User file
        Formatter output = Writer(Config.PointDataUrl);
        for(String l:lines){
            output.format(l+eol);
            output.flush();
        }
        output.close();        
    }
    
    static void delLine(int LineIndex){
        //Reading lines from User file
        Scanner input = Reader(Config.LineDataUrl);
        ArrayList<String> lines = new ArrayList<String>();

        while(input.hasNext()){
            String l = input.nextLine();
            String[] arr = l.split(",");
            
            if(Integer.parseInt(arr[0]) == LineIndex){
            }else{
                lines.add(l);
            }
        }
        input.close();

        //Update User file
        Formatter output = Writer(Config.LineDataUrl);
        for(String l:lines){
            output.format(l+eol);
            output.flush();
        }
        output.close();        
    }
    
    static void delAllLineOfPoint(int pointIndex){
        //Reading lines from User file
        Scanner input = Reader(Config.LineDataUrl);
        ArrayList<String> lines = new ArrayList<String>();

        while(input.hasNext()){
            String l = input.nextLine();
            String[] arr = l.split(",");
            
            if(Integer.parseInt(arr[1])== pointIndex || Integer.parseInt(arr[2])== pointIndex){
            }else{
                lines.add(l);
            }
        }
        input.close();

        //Update User file
        Formatter output = Writer(Config.LineDataUrl);
        for(String l:lines){
            output.format(l+eol);
            output.flush();
        }
        output.close();        
    }

    //Get new point index
    static int getNewPointIndex(ArrayList<objPoint> pointCollect) {
        int result = -1;
        for(objPoint p:pointCollect){
            if(p.Index > result) result = p.Index;
        }
        return result+1;
    }
    //Get new line index
    static int getNewLineIndex(ArrayList<objLine> lineCollect) {
        int result = -1;
        for(objLine l:lineCollect){
            if(l.Index > result) result = l.Index;
        }
        return result+1;
    }    
}
