package org.firstinspires.ftc.teamcode.mecanumdrive;

import com.arcrobotics.ftclib.command.CommandBase;
import java.util.function.DoubleSupplier;

public class DefaultDrive extends CommandBase {
    private final DriveSubsystem drive;
    private final DoubleSupplier xSupplier;
    private final DoubleSupplier ySupplier;
    private final DoubleSupplier rotationSupplier;
    private final DoubleSupplier headingSupplier;

    public DefaultDrive(DriveSubsystem drive, 
                       DoubleSupplier xSupplier,
                       DoubleSupplier ySupplier,
                       DoubleSupplier rotationSupplier,
                       DoubleSupplier headingSupplier) {
        this.drive = drive;
        this.xSupplier = xSupplier;
        this.ySupplier = ySupplier;
        this.rotationSupplier = rotationSupplier;
        this.headingSupplier = headingSupplier;
        addRequirements(drive);
    }

    @Override
    public void execute() {
        drive.driveFieldCentric(
            xSupplier.getAsDouble(),
            ySupplier.getAsDouble(),
            rotationSupplier.getAsDouble(),
            headingSupplier.getAsDouble()
        );
    }
} 