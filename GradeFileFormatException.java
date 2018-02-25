/////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Spring 2016 
// PROJECT:          (p1)
// FILE:             (GradeFileFormatException.java)
//
// TEAM:    (Team 03 - Numb Biscuits)
// Authors: (Nafis Faisal Arrafi, Christian Farris, Kevin Fischer, Matthew 
//  		Matthew Stout, Chen Yang, and Ruimin Zhang)
//
//////////////////////////// 80 columns wide //////////////////////////////////




/**
 * This is an exception class to be thrown whenever
 * a file was not presented with the proper format.
 * It provides a message to be used when it is caught.
 */


public class GradeFileFormatException extends Exception {

	
	
	/**
	 * This method creates a message which can be called when
	 * exception is thrown
	 * 
	 * @ return a String that is message to be returned.
	 */
	public String getMessage() {
		return "GradeFileFormatException";
	}
}
