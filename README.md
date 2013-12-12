CS471-vmemman
=============

Virtual memory algorithm comparison

To run this program, compile into a runnable jar program and copy to your 
Windows desktop. Open the command prompt and browse to the folder. Make 
sure Java is in your path by running the following command: 

set path=C:\Program Files\Java\jre7\bin 
(This may be different depending on the installed version of Java.)

The program is invoked at the line command with one argument, which is the name
of the test file that has the reference list of memory addresses in it. So to 
run it for testfile.dat (located in the same directory as the executable), enter:

>java -jar vmemman.jar testfile.dat

To output the results to a file, use the > redirect like this:

>java -jar vmemman.jar testfile.dat > results.txt
