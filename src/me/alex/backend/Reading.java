package me.alex.backend;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Reading {
	
	private File file;
	
	public Reading(File location) {
		this.file = location;
	}
	
	/*
	 * New:
	 * Purple,
	 * Pink,
	 * Teal,
	 * Gray,
	 * Violet
	 */
	
	public Paint convertToPaint(String str) {
		switch (str) {
		case("BLU"): return Color.BLUE; 
		case("YEL"): return Color.YELLOW;
		case("BLA"): return Color.BLACK;
		case("RED"): return Color.RED;
		case("BRO"): return Color.BROWN;
		case("GRE"): return Color.GREEN;
		case("WHI"): return Color.WHITE;
		case("PUR"): return Color.PURPLE;
		case("PIN"): return Color.PINK;
		case("TEA"): return Color.TEAL;
		case("GRA"): return Color.GRAY;
		case("VIO"): return Color.VIOLET;
		default: throw new UnsupportedOperationException("Why is the string not a valid paint?");
		}
	}
	
	public List<Paint[][]> getColouredArray() { // an array of animations essentially (an array of a 20x20 grid of rectangles)
		List<Paint[][]> allFrames = new ArrayList<Paint[][]>();
		try (BufferedReader br = new BufferedReader(new FileReader(this.file))) {
			String line;
			while ((line = br.readLine()) != null) { // get each row/column pair
				Paint[][] frame = new Paint[20][20];
				String[] rows = line.split("/");
				for (int i = 0; i < rows.length; i++) { // rows.length is 20 hopefully... else I fucked up
					String currentRow = rows[i];
					for (int z = 0; z < 59; z += 3) {
						String currentValue = currentRow.substring(z, z+3);
						Paint p = convertToPaint(currentValue);
						frame[i][z/3] = p;
					}
				}
				allFrames.add(frame.clone());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return allFrames;
	}
}
