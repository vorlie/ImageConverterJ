package com.vorlie.ImageConverter;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
//import javafx.geometry.Pos;
import javafx.stage.FileChooser;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

import javafx.scene.image.Image;

public class ImageConverter extends Application {

    private Label selectedFileLabel;
    private File selectedFile;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/vorlie/ImageConverter/icon.png"))));

        Button selectButton = new Button("Select File");
        selectButton.getStyleClass().add("button");
        selectButton.setOnAction(e -> selectFile());

        selectedFileLabel = new Label();
        selectedFileLabel.getStyleClass().add("label");

        ComboBox<String> formatComboBox = new ComboBox<>();
        formatComboBox.getItems().addAll("PNG", "JPEG", "GIF", "BMP");
        formatComboBox.getStyleClass().add("combobox");
        formatComboBox.setValue("PNG");

        Button convertButton = new Button("Convert");
        convertButton.setId("convertButton");
        convertButton.setOnAction(e -> convertFile(selectedFile, formatComboBox.getValue()));

        VBox root = new VBox(10, selectButton, formatComboBox, convertButton, selectedFileLabel);

        Scene scene = new Scene(root, 300, 200);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/vorlie/ImageConverter/styles.css")).toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Image Format Converter");
        primaryStage.show();
    }


    private void selectFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image File");
        selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            selectedFileLabel.setText("Selected File: " + selectedFile.getName());
        }
    }

    private void convertFile(File file, String outputFormat) {
        if (file == null) {
            displayNotification("Error", "No file selected.");
            return;
        }

        try {
            BufferedImage image = ImageIO.read(file);
            String fileExtension = outputFormat.toLowerCase();
            File outputFile = new File(file.getParent(), "converted_" + file.getName() + "." + fileExtension);
            ImageIO.write(image, fileExtension, outputFile);
            displayNotification("Conversion Complete", "Image converted successfully to " + outputFormat + " format.");
        } catch (IOException e) {
            displayNotification("Error", "Error converting image: " + e.getMessage());
        }
    }

    private void displayNotification(String title, String message) {
        System.out.println(title + ": " + message);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
