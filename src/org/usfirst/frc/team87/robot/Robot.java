/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/



package org.usfirst.frc.team87.robot;

import edu.wpi.first.wpilibj.DigitalInput;
//import edu.wpi.first.wpilibj.GamepadBase;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;

import org.usfirst.frc.team87.robot.commands.*;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team87.robot.subsystems.*;



public class Robot extends TimedRobot {

	
	// Not worrying about commands
	
	public static SendableChooser<Command> sendableChooser;
	public static AutonomousCommand autonomousCommand;
	public static DriveBaseSubsystem driveBaseSubsystem;
	public static TeleopCommand teleopCommand;
	public static LEDSubsystem ledsubsystem;
	public static ElevatorSubsystem elevator;
	public static Timer timer;
	public static OI oi;
	
	
	// Were using Shuffleboard...
	public static SmartDashboard dashboard;
	
	public static DigitalInput limitswitch;
	


	@Override
	public void robotInit() {
		sendableChooser = new SendableChooser<>();
		driveBaseSubsystem = new DriveBaseSubsystem();

		//autonomousCommand = new AutonomousCommand();
		//teleopCommand = new TeleopCommand();
		ledsubsystem = new LEDSubsystem();
		elevator = new ElevatorSubsystem();
		timer = new Timer();
		oi = new OI();
		
		
		
		//limitswitch = new DigitalInput(0);
		
		
		dashboard = new SmartDashboard();
		
	}


	@Override
	public void disabledInit() {
		

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		
		driveBaseSubsystem.disableDrive();
	}


	@Override
	public void autonomousInit() {
		
		timer.reset();
		timer.start();

	}


	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		
		
		// Go around in circles for two seconds...
		if(timer.get() < 2.0) {
			driveBaseSubsystem.autonomousDrive();
		}
	}

	@Override
	public void teleopInit() {
		
	}


	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		driveBaseSubsystem.teleopDrive("tank");
		
		
	}


	@Override
	public void testPeriodic() {
	}
}
