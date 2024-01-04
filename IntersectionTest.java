import org.junit.*;
import static org.junit.Assert.*;

public class IntersectionTest {

    // Point intersection test cases

    @Test
    public void pointIntersectsPoint() {
        Point p1 = new Point(5.0f, 5.0f);
        Point p2 = new Point(5.0f, 5.0f);
        assertTrue(p1.intersect(p2));
    
        Point p3 = new Point(1.1f, 1.1f);
        Point p4 = new Point(1.7f, 1.1f);
        assertFalse(p3.intersect(p4));
    }
    
    @Test
    public void pointIntersectsLineSeg() {
        Point p1 = new Point(2.0f, 2.0f);
        LineSeg l1 = new LineSeg(new Point(2.0f, 0.0f), new Point(2.0f, 4.0f));
        assertTrue(p1.intersect(l1));
    
        Point p2 = new Point(2.5f, 2.5f);
        LineSeg l2 = new LineSeg(new Point(0, 0), new Point(2.5f, 3.5f));
        assertFalse(p2.intersect(l2));
    }    

    @Test
    public void pointIntersectsRectangle() {
        Point p1 = new Point(2.0f, 2.0f);
        Rectangle r1 = new Rectangle(1.0f, 4.0f, 4.0f, 1.0f);
        assertTrue(p1.intersect(r1));
    
        Point p2 = new Point(5.5f, 5.5f);
        Rectangle r2 = new Rectangle(0.0f, 4.0f, 4.0f, 0.0f);
        assertFalse(p2.intersect(r2));
    }
    
    @Test
    public void pointIntersectsCircle() {
        Point p1 = new Point(2.0f, 2.0f);
        Circle c1 = new Circle(new Point(2.5f, 2.5f), 1);
        assertTrue(p1.intersect(c1));
    
        Point p2 = new Point(5.0f, 5.0f);
        Circle c2 = new Circle(new Point(3.0f, 3.0f), 2);
        assertFalse(p2.intersect(c2));
    }

    // LineSeg intersection test cases

    @Test
    public void lineSegIntersectsPoint() {
        LineSeg l1 = new LineSeg(new Point(1.0f, 1.0f), new Point(6.0f, 1.0f));
        Point p1 = new Point(6.0f, 1.0f);
        assertTrue(l1.intersect(p1));
    
        LineSeg l2 = new LineSeg(new Point(1.0f, 1.0f), new Point(5.0f, 5.0f));
        Point p2 = new Point(3.5f, 3.0f);
        assertFalse(l2.intersect(p2));
    }
    
    @Test
    public void lineSegIntersectsLineSeg() {
        LineSeg l1 = new LineSeg(new Point(1.0f, 1.0f), new Point(5.0f, 5.0f));
        LineSeg l2 = new LineSeg(new Point(1.0f, 5.0f), new Point(5.0f, 1.0f));
        assertTrue(l1.intersect(l2));

        LineSeg l3 = new LineSeg(new Point(1.0f, 1.0f), new Point(7.0f, 1.0f));
        LineSeg l4 = new LineSeg(new Point(1.0f, 1.3f), new Point(7.0f, 1.3f));
        assertFalse(l3.intersect(l4));

        LineSeg l5 = new LineSeg(new Point(1.0f, 1.0f), new Point(1.0f, 4.0f));
        LineSeg l6 = new LineSeg(new Point(1.0f, 1.0f), new Point(4.0f, 1.0f));
        assertTrue(l5.intersect(l6));
    }
    
    @Test
    public void lineSegIntersectsRectangle() {
        LineSeg l1 = new LineSeg(new Point(1.0f, 1.0f), new Point(5.1f, 5.1f));
        Rectangle r1 = new Rectangle(2.0f, 8.0f, 8.0f, 2.0f);
        assertTrue(l1.intersect(r1));

        LineSeg l2 = new LineSeg(new Point(1.0f, 5.7f), new Point(7.0f, 5.7f));
        Rectangle r2 = new Rectangle(1.0f, 7.0f, 5.0f, 1.0f);
        assertFalse(l2.intersect(r2));

        LineSeg l3 = new LineSeg(new Point(8.0f, 5.0f), new Point(10.0f, 5.0f));
        Rectangle r3 = new Rectangle(2.0f, 8.0f, 8.0f, 2.0f);
        assertTrue(l3.intersect(r3));

    }

    @Test
    public void lineSegIntersectsCircle() {
        LineSeg l1 = new LineSeg(new Point(1.5f, 3.0f), new Point(4.5f, 3.0f));
        Circle c1 = new Circle(new Point(3.0f, 3.0f), 1.0f);
        assertTrue(l1.intersect(c1));

        LineSeg l2 = new LineSeg(new Point(1.0f, 5.0f), new Point(5.0f, 5.0f));
        Circle c2 = new Circle(new Point(2.5f, 2.5f), 2.0f);
        assertFalse(l2.intersect(c2));

        LineSeg l3 = new LineSeg(new Point(4.5f, 2.5f), new Point(7.0f, 2.5f));
        Circle c3 = new Circle(new Point(2.5f, 2.5f), 2.0f);
        assertTrue(l3.intersect(c3));
    }

