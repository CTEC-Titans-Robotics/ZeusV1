package frc.robot.operators;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Robot;
import frc.robot.motors.AbstractMotorGroup;

public abstract class AbstractOperator {
    public Thread opThread;
    public AbstractMotorGroup[] motorGroups;
    public XboxController controller;

    public void setMotorGroups(AbstractMotorGroup[] motorGroups) {
        this.motorGroups = motorGroups;
    }

    public void setupOpThread(String name, Runnable runnable) {
        if(runnable != null) {
            opThread = new Thread(() -> {
                while(true) {
                    runnable.run();
                }
            });
            opThread.setName(name + " Operator Thread");
            opThread.start();
        }
    }

    public void setController(int port) {
        controller = new XboxController(port);
    }

    public abstract void tick();
}
