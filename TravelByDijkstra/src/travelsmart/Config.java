/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package travelsmart;

import java.awt.Color;
import java.awt.Font;

/**
 *
 * @author dato
 */
public class Config {
    /*--------  Data Config ------------*/
    static String PointDataUrl = "PointData.txt";
    static String LineDataUrl = "LineData.txt";    
    
    /*--------  GUI Config -------------*/
    //Line attributes
    static Color Line_NormalColor = Color.gray;
    static Color Line_HoveredColor = new Color(50,124,203);
    static Color Line_AddingColor = Color.RED;
    static Color Line_SelectedColor = new Color(220,71,26);
    static Float Line_Stroke = 1.5f;
    static Double Line_Area = 4.0;
    static Color Line_Distance_Normal_Color = Color.black;//Color(93,101,108);    
    static Color Line_Distance_Hovered_Color = Color.RED;
    static Color Line_Distance_Adding_Color = Color.RED;
    
    //Point attributes
    static String PointIcon_Normal = "images/pointicon_Normal.png";
    static String PointIcon_Hovered = "images/pointicon_Hover.png";
    static String PointIcon_Selected = "images/pointicon_Select.png";
    static Font PointLabel_font = new Font("Arial", Font.PLAIN, 13);
    static Color PointLabel_Color = Color.red;
    
    //Main Form
    static String Main_Background = "images/main_bg.jpg";
    
    //Map attributes
    static int Map_Margin_LR = 10;
    static int Map_Margin_TB = 10;
    static String Map_Background = "images/map_bg.png";
    static final String WorldMap = "images/worldmap.jpg";
    
    //Search bar
    static String SearchP_bg_normal = "images/searchpoint_bg.png";
    static String SearchP_bg_focus = "images/searchpoint__focus_bg.png";
    
    //dglSearchResult
    static Color dglSearchResult_Color = new Color(19,19,19);
    static String dglSearchResult_separator="images/dglSearchResult_separator.png";
    static final String dglSearchResult_bg = "images/dglSearchResult_bg.png";
    
    //other GUI config
    static String scrollbar_thumb_bg = "images/scrollbar_thumb_bg.png";
    static String scrollbar_down_normal = "images/down.png";
    static String scrollbar_down_hover = "images/down_hover.png";
    static String scrollbar_up_normal = "images/up.png";
    static String scrollbar_up_hover = "images/up_hover.png";
    static final String textbox1_bg = "images/textbox1_bg.png";
    static String textbox1_bg_focus  = "images/textbox1_bg_focus.png";
    static final String myDialog_bg = "images/dialog_bg.png";
}
