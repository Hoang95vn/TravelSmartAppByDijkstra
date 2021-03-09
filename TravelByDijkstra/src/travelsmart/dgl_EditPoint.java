/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * dgl_AddPoint.java
 *
 * Created on Mar 12, 2012, 2:14:50 AM
 */
package travelsmart;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author dato
 */
public class dgl_EditPoint extends javax.swing.JDialog {
    objPoint editPoint;
    objPoint oldPoint;
    int mouseX;
    int mouseY;    

    /** Creates new form dgl_AddPoint */
    public dgl_EditPoint() {
        initComponents();
        this.lblIndex.setText(String.valueOf(FileProcess.getNewPointIndex(TravelSmart.getApplication().pointCollect)));
        this.txtPosX.setText("0");
        this.txtPosY.setText("0");
        //Set background for dialog
        this.setBackground(new Color(0,0,0,0));
        this.getContentPane().setBackground(new Color(0,0,0,50));
        this.bgPanel1.bgUrl = Config.myDialog_bg;
        
        // Listen for changes in the text
        this.txtName.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                warn();
            }
            public void removeUpdate(DocumentEvent e) {
                warn();
            }
            public void insertUpdate(DocumentEvent e) {
                warn();
            }

            public void warn() {
                //khi text o? o textname thay doi..thay doi ten point tren map
                String Name;
                if(txtName.getText().equals("")) Name = " ";
                else Name = txtName.getText();
                editPoint.Name = Name;
                editPoint.SetPointState(0);
            }
        });
        
        this.txtPosX.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                //warn();
            }
            public void removeUpdate(DocumentEvent e) {
                warn();
            }
            public void insertUpdate(DocumentEvent e) {
                warn();
            }

            public void warn() {
                int x;
                if(txtPosX.getText().equals("")) x=0;
                else x = Integer.parseInt(txtPosX.getText());
                if(x>=0 && x<=1600){
                    editPoint.setPosX(x);
                    editPoint.SetPointState(0);
                }else {
                    SwingUtilities.invokeLater(new Runnable() {

                        public void run() {
                            txtPosX.setText(String.valueOf(editPoint.getPosX()));
                        }
                    });
                }
                    
            }
        });
        
        this.txtPosY.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                warn();
            }
            public void removeUpdate(DocumentEvent e) {
                warn();
            }
            public void insertUpdate(DocumentEvent e) {
                warn();
            }

            public void warn() {
                int y;
                if(txtPosY.getText().equals("")) y=0;
                else y = Integer.parseInt(txtPosY.getText());

                if(y>=0 && y<=900){
                    editPoint.setPosY(y);
                    editPoint.SetPointState(0);
                }  else{
                    SwingUtilities.invokeLater(new Runnable() {

                        public void run() {
                            txtPosY.setText(String.valueOf(editPoint.getPosY()));
                        }
                    });                
                }            
            }
        });
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        bgPanel1 = new travelsmart.bgPanel();
        Container = new travelsmart.bgPanel();
        lblIndex = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        bttSave = new javax.swing.JButton();
        txtPosX = new javax.swing.JTextField();
        txtPosY = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        bttCancel = new javax.swing.JButton();
        bttDelete = new javax.swing.JButton();
        bttClose = new javax.swing.JButton();

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(travelsmart.TravelSmart.class).getContext().getResourceMap(dgl_EditPoint.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setAlwaysOnTop(true);
        setName("Form"); // NOI18N
        setUndecorated(true);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
        });
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                formComponentHidden(evt);
            }
        });
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });
        getContentPane().setLayout(new java.awt.GridBagLayout());

        bgPanel1.setName("bgPanel1"); // NOI18N
        bgPanel1.setLayout(new java.awt.GridBagLayout());

        Container.setMaximumSize(new java.awt.Dimension(288, 82));
        Container.setMinimumSize(new java.awt.Dimension(288, 82));
        Container.setName("Container"); // NOI18N

        lblIndex.setForeground(resourceMap.getColor("lblIndex.foreground")); // NOI18N
        lblIndex.setText(resourceMap.getString("lblIndex.text")); // NOI18N
        lblIndex.setName("lblIndex"); // NOI18N

        txtName.setBackground(resourceMap.getColor("txtName.background")); // NOI18N
        txtName.setText(resourceMap.getString("txtName.text")); // NOI18N
        txtName.setName("txtName"); // NOI18N
        txtName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNameFocusLost(evt);
            }
        });
        txtName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNameKeyPressed(evt);
            }
        });

        jLabel3.setForeground(resourceMap.getColor("jLabel3.foreground")); // NOI18N
        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        jLabel4.setForeground(resourceMap.getColor("jLabel4.foreground")); // NOI18N
        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        bttSave.setIcon(resourceMap.getIcon("bttSave.icon")); // NOI18N
        bttSave.setText(resourceMap.getString("bttSave.text")); // NOI18N
        bttSave.setBorder(null);
        bttSave.setBorderPainted(false);
        bttSave.setContentAreaFilled(false);
        bttSave.setFocusPainted(false);
        bttSave.setName("bttSave"); // NOI18N
        bttSave.setPressedIcon(resourceMap.getIcon("bttSave.pressedIcon")); // NOI18N
        bttSave.setRolloverIcon(resourceMap.getIcon("bttSave.rolloverIcon")); // NOI18N
        bttSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttSaveActionPerformed(evt);
            }
        });

        txtPosX.setBackground(resourceMap.getColor("txtPosX.background")); // NOI18N
        txtPosX.setName("txtPosX"); // NOI18N
        txtPosX.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPosXFocusLost(evt);
            }
        });
        txtPosX.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPosXKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPosXKeyTyped(evt);
            }
        });

        txtPosY.setBackground(resourceMap.getColor("txtPosY.background")); // NOI18N
        txtPosY.setName("txtPosY"); // NOI18N
        txtPosY.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPosYFocusLost(evt);
            }
        });
        txtPosY.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPosXKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPosYKeyTyped(evt);
            }
        });

        jLabel1.setForeground(resourceMap.getColor("jLabel1.foreground")); // NOI18N
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        bttCancel.setIcon(resourceMap.getIcon("bttCancel.icon")); // NOI18N
        bttCancel.setText(resourceMap.getString("bttCancel.text")); // NOI18N
        bttCancel.setBorder(null);
        bttCancel.setContentAreaFilled(false);
        bttCancel.setFocusPainted(false);
        bttCancel.setName("bttCancel"); // NOI18N
        bttCancel.setPressedIcon(resourceMap.getIcon("bttCancel.pressedIcon")); // NOI18N
        bttCancel.setRolloverIcon(resourceMap.getIcon("bttCancel.rolloverIcon")); // NOI18N
        bttCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttCancelActionPerformed(evt);
            }
        });

        bttDelete.setIcon(resourceMap.getIcon("bttDelete.icon")); // NOI18N
        bttDelete.setText(resourceMap.getString("bttDelete.text")); // NOI18N
        bttDelete.setBorder(null);
        bttDelete.setBorderPainted(false);
        bttDelete.setContentAreaFilled(false);
        bttDelete.setFocusPainted(false);
        bttDelete.setName("bttDelete"); // NOI18N
        bttDelete.setPressedIcon(resourceMap.getIcon("bttDelete.pressedIcon")); // NOI18N
        bttDelete.setRolloverIcon(resourceMap.getIcon("bttDelete.rolloverIcon")); // NOI18N
        bttDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ContainerLayout = new javax.swing.GroupLayout(Container);
        Container.setLayout(ContainerLayout);
        ContainerLayout.setHorizontalGroup(
            ContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ContainerLayout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblIndex))
            .addGroup(ContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, ContainerLayout.createSequentialGroup()
                    .addComponent(bttSave)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(bttDelete)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(bttCancel))
                .addGroup(ContainerLayout.createSequentialGroup()
                    .addComponent(txtName)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jLabel3)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(txtPosX, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel4)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(txtPosY, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        ContainerLayout.setVerticalGroup(
            ContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ContainerLayout.createSequentialGroup()
                .addGroup(ContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(ContainerLayout.createSequentialGroup()
                        .addGroup(ContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(lblIndex))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(txtPosX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)
                        .addComponent(txtPosY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(bttSave)
                    .addComponent(bttDelete)
                    .addComponent(bttCancel)))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 10, 10);
        bgPanel1.add(Container, gridBagConstraints);

        bttClose.setIcon(resourceMap.getIcon("bttClose.icon")); // NOI18N
        bttClose.setBorder(null);
        bttClose.setContentAreaFilled(false);
        bttClose.setFocusPainted(false);
        bttClose.setName("bttClose"); // NOI18N
        bttClose.setRolloverIcon(resourceMap.getIcon("bttClose.rolloverIcon")); // NOI18N
        bttClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttCloseActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 10);
        bgPanel1.add(bttClose, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(bgPanel1, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void txtNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNameFocusLost
// TODO add your handling code here:
    if(this.txtName.getText().equals("")){
        this.txtName.setText(oldPoint.Name);
    }    
}//GEN-LAST:event_txtNameFocusLost

private void bttSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttSaveActionPerformed
    //Save to file
    FileProcess.editPoint(editPoint);
    this.oldPoint.Name = editPoint.Name;
        this.oldPoint.setPosX(editPoint.getPosX());
        this.oldPoint.setPosY(editPoint.getPosY());
    this.oldPoint.SetPointState(0);
    TravelSmart.getView().pn_Map.remove(editPoint);
    TravelSmart.getView().pn_Map.add(oldPoint);
    
    this.setVisible(false);
}//GEN-LAST:event_bttSaveActionPerformed

private void bttCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttCancelActionPerformed
    if(editPoint != null && oldPoint!= null){
        TravelSmart.getView().pn_Map.remove(editPoint);
        TravelSmart.getView().pn_Map.add(oldPoint);
        TravelSmart.getView().pn_Map.repaint();
    }
    this.setVisible(false);
}//GEN-LAST:event_bttCancelActionPerformed

