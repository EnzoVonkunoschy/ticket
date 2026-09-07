package com.example.ticket;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class v_ListarUsuarios {
    private static v_ListarUsuarios instancia;

    private Scene scene;
    private TableView<Usuario> tableView;
    private ObservableList<Usuario> observableList;

    private v_ListarUsuarios(Stage stage, Usuario usuarioEnSesion){
        Button btnVolver = new Button("Volver");

        observableList = FXCollections.observableArrayList();
        tableView = new TableView<>(observableList);

        TableColumn<Usuario, String> nombreCol = new TableColumn<>("Nombre");
        nombreCol.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getNombre()));

        TableColumn<Usuario, String> mobilCol = new TableColumn<>("Móvil");
        mobilCol.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getMobil()));

        TableColumn<Usuario, String> rolCol = new TableColumn<>("Rol");
        rolCol.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getRol()));

        tableView.getColumns().addAll(nombreCol, mobilCol, rolCol);

        btnVolver.setOnAction(e->{
            v_Menu.getInstancia(stage, usuarioEnSesion);
        });

        VBox panel = new VBox(tableView, btnVolver);
        scene = new Scene(panel, 640, 480);
    }

    private void actualizarTabla(Usuario usuarioEnSesion){
        Controlador controlador = Controlador.getInstancia();
        ArrayList<Usuario> usuarios = controlador.dameUsuarios(usuarioEnSesion);
        observableList.setAll(usuarios);
    }

    public static v_ListarUsuarios getInstancia(Stage stage, Usuario usuarioEnSesion){
        if(instancia == null){
            instancia = new v_ListarUsuarios(stage, usuarioEnSesion);
        }

        instancia.actualizarTabla(usuarioEnSesion);
        stage.setTitle("Listado de Usuarios");
        stage.setScene(instancia.scene);
        stage.show();
        return instancia;
    }
}
