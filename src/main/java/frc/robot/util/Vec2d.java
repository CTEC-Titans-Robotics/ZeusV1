package frc.robot.util;

public class Vec2d {
    private final double x;
    private final double y;

    public Vec2d(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Vec2d add(Vec2d vec) {
        return new Vec2d(vec.x + this.x, vec.y + this.y);
    }

    public Vec2d sub(Vec2d vec) {
        return new Vec2d(vec.x - this.x, vec.y - this.y);
    }
}
