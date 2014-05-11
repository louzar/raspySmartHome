Raspberry Pi Smart Home
==============

Alpha version. Temperature and humidity information gathering.
REST web service provided. Tested on jetty and winstone web servers.
LED notofication (just for test).

Supported sensors:
 * DHT11

Plans:
 * increase the number of supported sensors
 * web application for monitoring
 * android application
 * fire\gas protection
 * protection against thieves 
 * remote door opening
 * Arduino integration
 
How to compile native code:
	* gcc -I$JAVA_HOME/include -I$JAVA_HOME/include/linux -c read-sensor.c -l wiringPi
	* gcc -shared -o read-sensor.so read-sensor.o -l wiringPi
