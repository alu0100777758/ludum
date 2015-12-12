package es.ull.etsii.pai.practicafinal.editor;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import es.ull.etsii.pai.practicafinal.redvsblue.BasicEnemy;
import es.ull.etsii.pai.practicafinal.redvsblue.Player;
import es.ull.etsii.pai.practicafinal.redvsblue.ScreenManager;
import es.ull.etsii.pai.prct9.geometry.Point2D;

public class EnemyTool extends PlayerInitTool {
	public static final int BASIC = 0;
	public static final int YOQUESE = 1;
	int enemyType = 0;
	private JPopupMenu popup;
	
	public EnemyTool(){
		super();
		setMenu();
	}
	private void setMenu() {
		setPopup(new JPopupMenu());
		JMenuItem basic = new JMenuItem("basico");
		basic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setEnemyType(BASIC);
			}
		});
		JMenuItem yoquese = new JMenuItem("yoquese");
		yoquese.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setEnemyType(YOQUESE);
			}
		});
		getPopup().add(basic);
		getPopup().add(yoquese);
	}
	public void add(MouseEvent e) {
		addPlayer(getEnemy(e.getPoint()));
		setModified(true);
	}
	Player getEnemy(Point point){
		ScreenManager scr = ScreenManager.getInstance();
		Point2D pos = new Point2D(point.getX() - scr.getOffset_x() , point.getY()- scr.getOffset_y());
		Player player = null;
		switch(getEnemyType()){
		case BASIC :
			player = new BasicEnemy(pos,null);
			break;
		case YOQUESE:
			player = null;
			break;
		}
		return player;
	}
	public void addPlayer(Player player) {
		if (player != null) {
//				player.getTorax().setTexturePath("textures/char_sprite.png");
//				player.getLegs().setTexturePath("textures/leg_right_sprite.png");
//
//				getMap().addActor(player);
			if (getMap().getPlayer_one() == null) {
				getMap().setPlayer_one(player);
				player.getTorax().setTexturePath("textures/char_sprite.png");
				player.getLegs().setTexturePath("textures/leg_right_sprite.png");
//				player.getTorax().setTexturePath("textures/leg_right_sprite.png");
				getMap().addActor(getMap().getPlayer_one());
			}
			}
	}
	public int getEnemyType() {
		return enemyType;
	}
	public void setEnemyType(int enemyType) {
		this.enemyType = enemyType;
	}
	public JPopupMenu getPopup() {
		return popup;
	}
	public void setPopup(JPopupMenu popup) {
		this.popup = popup;
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		switch (arg0.getButton()) {
		case MouseEvent.BUTTON3:
			getPopup().show(getFrame().getContentPane(), arg0.getX(),
					arg0.getY());
			break;

		default:
			super.mousePressed(arg0);
			break;
		}
	}
}
