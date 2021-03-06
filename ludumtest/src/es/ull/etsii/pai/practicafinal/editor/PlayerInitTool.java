package es.ull.etsii.pai.practicafinal.editor;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import es.ull.etsii.pai.practicafinal.redvsblue.Player;
import es.ull.etsii.pai.practicafinal.redvsblue.ScreenManager;
import es.ull.etsii.pai.prct9.geometry.Point2D;

/**
 * Progamacion de aplicaciones interactivas.
 * Universidad de La Laguna.
 * 
 * @author Sabato Ceruso sab7093@gmail.com
 * @author Javier Martin Hernandez alu0100777758@ull.edu.es
 */
/**
 * Herramienta encargada de establecer las posiciones de inicio de los jugadores.
 *
 */
public class PlayerInitTool extends EditorTool {
	public static final String T_PLAYERINIT_ICON = "/icons/PlayerInitTool.png"; //	ruta del icono
	public static final String [] LEG_SPRITES = {"textures/leg_right_sprite.png","textures/leg_mid_sprite.png","textures/leg_left_sprite.png"};
	public PlayerInitTool() {
		setButton(new JButton(new ImageIcon(getClass().getResource(T_PLAYERINIT_ICON))));
	}
	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton()== MouseEvent.BUTTON1)
			add(e);
	}

	protected void add(MouseEvent e) {
		ScreenManager scr =  ScreenManager.getInstance();
		addPlayer(new Player(new Point2D(e.getX() - scr.getOffset_x() , e.getY()- scr.getOffset_y()), null));
		setModified(true);
	}
	/**
	 * @param player metodo que inserta el jugador en el mapa
	 */
	protected void addPlayer(Player player) {
		if (player != null) {
			if (getMap().getPlayer_one() == null) {
				getMap().setPlayer_one(player);
				player.getTorax().setTexturePath("textures/char_sprite.png");
				for(String path : LEG_SPRITES)
					player.getLegs().setTexturePath(path);
//				player.getLegs().setTexturePath("textures/leg_right_sprite.png");
//				player.getTorax().setTexturePath("textures/leg_right_sprite.png");

				getMap().addActor(getMap().getPlayer_one());
			}
		}
	}
	
	/*
	 * metodos sin usar de la clase padre 
	 * (conceptualmente definen la ausencia de reaccion por parte de la herramienta a cada tipo de accion)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}
	@Override
	public void mouseDragged(MouseEvent e) {
	}
	@Override
	public void mouseMoved(MouseEvent e) {	
	}
	@Override
	public void keyPressed(KeyEvent e) {	
	}
	@Override
	public void keyReleased(KeyEvent e) {
	}
	@Override
	public void keyTyped(KeyEvent e) {	
	}
	
	public void mouseWheelMoved(MouseWheelEvent e) {
		// TODO Auto-generated method stub
		
	}

}
