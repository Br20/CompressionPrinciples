package Clases;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Collections;
import java.util.Hashtable;
import java.util.PriorityQueue;
import java.util.Vector;
import javax.imageio.ImageIO;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class Imagen {

	private BufferedImage image = null;
	private String nombre = null;
	private Hashtable<Integer, Integer> distr;
	
	
	//CONSTRUCTOR DE LA IMAGEN
		public Imagen(String ruta){
			try{
				this.image = ImageIO.read(getClass().getResource(ruta));
				this.nombre = ruta;
				this.obtenerDistribucion();
			}catch (Exception e){
					e.printStackTrace();
			}
		}
	
	//CONSTRUCTOR2 DE LA IMAGEN
		public Imagen(BufferedImage im){
			this.image = im;
			distr = null;
		}
	
	
	//DEVUELVE LA IMAGEN CONTENIDA
	public BufferedImage getImagen(){
		return image;
	}
	
	//DEVUELVE EL ANCHO DE LA IMAGEN
	public int getAncho(){
		return image.getWidth();
	}
	
	
	//DEVUELVE EL ALTO DE LA IMAGEN
	
	public int getAlto(){
		return image.getHeight();
	}
	
	
	//OBTIENE LA DISTRIBUCION DE LA IMAGEN
	public void obtenerDistribucion(){
		int pixel;
		distr = new Hashtable<Integer, Integer>();
		for(int i = 0; i < image.getHeight(); i++)
			for(int j = 0; j < image.getWidth(); j++){
				pixel = (new Color(image.getRGB(j,i))).getRed();				
				if (distr.containsKey(pixel)){
					distr.put(pixel, distr.get(pixel)+1);
				}else
					distr.put(pixel, new Integer(1));
			
			}
	}
	
	//DEVUELVE LA DISTRIBUCION DE LA IMAGEN EN HASHTABLE
	public Hashtable <Integer,Integer> getDistribucionHash(){
		return this.distr;
	}
	
	
	//OBTIENE LA DISTRIBUCION EN UN VECTOR DE DUPLAS
	public Vector<Dupla> getDistribucion(){
		if (this.distr != null){
			Vector<Dupla> distrib = new Vector<Dupla>();
			Vector<Integer> aux = new Vector<Integer>(distr.keySet());
			for (int i= 0; i < aux.size(); i++)
				distrib.add(new Dupla(distr.get(aux.get(i)), aux.get(i)));
			return distrib;
		}
		return null;
	}
	
	
	//devuelve una Hashtable con la codificacion de los pixeles a cadena de bits
	public Hashtable<Integer, String> getCodificacion(Vector<Dupla> datos) {
		if (datos !=null){
			PriorityQueue<Nodo> cola = new PriorityQueue<Nodo>();
			for (int i=0; i<datos.size();i++)
				cola.add(new Nodo(datos.get(i)));
			while (cola.size()!=1) {
				Nodo aux1 = cola.poll() ,aux2 = cola.poll();
				Nodo aux3 = new Nodo(new Dupla(aux1.getDato().getProbabilidad()+aux2.getDato().getProbabilidad(),0),aux1,aux2);
				cola.add(aux3);
			}
			Hashtable<Integer, String> codigo =new Hashtable<>();
			cola.poll().getCodificacion(codigo,"");
			return codigo;
		}
		return null;
	}
	
	//devuelve un String con la imagen codificada (y comprimida) a nivel bit
	
	public String getImagenComprimida() {
		if (this.distr != null){
			String imagenEnHuffman = new String();
			int contador = 0;
			char caracter ='\u0000';
			Hashtable<Integer, String> codificacion = this.getCodificacion(this.getDistribucion());
			for(int i = 0; i < image.getHeight(); i++) {
				for(int j = 0; j < image.getWidth(); j++){
					String codigocolor = new String(codificacion.get((new Color(image.getRGB(j,i))).getRed()));
					int size = 0;
					while (size<codigocolor.length()) {
						if (contador<16) {
						
							caracter = (char)(caracter<<1);
							if (codigocolor.charAt(size)=='1') {
								caracter = (char)(caracter | '\u0001');
							}
							contador++;
							size++;
						}
						else {
							imagenEnHuffman = imagenEnHuffman + caracter;
							caracter = '\u0000';
							contador = 0;
						}
					}
				}
			}
			if (contador<16) {
				for (int k= contador; k<16;k++) {
					caracter = (char)(caracter<<1);
				}
			}
			imagenEnHuffman=imagenEnHuffman+caracter;	
			return imagenEnHuffman;
		}
		return null;
	}
	
	
	
	//DEVUELVE CANTIDAD DE TONALIDADES DE GRIS
	public int getCantValores(){
		if (distr != null)
			return distr.size();	
		else return 0;
	}
	
	//DEVUELVE LA FRECUENCIA DE I
	  public int getFrecuencia(int i){
		if (distr != null)
			if (distr.containsKey(i))
				return distr.get(i);
		return 0;
	}
	  
	//DEVUELVE LA PROBABILIDAD DE I
	  public double getProbabilidad(int i){
			if (distr != null)
				if (distr.containsKey(i))
					return (double)distr.get(i) / this.getCantPixeles();
			return 0;
		}
	  
	 //DEVUELVE LA CANTIDAD DE PIXELES DE LA IMAGEN
	  public int getCantPixeles(){
		  return image.getHeight() * image.getWidth();
	  }
	
	//DEVUELVE EL NOMBRE DEL ARCHIVO
	public String getNombre(){
		if (nombre != null)
			return this.nombre;
		else return "";
	}
	
	
	//MUESTRA LA DISTRIBUCION DE PROBABILIDADES
	public void showDistribucion(){
		Vector<Integer> valores =  new Vector<Integer>(this.distr.keySet());
		int val;
		for (int i = 0; i < valores.size(); i++){
			val = valores.elementAt(i);
			System.out.println("Valor: " + val + "  -->  " + distr.get(val));
		}
	}
	
	
	//CALCULA EL FACTOR DE CORRELACION CON LA IMAGEN PASADA POR PARAMETRO
	public double getFactorCorrelacion (BufferedImage imagenDos){
		int pixA, pixB;
		int tiradas = 0;
		float cov = 0;
		float mediaA = 0, mediaB = 0, mediaAB = 0;
		float totalA = 0, totalB = 0, totalAB = 0;
		float mediaCuadA = 0, mediaCuadB = 0;
		float totalCuadA = 0, totalCuadB = 0;
		double varA, varB;
		double factor;
		for (int i = 0; i < image.getHeight();i++)
			for (int j = 0; j < image.getWidth(); j++){
				pixA = (new Color(image.getRGB(j,i))).getRed();
				pixB = (new Color(imagenDos.getRGB(j,i))).getRed();
				totalA = totalA + pixA;
				totalB = totalB + pixB;
				totalAB = totalAB + (pixA * pixB);
				totalCuadA = totalCuadA + (pixA * pixA);
				totalCuadB = totalCuadB + (pixB * pixB);
				tiradas++;
			}
		mediaA = (float) totalA / tiradas;
		mediaB = (float) totalB / tiradas;
		mediaAB = (float) totalAB / tiradas;
		cov = mediaAB - (mediaA * mediaB);
		mediaCuadA = (float)totalCuadA / tiradas;
		mediaCuadB = (float)totalCuadB / tiradas;
		varA = Math.sqrt(mediaCuadA - (mediaA * mediaA));
		varB = Math.sqrt(mediaCuadB - (mediaB * mediaB));
		factor = cov / (varA * varB);
		return factor;
	}

	
	//MUESTRA EL HISTOGRAMA DE LA IMAGEN (TONOS DE GRIS, FRECUENCIA)
	public void mostrarHistograma(){

		DefaultCategoryDataset dataset;
		dataset = new DefaultCategoryDataset();
		Vector<Integer> val = new Vector<Integer>(distr.keySet());
		Collections.sort(val);
		for(int i=0; i < this.getCantValores(); i++)
			dataset.setValue(this.getFrecuencia(val.elementAt(i)),"Frecuencias de los tonos",val.elementAt(i));
		JFreeChart chart = ChartFactory.createBarChart(this.getNombre(), "Simbolos ", "Frecuencia en la imagen", dataset, PlotOrientation.VERTICAL, true, true, true);
		ChartFrame frame = new ChartFrame("Histograma", chart);
		//frame.setLocationRelativeTo(c); Para ubicarlo en realcion con algo
		frame.pack();
		frame.setVisible(true);
	}
	
	
	//DEVUELVE LA MEDIA DE LA DISTRIBUCION
	public double getMedia(){
		int pixel = 0;
		for (int i = 0; i < image.getHeight();i++)
			for (int j = 0; j < image.getWidth(); j++)
				pixel = pixel + (new Color(image.getRGB(j,i))).getRed();
		int cantPix = image.getHeight() * image.getWidth();
		return (double) pixel / (double) cantPix;
	}	
	
	
	//DEVUELVE EL DESVIO DE LA DISTRIBUCION
	public double getDesvio(){
		double media = this.getMedia();
		int pixel;
		double sumatoria = 0;
		for (int i = 0; i < image.getHeight();i++)
			for (int j = 0; j < image.getWidth(); j++){
				pixel = (new Color(image.getRGB(j,i))).getRed();
				sumatoria = sumatoria + Math.pow(((double)pixel - media),2);
			}
		int cantPix = image.getHeight() * image.getWidth();
		return Math.sqrt(sumatoria / cantPix);
				
	}
	
}
