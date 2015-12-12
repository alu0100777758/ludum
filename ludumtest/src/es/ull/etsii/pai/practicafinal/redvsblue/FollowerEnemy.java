package es.ull.etsii.pai.practicafinal.redvsblue;

import java.awt.Point;

import es.ull.etsii.pai.prct9.geometry.Point2D;

public class FollowerEnemy extends BasicEnemy {

	private static final long serialVersionUID = -6742486697640425955L;
	
	public FollowerEnemy(Point2D position, RvsB_World map) {
		super(position, map);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void calculateNextTarget() {
		if (!playerInVision())
			super.calculateNextTarget();
		
		setTarget(getMap().getMapData().getPlayer_one().getPosition());
		calculateMinMax();
	}

	@Override
	protected boolean checkHasToSelectTarget() {
		boolean hasTo = super.checkHasToSelectTarget();
		
		if(hasTo)
			return hasTo;
		
		if (playerInVision())
			setHasToSelectTarget(true);
		return true;
	}
	
	private boolean playerInVision() {
		double x1 = getMap().getMapData().getPlayer_one().getPosition().x();
		double x2 = getPosition().x();
		double y1 = getMap().getMapData().getPlayer_one().getPosition().y();
		double y2 = getPosition().y();
		
		if (Point.distance(x1, y1, x2, y2) <= getVision()) 
			return true;
		return false;
	}
}
