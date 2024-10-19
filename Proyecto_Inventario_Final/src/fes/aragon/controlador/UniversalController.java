package fes.aragon.controlador;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fes.aragon.inventario.general.Producto;
import fes.aragon.inventario.general.Productos;
import fes.aragon.inventario.general.TipoError;
import fes.aragon.model.archive.ProductoArchivo;
import javafx.collections.FXCollections;
import javafx.css.PseudoClass;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class UniversalController {
	protected boolean rfcValido = true;
	protected boolean correoValido = true;
	protected boolean telefonoValido = true;
	protected boolean precioValido = true;
	protected boolean cantidadValido = true;

	private String[] expresiones = { "(\\d+)(\\.\\d{1,2})", "(\\w){13}",
			"^[\\w-]+(\\.[\\w-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", "(\\d){10}", "(\\d+)" };

	public void nuevaVentana(String archivo) {
		try {
			Pane root = (Pane) FXMLLoader.load(getClass().getResource("/fes/aragon/fxml/" + archivo + ".fxml"));
			Scene scene = new Scene(root);
			Stage escenario = new Stage();
			escenario.setScene(scene);
			escenario.initStyle(StageStyle.UNDECORATED);
			escenario.initModality(Modality.APPLICATION_MODAL);
			escenario.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void ventanaEmergente(String titulo, String encabezado, String contenido) {
		Alert alerta;
		alerta = new Alert(AlertType.INFORMATION);
		alerta.setX(300);
		alerta.setTitle(titulo);
		alerta.setHeaderText(encabezado);
		alerta.setContentText(contenido);
		alerta.showAndWait();

	}

	public void cerrarVentana(Button boton) {
		Stage stage = (Stage) boton.getScene().getWindow();
		stage.close();
	}

	// VERIFICADOR DE ERRORES
	public void verificarEntrada(TextField caja, TipoError error) {
		caja.textProperty().addListener(evento -> {
			String text = caja.getText();
			if (text == null) {
				text = "";

			}
			String patron = expresiones[error.ordinal()];
			Pattern pt = Pattern.compile(patron);
			Matcher match = pt.matcher(text);
			caja.pseudoClassStateChanged(PseudoClass.getPseudoClass("error"), !match.matches());

			if (error == TipoError.NUMEROS) {
				this.precioValido = match.matches();
			} else if (error == TipoError.RFC) {
				this.rfcValido = match.matches();
			} else if (error == TipoError.CORREO) {
				this.correoValido = match.matches();
			} else if (error == TipoError.TELEFONO) {
				this.telefonoValido = match.matches();
			}if (error == TipoError.CANTIDAD) {
				this.cantidadValido = match.matches();}
			});
		}
	public void abrirArchivo(Button boton) throws IOException, ClassNotFoundException {
		Stage stage = (Stage) boton.getScene().getWindow();
		FileChooser archivos=new FileChooser();
		archivos.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Archivos para Inventario", "*.fes"));
		archivos.setTitle("Abrir archivo del Inventario");
		archivos.setInitialDirectory(new File(System.getProperty("user.dir")));
		File ruta=archivos.showOpenDialog(stage);
		if(ruta!=null) {
			FileInputStream fi=new FileInputStream(ruta);
			ObjectInputStream entrada=new ObjectInputStream(fi);
			ArrayList<ProductoArchivo> datos=(ArrayList<ProductoArchivo>)entrada.readObject();
			Productos.getInstancia().getGrupoProductos().clear();
			for (ProductoArchivo producto : datos) {
				Producto objeto=new Producto();
				objeto.setBodega(producto.getBodega());
				objeto.setContacto(producto.getContacto());
				objeto.setDistribuidor(producto.getDistribuidor());
				objeto.setProductoT(producto.getProductoT());
				objeto.setCantidad(producto.getCantidad());
				objeto.setInventarios(FXCollections.observableArrayList(producto.getInventarios()));
				Productos.getInstancia().getGrupoProductos().add(objeto);
			}
			fi.close();
			entrada.close();
		}
	}
	public void guardarArchivo(Button boton) throws IOException {
		Stage stage = (Stage) boton.getScene().getWindow();
		FileChooser archivos=new FileChooser();
		archivos.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Archivos para Inventario", "*.fes"));
		archivos.setTitle("Guardar archivo del Inventario");
		archivos.setInitialDirectory(new File(System.getProperty("user.dir")));
		File ruta=archivos.showSaveDialog(stage);
		if(ruta!=null) {
			FileOutputStream fo=new FileOutputStream(ruta);
			ObjectOutputStream salida=new ObjectOutputStream(fo);
			ArrayList<ProductoArchivo> productos=new ArrayList<>();
			for (Producto producto : Productos.getInstancia().getGrupoProductos()) {
				ProductoArchivo objeto=new ProductoArchivo();
				objeto.setBodega(producto.getBodega());
				objeto.setContacto(producto.getContacto());
				objeto.setDistribuidor(producto.getDistribuidor());
				objeto.setProductoT(producto.getProductoT());
				objeto.setCantidad(producto.getCantidad());
				objeto.setInventarios(new ArrayList<>(producto.getInventarios()));
				productos.add(objeto);
			}
			salida.writeObject(productos);
			salida.close();
			fo.close();
		}
	}
}
