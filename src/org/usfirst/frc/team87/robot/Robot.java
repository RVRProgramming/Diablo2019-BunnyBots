/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team87.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.AnalogAccelerometer;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.DigitalSource;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Timer;

import java.awt.List;
import java.util.ArrayList;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends IterativeRobot {
	

	
	// Values are arbitrary
	Encoder leftEncoder = new Encoder(0, 0);
	Encoder rightEncoder = new Encoder(0, 1);
	Gyro analogGyro = new AnalogGyro(0);
	
	Timer timer = new Timer();
	
	SmartDashboard _smartDashboard = new SmartDashboard();

	// PWM Vex Motor
	PWMVictorSPX _PWMVICTOR = new PWMVictorSPX(0);
	
	WPI_TalonSRX _leftFrontMotor = new WPI_TalonSRX(RobotMap.LEFTFRONTMOTOR);
	WPI_TalonSRX _leftRearMotor = new WPI_TalonSRX(RobotMap.LEFTREARMOTOR);
	WPI_TalonSRX _rightFrontMotor = new WPI_TalonSRX(RobotMap.RIGHTFRONTMOTOR);
	WPI_TalonSRX _rightRearMotor = new WPI_TalonSRX(RobotMap.RIGHTREARMOTOR);
	
	ArrayList<WPI_TalonSRX> talonList = new ArrayList<WPI_TalonSRX>();
	
	//{_leftFrontMotor, _leftRearMotor, _rightFrontMotor, _rightRearMotor};
	
	SpeedControllerGroup _leftDrive = new SpeedControllerGroup(_leftFrontMotor, _leftRearMotor);
	SpeedControllerGroup _rightDrive = new SpeedControllerGroup(_rightFrontMotor, _rightRearMotor);
	
	DifferentialDrive _robotDrive = new DifferentialDrive(_leftDrive, _rightDrive);

	
	int kP, kI, kD = 0;
	PIDController leftPidController = new PIDController(kP, kI, kD, leftEncoder, _leftDrive);
	PIDController rightPidController = new PIDController(kP, kI, kD, rightEncoder, _rightDrive);
	
	Joystick _joystick = new Joystick(0);
	Joystick _gamepad = new Joystick(1);
	
	@Override
	public void robotInit() {
		
		_leftFrontMotor.setInverted(false);
		_leftRearMotor.setInverted(false);
		_rightFrontMotor.setInverted(false);
		_rightRearMotor.setInverted(false);
		
		
		SmartDashboard.putBoolean("Robot Running", false);
		SmartDashboard.putBoolean("Full Speed", false);
		
		talonList.add(_leftFrontMotor);
		talonList.add(_leftRearMotor);
		talonList.add(_rightFrontMotor);
		talonList.add(_rightRearMotor);
		
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
		while(timer.get() < 2.0) {
			_robotDrive.tankDrive(0.5, -0.5);
		}
	}

	@Override
	public void teleopInit() {
		SmartDashboard.getBoolean("Robot Running", true);
		
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		
		// Deadband Elimination; Might Not Be Used
		double forward = _joystick.getY() * -1.0;
		if(Math.abs(forward) < 0.10) {
			forward = 0;
		}
		
		// Working Joystick Test
		SmartDashboard.putNumber("Joystick Values", _joystick.getY() * -1.0	);
		
		_robotDrive.setDeadband(0.10);
		_robotDrive.tankDrive(_gamepad.getRawAxis(2) * -1.0, _gamepad.getRawAxis(3) * -1.0);
		
		System.out.print(_robot);
	}
	
	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
		
		
	}
}
