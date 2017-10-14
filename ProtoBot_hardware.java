package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

/**
 * Created by Luke on 10/13/2017.
 */

public class ProtoBot_hardware {
	// declare all motors and sensors here:
	DcMotor rDrive = null;
	DcMotor lDrive = null;

	BNO055IMU imu = null;
	Orientation orientation;
	Acceleration acceleration;

	HardwareMap hardwareMap = null;
	ElapsedTime time = null;

	public ProtoBot_hardware(){}

	public void initMotors (HardwareMap hardware) {
		hardwareMap = hardware;

		// define and initialize motors
		rDrive = hardwareMap.dcMotor.get("left drive");
		lDrive = hardwareMap.dcMotor.get("left drive");

		// set speed
		rDrive.setPower(0.0);
		lDrive.setPower(0.0);

		// set direction
		lDrive.setDirection(DcMotorSimple.Direction.REVERSE);

		// set mode
		rDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
		lDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
	}

	public void initIMU (HardwareMap hardware) {
		hardwareMap = hardware;

		// initialise the IMU
		BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
		// setup the accelerometer
		parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
		parameters.accelPowerMode      = BNO055IMU.AccelPowerMode.NORMAL;
		parameters.accelBandwidth      = BNO055IMU.AccelBandwidth.HZ62_5;
		// setup the gyro
		parameters.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
		parameters.gyroPowerMode       = BNO055IMU.GyroPowerMode.FAST;
		parameters.gyroBandwidth       = BNO055IMU.GyroBandwidth.HZ32;
		parameters.gyroRange           = BNO055IMU.GyroRange.DPS2000;
		// setup the magnetometer
		parameters.magPowerMode        = BNO055IMU.MagPowerMode.FORCE;
		parameters.magRate             = BNO055IMU.MagRate.HZ20;
		// setup the calibration files and logging
		parameters.calibrationDataFile = "BNO055IMUCalibration.json";
		parameters.loggingEnabled      = true;
		parameters.loggingTag          = "IMU";
		parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

		imu = hardwareMap.get(BNO055IMU.class, "imu");
		imu.initialize(parameters);
	}
}
