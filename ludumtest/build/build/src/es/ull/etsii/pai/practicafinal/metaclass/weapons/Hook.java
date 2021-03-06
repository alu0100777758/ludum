package es.ull.etsii.pai.practicafinal.metaclass.weapons;
/**
 * Progamacion de aplicaciones interactivas.
 * Universidad de La Laguna.
 * 
 * @author Sabato Ceruso sab7093@gmail.com
 * @author Javier Martin Hernandez alu0100777758@ull.edu.es
 *
 */

import es.ull.etsii.pai.practicafinal.metaclass.Weapon;
import es.ull.etsii.pai.practicafinal.metaclass.weapons.bullets.Rope_bullet;
import es.ull.etsii.pai.practicafinal.redvsblue.Bullet;
import es.ull.etsii.pai.practicafinal.redvsblue.Player;
import es.ull.etsii.pai.practicafinal.redvsblue.Side;
import es.ull.etsii.pai.prct9.geometry.Point2D;



public class Hook extends Weapon{
	public static final double FIRE_RATE = 1.0;
	public static final int CLIP_SIZE = 3;
	public static final int RELOADING_TIME = 90;
	public static final int DAMAGE = 20;
	public static final int SPEED = 20;
	public static final int PUSH = 0;
	public static final int BULLET_SIZE = 15;
	
	public Hook(Player owner) {
		super(owner);
		initializeWeapon();
	}	
	
	public Hook(int x, int y) {
		super(x, y);
		initializeWeapon();
	}
	
	private void initializeWeapon() {
		setFireRate(FIRE_RATE);
		setMainClipSize(CLIP_SIZE);
		setMainAmmo(CLIP_SIZE);
		setSpeed(SPEED);
		setMainReloadingTime(RELOADING_TIME);
		setWidth(40);
		setHeight(15);
		setX_offset(-30);
		setY_offset(5);
		getGraphicShape().setTexturePath("textures/hook.png");
		getGraphicShape().setTextureAnchor(getGraphicShape());
		getGraphicShape().setImage(true);
		setReload_able(false);
	}
	protected void shootSecondary() {}

	protected void shootMain() {
		int side = getOwner().getLookingAt() == Side.LEFT? -getSpeed() : getSpeed();

		int addy = getOwner().isCrounched()? getY_offset() /2 : getY_offset();
		Point2D speed = new Point2D (side, 0);
		Point2D position = getOwner().getPosition();
		position = position.add(0, addy);
		Bullet breechBullet = new Rope_bullet(position, speed, DAMAGE, PUSH,getOwner(), BULLET_SIZE);
		breechBullet.setMaxDistance(600);
		addBullet(breechBullet);
	}
}
