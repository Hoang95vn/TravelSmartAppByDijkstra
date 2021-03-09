/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package travelsmart;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JScrollBar;
import javax.swing.plaf.basic.BasicScrollBarUI;

/**
 *
 * @author dato
 */
public class myScrollBarUI extends BasicScrollBarUI{

    public myScrollBarUI() {
    }
    
    @Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
        // your code
        super.paintTrack(g, c, trackBounds);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(new Color(34,34,34));
        g2d.fillRect(trackBounds.x,trackBounds.y,trackBounds.width,trackBounds.height);
    }

    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
        
        //super.paintTrack(g, c, thumbBounds);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.drawImage(new ImageIcon(Config.scrollbar_thumb_bg).getImage(),thumbBounds.x,thumbBounds.y,thumbBounds.width,thumbBounds.height,null);
    }
    
    @Override
    protected JButton createIncreaseButton(int orientation)
    {
        JButton IncBtt = new JButton();
        
        IncBtt.setBackground(new Color(0,0,0));
        IncBtt.setIcon(new ImageIcon(Config.scrollbar_down_normal)); // NOI18N
        IncBtt.setText(""); // NOI18N
        IncBtt.setBorder(null);
        IncBtt.setContentAreaFilled(false);
        IncBtt.setFocusPainted(false);
        IncBtt.setOpaque(false);
        IncBtt.setMargin(new java.awt.Insets(0, 0, 0, 0));
        IncBtt.setSize(18, 18);
        IncBtt.setPressedIcon(new ImageIcon(Config.scrollbar_down_normal)); // NOI18N
        IncBtt.setRolloverIcon(new ImageIcon(Config.scrollbar_down_hover)); // NOI18N
        return IncBtt;
    }

    @Override
    protected JButton createDecreaseButton(int orientation){
        JButton IncBtt = new JButton();
        
        IncBtt.setIcon(new ImageIcon(Config.scrollbar_up_normal)); // NOI18N
        IncBtt.setText(""); // NOI18N
        IncBtt.setBorder(null);
        IncBtt.setContentAreaFilled(false);
        IncBtt.setFocusPainted(false);
        IncBtt.setOpaque(false);
        IncBtt.setMargin(new java.awt.Insets(0, 0, 0, 0));
        IncBtt.setSize(18, 18);
        IncBtt.setName("IncBtt"); // NOI18N
        IncBtt.setPressedIcon(new ImageIcon(Config.scrollbar_up_normal)); // NOI18N
        IncBtt.setRolloverIcon(new ImageIcon(Config.scrollbar_up_hover)); 
        return IncBtt;
    }    
}
