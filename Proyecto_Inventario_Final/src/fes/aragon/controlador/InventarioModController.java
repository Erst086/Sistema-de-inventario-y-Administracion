package fes.aragon.controlador;

import java.net.URL;
import java.util.ResourceBundle;

import fes.aragon.inventario.general.Inventario;
import fes.aragon.inventario.general.Producto;
import fes.aragon.inventario.general.Productos;
//import javafx.beans.property.ReadOnlyObjectWrapper;
//import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class InventarioModController extends UniversalController implements Initializable {
	private Producto producto;

	@FXML
	private Button btnModificar;

	@FXML
	private Button btnCancelar;
	
    @FXML
    private Button btnNuevo;

	@FXML
	private TableColumn<Inventario, String> clmCaducidad;

	@FXML
	private TableColumn<Inventario, Float> clmCantidad;

	@FXML
	private TableColumn<Inventario, Float> clmPrecvent;

	@FXML
	private TableColumn<Inventario, String> clmProduc;

	@FXML
	private TableColumn<Inventario, Boolean> clmRefrigerado;

	@FXML
	private TableView<Inventario> tblInventario;
	
    @FXML
    void nuevo(ActionEvent event) {
    	Productos.getInstancia().setIndiceInventario(-1);
    	this.nuevaVentana("Inventario");
    }

	@FXML
	void modificar(ActionEvent event) {
		
		int indice = this.tblInventario.getSelectionModel().getSelectedIndex();
		if (indice >= 0) {
			Productos.getInstancia().setIndiceInventario(indice);
			this.nuevaVentana("Inventario");
		} else {
			this.ventanaEmergente("Informacion", "Seleccionar una fila", "Porfavor selecciones una fila");
			}
		// verificar para la tabla dinamica
	}

	@FXML
	void cancelar(ActionEvent event) {
		this.cerrarVentana(btnCancelar);
	}

	//@SuppressWarnings({ "rawtypes" })
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		/*Inventario inv = new Inventario();
		this.tblInventario.setItems(inv.getDatos());
		this.clmCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
		this.clmCantidad.setCellValueFactory(cellData -> {
			Inventario a = cellData.getValue();
			return new ReadOnlyObjectWrapper(a.getCantidad());
		});
		this.tblInventario.setRowFactory(tv -> {
			TableRow<Inventario> row = new TableRow<>();
			row.itemProperty().addListener((obs, entradaAnterior, entradaActual) -> {
				if (entradaActual != null) {
					row.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected-row"),
							entradaActual.getCantidad() > 500);
					row.pseudoClassStateChanged(PseudoClass.getPseudoClass("highlighted"),
							entradaActual.getCantidad() <= 500);
				}
			});
			return row;
		});*/
		
		this.clmProduc.setCellValueFactory(new PropertyValueFactory<>("nombreproc"));
		this.clmPrecvent.setCellValueFactory(new PropertyValueFactory<>("precio"));
		this.clmRefrigerado.setCellValueFactory(new PropertyValueFactory<>("refrigerado"));
		this.clmCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
		this.clmCaducidad.setCellValueFactory(new PropertyValueFactory<>("caducidad"));
		this.producto = Productos.getInstancia().getGrupoProductos().get(Productos.getInstancia().getIndice());
		this.tblInventario.setItems(producto.getInventarios());

	}

}

