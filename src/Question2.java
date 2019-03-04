import java.text.DecimalFormat;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Question2 extends Application {
	
	@Override // Override the start method in the Application class
	public void start(Stage primaryStage) throws Exception {

		GridPane pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		pane.setHgap(5);
		pane.setVgap(5);
		pane.setPadding(new Insets(25,25,25,25)); 

		//Investment Amount
		TextField investmentAmount  = new TextField();
		investmentAmount.setAlignment(Pos.BASELINE_RIGHT);
		investmentAmount.setPrefColumnCount(14);
		pane.add(new Label("Investment Amount: "), 0, 1);
		pane.add(investmentAmount, 1, 1);
		
		//Years
		TextField years = new TextField();
		years.setAlignment(Pos.BASELINE_RIGHT);
		years.setPrefColumnCount(14);
		pane.add(new Label("Years: "), 0, 2);
		pane.add(years, 1, 2);

		//Annual Interest Rate
		TextField annualInterestRate = new TextField();
		annualInterestRate.setAlignment(Pos.BASELINE_RIGHT);
		annualInterestRate.setPrefColumnCount(14);
		pane.add(new Label("Annual Interest Rate: "), 0, 3);
		pane.add(annualInterestRate, 1, 3);
		
		//Future Value
		TextField futureValue = new TextField();
		futureValue.setAlignment(Pos.BASELINE_RIGHT);
		futureValue.setPrefColumnCount(14);
		futureValue.setDisable(true);
		pane.add(new Label("Future Value: "), 0, 4);
		pane.add(futureValue, 1, 4);

		//Calculate Button
		Button calc_button = new Button("Calculate");
		pane.add(calc_button, 1, 5);
		
		calc_button.setOnAction(e -> {
			DecimalFormat f = new DecimalFormat("##.00");
			double result = Double.parseDouble(investmentAmount.getText()) * Math.pow((1 + 
								Double.parseDouble(annualInterestRate.getText()) / 1200),
								(Double.parseDouble(years.getText()) * 12));
			futureValue.setText(f.format(result) + "");
		});
				
		Scene scene = new Scene(pane);
		primaryStage.setTitle("Question 2");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}
}
