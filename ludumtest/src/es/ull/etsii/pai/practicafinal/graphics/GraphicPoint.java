package es.ull.etsii.pai.practicafinal.graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class GraphicPoint {
	private Point point;
	private static Color color;
	private static int radius;
	
	public GraphicPoint(Point point, Color color, int radius) {
		this.point = point;
		GraphicPoint.color = color;
		GraphicPoint.radius = radius;
	}
	
	public GraphicPoint(Point p) {
		this.point = p;
	}
	public void drawPoint(Graphics g) {
		g.setColor(color);

		g.fillOval((int)point.getX() - radius, (int)point.getY() - radius, radius * 2, radius * 2);
	}
}
