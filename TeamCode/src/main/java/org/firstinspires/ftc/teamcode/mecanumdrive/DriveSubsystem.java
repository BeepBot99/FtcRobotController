package org.firstinspires.ftc.teamcode.mecanumdrive;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;

public class DriveSubsystem extends SubsystemBase {

    private final MecanumDrive drive;

    public DriveSubsystem(MotorEx frontLeft, MotorEx frontRight, MotorEx backLeft, MotorEx backRight) {
        drive = new MecanumDrive(frontLeft, frontRight, backLeft, backRight);
    }

    public void driveFieldCentric(double x, double y, double rotation, double heading) {
        drive.driveFieldCentric(x, y, rotation, heading);
    }

    public void driveRobotCentric(double x, double y, double rotation) {
        drive.driveRobotCentric(x, y, rotation);
    }   
}

