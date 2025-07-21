package com.example.ticket;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class HelloApplication extends Application {

    private Stage primaryStage;
    private Usuario usuarioEnSesion = null;

    boolean produccion = false;

    @Override
    public void start(Stage stage) {
        if(produccion) {
            this.primaryStage = stage;
            mostrarPantallaInicio();
        }else {
            test();
        }
    }

    public void test(){
        System.out.println("Modo test...");
        Usuario alfredo = new Usuario("Alfredo","1234","12345678","admin");
        Entrada miEntrada = new Entrada("Texto de la entrada.",alfredo,"mesa");
        Ticket miTicket = new Ticket(miEntrada);
        Entrada otraEntrada = new Entrada("Recordas encargar bidones de agua!",alfredo,"mesa");
        miTicket.agregarEntrada(otraEntrada);
        System.out.println(miTicket);

        Oficina miOficina = new Oficina("mesa");
        miOficina.agregarUsuario(alfredo);
        miOficina.recibirTicket(miTicket);
        System.out.println(miOficina);

    }



    public void mostrarPantallaInicio() {
        VBox layout = new VBox();

        Label     labUser = new Label("Usuario");
        TextField texUser = new TextField();
        Label     labPass = new Label("Contraseña");
        TextField texPass = new TextField();

        Button btnContinuar = new Button("Ir al panel");
        btnContinuar.setOnAction(e -> {

            System.out.println(texUser.getText());
            System.out.println(texPass.getText());

            boolean boo_validar = Seguridad.validar(texUser.getText(),texPass.getText());
            if(boo_validar && (usuarioEnSesion == null)) {
                usuarioEnSesion = Seguridad.dameUsuario(texUser.getText(),texPass.getText());
                System.out.println("Usuario en sesión: "+usuarioEnSesion);
                mostrarPanelPrincipal();
                System.out.println("mostrarPantallaInicio -> mostrarPanelPrincipal");
            }else{
                mostrarPantallaInicio();
                System.out.println("Error de login");
            }
        });
        layout.getChildren().addAll(labUser, texUser, labPass, texPass, btnContinuar);



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

    public void nuevoTicket(){

        Label      labOficina = new Label("Oficina");
        TextField  texOficina = new TextField();
        Button butNuevoTicket = new Button("Aceptar");

        VBox layNuevoTicket = new VBox();
        layNuevoTicket.getChildren().addAll(labOficina, texOficina, butNuevoTicket);

        Scene sceNuevoTicket = new Scene(layNuevoTicket, 400,300);
        primaryStage.setScene(sceNuevoTicket);
        primaryStage.setTitle("Nuevo Ticket");
        primaryStage.show();

        butNuevoTicket.setOnAction(e -> {

            mostrarPanelPrincipal();
        } );
    };

    public void mostrarPanelPrincipal() {
        Pane layout = new Pane();

        Button btnSalir = new Button("Cerrar");
        btnSalir.setLayoutX(10);
        btnSalir.setLayoutY(10);

        btnSalir.setOnAction(e -> primaryStage.close());

        Button btnRegistrarIngreso = new Button("Registrar Ingreso");
        btnRegistrarIngreso.setLayoutX(10);
        btnRegistrarIngreso.setLayoutY(50);

        btnRegistrarIngreso.setOnAction(e -> registrarIngreso());

        Button btnNuevoTicket = new Button("Nuevo Ticket");
        btnNuevoTicket.setLayoutX(10);
        btnNuevoTicket.setLayoutY(10);
        btnNuevoTicket.setOnAction(e-> nuevoTicket());

        layout.getChildren().addAll(btnSalir, btnRegistrarIngreso, btnNuevoTicket);

        Scene escena = new Scene(layout, 500, 400);
        primaryStage.setScene(escena);
        primaryStage.setTitle("Panel principal");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
        /*
        ArrayList<Usuario> usu = Modelo.dameUsuarios();
        for(int i=0 ; i<usu.size() ; i++){
            System.out.println(usu.get(i));
        }*/
        System.out.println(Seguridad.dameUsuario("algo","algo"));;
    }


}