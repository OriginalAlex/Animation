package me.alex.backend;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Frame {
	
	private Paint[][] frame; // this will be a completed frame.
	
	public Frame(Paint[][] frame) {
		this.frame = frame.clone();
	}
	
	public Paint get(int row, int column) {
		return frame[row][column];
	}
	
	private String colourToString(Paint p) { // paint here will always be a Colour		
		if (p instanceof Color) {
			Color c = (Color) p;
			if (c == Color.YELLOW) return "YEL";
			else if (c == Color.BLACK) return "BLA";
			else if (c == Color.RED) return "RED";
			else if (c == Color.BLUE) return "BLU";
			else if (c == Color.BROWN) return "BRO";
			else if (c == Color.GREEN) return "GRE";
			else if (c == Color.WHITE) return "WHI";
			else if (c == Color.PURPLE) return "PUR";
			else if (c == Color.PINK) return "PIN";
			else if (c == Color.TEAL) return "TEA";
			else if (c == Color.GRAY) return "GRA";
			else if (c == Color.VIOLET) return "VIO";
		} else {
			System.out.println("Not all the rectangles are coloured with javafx.colour???");
		}
		
		return null;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < frame.length; i++) { 
			Paint[] arr = frame[i];
			for (Paint r : arr) {
				sb.append(colourToString(r));
			}
			if (i != frame.length-1) {
				sb.append("/");
			}
		}
		return sb.toString();
	}

}
