/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team87.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.*;
import edu.wpi.first.wpilibj.PWMVictorSPX;

//import edu.wpi.first.wpil

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends IterativeRobot {


	PWMVictorSPX _PWMVICTOR = new PWMVictorSPX(0);
	
	WPI_TalonSRX _leftFrontMotor = new WPI_TalonSRX(3);
	WPI_TalonSRX _leftRearMotor = new WPI_TalonSRX(1);//
	WPI_TalonSRX _rightFrontMotor = new WPI_TalonSRX(2);//
	WPI_TalonSRX _rightRearMotor = new WPI_TalonSRX(4);
	
	SpeedControllerGroup _leftDrive = new SpeedControllerGroup(_leftFrontMotor, _leftRearMotor);
	SpeedControllerGroup _rightDrive = new SpeedControllerGroup(_rightFrontMotor, _rightRearMotor);
	
	DifferentialDrive _robotDrive = new DifferentialDrive(_leftDrive, _rightDrive);

	Joystick _joystick = new Joystick(0);
	Joystick _gamepad = new Joystick(1);
	
	@Override
	public void robotInit() {
		//Makes sure motor spins correctly
		//_redline.setInverted(false);
		
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
		
		// Make forward on joystick positive
		
		/*
		double forward = _joystick.getY() * -1.0;
		
		if(Math.abs(forward) < 0.10) {
			forward = 0;
		}
		*/
		
		// Test for output of redline
		//_redline.set(forward);
		//_PWMVICTOR.set(forward);
		//System.out.println(_redline.get());
		//System.out.println(_PWMVICTOR.get());
		
		//_robotDrive.tankDrive(_gamepad.getRawAxis(2), _gamepad.getRawAxis(3));
	
		if(_gamepad.getRawButton(2)) {
			_robotDrive.tankDrive(_gamepad.getRawAxis(2) * -1.0, _gamepad.getRawAxis(3) * -1.0);
		} else {			
			_robotDrive.tankDrive(_gamepad.getRawAxis(2), _gamepad.getRawAxis(3));
		}
		
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
		
	}
}
