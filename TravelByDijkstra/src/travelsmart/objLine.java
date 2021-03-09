/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package travelsmart;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

/**
 *
 * @author Dato
 */
public class objLine extends JPanel {

    public int Index;
    public objPoint Start;
    public objPoint End;
    public double Length; //Do dai cua line
    private ViewListener vListener = new ViewListener();
    private EditPointListener epListener = new EditPointListener();
    private EditLineListener elListener = new EditLineListener();
    private Line2D l2d;
    private Color line_color = Config.Line_NormalColor;
    private Color Distance_color = Config.Line_Distance_Normal_Color;
    
    public objLine(int Index,objPoint Start, objPoint End, double Length){
        this.Index = Index;
        this.Start = Start;
        this.End = End;
        this.Length = Length;
        
        BufferedImage lineImg = getLineImg();
        this.setBounds(Math.min(this.Start.posXonMap, this.End.posXonMap), Math.min(this.Start.posYonMap, this.End.posYonMap),lineImg.getWidth(),lineImg.getHeight() );
        //this.setLocation(this.Start.posX, this.Start.posY);
        this.setOpaque(false);
        
        //At first, point always in view option        
        ChangeStatus(MapStatus.VIEW);
    }
    
    @Override
    public String toString(){
        String result = "";
        if(this.Start != null && this.End != null){
            result = this.Index+","+this.Start.Index+","+this.End.Index+","+this.Length;
        }
        return result;
    }
    
    //<editor-fold defaultstate="collapsed" desc="Painting Line">
    //Make sure shape of jpanel is a line
    @Override
    public boolean contains(int x, int y) {
        return l2d.ptSegDist((double)x, (double)y)<=Config.Line_Area;
    }
    
