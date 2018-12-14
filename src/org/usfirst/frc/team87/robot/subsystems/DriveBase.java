package org.usfirst.frc.team87.robot.subsystems;

import java.util.ArrayList;

import org.usfirst.frc.team87.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.PIDController;
//import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.Joystick;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
/**
 *
 */
public class DriveBase extends Subsystem {

	PIDController pidController;
	
	WPI_TalonSRX _leftFrontMotor = new WPI_TalonSRX(RobotMap.LEFTFRONTMOTOR);
	WPI_TalonSRX _leftRearMotor = new WPI_TalonSRX(RobotMap.LEFTREARMOTOR);
	WPI_TalonSRX _rightFrontMotor = new WPI_TalonSRX(RobotMap.RIGHTFRONTMOTOR);
	WPI_TalonSRX _rightRearMotor = new WPI_TalonSRX(RobotMap.RIGHTREARMOTOR);
	
	ArrayList<WPI_TalonSRX> talonList = new ArrayList<WPI_TalonSRX>();
	
	SpeedControllerGroup _leftDrive = new SpeedControllerGroup(_leftFrontMotor, _leftRearMotor);
	SpeedControllerGroup _rightDrive = new SpeedControllerGroup(_rightFrontMotor, _rightRearMotor);
	
	DifferentialDrive _robotDrive = new DifferentialDrive(_leftDrive, _rightDrive);

	
	int kP, kI, kD = 0;
	//PIDController leftPidController = new PIDController(kP, kI, kD, leftEncoder, _leftDrive);
	//PIDController rightPidController = new PIDController(kP, kI, kD, rightEncoder, _rightDrive);
	
	Joystick _joystick = new Joystick(RobotMap.JOYSTICK);
	Joystick _gamepad = new Joystick(RobotMap.GAMEPAD);
	
	
	// Dead band Elimination; Might Not Be Used
	/*
	double forward = _joystick.getY() * -1.0;
	if(Math.abs(forward) < 0.10) {
		forward = 0;
	}
	*/
	
	public DriveBase() {
		//pidController = new PIDController(0, 0, 0);
	}
	

	public void driveBaseInit() {
		talonList.add(_leftFrontMotor);
		talonList.add(_leftRearMotor);
		talonList.add(_rightFrontMotor);
		talonList.add(_rightRearMotor);
		//_robotDrive.setDeadband(0.10);
	}
	
	public void customTank(double leftSpeed, double rightSpeed) {
		_leftFrontMotor.set(ControlMode.PercentOutput, leftSpeed);
		_leftRearMotor.set(ControlMode.PercentOutput, leftSpeed);		
		_rightFrontMotor.set(ControlMode.PercentOutput, rightSpeed);
		_rightRearMotor.set(ControlMode.PercentOutput, rightSpeed);
	}

	public void customArcade(double speed, double rotation, boolean sqInp) {
		_robotDrive.arcadeDrive(speed, rotation, sqInp);
	}
	
	public void runTank() {
		_robotDrive.tankDrive(_gamepad.getRawAxis(1) * -1.0, _gamepad.getRawAxis(5) * -1.0);
	}
	
	public void runArcade() {
		_robotDrive.arcadeDrive(_gamepad.getRawAxis(1), _gamepad.getRawAxis(6));
	}
	
	public void stopRun() {
		_robotDrive.stopMotor();
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	//setDefaultCommand(new TankDrive());
    }
}