private void formComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentHidden
    bttCancelActionPerformed(null);
}//GEN-LAST:event_formComponentHidden

private void txtPosXFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPosXFocusLost
    if(this.txtPosX.getText().equals("")){
        this.txtPosX.setText(String.valueOf(oldPoint.getPosX()));
    }
}//GEN-LAST:event_txtPosXFocusLost

private void txtPosYFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPosYFocusLost
    if(this.txtPosY.getText().equals("")){
        this.txtPosY.setText(String.valueOf(oldPoint.getPosY()));
    }
}//GEN-LAST:event_txtPosYFocusLost

private void bttDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttDeleteActionPerformed
    if(JOptionPane.showConfirmDialog(null, "Are you sure want to delete this point?","Confirm delete line", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
        //-----Delete all line of the point
        ArrayList<objLine> delLines = new ArrayList<objLine>();
        for(objLine l:TravelSmart.getApplication().lineCollect)
        {
            if(l.Start.Index == editPoint.Index||l.End.Index == editPoint.Index){
                delLines.add(l);
            }
        }
        for(objLine l:delLines){
            TravelSmart.getView().pn_Map.remove(l);
            TravelSmart.getApplication().lineCollect.remove(l);
        }


        //------Delete point
        //Remove point from map
        TravelSmart.getView().pn_Map.remove(editPoint);
        TravelSmart.getView().pn_Map.repaint();
        //Remove from array list
        TravelSmart.getApplication().pointCollect.remove(oldPoint);
        //Delete point from file
        FileProcess.delPoint(oldPoint.Index);
        this.editPoint = null;
        this.oldPoint = null;

        this.setVisible(false);
    }
}//GEN-LAST:event_bttDeleteActionPerformed

