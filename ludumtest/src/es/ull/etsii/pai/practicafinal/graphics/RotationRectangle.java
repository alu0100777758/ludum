package es.ull.etsii.pai.practicafinal.graphics;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import es.ull.etsii.pai.practicafinal.redvsblue.ResourceManager;
import es.ull.etsii.pai.practicafinal.redvsblue.ScreenManager;

public class RotationRectangle extends GraphicRectangle {
	private static final long serialVersionUID = -2828798826897107548L;
	private double rotationg = 0;
	public RotationRectangle(int x1, int y1, int width, int height) {
		super(x1, y1, width, height);
		// TODO Auto-generated constructor stub
	}
	/**
	 * devuelve la la imagen "image" rotada "grades grados.
	 * @param image
	 * @param grades
	 * @return	BufferedImage
	 */
	public BufferedImage rotate(BufferedImage image, double grades) {
		double rotationRequired = Math.toRadians(grades);
		double locationX = image.getWidth() / 2;
		double locationY = image.getHeight() / 2;
		AffineTransform tx = AffineTransform.getRotateInstance(
				rotationRequired, locationX, locationY);
		AffineTransformOp op = new AffineTransformOp(tx,
				AffineTransformOp.TYPE_BILINEAR);
		return op.filter(image, null);
	}
	@Override
	public void paint(Graphics g2) {
		double xrate = ScreenManager.getInstance().getRate_x();
		double yrate = ScreenManager.getInstance().getRate_y();
		double xoffset = ScreenManager.getInstance().getOffset_x();
		double yoffset = ScreenManager.getInstance().getOffset_y();
			BufferedImage bimage = ResourceManager.getInstance()
					.getBufferedImage(getTexturePath());
				bimage = rotate(bimage, getRotationg());
			g2.drawImage(bimage, (int) ((getLocation().getX() + xoffset) * xrate),
					(int) ((getLocation().getY() + yoffset)* yrate),
					(int) (getWidth() * xrate), (int) (getHeight() * yrate),
					null);
	}
	public double getRotationg() {
		return rotationg;
	}
	public void setRotationg(double d) {
		this.rotationg = d;
	}
}
