/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team87.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	Joystick leftJoystick = new Joystick(RobotMap.LEFTJOYSTICK);
	Joystick rightJoystick = new Joystick(RobotMap.RIGHTJOYSTICK);
	
	Button leftButtonOne = new JoystickButton(leftJoystick, 0),
			leftButtonTwo = new JoystickButton(leftJoystick, 1),
			leftButtonThree = new JoystickButton(leftJoystick, 2);
	
	Button rightButtonOne = new JoystickButton(rightJoystick, 0),
			rightButtonTwo = new JoystickButton(rightJoystick, 1),
			rightButtonThree = new JoystickButton(rightJoystick, 2);

	public OI() {
		// Lower Elevator
		leftButtonOne.whenPressed(new Command() {
			
			@Override
			protected boolean isFinished() {
				// TODO Auto-generated method stub
				return false;
			}
		});
		
		// Center Elevator
		leftButtonTwo.whenPressed(new Command() {
			@Override
			protected boolean isFinished() {
				// TODO Auto-generated method stub
				return false;
			}
		});
		
		// Raised Elevator
		leftButtonThree.whenPressed(new Command() {
			@Override
			protected boolean isFinished() {
				// TODO Auto-Generated method stub
				return false;
			}
		});
	}
}
