package com.example.basedatos_ejemplo;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.converter.NumberStringConverter;

import java.util.Optional;

public class HelloController {
    private DAO productDAO = new DAO();
    private Pokemon pokeAux;
    private ObservableList<Pokemon> datos;

    @FXML
    private TableColumn tcProductVendor;
    @FXML
    private Button btnBorrar;
    @FXML
    private TextField txtVendedor;
    @FXML
    private TextField txtStock;
    @FXML
    private TableColumn tcBuyPrice;
    @FXML
    private TableView<Pokemon> tvProductos;
    @FXML
    private TableColumn tcQuantityInStock;
    @FXML
    private TextField txtID;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtEscala;
    @FXML
    private TableColumn tcProductDescription;
    @FXML
    private TextField txtPrecioCompra;
    @FXML
    private TableColumn tcProductScale;
    @FXML
    private Button btnAlta;
    @FXML
    private TableColumn tcMSRP;
    @FXML
    private TextField txtPrecioVenta;
    @FXML
    private TextField txtLinea;
    @FXML
    private TableColumn tcProductName;
    @FXML
    private TableColumn tcProductLine;
    @FXML
    private TextField txtDescripcion;
    @FXML
    private Button btnActualizar;
    @FXML
    private TableColumn tcProductCode;

    private DAO dao = new DAO();
    private ObservableList<Pokemon> olProductos;

    public void initialize()  {
        dobleClick();

        cargarDatosTabla();
        pokeAux = new Pokemon(0,"",0,
                0,0,0,0,0,0);
        realizarBindingsPokeAux(pokeAux);

    }
    private void realizarBindingsPokeAux (Pokemon poke) {

        txtEscala.textProperty().bindBidirectional(productoAux.productScaleProperty());
        txtDescripcion.textProperty().bindBidirectional(producto.productDescriptionProperty());
        txtID.textProperty().bindBidirectional(producto.productCodeProperty());
        txtLinea.textProperty().bindBidirectional(producto.productLineProperty());
        txtNombre.textProperty().bindBidirectional(producto.productNameProperty());
        txtPrecioCompra.textProperty().bindBidirectional(producto.buyPriceProperty(),new NumberStringConverter());
        txtPrecioVenta.textProperty().bindBidirectional(producto.MSRPProperty(), new NumberStringConverter() );
        txtStock.textProperty().bindBidirectional(producto.quantityInStockProperty(), new NumberStringConverter() );
        txtVendedor.textProperty().bindBidirectional(producto.productVendorProperty());
    }

    public void onAltaClicked(ActionEvent actionEvent) {
        if ( ! productoAux.getProductCode().trim().equals("")) {
            if (productDAO.altaProducto(productoAux)) {
                cargarDatosTabla();
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Debe introducir un código de producto.", ButtonType.OK );
            alert.showAndWait();
        }


    }
    public void onBorrarClicked(ActionEvent actionEvent) {
        Alert alert;
        String Codigo = String.valueOf(productoAux.getProductCode());
        alert = new Alert(Alert.AlertType.CONFIRMATION, "Quieres borrar la columna?");
        Optional<ButtonType> action  = alert.showAndWait();
        if(action.get() == ButtonType.OK) {
            if (!Codigo.trim().equals("")){
                if(dao.borrarProducto(productoAux)){
                    alert = new Alert(Alert.AlertType.INFORMATION, "La columna se ha borrado exitosamente");
                    alert.showAndWait();
                    cargarDatosTabla();
                }
            }
        }else {
            alert = new Alert(Alert.AlertType.INFORMATION, "No se han borrado los datos");
            alert.showAndWait();
        }


    }
    public void onActualizarClicked(ActionEvent actionEvent) {
        cargarDatosTabla();


    }
    private void cargarDatosTabla () {
        datos = productDAO.obtenerProductos();

        tcProductCode.setCellValueFactory(new PropertyValueFactory<Producto, String>("productCode"));
        tcProductDescription.setCellValueFactory(new PropertyValueFactory<Producto, String>("productDescription"));
        tcProductLine.setCellValueFactory(new PropertyValueFactory<Producto, String>("productLine"));
        tcProductName.setCellValueFactory(new PropertyValueFactory<Producto, String>("productName"));
        tcProductScale.setCellValueFactory(new PropertyValueFactory<Producto, String>("productScale"));
        tcProductVendor.setCellValueFactory(new PropertyValueFactory<Producto, String>("productVendor"));

        tcBuyPrice.setCellValueFactory(new PropertyValueFactory<Producto, Double>("buyPrice"));
        tcMSRP.setCellValueFactory(new PropertyValueFactory<Producto, Double>("MSRP"));
        tcQuantityInStock.setCellValueFactory(new PropertyValueFactory<Producto, Integer>("quantityInStock"));

        tvProductos.setItems(datos);
    }
    private void dobleClick(){
        tvProductos.setRowFactory(tv ->{
            TableRow<Producto> row = new TableRow<>();
            row.setOnMouseClicked(event ->{
                if(event.getClickCount() == 1 && (!row.isEmpty())){
                    productoAux.setProductCode(row.getItem().getProductCode());
                    productoAux.setProductDescription(row.getItem().getProductDescription());
                    productoAux.setProductLine(row.getItem().getProductLine());
                    productoAux.setProductName(row.getItem().getProductName());
                    productoAux.setProductScale(row.getItem().getProductScale());
                    productoAux.setProductVendor(row.getItem().getProductVendor());

                    productoAux.setBuyPrice(row.getItem().getBuyPrice());
                    productoAux.setMSRP(row.getItem().getMSRP());
                    productoAux.setQuantityInStock(row.getItem().getQuantityInStock());
                }
            });
            return row;
        });
    }
