package org.usfirst.frc.team87.robot.subsystems;

import java.util.ArrayList;

import org.usfirst.frc.team87.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
//import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
/**
 *
 */
public class DriveBase extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	PIDController pidController;
	
//	Joystick gamepad = new Joystick(0);
	boolean arcadeDriveActive = false;
	
	
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
	//PIDController leftPidController = new PIDController(kP, kI, kD, leftEncoder, _leftDrive);
	//PIDController rightPidController = new PIDController(kP, kI, kD, rightEncoder, _rightDrive);
	
	Joystick _joystick = new Joystick(RobotMap.JOYSTICK);
	Joystick _gamepad = new Joystick(RobotMap.GAMEPAD);
	
	
	// Deadband Elimination; Might Not Be Used
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
	
	public void tankDrive(double leftSpeed, double rightSpeed) {
		
		_robotDrive.tankDrive(leftSpeed, rightSpeed);
		/*
		_leftFrontMotor.set(leftSpeed);
		_leftRearMotor.set(leftSpeed);
		
		_rightFrontMotor.set(rightSpeed);
		_rightRearMotor.set(rightSpeed);
		 */
	}

	public void arcadeDrive(double speed, double rotation, boolean sqInp) {
		_robotDrive.arcadeDrive(speed, rotation, sqInp);
	}
	
	
	public void run() {
		_robotDrive.tankDrive(_gamepad.getRawAxis(1) * -1.0, _gamepad.getRawAxis(5) * -1.0);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	//setDefaultCommand(new TankDrive());
    }
}

