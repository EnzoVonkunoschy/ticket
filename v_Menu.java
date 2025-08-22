package com.example.ticketlujan3;


import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class v_Menu {

    private static v_Menu instancia;
    VBox panel;

    Label label;

    Button btnNuevaOficina;
    Button btnListarOficinas;
    Button btnNuevoUsuario;
    Button btnSalir;

    Scene scen;

    private v_Menu(Stage stage, Usuario usu){
        panel = new VBox();

        label = new Label("Menú:");

        btnNuevaOficina   = new Button("Nueva Oficina");
        btnListarOficinas = new Button("Listar Oficinas");
        btnNuevoUsuario   = new Button("Nuevo Usuario");
        btnSalir          = new Button("Salir");

        panel.getChildren().addAll(label, btnNuevaOficina, btnListarOficinas, btnSalir);

        btnNuevaOficina.setOnAction(e->{
            v_NuevaOficina nuevaOficina = v_NuevaOficina.getInstancia(stage, usu);
        });
        btnListarOficinas.setOnAction(e->{
            v_ListarOficinas.getInstancia(stage, usu);
        });

        scen = new Scene(panel, 640,480);

        stage.setTitle("Menú: "+usu.getNombre()+" - "+ usu.getRol());
        stage.setScene(scen);
        stage.show();
    }

    public static v_Menu getInstancia(Stage stage, Usuario usu){
        if(instancia == null){
            instancia = new v_Menu(stage, usu);
            System.out.println("Se instanció v_Menu");
        }else{
            stage.setTitle("Menú: "+usu.getNombre()+" - "+ usu.getRol());
            stage.setScene(instancia.scen);
            stage.show();
            System.out.println("Se reutilizó v_Menu");
        }
        return instancia;
    }

}
