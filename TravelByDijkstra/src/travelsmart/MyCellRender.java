/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package travelsmart;

import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author dato
 */
public class MyCellRender extends DefaultListCellRenderer {
    private BufferedImage image;
    private Color txtColor = new Color(208,208,208);
  
    @Override
    public Component getListCellRendererComponent(JList list,Object value,int index,boolean isSelected,boolean hasFocus) {
        JLabel label = (JLabel)super.getListCellRendererComponent(list,value,index,isSelected,hasFocus);
        if(isSelected){
            this.txtColor = new Color(50,235,251);
            label.setBackground(new Color(0,0,0,100));
        }else{
            this.txtColor = new Color(208,208,208);
        }
        image = RenderImg(label,list);
        label.setIcon(new ImageIcon(image));
        label.setText("");
        label.setPreferredSize(new Dimension(list.getWidth(), image.getHeight()));
        label.setBorder(noFocusBorder);
        return(label);
    }

    private BufferedImage RenderImg(JLabel label,JList list) {
        //Type 0:Normal, 1:Hovered, 2:Selected
        //Get icon image and name string
        BufferedImage separator = getSeparator();
        BufferedImage textImg= getTextImg(label.getText());
        
        //Draw name and icon to 1 image
        BufferedImage bi = new BufferedImage(
                list.getWidth(), separator.getHeight()+textImg.getHeight()+6,
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = (Graphics2D) bi.getGraphics();
        g2d.drawImage(textImg, 20,3, null);
        g2d.drawImage(separator,0,textImg.getHeight()+6,bi.getWidth(),separator.getHeight(),null);
        g2d.dispose();
        return bi;        
    }
    
    private BufferedImage getSeparator(){
        //Type 0:Normal, 1:Hovered, 2:Selected
        String imgUrl = Config.dglSearchResult_separator;
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
    private BufferedImage getTextImg(String text){
        FontRenderContext frc = new FontRenderContext(null, true, true);
        TextLayout layout = new TextLayout(text, Config.PointLabel_font, frc);
        //Get bound of text layout
        Rectangle r = layout.getPixelBounds(null, 0, 0);
        BufferedImage bi = new BufferedImage(r.width + 1, r.height + 2,BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = (Graphics2D) bi.getGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(txtColor);
        layout.draw(g2d, 0, -r.y);
        g2d.dispose();
        return bi;
    }
}
