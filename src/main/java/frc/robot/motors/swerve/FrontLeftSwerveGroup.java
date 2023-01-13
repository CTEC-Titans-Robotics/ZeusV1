package frc.robot.motors.swerve;

import frc.robot.motors.AbstractMotorGroup;

public class FrontLeftSwerveGroup extends AbstractMotorGroup {
    public FrontLeftSwerveGroup() {
        super(new int[]{
                4,
                5,
                6,
                7,
        }, "Front Left Swerve");
    }

    @Override
    public void reverseMotors() {
        // motors.get(5).setInverted(true);
        // motors.get(7).setInverted(true);
    }
}
