package com.example.ticket;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    private Stage primaryStage;

    @Override
    public void start(Stage stage) {
        this.primaryStage = stage;
        mostrarPantallaInicio();
    }



    public void mostrarPantallaInicio() {
        VBox layout = new VBox();
        Button btnContinuar = new Button("Ir al panel");
        btnContinuar.setOnAction(e -> mostrarPanelPrincipal());
        layout.getChildren().add(btnContinuar);



        Scene escena = new Scene(layout, 400, 300);
        primaryStage.setScene(escena);
        primaryStage.setTitle("Pantalla de inicio");
        primaryStage.show();
    }

    public void registrarIngreso(){

        Label     labTipoEquipo = new Label("Tipo de Equipo");
        TextField texTipoEquipo = new TextField();
        Button       butAceptar = new Button("Aceptar");

        VBox layout2 = new VBox();
        layout2.getChildren().addAll(labTipoEquipo, texTipoEquipo, butAceptar);

        Scene scene2 = new Scene(layout2, 400,300);
        primaryStage.setScene(scene2);
        primaryStage.setTitle("Registro de Ingreso");
        primaryStage.show();

        butAceptar.setOnAction(e -> mostrarPanelPrincipal() );
    };

    public void mostrarPanelPrincipal() {
        BorderPane layout = new BorderPane();
        layout.setCenter(new Label("Bienvenido al panel principal"));
        Button btnSalir = new Button("Cerrar");
        btnSalir.setOnAction(e -> primaryStage.close());
        layout.setBottom(btnSalir);

        Button btnRegistrarIngreso = new Button("Registrar Ingreso");
        //layout.getChildren().add(btnRegistrarIngreso);
        layout.setBottom(btnRegistrarIngreso);
        btnRegistrarIngreso.setOnAction(e -> registrarIngreso());

        Scene escena = new Scene(layout, 500, 400);
        primaryStage.setScene(escena);
        primaryStage.setTitle("Panel principal");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


}