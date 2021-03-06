package es.ull.etsii.pai.practicafinal.redvsblue;
/**
 * Progamacion de aplicaciones interactivas.
 * Universidad de La Laguna.
 * 
 * @author Sabato Ceruso sab7093@gmail.com
 * @author Javier Martin Hernandez alu0100777758@ull.edu.es
 */
import java.awt.Graphics;
import java.util.ArrayList;

import es.ull.etsii.pai.practicafinal.editor.MapPainter;
import es.ull.etsii.pai.practicafinal.graphics.Drawable;

public class RvsB_World implements Drawable {
	BvsR_Map mapData;
	private ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	private ArrayList<Drawable> GUI = new ArrayList<Drawable>();
	public RvsB_World(BvsR_Map map) {
		setMapData(map);
		setPlayers();
	}
	private void setPlayers() {
		getPlayer_one().setMap(this);
	}
	public void addBullet(Bullet actor) {
		getBullets().add(actor);
	}
	@Override
	public void paint(Graphics g) {
		MapPainter.paint(g,getMapData());
		PaintDynamicElements(g);
		MapPainter.paintForeground(g, getMapData());
	}
	private void PaintDynamicElements(Graphics g) {
		for(Actor dyn : getBullets())
			dyn.paint(g);
		for(Drawable gui : getGUI())
			gui.paint(g);
	}
	public BvsR_Map getMapData() {
		return mapData;
	}
	public void setMapData(BvsR_Map mapData) {
		this.mapData = mapData;
	}
	public ArrayList<Drawable> getGUI() {
		return GUI;
	}

	public void setGUI(ArrayList<Drawable> gUI) {
		GUI = gUI;
	}
	public ArrayList<Bullet> getBullets() {
		return bullets;
	}
	public void setBullets(ArrayList<Bullet> bullets) {
		this.bullets = bullets;
	}
	public  ArrayList<Entity> getStaticMap() {
		return getMapData().getStaticMap();
	}
	public Player getPlayer_one() { 
		// TODO Auto-generated method stub
		return getMapData().getPlayer_one();
	}
	public Player getPlayer_two() {
		// TODO Auto-generated method stub
		return getMapData().getPlayer_two();
	}

}
