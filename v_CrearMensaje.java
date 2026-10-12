package com.example.ticket;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class v_CrearMensaje {
    private static v_CrearMensaje instancia;

    private Scene scene;
    private ComboBox<Object> cmbDestinatarios;
    private TextArea txtMensaje;
    private Button btnVolver;
    private Button btnEnviar;

    private Modelo modelo = Modelo.getInstancia();

    private v_CrearMensaje(Stage stage, Usuario usuario) {
        VBox root = new VBox(15);
        root.setPadding(new Insets(0, 0, 20, 0));
        root.setStyle("-fx-background-color: white;");

        // 1. Cabecera superior con el usuario logueado
        HBox header = new HBox();
        header.setStyle("-fx-background-color: black;");
        header.setPadding(new Insets(12, 15, 12, 15));
        Label lblUsuario = new Label(usuario != null ? usuario.getNombre() : "Usuario");
        lblUsuario.setTextFill(Color.WHITE);
        lblUsuario.setFont(Font.font("System", FontWeight.BOLD, 14));
        header.getChildren().add(lblUsuario);

        // 2. Contenedor del formulario
        VBox form = new VBox(10);
        form.setPadding(new Insets(10, 20, 10, 20));

        // Selector de Destinatario (ComboBox)
        HBox destBox = new HBox(10);
        destBox.setAlignment(Pos.CENTER_LEFT);
        Label lblDest = new Label("Destinatario:");
        cmbDestinatarios = new ComboBox<>();
        cmbDestinatarios.setPrefWidth(220);

        // Carga de destinatarios desde el Modelo (Usuarios y Oficinas)
        ObservableList<Object> listaDestinatarios = FXCollections.observableArrayList();
        if (modelo.dameUsuarios() != null) {
            for (Usuario u : modelo.dameUsuarios()) {
                // Solo agrega al destinatario si no es el usuario en sesión
                if (usuario != null && !u.getNombre().equalsIgnoreCase(usuario.getNombre())) {
                    listaDestinatarios.add(u);
                }
            }
        }
        if (modelo.dameOficinas() != null) {
            listaDestinatarios.addAll(modelo.dameOficinas());
        }
        cmbDestinatarios.setItems(listaDestinatarios);

        // Formateador de texto para cada ítem del ComboBox
        cmbDestinatarios.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(Object item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else if (item instanceof Usuario) {
                    setText("Usuario: " + ((Usuario) item).getNombre());
                } else if (item instanceof Oficina) {
                    setText("Oficina: " + ((Oficina) item).getNombre());
                } else {
                    setText(item.toString());
                }
            }
        });
        cmbDestinatarios.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(Object item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else if (item instanceof Usuario) {
                    setText("Usuario: " + ((Usuario) item).getNombre());
                } else if (item instanceof Oficina) {
                    setText("Oficina: " + ((Oficina) item).getNombre());
                } else {
                    setText(item.toString());
                }
            }
        });

        if (!listaDestinatarios.isEmpty()) {
            cmbDestinatarios.getSelectionModel().selectFirst();
        }

        destBox.getChildren().addAll(lblDest, cmbDestinatarios);

        // Cuadro de texto para escribir el mensaje
        Label lblTexto = new Label("Texto:");
        txtMensaje = new TextArea();
        txtMensaje.setPrefRowCount(6);
        txtMensaje.setWrapText(true);

        form.getChildren().addAll(destBox, lblTexto, txtMensaje);

        // 3. Botones Volver y Enviar
        HBox botonesBox = new HBox(15);
        botonesBox.setAlignment(Pos.CENTER);
        botonesBox.setPadding(new Insets(10, 20, 0, 20));

        btnVolver = new Button("Volver");
        btnVolver.setPrefWidth(120);
        btnVolver.setPrefHeight(35);
        btnVolver.setStyle("-fx-background-color: #0000D0; -fx-text-fill: white; -fx-font-weight: bold;");

        btnEnviar = new Button("Enviar");
        btnEnviar.setPrefWidth(120);
        btnEnviar.setPrefHeight(35);
        btnEnviar.setStyle("-fx-background-color: #0000D0; -fx-text-fill: white; -fx-font-weight: bold;");

        botonesBox.getChildren().addAll(btnVolver, btnEnviar);

        // --- Acciones de los botones ---
        btnVolver.setOnAction(e -> {
            v_Usuario.getInstancia(stage, usuario);
        });

        btnEnviar.setOnAction(e -> {
            Object destinatario = cmbDestinatarios.getSelectionModel().getSelectedItem();
            String texto = txtMensaje.getText();

            // Aquí se crea el objeto usando el constructor de Mensaje
            Mensaje nuevoMensaje = new Mensaje(usuario, destinatario, texto);

            // Imprime en consola según la consigna
            System.out.println(nuevoMensaje.toString());

            // Vuelve a la pantalla principal del usuario
            v_Usuario.getInstancia(stage, usuario);
        });

        root.getChildren().addAll(header, form, botonesBox);

        scene = new Scene(root, 360, 420);
        stage.setScene(scene);
        stage.setTitle("Crear Mensaje");
        stage.show();
    }

   public static v_CrearMensaje getInstancia(Stage stage, Usuario usuario) {
        instancia = new v_CrearMensaje(stage, usuario);
        return instancia;
    }
}