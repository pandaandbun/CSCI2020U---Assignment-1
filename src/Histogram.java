import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;

public class Histogram extends Pane {
	private int[] counts;
	private int maxValue;
	private int paneHeight;

	public Histogram() {
		this.counts = new int[26];
		maxValue = 0;
		paneHeight = 400;
		drawHistogram();
	}

	public void drawHistogram() {
		for (int i = 0, x = 0; i < counts.length; i++, x += 25) {
			double rHeight = (double) counts[i] / maxValue * paneHeight;
			
			Rectangle r = new Rectangle(20, rHeight);
			r.setX(x);
			r.setY(paneHeight - rHeight);
			r.setFill(Color.WHITE);
			r.setStroke(Color.BLACK);
			
			Text t = new Text((char) (i + 65) + "");
			t.setX(x + 5);
			t.setY(paneHeight + 15);
			getChildren().addAll(r, t);
		}
	}

	public void setCounts(int[] counts) {
		this.counts = counts;
		maxValue = getMaxValue();
		drawHistogram();
	}

	public int getMaxValue() {
		int m = counts[0];
		for (int i = 1; i < counts.length; i++) {
			if (counts[i] > m) {
				m = counts[i];
			}
		}
		return m;
	}
}
