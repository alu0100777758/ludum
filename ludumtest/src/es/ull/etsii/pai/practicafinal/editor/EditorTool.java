package es.ull.etsii.pai.practicafinal.editor;
/**
 * Progamacion de aplicaciones interactivas.
 * Universidad de La Laguna.
 * 
 * @author Sabato Ceruso sab7093@gmail.com
 * @author Javier Martin Hernandez alu0100777758@ull.edu.es
 */
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import es.ull.etsii.pai.practicafinal.redvsblue.BvsR_Map;
import es.ull.etsii.pai.practicafinal.redvsblue.ScreenManager;
import es.ull.etsii.pai.prct9.geometry.Point2D;

/**
 * clase abstracta que representa cualquier herramienta del editor
 *
 */
public abstract class EditorTool implements ActionListener, MouseListener,
		MouseMotionListener, MouseWheelListener, KeyListener {
	private JButton button;					//	bot�n que representar� a la herramienta
	private int toolid = 0;					//	identificador de la herramienta para ser identificada por el editor
	private BvsR_Map map = null;			//	mapa sobre el que trabajar� la herramienta
	private JFrame frame = null;			//	JFrame en el que se estar� haciendo uso de la herramienta
	boolean modified = false;				//	True si necesita actualizar la pantalla
	
	public EditorTool(){
	}
	public void paint(Graphics g) {
	}
	Point inSystem(Point point){
		ScreenManager scr = ScreenManager.getInstance();
		point.setLocation((int)(point.getX()+scr.getOffset_x())*scr.getRate_x(),(int)(point.getY()+scr.getOffset_y())*scr.getRate_y());
		return point;
	}
	Point outSystem(Point point){
		ScreenManager scr = ScreenManager.getInstance();
		point.setLocation((int)(point.getX()-scr.getOffset_x())/scr.getRate_x(),(int)(point.getY()-scr.getOffset_y())/scr.getRate_y());
		return point;
	}
	Point2D inSystem(Point2D point){
		ScreenManager scr = ScreenManager.getInstance();
		point.setX((point.x()+scr.getOffset_x())*scr.getRate_x());
		point.setY((point.y()+scr.getOffset_y())*scr.getRate_y());
		return point;
	}
	/**
	 * Getters y setters
	 */
	public void setToolid(int toolid) {
		this.toolid = toolid;
		getButton().setName("" + getToolid());
	}
	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public boolean isModified() {
		return modified;
	}

	public void setModified(boolean modified) {
		this.modified = modified;
	}

	public BvsR_Map getMap() {
		return map;
	}

	public void setMap(BvsR_Map map) {
		this.map = map;
	}

	public int getToolid() {
		return toolid;
	}

	public JButton getButton() {
		return button;
	}

	public void setButton(JButton button) {
		this.button = button;
	}

}
