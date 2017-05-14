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
		/*
		 * It will be stored in the format:
		 * CCCCCCCCCCCCCCCCCCCCCCCCCCC/CCCCCCCCCCCCCCCCCCCCCCCCC/CCCCCCCCCCCCCCCCC
		 * (NEXT FRAME)
		 * Where C is representative of any colour used in the frame
		 */
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
