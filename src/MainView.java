import javax.swing.*;
import java.awt.*;

/**
 * MainView.java - The MainView is the GUI interface between the user and the GPIO pins of the Raspberry Pi.  The bulk
 * of the GPIO work is abstracted away into the PinController Class.  The only real function of the GUI is to allow
 * the user to select different GPIO to set from LOW to HIGH.
 *
 * @author Andrew McGuiness
 * @version 11/4/2017
 */
public class MainView extends JFrame {
    private final static int WINDOW_WIDTH = 250;
    private final static int WINDOW_HEIGHT = 250;
    private PinController pinController;


    public MainView() {
        //Initialize the GPIO interface
        pinController = new PinController();

        //Build and display the GUI
        initGUI();

        //Time to cleanUp, reset all pins
        pinController.cleanUp();
    }

    //Build our GUI
    private void initGUI() {
        JFrame frame = new JFrame( "GPIO Tester" );
        JPanel mainPanel = new JPanel();
        frame.setContentPane( mainPanel );
        mainPanel.setLayout( new GridBagLayout() );

        initSelectorPins( mainPanel );
        initInputPins( mainPanel );

        frame.setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        frame.setSize( WINDOW_WIDTH, WINDOW_HEIGHT );

        frame.setVisible( true );
    }

    //Build the 2 Checkboxes for Selector A and B
    private void initSelectorPins( JPanel mainPanel ) {
        //Make the boxes
        JCheckBox selectACheck = new JCheckBox( "Select A" );
        JCheckBox selectBCheck = new JCheckBox( "Select B" );

        //Give the checkboxes a callback linked to the PinController
        selectACheck.addItemListener( pinController.getPinListener( Pin.SELECT_A ) );
        selectBCheck.addItemListener( pinController.getPinListener( Pin.SELECT_B ) );

        //Add Everything to the GUI
        GridBagConstraints c = new GridBagConstraints();
        c.ipadx = 25;
        c.ipady = 10;
        c.gridx = 0;
        c.gridy = 0;

        mainPanel.add( new JLabel( "Input Selectors" ), c );

        c.gridy = 1;
        mainPanel.add( selectACheck, c );

        c.gridy = 2;
        mainPanel.add( selectBCheck, c );
    }

    //Build the 4 Checkboxes for the 4 Possible inputs to the Multiplexer
    private void initInputPins( JPanel mainPanel ) {
        //Make the boxes
        JCheckBox in0Check = new JCheckBox( "Input 0" );
        JCheckBox in1Check = new JCheckBox( "Input 1" );
        JCheckBox in2Check = new JCheckBox( "Input 2" );
        JCheckBox in3Check = new JCheckBox( "Input 3" );

        //Give the checkboxes a callback linked to the PinController
        in0Check.addItemListener( pinController.getPinListener( Pin.IN_0 ) );
        in1Check.addItemListener( pinController.getPinListener( Pin.IN_1 ) );
        in2Check.addItemListener( pinController.getPinListener( Pin.IN_2 ) );
        in3Check.addItemListener( pinController.getPinListener( Pin.IN_3 ) );


        //Add everything to the GUI
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 0;
        c.ipadx = 25;
        c.ipady = 10;

        mainPanel.add( new JLabel( "Input Pins" ), c );

        c.gridy = 1;
        mainPanel.add( in0Check, c );

        c.gridy = 2;
        mainPanel.add( in1Check, c );

        c.gridy = 3;
        mainPanel.add( in2Check, c );

        c.gridy = 4;
        mainPanel.add( in3Check, c );
    }
}