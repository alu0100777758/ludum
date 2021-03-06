package es.ull.etsii.pai.practicafinal.redvsblue;

/**
 * Progamacion de aplicaciones interactivas.
 * Universidad de La Laguna.
 * 
 * @author Sabato Ceruso sab7093@gmail.com
 * @author Javier Martin Hernandez alu0100777758@ull.edu.es
 *
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import es.ull.etsii.pai.common.CircularArrayList;
import es.ull.etsii.pai.practicafinal.graphics.GraphicRectangle;
import es.ull.etsii.pai.practicafinal.graphics.RotationRectangle;
import es.ull.etsii.pai.practicafinal.metaclass.Weapon;
import es.ull.etsii.pai.practicafinal.metaclass.gamemodeclasses.DefaultModeScoring;
import es.ull.etsii.pai.practicafinal.metaclass.weapons.Knife;
import es.ull.etsii.pai.practicafinal.physics.Line_trace;
import es.ull.etsii.pai.practicafinal.physics.PhysicalRectangle;
import es.ull.etsii.pai.practicafinal.physics.Physical_active;
import es.ull.etsii.pai.practicafinal.physics.Physical_passive;
import es.ull.etsii.pai.practicafinal.physics.Tracer;
import es.ull.etsii.pai.prct9.geometry.Point2D;
import es.ull.etsii.pai.prct9.geometry.Segment;

public class Player extends Actor implements Physical_active {
	private static final long serialVersionUID = -3033119409170313204L;
	public static final String[] LEG_SPRITES = { "textures/leg_right_sprite.png", "textures/leg_mid_sprite.png",
			"textures/leg_left_sprite.png" };
	public static final String[] INFECTION_STATE = { "textures/char_sprite.png", "textures/char_sprite2.png",
			"textures/char_sprite3.png", "textures/char_sprite4.png", "textures/char_sprite5.png",
			"textures/char_sprite6.png" };
	private RotationRectangle[] toraxs = new RotationRectangle[INFECTION_STATE.length];
	private Point2D speed = new Point2D(0, -5); // Vector velocidad.
	private Point2D push; // Vector de empuje.
	private int score; // puntuacion del jugador.
	private int hp; // Cantidad de vida actual.
	private int infection = 0;
	private int maxInfection = 100;
	private Side lookingAt; // Lado al que se esta mirando.
	private Weapon weapon; // Arma actual.
	private RvsB_World map; // Mapa en que se encuentra el jugador.
	private boolean dead = false; // True si esta muerto.
	private boolean block_up = false; // True si no se puede mover hacia arriba.
	private boolean block_down = false; // True si no se puede mover hacia
										// abajo.
	private boolean block_left = false; // True si no se puede mover a la
										// izquierda.
	private boolean block_right = false; // True si no se puede mover a la
											// derecha.
	private boolean move_up = false; // True si esta saltando.
	private boolean move_down = false; // True si se esta agachando.
	private boolean move_left = false; // True si se esta moviendo a la
										// izquierda.
	private boolean move_right = false; // True si se esta moviendo a la
										// derecha.
	private boolean crounched = false; // True si esta agachado.
	private boolean shooting = false; // True si esta disparando.
	private CircularArrayList<RotationRectangle> torax = new CircularArrayList<>();
	private CircularArrayList<RotationRectangle> legs = new CircularArrayList<>();
	private double rotationRate = 5;
	protected PlayerData stats = new PlayerData(20, 1, 0, 40, 40, 8, -5.0, 0, 1, 150, 2, Color.BLUE,
			new String[] { "playerhit01.wav", "playerhit02.wav", "playerhit03.wav", });
	private String reloadSound = "";
	private boolean physicalResponseSuspended = false; // denota si se
														// encuentran
														// desactivadas la
														// reparacion de
														// colisiones.
	private Tracer killTracer;

	/**
	 * Crea un jugador en la posicion dada en el mapa dado.
	 * 
	 * @param position
	 * @param map
	 */
	public Player(Point2D position, RvsB_World map) {
		super(position);
		setHp(stats.getDEFAULT_MAX_HP());
		setMaxHp(stats.getDEFAULT_MAX_HP());
		setMap(map);
		setPhysicalShape(new PhysicalRectangle((int) getPosition().x(), (int) getPosition().y(), stats.getWidth(),
				stats.getHeight()));
		setLookingAt(Side.RIGHT);
		setPush(new Point2D(0, 0));
		setJump(100, 0.33);

		for (int i = 0; i< INFECTION_STATE.length; i++) {
			toraxs[i] = new RotationRectangle((int) getPosition().x(), (int) getPosition().y(),
					stats.getWidth(), stats.getHeight());
			toraxs[i].setTexturePath(INFECTION_STATE[i]);
//			getTorax().setPaint(Color.BLUE);
//			getGraphicShapes().add(toraxs[i]);
		}
		setTorax(toraxs[0]);
		// RotationRectangle legs = new RotationRectangle((int)
		// getPosition().x(), (int) getPosition().y(),
		// stats.getWidth(), stats.getHeight());
		for (String path : LEG_SPRITES) {
			RotationRectangle legs = new RotationRectangle((int) getPosition().x(), (int) getPosition().y(),
					stats.getWidth(), stats.getHeight());
			addLegs(legs);
			legs.setTexturePath(path);
			getGraphicShapes().add(legs);
		}
		// addLegs(legs);
		// getGraphicShapes().add(legs);
		// getLegs().setPaint(Color.RED);
		// getGraphicShapes().get(stats.getBODY()).setPaint(Color.BLUE);
		// getGraphicShapes().add(null);

		setScore(0);
		// setWeapon(new RocketLauncher(this));
		setWeapon(new Knife(this));
		setSpeed(new Point2D(0, 0));
		setKillTracer(new Tracer(new Line_trace(getPosition(), getPosition().add(getSpeed()), this)));

	}

	public RotationRectangle[] getToraxs() {
		return toraxs;
	}

	public void setToraxs(RotationRectangle[] toraxs) {
		this.toraxs = toraxs;
	}

	void update_animSpeed() {
		if (getSpeed().equals(Point2D.ORIGIN)) {
			this.legs.setMaxSteps(0);
		} else {
			int ttl = (int) Math.sqrt(Math.pow(getSpeed().x(), 2) + Math.pow(getSpeed().y(), 2));
			// System.out.println(" la velocidad es : " + ttl);
			this.legs.setMaxSteps(40 - ttl);
		}
	}

	public void updateToraxRotation(Point p) {
		double deltaX = p.getX() - getPosition().x();
		double deltaY = getPosition().y() - p.getY();
		double degre = Math.atan2(deltaX, deltaY);
		// System.out.println("degree: " + degre+ " xdif: "+deltaX + " ydif: "
		// +deltaY);
		getTorax().setRotationg(Math.toDegrees(degre));
	}

	public void updateLegsRotation(Point2D p) {
		// System.out.println("pos:" + getPosition()+ " Vvector" + p);
		double deltaX = p.x() - getPosition().x();
		double deltaY = getPosition().y() - p.y();
		double degre = Math.atan2(deltaX, deltaY);
		// System.out.println("degree: " + degre+ " xdif: "+deltaX + " ydif:"
		// +deltaY);
		getLegs().setRotationg(Math.toDegrees(degre));
	}

	@Override
	public void paint(Graphics g) {
		getLegs().paint(g);
		getTorax().paint(g);
		// for (int i = 0; i < getGraphicShapes().size(); i++)
		// getGraphicShapes().get(i).paint(g.create());
	}

	@Override
	public boolean hasToDie() {
		dead = infection >= maxInfection ? true : dead;

		return isDead();
	}

	/**
	 * Mueve el jugador a la izquierda.
	 * 
	 * @return
	 */
	private boolean moveLeft() {
		int moduleSpeed = stats.getSPEED();

		getTorax().setRotationg(getTorax().getRotationg() - rotationRate);
		getLegs().setRotationg(getLegs().getRotationg() - rotationRate);

		getSpeed().setX(Math.cos(Math.toRadians(getTorax().getRotationg() - 90)) * moduleSpeed);
		getSpeed().setY(Math.sin(Math.toRadians(getTorax().getRotationg() - 90)) * moduleSpeed);

		increaseInfection();

		return true;
	}

	/**
	 * Mueve el jugador a la derecha.
	 * 
	 * @return
	 */
	private boolean moveRight() {
		int moduleSpeed = stats.getSPEED();

		getTorax().setRotationg(getTorax().getRotationg() + rotationRate);
		getLegs().setRotationg(getLegs().getRotationg() + rotationRate);

		getSpeed().setX(Math.cos(Math.toRadians(getTorax().getRotationg() - 90)) * moduleSpeed);
		getSpeed().setY(Math.sin(Math.toRadians(getTorax().getRotationg() - 90)) * moduleSpeed);

		increaseInfection();

		return true;
	}

	/**
	 * Hace que el jugador dispare.
	 */
	public void shoot() {
		getWeapon().triggerMain();
		setShooting(true);
	}

	/**
	 * Hace que deje de disparar.
	 */
	public void stopShooting() {
		getWeapon().releaseMain();
		setShooting(false);
	}

	@Override
	public boolean repair_collisionY(Point2D point) {
		return false;
	}

	@Override
	public boolean repair_collisionX(Point2D point) {

		return false;
	}

	@Override
	public boolean collides(Physical_passive actor) {
		return this.getPhysicalShape().collides(actor);
	}

	@Override
	public void setLocation(int x, int y) {
		super.setPosition(new Point2D(x, y));
		getTorax().setLocation(x, y);
		getLegs().setLocation(x, y);
		//		for (GraphicRectangle graphics : getGraphicShapes())
//			graphics.setLocation(new Point((int) getPosition().x(), (int) getPosition().y()));
	};

	/**
	 * Afinar esto.
	 */
	@Override
	public boolean repair_collision(Physical_passive actor) {
		if (isPhysicalResponseSuspended())
			return true;
		Rectangle intersection = actor.getCollisionedRectangle(this.getPhysicalRectangle());
		boolean repaired = false;
		if (intersection.isEmpty())
			return true;
		// Resolvemos colisiones primero en Y mejor.

		// Miramos si colisiona con la cabeza o los pies:

		// Si alguno es true colisiona con la cabeza.
		if ((actor.getPhysicalRectangle()
				.contains(new Point((int) getPhysicalRectangle().getMinX(), (int) getPhysicalRectangle().getMinY()))
				|| actor.getPhysicalRectangle().contains(
						new Point((int) getPhysicalRectangle().getMaxX(), (int) getPhysicalRectangle().getMinY())))
				&& !isBlock_down()) {
			if (Math.abs(getSpeed().y()) * 3 >= intersection.getHeight()) {
				this.setPosition(getPosition().add(new Point2D(0, intersection.getHeight()))); // Tocado
																								// con
																								// la
																								// cabeza
				repaired = true;
				setJumpTTL(0);
				// setBlock_up(true);
			}
		}
		// Si alguno es true colisiona con los pies.
		else if ((actor.getPhysicalRectangle()
				.contains(new Point((int) getPhysicalRectangle().getMinX(), (int) getPhysicalRectangle().getMaxY()))
				|| actor.getPhysicalRectangle().contains(
						new Point((int) getPhysicalRectangle().getMaxX(), (int) getPhysicalRectangle().getMaxY())))
				&& !isBlock_up()) {
			if (Math.abs(getSpeed().y()) * 3 >= intersection.getHeight() && !isBlock_down()) {
				this.setPosition(getPosition().add(new Point2D(0, -intersection.getHeight())));// Tocado
																								// con
																								// los
																								// pies.
				repaired = true;
				// setBlock_down(true);
			}
		}
		if (!repaired) {
			if (Math.abs(3 * getSpeed().add(getPush()).x()) >= intersection.getWidth()) { // Comentar
																							// esto,
																							// buscar
																							// solucion
																							// mejor
																							// que
																							// multiplicar
																							// por
																							// 2.
				if (getSpeed().add(getPush()).x() > 0) {
					this.setPosition(getPosition().substract(intersection.getWidth(), 0));
					// setBlock_right(true);
				} else {
					this.setPosition(getPosition().add(intersection.getWidth(), 0));
					// setBlock_left(true);
				}

			}
		}
		// System.out.println("Vel: " + getSpeed().x() + " ancho: " +
		// intersection.getWidth() + " alto: " + intersection.getHeight()); //
		return false;
	}

	/**
	 * Realiza las operaciones necesarias cuando es golpeado por una bala.
	 * 
	 * @param bullet
	 */
	public void gotHit(Bullet bullet) {
		if (bullet.getOwner() != this) {
			DefaultModeScoring.addHitScore(bullet.getOwner());
			AudioManager.startAudio(bullet.getSoundName());
			if (!isDead())
				AudioManager.startAudio(getSoundName());
			setHp(getHp() - bullet.getDamage());
			if (bullet.getSpeed().x() > 0)
				getPush().setX(getPush().x() + bullet.getPush());
			else
				getPush().setX(getPush().x() - bullet.getPush());
			if (getHp() <= 0 && !isDead())
				die();
		}
	}

	/**
	 * Realiza las operaciones necesarias para que el jugador muera.
	 */
	public void die() {
		setDead(true);
		setHp(0);
		getGraphicShapes().get(stats.getBODY()).setLocation((int) getPosition().x(),
				(int) getPosition().y() + stats.getHeight() - stats.getWidth());
		getGraphicShapes().get(stats.getBODY()).setSize(stats.getHeight(), stats.getWidth());
		getPhysicalRectangle().setLocation((int) getPosition().x(),
				(int) getPosition().y() + stats.getHeight() - stats.getWidth());
		getPhysicalRectangle().setSize(stats.getHeight(), stats.getWidth());
		getGraphicShapes().remove(stats.getWEAPON());
	}

	/**
	 * Resuelve los movimientos que ha de realizar el jugador en el proximo
	 * frame.
	 */
	void ResolveUnreleasedMovements() {
		// if (isMove_down())
		// moveDown();
		if (isMove_left())
			moveLeft();
		if (isMove_right())
			moveRight();
		// if (isMove_up())
		// moveUP();
		// setBlock_down(false);
		// if (!isMove_left() && !isMove_right())
		// getSpeed().setX(0);
		// if (!isMove_down() && !isMove_up())
		// getSpeed().setY(0);
	}

	/**
	 * Actualiza el empuje.
	 */
	private void updatePush() {
		if ((int) getPush().x() == 0 || Math.abs(getPush().x()) < stats.getPUSH_RESIST())
			getPush().setX(0);
		else if (getPush().x() > 0)
			getPush().setX(getPush().x() - stats.getPUSH_RESIST());
		else
			getPush().setX(getPush().x() + stats.getPUSH_RESIST());
	}

	/**
	 * Mueve el jugador segun marca la velocidad y actualiza el disparo del arma
	 * .
	 */
	@Override
	public boolean updatePos(Physical_passive map) {
		update_animSpeed();
		// if (!map.getPhysicalRectangle().contains(getPhysicalShape()))
		// return false;
		if (!isDead()) {
			ResolveUnreleasedMovements();
			getWeapon().update();
			updatePush();
			/*
			 * if (!isBlock_down()) { // Por lo visto esto controla el salto if
			 * (getJumpTTL() != 0) { moveJump(); } else // Y este 3 es la
			 * gravedad., lo paso a un metodo de actor // para decirle q empiece
			 * a caer fall(); // ; }
			 */
			// Aqui es donde realmente cambiamos la posicion una vez calculado
			// donde va a ir.
			// updateLegsRotation(getSpeed().add(getPosition()));
			getLegs().setLocation(new Point((int) getPosition().x(), (int) getPosition().y()));
			setPosition(getPosition().add(getSpeed().add(getPush())));
			getTorax().setLocation(new Point((int) getPosition().x(), (int) getPosition().y()));
			// setPosition(getPosition().add(getSpeed().add(getPush()))); //
			// CAmbiado;
			Line2D line = new Line2D.Float((float) getKillTracer().getTrace().getLine().getX1(),
					(float) getKillTracer().getTrace().getLine().getY1(), (float) getSpeed().x(),
					(float) getSpeed().y());
			getKillTracer().getTrace().setLine(line);
			ArrayList<Entity> hits = getKillTracer().getCollision(getMap());
			for (Entity ent : hits) {
				if (ent instanceof Player && ent != this) {
					Player plent = (Player) ent;
					plent.die();
				}
			}
		}
		return true;
		
	}

	/**
	 * Ejecuta la accion de saltar poniendo libre la direccion hacia abajo y
	 * dando valor al jumpTTL
	 */
	public void jump() {
		// if (isBlock_down()) {
		// setJumpTTL(getMaxJumpTTL());
		// setBlock_down(false);
		// }
	}

	/**
	 * Mueve el personaje hacia arriba reduciendo el tiempo que le queda por
	 * saltar.
	 */
	public void moveJump() {
		// getSpeed().setY(-getClimbPertTick());
		// setJumpTTL(getJumpTTL() - 1);

	}

	/**
	 * Hace caer al personaje segun marca la gravedad.
	 */
	public void fall() {
		// getSpeed().setY(-stats.getGRAVITY());
		setBlock_up(false);
	}

	/**
	 * A�ade una cantidad a la puntuacion.
	 * 
	 * @param value
	 */
	public void addToScore(int value) {
		setScore(getScore() + value);
	}

	@Override
	public PhysicalRectangle getPhysicalRectangle() {
		return getPhysicalShape();

	}

	@Override
	public Rectangle getCollisionedRectangle(Physical_passive actor) {
		return getPhysicalRectangle().getCollisionedRectangle(actor.getPhysicalRectangle());
	}

	@Override
	public ArrayList<Segment> getSegmentList() {
		return getPhysicalShape().getSegmentList();
	}

	public void playReload() {
		AudioManager.reproduceAudio(getReloadSound());
	}

	public void increaseInfection() {
		this.infection++;
		setTorax(toraxs[(int)((float)infection/maxInfection * (INFECTION_STATE.length-1))]);
	}

	public void increaseInfection(int quantity) {
		this.infection += quantity;
	}

	/**
	 * Getters y Setters.
	 * 
	 */
	public void setLeft(boolean b) {
		setMove_left(b);
	}

	public void setRight(boolean b) {
		setMove_right(b);
	}

	public void setUP(boolean b) {
		setMove_up(b);
	}

	public void setDown(boolean b) {
		setMove_down(b);
	}

	public Point2D getSpeed() {
		return speed;
	}

	public void setSpeed(Point2D speed) {
		this.speed = speed;
	}

	public int getJumpTTL() {
		return stats.getJumpTTL();
	}

	public void setJumpTTL(int jumpTTL) {
		this.stats.setJumpTTL(jumpTTL);
	}

	public boolean isBlock_up() {
		return block_up;
	}

	public void setBlock_up(boolean block_up) {
		this.block_up = block_up;
	}

	public boolean isBlock_down() {
		return block_down;
	}

	public void setBlock_down(boolean block_down) {
		this.block_down = block_down;
	}

	public boolean isBlock_left() {
		return block_left;
	}

	public void setBlock_left(boolean block_left) {
		this.block_left = block_left;
	}

	public boolean isBlock_right() {
		return block_right;
	}

	public void setBlock_right(boolean block_right) {
		this.block_right = block_right;
	}

	public boolean isMove_up() {
		return move_up;
	}

	public void setMove_up(boolean move_up) {
		this.move_up = move_up;
	}

	public boolean isMove_down() {
		return move_down;
	}

	public void setMove_down(boolean move_down) {
		this.move_down = move_down;
	}

	public boolean isMove_left() {
		return move_left;
	}

	public void setMove_left(boolean move_left) {
		this.move_left = move_left;
	}

	public boolean isMove_right() {
		return move_right;
	}

	public void setMove_right(boolean move_right) {
		this.move_right = move_right;
	}

	public int getMaxJumpTTL() {
		return stats.getMaxJumpTTL();
	}

	public void setMaxJumpTTL(int velY) {
		this.stats.setMaxJumpTTL(velY);
	}

	public boolean isCrounched() {
		return crounched;
	}

	public void setCrounched(boolean crounched) {
		this.crounched = crounched;
	}

	public boolean isShooting() {
		return shooting;
	}

	public void setShooting(boolean shooting) {
		this.shooting = shooting;
	}

	public Side getLookingAt() {
		return lookingAt;
	}

	public void setLookingAt(Side lookingAt) {
		if (lookingAt != getLookingAt()) {
			this.lookingAt = lookingAt;
			switchFlip();
		}
	}

	private void switchFlip() {
		for (GraphicRectangle rect : getGraphicShapes())
			rect.setFlipImage(!rect.isFlipImage());
	}

	public Weapon getWeapon() {
		return weapon;
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
		getGraphicShapes().set(stats.getWEAPON(), weapon.getGraphicShape());
	}

	public RvsB_World getMap() {
		return map;
	}

	public void setMap(RvsB_World map) {
		this.map = map;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	protected boolean isDead() {
		return dead;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}

	public Point2D getPush() {
		return push;
	}

	public void setPush(Point2D push) {
		this.push = push;
	}

	public int getMaxHp() {
		return stats.getMaxHp();
	}

	public void setMaxHp(int maxHp) {
		this.stats.setMaxHp(maxHp);
	}

	public void setJump(int height, double timeSeconds) {
		setMaxJumpTTL((int) (60 * timeSeconds));
		setClimbPertTick((double) height / getMaxJumpTTL());
	}

	public Color getColor() {
		return stats.getColor();
	}

	public double getClimbPertTick() {
		return stats.getClimbPertTick();
	}

	public void setClimbPertTick(double climbPertTick) {
		this.stats.setClimbPertTick(climbPertTick);
	}

	public void setColor(Color color) {
		this.stats.setColor(color);
	}

	private String getSoundName() {
		return stats.getHitSounds()[ResourceManager.getInstance().getRandGen().nextInt(stats.getHitSounds().length)];
	}

	public boolean isPhysicalResponseSuspended() {
		return physicalResponseSuspended;
	}

	public void setPhysicalResponseSuspended(boolean physicalResponseSuspended) {
		this.physicalResponseSuspended = physicalResponseSuspended;
	}

	public PlayerData getStats() {
		return stats;
	}

	public void setStats(PlayerData stats) {
		this.stats = stats;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getReloadSound() {
		return reloadSound;
	}

	public void setReloadSound(String reloadSound) {
		this.reloadSound = reloadSound;
	}

	public RotationRectangle getTorax() {
		return torax.get();
	}

	public void setTorax(RotationRectangle torax) {
		this.torax.add(torax);
	}

	public RotationRectangle getLegs() {
		return legs.get();
	}

	public void addLegs(RotationRectangle legs) {
		this.legs.add(legs);
	}

	public Tracer getKillTracer() {
		return killTracer;
	}

	public void setKillTracer(Tracer killTracer) {
		this.killTracer = killTracer;
	}

	public int getInfection() {
		return infection;
	}

	public void setInfection(int infection) {
		this.infection = infection;
		setTorax(toraxs[(int)((float)infection/maxInfection * (INFECTION_STATE.length)-1)]);

	}

	public int getMaxInfection() {
		return maxInfection;
	}

	public void setMaxInfection(int maxInfection) {
		this.maxInfection = maxInfection;
	}

}
