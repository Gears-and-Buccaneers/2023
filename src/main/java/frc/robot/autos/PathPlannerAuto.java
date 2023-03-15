package frc.robot.autos;

import frc.robot.Constants.kAuto;
import frc.robot.Constants.kSwerve;
import frc.robot.subsystems.Swerve;

import com.pathplanner.lib.PathPlanner;

import java.util.HashMap;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;

import com.pathplanner.lib.auto.SwerveAutoBuilder;

public class PathPlannerAuto {
	// This is just an example event map. It would be better to have a constant,
	// global event map in your code that will be used by all path following
	// commands.
	HashMap<String, Command> eventMap = new HashMap<>();
	// eventMap.put("marker1", new PrintCommand("Passed marker 1"));
	// eventMap.put("intakeDown", new IntakeDown());

	SwerveAutoBuilder autoBuilder;

	public PathPlannerAuto(Swerve swerve) {
		autoBuilder = new SwerveAutoBuilder(
				swerve::getPose, // Pose2d supplier
				swerve::resetOdometry, // Pose2d consumer, used to reset odometry at the beginning of auto
				kSwerve.kinematics, // SwerveDriveKinematics
				kAuto.translation, // PID constants to correct for translation error (used to create the X
				// and Y PID controllers)
				kAuto.rotation, // PID constants to correct for rotation error (used to create the
				// rotation controller)
				(moduleStates) -> swerve.setModuleStates(moduleStates, true), // Module states consumer used to output to the
																																			// drive subsystem
				eventMap,
				swerve // The drive subsystem. Used to properly set the requirements of path following
		// commands
		);
	}

	public CommandBase get(String name) {
		return autoBuilder.fullAuto(PathPlanner.loadPathGroup(name, kAuto.constraints));
	}
}
