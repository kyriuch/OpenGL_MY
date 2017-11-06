package maths;

public class Vector3 {
    private float x;
    private float y;
    private float z;

    public static Vector3 One() {
        return new Vector3(1f, 1f, 1f);
    }

    public static Vector3 Zero() {
        return new Vector3(0f, 0f, 0f);
    }

    public Vector3(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }
}
