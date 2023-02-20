package frc.lib.util;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public final class LogitechController {
	public static final int yAxis = 1;
	public static final int xAxis = 0;
	public static final int rotAxis = 4;

	private static final double deadband = 0.05;

	public final JoystickButton A, B, X, Y, LB, RB, BACK, START, LT, RT;
	public final JoystickAxis LS_X, LS_Y, RS_X, RS_Y, LT_S, RT_S;
	public final Supplier<Boolean> UP, UP_RIGHT, RIGHT, DOWN_RIGHT, DOWN, DOWN_LEFT, LEFT, UP_LEFT, CENTER;

	public LogitechController(int port) {

		Joystick joystick = new Joystick(port);

		A = new JoystickButton(joystick, 1);
		B = new JoystickButton(joystick, 2);
		X = new JoystickButton(joystick, 3);
		Y = new JoystickButton(joystick, 4);
		LB = new JoystickButton(joystick, 5);
		RB = new JoystickButton(joystick, 6);
		BACK = new JoystickButton(joystick, 7);
		START = new JoystickButton(joystick, 8);
		LT = new JoystickButton(joystick, 9);
		RT = new JoystickButton(joystick, 10);

		LS_X = new JoystickAxis(joystick, 0, deadband);
		LS_Y = new JoystickAxis(joystick, 1, deadband);
		RS_X = new JoystickAxis(joystick, 4, deadband);
		RS_Y = new JoystickAxis(joystick, 5, deadband);

		LT_S = new JoystickAxis(joystick, 2, deadband);
		RT_S = new JoystickAxis(joystick, 3, deadband);

		UP = () -> joystick.getPOV() == 0;
		UP_RIGHT = () -> joystick.getPOV() == 45;
		RIGHT = () -> joystick.getPOV() == 90;
		DOWN_RIGHT = () -> joystick.getPOV() == 135;
		DOWN = () -> joystick.getPOV() == 180;
		DOWN_LEFT = () -> joystick.getPOV() == 225;
		LEFT = () -> joystick.getPOV() == 270;
		UP_LEFT = () -> joystick.getPOV() == 315;
		CENTER = () -> joystick.getPOV() == -1;
	}
}
