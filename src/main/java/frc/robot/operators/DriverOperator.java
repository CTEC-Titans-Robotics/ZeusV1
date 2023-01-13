package frc.robot.operators;

import com.ctre.phoenix.motorcontrol.can.BaseTalon;

import frc.robot.motors.AbstractMotorGroup;
import frc.robot.motors.tank.LeftSideGroup;
import frc.robot.motors.tank.RightSideGroup;

public class DriverOperator extends AbstractOperator {
    //private final AbstractMotorGroup backLeft = new BackLeftSwerveGroup();
    //private final AbstractMotorGroup backRight = new BackRightSwerveGroup();
    //private final AbstractMotorGroup frontLeft = new FrontLeftSwerveGroup();
    //private final AbstractMotorGroup frontRight = new FrontRightSwerveGroup();

    private final LeftSideGroup leftSide = new LeftSideGroup();
    private final RightSideGroup rightSide = new RightSideGroup();

    public static final double speedModifier = 0.7;
    public static final double controllerDeadzone = 0.1;

    public DriverOperator() {
        setMotorGroups(new AbstractMotorGroup[] {
                //backLeft,
                //backRight,
                //frontLeft,
                //frontRight
                leftSide,
                rightSide
        });

        setController(0);
        setupOpThread("Driver", this::tick);
    }

    public void init() {
        
    }

    @Override
    public void tick() {
        // TODO: Wyatt, add math for swerve here, I will clean it up after.

        // Example for Tank
        // Left
        if(controller.getLeftY() > controllerDeadzone || controller.getLeftY() < -controllerDeadzone) {
            leftSide.drive(controller.getLeftY() * speedModifier);
        } else {
            leftSide.stop();
        }

        // Right
        if(controller.getRightY() > controllerDeadzone || controller.getRightY() < -controllerDeadzone) {
            rightSide.drive(controller.getRightY() * speedModifier);
        } else {
            rightSide.stop();
        }
    }
}
