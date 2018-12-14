package org.usfirst.frc.team87.robot.subsystems;

import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Intake extends Subsystem {
	
	PWMVictorSPX _PWMVICTOR = new PWMVictorSPX(0);
//	PWMVictorSPX _PWMVICTORRIGHT = new PWMVictorSPX(1);
	
//	SpeedControllerGroup _SPEEDCONTROLLERGROUP = new SpeedControllerGroup(_PWMVICTORLEFT, _PWMVICTORRIGHT);
	
	public Intake() {
		
	}
	
	
	public void initIntake() {
		_PWMVICTOR.setInverted(false);
	}
	
	public void run(double speed) {
		_PWMVICTOR.set(speed);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

