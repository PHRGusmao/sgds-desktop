package sgds.controller;

import java.io.IOException;
import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import sgds.App;
import sgds.api.ApiResponse;
import sgds.model.Usuario;

public class HomeEnController {

    @FXML
    private Button btnAgendamentos;

    @FXML
    private Button btnPerfil;

    @FXML
    private Button btnPesquisar;

    @FXML
    private Button btnSair;

    @FXML
    private Button btnTriagem;

    @FXML
    private Label lblCargoOperador;

    @FXML
    private Label lblData;

    @FXML
    private Label lblNomeOperador;

    @FXML
    private ComboBox<String> listaSuspensaPrioridade;

    @FXML
    private AnchorPane painelAgendamentos;

    @FXML
    private AnchorPane painelPerfil;

    @FXML
    private AnchorPane painelTriagem;

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
    private TextField textPesquisarCpf;


    @FXML
    void encerrar(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void pesquisarCpf(ActionEvent event) {

    }
    
    @FXML
    void verAgendamentos(ActionEvent event) {
        painelAgendamentos.toFront();
    }

    @FXML
    void verPerfil(ActionEvent event) {
        painelPerfil.toFront();
    }

    @FXML
    void verTriagem(ActionEvent event) {
        painelTriagem.toFront();
    }
    
    private App app;

    public void setAppInstance(App app) {
        this.app = app;
    }

    public void iniciar(Usuario usuario) {
        lblNomeOperador.setText(usuario.getNome());
        lblCargoOperador.setText(usuario.getCargo());

        LocalDate dataAtual = LocalDate.now();
        lblData.setText(dataAtual.toString());
        
        perfilNomeOp.setText("Nome: "+usuario.getNome());
        perfilCpfOp.setText("CPF: "+usuario.getCpf());
        perfilEmailOp.setText("E-mail: "+usuario.getEmail());
        perfilCargoOp.setText("Cargo: "+usuario.getCargo());
        perfilCodOp.setText("COD: Não informado");
        perfilTelefoneOp.setText("Telefone: Não informado");

        listaSuspensaPrioridade.getItems().addAll(
        "EMERGÊNCIA",
        "MUITO URGENTE",
        "URGENTE",
        "POUCO URGENTE",
        "NÃO URGENTE");

    }
}
