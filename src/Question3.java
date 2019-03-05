import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.concurrent.ThreadLocalRandom;

public class Question3 extends Application {
	
	private Point2D[] points = new Point2D[3];
	private Text[] texts = new Text[3];
	private int[] triAngles = new int[3];
	private ObservableList<Double> lines;
	
	@Override
	public void start(Stage primaryStage) {

		Pane pane = new Pane();
		pane.setPadding(new Insets(25, 25, 25, 25));

		//Create a Central Circle
		Circle cir = setCircle();
		pane.getChildren().add(cir);
		
		//Create three random triangle points 
		setTrianglePoints(cir);
		
		// Create the Triangle according to the points
		Polygon tri = setTriangle ();
		pane.getChildren().add(tri);
		
		//Calculate the initial Angles of the Triangle
		setTriangleAngles();
		
		// Set three small red circles on top of each triangle points and label each point with its Angles
		setSmallCircleAndAngles (pane, cir);
				
		// Create a scene and place it in the stage
		Scene scene = new Scene(pane);
		primaryStage.setTitle("Question 3"); // Set the stage title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show(); // Display the stage
	}
	
	//Set three small red circles on top of each triangle points and label each point with its Angles
	private void setSmallCircleAndAngles (Pane pane, Circle cir) {
		for (int i = 0; i < 3; i++) {

			//Set circle points
			Circle point = new Circle(points[i].getX(), points[i].getY(), 5);
			point.setFill(Color.RED);
			point.setStroke(Color.BLACK);
			
			//Set angle texts
			Text text = new Text();
			text.setText(triAngles[i] + "");
			text.relocate(point.getCenterX(), point.getCenterY());
			texts[i] = text;
			
			//Set each circles to be movable 
			makePointsMoveable(point, text, lines, i, cir);
			pane.getChildren().addAll(text,point);
		}
	}
	
	//Create a Central Circle
	private Circle setCircle () {
		Circle cir = new Circle();
		cir.setCenterX(200);
		cir.setCenterY(150);
		cir.setRadius(100);
		cir.setFill(Color.WHITE);
		cir.setStroke(Color.BLACK);
		
		return cir;
	}
	
	//Create three random triangle points
	private void setTrianglePoints (Circle cir) {
		for (int i = 0; i < 3; i++) {
			double random = ThreadLocalRandom.current().nextDouble(0, 2 * Math.PI);
			// Y
			double Y = Math.round(cir.getCenterY() + cir.getRadius() * Math.sin(random));
			// X
			double X = Math.round(cir.getCenterX() + cir.getRadius() * Math.cos(random));
			points[i] = new Point2D(X, Y);
		}
	}
	
	//Create the Triangle according to the points
	private Polygon setTriangle () {
		
		Polygon tri = new Polygon();
		tri.getPoints().addAll(points[0].getX(), points[0].getY(),
								points[1].getX(), points[1].getY(),
								points[2].getX(), points[2].getY());
		tri.setFill(Color.WHITE);
		tri.setStroke(Color.BLACK);
		lines = tri.getPoints();
		
		return tri;
	}
	
	//Calculate the initial Angles of the Triangle
	private void setTriangleAngles () {
		
		double sideC = points[1].distance(points[0]);
		double sideB = points[2].distance(points[0]);
		double sideA = points[1].distance(points[2]);

		triAngles[0] = (int) Math
				.toDegrees(Math.acos((sideA * sideA - sideB * sideB - sideC * sideC) / (-2 * sideB * sideC)));
		triAngles[1] = (int) Math
				.toDegrees(Math.acos((sideB * sideB - sideA * sideA - sideC * sideC) / (-2 * sideA * sideC)));
		triAngles[2] = (int) Math
				.toDegrees(Math.acos((sideC * sideC - sideB * sideB - sideA * sideA) / (-2 * sideA * sideB)));
	}
	
	//Update each angles when a point is move
	private void updateAngles () {
		
		double sideC = points[1].distance(points[0]);
		double sideB = points[2].distance(points[0]);
		double sideA = points[1].distance(points[2]);

		triAngles[0] = (int) Math
				.toDegrees(Math.acos((sideA * sideA - sideB * sideB - sideC * sideC) / (-2 * sideB * sideC)));
		triAngles[1] = (int) Math
				.toDegrees(Math.acos((sideB * sideB - sideA * sideA - sideC * sideC) / (-2 * sideA * sideC)));
		triAngles[2] = (int) Math
				.toDegrees(Math.acos((sideC * sideC - sideB * sideB - sideA * sideA) / (-2 * sideA * sideB)));
		
		texts[0].setText(triAngles[0] + "");
		texts[1].setText(triAngles[1] + "");
		texts[2].setText(triAngles[2] + "");
		
	}

	//Make each points movable
	private void makePointsMoveable(Circle point, Text text, ObservableList<Double> line, int index, Circle cir) {
		point.setOnMouseDragged((MouseEvent me) -> {
			
			Point2D center = new Point2D(cir.getCenterX(), cir.getCenterY());
			Point2D mouse = new Point2D(me.getX(), me.getY());
			Point2D centerToMouse = mouse.subtract(center);
			Point2D centerToNewPoint = centerToMouse.normalize().multiply(cir.getRadius());
			Point2D newPoint = centerToNewPoint.add(center);
			Point2D newAnglePoint = centerToNewPoint.add(center).subtract(20, 20);

			points[index] = newPoint;
			point.setCenterX(newPoint.getX());
			point.setCenterY(newPoint.getY());
			
			line.set(index * 2, newPoint.getX());
			line.set(index * 2 + 1, newPoint.getY());
			
			updateAngles();

			text.relocate(newAnglePoint.getX(), newAnglePoint.getY());
		});
	}

	public static void main(String[] args) {
		launch(args);
	}

}