    // Rectangle intersection test cases

    @Test
    public void rectangleIntersectsPoint() {
        Rectangle r1 = new Rectangle(0.0f, 5.0f, 5.0f, 0.0f);
        Point p1 = new Point(4.0f, 5.0f);
        assertTrue(r1.intersect(p1));

        Rectangle r2 = new Rectangle(0.0f, 5.0f, 5.0f, 0.0f);
        Point p2 = new Point(5.6f, 1.3f);
        assertFalse(r2.intersect(p2));
    }

    @Test
    public void rectangleIntersectsLineSeg() {
        Rectangle r1 = new Rectangle(2.0f, 7.0f, 5.0f, 0.0f);
        LineSeg l1 = new LineSeg(new Point(1.0f, 5.0f), new Point(8.0f, 5.0f));
        assertTrue(r1.intersect(l1));

        Rectangle r2 = new Rectangle(2.0f, 6.0f, 4.0f, 2.0f);
        LineSeg l2 = new LineSeg(new Point(5.0f, 7.0f), new Point(7.0f, 3.0f));
        assertFalse(r2.intersect(l2));
    }

    @Test
    public void rectangleIntersectsRectangle() {
        Rectangle r1 = new Rectangle(0.0f, 4.0f, 4.0f, 0);
        Rectangle r2 = new Rectangle(2.0f, 6.0f, 6.0f, 2.0f);
        assertTrue(r1.intersect(r2));

        Rectangle r3 = new Rectangle(0.0f, 4.0f, 2.0f, 0.0f);
        Rectangle r4 = new Rectangle(4.0f, 8.0f, 2.0f, 0.0f);
        assertFalse(r3.intersect(r4));

        Rectangle r5 = new Rectangle(1.0f, 6.0f, 5.0f, 1.0f);
        Rectangle r6 = new Rectangle(2.0f, 5.0f, 4.0f, 2.0f);
        assertTrue(r5.intersect(r6));
    }

    @Test
    public void RectangleIntersectsCircle() {
        Rectangle r1 = new Rectangle(2.0f, 5.0f, 2.0f, 0.0f);
        Circle c1 = new Circle(new Point(3.0f, 3.0f), 2.0f);
        assertTrue(r1.intersect(c1));

        Rectangle r2 = new Rectangle(2.0f, 4.0f, 4.0f, 2.0f);
        Circle c2 = new Circle(new Point(3.0f, 5.0f), 1.0f);
        assertFalse(r2.intersect(c2));
    }

    // Circle intersection test cases

    @Test
    public void CircleIntersectsPoint() {
        Circle c1 = new Circle(new Point(3.0f, 3.0f), 2.0f);
        Point p1 = new Point(5.0f, 3.0f);
        assertTrue(c1.intersect(p1));

        Circle c2 = new Circle(new Point(2.0f, 2.0f), 2.0f);
        Point p2 = new Point(0.0f,0.0f);
        assertFalse(c2.intersect(p2));
    }

    @Test
    public void CircleIntersectsLineSeg() {
        Circle c1 = new Circle(new Point(3.0f, 3.0f), 2.0f);
        LineSeg l1 = new LineSeg(new Point(5.0f, 6.0f), new Point(5.0f, 0.0f));
        assertTrue(c1.intersect(l1));

        Circle c2 = new Circle(new Point(2.0f, 2.0f), 2.0f);
        LineSeg l2 = new LineSeg(new Point(4.5f, 1.0f), new Point(6.0f, 1.0f));
        assertFalse(c2.intersect(l2));
    }

    @Test
    public void CircleIntersectsRectangle() {
        Rectangle r1 = new Rectangle(2.0f, 8.0f, 8.0f, 2.0f);
        Circle c1 = new Circle(new Point(5.0f, 5.0f), 3.0f);
        assertTrue(c1.intersect(r1));

        Rectangle r2 = new Rectangle(0.0f, 4.0f, 2.0f, 0.0f);
        Circle c2 = new Circle(new Point(5.0f, 3.0f), 1.0f);
        assertFalse(c2.intersect(r2));
    }

    @Test
    public void CircleIntersectsCircle() {
        Circle c1 = new Circle(new Point(2.0f, 2.0f), 2.0f);
        Circle c2 = new Circle(new Point(5.5f, 2.0f), 2.0f);
        assertTrue(c1.intersect(c2));

        Circle c3 = new Circle(new Point(2.0f, 4.0f), 2.0f);
        Circle c4 = new Circle(new Point(6.0f, 4.0f), 2.0f);
        assertFalse(c3.intersect(c4));

        Circle c5 = new Circle(new Point(4.25f, 4.25f), 4.0f);
        Circle c6 = new Circle(new Point(3.75f, 3.75f), 3.0f);
        assertTrue(c5.intersect(c6));
    }

}