package es.ull.etsii.pai.practicafinal.physics;

import java.awt.Point;
import java.awt.geom.Line2D;

import es.ull.etsii.pai.practicafinal.redvsblue.Entity;
import es.ull.etsii.pai.prct9.geometry.Point2D;
import es.ull.etsii.pai.prct9.geometry.Positionable;

public class Line_trace {
	Line2D line;
	Point2D origin;
	Positionable owner;
	public Line_trace(Point init, Point end, Positionable owner) {
		line = new Line2D.Float(init.x, init.y, end.x, end.y);
		this.owner = owner;
		this.origin = owner.getPos();
	}
	public boolean collides(Entity actor) {
		Point2D diff = getOrigin().substract(getOwner().getPos());
		java.awt.geom.Point2D init = getLine().getP1();
		java.awt.geom.Point2D end = getLine().getP2();
		Line2D line = new Line2D.Float((int)(init.getX() + diff.x()),(int)( init.getY() + diff.y()),(int)(end.getX() + diff.x()), (int)(end.getY() + diff.y()));
		return line.intersects(actor.getShape().getBounds2D());
	}
	public Line2D getLine() {
		return line;
	}
	public void setLine(Line2D line) {
		this.line = line;
	}
	public Point2D getOrigin() {
		return origin;
	}
	public void setOrigin(Point2D origin) {
		this.origin = origin;
	}
	public Positionable getOwner() {
		return owner;
	}
	public void setOwner(Positionable owner) {
		this.owner = owner;
	}
	
}
