package sd.service;

/**
 * This is a simple Calculator class that
 * has four public methods that will be used
 * as an Axis2 Web Service.
 * 
 * @author Shane Doyle <iamshanedoyle>
 */
public class Calculator {
	
	private double result = 0;
	
	/**
	 * This method is used for adding two values and
	 * it returns the result.
	 * @param value1
	 * @param value2
	 * @return
	 */
	public double Add(double value1, double value2) {
		if(validValues(value1, value2)) {
			result = value1 + value2;
		}
		
		return result;
	}
	
	/**
	 * This method is used for dividing two values and
	 * it returns the result.
	 * @param value1
	 * @param value2
	 * @return
	 */
	public double Divide(double value1, double value2) {
		if(validValues(value1, value2)) {
			result = value1 / value2;
		}
		
		return result;
	}
	
	/**
	 * This method is used for multiplying two values and
	 * it returns the result.
	 * @param value1
	 * @param value2
	 * @return
	 */
	public double Multiply(double value1, double value2) {
		if(validValues(value1, value2)) {
			result = value1 * value2;
		}
		
		return result;
	}
	
	/**
	 * This method is used for subtracting two values and
	 * it returns the result.
	 * @param value1
	 * @param value2
	 * @return
	 */
	public double Subtract(double value1, double value2) {
		if(validValues(value1, value2)) {
			result = value1 - value2;
		}
		
		return result;
	}
	
	/**
	 * This method is used to check if the values are valid.
	 * @param value1
	 * @param value2
	 * @return
	 */
	private boolean validValues(double value1, double value2) {
		if(value1==0.00&&value2==0.0) {
			return false;
		} else {
			return true;
		}
	}
}
