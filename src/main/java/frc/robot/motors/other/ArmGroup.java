package frc.robot.motors.other;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.BaseTalon;
import com.revrobotics.CANSparkMax;
import frc.robot.motors.AbstractMotorGroup;

import java.util.Map;

public class ArmGroup extends AbstractMotorGroup {
    public ArmGroup() {
        super(new int[] {
                8,
                13,
                3
        }, "Arm");
    }

    @Override
    public void reverseMotors() {
        motors.get(13).setInverted(true);
    }

    public void extend(double percent) {
        motors.get(3).set(ControlMode.PercentOutput, 0.35);
        motors.get(13).set(ControlMode.PercentOutput, 0.35);
        motors.get(8).set(ControlMode.PercentOutput, -percent);
    }

    public void retract() {
        motors.get(3).set(ControlMode.PercentOutput, -0.35);
        motors.get(13).set(ControlMode.PercentOutput, -0.35);
    }

    public double getSensorPos() {
        return motors.get(3).getSelectedSensorPosition();
    }

    public void stop() {
        for(Map.Entry<Integer, BaseTalon> motor : motors.entrySet()) {
            motor.getValue().set(ControlMode.PercentOutput, 0);
        }
    }
}
