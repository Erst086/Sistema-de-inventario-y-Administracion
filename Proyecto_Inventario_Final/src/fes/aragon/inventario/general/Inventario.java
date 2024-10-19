package fes.aragon.inventario.general;

import java.io.Serializable;

//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;

public class Inventario implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//private ObservableList<Inventario> datos = FXCollections.observableArrayList();
	private String nombreProc;
	private float cantidad; 
	private float precio;
	private boolean refrigerado;
	private String caducidad;

	public Inventario() {
		// TODO Auto-generated constructor stub
	}
	/*public Inventario(float cantidad) {
		// TODO Auto-generated constructor stub
		super();
		this.cantidad=cantidad;
	}*/
	
	public String getNombreproc() {
		return nombreProc;
	}

	public void setNombreproc(String nombreproc) {
		this.nombreProc = nombreproc;
	}

	public float getCantidad() {
		return cantidad;
	}

	public void setCantidad(float cantidad) {
		this.cantidad = cantidad;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public boolean isRefrigerado() {
		return refrigerado;
	}

	public void setRefrigerado(boolean refrigerado) {
		this.refrigerado = refrigerado;
	}

	public String getCaducidad() {
		return caducidad;
	}

	public void setCaducidad(String caducidad) {
		this.caducidad = caducidad;
	}

	/*public ObservableList<Inventario> getDatos() {
		return datos;
	}*/
	@Override
	public String toString() {
		return "Inventario [nombreProc=" + nombreProc + ", cantidad=" + cantidad + ", precio=" + precio
				+ ", refrigerado=" + refrigerado + ", caducidad=" + caducidad + "]";
	}

}
