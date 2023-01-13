package frc.robot.motors.swerve;

import frc.robot.motors.AbstractMotorGroup;

public class BackLeftSwerveGroup extends AbstractMotorGroup {
    public BackLeftSwerveGroup() {
        super(new int[]{
                0,
                1,
                2,
                3,
        }, "Back Left Swerve");
    }

    @Override
    public void reverseMotors() {
        // motors.get(1).setInverted(true);
        // motors.get(2).setInverted(true);
    }
}
