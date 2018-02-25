/////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Spring 2016 
// PROJECT:          (p1)
// FILE:             (ScoreIterator.java)
//
// TEAM:    (Team 03 - Numb Biscuits)
// Authors: (Nafis Faisal Arrafi, Christian Farris, Kevin Fischer, Matthew 
//  		Matthew Stout, Chen Yang, and Ruimin Zhang)
//
//////////////////////////// 80 columns wide //////////////////////////////////




import java.util.NoSuchElementException;



/**
 * This class serves as an iterator for a ScoreList.
 * It has indirect access to the instances of
 * Score stored within.  It can check whether there is
 * another score in ScoreList's array and it can also
 * give access to the next Score.  
 * 
 */
public class ScoreIterator implements ScoreIteratorADT {

	private ScoreList list;
	private int currPos; // keeps track of the position that the iterator is at
	private String category;

	/**
	 * This is the constructor. It initializes all of the class variables
	 * 
	 * 
	 * @param list
	 *            the instance to the ScoreList to be iterated through
	 * @param category
	 *            the type of items to search through
	 */

	public ScoreIterator(ScoreList list, String category) {
		this.list = list;
		this.category = category;
		this.currPos = 0;

	}

	/**
	 * The method checks whether there is another item of the given category
	 * type to be processed. It will advance the pointer past Scores with
	 * non-matching categories.
	 * 
	 * @return returns true if there is another item and it is of the desired
	 *         category process, otherwise it returns false.
	 */

	public boolean hasNext() {

		while (currPos < list.size()) {

			if (list.get(currPos).getName().charAt(0) == category.charAt(0)) { // uses
																				// first
																				// letter
																				// of
																				// category
				return true; // and next item to check if in right category
			}

			else {
				currPos++; // this element is not of relevant category, so skip
							// it
			}

		}
		return false;
	}

	/**
	 * Returns the current item and advances to the next item in list
	 * 
	 * @exception if
	 *                there is not a next element, then an exception is thrown
	 * @return the current item in the list
	 */

	public Score next() {

		if (!hasNext()) { // if there is no next element. This advances pointer
							// to correct position if there is
			throw new NoSuchElementException(); // another element of correct
												// category
		}

		return list.get(currPos++);

	}

}
