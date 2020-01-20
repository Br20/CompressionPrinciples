package Clases;

import java.util.Hashtable;

public class Nodo implements Comparable<Nodo> {
	private Dupla dato;
	private Nodo menor;
	private Nodo mayor;
	
	public Nodo(Dupla dato,Nodo hijo1, Nodo hijo2) {
		this.dato=dato;
		this.menor=hijo1;
		this.mayor=hijo2;

	}
	
	public Nodo(Dupla dato) {
		this.dato=dato;
		this.mayor=null;
		this.menor=null;
	}


	public Dupla getDato() {
		return this.dato;
	}

	public Nodo getMenor() {
		return menor;
	}


	public Nodo getMayor() {
		return mayor;
	}
	
	public boolean cadenaValida(String cadena,int i) {
		if (cadena.length()!=0) {
			if (cadena.length()>i) {
				if (cadena.charAt(i)=='1') {
					if (this.mayor!=null) {
						i++;
						return this.menor.cadenaValida(cadena, i);
					}
				}
				else {
					if (this.menor!=null) {
						i++;
						return this.mayor.cadenaValida(cadena, i);
					}
				}
			}
			else
				if (this.esHoja()) 
					return true;
		}
		return false;
	}
	
	public int getSimbolo(String cadena, int i) {
		if (cadena.length()>i)
			if (cadena.charAt(i)=='1')
				return this.menor.getSimbolo(cadena, ++i);
			else		
				return this.mayor.getSimbolo(cadena, ++i);
		else
			if (this.esHoja()) 
				return this.getDato().getSimbolo();
			return -1;
	}

	
	public boolean esHoja() {
		if ((this.mayor==null) && (this.menor==null))
			return true;
		return false;
	}
	
	public void mostrar() {
		if (this.menor!=null)
			menor.mostrar();
		System.out.println(this.dato.getProbabilidad());
		if (this.mayor!=null)
			mayor.mostrar();
	}
	public void getCodificacion(Hashtable<Integer,String> machete, String provisorio) {
		if (!this.esHoja()) {
			if (menor!=null)
				menor.getCodificacion(machete, provisorio+"1");
			if (mayor!=null)
				mayor.getCodificacion(machete, provisorio+"0");
		}
		else
			machete.put(this.dato.getSimbolo(),provisorio);
		
	}

	@Override
	public int compareTo(Nodo arg0) {
		return (this.dato.getProbabilidad()-arg0.getDato().getProbabilidad());
	}
	

}
