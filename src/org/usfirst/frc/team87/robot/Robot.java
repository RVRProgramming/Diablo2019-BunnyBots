/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team87.robot;

import org.usfirst.frc.team87.robot.subsystems.DriveBase;
import org.usfirst.frc.team87.robot.commands.*;
import edu.wpi.first.wpilibj.smartdashboard.*;
import edu.wpi.first.wpilibj.command.*;
import edu.wpi.first.wpilibj.interfaces.Gyro;
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
	
	AnalogGyro gyro = new AnalogGyro(0);
	
	Command tankDriveCommand;
	Command arcadeDriveCommand;
	Command teleopCommand;
	
	SendableChooser<Command> teleopCommandSendableChooser = new SendableChooser<>();
	
	DriveBase driveBase = new DriveBase();
	
	
	//Gyro gyro = new GyroBase(0);
	
	Joystick _joystick = new Joystick(0);
	Joystick _gamepad = new Joystick(1);
	
	
	@Override
	public void robotInit() {
		driveBase.driveBaseInit();
		
		
		SmartDashboard.getNumber("Gamepad Left Value", 0.0);
		SmartDashboard.getNumber("Gamepad Right Value", 0.0);
		
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

		teleopCommand = teleopCommandSendableChooser.getSelected();


		SmartDashboard.putNumber("Gamepad Left Value", _gamepad.getRawAxis(2));
		SmartDashboard.putNumber("Gamepad Right Value", _gamepad.getRawAxis(3));
	}
	
	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
		
	}
}
