/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team87.robot;

import org.usfirst.frc.team87.robot.commands.ArcadeDriveCommand;
import org.usfirst.frc.team87.robot.commands.TankDriveCommand;
import org.usfirst.frc.team87.robot.subsystems.DriveBase;
import org.usfirst.frc.team87.robot.subsystems.Intake;
import org.usfirst.frc.team87.robot.subsystems.Claw;
import edu.wpi.first.wpilibj.smartdashboard.*;
import edu.wpi.first.wpilibj.command.*;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */

public class Robot extends IterativeRobot {
	// Command tankDriveCommand = new TankDrive();
	// Command arcadeDriveCommand = new ArcadeDrive();
	// Command teleopCommand;
	
	
	SendableChooser<Command> teleopCommandSendableChooser = new SendableChooser<>();
	
	public static Intake intake = new Intake();
	public static Claw claw = new Claw();
	public static DriveBase driveBase = new DriveBase();

	ADXRS450_Gyro _gyro = new ADXRS450_Gyro();
	Joystick _joystick = new Joystick(RobotMap.JOYSTICK);
	Joystick _gamepad = new Joystick(RobotMap.GAMEPAD);

	@Override
	public void robotInit() {
		
		// Initialize our subsystems
		claw.clawInit();
		intake.initIntake();
		driveBase.driveBaseInit();

		// Put Subsystems In SmartDashboard
		SmartDashboard.putData(driveBase);
		SmartDashboard.putData(claw);
		SmartDashboard.putData(intake);
		
		
		// Have Arcade Drive As Default
		teleopCommandSendableChooser.addDefault("Tank Drive", new TankDriveCommand());
		teleopCommandSendableChooser.addObject("Arcade Drive", new ArcadeDriveCommand());
		SmartDashboard.putData("Teleop Mode", teleopCommandSendableChooser);	
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
		_gyro.calibrate();
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();

		System.out.println(_gyro.getRate());
		
		claw.run(_gamepad.getRawAxis(4));
		driveBase.runTank();
		
		_gamepad.getPOV(360);
		_gamepad.getPOV(180);
		
		
		//teleopCommand = teleopCommandSendableChooser.getSelected();
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
