package es.ull.etsii.pai.practicafinal.redvsblue;

import java.awt.Point;
import java.util.Random;

import es.ull.etsii.pai.practicafinal.physics.Physical_passive;
import es.ull.etsii.pai.prct9.geometry.Point2D;

public class BasicEnemy extends Player {
	private boolean hasToSelectTarget = true;
	private Point2D target;
	private int vision = 250;
	private Integer xMax;
	private Integer yMax;
	private Integer xMin;
	private Integer yMin;
	private Integer waitTime = 30; 	// Ticks a esperar entre objetivo y objetivo.
	private Integer waitLeft;
	
	private static final long serialVersionUID = 637757710869339130L;
	private static final Random ENGINE = new Random();
	
	public BasicEnemy(Point2D position, RvsB_World map) {
		super(position, map);
		// TODO Auto-generated constructor stub
	}

	private void calculateNextTarget() {
		int x = ENGINE.nextInt(vision * 2) - vision;
		int y = ENGINE.nextInt(vision * 2) - vision;
		
		target = new Point2D(getPosition().x() + x, getPosition().y() + y);
		
		if (x > 0) {
			xMin = null;
			xMax = (int) target.x();
		}
		else{
			xMax = null;
			xMin = (int) target.x();
		}
			
		if (y > 0) {
			yMin = null;
			yMax = (int) target.y();
		}
		else {
			yMax = null;
			yMin = (int) target.y();
		}
		
	}
	
	private void calculateSpeed() {
		double difX = target.x() - getPosition().x();
		double difY = target.y() - getPosition().y();
		double angle = Math.atan2(difX, difY);
		
		getSpeed().setX(Math.cos(angle - Math.toRadians(90)) * stats.getSPEED());
		getSpeed().setY(Math.sin(angle - Math.toRadians(90)) * stats.getSPEED());
	}
	
	@Override
	public boolean repair_collision(Physical_passive actor) {
		boolean collided =  super.repair_collision(actor);
		
		hasToSelectTarget = !collided;
		
		return collided;
	}
	private boolean checkHasToSelectTarget() {
		if (hasToSelectTarget)
			return hasToSelectTarget;
		if (xMin != null && xMin > getPosition().x()) {
			hasToSelectTarget = true;
		}
		else if (xMax != null && xMax < getPosition().x()) {
			hasToSelectTarget = true;
		}
		else if (yMin != null && yMin > getPosition().y()) {
			hasToSelectTarget = true;
		}
		else if (yMax != null && yMax < getPosition().y()) {
			hasToSelectTarget = true;
		}
		
		return hasToSelectTarget;
		
	}
	private void startWait() {
		waitLeft = waitTime;
	}
	@Override
	public boolean updatePos(Physical_passive map) {		
		if (!map.getPhysicalRectangle().contains(getPhysicalShape())) 
			return false;
		
		if (!isDead()) {
			
			if (checkHasToSelectTarget()) {
				startWait();
				calculateNextTarget();
				calculateSpeed();
				
				hasToSelectTarget = false;
			}
			
			if (waitLeft > 0) {
				waitLeft--;
			}
			else if (waitLeft == 0) {
				updateToraxRotation(new Point((int)target.x(), (int)target.y()));
				updateLegsRotation(target);
				waitLeft--;
			}
			else {
				getLegs().setLocation(new Point((int) getPosition().x(), (int) getPosition().y()));
				setPosition(getPosition().add(getSpeed().add(getPush())));
				getTorax().setLocation(new Point((int) getPosition().x(), (int) getPosition().y()));
			}
			
				
		}
		return true;
	}

	
}
