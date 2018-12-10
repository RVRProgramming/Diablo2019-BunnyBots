package org.usfirst.frc.team87.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.BaseMotorController;

import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {
	
	PWMVictorSPX _PWMVICTORLEFT = new PWMVictorSPX(0);
	PWMVictorSPX _PWMVICTORRIGHT = new PWMVictorSPX(1);
	
	SpeedControllerGroup _SPEEDCONTROLLERGROUP = new SpeedControllerGroup(_PWMVICTORLEFT, _PWMVICTORRIGHT);
	
	public Intake() {
		
	}
	
	
	public void initIntake() {
		_PWMVICTORLEFT.setInverted(false);
		_PWMVICTORRIGHT.setInverted(true);
	}
	
	public void run(double speed) {
		_SPEEDCONTROLLERGROUP.set(speed);
	}

	public void getMotorVoltages() {
		
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

