package sgds.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import sgds.App;
import sgds.model.Usuario;

public class HomeMedicoController {

    @FXML
    private Button bntSair;

    @FXML
    private Button btnAgendamento;

    @FXML
    private Button btnConsulta;

    @FXML
    private Button btnExames;

    @FXML
    private Button btnNovaConsulta;

    @FXML
    private Button btnPerfil;

    @FXML
    private AnchorPane paineilSubConsulta;

    @FXML
    private AnchorPane paineilSubExames;

    @FXML
    private AnchorPane painelAgendamento;

    @FXML
    private AnchorPane painelConsulta;

    @FXML
    private AnchorPane painelPerfil;

    @FXML
    private Label perfilCargoOp;

    @FXML
    private Label perfilCodOp;

    @FXML
    private Label perfilCpfOp;

    @FXML
    private Label perfilEmailOp;

    @FXML
    private Label perfilNomeOp;

    @FXML
    private Label perfilTelefoneOp;

    @FXML
    void encerrar(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void verAgendamentos(ActionEvent event) {
        painelAgendamento.toFront();
    }

    @FXML
    void verConsulta(ActionEvent event) {
        painelConsulta.toFront();
        paineilSubConsulta.toFront();
    }

    @FXML
    void verPainelSubConsulta(ActionEvent event) {
        paineilSubConsulta.toFront();
    }

    @FXML
    void verPainelSubExames(ActionEvent event) {
        paineilSubExames.toFront();
    }

    @FXML
    void verPerfil(ActionEvent event) {
        painelPerfil.toFront();
    }

    private App app;

    public void setAppInstance(App app) {
        this.app = app;
    }

    public void iniciar(Usuario usuario){
        perfilNomeOp.setText("Nome: "+usuario.getNome());
        perfilCpfOp.setText("CPF: "+usuario.getCpf());
        perfilEmailOp.setText("E-mail: "+usuario.getEmail());
        perfilCargoOp.setText("Cargo: "+usuario.getCargo());
        perfilCodOp.setText("COD: Não informado");
        perfilTelefoneOp.setText("Telefone: Não informado");

    }
}
