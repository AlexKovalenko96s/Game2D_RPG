package ua.kas.main;

//speed-meter

public class Vector {

	private double x, y, angle, radius;
	static byte Cartesian = 0, Polar = 1;

	public Vector() {
		this.angle = 0;
		this.radius = 0;
		this.y = 0;
		this.x = 0;

	}

	public Vector(double scalar1, double scalar2, byte coordianteSystem) {
		this.angle = 0;
		this.y = 0;
		this.x = 0;
		this.radius = 0;
		if (coordianteSystem == Cartesian) {
			this.x = scalar1;
			this.y = scalar2;
			refreshPolar();
		} else {
			this.radius = scalar1;
			this.angle = scalar2;
			refreshCartesian();
		}

	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
		refreshPolar();
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
		refreshPolar();
	}

	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
		refreshCartesian();
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
		refreshCartesian();

	}

	private void refreshPolar() {
		radius = Math.sqrt(x * x + y * y);
		angle = Math.atan2(y, x);
	}

	private void refreshCartesian() {
		x = radius * Math.cos(angle);
		y = radius * Math.sin(angle);
	}

	static double normalizeAngle(double angle) {
		while (angle >= 2 * Math.PI) {
			angle -= 2 * Math.PI;
		}
		return angle;
	}

}
