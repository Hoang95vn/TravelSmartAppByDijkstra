/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package travelsmart;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

/**
 *
 * @author Dato
 */
public class objPoint extends JPanel {
    String Name;
    private int posX;
    private int posY;
    int Index;
    
    int posXonMap;
    int posYonMap;
    ViewListener vListener = new ViewListener();
    EditPointListener epListener = new EditPointListener();
    EditLineListener elListener = new EditLineListener();
    BufferedImage image;
    Area area = new Area();
    
    public objPoint(String Name, int posX, int posY, int Index){
        if(Name.equals("")) Name = " ";
        //Set Variables
        this.Name = Name;
        this.posX = posX;
        this.posY = posY;
        this.Index = Index;
        
        this.posXonMap = getPosXonMap();
        this.posYonMap = getPosYonMap();
        this.image = getPointImg(0);
        //Set Component size
        this.setBounds(this.posXonMap-(image.getWidth()/2),this.posYonMap-this.image.getHeight(),image.getWidth(),image.getHeight());        
        this.setOpaque(false);
        
        ChangeStatus(MapStatus.VIEW);
    } 
    
    public objPoint(String line){
        String[] arr = line.split(",");
        
        //Set Variables
        this.Name = arr[1];
        this.posX = Integer.parseInt(arr[2]);
        this.posY = Integer.parseInt(arr[3]);
        this.Index = Integer.parseInt(arr[0]);        
        
        this.posXonMap = getPosXonMap();
        this.posYonMap = getPosYonMap();
        this.image = getPointImg(0);
        //Set Component size
        this.setBounds(this.posXonMap-(image.getWidth()/2),this.posYonMap-this.image.getHeight(),image.getWidth(),image.getHeight());        
        this.setOpaque(false);
               
        ChangeStatus(MapStatus.VIEW);      
    }
    
    @Override
    public String toString(){
        String result = "";
        if(this.Name != null){
            result = this.Index+","+this.Name+","+this.getPosX()+","+this.getPosY();
        }
        return result;
    }
    
    //<editor-fold defaultstate="collapsed" desc="Painting Methods">
    //Make sure shape of jpanel is a line
    @Override
    public boolean contains(int x, int y) {
        return area.contains(x, y);
    }
    
    public void refreshPoint(){
        this.posXonMap = getPosXonMap();
        this.posYonMap = getPosYonMap();
        this.repaint();
    }
    
