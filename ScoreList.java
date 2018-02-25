/////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Spring 2016 
// PROJECT:          (p1)
// FILE:             (ScoreList.java)
//
// TEAM:    (Team 03 - Numb Biscuits)
// Authors: (Nafis Faisal Arrafi, Christian Farris, Kevin Fischer, Matthew 
//  		Matthew Stout, Chen Yang, and Ruimin Zhang)
//
//////////////////////////// 80 columns wide //////////////////////////////////

/**
 * The ScoreList class implements ScoreListADT.
 * A container that stores references to instances of Score in a 
 * contiguous and ordered list of those instances.
 *
 */
public class ScoreList implements ScoreListADT{
	
	private int numItems;
	private Score[] items;
	
	/**
	 * (Constructor for a new Scorelist. It assigns instance variables
	 * values. numItems is assigned to 0 and items in instantiated to
	 * an array with a random length. The shorter the length the less
	 * memory it will take up but the longer the length the longer it will
	 * take for the array to need to be expanded.)
	 */
	public ScoreList(){
		numItems = 0;
		items = new Score[100];
	}
	
	/** 
	 * Returns the number of Scores in the list or zero
	 * @return the number of scores in this list
	 */
	@Override
	public int size() {
		return numItems;
	}
	
	/** 
	 * Adds the score to the end of this list.
	 * 
	 * @param s a non-null Score to place as the last item in the list.
	 * @throws IllegalArgumentException
	 */
	@Override
	public void add(Score s) throws IllegalArgumentException {
		if(s == null){
			throw new IllegalArgumentException();
		}
		if(numItems == items.length){
			expandArray();
		}
		items[numItems]=s;
		numItems++;
	}
	
	/**
	 * Removes and returns the item at index position i.
	 * If i is less than zero or greater than size()-1,
	 * will throw an IndexOutOfBoundsException.
	 * @param i must be greater than or equal to zero and less than size()
	 * @return the item at index i
	 * @throws IndexOutOfBoundsException
	 */
	@Override
	public Score remove(int i) throws IndexOutOfBoundsException {
		if(i<0 || i>(size()-1)){
			throw new IndexOutOfBoundsException();
		}
		Score temp = items[i];
		changeArray(i);
		return temp;
	}
	
	/**
	 * Returns (without removing) the item at index position i.
	 * If i is less than zero or greater than size()-1,
	 * will throw an IndexOutOfBoundsException.
	 * @param i must be greater than or equal to zero and less than size()
	 * @return the item at index i
	 * @throws IndexOutOfBoundsException
	 */
	@Override
	public Score get(int i) throws IndexOutOfBoundsException {
		if(i<0 || i>(size()-1)){
			throw new IndexOutOfBoundsException();
		}
		
		return items[i];
	}
	
	/**
	 * (Copies the array items into a new one that is twice
	 * the size. Then assigns items to reference the new, larger
	 * array.)
	 */
	public void expandArray(){
		Score[] a = new Score [items.length*2];
		
		for(int i=0; i<numItems; i++){
			a[i] = items[i];
		}
		
		items = a;
	}
	
	/**
	 * (Copies the array items into a new array without the item at the
	 * index passed through as a parameter. Then it assigns items to 
	 * reference the new array.)
	 * 
	 * @param (int i) (the index of the array the needs to be removed.)
	 */
	public void changeArray(int removed){
		Score[] x = new Score[items.length];
		for(int i=0; i<numItems; i++){
			if(i<removed){
				x[i] = items[i];
			}
			if(i>removed){
				x[i-1] = items[i];
			}
		}
		numItems--;
		items = x;
	}
	
}
