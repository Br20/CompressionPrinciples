package Clases;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class PanelconFondo extends JPanel{

	private static final long serialVersionUID = 1L;
	private Imagen im;

	public void setImage (String ruta){
		im = new Imagen(ruta);
		repaint();
	}
	
	public void setImage(Imagen imag){
		this.im = imag;
	}
	
	
	public Imagen getImagen(){
		return this.im;
	}
	
	
	public BufferedImage getFondo(){
		return this.im.getImagen();
	}
	
	public void paint(Graphics g) {
		if (im!=null){
			g.drawImage(im.getImagen(), 0, 0, getWidth(), getHeight(),this);
        	setOpaque(false);
        }
        else {
        	setOpaque(true);
        }
		super.paint(g);
		repaint();
	}
}
