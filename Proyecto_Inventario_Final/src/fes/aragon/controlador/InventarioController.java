package fes.aragon.controlador;

import java.net.URL;
import java.util.ResourceBundle;

import fes.aragon.inventario.general.Inventario;
import fes.aragon.inventario.general.Producto;
import fes.aragon.inventario.general.Productos;
import fes.aragon.inventario.general.TipoError;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class InventarioController extends UniversalController implements Initializable {
	private Producto producto;
	private String mensajes = "";

	@FXML
	private Button btnAceptar;

	@FXML
	private Button btnCancelar;

	@FXML
	private ChoiceBox<String> chcCaducidad;

	@FXML
	private CheckBox chkRefrigerado;

	@FXML
	private TextField txtCantidad;

	@FXML
	private TextField txtNombreProducto;

	@FXML
	private TextField txtPrecioVenta;

	@FXML
	void cancelarInventario(ActionEvent event) {
		this.cerrarVentana(btnCancelar);

	}

	@FXML
	void nuevoInventario(ActionEvent event) {

		Inventario inv = new Inventario();
		if (this.verificar()) {
			inv.setNombreproc(this.txtNombreProducto.getText());
			inv.setCantidad(Float.valueOf(this.txtCantidad.getText()));
			inv.setPrecio(Float.valueOf(this.txtPrecioVenta.getText()));
			inv.setRefrigerado(this.chkRefrigerado.isSelected());
			inv.setCaducidad(this.chcCaducidad.getValue());
			if (Productos.getInstancia().isModificarProducto() && Productos.getInstancia().getIndiceInventario()!=-1) {
				producto.getInventarios().set(Productos.getInstancia().getIndiceInventario(), inv);
			} else {
				producto.getInventarios().add(inv);
			}
			this.cerrarVentana(btnAceptar);
		} else {
			this.ventanaEmergente("Mensaje", "Datos no correctos", this.mensajes);
			this.mensajes = "";
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.chcCaducidad.getItems().addAll("Selecciona la caducidad", "Menor a 4 meses", "Menor a 9 meses",
				"Menor a 12 meses", "Menor a 2 años", "Menor a 4 años", "Producto ya caducado");
		this.chcCaducidad.getSelectionModel().select(0);
		this.verificarEntrada(txtCantidad, TipoError.CANTIDAD);
		this.verificarEntrada(txtPrecioVenta, TipoError.NUMEROS);

		if (Productos.getInstancia().isModificarProducto()) {
			this.producto = Productos.getInstancia().getGrupoProductos().get(Productos.getInstancia().getIndice());
			int indice=Productos.getInstancia().getIndiceInventario();
			Inventario inv=null;
			if(indice==-1) {
				inv=new Inventario();
				inv.setNombreproc("Nuevo inventario");
			}else {
				inv=producto.getInventarios().get(Productos.getInstancia().getIndiceInventario());
			}
			
			this.txtPrecioVenta.setText(String.valueOf(inv.getPrecio()));
			this.txtCantidad.setText(String.valueOf(inv.getCantidad()));
			this.chkRefrigerado.setSelected(inv.isRefrigerado());
			this.chcCaducidad.setValue(inv.getCaducidad());
			this.txtNombreProducto.setText(inv.getNombreproc());

		} else {
			producto = Productos.getInstancia().getGrupoProductos()
					.get(Productos.getInstancia().getGrupoProductos().size() - 1);
		}
	}

	// metodo para validar componentes
	private boolean verificar() {
		boolean valido = true;

		if ((this.txtNombreProducto.getText() == null)
				|| (this.txtNombreProducto != null && this.txtNombreProducto.getText().isEmpty())) {
			this.mensajes += "El nombre del producto no es valido , complete el campo\n";
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
		if ((this.txtPrecioVenta.getText() == null)
				|| (this.txtPrecioVenta != null && this.txtPrecioVenta.getText().isEmpty())) {
			this.mensajes += "El precio del producto no es valido , complete el campo\n";
			valido = false;
		}
		if ((this.txtPrecioVenta.getText() == null)
				|| (this.txtPrecioVenta != null && !this.txtPrecioVenta.getText().isEmpty())) {
			try {
				if (!this.precioValido) {
					throw new NumberFormatException();
				}
				Float.parseFloat(this.txtPrecioVenta.getText());
			} catch (NumberFormatException ex) {
				this.mensajes += "El precio no es valido, debe de contener decimales\n";
				valido = false;
			}

		}
		if (this.chcCaducidad.getSelectionModel().getSelectedIndex() == 0
				|| this.chcCaducidad.getSelectionModel().getSelectedIndex() == -1) {
			this.mensajes += "Selecciona la caducidad aproximada\n";
			valido = false;
		}
		return valido;

	}
}
