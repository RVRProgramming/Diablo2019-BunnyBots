package org.usfirst.frc.team87.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
/**
 *
 */
public class DriveBase extends Subsystem implements PIDOutput{

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	PIDController pidController;
	
	
	
	public DriveBase() {
		//pidController = new PIDController(0, 0, 0);
	}
	

	
	public void tankDrive() {
		
	}

	public void arcadeDrive() {
		
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	//setDefaultCommand(new tankDrive);
    }

    
    /*
    public void drive(ControlMode mode, double leftValue, double rightValue) {
    	return _leftDrive.set(mode, leftValue);
    	return _rightDrive.set(mode, rightValue);
    }
	*/

	@Override
	public void pidWrite(double output) {
		// TODO Auto-generated method stub
		//drive(ControlMode.PercentOutput, -output, output);
	}
}

