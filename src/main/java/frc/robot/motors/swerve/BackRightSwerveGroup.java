package frc.robot.motors.swerve;

import frc.robot.motors.AbstractMotorGroup;

public class BackRightSwerveGroup extends AbstractMotorGroup {
    public BackRightSwerveGroup() {
        super(new int[]{
                8,
                9,
                10,
                11,
        }, "Back Right Swerve");
    }

    @Override
    public void reverseMotors() {
        // motors.get(9).setInverted(true);
        // motors.get(11).setInverted(true);
    }
}
