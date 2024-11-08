package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.mecanumdrive.DefaultDrive;
import org.firstinspires.ftc.teamcode.mecanumdrive.DriveSubsystem;

@TeleOp
public class CommandMecanumDrive extends CommandOpMode {
    @Override
    public void initialize() {
        MotorEx frontLeft = new MotorEx(hardwareMap, "frontLeftMotor");
        MotorEx frontRight = new MotorEx(hardwareMap, "frontRightMotor"); 
        MotorEx backLeft = new MotorEx(hardwareMap, "backLeftMotor");
        MotorEx backRight = new MotorEx(hardwareMap, "backRightMotor");

        frontRight.setInverted(true);
        backLeft.setInverted(true);
        backRight.setInverted(true);

        DriveSubsystem driveSubsystem = new DriveSubsystem(frontLeft, frontRight, backLeft, backRight);

        GamepadEx driverOp = new GamepadEx(gamepad1);
        IMU imu = hardwareMap.get(IMU.class, "imu");
        imu.initialize(
                new IMU.Parameters(
                        new RevHubOrientationOnRobot(
                            RevHubOrientationOnRobot.LogoFacingDirection.UP,
                            RevHubOrientationOnRobot.UsbFacingDirection.RIGHT
        )));
        imu.resetYaw();

        DefaultDrive defaultDrive = new DefaultDrive(
            driveSubsystem,
            driverOp::getLeftX,
            driverOp::getLeftY, 
            driverOp::getRightX,
            () -> imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES)
        );

        driveSubsystem.setDefaultCommand(defaultDrive);

        register(driveSubsystem);
    }
}