    //Paint normal line
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        BufferedImage lineImg = getLineImg();
        this.setBounds(Math.min(this.Start.posXonMap, this.End.posXonMap), Math.min(this.Start.posYonMap, this.End.posYonMap),lineImg.getWidth(),lineImg.getHeight() );
        g2.drawImage(lineImg,0,0,null);
    }
    
    private BufferedImage getDistanceImg(){
        FontRenderContext frc = new FontRenderContext(null, true, true);
        TextLayout layout = new TextLayout(String.valueOf(this.Length), Config.PointLabel_font, frc);
        //Get bound of text layout
        Rectangle r = layout.getPixelBounds(null, 0, 0);
        BufferedImage bi = new BufferedImage(r.width + 1, r.height + 2,BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = (Graphics2D) bi.getGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Distance_color);
        layout.draw(g2d, 0, -r.y);
        g2d.dispose();
        return bi;
    }
    
    //Get Line2D for painting using Graphic 2D
    private Line2D getLine2D() {
        if(this.End.posXonMap == this.Start.posXonMap){
            return new Line2D.Double(0,0,0,Math.abs(this.Start.posYonMap - this.End.posYonMap));
        }else if(this.End.posYonMap == this.Start.posYonMap){
            return new Line2D.Double(0,0,Math.abs(this.Start.posXonMap - this.End.posXonMap),0);
        }
        else{
            if(((this.End.posYonMap-this.Start.posYonMap)>0 && ((double)this.End.posXonMap-this.Start.posXonMap) > 0) || ((this.End.posYonMap-this.Start.posYonMap)< 0 && ((double)this.End.posXonMap-this.Start.posXonMap) < 0))
            {
                return new Line2D.Double(0,0,Math.abs(this.Start.posXonMap - this.End.posXonMap), Math.abs(this.Start.posYonMap - this.End.posYonMap));
            }
            else return new Line2D.Double(0,Math.abs(this.Start.posYonMap - this.End.posYonMap),Math.abs(this.Start.posXonMap - this.End.posXonMap),0);
        }
    }
    
    private BufferedImage getLineImg(){
        BufferedImage DistanceImg = getDistanceImg();
        this.l2d = getLine2D();
        
        //Draw distance number
        if(l2d.getX1() == 0 && l2d.getY1() == 0){//Neu l2d la duong thang goc tren trai xuong goc duoi phai thi ghi distance len phia tren
            BufferedImage bi = new BufferedImage((int)Math.max(l2d.getBounds().getWidth(),l2d.getBounds().getWidth()/2 + 7 + DistanceImg.getWidth()), (int)Math.max(l2d.getBounds().getHeight(), Math.abs(l2d.getBounds().getHeight()/2-7)+DistanceImg.getHeight()),BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = (Graphics2D) bi.getGraphics();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            //Draw line
            g2d.setColor(line_color);
            g2d.setStroke(new BasicStroke(Config.Line_Stroke));
            g2d.draw(l2d);
            //Draw Distance
            g2d.drawImage(DistanceImg, l2d.getBounds().width/2+7, Math.abs(l2d.getBounds().height/2-7),null);
            g2d.dispose();
            return bi;
        }else{
            BufferedImage bi = new BufferedImage((int)Math.max(l2d.getBounds().getWidth(),l2d.getBounds().getWidth()/2 + 7 + DistanceImg.getWidth()), (int)Math.max(l2d.getBounds().getHeight(), l2d.getBounds().getHeight()/2+7+DistanceImg.getHeight()),BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = (Graphics2D) bi.getGraphics();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            //Draw line
            g2d.setColor(line_color);
            g2d.setStroke(new BasicStroke(Config.Line_Stroke));
            g2d.draw(l2d);
            //Draw Distance
            g2d.drawImage(DistanceImg, l2d.getBounds().width/2+7, l2d.getBounds().height/2+7,null);
            g2d.dispose();
            return bi;
        }
    }
    
    public void SetLineState(int Type){
        //0: Normal, 1: Hovered, 2: Adding
        switch(Type){
            case 0:
                this.line_color = Config.Line_NormalColor;
                this.Distance_color = Config.Line_Distance_Normal_Color;
                this.repaint();
                break;
            case 1:
                this.line_color = Config.Line_HoveredColor;
                this.Distance_color = Config.Line_Distance_Hovered_Color;
                this.repaint();                
                break;
            case 2:
                this.line_color = Config.Line_AddingColor;
                this.Distance_color = Config.Line_Distance_Adding_Color;
                this.repaint();                
                break;
        }
    }
    //</editor-fold>

    void ChangeStatus(MapStatus Status) {
        switch(Status){
            case VIEW:
                //remove all listener
                this.removeMouseListener(vListener);
                this.removeMouseMotionListener(vListener);
                this.removeMouseListener(epListener);
                this.removeMouseMotionListener(epListener);
                this.removeMouseMotionListener(elListener);
                this.removeMouseListener(elListener);
                this.removeMouseMotionListener(elListener);
                //add mouse listener of edit option
                this.addMouseListener(vListener);
                this.addMouseMotionListener(vListener);
                break;            
            case EDIT_POINT:
                //remove all listener
                this.removeMouseListener(vListener);
                this.removeMouseMotionListener(vListener);
                this.removeMouseListener(epListener);
                this.removeMouseMotionListener(epListener);
                this.removeMouseMotionListener(elListener);
                this.removeMouseListener(elListener);
                this.removeMouseMotionListener(elListener);
                //add mouse listener of edit option
                this.addMouseListener(epListener);
                this.addMouseMotionListener(epListener);
                break;
            case EDIT_LINE:
                //remove all listener
                this.removeMouseListener(vListener);
                this.removeMouseMotionListener(vListener);
                this.removeMouseListener(epListener);
                this.removeMouseMotionListener(epListener);
                this.removeMouseMotionListener(elListener);
                this.removeMouseListener(elListener);
                this.removeMouseMotionListener(elListener);
                //add mouse listener of edit option
                this.addMouseListener(elListener);
                this.addMouseMotionListener(elListener);
                break;
        }
    }    
    
    //<editor-fold defaultstate="collapsed" desc="Mouse Event Handler">
    
    //Mouse Event Handler for view option
    class ViewListener extends MouseInputAdapter{
        //mouseClicked, mouseEntered, mouseExited, mousePressed, mouseReleased,mouseDragged, mouseMoved
        
        @Override
        public void mouseMoved(MouseEvent e){
        }
        
        @Override
        public void mouseExited(MouseEvent e){
        }
        
        @Override
        public void mouseClicked(MouseEvent e){
            
        }
    }
    
    //Mouse Event Handler for Edit Line option
    class EditLineListener extends MouseInputAdapter{
        //mouseClicked, mouseEntered, mouseExited, mousePressed, mouseReleased,mouseDragged, mouseMoved
        
        @Override
        public void mouseMoved(MouseEvent e){
            mouseMovedEL(e);
        }
        
        @Override
        public void mouseExited(MouseEvent e){
            mouseExitedEL(e);
        }
        
        @Override
        public void mouseClicked(MouseEvent e){
            mouseClickedEL(e);
        }

    }
    private void mouseMovedEL(MouseEvent e) {
        if(TravelSmart.getApplication().lineCollect.indexOf(this)!=-1){
            SetLineState(1);
        }
    }
    private void mouseExitedEL(MouseEvent e) {
        if(TravelSmart.getApplication().lineCollect.indexOf(this)!=-1){
            SetLineState(0);
        }
    }
    private void mouseClickedEL(MouseEvent e) {            
        //Show dialog Edit line
        TravelSmart.getView().dgl_editLine.showDgl(e.getXOnScreen(), e.getYOnScreen(), this);
    }
    
    //Mouse Event Handler for Edit Point option
    class EditPointListener extends MouseInputAdapter{
        //mouseClicked, mouseEntered, mouseExited, mousePressed, mouseReleased,mouseDragged, mouseMoved
        
        @Override
        public void mouseMoved(MouseEvent e){
        }
        
        @Override
        public void mouseExited(MouseEvent e){
        }
        
        @Override
        public void mouseClicked(MouseEvent e){
            
        }
    }
    //</editor-fold>   
}
