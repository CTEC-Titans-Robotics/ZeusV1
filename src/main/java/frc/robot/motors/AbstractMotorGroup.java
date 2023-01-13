package frc.robot.motors;

import com.ctre.phoenix.motorcontrol.can.BaseTalon;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractMotorGroup {
    protected final int[] motorIds;
    public final Map<Integer, BaseTalon> motors = new HashMap<>();

    public AbstractMotorGroup(int[] motorIds, String name) {
        this.motorIds = motorIds;

        initMotors();
        if(!checkMotors()) System.out.println("WARNING! One or more motor checks failed, proceed with caution.");
        reverseMotors();
        Thread thread = new Thread(this::watchMotors);
        thread.setName(name + " Motor Group Watchdog");
        thread.start();
    }

    public abstract void reverseMotors();

    public boolean checkMotors() {
        for(Map.Entry<Integer, BaseTalon> motor : motors.entrySet()) {
            checkTemp(motor.getValue());
        }
        return true;
    }

    public void checkTemp(BaseTalon motor) {
        if(motor.getTemperature() > 60) {
            System.out.println("MOTOR " + motor.getDeviceID() + " IS OVERHEATED! COOL IT DOWN!");
        }
    }

    public void initMotors() {
        for(int motorId : motorIds) {
            motors.put(motorId, new TalonFX(motorId));
        }
    }

    public void watchMotors() {
        while(true) {
            for (Map.Entry<Integer, BaseTalon> motor : motors.entrySet()) {
                checkTemp(motor.getValue());
            }
        }
    }
}
