package com.example.basedatos_ejemplo;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.util.converter.NumberStringConverter;
import net.sf.jasperreports.engine.JRException;

import java.sql.SQLException;
import java.util.Optional;

public class HelloController {
    private DAO pokeDAO = new DAO();
    private Pokemon pokeAux;
    private ObservableList<Pokemon> datos;

    private DAO dao = new DAO();
    private ObservableList<Pokemon> olPokemon;

    @FXML
    private TableView<Pokemon> tvPokemon;
    @FXML
    private TableColumn tcId;
    @FXML
    private TableColumn tcNombre;
    @FXML
    private TableColumn tcHP;
    @FXML
    private TableColumn tcAtaque;
    @FXML
    private TableColumn tcDefensa;
    @FXML
    private TableColumn tcAtaqueEsp;
    @FXML
    private TableColumn tcDefensaEsp;
    @FXML
    private TableColumn tcVelocidad;
    @FXML
    private TableColumn tcTipo;

    @FXML
    private Button btnBorrar;
    @FXML
    private Button btnAlta;
    @FXML
    private Button btnActualizar;

    @FXML
    private TextField txtID;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtHP;
    @FXML
    private TextField txtAtaque;
    @FXML
    private TextField txtDefensa;
    @FXML
    private TextField txtAtaqueEsp;
    @FXML
    private TextField txtDefensaEsp;
    @FXML
    private TextField txtVelocidad;
    @FXML
    private TextField txtTipo;
    @FXML
    private ImageView Image;


