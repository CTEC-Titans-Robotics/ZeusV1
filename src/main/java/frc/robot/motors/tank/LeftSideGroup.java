package frc.robot.motors.tank;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.BaseTalon;
import com.revrobotics.CANSparkMax;
import frc.robot.motors.AbstractMotorGroup;

import java.util.Map;

public class LeftSideGroup extends AbstractMotorGroup {
    public LeftSideGroup() {
        super(new int[] {
                2,
                4
        }, "Tank Right");
    }

    @Override
    public void reverseMotors() {
    }

    public void drive(double percent) {
        for(Map.Entry<Integer, BaseTalon> motor : motors.entrySet()) {
            motor.getValue().set(ControlMode.PercentOutput, percent);
        }
    }

    public void stop() {
        for(Map.Entry<Integer, BaseTalon> motor : motors.entrySet()) {
            motor.getValue().set(ControlMode.PercentOutput, 0);
        }
    }
}
