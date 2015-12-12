package es.ull.etsii.pai.practicafinal.physics;

import java.awt.Point;
import java.awt.geom.Line2D;
import java.io.Serializable;

import es.ull.etsii.pai.practicafinal.redvsblue.Actor;
import es.ull.etsii.pai.practicafinal.redvsblue.Entity;
import es.ull.etsii.pai.prct9.geometry.Point2D;
import es.ull.etsii.pai.prct9.geometry.Positionable;

public class Line_trace implements Serializable{
	Line2D line;
	Point2D origin;
	Actor owner;
	public Line_trace(Point2D init, Point2D end, Actor owner) {
		line = new Line2D.Float((int)init.x(), (int)init.y(), (int)end.x(), (int)end.y());
		this.owner = owner;
		this.origin = owner.getPosition();
	}
	public boolean collides(Entity actor) {
		Point2D diff = getOrigin().substract(getOwner().getPosition());
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
	public Actor getOwner() {
		return owner;
	}
	public void setOwner(Actor owner) {
		this.owner = owner;
	}
	
}
