package frc.robot.motors.other;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.BaseTalon;
import frc.robot.motors.AbstractMotorGroup;

import java.util.Map;

public class IntakeGroup extends AbstractMotorGroup {
    public IntakeGroup() {
        super(new int[] {
                7,
                6
        }, "Intake");
    }

    @Override
    public void reverseMotors() {
        motors.get(7).setInverted(true);
    }

    public void spin(double percent) {
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
