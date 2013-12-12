package vmemman;

public class Lru {
	
	public Lru(int[] refList, int size, int frames) {
		
		int[] pages = new int[refList.length]; // Will hold the page reference list after converted to page numbers.
		int faults = 0;
		int[] frame = new int[frames];
		int[] lastUsed = new int[frames]; // Will keep track of the last use for each page
		int hit = -1;
		int oldest;	// Pointer to the oldest frame.
		
		// Change the refList array to an array of pages using division.
		for (int i = 0; i < refList.length; ++i){
			pages[i] = refList[i] / size;
		}
		
		// Initialize all frames to -1.
		for (int i = 0; i < frame.length; ++i) {
			frame[i] = -1;
			lastUsed[i] = 0;
		}

		// Loop through the pages list and check for hits and misses.
		for (int i = 0; i < pages.length; ++i) {
			for (int j = 0; j < frame.length; ++j) {
				if (pages[i] == frame[j]) {
					hit = j;	// Keep track of where exactly the hit took place
				}
			}
			// If not a hit, find the oldest used value and replace it with this one
			if (hit < 0) {
				oldest = 0;
				for (int k = 0; k < lastUsed.length; ++k) {
					if (lastUsed[k] > lastUsed[oldest]) {
						oldest = k;
					}
					++lastUsed[k];	// Increment all the lastUsed values while you're looping anyway.
				}
				// Put the current page value into the oldest frame.
				frame[oldest] = pages[i];
				lastUsed[oldest] = 0;	// Reset the age on that frame.
				++faults;	// Keep track of faults.	
			}
			// If there was a hit, increment all the lastUsed values and set the hit one to 0
			else {
				for (int k = 0; k < lastUsed.length; ++k) {
					++lastUsed[k];
				}
				lastUsed[hit] = 0;
			}
			hit = -1;	
		}
		// Print out the results.
		System.out.print(size + "\t" + frames + "\tLRU\t");
		System.out.printf("%.2f",(double)(faults*100)/pages.length);
		System.out.print("%\n");		
	}
}
