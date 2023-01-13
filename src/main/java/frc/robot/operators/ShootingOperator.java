package frc.robot.operators;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.BaseTalon;
import com.revrobotics.CANSparkMax;
import frc.robot.motors.AbstractMotorGroup;
import frc.robot.motors.other.ArmGroup;
import frc.robot.motors.other.IntakeGroup;
import frc.robot.motors.other.TipGroup;

import java.util.Map;

import org.opencv.ml.EM;

public class ShootingOperator extends AbstractOperator {
    private final IntakeGroup intake = new IntakeGroup();
    private final TipGroup tip = new TipGroup();
    private final Thread armThread = new Thread(this::tickArm);

    private final ArmGroup arm = new ArmGroup();
    private boolean isEmergencyStopped = false;

    public ShootingOperator() {
        setMotorGroups(new AbstractMotorGroup[] {
                intake,
                tip,
                arm
        });

        setController(0);
        setupOpThread("Shooter", this::tick);
    }

    @Override
    public void tick() {
        if(armThread.getState() == Thread.State.NEW) {
            armThread.setName("Arm");
            armThread.start();
        }

        if(controller.getRightBumper()) {
            intake.spin(0.5);
        } else if(controller.getAButton()) {
            intake.spin(-0.5);
        } else if(controller.getLeftBumper()) {
            intake.spin(0.5);
            tip.shoot();
        } else {
            intake.stop();
            tip.stop();
        }

        // EMERGENCY STOP
        if(controller.getAButton() && controller.getBButton() && controller.getXButton() && controller.getYButton()) {
            isEmergencyStopped = true;
        }
    }

    private void tickArm() {
        while (!isEmergencyStopped) {
            if (controller.getLeftBumper() || controller.getAButton()) {
                while (arm.getSensorPos() * 0.17578152 < 150) {
                    arm.extend(0.35);
                    if(controller.getAButton()) {
                        arm.extend(-0.35);
                    }
                }
            }
            if (arm.getSensorPos() * 0.17578152 > 145) {
                arm.stop();
                if(controller.getLeftBumper()) {
                    arm.motors.get(8).set(ControlMode.PercentOutput, 0.35);
                }

                if(controller.getAButton()) {
                    arm.motors.get(8).set(ControlMode.PercentOutput, -0.35);
                }
            }
            if (!controller.getLeftBumper() && !controller.getAButton() && (arm.getSensorPos() * 0.17578152 > 25 || Math.abs(arm.getSensorPos()) * 0.17578152 > 25)) {
                while (arm.getSensorPos() * 0.17578152 > 10) {
                    arm.retract();
                }
            } else if (arm.getSensorPos() * 0.17578152 < 0) {
                arm.motors.get(3).set(ControlMode.PercentOutput, -0.01);
                arm.motors.get(13).set(ControlMode.PercentOutput, -0.01);
            }
        }
    }

    public void init() {
        arm.motors.get(3).configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, 0, 0);
    }
}
