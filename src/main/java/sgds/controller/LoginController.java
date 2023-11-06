package sgds.controller;

import java.io.IOException;
import java.nio.file.Paths;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import sgds.App;
import sgds.api.ApiResponse;
import sgds.model.Usuario;

public class LoginController {

    @FXML
    private Button btnCadastrar;

    @FXML
    private Button btnEntrar;

    @FXML
    private Button btnFechar;

    @FXML
    private Button btnRealizarLogin;

    @FXML
    private Button btnRegistrar;

    @FXML
    private ComboBox<String> listaSuspensa;

    @FXML
    private ImageView mostrarSenha;

    @FXML
    private AnchorPane painelCadastro;

    @FXML
    private AnchorPane painelLogin;

    @FXML
    private AnchorPane painelPrincipal;

    @FXML
    private PasswordField textConfirmarSenhaCadastro;

    @FXML
    private TextField textConfirmarSenhaVisivelCadastro;

    @FXML
    private TextField textCpfCadastro;

    @FXML
    private TextField textEmailCadastro;

    @FXML
    private TextField textNomeCompletoCadastro;

    @FXML
    private PasswordField textSenha;

    @FXML
    private PasswordField textSenhaCadastro;

    @FXML
    private TextField textSenhaVisivelCadastro;

    @FXML
    private TextField textUsuario;

    private boolean visibilidade;

    private Usuario usuario;


    @FXML
    void cadastrar(ActionEvent event) throws IOException, InterruptedException {
        if(listaSuspensa.getValue() != null 
        && textNomeCompletoCadastro.getText() != ""
        && textCpfCadastro.getText() != ""
        && textEmailCadastro.getText() != ""
        && textSenhaCadastro.getText() != ""
        && listaSuspensa.getValue() != "")
        {
            if(textConfirmarSenhaCadastro.getText().equals(textSenhaCadastro.getText())){
                Usuario usuario = new Usuario(
                    textNomeCompletoCadastro.getText(),
                    textCpfCadastro.getText(),
                    textEmailCadastro.getText(),
                    textSenhaCadastro.getText(),
                    listaSuspensa.getValue()
                );

                boolean cadastro = new ApiResponse().cadastro(usuario);
                
                if(cadastro){
                    textNomeCompletoCadastro.setText(null);
                    textCpfCadastro.setText(null);
                    textEmailCadastro.setText(null);
                    textSenhaCadastro.setText(null);
                    textConfirmarSenhaCadastro.setText(null);
                    listaSuspensa.setPromptText("Cargo");
                }
            }else{
                JOptionPane.showMessageDialog(null, "Erro no cadastro.\nSenhas incongruentes!", "Aviso do sistema", JOptionPane.ERROR_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Erro no cadastro.\nPreencha todos os campos!", "Aviso do sistema", JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    void exibirPainelCadastro(ActionEvent event) {
        painelCadastro.toFront();
    }

    @FXML
    void exibirPainelLogin(ActionEvent event) {
        painelLogin.toFront();
    }

    @FXML
    void exibirSenha(MouseEvent event) {
        if(visibilidade){
            textSenhaCadastro.setVisible(false);
            textSenhaVisivelCadastro.setText(textSenhaCadastro.getText());
            textSenhaVisivelCadastro.setVisible(true);

            textConfirmarSenhaCadastro.setVisible(false);
            textConfirmarSenhaVisivelCadastro.setText(textConfirmarSenhaCadastro.getText());
            textConfirmarSenhaVisivelCadastro.setVisible(true);

            visibilidade = false;
        }else{
            textSenhaVisivelCadastro.setVisible(false);
            textSenhaCadastro.setText(textSenhaVisivelCadastro.getText());
            textSenhaCadastro.setVisible(true);

            textConfirmarSenhaVisivelCadastro.setVisible(false);
            textConfirmarSenhaCadastro.setText(textConfirmarSenhaVisivelCadastro.getText());
            textConfirmarSenhaCadastro.setVisible(true);

            visibilidade = true;
        }
    }

    @FXML
    void encerrar(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void logar(ActionEvent event) throws IOException, InterruptedException {
        fazerLogin();
    }

    @FXML
    void teclaPressionadaUsuario(KeyEvent event) throws IOException, InterruptedException {
        if (event.getCode() == KeyCode.ENTER) {
            fazerLogin();
        }
    }

    @FXML
    void teclaPressionadaSenha(KeyEvent event) throws IOException, InterruptedException {
        if (event.getCode() == KeyCode.ENTER) {
            fazerLogin();
        }
    }

    private App app;

    public void setAppInstance(App app) {
        this.app = app;
    }

    public void fazerLogin() throws IOException, InterruptedException{
        if(textUsuario.getText() != "" && textSenha.getText() != ""){
            int logado = new ApiResponse().login(textUsuario.getText(), textSenha.getText());
            if(logado != 0){
                if(logado == 1){
                    FXMLLoader homeLoader = new FXMLLoader(Paths.get("src\\resources\\templates\\homeMedico.fxml").toUri().toURL());
                    Parent homeRoot = homeLoader.load();
                    app.changeScene(homeRoot, "SGDS - Home", textUsuario.getText(), homeLoader);
                }else{
                    FXMLLoader homeLoader = new FXMLLoader(Paths.get("src\\resources\\templates\\homeEnfermagem.fxml").toUri().toURL());
                    Parent homeRoot = homeLoader.load();
                    app.changeScene(homeRoot, "SGDS - Home", textUsuario.getText(), homeLoader);
                }
            }
        }else{
            JOptionPane.showMessageDialog(null, "Preencha os campos!", "Aviso do Sistema", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void iniciarLista(){
        visibilidade = true;
        listaSuspensa.getItems().addAll(
        "Enfermagem",
        "Médico",
        "Técnico de Manutenção",
        "Administrador"
        );

        
    }
}


