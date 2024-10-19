package fes.aragon.controlador;

import static javafx.scene.control.ButtonType.OK;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import fes.aragon.inventario.general.Producto;
import fes.aragon.inventario.general.Productos;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class PrincipalController extends UniversalController  implements Initializable {

    @FXML
    private Button btnAbrirInventario;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnGuardarInventario;

    @FXML
    private Button btnModificar;

    @FXML
    private Button btnNuevo;

    @FXML
    private Button btnSalir;

	@FXML
	private TableColumn<Producto, String> clmBodega;

	@FXML
	private TableColumn<Producto, String> clmCantidad;

	@FXML
	private TableColumn<Producto, String> clmContacto;

	@FXML
	private TableColumn<Producto, String> clmDistribuidor;

	@FXML
	private TableColumn<Producto, String> clmProducto;

	@FXML
	private TableView<Producto> tblTabla;

    @FXML
    void abrirInventario(ActionEvent event) {
    	try {
			this.abrirArchivo(btnAbrirInventario);
			this.desabilitar(false);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			this.ventanaEmergente("Mensaje", "Problema en el archivo", "Hay un problema en el archivo, consulta al programador");
			e.printStackTrace();
		}
    }
    
    @FXML
    void guardarInventario(ActionEvent event) {
    	try {
			this.guardarArchivo(btnGuardarInventario);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			this.ventanaEmergente("Mensaje", "Problema en el archivo", "Hay un problema en el archivo, consulta al programador");
			e.printStackTrace();
		}
    }
    private void desabilitar(boolean valor) {
    	this.btnGuardarInventario.setDisable(valor);
    	this.btnModificar.setDisable(valor);
    	this.btnEliminar.setDisable(valor);
    	
    }

	@FXML
	void eliminarProducto(ActionEvent event) {
		int indice=this.tblTabla.getSelectionModel().getSelectedIndex();
		if(indice>=0) {
			this.tblTabla.getItems().remove(indice);
		}else {
			Alert alerta;
			alerta = new Alert(AlertType.INFORMATION);
			alerta.setTitle("Dialogo de Aviso");
			alerta.setHeaderText("Se necesitan seleccionar una fila");
			alerta.setContentText("Porfavor selecciona una fila, para eliminar");
			Optional<ButtonType> resultado = alerta.showAndWait();
			if (resultado.get().equals(OK)) {

			}
		}

	}

	@FXML
	void modificarProducto(ActionEvent event) {
		int indice = this.tblTabla.getSelectionModel().getSelectedIndex();
		if (indice >= 0) {
			Productos.getInstancia().setModificarProducto(true);
			Productos.getInstancia().setIndice(indice);
			this.nuevaVentana("Producto");
		} else {
			Alert alerta;
			alerta = new Alert(AlertType.INFORMATION);
			alerta.setTitle("Dialogo de Aviso");
			alerta.setHeaderText("Se necesitan seleccionar una fila");
			alerta.setContentText("Porfavor selecciona una fila, para modificar");
			Optional<ButtonType> resultado = alerta.showAndWait();
			if (resultado.get().equals(OK)) {

			}
		}

	}

	@FXML
	void nuevoProducto(ActionEvent event) {
		Productos.getInstancia().setIndice(-1);
		Productos.getInstancia().setModificarProducto(false);
		Productos.getInstancia().setIndiceInventario(-1);
		Productos.getInstancia().getGrupoProductos().add(new Producto());
		this.nuevaVentana("Producto");
		this.desabilitar(false);

	}
	
	@FXML
	void salir(ActionEvent event) {
		Platform.exit();

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		// TODO Auto-generated method stub
		this.clmProducto.setCellValueFactory(new PropertyValueFactory<>("productoT"));
		this.clmBodega.setCellValueFactory(new PropertyValueFactory<>("bodega"));
		this.clmContacto.setCellValueFactory(new PropertyValueFactory<>("contacto"));
		this.clmDistribuidor.setCellValueFactory(new PropertyValueFactory<>("distribuidor"));
		this.clmCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
		this.tblTabla.setItems(Productos.getInstancia().getGrupoProductos());
		this.desabilitar(true);
	}

}
