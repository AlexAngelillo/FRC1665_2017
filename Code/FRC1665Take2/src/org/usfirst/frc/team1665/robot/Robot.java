package org.usfirst.frc.team1665.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	final String defaultAuto = "Do Nothing";
	final String driveForwardAuto = "Drive Forward";
	final String gearAuto = "Gear";
	final String leftGearAuto = "Left Gear";
	final String rightGearAuto = "Right Gear";
	String autoSelected;
	SendableChooser<String> chooser = new SendableChooser<>();
	myRobot FRC1665robot;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		chooser.addDefault("Do Nothing", defaultAuto);
		chooser.addObject("Drive Forward", driveForwardAuto);
		chooser.addObject("Gear", gearAuto);
		chooser.addObject("Left Gear", leftGearAuto);
		chooser.addObject("Right Gear", rightGearAuto);
		SmartDashboard.putData("Auto choices", chooser);
		FRC1665robot = new myRobot();
		FRC1665robot.init();
		FRC1665robot.compressorControl();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString line to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the
	 * switch structure below with additional strings. If using the
	 * SendableChooser make sure to add them to the chooser code above as well.
	 */
	@Override
	public void autonomousInit() {
		autoSelected = chooser.getSelected();
		autoSelected = SmartDashboard.getString("Auto Selector", defaultAuto);
		System.out.println("Auto selected: " + autoSelected);
		FRC1665robot.resetEncoders();
		FRC1665robot.startTimer();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		switch (autoSelected) {
		case driveForwardAuto:
			// Put custom auto code here
			FRC1665robot.driveForwardAuto();
			break;
		case gearAuto:
			FRC1665robot.gearAuto();
			break;
		case leftGearAuto:
			FRC1665robot.ballAndGearAuto(-1);
			break;
		case rightGearAuto:
			FRC1665robot.ballAndGearAuto(1);
			break;
		case defaultAuto:
		default:
			// Put default auto code here
			break;
		}
	}
	
	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Timer.delay(0.01);
		FRC1665robot.baseDriveControl();
		FRC1665robot.ballLiftControl();
		FRC1665robot.ballCaptureControl();
		FRC1665robot.outtakeControl();
		FRC1665robot.gearControl();
		FRC1665robot.climberControl();
		FRC1665robot.batteryControl();
		FRC1665robot.smartDashboardControl();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
	}
}

