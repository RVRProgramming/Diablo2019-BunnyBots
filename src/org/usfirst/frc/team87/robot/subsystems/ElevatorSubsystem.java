package org.usfirst.frc.team87.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;


public class ElevatorSubsystem extends Subsystem {

	DigitalInput leftLimitSwitch = new DigitalInput(0);
	DigitalInput rightLimitSwitch = new DigitalInput(1);
	
	
	
	
	public void up() {

		
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

