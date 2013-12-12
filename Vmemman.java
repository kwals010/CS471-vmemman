package vmemman;

/*  Keith Walsh
 *  CS471 Fall 2013
 *  Course Project Part 2
 *  
 *  This program contains five classes:
 *  Vmemman: Main driver. Reads test file and calls the four other classes
 *  	using parameters set by the assignment.
 *  Fifo: Creates a fault rate report based on data read using the FIFO
 *  	(First In First Out) replacement algorithm.
 *  Lru: Creates a fault rate report based on data read using the LRU
 *  	(Least Recently Used) replacement algorithm.  
 *  Mru: Creates a fault rate report based on data read using the MRU
 *  	(Most Recently Used) replacement algorithm. 
 *  Optimal: Creates a fault rate report based on data read using the Optimal
 *  	replacement algorithm. 
 * 
 */


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Vmemman {

	static String fileName;
	static int[] vals;
	static int[] pSize = {512, 1024, 2048};	// Parameters required by assignment.
	static int[] nFrames = {4, 8, 12};	// Parameters required by assignment.
	
	
	public static void main(String[] args) {

		// The file name comes in from the line command
		 // i.e. >java -jar vmemman.jar testfile.dat
		 if (args.length == 1) {
		    try {
		    	fileName = args[0];
		    } catch (NumberFormatException e) {
		        System.err.println("Must provide file name as argument");
		        System.exit(1);
		    }
		 }
		 // Read the file and store the return value in the vals array.
		 vals = readfile(fileName);		
		
		 // Print a header for the results.
		System.out.println("Size\tFrames\tALG\tFault Rate");
	
		// All the work is done in the class constructors.
		// Loop through the memory size array.
		for (int i = 0; i < pSize.length; ++i) {
			// Loop through the frame count array.
			for (int j = 0; j < nFrames.length; ++j) {
				new Fifo(vals,pSize[i],nFrames[j]);
				new Lru(vals,pSize[i],nFrames[j]);
				new Mru(vals,pSize[i],nFrames[j]);
				new Optimal(vals,pSize[i],nFrames[j]);
			}
		}
	}
	
	public static int[] readfile(String fn) {
		// Reads the file and returns an array of ints
		ArrayList<String> al = new ArrayList<String>();
		BufferedReader br = null;
		 
		try {
			String sCurrentLine;
			br = new BufferedReader(new FileReader(fn));
			// Reads each line of the file and add to arraylist
			while ((sCurrentLine = br.readLine()) != null) {
				al.add(sCurrentLine); 
			}
 
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		// Convert the arraylist to an array to create return array
		Object[] ia = al.toArray();
		int[] ret = new int[ia.length];
		for(int i=0; i<ia.length; i++) {
			ret[i] = Integer.parseInt((String) ia[i]);
		}
		return ret;
	}	
}
