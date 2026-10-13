package com.example.ticket;

import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;


public class v_CrearMensaje {

    private static v_CrearMensaje instancia;

    private Label lblUsuario;
    private Label lblDestinatario;
    private Label lblTexto;

    private ComboBox<String> cmbDestinatario;
    private TextArea txtTexto;

    private Button btnVolver;
    private Button btnEnviar;

    private Scene scene;

    // Se guarda el usuario actual para que la instancia reutilizada
    // siempre vuelva a la vista correcta.
    private Usuario usuarioEnSesion;

    private v_CrearMensaje(Stage stage, Usuario usu) {
        usuarioEnSesion = usu;

        VBox panel = new VBox();
        panel.setSpacing(10);

        lblUsuario = new Label(usu.getNombre());
        lblDestinatario = new Label("Destinatario:");
        lblTexto = new Label("Texto:");

        cmbDestinatario = new ComboBox<>();
        txtTexto = new TextArea();

        btnVolver = new Button("Volver");
        btnEnviar = new Button("Enviar");

        // Carga inicial de usuarios y oficinas.
        cargarDestinatarios(usu);

        panel.getChildren().addAll(
                lblUsuario,
                lblDestinatario,
                cmbDestinatario,
                lblTexto,
                txtTexto,
                btnVolver,
                btnEnviar
        );


        btnVolver.setOnAction(e -> {
            v_Usuario.getInstancia(stage, usuarioEnSesion);
        });

        btnEnviar.setOnAction(e -> {
            String destinatario = cmbDestinatario.getValue();
            String texto = txtTexto.getText();

            if (destinatario == null || destinatario.isBlank()
                    || texto == null || texto.isBlank()) {

                Alert alerta = new Alert(Alert.AlertType.WARNING);
                alerta.setTitle("Datos incompletos");
                alerta.setHeaderText(null);
                alerta.setContentText("Seleccione un destinatario y escriba un mensaje.");
                alerta.showAndWait();
                return;
            }

            Mensaje mensaje = new Mensaje(destinatario, texto.trim());

            // Requisito de la consigna: mostrar el mensaje por consola.
            System.out.println(mensaje.toString());

            // Limpiamos los controles para la próxima vez que se abra la vista.
            txtTexto.clear();
            cmbDestinatario.getSelectionModel().clearSelection();

            v_Usuario.getInstancia(stage, usuarioEnSesion);
        });

        scene = new Scene(panel, 640, 480);

        stage.setTitle("Crear Mensaje - " + usu.getNombre());
        stage.setScene(scene);
        stage.show();
    }

    private void cargarDestinatarios(Usuario usu) {
        Controlador controlador = Controlador.getInstancia();
        ArrayList<String> destinatarios = new ArrayList<>();

        ArrayList<Usuario> usuarios = controlador.dameUsuarios(usu);
        if (usuarios != null) {
            for (Usuario usuario : usuarios) {
                destinatarios.add("Usuario: " + usuario.getNombre());
            }
        }

        ArrayList<Oficina> oficinas = controlador.dameOficinasParaMensajes(usu);
        if (oficinas != null) {
            for (Oficina oficina : oficinas) {
                destinatarios.add("Oficina: " + oficina.getNombre());
            }
        }

        cmbDestinatario.setItems(FXCollections.observableArrayList(destinatarios));
    }

    /**
     * Mantiene el patrón Singleton utilizado
     */
    public static v_CrearMensaje getInstancia(Stage stage, Usuario usu) {
        if (instancia == null) {
            instancia = new v_CrearMensaje(stage, usu);
        } else {
            instancia.usuarioEnSesion = usu;
            instancia.lblUsuario.setText(usu.getNombre());
            instancia.cargarDestinatarios(usu);

            stage.setTitle("Crear Mensaje - " + usu.getNombre());
            stage.setScene(instancia.scene);
            stage.show();
        }

        return instancia;
    }
}