    //Paint normal line
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        //Set translucency for graphic
        AlphaComposite newComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f);
        //g2.setComposite(newComposite);
        g2.drawImage(image, 0,0, null);
        this.setBounds(this.posXonMap-(image.getWidth()/2),this.posYonMap-this.image.getHeight(),image.getWidth(),image.getHeight());
    }
    
    private BufferedImage getIcon(int Type){
        //Type 0:Normal, 1:Hovered, 2:Selected
        String imgUrl = "";
        switch(Type){
            case 0: imgUrl = Config.PointIcon_Normal;
            break;
            case 1: imgUrl = Config.PointIcon_Hovered;
            break;
            case 2: imgUrl = Config.PointIcon_Selected;
        }
        /* Create an icon of point */
        BufferedImage img;
        try {
            img = ImageIO.read(new File(imgUrl));
        } catch (IOException ex) {
            Logger.getLogger(objPoint.class.getName()).log(Level.SEVERE, null, ex);
            img = null;
        }
        int w = img.getWidth(null);
        int h = img.getHeight(null);
        BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = (Graphics2D)bi.getGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.drawImage(img, 0, 0, null);
        g2.dispose();
        return bi;
    }
    private BufferedImage getNameImg(){
        FontRenderContext frc = new FontRenderContext(null, true, true);
        TextLayout layout = new TextLayout(this.Name, Config.PointLabel_font, frc);
        //Get bound of text layout
        Rectangle r = layout.getPixelBounds(null, 0, 0);
        BufferedImage bi = new BufferedImage(r.width + 1, r.height + 2,BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = (Graphics2D) bi.getGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Config.PointLabel_Color);
        layout.draw(g2d, 0, -r.y);
        g2d.dispose();
        return bi;
    }
    private BufferedImage getPointImg(int Type){
        //Type 0:Normal, 1:Hovered, 2:Selected
        //Get icon image and name string
        BufferedImage icon = getIcon(Type);
        BufferedImage nameimg = getNameImg();
        
        //Draw name and icon to 1 image
        BufferedImage bi = new BufferedImage(
                Math.max(nameimg.getWidth() + 1,icon.getWidth()+1), icon.getHeight()+ nameimg.getHeight() + 2,
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = (Graphics2D) bi.getGraphics();
        g2d.setColor(Color.BLACK);
        g2d.drawImage(nameimg, (bi.getWidth()-nameimg.getWidth())/2,0, null);
        g2d.drawImage(icon,(Math.abs(bi.getWidth()-icon.getWidth()))/2,nameimg.getHeight()+2,null);
        this.area.reset();
        this.area.add(new Area(new Rectangle((bi.getWidth()-nameimg.getWidth())/2,0,nameimg.getWidth(),nameimg.getHeight())));
        this.area.add(new Area(new Rectangle((Math.abs(bi.getWidth()-icon.getWidth()))/2,nameimg.getHeight()+2,icon.getWidth(),icon.getHeight())));
        g2d.dispose();
        return bi;
    }

    public void SetPointState(int Type){
        image = getPointImg(Type);
        repaint();
    }
    //</editor-fold>
    
    protected void ChangeStatus(MapStatus Status) {
        switch(Status){
            case VIEW:
                //remove all listeners
                this.removeMouseListener(vListener);
                this.removeMouseMotionListener(vListener);
                this.removeMouseListener(epListener);
                this.removeMouseMotionListener(epListener);
                this.removeMouseListener(elListener);
                this.removeMouseMotionListener(elListener);
                //add mouse listener of view option
                this.addMouseListener(vListener);
                this.addMouseMotionListener(vListener);
                break;            
            case EDIT_POINT:           
                //remove all listeners
                this.removeMouseListener(vListener);
                this.removeMouseMotionListener(vListener);
                this.removeMouseListener(epListener);
                this.removeMouseMotionListener(epListener);
                this.removeMouseListener(elListener);
                this.removeMouseMotionListener(elListener);
                //add mouse listener of edit option
                this.addMouseListener(epListener);
                this.addMouseMotionListener(epListener);
                break;
            case EDIT_LINE:           
                //remove all listeners
                this.removeMouseListener(vListener);
                this.removeMouseMotionListener(vListener);
                this.removeMouseListener(epListener);
                this.removeMouseMotionListener(epListener);
                this.removeMouseListener(elListener);
                this.removeMouseMotionListener(elListener);
                //add mouse listener of edit option
                this.addMouseListener(elListener);
                this.addMouseMotionListener(elListener);
                break;
        }
    }

    int getPosXonMap() {
        double x = (Double.valueOf(TravelSmart.getView().pn_Map.getWidth())-2*(Config.Map_Margin_LR))/1600*this.getPosX() + Config.Map_Margin_LR;
        return (int)x;
    }
    
    int getPosYonMap() {
        double y = (Double.valueOf(TravelSmart.getView().pn_Map.getHeight())-2*Config.Map_Margin_TB)/900*this.getPosY() + Config.Map_Margin_TB;
        return (int)y;
    }

    /**
     * @return the posX
     */
    public int getPosX() {
        return posX;
    }

    /**
     * @param posX the posX to set
     */
    public void setPosX(int posX) {
        if(posX>=0 && posX<=1600){
            this.posX = posX;
            this.posXonMap = getPosXonMap();
        }
    }

    /**
     * @return the posY
     */
    public int getPosY() {
        return posY;        
    }

    /**
     * @param posY the posY to set
     */
    public void setPosY(int posY) {
        if(posY>=0 && posY<=900){
            this.posY = posY;
            this.posYonMap = getPosYonMap();
        }
    }

    void fireClick() {
        long i = new Long("1331727210805");
        this.processMouseEvent(new MouseEvent(this,500,i,16,0,0,1,false));
    }


    //<editor-fold defaultstate="collapsed" desc="Mouse Event Handler in View">
    //Mouse Event Handler for view option
    class ViewListener extends MouseInputAdapter{
        //mouseClicked, mouseEntered, mouseExited, mousePressed, mouseReleased,mouseDragged, mouseMoved
        
        @Override
        public void mouseMoved(MouseEvent e){
            mouseMovedV(e);
        }
        
        @Override
        public void mouseExited(MouseEvent e){
            mouseExitedV(e);
        }
        
        @Override
        public void mouseClicked(MouseEvent e){
            mouseClickedV();
        }
    }
    
    void mouseMovedV(MouseEvent e){
        if(TravelSmart.getView().pn_Map.selectingPoint.indexOf(this) == -1){
            SetPointState(1);
        }
    }
    
    void mouseExitedV(MouseEvent e){
        if(TravelSmart.getView().pn_Map.selectingPoint.indexOf(this) == -1){
            SetPointState(0);
        }
    }
    
    void mouseClickedV(){
        if(TravelSmart.getView().pn_Map.selectingPoint.indexOf(this) == -1){
            SetPointState(2);
            TravelSmart.getView().pn_Map.selectingPoint.add(this);
            if(TravelSmart.getView().pn_Map.selectingPoint.size()==2)
            {
                TravelSmart.getView().pn_Map.SearchDirection(TravelSmart.getView().pn_Map.selectingPoint.get(0),TravelSmart.getView().pn_Map.selectingPoint.get(1));
            }
            else
                if(TravelSmart.getView().pn_Map.selectingPoint.size()==3)
                {
                    objPoint p1 = TravelSmart.getView().pn_Map.selectingPoint.get(0);
                    objPoint p2 = TravelSmart.getView().pn_Map.selectingPoint.get(1);
                    TravelSmart.getView().pn_Map.selectingPoint.remove(p1);
                    TravelSmart.getView().pn_Map.selectingPoint.remove(p2);
                    p1.SetPointState(0);
                    p2.SetPointState(0);
                }
        }else{
            SetPointState(0);
            TravelSmart.getView().pn_Map.selectingPoint.remove(this);
        }
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Mouse Event Handler in Edit Point">
    //Mouse Event Handler for Edit option
    class EditPointListener extends MouseInputAdapter{
        //mouseClicked, mouseEntered, mouseExited, mousePressed, mouseReleased,mouseDragged, mouseMoved
        
        @Override
        public void mouseMoved(MouseEvent e){
            SetPointState(1);
        }
        
        @Override
        public void mouseExited(MouseEvent e){
            SetPointState(0);
        }
        
        @Override
        public void mouseClicked(MouseEvent e){
            mouseClickedEP(e);
        }
        
        @Override
        public void mouseDragged(MouseEvent e){
            mouseDraggedEP(e);
        }
    }
    
    private void mouseDraggedEP(MouseEvent e) {
        this.setPosX(this.getPosX() + e.getX());
        this.setPosY(this.getPosY() + e.getY());
        //TravelSmart.getView().dgl_editPoint.UpdatePos(this.posXonMap, this.posYonMap);
        SetPointState(2);
        FileProcess.editPoint(this);
    }
    
    private void mouseClickedEP(MouseEvent e) {
        //Show popup adding point
        TravelSmart.getView().dgl_editPoint.showDgl(e.getXOnScreen(),e.getYOnScreen(),this);
    }
    //</editor-fold>    

    //<editor-fold defaultstate="collapsed" desc="Mouse Event Handler in Edit Line">
    //Mouse Event Handler for view option
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
    
    void mouseMovedEL(MouseEvent e){
        if(TravelSmart.getView().pn_Map.selectingPoint.indexOf(this) == -1){
            SetPointState(1);
        }
    }
    
    void mouseExitedEL(MouseEvent e){
        if(TravelSmart.getView().pn_Map.selectingPoint.indexOf(this) == -1){
            SetPointState(0);
        }
    }

    void mouseClickedEL(MouseEvent e) {
        if(TravelSmart.getView().pn_Map.selectingPoint.indexOf(this) == -1){//If this point is not selected
            SetPointState(2);
            TravelSmart.getView().pn_Map.selectingPoint.add(this);
            if(TravelSmart.getView().pn_Map.selectingPoint.size()==2)
            {
                //If have exist line -> Edit
                for(objLine l:TravelSmart.getApplication().lineCollect){
                    if( (l.Start.Index == TravelSmart.getView().pn_Map.selectingPoint.get(0).Index && l.End.Index == TravelSmart.getView().pn_Map.selectingPoint.get(1).Index)||
                        (l.End.Index == TravelSmart.getView().pn_Map.selectingPoint.get(0).Index && l.Start.Index == TravelSmart.getView().pn_Map.selectingPoint.get(1).Index)
                            ){
                        //Open edit line dialog
                        TravelSmart.getView().dgl_editLine.showDgl(l.getLocationOnScreen().x+l.getWidth()/2, l.getLocationOnScreen().y+l.getHeight()/2, l);
                        return;
                    }
                }
                //Else add new line
                TravelSmart.getView().dgl_addLine.showDgl(TravelSmart.getView().pn_Map.selectingPoint.get(0),TravelSmart.getView().pn_Map.selectingPoint.get(1));
                
            }
            else
                if(TravelSmart.getView().pn_Map.selectingPoint.size()==3)
                {
                    objPoint p1 = TravelSmart.getView().pn_Map.selectingPoint.get(0);
                    objPoint p2 = TravelSmart.getView().pn_Map.selectingPoint.get(1);
                    TravelSmart.getView().pn_Map.selectingPoint.remove(p1);
                    TravelSmart.getView().pn_Map.selectingPoint.remove(p2);
                    p1.SetPointState(0);
                    p2.SetPointState(0);
                    return;
                }
        }else{
            //If this point is selected, then unseletect it
            SetPointState(0);
            TravelSmart.getView().pn_Map.selectingPoint.remove(this);
            return;
        }        
    }
    //</editor-fold>
}