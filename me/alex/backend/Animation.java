package me.alex.backend;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Animation {
	
	private List<Frame> allFrames;
	
	public Animation() {
		this.allFrames = new ArrayList<Frame>();
	}
	
	public void addFrame(Frame frame) {
		this.allFrames.add(frame);
	}
	
	public void writeToFile(File target) { // Create file directory
		try (PrintWriter pw = new PrintWriter(new FileWriter(target))) {
			for (Frame f : allFrames) {
				pw.write(f.toString() + System.getProperty("line.separator"));
			}
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
