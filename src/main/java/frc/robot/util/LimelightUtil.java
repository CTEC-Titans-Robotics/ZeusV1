package frc.robot.util;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;

public class LimelightUtil {
    private static final NetworkTable TABLE = NetworkTableInstance.getDefault().getTable("limelight");
    private static double x;
    private static double y;
    private static double area;

    public static void pullVars() {
        x = TABLE.getEntry("tx").getDouble(0.0);
        y = TABLE.getEntry("ty").getDouble(0.0);
        area = TABLE.getEntry("ta").getDouble(0.0);
    }

    public static double getDistance(double offsetAngle, double robotCameraHeight, double goalHeight) {
        pullVars();
        // double offsetAngle = 11;
        // double robotCameraHeight = 28.25;
        // double goalHeight = 104;

        double angleToGoalRadians = (y + offsetAngle) * (Math.PI / 180.0);

        double distance;
        distance = (goalHeight - robotCameraHeight)/Math.tan(angleToGoalRadians);

        return distance;
    }
}
