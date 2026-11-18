package com.example.servicioweb.controller;

import com.example.servicioweb.model.Usuario;
import com.example.servicioweb.service.UsuarioService;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class UsuarioController {
    @FXML
    private TableView<Usuario> tblUsuario;
    @FXML
    private TableColumn<Usuario, Integer> colId;
    @FXML
    private TableColumn<Usuario, String> colNombre;
    @FXML
    private TableColumn<Usuario, String> colUsername;
    @FXML
    private TableColumn<Usuario, String> colCorreo;
    @FXML
    private TableColumn<Usuario, String> colTelefono;
    @FXML
    private TableColumn<Usuario, String> colCiudad;
    @FXML
    private TableColumn<Usuario, String> colEmpresa;
    @FXML
    private Button btnConsulta;
    @FXML
    private Label lblEstado;

    private final UsuarioService service = new UsuarioService();

    @FXML
    public void initialize(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        colCorreo.setCellValueFactory(new PropertyValueFactory<>("email"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colCiudad.setCellValueFactory(new PropertyValueFactory<>("ciudad"));
        colEmpresa.setCellValueFactory(new PropertyValueFactory<>("empresa"));
    }

    @FXML
    private void consultarUsuarios(){
        Task<List<Usuario>> task = new Task<List<Usuario>>() {
            @Override
            protected List<Usuario> call() throws Exception {
                return service.getUsuarios();
            }
        };
        btnConsulta.disableProperty().bind(
                task.runningProperty()
        );
        lblEstado.setText("Consultando API REST . . .");
        task.setOnSucceeded(event -> {
            List<Usuario> usuarios = task.getValue();
            tblUsuario.setItems(FXCollections.observableArrayList(usuarios));
            lblEstado.setText("Usuarios encontrados: ");

        });

        //en caso de error
        task.setOnFailed(event -> {
            Throwable error = task.getException();
            lblEstado.setText(error.getMessage());

        });

        Thread hilo = new Thread(task);
        hilo.setDaemon(true);
        hilo.start();
    }



}

