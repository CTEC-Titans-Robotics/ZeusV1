package frc.robot.motors.other;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.BaseTalon;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkMax;
import frc.robot.motors.AbstractMotorGroup;
import frc.robot.util.LimelightUtil;

import java.util.Map;

public class TipGroup extends AbstractMotorGroup {
    public TipGroup() {
        super(new int[] {
                10,
                9
        }, "Tip");
    }

    @Override
    public void reverseMotors() {
    }

    @Override
    public void initMotors() {
        for(int motorId : motorIds) {
            motors.put(motorId, new TalonSRX(motorId));
        }
    }

    public void spinVarying(double motorSpeed1, double motorSpeed2) {
        motors.get(10).set(ControlMode.PercentOutput, motorSpeed1);
        motors.get(9).set(ControlMode.PercentOutput, motorSpeed2);
    }

    public void stop() {
        for(Map.Entry<Integer, BaseTalon> motor : motors.entrySet()) {
            motor.getValue().set(ControlMode.PercentOutput, 0);
        }
    }

    public void shoot() {
        double distance = (LimelightUtil.getDistance(11, 28.25, 104) + 6) / 12;
        if (distance > 300D / 12D){
            distance = 10;
        } else if (distance < -1){
            distance = 10;
        }

        // FIRE IN THE HOLE
        spinVarying(0.0149 * distance + 0.3428, 0.0027 * distance + 0.0605);
    }
}