    /**
     * Inicializa el programa realizando una serie de tareas.
     * Este método realiza un doble clic, carga los datos de la tabla,
     * crea un objeto Pokemon auxiliar y realiza los bindings necesarios.
     */
    public void initialize() {
        dobleClick();

        cargarDatosTabla();
        pokeAux = new Pokemon(0, "", 0,
                0, 0, 0, 0, 0, 0);
        realizarBindingsPokeAux(pokeAux);

    }
    /**
     * Realiza los bindings bidireccionales entre las propiedades del objeto Pokemon y los componentes de la interfaz de usuario.
     * Los bindings aseguran que los valores de las propiedades y los componentes estén siempre sincronizados.
     *
     * @param poke El objeto Pokemon al que se le realizarán los bindings.
     */
    private void realizarBindingsPokeAux(Pokemon poke) {

        txtID.textProperty().bindBidirectional(poke.IDpokeProperty(), new NumberStringConverter());
        txtNombre.textProperty().bindBidirectional(poke.pokenameProperty());
        txtHP.textProperty().bindBidirectional(poke.HPProperty(), new NumberStringConverter());
        txtAtaque.textProperty().bindBidirectional(poke.attackProperty(), new NumberStringConverter());
        txtDefensa.textProperty().bindBidirectional(poke.defenseProperty(), new NumberStringConverter());
        txtAtaqueEsp.textProperty().bindBidirectional(poke.spattackProperty(), new NumberStringConverter());
        txtDefensaEsp.textProperty().bindBidirectional(poke.spdefenseProperty(), new NumberStringConverter());
        txtVelocidad.textProperty().bindBidirectional(poke.speedProperty(), new NumberStringConverter());
        txtTipo.textProperty().bindBidirectional(poke.dualtypeProperty(), new NumberStringConverter());

    }
    /**
     * Maneja el evento de clic en el botón de Alta.
     * Si el ID del Pokemon auxiliar es mayor o igual a cero, se intenta dar de alta el Pokemon utilizando el objeto PokeDAO.
     * Si el alta es exitosa, se cargan los datos actualizados en la tabla.
     * En caso contrario, se muestra una alerta informando que se debe introducir un código de producto válido.
     *
     * @param actionEvent El evento de acción generado por el clic en el botón de Alta.
     */
    public void onAltaClicked(ActionEvent actionEvent) {
        if ( pokeAux.getIDpoke() >= 0) {
            if (pokeDAO.altaPokemon(pokeAux)) {
                cargarDatosTabla();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Debe introducir un código de producto.", ButtonType.OK);
            alert.showAndWait();
        }


    }
    /**
     * Maneja el evento de clic en el botón de Borrar.
     * Muestra una alerta de confirmación para confirmar si el usuario desea borrar la columna del Pokemon auxiliar.
     * Si el usuario confirma, se intenta borrar la columna utilizando el objeto DAO.
     * Si el borrado es exitoso, se muestra una alerta informativa y se cargan los datos actualizados en la tabla.
     * En caso contrario, se muestra una alerta indicando que los datos no han sido borrados.
     *
     * @param actionEvent El evento de acción generado por el clic en el botón de Borrar.
     */
    public void onBorrarClicked(ActionEvent actionEvent) {
        Alert alert;
        String Codigo = String.valueOf(pokeAux.getIDpoke());
        alert = new Alert(Alert.AlertType.CONFIRMATION, "Quieres borrar la columna?");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            if (!Codigo.trim().equals("")) {
                if (dao.borrarProducto(pokeAux)) {
                    alert = new Alert(Alert.AlertType.INFORMATION, "La columna se ha borrado exitosamente");
                    alert.showAndWait();
                    cargarDatosTabla();
                }
            }
        } else {
            alert = new Alert(Alert.AlertType.INFORMATION, "No se han borrado los datos");
            alert.showAndWait();
        }


    }
    /**
     * Maneja el evento de clic en el botón de Actualizar.
     * Carga los datos actualizados en la tabla.
     *
     * @param actionEvent El evento de acción generado por el clic en el botón de Actualizar.
     */
    public void onActualizarClicked(ActionEvent actionEvent) {
        cargarDatosTabla();


    }
    /**
     * Carga los datos de Pokemon en la tabla.
     * Obtiene los datos de Pokemon utilizando el objeto PokeDAO y los asigna a la variable "datos".
     * Luego, configura las propiedades de cada columna de la tabla para mostrar los datos correspondientes.
     * Finalmente, asigna los datos a la tabla.
     */
    private void cargarDatosTabla() {
        datos = pokeDAO.obtenerPokemon();

        tcId.setCellValueFactory(new PropertyValueFactory<Pokemon, Integer>("IDpoke"));
        tcNombre.setCellValueFactory(new PropertyValueFactory<Pokemon, String>("pokename"));
        tcHP.setCellValueFactory(new PropertyValueFactory<Pokemon, Integer>("HP"));
        tcAtaque.setCellValueFactory(new PropertyValueFactory<Pokemon, Integer>("attack"));
        tcDefensa.setCellValueFactory(new PropertyValueFactory<Pokemon, Integer>("defense"));
        tcAtaqueEsp.setCellValueFactory(new PropertyValueFactory<Pokemon, Integer>("spattack"));
        tcDefensaEsp.setCellValueFactory(new PropertyValueFactory<Pokemon, Integer>("spdefense"));
        tcVelocidad.setCellValueFactory(new PropertyValueFactory<Pokemon, Integer>("speed"));
        tcTipo.setCellValueFactory(new PropertyValueFactory<Pokemon, Integer>("dualtype"));

        tvPokemon.setItems(datos);
    }
    /**
     * Configura el comportamiento de doble clic en la tabla de Pokemon.
     * Al hacer doble clic en una fila de la tabla, se asignan los valores de los Pokemon seleccionado al Pokemon auxiliar.
     * Esto permite seleccionar un Pokemon de la tabla para su edición o visualización detallada.
     */
    private void dobleClick() {
        tvPokemon.setRowFactory(tv -> {
            TableRow<Pokemon> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (!row.isEmpty())) {
                    pokeAux.setIDpoke(row.getItem().getIDpoke());
                    pokeAux.setPokename(row.getItem().getPokename());
                    pokeAux.setHP(row.getItem().getHP());
                    pokeAux.setAttack(row.getItem().getAttack());
                    pokeAux.setDefense(row.getItem().getDefense());
                    pokeAux.setSpattack(row.getItem().getSpattack());
                    pokeAux.setSpdefense(row.getItem().getSpdefense());
                    pokeAux.setSpeed(row.getItem().getSpeed());
                    pokeAux.setDualtype(row.getItem().getDualtype());
                }
            });
            return row;
        });
    }
    /**
     * Maneja el evento de clic en el botón de Imprimir Listado.
     * Intenta mostrar un informe Jasper utilizando la clase JasperReports.
     * Captura y maneja posibles excepciones de ClassNotFoundException, JRException y SQLException.
     *
     * @param actionEvent El evento de acción generado por el clic en el botón de Imprimir Listado.
     */
    public void onImprimirListadoClick(ActionEvent actionEvent) {
        try {
            // --- Show Jasper Report on click-----
            new JasperReports().showReportSimple();
        } catch (ClassNotFoundException | JRException | SQLException e1) {
            e1.printStackTrace();
        }

    }
}
