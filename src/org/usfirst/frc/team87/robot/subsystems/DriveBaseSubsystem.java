package org.usfirst.frc.team87.robot.subsystems;


import org.usfirst.frc.team87.robot.RobotMap;
import org.usfirst.frc.team87.robot.commands.TeleopDriveCommand;

//import edu.wpi.first.wpilibj.GamepadBase;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;


public class DriveBaseSubsystem extends Subsystem {

	Joystick leftJoystick = new Joystick(RobotMap.leftJoystick);
	Joystick rightJoystick = new Joystick(RobotMap.rightJoystick);
	Joystick gamepad = new Joystick(RobotMap.gamepad);
	
	Talon leftFrontMotor = new Talon(RobotMap.leftFrontMotor);
	Talon leftRearMotor = new Talon(RobotMap.leftRearMotor);
	SpeedControllerGroup leftDrive = new SpeedControllerGroup(leftFrontMotor, leftRearMotor);
	
	Talon rightFrontMotor = new Talon(RobotMap.rightFrontMotor);
	Talon rightRearMotor = new Talon(RobotMap.rightRearMotor);
	SpeedControllerGroup rightDrive = new SpeedControllerGroup(rightFrontMotor, rightRearMotor);
	
	DifferentialDrive robotDrive = new DifferentialDrive(leftDrive, rightDrive);

	Timer timer = new Timer();
	
	// Might be used to put into robotInit...
	public DriveBaseSubsystem() {
		
	}

	public void autonomousDrive() {

	}
	
	public void teleopDrive() {
		if(gamepad.getRawButton(1)) {
//			System.out.println("IT WORKS");
			robotDrive.tankDrive(0.5, 0.5);
		}
//		System.out.println("ONes");
		
		/*
		switch(driveType) {
			
			// Drive with two joysticks
			case "tank": robotDrive.tankDrive(leftJoystick.getX(), rightJoystick.getX());
				break;
				
			// Drive with right bumper and left thumbstick to turn
			case "arcade": robotDrive.arcadeDrive(gamepad.getRawAxis(3), gamepad.getRawAxis(3));
				break;
				
		}
		*/
	}
	
	
	public void disableDrive() {
		//robotDrive.stopMotor();
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	
    	//setDefaultCommand(new TeleopCommand());
    }
}

