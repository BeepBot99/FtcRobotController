package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@TeleOp
public class FTCLibTesting extends OpMode {
    private MecanumDrive mecanumDrive;
    private GamepadEx driverOp;
    private IMU imu;

    public void init() {
        Motor frontLeft = new Motor(hardwareMap, "frontLeftMotor");
        Motor frontRight = new Motor(hardwareMap, "frontRightMotor");
        Motor backLeft = new Motor(hardwareMap, "backLeftMotor");
        Motor backRight = new Motor(hardwareMap, "backRightMotor");

        frontRight.setInverted(true);
        backLeft.setInverted(true);
        backRight.setInverted(true);

        mecanumDrive = new MecanumDrive(frontLeft, frontRight, backLeft, backRight);

        imu = hardwareMap.get(IMU.class, "imu");
        imu.initialize(
                new IMU.Parameters(
                        new RevHubOrientationOnRobot(
                            RevHubOrientationOnRobot.LogoFacingDirection.UP,
                            RevHubOrientationOnRobot.UsbFacingDirection.RIGHT
        )));
        imu.resetYaw();

        driverOp = new GamepadEx(gamepad1);
        
        telemetry.addData("Status", "Initialized");
    }

    public void loop() {
        if (driverOp.getButton(GamepadKeys.Button.START)) {
            imu.resetYaw();
        }
        mecanumDrive.driveFieldCentric(
                driverOp.getLeftX(),
                driverOp.getLeftY(),
                driverOp.getRightX(),
                imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES)
                );
    }
}
