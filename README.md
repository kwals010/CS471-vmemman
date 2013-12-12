CS471-vmemman
=============

Virtual memory algorithm comparison

To run this program, copy the runnable java program to your Windows desktop.
Open the command prompt and browse to that folder. The make sure Java is in 
your path but running: 

set path=C:\Program Files\Java\jre7\bin 
(This may be different depending on the installed version of Java.)

The program is invoked at the line command with one arguments, which is the name
of the test file that has the list of memory addresses in it. So to run it for 
testfile.dat (located in the same directory as the executable), enter:

>java -jar vmemman.jar testfile.dat

To output the results to a file, use the > redirect like this:

>java -jar vmemman.jar testfile.dat > results.txt
