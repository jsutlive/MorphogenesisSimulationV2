package Utilities;

import Physics.Rigidbodies.Node;
import Utilities.Geometry.Boundary;
import Utilities.Geometry.Vector2f;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BoundaryTests {
    @Test
    void point_in_circle_returns_accurate_value_inside() {
        Vector2f center = new Vector2f(0);
        float radius = 5.0f;
        Vector2f test = new Vector2f(3);
        assertTrue(Boundary.ContainsPosition(test, center, radius));
    }

    @Test
    void point_in_circle_returns_accurate_value_outside(){
        Vector2f center = new Vector2f(0);
        float radius = 5.0f;
        Vector2f test = new Vector2f(10);
        assertFalse(Boundary.ContainsPosition(test, center, radius));
    }

    @Test
    void point_in_circle_returns_accurate_value_on_circle(){
        Vector2f center = new Vector2f(0);
        float radius = 5.0f;
        Vector2f test = new Vector2f(5f,0f);
        assertTrue(Boundary.ContainsPosition(test, center, radius));
    }

}