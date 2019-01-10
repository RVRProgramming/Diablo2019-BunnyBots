/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team87.robot;
//
//import org.usfirst.frc.team87.robot.commands.ArcadeDriveCommand;
//import org.usfirst.frc.team87.robot.commands.TankDriveCommand;
import org.usfirst.frc.team87.robot.subsystems.DriveBase;
import org.usfirst.frc.team87.robot.subsystems.Intake;
import org.usfirst.frc.team87.robot.subsystems.Claw;
import edu.wpi.first.wpilibj.smartdashboard.*;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.*;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
//import edu.wpi.first.wpilibj.CameraServer;
//import edu.wpi.first.wpilibj.ADXRS450_Gyro;

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


	Joystick _joystick = new Joystick(RobotMap.JOYSTICK);
	Joystick _gamepad = new Joystick(RobotMap.GAMEPAD);
	
	
	Timer timer = new Timer();

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
		//SmartDashboard.putNumber("Gyro", _gyro.getRate());
		
		// Have Arcade Drive As Default
		//teleopCommandSendableChooser.addDefault("Tank Drive", new TankDriveCommand());
		//teleopCommandSendableChooser.addObject("Arcade Drive", new ArcadeDriveCommand());
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
		timer.reset();
		timer.start();
		
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		
		Scheduler.getInstance().run();
		if(timer.get() < 10.0) {
			driveBase.autonomousArcade(0.75, 0, true);
			//driveBase.customTank(-0.45, 0.5);
		} else {
			driveBase.stopRun();
		}
	}

	@Override
	public void teleopInit() {
		//_gyro.calibrate();
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		//SmartDashboard.getNumber("Gyro", _gyro.getRate());
				
		// Subsystems
		intake.run(_joystick.getRawAxis(1) * -1.0);
		claw.run(_joystick.getRawAxis(2) * 0.75);
//		driveBase.customArcade(_gamepad.getRawAxis(4) * 0.75, _gamepad.getRawAxis(1) * 1.0, false);
		driveBase.customArcade(_gamepad.getRawAxis(1) * -1.0, _gamepad.getRawAxis(4) * -1.0, false);
//		do {claw.enableClose();}while(_joystick.getRawButton(3));
//		do {claw.enableOpen();}while(_joystick.getRawButton(4));
	}
	
	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
		
	}
}
