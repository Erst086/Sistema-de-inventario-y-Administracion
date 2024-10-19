package fes.aragon.inventario.general;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Producto {

	private Distribuidor distribuidor = new Distribuidor();
	private ObservableList<Inventario> inventarios = FXCollections.observableArrayList();
	private String ProductoT;
	private String cantidad;
	private String bodega;
	private String Contacto;
	
	private static Producto instancia = new Producto();

	public Producto() {
		// TODO Auto-generated constructor stub
	}

	public static Producto getProducto() {
		return instancia;

	}

	public Distribuidor getDistribuidor() {
		return distribuidor;
	}

	public void setDistribuidor(Distribuidor distribuidor) {
		this.distribuidor = distribuidor;
	}

	public ObservableList<Inventario> getInventarios() {
		return inventarios;
	}

	public void setInventarios(ObservableList<Inventario> inventarios) {
		this.inventarios = inventarios;
	}

	public String getProductoT() {
		return ProductoT;
	}

	public void setProductoT(String productoT) {
		ProductoT = productoT;
	}

	public String getCantidad() {
		return cantidad;
	}

	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}

	public String getBodega() {
		return bodega;
	}

	public void setBodega(String bodega) {
		this.bodega = bodega;
	}

	public String getContacto() {
		return Contacto;
	}

	public void setContacto(String contacto) {
		Contacto = contacto;
	}
	@Override
	public String toString() {
		return "Producto [distribuidor=" + distribuidor + ", inventarios=" + inventarios + ", ProductoT=" + ProductoT
				+ ", cantidad=" + cantidad + ", bodega=" + bodega + ", Contacto=" + Contacto + "]";
	}



}
