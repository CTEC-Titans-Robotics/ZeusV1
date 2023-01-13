package frc.robot.motors.swerve;

import frc.robot.motors.AbstractMotorGroup;

public class FrontRightSwerveGroup extends AbstractMotorGroup {
    public FrontRightSwerveGroup() {
        super(new int[]{
                12,
                13,
                14,
                15,
        }, "Front Right Swerve");
    }

    @Override
    public void reverseMotors() {
        // motors.get(13).setInverted(true);
        // motors.get(15).setInverted(true);
    }
}
