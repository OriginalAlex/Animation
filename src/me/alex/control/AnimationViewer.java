package me.alex.control;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import me.alex.backend.Reading;

public class AnimationViewer {
	
	@FXML
	private GridPane gp;
	
	@FXML
	private TextArea delay;
	
	private Reading read;
	private List<Paint[][]> allFrames;
	private volatile boolean running;
	private static AnimationViewer instance;
	
	public AnimationViewer() {
		instance = this;
	}
	
	public static AnimationViewer getPrimaryInstance() {
		return instance;
	}
	
	@FXML
	public void initialize() {
		this.running = true;
		this.allFrames = new ArrayList<Paint[][]>();
		for (int row = 0; row < 20; row++) {
			for (int column = 0; column < 20; column++) {
				Rectangle r = new Rectangle();
				r.setHeight(27); r.setWidth(27);
				r.setFill(Color.YELLOW);
				gp.add(r, column, row);
			}
		}	
	}
	
	@FXML
	public void openFileDir() {
		FileChooser fc = new FileChooser();
		FileChooser.ExtensionFilter ef = new FileChooser.ExtensionFilter("Text Files (*.txt)", "*.txt");
		String home = System.getProperty("user.home");
		fc.getExtensionFilters().add(ef);
		File file = new File(home);
		if (!file.canRead()) {
			file = new File("c:/");
		} 
		
		fc.setInitialDirectory(file);
		File chosen = fc.showOpenDialog(null);
		if (chosen == null) {
			System.out.println("Select a valid file");
			openFileDir();
		} else {
			this.read = new Reading(chosen);
			startAnimation();
		}
	}
	
	private long getDelay(String str) {
		long lDelay = 0;
		try {
			lDelay = Long.parseLong(str);
		} catch (NumberFormatException e) {
			lDelay = 500;
		}
		if (lDelay < 4) return 500;
		return lDelay;
	}
	
	private void startAnimation() {
		allFrames = read.getColouredArray();
		ObservableList<Node> list = gp.getChildren();
		Thread th = new Thread(() -> {
			while (running) {
				String delayString = delay.getText();
				long lDelay = getDelay(delayString);
				for (int i = 0; i < allFrames.size(); i++) {
					if (!delayString.equals(delay.getText())) {
						lDelay = getDelay(delay.getText());
					}
					Paint[][] grid = allFrames.get(i);
					for (int row = 0; row < 20; row++) {
						int row20 = row*20;
						for (int column = 0; column < 20; column++) {
							((Rectangle) list.get(row20 + column)).setFill(grid[row][column]);
						}
					}
					try {
						Thread.sleep(lDelay);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}	
		});
		th.start();
	}
	
	public void setRunning(boolean runState) {
		this.running = runState;
	}
}
