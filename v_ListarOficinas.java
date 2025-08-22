package com.example.ticketlujan3;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.text.Element;
import javax.swing.text.html.ListView;
import java.util.ArrayList;
import java.util.List;

public class v_ListarOficinas {
    public static v_ListarOficinas instancia;

    private Scene scene;
    private Button btnVolver;
    private Button btnEliminar;

    Modelo modelo = Modelo.getInstancia();
    Controlador controlador = Controlador.getInstancia();

    private v_ListarOficinas(Stage st, Usuario usu) {
        System.out.println("v_ListarOficinas(Stage st, Usuario usu)");

        btnVolver   = new Button("Volver");
        btnEliminar = new Button("Eliminar");

        ArrayList<Oficina> colOfi = modelo.dameOficinas();/**/
        System.out.println(colOfi);

        ObservableList<Oficina> observableList = FXCollections.observableArrayList(colOfi);

        TableView<Oficina> tableView = new TableView<>(observableList);

        TableColumn<Oficina, String> nombreCol = new TableColumn<>("Nombre");
        nombreCol.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getNombre()));

        TableColumn<Oficina, String> edadCol = new TableColumn<>("Edad");
        edadCol.setCellValueFactory(data -> new ReadOnlyStringWrapper(String.valueOf(data.getValue().getNombre())));

        tableView.getColumns().addAll(nombreCol, edadCol);

        // comportamiento
        btnVolver.setOnAction(e->{
            System.out.printf("Menu <-- ListarOficinas");
            v_Menu.getInstancia(st, usu);
        });


        btnEliminar.setOnAction(e -> {
            Oficina seleccionada = tableView.getSelectionModel().getSelectedItem();
            if (seleccionada != null) {
                Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
                alerta.setTitle("Confirmar eliminación");
                alerta.setHeaderText("¿Estás seguro que querés eliminar esta oficina?");
                alerta.setContentText("Oficina: " + seleccionada.getNombre());

                alerta.showAndWait().ifPresent(respuesta -> {
                    if (respuesta == ButtonType.OK) {
                        observableList.remove(seleccionada);
                        controlador.eliminarOficina(usu, seleccionada.getNombre());
                    }
                });
            }
        });

        VBox panel = new VBox(tableView);
        panel.getChildren().addAll(btnVolver, btnEliminar);
        Scene scene = new Scene(panel,300, 300);

        st.setTitle("Listado de Oficinas");
        st.setScene(scene);
    }

    public static v_ListarOficinas getInstancia(Stage st, Usuario usu) {
        if (instancia == null) {
            v_ListarOficinas instancia = new v_ListarOficinas(st, usu);
        } else {
            st.setTitle("Listar Oficinas");
            st.setScene(instancia.scene);
            st.show();
        }

        return instancia;
    }
}