/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team87.robot;

import org.usfirst.frc.team87.robot.subsystems.Claw;
import org.usfirst.frc.team87.robot.subsystems.DriveBase;
import org.usfirst.frc.team87.robot.subsystems.Intake;
import edu.wpi.first.wpilibj.smartdashboard.*;
import edu.wpi.first.wpilibj.command.*;
import edu.wpi.first.wpilibj.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */

public class Robot extends IterativeRobot {
	
	Timer timer = new Timer();
	
	// Command tankDriveCommand = new TankDrive();
	// Command arcadeDriveCommand = new ArcadeDrive();
	// Command teleopCommand;
	
	SendableChooser<Command> teleopCommandSendableChooser = new SendableChooser<>();
	

//	PowerDistributionPanel pdp = new PowerDistributionPanel();
	Intake intake = new Intake();
	Claw claw = new Claw();
	DriveBase driveBase = new DriveBase();

	Joystick _joystick = new Joystick(RobotMap.JOYSTICK);
	Joystick _gamepad = new Joystick(RobotMap.GAMEPAD);

	int counter = 2;
	
	@Override
	public void robotInit() {
		
		// Initialize our subsystems
		claw.clawInit();
		intake.initIntake();
		driveBase.driveBaseInit();
		
		// Initialize Smartdashboard
		SmartDashboard.getNumber("Gamepad Left", _gamepad.getRawAxis(2));
		SmartDashboard.getNumber("Gamepad Right", _gamepad.getRawAxis(3));
		SmartDashboard.getNumber("Joystick Y", _joystick.getY());

		// Have Arcade Drive As Default
		//teleopCommandSendableChooser.addDefault("Tank Drive", new TankDrive());
		//teleopCommandSendableChooser.addObject("Arcade Drive", new ArcadeDrive());
		//SmartDashboard.putData("Teleop Mode", teleopCommandSendableChooser);
		
	}

	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void autonomousInit() {
		
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		
		// Running subsystems that we created
		intake.run(_gamepad.getRawAxis(0));
		claw.run(_gamepad.getRawAxis(4));
		
		//teleopCommand = teleopCommandSendableChooser.getSelected();

		// Smartboard requests
//		SmartDashboard.putNumber("Gamepad Left", _gamepad.getRawAxis(2));
//		SmartDashboard.putNumber("Gamepad Right", _gamepad.getRawAxis(3));
//		SmartDashboard.putNumber("Joystick Y", _joystick.getY());

		
		// Test that Shuffleboard is working
//		System.out.println(SmartDashboard.getData("Number Slider"));
		
		// Joystick Inputs are reversed...
		//driveBase.tankDrive(_gamepad.getRawAxis(5), _gamepad.getRawAxis(1));
		
		driveBase.run();
		
		/*
		if(counter % 2 == 0) {
			driveBase.tankDrive(_gamepad.getRawAxis(1) * -1.0, _gamepad.getRawAxis(5) * -1.0, _joystick.getRawAxis(3));
		} else {
			driveBase.arcadeDrive(-_joystick.getX() * -1.0, _joystick.getZ(), false);
		}
		
		if(_gamepad.getRawButtonPressed(4))
			counter += 1;
		*/
//		System.out.println(_joystick.getRawAxis(3)); 
		
		/*
		if(_gamepad.getRawButtonPressed(4) == arcadeActive) {
			driveBase.arcadeDrive(_gamepad.getRawAxis(1) * -1.0, _gamepad.getRawAxis(4), false);
		} else {
			driveBase.tankDrive(_gamepad.getRawAxis(2), _gamepad.getRawAxis(3));
		}
		*/
	}
	
	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
		
	}
}
