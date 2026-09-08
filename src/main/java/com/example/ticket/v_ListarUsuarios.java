package com.example.ticket;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class v_ListarUsuarios {
    public static v_ListarUsuarios instancia;

    private Scene scene;
    private Button btnVolver;
    private Button btnEliminar;

    private Controlador controlador = Controlador.getInstancia();

    private v_ListarUsuarios(Stage st, Usuario usu) {
        btnVolver   = new Button("Volver");
        btnEliminar = new Button("Eliminar");


        ArrayList<Usuario> colUsu = controlador.dameUsuarios(usu);
        if (colUsu == null) {
            colUsu = new ArrayList<>();
        }

        ObservableList<Usuario> observableList = FXCollections.observableArrayList(colUsu);
        TableView<Usuario> tableView = new TableView<>(observableList);


        TableColumn<Usuario, String> colNombre = new TableColumn<>("Nombre");
        colNombre.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getNombre()));

        TableColumn<Usuario, String> colMobil = new TableColumn<>("Móvil");
        colMobil.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getMobil()));

        TableColumn<Usuario, String> colRol = new TableColumn<>("Rol");
        colRol.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getRol()));

        tableView.getColumns().addAll(colNombre, colMobil, colRol);


        btnVolver.setOnAction(e -> {
            v_Menu.getInstancia(st, usu);
        });


        btnEliminar.setOnAction(e -> {
            Usuario seleccionado = tableView.getSelectionModel().getSelectedItem();
            if (seleccionado != null) {
                Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
                alerta.setTitle("Confirmar eliminación");
                alerta.setHeaderText("¿Estás seguro que querés eliminar este usuario?");
                alerta.setContentText("Usuario: " + seleccionado.getNombre());

                alerta.showAndWait().ifPresent(respuesta -> {
                    if (respuesta == ButtonType.OK) {
                        observableList.remove(seleccionado);
                        controlador.eliminarUsuario(seleccionado.getNombre(), usu);
                    }
                });
            }
        });

        VBox panel = new VBox(10);
        panel.getChildren().addAll(tableView, btnVolver, btnEliminar);

        scene = new Scene(panel, 400, 350);

        st.setTitle("Listado de Usuarios - " + usu.getNombre());
        st.setScene(scene);
        st.show();
    }

    public static v_ListarUsuarios getInstancia(Stage st, Usuario usu) {
        if (instancia == null) {
            instancia = new v_ListarUsuarios(st, usu);
        } else {

            instancia = new v_ListarUsuarios(st, usu);
        }
        return instancia;
    }
}