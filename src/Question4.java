import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.layout.BorderPane;
import javafx.geometry.Insets;
import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class Question4 extends Application {
	static int[] counts;

	@Override
	public void start(Stage primaryStage) {
		HBox controlBox = new HBox(10);
		BorderPane pane = new BorderPane();
		Pane chart = new Pane();
		
		Histogram hist = new Histogram();
		// Set text field
		TextField textFilename = new TextField();
		// Set button
		Button buttonView = new Button("View");

		// Set text field to listen for ENTER
		textFilename.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ENTER) {
				readFile(textFilename,hist);
			}
		});
		// Set action on button pressed
		buttonView.setOnAction(e -> readFile(textFilename,hist));
		// Set control menu
		controlBox.getChildren().addAll(new Label("Filename"), textFilename, buttonView);
		// Set root
		pane.setCenter(hist);
		pane.setBottom(controlBox);
		pane.setMargin(hist, new Insets(20));

		// Create a scene and place it in the stage
		Scene scene = new Scene(pane);
		primaryStage.setTitle("Question 4"); // Set the stage title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show(); // Display the stage
	}

	private void readFile(TextField textFilename, Histogram hist) {
		counts = new int[26];
		File file = new File(textFilename.getText());

		try (Scanner input = new Scanner(file);) {
			String fileString = "";
			while (input.hasNext()) {
				fileString += input.nextLine().toLowerCase() + "\n";
			}

			countLetters(fileString);
			hist.setCounts(counts);
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	public static void countLetters(String s) {
		for (int i = 0; i < s.length(); i++) {
			char character = s.charAt(i);

			if (Character.isLetter(character)) {
				counts[character - 97]++;
			}
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
