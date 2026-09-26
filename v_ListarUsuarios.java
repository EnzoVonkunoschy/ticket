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

    Modelo modelo = Modelo.getInstancia();
    Controlador controlador = Controlador.getInstancia();

    private v_ListarUsuarios(Stage st, Usuario usu) {
        System.out.println("v_ListarUsuarios(Stage st, Usuario usu)");

        btnVolver   = new Button("Volver");
        btnEliminar = new Button("Eliminar");

        ArrayList<Usuario> colUsu = controlador.dameUsuarios(usu);
        if (colUsu == null) {
            colUsu = modelo.dameUsuarios();
        }
        System.out.println(colUsu);

        ObservableList<Usuario> observableList = FXCollections.observableArrayList(colUsu);

        TableView<Usuario> tableView = new TableView<>(observableList);

        TableColumn<Usuario, String> nombreCol = new TableColumn<>("Nombre");
        nombreCol.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getNombre()));

        TableColumn<Usuario, String> claveCol = new TableColumn<>("Clave");
        claveCol.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getClave()));

        TableColumn<Usuario, String> mobilCol = new TableColumn<>("Móvil");
        mobilCol.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getMobil()));

        TableColumn<Usuario, String> rolCol = new TableColumn<>("Rol");
        rolCol.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getRol()));

        tableView.getColumns().addAll(nombreCol, claveCol, mobilCol, rolCol);

        // comportamiento
        btnVolver.setOnAction(e -> {
            System.out.printf("Menu <-- ListarUsuarios");
            v_Menu.getInstancia(st, usu);
        });

        btnEliminar.setOnAction(e -> {
            Usuario seleccionada = tableView.getSelectionModel().getSelectedItem();
            if (seleccionada != null) {
                Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
                alerta.setTitle("Confirmar eliminación");
                alerta.setHeaderText("¿Estás seguro que querés eliminar este usuario?");
                alerta.setContentText("Usuario: " + seleccionada.getNombre());

                alerta.showAndWait().ifPresent(respuesta -> {
                    if (respuesta == ButtonType.OK) {
                        observableList.remove(seleccionada);
                        controlador.eliminarUsuario(seleccionada.getNombre(), usu);
                    }
                });
            }
        });

        VBox panel = new VBox(tableView);
        panel.getChildren().addAll(btnVolver, btnEliminar);
        scene = new Scene(panel, 400, 350);

        st.setTitle("Listado de Usuarios");
        st.setScene(scene);
    }

    public static v_ListarUsuarios getInstancia(Stage st, Usuario usu) {
        instancia = new v_ListarUsuarios(st, usu);
        st.setTitle("Listado de Usuarios");
        st.setScene(instancia.scene);
        st.show();
        return instancia;
    }
}
