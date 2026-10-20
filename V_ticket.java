package com.example.ticket;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.ArrayList;

public class V_ticket {

    private static V_ticket instancia;

    private ComboBox<Object> cmbDestinatario;
    private TextArea txtTicket;
    private VBox pane;
    private Scene scen;

    private V_ticket(Stage stage, Usuario usuario) {

        Label titulo = new Label("Crear ticket");

        Label lblDestinatario = new Label("Destinatario:");
        cmbDestinatario = new ComboBox<>();

        ArrayList<Usuario> usuarios =
                Seguridad.getInstancia().dameUsuarios(usuario);
        ArrayList<Oficina> oficinas =
                Modelo.getInstancia().dameOficinas();

        for (Usuario u : usuarios) {
            if (!u.equals(usuario)) {
                cmbDestinatario.getItems().add(u);
            }
        }
        for (Oficina o : oficinas) {
            cmbDestinatario.getItems().add(o);
        }

        Label lblTexto = new Label("Texto:");
        txtTicket = new TextArea();

        txtTicket.setPromptText("Escribí tu ticket acá...");
        txtTicket.setPrefRowCount(8);
        txtTicket.setWrapText(true);

        Button btnVolver = new Button("Volver");
        Button btnEnviar = new Button("Enviar");

        pane = new VBox(10);
        pane.getChildren().addAll(
                titulo,
                lblDestinatario,
                cmbDestinatario,
                lblTexto,
                txtTicket,
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
            String texto = txtTicket.getText();

            if (destinatario == null || texto.trim().isEmpty()) {

                Alert alerta = new Alert(Alert.AlertType.WARNING);
                alerta.setTitle("Datos incompletos");
                alerta.setHeaderText(null);
                alerta.setContentText("Seleccioná un destinatario y escribí un ticket.");
                alerta.showAndWait();
                return;
            }

            Ticket ticket = new Ticket(destinatario, texto);
            Modelo.getInstancia().agregarTicket(ticket);
            if (destinatario instanceof Oficina) {
                Oficina ofi = (Oficina) destinatario;
                ofi.recibirTicket(ticket);
                Modelo.getInstancia().guardarOficinas(oficinas);
            } else if (destinatario instanceof Usuario) {
                Usuario UsuarioDestino = (Usuario) destinatario;

            }

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

    public static V_ticket getInstancia(Stage stage, Usuario usuario) {
        instancia = new V_ticket(stage, usuario);
        return instancia;
    }

}