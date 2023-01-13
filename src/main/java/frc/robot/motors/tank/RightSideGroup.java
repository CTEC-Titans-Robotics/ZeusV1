package frc.robot.motors.tank;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.BaseTalon;
import com.revrobotics.CANSparkMax;
import frc.robot.motors.AbstractMotorGroup;

import java.util.Map;

public class RightSideGroup extends AbstractMotorGroup {
    public RightSideGroup() {
        super(new int[] {
                0,
                15
        }, "Tank Right");
    }

    @Override
    public void reverseMotors() {
        motors.get(0).setInverted(true);
        motors.get(15).setInverted(true);
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
