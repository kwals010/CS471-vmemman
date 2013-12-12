package vmemman;

public class Fifo {
	
	public Fifo(int[] refList, int size, int frames){
		
		int[] pages = new int[refList.length]; // Will hold the page reference list after converted to page numbers
		int faults = 0;
		int[] frame = new int[frames];
		int first = -1;
		boolean hit = false;
		
		// Change the refList array to an array of pages using division
		for (int i = 0; i < refList.length; ++i){
			pages[i] = refList[i] / size;
		}
		
		// Initialize all frames to -1.
		for (int i = 0; i < frame.length; ++i) {
			frame[i] = -1;
		}

		// Loop through the pages list and check for hits and misses.
		for (int i = 0; i < pages.length; ++i) {
			for (int j = 0; j < frame.length; ++j) {
				if (pages[i] == frame[j]) {
					hit = true;
				}
			}
			// If there is not a hit, move the pointer to first up one and put the value there.
			if (!hit) {
				first = (first + 1) % frame.length;
				frame[first] = pages[i];
				++faults; 		// Keep track of faults.
			}
			// No need for an else statement here since hits just stay in the frame array.
			hit = false;
		}
		// Print out the results.
		System.out.print(size + "\t" + frames + "\tFIFO\t");
		System.out.printf("%.2f",(double)(faults*100)/pages.length);
		System.out.print("%\n");
	 }
}

	
	