private void txtNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNameKeyPressed
    if(evt.getKeyCode() == KeyEvent.VK_ENTER){
        bttSaveActionPerformed(null);
    }else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE){
        bttCancelActionPerformed(null);
    }
}//GEN-LAST:event_txtNameKeyPressed

private void txtPosXKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPosXKeyPressed
    if(evt.getKeyCode() == KeyEvent.VK_ENTER){
        bttSaveActionPerformed(null);
    }else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE){
        bttCancelActionPerformed(null);
    }
}//GEN-LAST:event_txtPosXKeyPressed

private void txtPosXKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPosXKeyTyped
    String badchars = "`~!@#$%^&*()_+=\\|\"':;?/>.<, -";
    char c = evt.getKeyChar();
    if((Character.isLetter(c) && !evt.isAltDown()) || badchars.indexOf(c) > -1) {
        evt.consume();
        return;
    }
    if(c == '-' && txtPosX.getDocument().getLength() > 0) evt.consume();    
    else  super.processKeyEvent(evt);
}//GEN-LAST:event_txtPosXKeyTyped

private void txtPosYKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPosYKeyTyped
    String badchars = "`~!@#$%^&*()_+=\\|\"':;?/>.<, -";
    char c = evt.getKeyChar();
    if((Character.isLetter(c) && !evt.isAltDown()) || badchars.indexOf(c) > -1) {
        evt.consume();
        return;
    }
    if(c == '-' && txtPosY.getDocument().getLength() > 0) evt.consume();
    else super.processKeyEvent(evt);
}//GEN-LAST:event_txtPosYKeyTyped

