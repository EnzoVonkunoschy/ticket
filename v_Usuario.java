package com.example.ticket;


import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

    public class v_Usuario {
        private static v_Usuario instancia;
        VBox panel;

        private Usuario enSesion;

        private Label  lblNombre;
        private Button btnCrearTicket;
        private Button btnVerTickets;
        private Scene  scen;

        private v_Usuario(Stage stage, Usuario usuario) {
            this.enSesion = usuario;

            panel = new VBox();

            panel.setAlignment(Pos.TOP_LEFT);

            lblNombre = new Label(usuario.getNombre());



            btnCrearTicket = new Button("Crear Ticket");
            btnVerTickets  = new Button("Ver mis Tickets");

            panel.getChildren().addAll(lblNombre, btnCrearTicket, btnVerTickets);

            scen = new Scene(panel, 300, 300);

            stage.setTitle(usuario.getRol());
            stage.setScene(scen);
            stage.show();
        }


        public static v_Usuario getInstancia(Stage stage, Usuario usuario) {
            if (instancia == null) {
                instancia = new v_Usuario(stage, usuario);
            } else {
                instancia.enSesion = usuario;
                instancia.lblNombre.setText(usuario.getNombre());
                stage.setTitle(usuario.getRol());
                stage.setScene(instancia.scen);
                stage.show();
            }
            return instancia;
        }

    }

