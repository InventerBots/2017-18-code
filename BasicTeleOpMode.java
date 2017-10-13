package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * Created by Luke on 10/12/2017.
 *
 * This is a basic TeleOp mode for learning basic motor commands. There is a Autonomous training sample at {@link BasicAutoumonumsMode}
 *
 * This mode will teach:
 *      Motor declaration and initialisation
 *      Setting the mode of a motor
 *      Setting the speed of a motor
 *      Using a gamepad to control a motor
 *      Basic POV (point of view) control
 */

/**
 * @TeleOp allows the program to be run on the Drive Station phone as a TeleOp mode.
 * "name" sets the name that will be displayed on the dropdown window for the TeleOp modes. If no name is set, the class name (ie. BasicTeleOpMode)
 * will be displayed
 * "group" organises the OpModes in the dropdown window. If the group is  not set, then this OpMode will not be sorted.
 *
 * @Disabled makes this OpMode "invisible" on the dropdown mode selection window. Comment out (ctrl + / or //) to view in the dropdown mode selection window.
 */
@TeleOp(name = "Basic POV TeleOp", group = "Training")
@Disabled
public class BasicTeleOpMode extends LinearOpMode {
	/**
	 * This is ware all the global variables, constants, and fields are defined.
	 * Examples of global variables are:
	 *      public double lMotorSpeed = 0;
	 *      private double gyroZVal;
	 *      int rDriveTarget = 492;
	 *
	 * Public means that the variable (or field) can be accessed from anywhere, not just in the in this program.
	 * Private means that that the variable (or field) can be accessed from this program only.
	 * If neither of the modifiers listed above are used (ie. String text;) the variable (or field) can only be accessed from this program.
	 *
	 * Examples of fields:
	 *
	 */
	DcMotor lDrive = null; // declare the left drive motor
	DcMotor rDrive = null; // declare the right drive motor

	@Override
	public void runOpMode() throws InterruptedException {
		/**
		 * Initialisation code goes here:
		 *
		 * It is best to set the motor power before setting the mode and direction.
		 *
		 * NOTE:
		 * When initialising a motor (or any other deice in ftc) the text in the double qwotes is the name
		 * that goes in the initiation file of the Robot Controller phone.
		 */

		/**
		 * Telemetry:
		 *
		 * The text in the double qwotes is displayed on the Driver Station phone.
		 *
		 * telemetry.addLine(); can add a line between two lines of text.
		 * Telemetry MUST be updated using ether "telemetry.update();", or "updateTelemetry(telemetry);"!
		 */
		telemetry.addLine("Initialisation has stared, please wait..."); // show on the driver station phone that the robot is initialising
		telemetry.update();

		lDrive = hardwareMap.dcMotor.get("left drive"); // initialise the left drive motor as "left drive"
		rDrive = hardwareMap.dcMotor.get("right drive"); // initialise the right motor as "right drive"

		/**
		 * Motor speeds:
		 *
		 * To run a motor at full speed forward, set the motor power to 1.0, or full speed reverse set the motor speed to -1.0,
		 * and stop is 0.0. Speeds in between full and stopped, set the speed to a decimal value such as 0.5 for half speed and 0.25
		 * for courter speed, and so on.
		 */

		lDrive.setPower(0.0); // set the speed of the left motor to 0 or stopped
		rDrive.setPower(0.0); // set the speed of the right motor to 0 or stopped

		/**
		 * Run Modes:
		 *
		 * There are five modes that the motors can be run in:
		 * RUN_TO_POSITION, RUN_USING_ENCODER, RUN_WITHOUT_ENCODER, RESET_ENCODER, STOP_AND_RESET_ENCODER
		 *
		 * RUN_USING_ENCODER:
		 * This mode uses the encoder of the motor to maintain a constant speed using a PID loop "https://en.wikipedia.org/wiki/PID_controller".
		 * This mode requires the encoder to be connected to the REV Expansion Hub.
		 *
		 * RUN_WITHOUT_ENCODER:
		 * This mode does not use the encoder of the motor, it just sets the speed and doesn't change it.
		 * This mode does not require the encoder to be connected to the REV Expansion Hub.
		 *
		 * RUN_TO_POSITION:
		 * This mode will run the motor until the encoder reaches a target that is set using this command: motor.setTargetPosition(int position)
		 * This mode requires the encoder to be connected to the REV Expansion Hub.
		 *
		 * RESET_ENCODER:
		 * This mode will reset the encoder position. It is not required to reset the encoder at any time, if the new target position is added to the
		 * current position.
		 *
		 * STOP_AND_RESET_ENCODER:
		 * This mode will stop the motor and reset the encoder position. It is not required to reset the encoder at any time, if the new target position is added to the
		 * current position.
		 *
		 * NOTE:
		 * RUN_USING_ENCODER is the most common mode used on the drive, or anything that needs constant power.
		 */
		lDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER); // set the mode of the left motor to RUN_USING_ENCODER
		rDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER); // set the mode of the right motor to RUN_USING_ENCODER

		/**
		 * Reversing motors:
		 *
		 * Do this ONLY in initialisation!
		 * All motors can be reversed. The options are FORWARD, and REVERSE.
		 */
		lDrive.setDirection(DcMotorSimple.Direction.REVERSE); // reverse the left drive motor

		telemetry.addLine("Initialisation has completed, thank you for waiting!"); // show on the driver station phone that the robot has finished initialising
		telemetry.update();

		waitForStart(); // wait for the play button to be pressed

		/**
		 * This is where the code that will run until we stop the program will run.
		 * A while loop will loop everything in the brackets as long as the condition in the parentheses is true
		 *
		 * Example:
		 * while (1 < 2) {
		 *      code:
		 * }
		 * this will run code: as long as 1 < 2, once 1 !< (not less than) 2 the loop will stop and code: will not be run any more
		 *
		 *
		 * gamepad1 and gamepad2 are how we read the joystick, and buttons of controllers that control the robot
		 */
		while (opModeIsActive()) {
			telemetry.addLine("OpMode is running!");
			// we don't need to update the telemetry until the end of the loop because then every line of telemetry will be updated
			lDrive.setPower(gamepad1.left_stick_x + gamepad1.left_stick_y); // add the X axes of the left joystick to the Y axes of the same joystick and set the left drive motor speed
			rDrive.setPower(gamepad1.left_stick_x - gamepad1.left_stick_y); // subtract the X axes of the left joystick to the Y axes of the same joystick and set the left drive motor speed
			telemetry.addData("Left drive motor", lDrive.getPower()); // display the speed of the left motor
			telemetry.addData("right drive motor", rDrive.getPower()); // display the speed of the right motor
			telemetry.update(); // now we will update the telemetry
		}
	}
}
