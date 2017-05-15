package me.alex.control;

import java.io.File;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import me.alex.backend.Animation;
import me.alex.backend.Frame;
import me.alex.backend.Main;

public class Creation {
	
	@FXML
	private GridPane gp;
	
	@FXML
	private Rectangle yellow;
	
	@FXML
	private Rectangle black;
	
	@FXML
	private Rectangle red;
	
	@FXML
	private Rectangle blue;
	
	@FXML
	private Rectangle white;
	
	@FXML
	private Rectangle green;
	
	@FXML
	private Rectangle purple;
	
	@FXML
	private Rectangle brown;
	
	@FXML
	private Rectangle pink;
	
	@FXML
	private Rectangle teal;
	
	@FXML
	private Rectangle gray;
	
	@FXML
	private Rectangle violet;
	
	private Color selectedColor = Color.WHITE;
	private Rectangle[][] frame = new Rectangle[20][20];
	private Animation animation;
	
	public void completeFrame() {
		selectedColor = Color.WHITE;
		Paint[][] arr = new Paint[20][20];
	
		for (int row = 0; row < 20; row++) {
			int row20 = row*20;
			for (int column = 0; column < 20; column++) { // row on onside
				arr[row][column] = ((Rectangle) gp.getChildren().get(row20 + column)).getFill();
			}
		}
		Frame f = new Frame(arr);
		animation.addFrame(f);
	}
	
	private void clearGrid() {
		for (Rectangle[] arr : frame) {
			for (Rectangle r : arr) {
				r.setFill(Color.WHITE);
			}
		}
		
	}
	
	private void createPalette() {
		yellow.setFill(Color.YELLOW);
		black.setFill(Color.BLACK);
		red.setFill(Color.RED);
		blue.setFill(Color.BLUE);
		white.setFill(Color.WHITE);
		green.setFill(Color.GREEN);
		brown.setFill(Color.BROWN);
		pink.setFill(Color.PINK);
		teal.setFill(Color.TEAL);
		gray.setFill(Color.GRAY);
		violet.setFill(Color.VIOLET);
	}
	
	@FXML
	public void initialize() {
		this.animation = new Animation();
		for (int row = 0; row < 20; row++) {
			for (int column = 0; column < 20; column++) {
				Rectangle r = new Rectangle();
				r.setHeight(27); 
				r.setWidth(27);
				r.setFill(Color.WHITE);
				/*
				 * Formula to get Rectangle at index = row*rowCount + column
				 */
				r.setOnDragEntered(event -> {
					r.setFill(selectedColor);
				});
				
				r.setOnDragDetected(event -> {
			          Dragboard dragboard = r.startDragAndDrop(TransferMode.COPY);
			          ClipboardContent content = new ClipboardContent();
			          content.putString("Test");
			          dragboard.setContent(content);
			          event.consume();
			        });
				
				r.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent arg0) {
						r.setFill(selectedColor);
					}				
				});
				frame[row][column] = r;
				gp.add(r, column, row);
			}
		}	
		createPalette();
	}
	
	@FXML
	public void save() {
		FileChooser fd = new FileChooser();
		FileChooser.ExtensionFilter fe = new FileChooser.ExtensionFilter("Text Files (*.txt)", "*.txt");
		fd.getExtensionFilters().add(fe);
		String userDir = System.getProperty("user.home");
		File file = new File(userDir);
		
		if (!file.canRead()) {
			file = new File("c:/");
		}
		
		fd.setInitialDirectory(file);
		File chosen = fd.showOpenDialog(null);
		if (chosen == null) {
			System.out.println("Choose a valid file!");
		} else {
			animation.writeToFile(chosen);
		}
	}
	
	
	@FXML
	public void switchToAnim() {
		Main main = Main.getInstance();
		// "/me/alex/visual/CreateAnimation.fxml"
		main.switchScene("/me/alex/visual/Animation.fxml", 540, 580);
	}
	
	@FXML
	public void clear() {
		clearGrid();
	}
	
	@FXML
	public void setYellow() {
		selectedColor = Color.YELLOW;
	}
	
	@FXML
	public void setBlack() {
		selectedColor = Color.BLACK;
	}
	
	@FXML
	public void setBlue() {
		selectedColor = Color.BLUE;
	}
	
	@FXML
	public void setGreen() {
		selectedColor = Color.GREEN;
	}
	
	@FXML
	public void setRed() {
		selectedColor = Color.RED;
	}
	
	@FXML
	public void setWhite() {
		selectedColor = Color.WHITE;
	}
	
	@FXML
	public void setBrown() {
		selectedColor = Color.BROWN;
	}
	
	@FXML
	public void setPurple() {
		selectedColor = Color.PURPLE;
	}
	
	@FXML
	public void setPink() {
		selectedColor = Color.PINK;
	}
	
	@FXML
	public void setTeal() {
		selectedColor = Color.TEAL;
	}
	
	@FXML
	public void setGray() {
		selectedColor = Color.GRAY;
	}
	
	@FXML
	public void setViolet() {
		selectedColor = Color.VIOLET;
	}
}
