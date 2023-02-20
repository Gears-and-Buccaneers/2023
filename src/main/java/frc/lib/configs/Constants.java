package frc.lib.configs;

import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import frc.lib.util.LogitechController;
import frc.lib.util.TalonConfig;
import frc.robot.subsystems.SwerveModule;

import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;

public final class Constants {
	public static final int armControllerId = 20;

	/** Robot Controls */
	public static final class Controls {
		public static final LogitechController driver = new LogitechController(0);
		public static final LogitechController operator = new LogitechController(1);
	}

	public static final class Vision {
		public static final String cameraName = "photonvision";
	}

	/** Swerve Configs */
	public static final class Swerve {
		public static final boolean openLoop = true;

		public static final int pigeonID = 24;
		public static final boolean invertGyro = false; // Always ensure Gyro is CCW+ CW-

		/* Drivetrain Constants */
		public static final double trackWidth = Units.inchesToMeters(21.73);
		public static final double wheelBase = Units.inchesToMeters(21.73);
		public static final double wheelDiameter = Units.inchesToMeters(3.94);
		public static final double wheelCircumference = wheelDiameter * Math.PI;

		public static final double driveGearRatio = 1 / 8.14; // 6.86:1
		public static final double angleGearRatio = (150 / 7); // 12.8:1

		/** Swerve angle motor configs */
		public static final TalonConfig angleConfig = new TalonConfig(0.6, 0.0, 12.0, 0.0, true, NeutralMode.Coast,
				new SupplyCurrentLimitConfiguration(true, 25, 40, 0.1));

		/** Swerve drive motor configs */
		public static final TalonConfig driveConfig = new TalonConfig(0.1, 0.0, 12.0, 0.0, false, NeutralMode.Brake,
				new SupplyCurrentLimitConfiguration(true, 35, 60, 0.1), 0.25, 0.0);

		/* Drive Motor Characterization Values */
		public static final SimpleMotorFeedforward driveFeedforward = new SimpleMotorFeedforward(0.667 / 12, 2.44 / 12,
				0.27 / 12);

		/* Swerve Profiling Values */
		public static final double maxSpeed = 2.5; // meters per second
		public static final double maxSpeedBoost = 4.5; // meters per second
		public static final double maxAngularVelocity = 11.5;

		/* Angle Encoder Invert */
		public static final boolean canCoderInvert = false;

		/** Swerve modules */
		public static final SwerveModule[] mods = {
				// Front Left Module - Module 1
				new SwerveModule(0, 1, 5, 1, 23.2),
				// Front Right Module - Module 2
				new SwerveModule(1, 2, 6, 2, 19.2),
				// Back Right Module - Module 3
				new SwerveModule(2, 3, 7, 3, 255.6),
				// Back Left Module - Module 4
				new SwerveModule(3, 4, 8, 4, 187.6)
		};

		public static final SwerveDriveKinematics swerveKinematics = new SwerveDriveKinematics(
				new Translation2d(wheelBase / 2.0, trackWidth / 2.0),
				new Translation2d(wheelBase / 2.0, -trackWidth / 2.0),
				new Translation2d(-wheelBase / 2.0, -trackWidth / 2.0),
				new Translation2d(-wheelBase / 2.0, trackWidth / 2.0));
	}

	public static final class Pneumatics {
		public static final int compressorId = 0;
		public static Compressor compressor = new Compressor(compressorId, PneumaticsModuleType.CTREPCM);
	}

	public static final class Boom {
		public enum BoomLevel {
			BOTTOM(1),
			MIDDLE(10),
			TOP(15),
			INTAKE(3);

			private final double length;

			private BoomLevel(double l) {
				length = l;
			}

			public double getLength() {
				return length;
			}
		}

		public static final int forwardId = 2;
		public static final int reverseId = 3;
	}

	public static final class Gripper {
		public static final int forwardId = 0;
		public static final int reverseId = 1;
	}

	public static final class AutoConstants {
		public static final double kMaxSpeedMetersPerSecond = 3;
		public static final double kMaxAccelerationMetersPerSecondSquared = 3;
		public static final double kMaxAngularSpeedRadiansPerSecond = Math.PI;
		public static final double kMaxAngularSpeedRadiansPerSecondSquared = Math.PI;

		public static final double kPXController = 1;
		public static final double kPYController = 1;
		public static final double kPThetaController = 1;

		// Constraint for the motion profilied robot angle controller
		public static final TrapezoidProfile.Constraints kThetaControllerConstraints = new TrapezoidProfile.Constraints(
				kMaxAngularSpeedRadiansPerSecond, kMaxAngularSpeedRadiansPerSecondSquared);
	}

}
