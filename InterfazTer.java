package Clases;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Collections;
import java.util.Hashtable;
import java.util.PriorityQueue;
import java.util.Vector;
import javax.swing.*;
import java.io.*;



public class InterfazTer {
	
	private JFramee ventana;
	private JPanel panelPrinc;
	private JLabel labelName, status ;
	private JTextArea codigo, codigo2;
	private JFileChooser uploader_Arch;
	private JButton botonComprimir, botonDescomprimir;
	private Color blanco = new Color(255,255,255);

	
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
	
	
	
	
	public InterfazTer(Imagen aCodificar){
		
		
		
		//----------------------Ventana Principal-------------------------//
		
		ventana = new JFramee("Trabajo Practico Especial - Teoria de la Informacion");
		ventana.setSize(600,500);
		ventana.setLocationRelativeTo(null);
		ventana.setLayout(null);
		ventana.setVisible(true);
		ventana.setResizable(false);
		ventana.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
		//--------------------Panel Principal------------------------//
		panelPrinc = new JPanel();
		panelPrinc.setBackground(new Color(50,50,50));
		panelPrinc.setVisible(true);
		panelPrinc.setLayout(null);
		panelPrinc.setSize(800,600);
		ventana.add(panelPrinc);
		
		//--------------------Label Nombre---------------------------//
		labelName = new JLabel();
		labelName.setText(aCodificar.getNombre());
		labelName.setFont(new Font("Calibri",0,20));
		labelName.setBounds(165, 10, 350, 30);
		labelName.setForeground(new Color(255,255,255));
		labelName.setVisible(true);
		labelName.setLayout(null);
		panelPrinc.add(labelName);
		
		
		//--------------------Boton Comprimir-----------------------//
		
		botonComprimir = new JButton();
		botonComprimir.setText("Comprimir imagen");
		botonComprimir.setFont(new Font ("Calibri",Font.BOLD,14));
		botonComprimir.setBounds(350,180,180,30);
		botonComprimir.setEnabled(true);
		botonComprimir.setVisible(true);
		botonComprimir.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				botonDescomprimir.setEnabled(false);
				status.setVisible(true);
				uploader_Arch = new JFileChooser("");
				if(uploader_Arch.showSaveDialog(ventana) == JFileChooser.APPROVE_OPTION)
						try {
							guardarImagenComp(uploader_Arch.getSelectedFile().getAbsolutePath()+".txt",aCodificar);
							botonDescomprimir.setEnabled(true);
						} catch (IOException e) {
							e.printStackTrace();
						}
				botonDescomprimir.setEnabled(true);
				status.setVisible(false);
			}
		});
		panelPrinc.add(botonComprimir);
		
		
		//---------------------Boton Descomprimir-------------//
		
		botonDescomprimir = new JButton();
		botonDescomprimir.setText("Descomprimir Imagen");
		botonDescomprimir.setFont(new Font("Calibri",Font.BOLD,14));
		botonDescomprimir.setEnabled(true);
		botonDescomprimir.setVisible(true);
		botonDescomprimir.setBounds(350,250,180,30);
		botonDescomprimir.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				if (botonDescomprimir.isEnabled()){
					uploader_Arch = new JFileChooser("");
					if(uploader_Arch.showOpenDialog(ventana) == JFileChooser.APPROVE_OPTION)
							try {
								visualizar(uploader_Arch.getSelectedFile().getAbsolutePath());
							} catch (FileNotFoundException e) {
								e.printStackTrace();
							} catch (IOException e) {
								e.printStackTrace();
							}
				}
			}
		});
		panelPrinc.add(botonDescomprimir);
		
		
		
		//---------------------Label Estatus-------------------//
		
		status = new JLabel();
		status.setBounds(360,215,180,30);
		status.setText("comprimiendo...");
		status.setFont(new Font("Calibri",0,16));
		status.setForeground(new Color (255,255,255));
		status.setVisible(false);
		panelPrinc.add(status);
		
		
		
		//-------------------Caja de texto de codigo--------------//
		codigo = new JTextArea();
		codigo2 = new JTextArea();
		codigo.setFont(new Font("Calibri",0,15));
		codigo2.setFont(new Font ("Calibri",0,15));
		codigo.setVisible(true);
		codigo2.setVisible(true);
		codigo.setEditable(false);
		codigo2.setEditable(false);
		codigo.setForeground(blanco);
		codigo2.setForeground(blanco);
		codigo2.setBackground(new Color (100,100,100));
		codigo.setBackground(new Color(100,100,100));
		codigo2.setBounds(210, 60, 100, 350);
		codigo.setBounds(80, 60, 100, 350);
		codigo.setBorder(BorderFactory.createTitledBorder(""));
		codigo2.setBorder(BorderFactory.createTitledBorder(""));
		Hashtable<Integer, String> codif = aCodificar.getCodificacion(aCodificar.getDistribucion());
		Vector<Integer> simbolos = new Vector<Integer> (codif.keySet());
		Collections.sort(simbolos);
		codigo.append("Simbolo\n"+"\n");
		codigo2.append("Codificacion\n"+"\n");
		for (int i = 0; i < simbolos.size() ; i++){
			codigo.append(" " + simbolos.elementAt(i)+":   \n");
			codigo2.append(" " + codif.get(simbolos.elementAt(i)) + "\n");
		}
		panelPrinc.add(codigo2);
		panelPrinc.add(codigo);
		panelPrinc.repaint();

	}	


	
	
	private void guardarImagenComp(String ruta, Imagen aComprimir) throws IOException{
		
		DataOutputStream dOS = new DataOutputStream(new FileOutputStream(ruta));
		dOS.writeInt(aComprimir.getAncho());
		dOS.writeInt(aComprimir.getAlto());
		Hashtable <Integer,Integer> distr = aComprimir.getDistribucionHash();
   		Vector <Integer> simb = new Vector<Integer>(distr.keySet());
		Collections.sort(simb);
		for (int i = 0; i < simb.size(); i++){
			dOS.writeInt(simb.elementAt(i));
			dOS.writeInt(distr.get(simb.elementAt(i)));
		}
		dOS.writeChars(aComprimir.getImagenComprimida());
		dOS.close();
	}
	
	
	private Imagen Descomprimir(String ruta) throws IOException{
		DataInputStream dIS = new DataInputStream(new FileInputStream(ruta));
		int ancho = dIS.readInt();
		int alto =dIS.readInt();
		int totalPix = ancho * alto;
		int simbolo, frecuencia, suma = 0;
		Vector<Dupla> distrib = new Vector<Dupla>();
		while (suma<totalPix){
			simbolo = dIS.readInt();
			frecuencia = dIS.readInt();
			suma = suma + frecuencia;
			distrib.add(new Dupla(frecuencia,simbolo));
		}
		PriorityQueue<Nodo> cola = new PriorityQueue<Nodo>();
		for (int i=0; i<distrib.size();i++)
			cola.add(new Nodo(distrib.get(i)));
		while (cola.size()!=1) {
			Nodo aux1 = cola.poll() ,aux2 = cola.poll();
			Nodo aux3 = new Nodo(new Dupla(aux1.getDato().getProbabilidad()+aux2.getDato().getProbabilidad(),0),aux1,aux2);
			cola.add(aux3);
		}
		Nodo arbol = cola.poll();
		Hashtable<Integer, String> codrigo =new Hashtable<>();
		arbol.getCodificacion(codrigo,"");
		
		
		BufferedImage imDescomp = new BufferedImage(ancho,alto,BufferedImage.TYPE_INT_ARGB);
		Imagen im = new Imagen(imDescomp);
		
		StringBuilder x =new StringBuilder();
		while (dIS.available()!=0) {
			x.append(dIS.readChar());
		}
		String imagenCod= x.toString();
		dIS.close();

		int k = 0;
		int contb =0;
		String cadena = "";
		char a = imagenCod.charAt(k);
		for(int i = 0; i < alto; i++) {
			for(int j = 0; j < ancho; j++) {
				boolean pixelseteado=false;
				while (!pixelseteado){
					while ((contb<16)&&(!pixelseteado)) {
							if ((a&'\u8000')==32768) {
								cadena=cadena+"1";
								if (arbol.cadenaValida(cadena, 0)){
									int rgb = arbol.getSimbolo(cadena, 0);
									Color tono = new  Color(rgb,rgb,rgb);
									im.getImagen().setRGB(j, i,tono.getRGB());
									cadena = "";
									pixelseteado=true;
								}
							}
							else {
								cadena=cadena+'0';
								if (arbol.cadenaValida(cadena, 0)) {
									int rgb = arbol.getSimbolo(cadena, 0);
									Color tono = new  Color(rgb,rgb,rgb);
									im.getImagen().setRGB(j, i,tono.getRGB());
									cadena = "";
									pixelseteado=true;
								}
							}
							a = (char)(a<<1);
							contb++;
					}
					if (contb>=16) {
						k++;
						contb=0;
						a=imagenCod.charAt(k);
					}
				}


			}

		}
		

		return im;
	}
    
	public void visualizar(String ruta) throws IOException {
		Imagen imagen = this.Descomprimir(ruta);
		JFrame imagenDescomprimida = new JFrame("Imagen Descomprimida");
		PanelconFondo panelimg = new PanelconFondo();
		imagenDescomprimida.setSize(imagen.getAncho(),imagen.getAlto());
		imagenDescomprimida.setVisible(true);
		imagenDescomprimida.setLocationRelativeTo(null);
		imagenDescomprimida.setResizable(false);
		imagenDescomprimida.setLayout(null);
		panelimg.setSize(imagen.getAncho(),imagen.getAlto());
		panelimg.setVisible(true);
		panelimg.setImage(imagen);
		panelimg.setLayout(null);
		imagenDescomprimida.add(panelimg);
	}

	
}
