
public class Main {
	public static void main(String[] args) {
		
		//composition
		LineSeg l = new LineSeg(new Point(2,2), new Point(1,1));
		Circle c = new Circle(new Point(7.1f, 0.4f), 3.3f);
		Rectangle r = new Rectangle(1,3,3,2);
		CompShape s = new CompShape();
		s.addShape(l);
		s.addShape(c);
		s.addShape(r);
		
		CompShape s2 = new CompShape();
		s.removeShape(r);
		s2.addShape(r);
		s2.addShape(s);
		
		//intersect
		System.out.println("s2 intersect l: " + s2.intersect(l));
        System.out.println("s2 intersect c: " + s2.intersect(c));
        System.out.println("s2 intersect r: " + s2.intersect(r));
        System.out.println("s2 intersect s: " + s2.intersect(s));
		
		//access components
		CompShapeIterator it = s2.getIterator();
		it.first();
		while(!it.isDone())
			{
				AbstractShape shape = it.getCurrentShape();
				System.out.println("Current shape: " + shape.getClass().getSimpleName());
				it.next();
			}
		
		//singleton Drawing
		Drawing dwg = Drawing.getInstance();
		dwg.setShape(s2);
		AbstractShape ds = dwg.getShape();
		System.out.println("Shape in drawing: " + ds.getClass().getSimpleName());
	}

}
