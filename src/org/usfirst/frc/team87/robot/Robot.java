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
import org.usfirst.frc.team87.robot.commands.*;
import edu.wpi.first.wpilibj.smartdashboard.*;
import edu.wpi.first.wpilibj.command.*;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.*;

import javax.sound.sampled.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */

public class Robot extends IterativeRobot {
	
	Timer timer = new Timer();
	
	Command tankDriveCommand;
	Command arcadeDriveCommand;
	Command teleopCommand;
	
	SendableChooser<Command> teleopCommandSendableChooser = new SendableChooser<>();
	

	PowerDistributionPanel pdp = new PowerDistributionPanel();
	Intake intake = new Intake();
	Claw claw = new Claw();
	DriveBase driveBase = new DriveBase();

	Joystick _joystick = new Joystick(0);
	Joystick _gamepad = new Joystick(1);
	
	static boolean arcadeActive;
	
	@Override
	public void robotInit() {
		// Initialize our subsystems
		intake.initIntake();
		driveBase.driveBaseInit();
		arcadeActive = false;
		
		// Initialize Smartdashboard
		SmartDashboard.getNumber("Gamepad Left", _gamepad.getRawAxis(2));
		SmartDashboard.getNumber("Gamepad Right", _gamepad.getRawAxis(3));
		SmartDashboard.getNumber("Joystick Y", _joystick.getY());
		SmartDashboard.getBoolean("Arcade Active", arcadeActive);
		
		// Have Arcade Drive As Default
		teleopCommandSendableChooser.addDefault("Tank Drive", new TankDrive());
		teleopCommandSendableChooser.addObject("Arcade Drive", new ArcadeDrive());
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
		intake.run(_gamepad.getY());
		claw.run(_gamepad.getRawAxis(1));
		
		teleopCommand = teleopCommandSendableChooser.getSelected();

		// Smartboard requests
		SmartDashboard.putNumber("Gamepad Left", _gamepad.getRawAxis(2));
		SmartDashboard.putNumber("Gamepad Right", _gamepad.getRawAxis(3));
		SmartDashboard.putNumber("Joystick Y", _joystick.getY());
		SmartDashboard.putBoolean("Arcade Active", arcadeActive);

		
		// Test that Shuffleboard is working
		System.out.println(SmartDashboard.getData("Number Slider"));
		
		if(_gamepad.getRawButtonPressed(4) == arcadeActive) {
			driveBase.arcadeDrive(_gamepad.getRawAxis(1) * -1.0, _gamepad.getRawAxis(4), false);
		} else {
			driveBase.tankDrive(_gamepad.getRawAxis(2), _gamepad.getRawAxis(3));
		}
	}
	
	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
		
	}
}
