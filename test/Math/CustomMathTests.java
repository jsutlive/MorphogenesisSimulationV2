package Math;

import Physics.Rigidbodies.BasicEdge;
import Physics.Rigidbodies.Edge;
import Physics.Rigidbodies.Node;
import Utilities.Geometry.Vector2f;
import Utilities.Math.CustomMath;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CustomMathTests {
    @Test
    void calculate_normal_perpendicular_horiz_line()
    {
        Vector2f a = new Vector2f(0);
        Vector2f b = new Vector2f(0, 1);
        Vector2f norm = CustomMath.normal(a, b);
        assertEquals(0, norm.y);
        assertEquals(-1, norm.x);
    }

    @Test
    void calculate_normal_flipped_perpendicular_horiz_line()
    {
        Vector2f a = new Vector2f(0);
        Vector2f b = new Vector2f(0, 1);
        Vector2f norm = CustomMath.normalFlipped(a, b);
        assertEquals(1f, norm.y);
        assertEquals(-0.0f, norm.x);
    }

    @Test
    void calculate_edge_normal_horiz_line(){
        Node a = new Node(0,0);
        Node b = new Node(0, 1);
        Edge e = new BasicEdge(a, b);
        Vector2f norm = CustomMath.normal(e);
        assertEquals(0.0f, norm.y);
        assertEquals(1.0f, norm.x);
    }

    @Test
    void calculate_edge_normal_45_degree_angle()
    {
        float root2 = (float) (Math.sqrt(2)/2);
        Node a = new Node(new Vector2f(0));
        Node b = new Node(new Vector2f(root2));
        Edge e = new BasicEdge(a,b);
        Vector2f norm = CustomMath.normal(e);
        //assertEquals(-root2, norm.y);
        assertEquals(root2, norm.x);
    }

    @Test
    void calculate_unit_vector_30_degrees(){
        int totalSegments = 12;
        int currentSegment = 1;
        Vector2f v = CustomMath.GetUnitVectorOnCircle(currentSegment, totalSegments);
        assertEquals(.5f, v.x);
        assertEquals(.8660254f, v.y);
    }

    @Test
    void calculate_inverse()
    {
        assertEquals(.2f, CustomMath.inv(-5f));
        assertEquals(Float.NEGATIVE_INFINITY, CustomMath.inv(0f));
        assertEquals(-10f, CustomMath.inv(0.1f));
    }

    @Test
    void calculate_slope()
    {
        assertEquals(.75f, CustomMath.slope(new Vector2f(0), new Vector2f(4,3)));
    }

    @Test
    void check_perpendicular_distance_simple_line()
    {
        Node n = new Node(0,1);
        Edge e = new BasicEdge(
                new Node(-1,0),
                new Node(1,0));

        float dist = CustomMath.pDistanceSq(n, e);
        assertEquals(1f, dist);
    }

    @Test
    void check_perpendicular_distance_simple_line_at_end_of_edge()
    {
        Node n = new Node(-1,1);
        Edge e = new BasicEdge(
                new Node(-1,0),
                new Node(1,0));
        float dist = CustomMath.pDistanceSq(n, e);
        assertEquals(1f, dist);
    }

    @Test
    void check_perpendicular_distance_simple_line_diagonal_to_edge()
    {
        Node n = new Node(-4,4);
        Edge e = new BasicEdge(
                new Node(-1,0),
                new Node(1,0));
        float dist = CustomMath.pDistanceSq(n, e);
        assertEquals(16f, dist);
    }
}
