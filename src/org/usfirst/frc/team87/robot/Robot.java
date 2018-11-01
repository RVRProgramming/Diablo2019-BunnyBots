/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team87.robot;

// Not used so JAVA can stfu
/*
 *import edu.wpi.first.wpilibj.GamepadBase;
 *import edu.wpi.first.wpilibj.SpeedController;
 *import edu.wpi.first.wpilibj.GenericHID.RumbleType;
*/

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
// API Imports
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

// Project Imports
// import org.usfirst.frc.team87.robot.commands.ExampleCommand;
// import org.usfirst.frc.team87.robot.commands.AutonomousCommand;
import org.usfirst.frc.team87.robot.commands.*;
import org.usfirst.frc.team87.robot.subsystems.ExampleSubsystem;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	// Examples
	public static ExampleSubsystem m_subsystem = new ExampleSubsystem();
	public static OI m_oi;
	
	// A timer
	Timer timer = new Timer();
	
	
	// CHANNEL NUMBERS ARE SUBJECT TO CHANGE
	Talon leftFrontMotor = new Talon(13);
	Talon leftRearMotor = new Talon(14);
	Talon rightFrontMotor = new Talon(15);
	Talon rightRearMotor = new Talon(16);
	
	SpeedControllerGroup leftMotorDrive = new SpeedControllerGroup(leftFrontMotor, leftRearMotor);
	SpeedControllerGroup rightMotorDrive = new SpeedControllerGroup(rightFrontMotor, rightRearMotor);
	
	DifferentialDrive drive = new DifferentialDrive(leftMotorDrive, rightMotorDrive);
	
	
	// Gamepad can be a Joystick or XBoxController or GenericHID(deprecated)
	
	Joystick joystick = new Joystick(0);
	Joystick gamepad = new Joystick(1);
	XboxController dapemag = new XboxController(3);

	
	Command autonomousCommand;
	SendableChooser<Command> sendableChooser = new SendableChooser<>();
	
	//Command m_autonomousCommand;
	//SendableChooser<Command> m_chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		m_oi = new OI();
		sendableChooser.addDefault("Default Auto", new ExampleCommand());
		sendableChooser.addObject("My Auto", new AutonomousCommand());
		SmartDashboard.putData("Auto mode", sendableChooser);
		
		// Camera Feed - One Line 😂😂😂
		CameraServer.getInstance().startAutomaticCapture(0);
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		autonomousCommand = sendableChooser.getSelected();
		
		timer.reset();
		timer.start();
		
		
		 String autoSelected = SmartDashboard.getString("Auto Selector", "Default"); 
		 
		 switch(autoSelected){
		 	case "My Auto": autonomousCommand = new AutonomousCommand();
		 		break;
		 	
		 	case "Default Auto":
		 		
		 	default:autonomousCommand = new ExampleCommand(); 
		 		break; 
		 }
		 

		// schedule the autonomous command (example)
		if (autonomousCommand != null) {
			autonomousCommand.start();
		}
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
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null) {
			autonomousCommand.cancel();
		}
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		
		
		// Simple drive functionality of the drive base
		leftMotorDrive.set(gamepad.getRawAxis(2));
		rightMotorDrive.set(gamepad.getRawAxis(3));
		
		
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}
