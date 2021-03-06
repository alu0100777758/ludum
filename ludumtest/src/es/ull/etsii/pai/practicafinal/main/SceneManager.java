package es.ull.etsii.pai.practicafinal.main;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

/**
 * Progamacion de aplicaciones interactivas.
 * Universidad de La Laguna.
 * 
 * @author Sabato Ceruso sab7093@gmail.com
 * @author Javier Martin Hernandez alu0100777758@ull.edu.es
 *
 */

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import es.ull.etsii.pai.practicafinal.redvsblue.GameScenario;
import es.ull.etsii.pai.practicafinal.redvsblue.ScenarioPanel;
import es.ull.etsii.pai.practicafinal.redvsblue.ScreenManager;

public class SceneManager extends JFrame {
	ScenarioPanel currentScenario;
	private boolean keyHandlerON = true;

	public SceneManager() {
		super();
		this.addKeyListener(new KeyHandler());
		this.addComponentListener(new SizeHandler());
	//	this.addMouseMotionListener(new MouseMotionHandler());
		// setCurrentScenario(scenario);
		// add(getCurrentScenario());
		// scenario.setSceneManager(this);
	}

	public void switchScenario(ScenarioPanel scenario) {
		if (currentScenario != null)
			remove(getCurrentScenario());
		setCurrentScenario(scenario);
		add(scenario);
		scenario.setSceneManager(this);
		validate();
		setKeyHandlerON(true);
	}

	public ScenarioPanel getCurrentScenario() {
		return currentScenario;
	}

	public void setCurrentScenario(ScenarioPanel currentScenario) {
		this.currentScenario = currentScenario;
	}
	class MouseMotionHandler implements MouseMotionListener {

		@Override
		public void mouseDragged(MouseEvent e) {
		//	getCurrentScenario().moveMouse(e.getX(), e.getY());
			
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			//getCurrentScenario().moveMouse(e.getX(), e.getY());
		}
		
	}
	/**
	 * Manejador de teclas.
	 * 
	 * @author Sabato Ceruso.
	 * @author Javier Martin Hernandez.
	 *
	 */
	class KeyHandler implements KeyListener {

		@Override
		public void keyPressed(KeyEvent e) {
			if (isKeyHandlerON())
				getCurrentScenario().pulsedKey(e.getKeyCode(), e.getKeyChar());
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			if (isKeyHandlerON())
				getCurrentScenario().releasedKey(arg0.getKeyCode(),
						arg0.getKeyChar());
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
		}

	}
	
	class SizeHandler extends ComponentAdapter{
		@Override
		public void componentResized(ComponentEvent e) {
			ScreenManager.getInstance().updateWindowSize(getWidth(), getHeight());
		}
	}
	public void notify_resize() {
		getCurrentScenario().sizeUpdate();
	}

	public boolean isKeyHandlerON() {
		return keyHandlerON;
	}

	public void setKeyHandlerON(boolean keyHandlerON) {
		this.keyHandlerON = keyHandlerON;
	}

}
