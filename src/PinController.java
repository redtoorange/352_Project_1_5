import com.pi4j.io.gpio.*;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * PinController.java - This Class controls the interaction between the GUI and Pi4J, which in turn uses wiringPi to
 * control the GPIO pins of the Raspberry Pi.  This class handles the setup and shutdown of the GPIO pins as well as
 * setting their states.
 *
 * @author Andrew McGuiness
 * @version 11/4/2017
 */
public class PinController {
    private GpioController gpio;

    private GpioPinDigitalOutput input0;
    private GpioPinDigitalOutput input1;
    private GpioPinDigitalOutput input2;
    private GpioPinDigitalOutput input3;

    private GpioPinDigitalOutput selectA;
    private GpioPinDigitalOutput selectB;

    /** Initialize Pi4J and Provision all of our pins. */
    public PinController() {
        //Initialize the Pi4J
        gpio = GpioFactory.getInstance();

        //Provision the Input Pins
        input3 = gpio.provisionDigitalOutputPin(
                RaspiPin.GPIO_00,
                null,
                PinState.LOW );
        input2 = gpio.provisionDigitalOutputPin(
                RaspiPin.GPIO_02,
                null,
                PinState.LOW );
        input1 = gpio.provisionDigitalOutputPin(
                RaspiPin.GPIO_03,
                null,
                PinState.LOW );
        input0 = gpio.provisionDigitalOutputPin(
                RaspiPin.GPIO_12,
                null,
                PinState.LOW );


        //Provision the Selector Pins
        selectA = gpio.provisionDigitalOutputPin(
                RaspiPin.GPIO_13,
                null,
                PinState.LOW );

        selectB = gpio.provisionDigitalOutputPin(
                RaspiPin.GPIO_14,
                null,
                PinState.LOW );
    }

    /**
     * Change the state of a Pin to LOW or HIGH
     *
     * @param pin   Pin to change
     * @param state New State
     */
    public void setPinState( Pin pin, PinState state ) {
        GpioPinDigitalOutput p = null;

        switch ( pin ) {
            case SELECT_A:
                p = selectA;
                break;
            case SELECT_B:
                p = selectB;
                break;
            case IN_0:
                p = input0;
                break;
            case IN_1:
                p = input1;
                break;
            case IN_2:
                p = input2;
                break;
            case IN_3:
                p = input3;
                break;
        }

        if ( p != null ) {
            if ( state == PinState.HIGH )
                p.high();
            else
                p.low();
        }

    }

    /** Reset all pins to LOW and shutdown Pi4j. */
    public void cleanUp() {
        input0.low();
        input1.low();
        input2.low();
        input3.low();

        selectA.low();
        selectB.low();

        gpio.shutdown();
    }


    /**
     * Create a callback for a pin.  The pin's state will be set based on whether a checkbox was selected or not.
     *
     * @param pin Pin to attach this callback to.
     * @return a new ItemListener.
     */
    public ItemListener getPinListener( final Pin pin ) {
        return new ItemListener() {
            @Override
            public void itemStateChanged( ItemEvent e ) {
                if ( e.getStateChange() == ItemEvent.SELECTED ) {
                    setPinState( pin, PinState.HIGH );
                } else {
                    setPinState( pin, PinState.LOW );
                }

            }
        };
    }
}
