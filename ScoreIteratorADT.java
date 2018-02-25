/////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Spring 2016 
// PROJECT:          (p1)
// FILE:             (ScoreIteratorADT.java)
//
// TEAM:    (Team 03 - Numb Biscuits)
// Authors: (Nafis Faisal Arrafi, Christian Farris, Kevin Fischer, Matthew 
//  		Matthew Stout, Chen Yang, and Ruimin Zhang)
//
//////////////////////////// 80 columns wide //////////////////////////////////


/**
 * This interface specifies the methods needed to create
 * a ScoreIterator class.  It contains the two most 
 * common methods within an iterator - one to check
 * whether a Score is left and one to return the next
 * Score while iterating through the list.  
 * 
 */
public interface ScoreIteratorADT {

	/**
	 * The method checks whether there is another item to be processed.
	 * 
	 * 
	 * @return returns true if there is another item to process, false if there
	 *         isn't
	 */

	public boolean hasNext();

	/**
	 * Returns the current item and advances to the next item in list
	 * 
	 * 
	 * @return the current item in the list
	 */

	public Score next();
}
