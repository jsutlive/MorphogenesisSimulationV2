package Utilities.Geometry;

import Utilities.Math.CustomMath;

public class Vector2f
{
    public float x;
    public float y;

    public Vector2f(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector2f(float x) {
        this.x = x;
        this.y = x;
    }

    public Vector2f() {
        this.x = 0;
        this.y = 0;
    }

    public void add(Vector2f vec)
    {
        x += vec.x;
        y += vec.y;
    }

    public void sub(Vector2f vec)
    {
        x -= vec.x;
        y -= vec.y;
    }

    public void mul(int i)
    {
        x *= i;
        y *= i;
    }

    public void mul(float f)
    {
        x *= f;
        y *= f;
    }

    public float cross(Vector2f vec)
    {
        return (x * vec.y) - (vec.x * y);
    }

    public float dot(Vector2f vec)
    {
        return (x * vec.x) + (y * vec.y);
    }

    public Vector2i asInt()
    {
        int xi = (int)x;
        int yi = (int)y;
        return new Vector2i(xi,yi);
    }

    public float greater()
    {
        return Math.max(x,y);
    }

    public float lesser()
    {
        return Math.min(x,y);
    }

    public static float dist(Vector2f a, Vector2f b){
        return (float)Math.hypot(b.x -a.x, b.y - a.y);
    }

    public static Vector2f center(Vector2f a, Vector2f b){
        float xAvg = CustomMath.avg(a.x, b.x);
        float yAvg = CustomMath.avg(a.y, b.y);
        return new Vector2f(xAvg, yAvg);
    }

}
