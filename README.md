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

**I'm persuming that you have all of the above configured and working correctly in this tutorial!**

Step One
----------

Create a new Maven Project in Eclipse.  
You can create this in Eclipse by Clicking File-->New Project and then Maven-->Maven Project.

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
-------

Next we have to update the pom.xml with the information about the project and the configuration details used by Maven to build the project

	<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
	  <modelVersion>4.0.0</modelVersion>
	  <groupId>CalculateWS</groupId>
	  <artifactId>CalculateWS</artifactId>
	  <version>0.0.1-SNAPSHOT</version>
	  <packaging>war</packaging>
	  <name>CalculatorWS</name>
	  <description>A Calculator Axis2 Web Service using Maven</description>

	  <build>
		  <plugins>
		    <plugin>
		      <groupId>org.apache.axis2</groupId>
		      <artifactId>axis2-java2wsdl-maven-plugin</artifactId>
		      <version>1.5.4</version>
		      <executions>
			<execution>
			  <phase>process-classes</phase>
			  <goals>
			    <goal>java2wsdl</goal>
			  </goals>
			</execution>
		      </executions>
		      <configuration>
			<className>sd.service.Calculator</className>
			<outputFileName>${project.build.directory}/Calculator.wsdl</outputFileName>
		      </configuration>
		    </plugin>
		  </plugins>
		  
		  <pluginManagement>
		  	<plugins>
		  		<!--This plugin's configuration is used to store Eclipse m2e settings only. It has no influence on the Maven build itself.-->
		  		<plugin>
		  			<groupId>org.eclipse.m2e</groupId>
		  			<artifactId>lifecycle-mapping</artifactId>
		  			<version>1.0.0</version>
		  			<configuration>
		  				<lifecycleMappingMetadata>
		  					<pluginExecutions>
		  						<pluginExecution>
		  							<pluginExecutionFilter>
		  								<groupId>
		  									org.apache.axis2
		  								</groupId>
		  								<artifactId>
		  									axis2-java2wsdl-maven-plugin
		  								</artifactId>
		  								<versionRange>
		  									[1.5.4,)
		  								</versionRange>
		  								<goals>
		  									<goal>java2wsdl</goal>
		  								</goals>
		  							</pluginExecutionFilter>
		  							<action>
		  								<ignore></ignore>
		  							</action>
		  						</pluginExecution>
		  					</pluginExecutions>
		  				</lifecycleMappingMetadata>
		  			</configuration>
		  		</plugin>
		  	</plugins>
		  </pluginManagement>
	  </build>


	  <dependencies>
		<dependency>
		  <groupId>org.apache.axis2</groupId>
		  <artifactId>axis2</artifactId>
		  <version>1.5.4</version>
		</dependency>
		 <dependency>
		  <groupId>org.apache.axis2</groupId>
		  <artifactId>axis2-transport-http</artifactId>
		  <version>1.5.4</version>
		</dependency>
		 <dependency>
		  <groupId>org.apache.axis2</groupId>
		  <artifactId>axis2-transport-local</artifactId>
		  <version>1.5.4</version>
		</dependency>
		<dependency>
		  <groupId>org.apache.xmlbeans</groupId>
		  <artifactId>xmlbeans</artifactId>
		  <version>2.4.0</version>
		</dependency>
	    <dependency>
	      <groupId>javax.servlet</groupId>
	      <artifactId>servlet-api</artifactId>
	      <version>2.5</version>
	      <scope>provided</scope>
	    </dependency>
	    <dependency>
	      <groupId>junit</groupId>
	      <artifactId>junit</artifactId>
	      <version>3.8.1</version>
	      <scope>test</scope>
	    </dependency>
	  </dependencies>
	</project>

Step Five
-------

Next we are going to configure the web.xml file located in the WEB-INF folder. This file is known as the Web Application Descriptor, it is used to describe the servlets and components that make up the application. We need add an entry for the Axis2 servlet that will be used.  
You can view more information about this file on the [Apache Tomcat Site](http://tomcat.apache.org/tomcat-7.0-doc/appdev/deployment.html).

	<?xml version="1.0" encoding="UTF-8"?>
	<web-app xmlns="http://java.sun.com/xml/ns/javaee" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd" version="3.0">
		<display-name>CalculatorWS</display-name>
		<servlet>
			<servlet-name>AxisServlet</servlet-name>
			<servlet-class>org.apache.axis2.transport.http.AxisServlet</servlet-class>
			<load-on-startup>1</load-on-startup>
		</servlet>
		<servlet-mapping>
			<servlet-name>AxisServlet</servlet-name>
			<url-pattern>/services/*</url-pattern>
		</servlet-mapping>
	</web-app>

Step Six
-------

We need to update the services.xml file located in the META-INF folder. This file contains the configuration for the web service and every Axis2 Web Service requires this file. It provides a description of the web service, the name of the service class and the operations of the class.  
For more information regarding this file refer to [Apache Axis Site](http://axis.apache.org/axis2/java/core/docs/xmlbased-server.html)

	<serviceGroup>
	  <service name="Calculator" targetNamespace="http://sd.service.Calculator/">
	    <description>A very simple Calculator Web Service.</description>
	    <schema schemaNamespace="http://sd.service.Calculator/" />
	    <parameter name="ServiceClass" locked="false">sd.service.Calculator</parameter>
	    <operation name="Add">
	      <messageReceiver class="org.apache.axis2.rpc.receivers.RPCMessageReceiver" />
	    </operation>
	    <operation name="Subtract">
	      <messageReceiver class="org.apache.axis2.rpc.receivers.RPCMessageReceiver" />
	    </operation>
	    <operation name="Divide">
	      <messageReceiver class="org.apache.axis2.rpc.receivers.RPCMessageReceiver" />
	    </operation>
	    <operation name="Multiply">
	      <messageReceiver class="org.apache.axis2.rpc.receivers.RPCMessageReceiver" />
	    </operation>
	  </service>
	</serviceGroup>

Step Seven
-------

Everything has been configured and we are now ready to start to deploy the web service but first we have to get Maven create the war file.  

1. This can be done by opening up terminal (Command Prompt for Windows Users) and change directory to the project.  
2. Running the following command will create a war file in the target folder. `mvn clean install`  
3. Start your local version of Tomcat and deploy the war file located in the target folder. Open a web browser and [see if this works, the result should be 2](http://localhost:8080/CalculateWS-0.0.1-SNAPSHOT/services/Calculator/Subtract?value1=6&value2=4).  
4. If it does then go back to terminal and change directory to the target folder and run the following command which is used to push the web service to Cloud Foundry. `vmc push`  
5. In the Cloud Foundry just set the name of the Application to whatever you want, I called mine CalculatorWebService and set all the rest of the values to the default. This will deploy the web service to Cloud Foundry and you can access it from anywhere.

Below are the links to my version.
* [CalculatorWS WSDL](http://calculatorwebservice.cloudfoundry.com/services/Calculator?wsdl)
* [Add Operation](http://calculatorwebservice.cloudfoundry.com/services/Calculator/Add?value1=39.9&value2=7.9)
* [Divide Operation](http://calculatorwebservice.cloudfoundry.com/services/Calculator/Divide?value1=39.9&value2=7.9)
* [Multiply Operation](http://calculatorwebservice.cloudfoundry.com/services/Calculator/Multiply?value1=39.9&value2=7.9)
* [Subtract Operation](http://calculatorwebservice.cloudfoundry.com/services/Calculator/Subtract?value1=39.9&value2=7.9)

Author
-------

**Shane Doyle**

+ http://twitter.com/iamshanedoyle
+ http://github.com/iamshanedoyle

