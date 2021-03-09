/*
 * TravelSmart.java
 */

package travelsmart;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

/**
 * The main class of the application.
 */
public class TravelSmart extends SingleFrameApplication {
    
    public ArrayList<objLine> lineCollect = new ArrayList<objLine>();
    public ArrayList<objPoint> pointCollect = new ArrayList<objPoint>();
    /**
     * At startup create and show the main frame of the application.
     */
    @Override protected void startup() {
        
        //Show GUI
        show(new TravelSmartView(this));
        getView().pn_Map.FillMap();
    }

    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     */
    @Override protected void configureWindow(java.awt.Window root) {
    }

    /**
     * A convenient static getter for the application instance.
     * @return the instance of TravelSmart
     */
    public static TravelSmart getApplication() {
        return Application.getInstance(TravelSmart.class);
    }
    
    public static TravelSmartView getView(){
        return (TravelSmartView)TravelSmart.getApplication().getMainView();
    }
    
    public static Dimension GetScreenSize(){    
        Toolkit toolkit =  Toolkit.getDefaultToolkit ();
        return toolkit.getScreenSize();
    }

    /**
     * Main method launching the application.
     */
    public static void main(String[] args) {
        launch(TravelSmart.class, args);
    }
}
