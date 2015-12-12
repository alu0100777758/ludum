package es.ull.etsii.pai.practicafinal.editor;

import java.awt.event.MouseEvent;

import es.ull.etsii.pai.practicafinal.redvsblue.BasicEnemy;
import es.ull.etsii.pai.practicafinal.redvsblue.Player;
import es.ull.etsii.pai.prct9.geometry.Point2D;

public class EnemyTool extends PlayerInitTool {
	protected void add(MouseEvent e) {
		addPlayer(new BasicEnemy(new Point2D(e.getX(), e.getY()), null));
		setModified(true);
	}
}
