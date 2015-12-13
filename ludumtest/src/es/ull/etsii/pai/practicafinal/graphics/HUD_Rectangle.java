package es.ull.etsii.pai.practicafinal.graphics;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;

import es.ull.etsii.pai.practicafinal.redvsblue.ResourceManager;
import es.ull.etsii.pai.practicafinal.redvsblue.ScreenManager;

public class HUD_Rectangle extends GraphicRectangle {

	public HUD_Rectangle(int x1, int y1, int width, int height) {
		super(x1, y1, width, height);
	}
	public void paint(Graphics g) {
		double xrate = ScreenManager.getInstance().getRate_x();
		double yrate = ScreenManager.getInstance().getRate_y();
		if(getTextureAnchor() != null)
			setTextureAnchor(new Rectangle((int)((getTextureAnchor().x)* xrate), (int)((getTextureAnchor().y )* yrate),(int)(getTextureAnchor().width ), (int)(getTextureAnchor().height )));
		if (isTexturized() && !isImage())
			texturize(getTexturePath());
		Graphics2D g2 = (Graphics2D) g.create();
		if (isImage()) {
			BufferedImage bimage = ResourceManager.getInstance()
					.getBufferedImage(getTexturePath());
//			if (isFlipImage())
//				bimage = createFlipped(bimage);
//			if(isRotated()){
//				bimage = createRotated(bimage, getRotation());
//			}
			g2.drawImage(bimage, (int) ((getLocation().getX())* xrate),
					(int) (getLocation().getY() * yrate),
					(int) (getWidth() * xrate), (int) (getHeight() * yrate),
					null);
		} else {
			g2.setPaint(getPaint());
			g2.fill(new Rectangle((int) ((getLocation().getX())* xrate),
					(int) ((getLocation().getY())* yrate),
					(int) (getWidth() * xrate), (int) (getHeight() * yrate)));
		}

		g2.dispose();
	}
	private void texturize(String texturePath2) {
		double xrate = ScreenManager.getInstance().getRate_x();
		double yrate = ScreenManager.getInstance().getRate_y();
		Rectangle a = getTextureAnchor();
		setPaint(new TexturePaint(ResourceManager.getInstance()
				.getBufferedImage(getTexturePath()), new Rectangle((int) (a
				.getLocation().getX() * xrate),
				(int) (a.getLocation().getY() * yrate),
				(int) (a.getWidth() * xrate), (int) (a.getHeight() * yrate))));
		setTexturized(false);
	}
}
