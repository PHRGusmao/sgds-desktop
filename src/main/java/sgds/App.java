package sgds;

import java.io.IOException;
import java.nio.file.Paths;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sgds.controller.LoginController;
import sgds.api.ApiResponse;
import sgds.controller.HomeEnController;
import sgds.controller.HomeMedicoController;
import sgds.model.Usuario;

public class App extends Application
{
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        showLoginScene(); // Inicie com a cena de login
    }

    public void showLoginScene() throws Exception {
        FXMLLoader loader = new FXMLLoader(Paths.get("src\\resources\\templates\\login.fxml").toUri().toURL());
        Parent root = loader.load();

        LoginController controller = loader.getController();
        controller.setAppInstance(this); // Passe a instância de App para o controlador
        controller.iniciarLista();

        Scene scene = new Scene(root);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(scene);
        primaryStage.setTitle("SGDS - Login");
        primaryStage.centerOnScreen();
        primaryStage.setResizable(false);
        primaryStage.show();

    }

    public void changeScene(Parent root, String title, String cpf, FXMLLoader loader) throws IOException, InterruptedException {
        
        primaryStage.close();
        
        primaryStage = new Stage();
        
        Usuario usuario = new ApiResponse().getUsuarioApi(cpf);

        if(usuario.getCargo().equals("Médico")){
            HomeMedicoController homeMedicoController = loader.getController();
            homeMedicoController.setAppInstance(this);
            homeMedicoController.iniciar(usuario);
        }else{
            HomeEnController homeEnController = loader.getController();
            homeEnController.setAppInstance(this);
            homeEnController.iniciar(usuario);
        }

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle(title);
        primaryStage.setMaximized(true);

        primaryStage.show();
    }
    public static void main( String[] args )
    {
        launch(args);
    }
}
