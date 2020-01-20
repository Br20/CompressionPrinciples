package Clases;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Vector;

import javax.swing.*;


public class Interfaz {
	
	
	private JFrame ventana;
	private PanelconFondo panelImOriginal, panelPrincipal, panelImMas, panelImMenos;
	private boolean cargadaOri, cargadaMenos, cargadaMas;
	private JPanel panelBorde0, panelBorde1, panelBorde2;
	private JLabel labelMediaOri, labelMediaMas, labelMediaMenos, labelDesvioOri;
	private JLabel labelImOriginal, labelImMas, labelDesvioMas, labelDesvioMenos, labelImMenos;
	private JButton botonHistOri, botonHistMenos, botonHistMas, botonCodifOri, botonCodifMas, botonCodifMenos, botonCargar, botonTodas;
	private Color colorFondo = new Color(50,50,50);
	private Color colorFuente = new Color(255,255,255);
	private Color colorImagen = new Color(150,150,150);
	private Color colorMarco = new Color(0,0,0);
	private Font fuente = new Font("Calibri",0,14);
	Hashtable <Double, Imagen> imagenes = new Hashtable <Double, Imagen>();
	
	
	public Interfaz() {
		
		Imagen im0 = new Imagen("/photos/Will(Original).bmp");
		Imagen im1 = new Imagen("/photos/Will_1.bmp");
		Imagen im2 = new Imagen("/photos/Will_2.bmp");
		Imagen im3 = new Imagen("/photos/Will_3.bmp");
		Imagen im4 = new Imagen("/photos/Will_4.bmp");
		Imagen im5 = new Imagen("/photos/Will_5.bmp");
		Imagen im6 = new Imagen("/photos/Will_6.bmp");
		Imagen im7 = new Imagen("/photos/Will_7.bmp");
		imagenes.put(im0.getFactorCorrelacion(im1.getImagen()), im1);
		imagenes.put(im0.getFactorCorrelacion(im2.getImagen()), im2);
		imagenes.put(im0.getFactorCorrelacion(im3.getImagen()), im3);
		imagenes.put(im0.getFactorCorrelacion(im4.getImagen()), im4);
		imagenes.put(im0.getFactorCorrelacion(im5.getImagen()), im5);
		imagenes.put(im0.getFactorCorrelacion(im6.getImagen()), im6);
		imagenes.put(im0.getFactorCorrelacion(im7.getImagen()), im7);
		
		//-----------Ventana Principal--------------//
		
		ventana = new JFrame("Trabajo Practico Especial - Teoria de la Información");
		ventana.setVisible(true);
		ventana.setSize(800, 600);
		ventana.setVisible(true);
		ventana.setResizable(false);
		ventana.setLocationRelativeTo(null);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		//-------------Panel Principal--------------//
				
		
		panelPrincipal = new PanelconFondo();
		panelPrincipal.setBackground(colorFondo);
		panelPrincipal.setLayout(null);
		ventana.add(panelPrincipal);
		
		//---------Label Imagen Original----------//
		
		labelImOriginal = new JLabel();				
		labelImOriginal.setBounds(350,10,100,20);	
		labelImOriginal.setFont(fuente);					
		labelImOriginal.setLayout(null);
		labelImOriginal.setForeground(colorFuente);
		labelImOriginal.setVisible(true); 									
		labelImOriginal.setBorder(BorderFactory.createTitledBorder(""));	
		labelImOriginal.setText(" Imagen Original");						
		panelPrincipal.add(labelImOriginal);
		
		
		//------------Panel imagen original--------------//
		panelImOriginal = new PanelconFondo();
		panelBorde0 = new JPanel();
		panelBorde0.setBounds(316, 36, 168, 208);
		panelImOriginal.setBounds(320, 40, 160, 200);
		panelBorde0.setVisible(true);
		panelImOriginal.setVisible(true);
		panelBorde0.setBackground(colorMarco);
		panelImOriginal.setBackground(colorImagen);
		panelBorde0.setLayout(null);
		panelImOriginal.setLayout(null);
		panelPrincipal.add(panelImOriginal);
		panelPrincipal.add(panelBorde0);
		panelImOriginal.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				if (panelImOriginal.getImagen() != null){
					PanelconFondo pan = new PanelconFondo();
					Imagen aux = panelImOriginal.getImagen();
					pan.setImage(aux.getNombre());
					pan.setVisible(true);
					pan.setLayout(null);
					JFrame nuevaVent = new JFrame(aux.getNombre());
					nuevaVent.add(pan);
					nuevaVent.setVisible(true);
					nuevaVent.setLayout(null);
					nuevaVent.setSize(aux.getAncho(), aux.getAlto());
					pan.setSize(aux.getAncho(), aux.getAlto());
					nuevaVent.setLocationRelativeTo(null);
					nuevaVent.setResizable(false);
				}
			}
		});
		
		
		
		//---------Label imagen Mas parecida ----------//
		labelImMas = new JLabel(); 
		labelImMas.setBounds(135, 150, 130, 20);							
		labelImMas.setFont(fuente);					
		labelImMas.setForeground(colorFuente);
		labelImMas.setLayout(null);										
		labelImMas.setVisible(true); 									
		labelImMas.setBorder(BorderFactory.createTitledBorder(""));		
		labelImMas.setText(" Imagen más parecida");						
		panelPrincipal.add(labelImMas);
		
		
		//------------Panel imagen mas parecida--------------//
		panelImMas = new PanelconFondo();
		panelBorde1 = new JPanel();
		panelBorde1.setBounds(116, 176, 168, 208);
		panelImMas.setBounds(120, 180, 160, 200);	
		panelBorde1.setVisible(true);
		panelImMas.setVisible(true);
		panelBorde1.setBackground(colorMarco);
		panelImMas.setBackground(colorImagen);	
		panelBorde1.setLayout(null);
		panelImMas.setLayout(null);
		panelPrincipal.add(panelImMas);
		panelPrincipal.add(panelBorde1);
		panelImMas.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				if (panelImMas.getImagen() != null){
					PanelconFondo pan = new PanelconFondo();
					Imagen aux = panelImMas.getImagen();
					pan.setImage(aux.getNombre());
					pan.setVisible(true);
					pan.setLayout(null);
					JFrame nuevaVent = new JFrame(aux.getNombre());
					nuevaVent.add(pan);
					nuevaVent.setVisible(true);
					nuevaVent.setLayout(null);
					nuevaVent.setSize(aux.getAncho(), aux.getAlto());
					pan.setSize(aux.getAncho(), aux.getAlto());
					nuevaVent.setLocationRelativeTo(null);
					nuevaVent.setResizable(false);
					
				}
			}
		});
		
		
		//---------Label imagen menos parecida ----------//
		labelImMenos = new JLabel();
		labelImMenos.setBounds(528,150,144,20);	
		labelImMenos.setFont(fuente);					
		labelImMenos.setForeground(colorFuente);
		labelImMenos.setLayout(null);										
		labelImMenos.setVisible(true); 				
		labelImMenos.setBorder(BorderFactory.createTitledBorder(""));		
		labelImMenos.setText(" Imagen menos parecida");						
		panelPrincipal.add(labelImMenos);
		
		
		//------------Panel imagen menos parecida--------------//
		panelImMenos = new PanelconFondo();
		panelBorde2 = new JPanel();
		panelBorde2.setBounds(516, 176, 168, 208);
		panelImMenos.setBounds(520, 180, 160, 200);	
		panelBorde2.setVisible(true);
		panelImMenos.setVisible(true);
		panelBorde2.setBackground(colorMarco);
		panelImMenos.setBackground(colorImagen);	
		panelBorde2.setLayout(null);
		panelImMenos.setLayout(null);
		panelPrincipal.add(panelImMenos);
		panelPrincipal.add(panelBorde2);
		panelImMenos.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				if (cargadaMenos){
					PanelconFondo pan = new PanelconFondo();
					Imagen aux = panelImMenos.getImagen();
					pan.setImage(aux.getNombre());
					pan.setVisible(true);
					pan.setLayout(null);
					JFrame nuevaVent = new JFrame(aux.getNombre());
					nuevaVent.add(pan);
					nuevaVent.setVisible(true);
					nuevaVent.setLayout(null);
					nuevaVent.setSize(aux.getAncho(), aux.getAlto());
					pan.setSize(aux.getAncho(), aux.getAlto());
					nuevaVent.setLocationRelativeTo(null);
					nuevaVent.setResizable(false);
				}
			}
		});
		
		
				
		//----------Boton cargar imagenes--------------//
		botonCargar = new JButton();
		botonCargar.setText("Cargar Imagenes");
		botonCargar.setBounds(30, 30, 150 , 30);
		botonCargar.setEnabled(true);
		botonCargar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				if (botonCargar.isEnabled()){
					botonCargarClicked();
				}
			}
		});
		panelPrincipal.add(botonCargar);
		
		//-----------Ver todas las imagenes-------------//
		botonTodas = new JButton();
		botonTodas.setText("Ver imagenes");
		botonTodas.setEnabled(false);
		botonTodas.setBounds(615,30,150,30);
		botonTodas.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				if (botonTodas.isEnabled()){
					new InterfazSec(imagenes);
					ventana.dispose();
				}
			}
		});
		panelPrincipal.add(botonTodas);
		
		
		//-----------Boton Histograma Original-----------//
		botonHistOri = new JButton();
		botonHistOri.setText("Histograma");
		botonHistOri.setBounds(325, 250, 150 , 30);
		botonHistOri.setEnabled(false);
		botonHistOri.setVisible(true);
		botonHistOri.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				if (cargadaOri)
					histogramaOriginal();
			}
		});
		panelPrincipal.add(botonHistOri);
		
		
		//-----------Boton Histograma Menos-----------//
		botonHistMenos = new JButton();
		botonHistMenos.setText("Histograma");
		botonHistMenos.setBounds(525, 390, 150 , 30);
		botonHistMenos.setEnabled(false);
		botonHistMenos.setVisible(true);
		botonHistMenos.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				if (cargadaMenos)	
					histogramaMenos();
			}
		});
		panelPrincipal.add(botonHistMenos);
				
		//-----------Boton Histograma Mas-----------//
		botonHistMas = new JButton();
		botonHistMas.setText("Histograma");
		botonHistMas.setBounds(125, 390, 150 , 30);
		botonHistMas.setEnabled(false);
		botonHistMas.setVisible(true);
		botonHistMas.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				if (cargadaMas)
					histogramaMas();
			}
		});
		panelPrincipal.add(botonHistMas);
		
		
		//-----------Boton Obtener Codificacion Original------------//
		
		botonCodifOri = new JButton();
		botonCodifOri.setText("Codificar");
		botonCodifOri.setEnabled(false);
		botonCodifOri.setVisible(true);
		botonCodifOri.setBounds(325, 290, 150, 30);
		botonCodifOri.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				if (cargadaOri){
					ventana.dispose();
					new InterfazTer(panelImOriginal.getImagen());
				}
			}
		});
		panelPrincipal.add(botonCodifOri);
		
		
		//-----------Boton Obtener Codificacion Mas------------//
		
		botonCodifMas = new JButton();
		botonCodifMas.setText("Codificar");
		botonCodifMas.setEnabled(false);
		botonCodifMas.setVisible(true);
		botonCodifMas.setBounds(125, 430, 150, 30);
		botonCodifMas.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				if (cargadaMas){
					ventana.dispose();
					new InterfazTer(panelImMas.getImagen());
				}
					
			}
		});
		panelPrincipal.add(botonCodifMas);
		
				
		//-----------Boton Obtener Codificacion Menos------------//
		
		botonCodifMenos = new JButton();
		botonCodifMenos.setText("Codificar");
		botonCodifMenos.setEnabled(false);
		botonCodifMenos.setVisible(true);
		botonCodifMenos.setBounds(525, 430, 150, 30);
		botonCodifMenos.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				if (cargadaMenos){
					ventana.dispose();
					new InterfazTer(panelImMenos.getImagen());
				}
			}
		});
		panelPrincipal.add(botonCodifMenos);		
		
		
		
		//---------Label Media Original----------------//
		labelMediaOri = new JLabel();
		labelMediaOri.setLayout(null);
		labelMediaOri.setText("Media: ");
		labelMediaOri.setForeground(colorFuente);
		labelMediaOri.setBounds(328, 330, 145, 20);
		labelMediaOri.setVisible(true);	
		labelMediaOri.setFont(fuente);
		panelPrincipal.add(labelMediaOri);
		
		//------------Label Desvío Original-----------------//
		labelDesvioOri = new JLabel();
		labelDesvioOri.setLayout(null);
		labelDesvioOri.setText("Desvío: ");
		labelDesvioOri.setForeground(colorFuente);
		labelDesvioOri.setBounds(328, 360, 145, 20);
		labelDesvioOri.setVisible(true);	
		labelDesvioOri.setFont(fuente);
		panelPrincipal.add(labelDesvioOri);
		
		
		//---------Label Media Menos----------------//
		labelMediaMenos = new JLabel();
		labelMediaMenos.setLayout(null);
		labelMediaMenos.setText("Media: ");
		labelMediaMenos.setForeground(colorFuente);
		labelMediaMenos.setBounds(525, 470, 145, 20);
		labelMediaMenos.setVisible(true);	
		labelMediaMenos.setFont(fuente);
		panelPrincipal.add(labelMediaMenos);
				
				
		//------------Label Desvio Menos----------------//
				
		labelDesvioMenos = new JLabel();
		labelDesvioMenos.setLayout(null);
		labelDesvioMenos.setText("Desvio: ");
		labelDesvioMenos.setForeground(colorFuente);
		labelDesvioMenos.setBounds(525, 500, 145, 20);
		labelDesvioMenos.setVisible(true);	
		labelDesvioMenos.setFont(fuente);
		panelPrincipal.add(labelDesvioMenos);
		
		
		//-----------Label Media Mas---------------//
		labelMediaMas = new JLabel();
		labelMediaMas.setLayout(null);
		labelMediaMas.setText("Media: ");
		labelMediaMas.setForeground(colorFuente);
		labelMediaMas.setBounds(125, 470, 145, 20);
		labelMediaMas.setVisible(true);	
		labelMediaMas.setFont(fuente);
		panelPrincipal.add(labelMediaMas);
		
		//------------Label Desvio Menos----------------//
		
		labelDesvioMas = new JLabel();
		labelDesvioMas.setLayout(null);
		labelDesvioMas.setText("Desvio: ");
		labelDesvioMas.setForeground(colorFuente);
		labelDesvioMas.setBounds(125, 500, 145, 20);
		labelDesvioMas.setVisible(true);	
		labelDesvioMas.setFont(fuente);
		panelPrincipal.add(labelDesvioMas);
		
		
		//-------------Label Indicacion----------------//
		JLabel labelIndi = new JLabel();
		labelIndi.setText("Presionar sobre la imagen para visualizarla en tamaño real");
		labelIndi.setForeground(new Color(255,150,150));
		labelIndi.setBounds(250, 550 , 350, 20);
		labelIndi.setVisible(true);
		labelIndi.setFont(fuente);
		labelIndi.setLayout(null);
		panelPrincipal.add(labelIndi);
			
	}
	
	
	
	//--------Metodo Boton Cargar--------------//
	public void botonCargarClicked(){
		Vector<Double> factores = new Vector<Double>(imagenes.keySet());
		Collections.sort(factores);
		Collections.reverse(factores);
		botonCargar.setEnabled(false);
		cargadaMas = true;
		cargadaMenos = true;
		cargadaOri = true;
		panelImOriginal.setImage("/photos/Will(Original).bmp");
		panelImMas.setImage(imagenes.get(factores.elementAt(0)));
		panelImMenos.setImage(imagenes.get(factores.elementAt(factores.size()-1)));
		labelDesvioOri.setText("Desvio: " + panelImOriginal.getImagen().getDesvio());
		labelMediaOri.setText("Media: " + panelImOriginal.getImagen().getMedia());
		labelDesvioMas.setText("Desvio: " + panelImMas.getImagen().getDesvio());
		labelMediaMas.setText("Media: " + panelImMas.getImagen().getMedia());
		labelDesvioMenos.setText("Desvio: " + panelImMenos.getImagen().getDesvio());
		labelMediaMenos.setText("Media: " + panelImMenos.getImagen().getMedia());
		botonCodifMas.setEnabled(true);
		botonCodifMenos.setEnabled(true);
		botonCodifOri.setEnabled(true);
		botonHistMas.setEnabled(true);
		botonHistMenos.setEnabled(true);
		botonHistOri.setEnabled(true);
		botonTodas.setEnabled(true);
		ventana.repaint();
	}
	
	
	//-----------Metodo Histograma Original-----------//
	public void histogramaOriginal(){
		panelImOriginal.getImagen().mostrarHistograma();
	}
	
	//-----------Metodo Histograma Mas-----------//
	public void histogramaMas(){
		panelImMas.getImagen().mostrarHistograma();
	}
	
	
	
	//----------Metodo Histograma Menos--------------//
	public void histogramaMenos(){
		panelImMenos.getImagen().mostrarHistograma();
	}
		
		
	
	//---------------Main---------------//
	public static void main(String[] args) {
		new Interfaz();
	}
	
	
	
}
