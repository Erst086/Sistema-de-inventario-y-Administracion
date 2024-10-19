package fes.aragon.model.archive;

import java.io.Serializable;
import java.util.ArrayList;

import fes.aragon.inventario.general.Distribuidor;
import fes.aragon.inventario.general.Inventario;

public class ProductoArchivo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Distribuidor distribuidor = new Distribuidor();
	private ArrayList<Inventario> inventarios = new ArrayList<>();
	private String ProductoT;
	private String cantidad;
	private String bodega;
	private String Contacto;

	private static ProductoArchivo instancia = new ProductoArchivo();

	public ProductoArchivo() {
		// TODO Auto-generated constructor stub

	}

	public static ProductoArchivo getProducto() {
		return instancia;

	}

	public Distribuidor getDistribuidor() {
		return distribuidor;
	}

	public void setDistribuidor(Distribuidor distribuidor) {
		this.distribuidor = distribuidor;
	}

	public void setInventarios(ArrayList<Inventario> inventarios) {
		this.inventarios = inventarios;
	}

	public ArrayList<Inventario> getInventarios() {
		return inventarios;
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
