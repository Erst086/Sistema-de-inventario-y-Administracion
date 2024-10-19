package fes.aragon.inventario.general;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Productos {
	private static Productos instancia = new Productos();
	private ObservableList<Producto> grupoProductos = FXCollections.observableArrayList();
	private boolean modificarProducto = false;
	private int indice = -1;
	private int indiceInventario = -1;

	private Productos() {
		/*Producto p = new Producto();
		p.setProductoT("Dato1");
		p.setBodega("Dato2");
		p.setCantidad("12345");
		p.setContacto("asdasg@gmail.com");

		Distribuidor d = new Distribuidor();
		d.setNombre("panchito");
		d.setEmpresa("coca cola");
		d.setRepartidor("Juanito");
		d.setCorreo("daasd@gmail.com");
		d.setRfc("ADADAFAFAF445");
		d.setTelefono("55482513");
		p.setDistribuidor(d);
		Inventario inv = new Inventario();
		inv.setNombreproc("Demo 1");
		inv.setCantidad(12.22f);
		inv.setCaducidad("Menor a 4 meses");
		inv.setRefrigerado(true);
		inv.setPrecio(12.36f);

		p.getInventarios().add(inv);
		this.grupoProductos.add(p);*/

	}

	public static Productos getInstancia() {
		return instancia;
	}

	public boolean isModificarProducto() {
		return modificarProducto;
	}

	public void setModificarProducto(boolean modificarProducto) {
		this.modificarProducto = modificarProducto;
	}

	public int getIndice() {
		return indice;
	}

	public void setIndice(int indice) {
		this.indice = indice;
	}

	public int getIndiceInventario() {
		return indiceInventario;
	}

	public void setIndiceInventario(int indiceInventario) {
		this.indiceInventario = indiceInventario;
	}

	public ObservableList<Producto> getGrupoProductos() {
		return grupoProductos;
	}

	public void setGrupoProductos(ObservableList<Producto> grupoProductos) {
		this.grupoProductos = grupoProductos;
	}

}
