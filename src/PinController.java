import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;


public class PinController {
	private GpioController gpio;
	
	private GpioPinDigitalOutput input0;
	private GpioPinDigitalOutput input1;
	private GpioPinDigitalOutput input2;
	private GpioPinDigitalOutput input3;
	
	private GpioPinDigitalOutput selectA;
	private GpioPinDigitalOutput selectB;
	
	
	public PinController(){
		gpio = GpioFactory.getInstance();
		
		input0 = gpio.provisionDigitalOutputPin(
				RaspiPin.GPIO_00,
				null,
				PinState.LOW);
		input1 = gpio.provisionDigitalOutputPin(
				RaspiPin.GPIO_02,
				null,
				PinState.HIGH);
		input2 = gpio.provisionDigitalOutputPin(
				RaspiPin.GPIO_03,
				null,
				PinState.HIGH);
		input3 = gpio.provisionDigitalOutputPin(
				RaspiPin.GPIO_12,
				null,
				PinState.LOW);
		
		
		selectA = gpio.provisionDigitalOutputPin(
				RaspiPin.GPIO_13,
				null,
				PinState.LOW);
		
		selectB = gpio.provisionDigitalOutputPin(
				RaspiPin.GPIO_14,
				null,
				PinState.LOW);
	}
	
	public void setPinState( Pin pin, PinState state){
		GpioPinDigitalOutput p = null;
		
		switch( pin ){
			case SELECT_A:
				p = selectA; break;
			case SELECT_B:
				p = selectB; break;
			case IN_0:
				p = input0; break;
			case IN_1:
				p = input1; break;
			case IN_2:
				p = input2; break;
			case IN_3:
				p = input3; break;
		}
		
		if( p != null){
			if( state == PinState.HIGH)
				p.high();
			else
				p.low();
		}
		
	}
	
	public void cleanUp(){
		input0.low();
		input1.low();
		input2.low();
		input3.low();
		
		selectA.low();
		selectB.low();
		
		gpio.shutdown();
	}


	public ItemListener getPinListener( final Pin pin ){
		return (e)-> {
				if(e.getStateChange() == ItemEvent.SELECTED){
					setPinState(pin, PinState.HIGH);
				}
				else{
					setPinState(pin, PinState.LOW );
				}

		};
	}





}
