package com.example.servicioweb.controller;

import com.example.servicioweb.model.Customers;
import com.example.servicioweb.service.CustomerService;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDateTime;
import java.util.List;

public class CustomerController {
    @FXML
    private TableView<Customers> tblCustomers;
    @FXML
    private TableColumn<Customers, Integer> colId;
    @FXML
    private TableColumn<Customers, String> colFirstName;
    @FXML
    private TableColumn<Customers, String> colLastName;
    @FXML
    private TableColumn<Customers, String> colEmail;
    @FXML
    private TableColumn<Customers, String> colPhone;
    @FXML
    private TableColumn<Customers, LocalDateTime> colDateOfBirth;
    @FXML
    private TableColumn<Customers, String> colCountry;
    @FXML
    private TableColumn<Customers, String> colCity;
    @FXML
    private TableColumn<Customers, String> colAddress;
    @FXML
    private TableColumn<Customers, Double> colAnnualIncome;
    @FXML
    private TableColumn<Customers, Integer> colCreditScore;
    @FXML
    private TableColumn<Customers, String> colUnpaidDebt;
    @FXML
    private TableColumn<Customers, Double> colUnpaidDebtAmount;
    @FXML
    private TableColumn<Customers, String> colStatus;
    @FXML
    private TableColumn<Customers, LocalDateTime> colCreatedAt;
    @FXML
    private TableColumn<Customers, LocalDateTime> colUpdatedAt;
    @FXML
    private Button btnConsulta;
    @FXML
    private Label lblEstado;

    private final CustomerService service = new CustomerService();

    @FXML
    public void initialize(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colDateOfBirth.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        colCountry.setCellValueFactory(new PropertyValueFactory<>("country"));
        colCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colAnnualIncome.setCellValueFactory(new PropertyValueFactory<>("annualIncome"));
        colCreditScore.setCellValueFactory(new PropertyValueFactory<>("creditScore"));
        colUnpaidDebt.setCellValueFactory(new PropertyValueFactory<>("unpaidDebt"));
        colUnpaidDebtAmount.setCellValueFactory(new PropertyValueFactory<>("unpaidDebtAmount"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colCreatedAt.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
        colUpdatedAt.setCellValueFactory(new PropertyValueFactory<>("updatedAt"));
    }

    @FXML
    private void consultarClientes(){
        Task<List<Customers>> task = new Task<List<Customers>>() {
            @Override
            protected List<Customers> call() throws Exception {
                return service.getCustomers();
            }
        };
        btnConsulta.disableProperty().bind(
                task.runningProperty()
        );
        lblEstado.setText("Consultando API REST . . .");
        task.setOnSucceeded(event -> {
            List<Customers> customers = task.getValue();
            tblCustomers.setItems(FXCollections.observableArrayList(customers));
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
