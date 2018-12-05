package org.usfirst.frc.team87.robot.subsystems;

import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {
	
	PWMVictorSPX _PWMVICTOR = new PWMVictorSPX(0);
	
	public Intake() {
		
	}
	
	public void run(double speed) {
		_PWMVICTOR.set(speed);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

