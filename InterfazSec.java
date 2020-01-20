package Clases;

import java.awt.Color;
import java.awt.Font;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Vector;

import javax.swing.*;

public class InterfazSec{
	
	private JFramee ventana;
	private JPanel panelPrinc;
	private PanelconFondo im1,im2,im3,im4,im5,im6,im7;
	private JLabel labelName1, labelName2, labelName3, labelName4, labelName5, labelName6, labelName7;
	private JLabel labelFactor1, labelFactor2, labelFactor3, labelFactor4, labelFactor5, labelFactor6, labelFactor7;
	private Color colorFondo = new Color(50,50,50);
	private Color blanco = new Color(255,255,255);
	private Font fuente = new Font("Calibri",0,14);
	private Imagen original = new Imagen("/photos/Will(Original).bmp");
	
	
	private class JFramee extends JFrame{
		private static final long serialVersionUID = 1L;

		@Override
		public void dispose(){
			new Interfaz().botonCargarClicked();
			super.dispose();
		}
		
		public JFramee(String srt){
			super(srt);
		}
	}
	
	
	public InterfazSec(Hashtable<Double, Imagen> imagenes){
		
		Vector<Double> factores = new Vector<Double>(imagenes.keySet());
		Collections.sort(factores);
		Collections.reverse(factores);
		
		
		//-----------------Ventana Principal--------------------//
		
		ventana = new JFramee("Trabajo Practico Especial - Teoria de la Información");
		ventana.setVisible(true);
		ventana.setSize(800, 600);
		ventana.setVisible(true);
		ventana.setResizable(false);
		ventana.setLocationRelativeTo(null);
		ventana.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
		//---------------Panel Principal----------------------//
		
		panelPrinc = new PanelconFondo();
		panelPrinc.setBackground(colorFondo);
		panelPrinc.setSize(800,600);
		panelPrinc.setLayout(null);
		ventana.add(panelPrinc);
		
		//---------------Panel Imagen 1---------------------//
		
		im1 = new PanelconFondo();
		im1.setBounds(15, 50, 160, 200);
		im1.setVisible(true);
		im1.setLayout(null);
		im1.setImage(imagenes.get(factores.elementAt(0)));
		panelPrinc.add(im1);
		
		//---------------Panel Imagen 2---------------------//
		
		im2 = new PanelconFondo();
		im2.setBounds(215, 50, 160, 200);
		im2.setVisible(true);
		im2.setLayout(null);
		im2.setImage(imagenes.get(factores.elementAt(1)));
		panelPrinc.add(im2);
		
		//---------------Panel Imagen 3---------------------//
		
		im3 = new PanelconFondo();
		im3.setBounds(415, 50, 160, 200);
		im3.setVisible(true);
		im3.setLayout(null);
		im3.setImage(imagenes.get(factores.elementAt(2)));
		panelPrinc.add(im3);
		
		//---------------Panel Imagen 4---------------------//
		
		im4 = new PanelconFondo();
		im4.setBounds(615, 50, 160, 200);
		im4.setVisible(true);
		im4.setLayout(null);
		im4.setImage(imagenes.get(factores.elementAt(3)));
		panelPrinc.add(im4);
		
		//---------------Panel Imagen 5-------------------//
		
		im5 = new PanelconFondo();
		im5.setBounds(100, 320, 160, 200);
		im5.setVisible(true);
		im5.setLayout(null);
		im5.setImage(imagenes.get(factores.elementAt(4)));
		panelPrinc.add(im5);
		
		//---------------Panel Imagen 6--------------------// 		
		
		im6 = new PanelconFondo();
		im6.setBounds((ventana.getWidth() / 2) - 80, 320, 160, 200);
		im6.setVisible(true);
		im6.setLayout(null);
		im6.setImage(imagenes.get(factores.elementAt(5)));
		panelPrinc.add(im6);

		//---------------Panel Imagen 7--------------------// 		
		
		im7 = new PanelconFondo();
		//im7.setBackground(new Color(150,150,150));
		im7.setBounds(540, 320, 160, 200);
		im7.setVisible(true);
		im7.setLayout(null);
		im7.setImage(imagenes.get(factores.elementAt(6)));
		panelPrinc.add(im7);
		
		
		//---------------Label nombre Imagen 1-----------------//
		
		labelName1 = new JLabel();
		labelName1.setText("1: " + im1.getImagen().getNombre());
		labelName1.setForeground(blanco);
		labelName1.setLayout(null);
		labelName1.setFont(fuente);
		labelName1.setVisible(true);
		labelName1.setBounds(20,25,145,20);
		panelPrinc.add(labelName1);
		
		//--------------Label nombre Imagen 2-----------------//
		
		labelName2 = new JLabel();
		labelName2.setText("2: " + im2.getImagen().getNombre());
		labelName2.setForeground(blanco);
		labelName2.setFont(fuente);
		labelName2.setLayout(null);
		labelName2.setVisible(true);
		labelName2.setBounds(220,25,145,20);
		panelPrinc.add(labelName2);
		
		//--------------Label nombre Imagen 3-----------------//
		
		labelName3 = new JLabel();
		labelName3.setText("3: " + im3.getImagen().getNombre());
		labelName3.setForeground(blanco);
		labelName3.setFont(fuente);
		labelName3.setLayout(null);
		labelName3.setVisible(true);
		labelName3.setBounds(420,25,145,20);
		panelPrinc.add(labelName3);
		
		//--------------Label nombre Imagen 4-----------------//
		
		labelName4 = new JLabel();
		labelName4.setText("4: " + im4.getImagen().getNombre());
		labelName4.setForeground(blanco);
		labelName4.setFont(fuente);
		labelName4.setLayout(null);
		labelName4.setVisible(true);
		labelName4.setBounds(620,25,145,20);
		panelPrinc.add(labelName4);
		
		//--------------Label nombre Imagen 5-----------------//
		
		labelName5 = new JLabel();
		labelName5.setText("5: " + im5.getImagen().getNombre());
		labelName5.setForeground(blanco);
		labelName5.setFont(fuente);
		labelName5.setLayout(null);
		labelName5.setVisible(true);
		labelName5.setBounds(110,295,145,20);
		panelPrinc.add(labelName5);
		
		//--------------Label nombre Imagen 6-----------------//
		
		labelName6 = new JLabel();
		labelName6.setText("6: " + im6.getImagen().getNombre());
		labelName6.setForeground(blanco);
		labelName6.setFont(fuente);
		labelName6.setLayout(null);
		labelName6.setVisible(true);
		labelName6.setBounds(330,295,145,20);
		panelPrinc.add(labelName6);
		
		//--------------Label nombre Imagen 7-----------------//
		
		labelName7 = new JLabel();
		labelName7.setText("7: " + im7.getImagen().getNombre());
		labelName7.setForeground(blanco);
		labelName7.setFont(fuente);
		labelName7.setLayout(null);
		labelName7.setVisible(true);
		labelName7.setBounds(550,295,145,20);
		panelPrinc.add(labelName7);
		
		
		//-----------------Label factor Imagen 1------------------//
		labelFactor1 = new JLabel();
		labelFactor1.setText("Factor: " + im1.getImagen().getFactorCorrelacion(original.getImagen()));
		labelFactor1.setForeground(blanco);
		labelFactor1.setFont(fuente);
		labelFactor1.setLayout(null);
		labelFactor1.setVisible(true);
		labelFactor1.setBounds(15, 250, 200, 20);
		panelPrinc.add(labelFactor1);
		
		//-----------------Label factor Imagen 2------------------//
		labelFactor2 = new JLabel();
		labelFactor2.setText("Factor: " + im2.getImagen().getFactorCorrelacion(original.getImagen()));
		labelFactor2.setForeground(blanco);
		labelFactor2.setFont(fuente);
		labelFactor2.setLayout(null);
		labelFactor2.setVisible(true);
		labelFactor2.setBounds(215, 250, 200, 20);
		panelPrinc.add(labelFactor2);
		
		//-----------------Label factor Imagen 3------------------//
		labelFactor3 = new JLabel();
		labelFactor3.setText("Factor: " + im3.getImagen().getFactorCorrelacion(original.getImagen()));
		labelFactor3.setForeground(blanco);
		labelFactor3.setFont(fuente);
		labelFactor3.setLayout(null);
		labelFactor3.setVisible(true);
		labelFactor3.setBounds(415, 250, 200, 20);
		panelPrinc.add(labelFactor3);
		
		//-----------------Label factor Imagen 4------------------//
		labelFactor4 = new JLabel();
		labelFactor4.setText("Factor: " + im4.getImagen().getFactorCorrelacion(original.getImagen()));
		labelFactor4.setForeground(blanco);
		labelFactor4.setFont(fuente);
		labelFactor4.setLayout(null);
		labelFactor4.setVisible(true);
		labelFactor4.setBounds(615, 250, 200, 20);
		panelPrinc.add(labelFactor4);
		
		//-----------------Label factor Imagen 5------------------//
		labelFactor5 = new JLabel();
		labelFactor5.setText("Factor: " + im5.getImagen().getFactorCorrelacion(original.getImagen()));
		labelFactor5.setForeground(blanco);
		labelFactor5.setFont(fuente);
		labelFactor5.setLayout(null);
		labelFactor5.setVisible(true);
		labelFactor5.setBounds(100, 520, 200, 20);
		panelPrinc.add(labelFactor5);
		
		//-----------------Label factor Imagen 6------------------//
		labelFactor6 = new JLabel();
		labelFactor6.setText("Factor: " + im6.getImagen().getFactorCorrelacion(original.getImagen()));
		labelFactor6.setForeground(blanco);
		labelFactor6.setFont(fuente);
		labelFactor6.setLayout(null);
		labelFactor6.setVisible(true);
		labelFactor6.setBounds(320, 520, 200, 20);
		panelPrinc.add(labelFactor6);
		
		//-----------------Label factor Imagen 7------------------//
		labelFactor7 = new JLabel();
		labelFactor7.setText("Factor: " + im7.getImagen().getFactorCorrelacion(original.getImagen()));
		labelFactor7.setForeground(blanco);
		labelFactor7.setFont(fuente);
		labelFactor7.setLayout(null);
		labelFactor7.setVisible(true);
		labelFactor7.setBounds(540, 520, 200, 20);
		panelPrinc.add(labelFactor7);
		
		
		//-------------Label Indicacion----------------//
		JLabel labelIndi = new JLabel();
		labelIndi.setText("Cerrar la ventana para volver a la anterior");
		labelIndi.setForeground(new Color(255,150,150));
		labelIndi.setBounds(250, 550 , 350, 20);
		labelIndi.setVisible(true);
		labelIndi.setFont(fuente);
		labelIndi.setLayout(null);
		panelPrinc.add(labelIndi);
		

		
		
}	
	

	
}
