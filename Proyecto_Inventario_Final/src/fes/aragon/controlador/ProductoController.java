package fes.aragon.controlador;

import java.net.URL;
import java.util.ResourceBundle;

import fes.aragon.inventario.general.Producto;
import fes.aragon.inventario.general.Productos;
import fes.aragon.inventario.general.TipoError;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ProductoController extends UniversalController implements Initializable {
	private Producto producto;
	private String mensajes = "";

	@FXML
	private Button btnAceptar;

	@FXML
	private Button btnCancelar;

	@FXML
	private Button btnDistribuidor;

	@FXML
	private Button btnInventario;

	@FXML
	private TextField txtBodega;

	@FXML
	private TextField txtCantidad;

	@FXML
	private TextField txtProductoT;

	@FXML
	private TextField txtTipoContacto;

	@FXML
	void cancelarProducto(ActionEvent event) {
		this.cerrarVentana(btnCancelar);

	}

	@FXML
	void nuevoDistribuidor(ActionEvent event) {
		this.nuevaVentana("Distribuidor");
	}

	@FXML
	void nuevoInventario(ActionEvent event) {

		if (Productos.getInstancia().isModificarProducto()) {
			this.nuevaVentana("InventarioMod");
		} else {
			this.nuevaVentana("Inventario");
		}
	}

	@FXML
	void nuevoProducto(ActionEvent event) {
		if (this.verificar()) {

			producto.setProductoT(this.txtProductoT.getText());
			producto.setCantidad(this.txtCantidad.getText());
			producto.setBodega(this.txtBodega.getText());
			producto.setContacto(this.txtTipoContacto.getText());

			if (Productos.getInstancia().isModificarProducto()) {
				Productos.getInstancia().getGrupoProductos().set(Productos.getInstancia().getIndice(), producto);
				Productos.getInstancia().setIndice(-1);
				Productos.getInstancia().setModificarProducto(false);
				Productos.getInstancia().setIndiceInventario(-1);
			} else {
				Productos.getInstancia().getGrupoProductos()
						.set(Productos.getInstancia().getGrupoProductos().size() - 1, producto);
			}
			this.cerrarVentana(btnAceptar);
		} else {
			this.ventanaEmergente("Mensaje", "Datos no correctos", this.mensajes);
			this.mensajes="";
		}

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		this.verificarEntrada(txtCantidad, TipoError.CANTIDAD);

		if (Productos.getInstancia().isModificarProducto()) {
			this.producto = Productos.getInstancia().getGrupoProductos().get(Productos.getInstancia().getIndice());

			this.txtProductoT.setText(producto.getProductoT());
			this.txtCantidad.setText(producto.getCantidad());
			this.txtBodega.setText(producto.getBodega());
			this.txtTipoContacto.setText(producto.getContacto());
		} else {
			producto = Productos.getInstancia().getGrupoProductos()
					.get(Productos.getInstancia().getGrupoProductos().size() - 1);

		}
	}

	// metodo para validar componentes
	private boolean verificar() {
		boolean valido = true;
		if ((this.txtProductoT.getText() == null)
				|| (this.txtProductoT.getText() != null && this.txtProductoT.getText().isEmpty())) {
			this.mensajes += "El tipo de producto no es valido, complete el espacio\n";
			valido = false;
		}
		if ((this.txtCantidad.getText() == null)
				|| (this.txtCantidad != null && this.txtCantidad.getText().isEmpty())) {
			this.mensajes += "La cantidad no es valida, complete el espacio \n";
			valido = false;
		}
		if ((this.txtCantidad.getText() == null)
				|| (this.txtCantidad != null && !this.txtCantidad.getText().isEmpty())) {
				if (!this.cantidadValido) {
			this.mensajes += "La cantidad escrita no es valida, ingrese la cantidad unicamente con numeros\n";
			valido = false;
				}
			}
		if ((this.txtBodega.getText() == null) || (this.txtBodega != null && this.txtBodega.getText().isEmpty())) {
			this.mensajes += "La bodega no es valida, complete el espacio\n";
			valido = false;
		}
		if ((this.txtTipoContacto.getText() == null)
				|| (this.txtTipoContacto.getText() != null && this.txtTipoContacto.getText().isEmpty())) {
			this.mensajes += "El tipo de contacto no es valido, complete el espacio\n";
			valido = false;
		}

		return valido;
	}
}
