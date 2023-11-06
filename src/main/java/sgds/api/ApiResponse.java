package sgds.api;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import javax.swing.JOptionPane;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import sgds.model.Usuario;


public class ApiResponse {
    final String API_URL = "https://www.panastech.com/";

    public int login(String cpf, String senha) throws IOException, InterruptedException{

        HttpRequest request = HttpRequest.newBuilder()
        .GET()
        .uri(URI.create(API_URL+"usuarios/search/findByCpf?cpf="+cpf))
        .build();

        HttpClient client = HttpClient.newBuilder().build();

        HttpResponse<String> response = (HttpResponse<String>) client.send(request, BodyHandlers.ofString());
      
        Usuario usuario;

        if (response.statusCode() == 200) {
            ObjectMapper objectMapper = new ObjectMapper();
            usuario = objectMapper.readValue(response.body(), Usuario.class);

        } else {
            JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos", "Aviso do sistema", JOptionPane.ERROR_MESSAGE);
            throw new IOException("Falha na solicitação HTTP: " + response.statusCode());
        }
        
        if(autorizar(usuario, senha)){
            if(usuario.getCargo().equals("Médico")){
                JOptionPane.showMessageDialog(null, "Logado com sucesso!","Aviso do sistema",JOptionPane.INFORMATION_MESSAGE);
                return 1;
            }else if(usuario.getCargo().equals("Enfermagem")){
                JOptionPane.showMessageDialog(null, "Logado com sucesso!","Aviso do sistema",JOptionPane.INFORMATION_MESSAGE);
                return 2;
            }else{
                JOptionPane.showMessageDialog(null, "Acesso apenas para Profissionais da Saúde","Aviso do sistema",JOptionPane.INFORMATION_MESSAGE);
                return 0;
            }
        }else{
            JOptionPane.showMessageDialog(null, "Usuario ou senha incorretos!","Aviso do sistema",JOptionPane.ERROR_MESSAGE);
            return 0;
        }
    }

    public boolean autorizar(Usuario usuario, String senha){
        if(usuario.getSenha().equals(senha)){
            return true;
        }else{
            return false;
        }
    }
 
    public boolean cadastro(Usuario usuario) throws IOException, InterruptedException{
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(usuario);

        HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(API_URL+"usuarios"))
        .POST(HttpRequest.BodyPublishers.ofString(json))
        .build();

        HttpClient httpClient = HttpClient.newHttpClient();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if(response.statusCode() == 200 || response.statusCode() == 201){
            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!", "Aviso do sistema", JOptionPane.INFORMATION_MESSAGE);
            return true;
        }else if(response.statusCode() == 409){
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar.\nUsuário já existente!", "Aviso do sistema", JOptionPane.ERROR_MESSAGE);
            return false;
        }else{
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar!\n", "Aviso do sistema", JOptionPane.ERROR_MESSAGE);
            throw new IOException("Falha na solicitação HTTP: " + response.statusCode());
        }
    }

    public Usuario getUsuarioApi(String cpf) throws IOException, InterruptedException{
        
        HttpRequest request = HttpRequest.newBuilder()
        .GET()
        .uri(URI.create(API_URL+"usuarios/search/findByCpf?cpf="+cpf))
        .build();

        HttpClient client = HttpClient.newBuilder().build();

        HttpResponse<String> response = (HttpResponse<String>) client.send(request, BodyHandlers.ofString());
      
        Usuario usuario;

        ObjectMapper objectMapper = new ObjectMapper();

        usuario = objectMapper.readValue(response.body(), Usuario.class);

        return usuario;
    }
}
