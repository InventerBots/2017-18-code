package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * Created by Luke on 10/13/2017.
 *
 * TESTING!
 *
 * Robot will drive 1.00 meters and stop, without encoders
 */

public class AcclToDistance extends LinearOpMode {
	ProtoBot_hardware protoBot = new ProtoBot_hardware();
	/**
	 * Calculate the error of the robot in meters per second using 90mm wheels powered by REV Core Hex motors
	 * running at 62.5rpm.
	 */
	private static final double TARGET_SPEED = ((Math.PI * 90) * 62.5) / 60;
	private static final double SPEED_THRESHOLD = 0.0025;

	private double leftDrive;
	private double rightDrive;

	private volatile double xAccel;

	private double estTime;
	private double error;
	private double targetDistInMeters = 1.00; // travel 1.00 meter or 3.28 feet

	@Override
	public void runOpMode() throws InterruptedException {
		telemetry.addLine("Initialisation has started, pleas wait...");
		telemetry.update();

		protoBot.initMotors(hardwareMap);
		protoBot.initIMU(hardwareMap);

//		estTime = targetDistInMeters / TARGET_SPEED; // calculate the estimated time to reach the target

		telemetry.addLine("Initialisation has finished, thank you for waiting!");
		telemetry.update();

		waitForStart();


		do {
			telemetry.addLine("OpMode has started!");

			protoBot.time.startTime();

			protoBot.lDrive.setPower(0.5);
			protoBot.rDrive.setPower(0.5);

			protoBot.acceleration = protoBot.imu.getLinearAcceleration();
			protoBot.time.reset(); // reset the timer
			xAccel = protoBot.acceleration.xAccel;

			telemetry.addData("IMU accelerometer X value", xAccel).addData("Target distance ", targetDistInMeters)
					.addData("Time before reaching target ", estTime).addData("Run time ", protoBot.time.milliseconds());

			/**
			 * This is where all the calculations take place.
			 *
			 * First, we will calculate the estimated time before reaching the target, then we decide if we need to add
			 * or subtract the new time from the current time.
			 */
			estTime = targetDistInMeters / xAccel; // calculate the time until reaching the target

			// calculate the error error
			if (xAccel < TARGET_SPEED) estTime -= (xAccel / TARGET_SPEED);
			if (xAccel > TARGET_SPEED) estTime += (xAccel / TARGET_SPEED);

			error = estTime - protoBot.time.milliseconds();

			telemetry.addData("Time error ", error);
			telemetry.update();
		} while (xAccel > SPEED_THRESHOLD && opModeIsActive() && protoBot.time.milliseconds() < estTime);

		protoBot.lDrive.setPower(0);
		protoBot.rDrive.setPower(0);
	}
}

