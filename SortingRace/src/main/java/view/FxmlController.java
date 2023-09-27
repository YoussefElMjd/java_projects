package view;

import controller.Controller;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import model.*;

import java.net.Socket;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class FxmlController implements Initializable {
    @FXML
    private MenuItem aboutItem;

    @FXML
    private LineChart chart;

    @FXML
    private ChoiceBox<Configuration> configurationChoice;

    @FXML
    private TableColumn<SortResult, Long> durationCol;

    @FXML
    private Label leftStatus;

    @FXML
    private TableColumn<SortResult, String> nameCol;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private MenuItem quitItem;

    @FXML
    private Label rightStatus;

    @FXML
    private TableColumn<SortResult, Integer> sizeCol;

    @FXML
    private ChoiceBox<String> sortChoice;

    @FXML
    private Button start;

    @FXML
    private TableColumn<SortResult, Long> swapCol;

    @FXML
    private TableView<SortResult> table;

    @FXML
    private Spinner<Integer> threadSpinner;
    @FXML
    private XYChart.Series<Number, Number> seriesBubble, seriesMerge;
    @FXML
    private Menu viewMenu;

    @FXML
    private MenuItem simulations;

    private LocalDateTime begin;
    private LocalDateTime end;
    private int durationRun;
    ObservableList<SortResult> list;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        list = FXCollections.observableArrayList();
        this.leftStatus.setText("Thread actifs : " + Thread.activeCount());
        this.threadSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 9, 5));
        this.sortChoice.getItems().add("BUBBLE");
        this.sortChoice.getItems().add("MERGE");
        this.configurationChoice.getItems().add(Configuration.VERY_EASY);
        this.configurationChoice.getItems().add(Configuration.EASY);
        this.configurationChoice.getItems().add(Configuration.NORMAL);
        this.configurationChoice.getItems().add(Configuration.HARD);
        nameCol.setCellValueFactory(element -> new ReadOnlyObjectWrapper<>(element.getValue().sort()));
        durationCol.setCellValueFactory(element -> new ReadOnlyObjectWrapper<>(element.getValue().duration()));
        sizeCol.setCellValueFactory(element -> new ReadOnlyObjectWrapper<>(element.getValue().size()));
        swapCol.setCellValueFactory(element -> new ReadOnlyObjectWrapper<>(element.getValue().operation()));
        table.setItems(list);
        seriesBubble = new XYChart.Series<>();
        seriesBubble.setName("BUBBLE");
        seriesMerge = new XYChart.Series<>();
        seriesMerge.setName("MERGE");
        chart.getData().addAll(seriesMerge, seriesBubble);
        table.getSortOrder().add(sizeCol);
        sizeCol.setSortType(TableColumn.SortType.ASCENDING);
        progressBar.setProgress(0);
    }

    public void setMousseClicked(Controller controller) {
        this.start.setOnMouseClicked(e -> {
            clear();
            progressBar.setProgress(0);
            begin = LocalDateTime.now();
            controller.start(begin,threadSpinner.getValue(), sortChoice.getValue(), configurationChoice.getValue());
            end = LocalDateTime.now();
            durationRun = 0;
        });

        this.simulations.setOnAction(e ->{
            controller.openSimulations();
        });
    }

    public void clear() {
        table.getItems().clear();
    }

    public synchronized void update(Observable observable) {
        Platform.runLater(() -> {
            if (sortChoice.getValue().equals("BUBBLE")) {
                BubbleRunnable bubbleRunnable = (BubbleRunnable) observable;
                long nbOperation = bubbleRunnable.getOperation();
                int size = bubbleRunnable.getArray().length;
                Duration duration = bubbleRunnable.getDuration();
                durationRun += duration.toMillis();
                SortResult result = new SortResult(sortChoice.getValue(), size, nbOperation, duration.toMillis());
                if (bubbleRunnable.getEnd().toLocalTime().compareTo(end.toLocalTime()) == 0) {
                    end = bubbleRunnable.getEnd();
                }
                rightStatus.setText("Dernière exécution | Début : " + begin.toLocalTime() + " - Fin : " + end.toLocalTime() + " - Durée : " + durationRun + " ms");
                seriesBubble.getData().add(new XYChart.Data<>(size, nbOperation));
                table.getItems().add(result);
                table.sort();
                progressBar.setProgress(progressBar.getProgress() + 0.1);
            } else {
                MergeRunnable mergeRunnable = (MergeRunnable) observable;
                long nbOperation = mergeRunnable.getOperation();
                int size = mergeRunnable.getArray().length;
                Duration duration = mergeRunnable.getDuration();
                durationRun += duration.toMillis();
                rightStatus.setText("Dernière exécution | Début : " + mergeRunnable.getBegin() + " - Fin : " + mergeRunnable.getEnd() + " - Durée : " + durationRun + " ms");
                SortResult result = new SortResult(sortChoice.getValue(), size, nbOperation, duration.toMillis());
                if (mergeRunnable.getEnd().toLocalTime().compareTo(end.toLocalTime()) == 0) {
                    end = mergeRunnable.getEnd();
                }
                seriesMerge.getData().add(new XYChart.Data<>(size, nbOperation));
                table.getItems().add(result);
                table.sort();
                progressBar.setProgress(progressBar.getProgress() + 0.1);
            }
        });
    }
}