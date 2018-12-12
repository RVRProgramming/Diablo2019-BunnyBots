package org.usfirst.frc.team87.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team87.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
/**
 *
 */
public class Claw extends Subsystem {

	WPI_TalonSRX _leftClawMotor = new WPI_TalonSRX(RobotMap.LEFTCLAWMOTOR);
	WPI_TalonSRX _rightClawMotor = new WPI_TalonSRX(RobotMap.RIGHTCLAWMOTOR);
	SpeedControllerGroup claw = new SpeedControllerGroup(_leftClawMotor, _rightClawMotor);
	
	
	public void clawInit() {
		_leftClawMotor.setInverted(false);
	}
	
	public void run(double speed) {
		claw.set(speed);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

