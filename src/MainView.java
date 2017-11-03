import javax.swing.*;
import java.awt.*;

/**
 * MainView.java - Description
 *
 * @author Andrew McGuiness
 * @version 11/3/2017
 */
public class MainView extends JFrame {
    private PinController pinController;


    public MainView(){
        pinController = new PinController();

        initGUI();

        pinController.cleanUp();
    }

    private void initGUI() {
        JFrame frame = new JFrame("GPIO Tester");
        JPanel mainPanel = new JPanel();
        frame.setContentPane(mainPanel);
        mainPanel.setLayout(new GridBagLayout());


        initSelectorPins( mainPanel );
        initInputPins( mainPanel );


        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.pack();
        frame.setVisible(true);
    }

    private void initSelectorPins( JPanel mainPanel ) {
        JCheckBox selectACheck = new JCheckBox("Select A");
        selectACheck.addItemListener( pinController.getPinListener( Pin.SELECT_A ));

        JCheckBox selectBCheck = new JCheckBox("Select B");
        selectBCheck.addItemListener(pinController.getPinListener( Pin.SELECT_B ));

        //Input Selectors
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        mainPanel.add(new JLabel("Input Selectors"), c);

        c.gridy = 1;
        mainPanel.add(selectACheck, c);

        c.gridy = 2;
        mainPanel.add(selectBCheck, c);
    }

    private void initInputPins(JPanel mainPanel){
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 0;
        mainPanel.add(new JLabel("Input Pins"), c);

        JCheckBox in0Check = new JCheckBox("Input 0");
        in0Check.addItemListener(pinController.getPinListener( Pin.IN_0 ));

        JCheckBox in1Check = new JCheckBox("Input 1");
        in1Check.addItemListener(pinController.getPinListener( Pin.IN_1 ));

        JCheckBox in2Check = new JCheckBox("Input 2");
        in2Check.addItemListener(pinController.getPinListener( Pin.IN_2 ));

        JCheckBox in3Check = new JCheckBox("Input 3");
        in3Check.addItemListener(pinController.getPinListener( Pin.IN_3 ));


        c.gridy = 1;
        mainPanel.add(in0Check, c);

        c.gridy = 2;
        mainPanel.add(in1Check, c);

        c.gridy = 3;
        mainPanel.add(in2Check, c);

        c.gridy = 4;
        mainPanel.add(in3Check, c);
    }
}
