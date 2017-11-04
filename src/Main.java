import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;

/**
 * Main.java - Main Driver for the Application.  Attempt to initialize the look and feel to something less ugly, then
 * launch an instance of MainView, which is the primary GUI for the Application.
 *
 * @author Andrew McGuiness
 * @version 11/4/2017
 */
public class Main {
    public static void main( String[] args ) {
        try {
            //Try to find a less-horrid GUI Scheme
            for ( LookAndFeelInfo info : UIManager.getInstalledLookAndFeels() ) {
                if ( "Nimbus".equals( info.getName() ) ) {
                    UIManager.setLookAndFeel( info.getClassName() );
                    break;
                }
            }

            //Fire up the main application
            new MainView();
        } catch ( Exception e ) {
            System.out.println( "Something bad happened" );
        }
    }
}