private void bttCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttCloseActionPerformed
    bttCancelActionPerformed(evt);
}//GEN-LAST:event_bttCloseActionPerformed

private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
    this.setLocation(this.getLocation().x- mouseX + evt.getX(), this.getLocation().y- mouseY +evt.getY());
}//GEN-LAST:event_formMouseDragged

private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
    this.mouseX = evt.getX();
    this.mouseY = evt.getY();
}//GEN-LAST:event_formMousePressed

    public void showDgl(int x, int y, objPoint editPoint){
        this.setLocation(x, y);
        this.editPoint = new objPoint(editPoint.toString()); 
        this.editPoint.ChangeStatus(MapStatus.EDIT_POINT);
        
        //Save old infomation
        this.oldPoint = editPoint;
        //Remove oldPoint, add editPoint to map
        TravelSmart.getView().pn_Map.remove(oldPoint);
        oldPoint.SetPointState(0);
        TravelSmart.getView().pn_Map.add(this.editPoint);
        
        //Show information in fields
        this.txtName.setText(editPoint.Name);
        this.lblIndex.setText(String.valueOf(editPoint.Index));
        this.txtPosX.setText(String.valueOf(editPoint.getPosX()));
        this.txtPosY.setText(String.valueOf(editPoint.getPosY()));
        if(!this.isVisible()) this.setVisible(true);
        else{
            this.setAlwaysOnTop(true);
        }
    }
    
    public void UpdatePos(int xonMap,int yonMap){
        int x = (int)(Double.valueOf(xonMap - Config.Map_Margin_LR)/Double.valueOf(TravelSmart.getView().pn_Map.getWidth()-2*Config.Map_Margin_LR)*1600);
        int y = (int)(Double.valueOf(yonMap - Config.Map_Margin_TB)/Double.valueOf(TravelSmart.getView().pn_Map.getHeight()-2*Config.Map_Margin_TB)*900);
        this.txtPosX.setText(String.valueOf(x));
        this.txtPosY.setText(String.valueOf(y));
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private travelsmart.bgPanel Container;
    private travelsmart.bgPanel bgPanel1;
    private javax.swing.JButton bttCancel;
    private javax.swing.JButton bttClose;
    private javax.swing.JButton bttDelete;
    private javax.swing.JButton bttSave;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel lblIndex;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPosX;
    private javax.swing.JTextField txtPosY;
    // End of variables declaration//GEN-END:variables
}