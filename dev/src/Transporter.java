import org.newdawn.slick.geom.Polygon;


public class Transporter {
	
	private Polygon poly;
	
	public Transporter(int x, int y){
		setPoly(new Polygon(new float[]{
				 x, y,
				 x + 4, y,
				 x + 4, y + 48,
				 x, y + 48
      }));	
	}

	public Polygon getPoly() {
		return poly;
	}

	public void setPoly(Polygon poly) {
		this.poly = poly;
	}

}
