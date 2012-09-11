Apache Axis2 Web Service using Maven
=================

This is code used to deploy an Apache Axis2 Web Service to Cloud Foundry using Maven.  

It is a simple Calculator Web Service that has four operations(Add, Divide, Multiply, Subtract).  

Operations
-----------

* Add(value1, value2)
* Divide(value1, value2)
* Multiply(value1, value2)
* Subtract(value1, value2)

How to do it!
=================

Requirements
-----------

The following are required for this tutorial to work:

* [Eclipse IDE for JavaEE](http://www.eclipse.org/downloads/packages/eclipse-ide-java-ee-developers/indigosr2)
* [Apache Tomcat 7](http://tomcat.apache.org/download-70.cgi)
* [Maven Plugin for Eclipse](http://maven.apache.org/eclipse-plugin.html)
* [Cloud Foundry A/C](http://www.cloudfoundry.com/)
* As well as all the obvious stuff, JRE etc..

**I am persuming that you have all of the above configured and working correctly in this tutorial!**

Step One
----------

Create a new Maven Project in Eclipse. You can create this in Eclipse by Clicking File-->New Project and then Maven-->Maven Project.

Step Two
-----------

You will need to match the folder structure to the following:

-src
--java
--resources
--webapp
---WEB-INF
----services
-----CalculatorWS
------META-INF

In the WEB-INF folder create the file web.xml and in the folder META-INF create the file services.xml. We will talk about these files later.


Step Three
---------------

Next create the simple web service. This is going to be a java class called Calculator which will contain four operations. I have created the class in a package named "sd.service".

	package sd.service;
	public class Calculator {
	
		private double result = 0;
	
		public double Add(double value1, double value2) {
			if(validValues(value1, value2)) {
				result = value1 + value2;
			}
		
			return result;
		}
	
		public double Divide(double value1, double value2) {
			if(validValues(value1, value2)) {
				result = value1 / value2;
			}
		
			return result;
		}
	
		public double Multiply(double value1, double value2) {
			if(validValues(value1, value2)) {
				result = value1 * value2;
			}
		
			return result;
		}
	
		public double Subtract(double value1, double value2) {
			if(validValues(value1, value2)) {
				result = value1 - value2;
			}
		
			return result;
		}
	
		private boolean validValues(double value1, double value2) {
			if(value1==0.00&&value2==0.0) {
				return false;
			} else {
				return true;
			}
		}
	}


Step Four
----


Author
-------

**Shane Doyle**

+ http://twitter.com/iamshanedoyle
+ http://github.com/iamshanedoyle



