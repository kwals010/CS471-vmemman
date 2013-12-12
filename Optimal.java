package vmemman;

public class Optimal {

	public Optimal(int[] refList, int size, int frames){
		
		int[] pages = new int[refList.length]; // Will hold the page reference list after converted to page numbers.
		int faults = 0;
		int[] frame = new int[frames];
		int[] nextUsed = new int[frames]; // Will keep track of when the value in each frame will be used next.
		boolean hit = false;
		
		// Change the refList array to an array of pages using division
		for (int i = 0; i < refList.length; ++i){
			pages[i] = refList[i] / size;
		}
		
		// Initialize all frames to -1
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
			// If not a hit, find the one that won't be used for the longest and replace it with this one
			if (!hit) {
				// If the frame array has not yet been filled, fill from the top
				boolean done = false;
				for (int k = 0; k < frame.length; ++k) {
					if (frame[k] == -1 && done == false) {
						frame[k] = pages[i];
						done = true;
					}
				}
				// If the frame array was full, done will still be false here.
				if (!done) {
					// Zero out nextUsed array
					for (int k = 0; k < nextUsed.length; ++k) {
						nextUsed[k] = 0;
					}
					// Look at everything in the array and see which has the longest to go until it's used again.
					for (int k = 0; k < frame.length; ++k) {
						for (int l = i+1; l < pages.length; ++l) {
							if (frame[k] != pages[l]) {
								++nextUsed[k];	// Increment the value in the nextUsed array
							}
						}
					}
					// Find the highest nextUsed value and replace that index
					int highest = 0;
					for (int k = 0; k < nextUsed.length; ++k) {
						if (nextUsed[k] > nextUsed[highest]) {
							highest = k;
						}
					}
					frame[highest] = pages[i];
				}
				++faults;
			}
			// No need for an else statement since the page is already in the frame array
			hit = false;
		}
		
		// Print out the results.
		System.out.print(size + "\t" + frames + "\tOptimal\t");
		System.out.printf("%.2f",(double)(faults*100)/pages.length);
		System.out.print("%\n");
	}	
}
