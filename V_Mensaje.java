package com.example.ticket;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.ArrayList;

public class V_Mensaje {

    private static V_Mensaje instancia;

    private ComboBox<Object> cmbDestinatario;
    private TextArea txtMensaje;
    private VBox pane;
    private Scene scen;

    private V_Mensaje(Stage stage, Usuario usuario) {

        Label titulo = new Label("Crear mensaje");

        Label lblDestinatario = new Label("Destinatario:");
        cmbDestinatario = new ComboBox<>();

        ArrayList<Usuario> usuarios =
                Seguridad.getInstancia().dameUsuarios(usuario);
        ArrayList<Oficina> oficinas =
                Modelo.getInstancia().dameOficinas();

        for (Usuario u : usuarios) {
            if (!u.equals(usuario)) {
                cmbDestinatario.getItems().add(u.getNombre());
            }
        }
        for (Oficina o : oficinas) {
            cmbDestinatario.getItems().add(o);
        }

        Label lblTexto = new Label("Texto:");
        txtMensaje = new TextArea();

        txtMensaje.setPromptText("Escribí tu mensaje acá...");
        txtMensaje.setPrefRowCount(8);
        txtMensaje.setWrapText(true);

        Button btnVolver = new Button("Volver");
        Button btnEnviar = new Button("Enviar");

        pane = new VBox(10);
        pane.getChildren().addAll(
                titulo,
                lblDestinatario,
                cmbDestinatario,
                lblTexto,
                txtMensaje,
                btnVolver,
                btnEnviar
        );

        pane.setStyle("-fx-padding: 20;");

        scen = new Scene(pane, 400, 450);


        btnVolver.setOnAction(e -> {
            instancia = null;
            v_Usuario.getInstancia(stage, usuario);
        });

        btnEnviar.setOnAction(e -> {

            Object destinatario = cmbDestinatario.getValue();
            String texto = txtMensaje.getText();

            if (destinatario == null || texto.trim().isEmpty()) {

                Alert alerta = new Alert(Alert.AlertType.WARNING);
                alerta.setTitle("Datos incompletos");
                alerta.setHeaderText(null);
                alerta.setContentText("Seleccioná un destinatario y escribí un mensaje.");
                alerta.showAndWait();
                return;
            }

            Entrada entrada = new Entrada(texto, usuario, destinatario);
            Ticket ticket = new Ticket(entrada);
            System.out.println(ticket);

            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Ticket creado");
            alerta.setHeaderText(null);
            alerta.setContentText("El ticket se creó correctamente.");
            alerta.showAndWait();

            instancia = null;
            v_Usuario.getInstancia(stage, usuario);
        });

        stage.setTitle(usuario.getNombre());
        stage.setScene(scen);
        stage.show();
    }

    public static V_Mensaje getInstancia(Stage stage, Usuario usuario) {
        instancia = new V_Mensaje(stage, usuario);
        return instancia;
    }

}