package es.ull.etsii.pai.practicafinal.redvsblue;

import java.awt.Point;
import java.util.Random;

import es.ull.etsii.pai.practicafinal.physics.Physical_passive;
import es.ull.etsii.pai.prct9.geometry.Point2D;

public class BasicEnemy extends Player {
	private boolean hasToSelectTarget = true;
	private Point2D target;
	private int vision = 100;
	
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
	}

	@Override
	public boolean updatePos(Physical_passive map) {
		if (!map.getPhysicalRectangle().contains(getPhysicalShape()))
			return false;
		if (!isDead()) {
			
			if (hasToSelectTarget)
				calculateNextTarget();
			getLegs().setLocation(new Point((int) getPosition().x(), (int) getPosition().y()));
			setPosition(getPosition().add(getSpeed().add(getPush())));
			getTorax().setLocation(new Point((int) getPosition().x(), (int) getPosition().y()));

		}
		return true;
	}

	
}
