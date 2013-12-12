package vmemman;


public class Mru {
	
	public Mru (int[] refList, int size, int frames) {
		
		int[] pages = new int[refList.length]; // Will hold the page reference list after converted to page numbers.
		int faults = 0;
		int[] frame = new int[frames];
		int hit = -1;
		
		// Change the refList array to an array of pages using division.
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
					hit = j;	// Keep track of where exactly the hit took place.
				}
			}
			// If not a hit, find the most recently used value and replace it with this one
			if (hit < 0) {
				boolean done = false; // 
				// If the frame array has not yet been filled, fill from the bottom
				for (int k = frame.length -1; k >= 0; --k) {
					if (frame[k] == -1 && done == false) {
						frame[k] = pages[i];
						done = true;
					}
				}
				// If the frame array was full, done will still be false here. Replace the newest on top.
				if (!done) {
					frame[0] = pages[i];
				}
				++faults;	
			}
			// If there was a hit, pull that value out and put it on the top.
			else {
				for (int k = hit; k > 0; --k) {
					frame[k] = frame[k-1];
				}
				frame[0] = pages[i];
			}
			hit = -1;	
		}
		
		// Print out the results.
		System.out.print(size + "\t" + frames + "\tMRU\t");
		System.out.printf("%.2f",(double)(faults*100)/pages.length);
		System.out.print("%\n");
		
		
		
		
	}

}

