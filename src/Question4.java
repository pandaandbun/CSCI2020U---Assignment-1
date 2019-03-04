import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.util.Random;

public class Question4 extends Application {
	@Override
	public void start (Stage primaryStage) {
		
		Random rn = new Random();
		
		GridPane pane = new GridPane();
	    pane.setAlignment(Pos.CENTER);
	    pane.setHgap(5);
	    
	    for (int i = 0; i < 3; i++) {
	    	int cardChoosen = rn.nextInt(54) + 1;
	    	String cardLocation = "Cards/" + cardChoosen + ".png";
	    	ImageView imageView1 = new ImageView(cardLocation);
	    	pane.add(imageView1, i, 0);
	    }
	    
	    // Create a scene and place it in the stage
	    Scene scene = new Scene(pane);
	    primaryStage.setTitle("Question 1"); // Set the stage title
	    primaryStage.setScene(scene); // Place the scene in the stage
	    primaryStage.show(); // Display the stage
	}

	public static void main(String[] args) {
		launch (args);
	}

}
