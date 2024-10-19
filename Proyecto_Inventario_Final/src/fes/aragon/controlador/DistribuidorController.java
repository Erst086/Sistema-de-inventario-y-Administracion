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

public class DistribuidorController extends UniversalController implements Initializable {
	private Producto producto;
	private String mensajes="";

	@FXML
	private Button btnAceptar;

	@FXML
	private Button btnCancelar;

	@FXML
	private TextField txtCorreoElect;

	@FXML
	private TextField txtEmpresa;

	@FXML
	private TextField txtNombre;

	@FXML
	private TextField txtRFC;

	@FXML
	private TextField txtRepartidor;

	@FXML
	private TextField txtTelefono;

	@FXML
	void calcelarDistribuidor(ActionEvent event) {
		this.cerrarVentana(btnCancelar);

	}

	@FXML
	void nuevoDistribuidor(ActionEvent event) {
		if(this.verificar()){
		producto.getDistribuidor().setNombre(this.txtNombre.getText());
		producto.getDistribuidor().setEmpresa(this.txtEmpresa.getText());
		producto.getDistribuidor().setRepartidor(this.txtRepartidor.getText());
		producto.getDistribuidor().setCorreo(this.txtCorreoElect.getText());
		producto.getDistribuidor().setRfc(this.txtRFC.getText());
		producto.getDistribuidor().setTelefono(this.txtTelefono.getText());

		this.cerrarVentana(btnAceptar);
		} else {
			this.ventanaEmergente("Mensaje", "Datos no correctos", this.mensajes);
			this.mensajes="";
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		this.verificarEntrada(txtRFC, TipoError.RFC);
		this.verificarEntrada(txtCorreoElect, TipoError.CORREO);
		this.verificarEntrada(txtTelefono, TipoError.TELEFONO);
		if (Productos.getInstancia().isModificarProducto()) {
			this.producto = Productos.getInstancia().getGrupoProductos().get(Productos.getInstancia().getIndice());
			this.txtNombre.setText(producto.getDistribuidor().getNombre());
			this.txtEmpresa.setText(producto.getDistribuidor().getEmpresa());
			this.txtRepartidor.setText(producto.getDistribuidor().getRepartidor());
			this.txtCorreoElect.setText(producto.getDistribuidor().getCorreo());
			this.txtRFC.setText(producto.getDistribuidor().getRfc());
			this.txtTelefono.setText(producto.getDistribuidor().getTelefono());
		} else {
			producto = Productos.getInstancia().getGrupoProductos()
					.get(Productos.getInstancia().getGrupoProductos().size() - 1);

		}
	}


	// metodo para validar componentes
	private boolean verificar() {
		boolean valido = true;


			if ((this.txtNombre.getText() == null)
					|| (this.txtNombre.getText() != null && this.txtNombre.getText().isEmpty())) {
				this.mensajes += "El nombre del distribuidor no es valido , complete el espacio\n";
				valido = false;
			}
			if ((this.txtRepartidor.getText() == null)
					|| (this.txtRepartidor != null && this.txtRepartidor.getText().isEmpty())) {
				this.mensajes += "El nombre del repartidor no es valido , complete el espacio\n";
				valido = false;
			}
			if ((this.txtEmpresa.getText() == null)
					|| (this.txtEmpresa != null && this.txtEmpresa.getText().isEmpty())) {
				this.mensajes += "El nombre del la empresa no es valido , complete el espacio\n";
				valido = false;
			}
			if ((this.txtCorreoElect.getText() == null)
					|| (this.txtCorreoElect != null && this.txtCorreoElect.getText().isEmpty())) {
				this.mensajes += "El correo del distribuidor no es valido , complete el espacio\n";
				valido = false;
			}
			if ((this.txtCorreoElect.getText() == null)
					|| (this.txtCorreoElect != null && !this.txtCorreoElect.getText().isEmpty())) {
				if (!this.correoValido) {
					this.mensajes += "El correo del distribuidor no es valido , esta mal estructurado\n";
					valido = false;
				}
			}
		    if ((this.txtRFC.getText() == null) || (this.txtRFC.getText() != null && this.txtRFC.getText().isEmpty())) {
			    this.mensajes += "El RFC del distribuidor no es valido , complete el espacio\n";
			    valido = false;
		     }
		    if ((this.txtRFC.getText() == null) || (this.txtRFC.getText() != null && !this.txtRFC.getText().isEmpty())) {
			    if (!this.rfcValido) {
				 this.mensajes += "El RFC del gdistribuidor no es valido , minimo=13 , maximo=13 caracteres\n";
				 valido = false;
			    }
			}
			if ((this.txtTelefono.getText() == null)
					|| (this.txtTelefono != null && this.txtTelefono.getText().isEmpty())) {
				this.mensajes += "El telefono del distribuidor no es valido , complete el espacio\n";
				valido = false;
			}
			if ((this.txtTelefono.getText() == null)
					|| (this.txtTelefono != null && !this.txtTelefono.getText().isEmpty())) {
				if (!this.telefonoValido) {
					this.mensajes += "El telefono del distribuidor no es valido , minimo 10 y maximo 10 digitos\n";
					valido = false;
				}
			}
			return valido;
		}
	}
