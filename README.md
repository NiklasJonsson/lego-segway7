# lego-segway7
Repo for the frtn01 lego segway project, 2016

TODO:
* Remove integrator windup
* Use both accelerometer and gyro
* Tune parameters for regulator
* Fix smoother plotting, possibly by changing the SwingWorker into a regular thread that calls putData on the plotterPanel
	Maybe give plotterPanel/GUI directly to dataReceiver thread?
	
* Several plotting panels for different types of signals, e.g. u goes from -99 to 99 whilst y goes from -30 to 30 and they might need different ranges (panels)

* Optionally try other regulators. IF we do this we need:
 	* Different parameter and signal classes for each type of Regulator
 	* GUI needs to handle different Regulators (maybe two panels for common signals and then extra panels for differing signals)

* Maybe decouple putting signals in monitor from regulator class
* Write report
* Make presentation
