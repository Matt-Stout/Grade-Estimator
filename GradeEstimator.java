/////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Spring 2016 
// PROJECT:          (p1)
// FILE:             (GradeEstimator.java)
//
// TEAM:    (Team 03 - Numb Biscuits)
// Authors: (Nafis Faisal Arrafi, Christian Farris, Kevin Fischer, Matthew 
//  		Matthew Stout, Chen Yang, and Ruimin Zhang)
//
//////////////////////////// 80 columns wide //////////////////////////////////

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * (This creates a grade estimate based off of a file. It also grades and displays
 * a grade report.)
 *
 */
public class GradeEstimator {

    private ScoreList list;
    private final String[] LETTER;
    private final Double[] THRESHOLD;
    private final String[] CATEGORY;
    private final Double[] WEIGHT;

    /**
     * (This method constructs GradeEstimator objects.)
     *
     * PRECONDITIONS: (Threshold and Letter arrays must be equal. Category
     * and Weight arrays must be equal.)
     * 
     * POSTCONDITIONS: (Field variables must be set to parameters.)
     *
     * @param (String[] letter_grade) (An array of letter grades.)
     * @param (Double[] grade_threshold) (An array holding the grade thresholds.)
     * @param (String[] category) (An array holding homework categories.)
     * @param (Double[] weight) (An array holding the weight for each category.)
     * @param (ScoreList list) (A ScoreList holding information from the file.)
     */
    public GradeEstimator(String[] letter_grade, Double[] grade_threshold, String[] category,Double[] weight, ScoreList list) throws GradeFileFormatException {
        
    	if(grade_threshold.length != letter_grade.length){
    		
            throw new GradeFileFormatException();
    	
    	}
        
    	if(category.length != weight.length){
         
        	throw new GradeFileFormatException();
        
    	}
            
        this.LETTER = letter_grade;
        this.THRESHOLD = grade_threshold;
        this.CATEGORY = category;
        this.list = list;
        this.WEIGHT = weight;
    
    }
    
    /**
     * (A grade estimator and a report will be created.If not a 
     * FileNotFoundException or GradeFileFormatException will 
     * be thrown and then caught.)
     *
     * PRECONDITIONS: (Args must be equal to 1. If it isn't it 
     * 					a message.)
     *
     * @param (String[] args) (Command line arguments.)
     */
    public static void main(String[] args) {
    	
        try {
        	if (args.length != 1) { 
        		System.out.println(Config.USAGE_MESSAGE);
        		Double[] threshold = new Double[Config.GRADE_THRESHOLD.length];
        		Double[] weight = new Double[Config.CATEGORY_WEIGHT.length];
        		//convert double to Double
        		for(int i = 0; i < Config.GRADE_THRESHOLD.length; i++) {
        			threshold[i] = Config.GRADE_THRESHOLD[i];    //auto-boxing
        		}
        		
        		for(int i = 0; i < Config.CATEGORY_WEIGHT.length; i++) {
        			weight[i] = Config.CATEGORY_WEIGHT[i];  //auto-boxing
        		}
        		//create grade estimator using defaults
        		GradeEstimator gradeEstimator = new GradeEstimator(Config.GRADE_LETTER, threshold, 
        													Config.CATEGORY_KEY, weight, new ScoreList());
        		System.out.println(gradeEstimator.getEstimateReport());
            }
        	else{
        		
        		// Creates a new GradeEstimator from the information in args at index 0.
        		GradeEstimator gradeEstimator = createGradeEstimatorFromFile(args[0]);
            	
        		System.out.println(gradeEstimator.getEstimateReport());
        	}
        
        } catch (FileNotFoundException e) {
            
        	System.out.println("java.io.FileNotFoundException: " + args[0] + " (No such file or directory)");
        
        } catch (GradeFileFormatException e) {
           
        	System.out.println("GradeFileFormatException");
        }
    }
    
    /**
     * (This method converts the parameter into a formatted String. Then it
     * creates a Grade Estimator object using the formated String.)
     *
     * PRECONDITIONS: (None.)
     * 
     * POSTCONDITIONS: (A GradeEstimator has been created.)
     *
     * @param (String gradeInfo) (File name.)
     * @return (It constructs a GradeEstimator object.)
     */
    public static GradeEstimator createGradeEstimatorFromFile(String gradeInfo)
            throws FileNotFoundException, GradeFileFormatException {
        
    	//String variable to hold the formated String from the .txt file.
    	String text = convertToString(gradeInfo);
    	
        // Creates a new GradeEstimator from a formatted String.
    	GradeEstimator gradeEstimator = formatStrings(text);
        
    	return gradeEstimator;
    }
    
    /**
     * (The information in the file is stored in a formatted String.)
     *
     * PRECONDITIONS: (None.)
     * 
     * POSTCONDITIONS: (Text has been formatted.)
     *
     * @param (String text) (The file name passed through the command line.)
     * @return (The file formatted into a string.)
     */
    private static String convertToString(String text) throws FileNotFoundException {
    		
    	// Scanner to add the information from the file to a String.
        Scanner scnr = new Scanner(new File(text));
        
        //String to hold information from the .txt file.
        String combinedText = "";
        
        // Adds the text from each line to the string and adds a ' to seperate lines.
        while (scnr.hasNextLine()){
        	combinedText += scnr.nextLine() + "`"; 
        }

        scnr.close();
        
        return combinedText;
    }
    
    /**
     * (This method splits the string into an array based upon lines.
     * Then it seperates the information stored in the lines.)
     * 
     * POSTCONDITIONS: (The file information has been seperated into arrays
     * 					that store relevent information.)
     *
     * @param (String text) (Formatted file information.)
     * @return (Parameters for a new GradeEstimator object.)
     */
    private static GradeEstimator formatStrings(String text) throws GradeFileFormatException {
    	
    	// Minimun number of lines the file must contain.
    	final int MIN_NUM_OF_LINES = 5;
    	
    	// Number of Lines with formatting information.
    	final int LINES_WITH_ARRAY_INFO = 4;
    	
    	// Creates a new Scorelist to hold Scores from the .txt file.
    	ScoreList list = new ScoreList();
       
    	// A String array to seperate the lines of the .txt file.
    	String[] temp = text.split("`");
       
    	// A 2D array to hold the important information for creating the grade report.
    	Object[][] arguments = new Object[4][];

    	// Eliminates comments on the lines.
        for(int i = 0; i < temp.length; i++){
        
        	temp[i] = temp[i].split("#")[0]; 
       
        }
        
        // Checks if the file has enough lines.
        if (temp.length < MIN_NUM_OF_LINES){
        
        	throw new GradeFileFormatException();
    	
        }
        
        // Constructs the arrays for important information and adds Scores to the ScoreList
        for(int i = 0; i < temp.length; i++){
          
        	try {
               
        		if(i < LINES_WITH_ARRAY_INFO){
        
                	arguments[i] = stringArrayToTypeArray(temp[i], i, list);
        		
        		}else{
                	
                	stringArrayToTypeArray(temp[i], i, list);
        		}
            
        	} catch (Exception e) {
            
            	throw new GradeFileFormatException();
            
            }
        }

        // Makes sure a score has been added to the ScoreList.
        if (list.size() == 0){
            
        	throw new GradeFileFormatException();
        
        }
        
        return new GradeEstimator((String[])arguments[0], (Double[])arguments[1], 
        						(String[])arguments[2], (Double[])arguments[3], list);
    }
    
    /**
     * (This method converts a string to and array of strings. Then it converts that
     * array into a type array.)
     *
     * @param (String temp) (String holding information from .txt file.)
     * @param (int index) (The line of text minus one.)
     * @param (ScoreList list) (Scorelist to hold Score information.)
     * @return (An object array holding formatted information from the .txt file.)
     */
    private static Object[] stringArrayToTypeArray(String temp, int index, ScoreList list) throws GradeFileFormatException {
        
    	// A String array that splits the string by spaces
    	String[] splitArray = temp.split(" ");
        
    	// Index of the array for Letter grade info
    	final int LETTER_INDEX = 0;
        
    	// Index of the array for Threshold info
    	final int THRESHOLD_INDEX = 1;
        
    	// Index of the array for Category info
    	final int CATEGORY_INDEX = 2;
        
    	// Index of the array for Weight info.
    	final int WEIGHT_INDEX = 3;
    	
    	// Index of the array that assignment information begins.
    	final int ASSIGNMENT_START_INDEX = 3;
        
        try{
           
        	// An object array to hold information from the .txt file.
        	Object[] typeArray = null;
            
        	// Check to make sure the .txt file was properly formatted.
            if(index == THRESHOLD_INDEX || index == WEIGHT_INDEX){
                
            	try {
                	
            		typeArray = new Double[splitArray.length];
        
                	for(int i = 0; i < typeArray.length; i++){
                    
                		typeArray[i] = Double.parseDouble(splitArray[i]); 
                    
                	}
                
            	}catch(Exception e){
                	
                    throw new GradeFileFormatException();
                
                }
           
            }
            
            // Check to make sure the .txt file was properly formatted.
            else if(index == LETTER_INDEX || index == CATEGORY_INDEX){
                
            	for(int i = 0; i < splitArray.length; i++){
            		
                    if(isNum(splitArray[i]))
                        
                    	throw new GradeFileFormatException();
                }
                
                typeArray = splitArray;
            } 
            
            // Starts adding scores to ScoreList
            else if(index > ASSIGNMENT_START_INDEX){
                
            	arrayToScore(splitArray, list);
            }
            
            return typeArray;
        
        } catch (Exception e) {
            
        	throw new GradeFileFormatException();
        
        }
    }
    
    /**
     * (Checks if the value of the string is a number. If not it throws
     * and catches a NumberFormatException Exception.)
     *
     * @param (String text) (A string that holds information from the .txt file.)
     * @return (Boolean of whether or not the string is a number.)
     */
    private static boolean isNum(String text) {
        try  {
        
        	Double.parseDouble(text);
        
        } catch(NumberFormatException e) {
        
        	return false;
        }
       
        return true;
      
    }
    
    /**
     * (This method takes the array and uses its information to create Score
     * objects to add to a ScoreList.)
     *
     * @param (String[] splitArray) (Information to create Score objects.)
     * @param (ScoreList list) (Scorelist to add Scores to.)
     */
    private static void arrayToScore(String[] splitArray, ScoreList list) throws Exception {
       
    	try {
    		
    		// String to hold assignment name
            String name = splitArray[0];
        
            // double to hold points
            double points = Double.parseDouble(splitArray[1]);
            // Double to hold points possible
            double pointsPossible = Double.parseDouble(splitArray[2]);

            // Adds new score to ScoreList.
            list.add(new Score(name, points, pointsPossible));
        
        } catch(Exception e) {
            
        	throw e;
        }
    }
    
    /**
     * (This method takes the information from the file that has been used to
     * create a GradeEstimator object. Then it uses that information to create
     * a report to display that it.)
     * 
     * @return (A String holding grade information from the file)
     */
    public String getEstimateReport() {
        
    	// String to contain the grade report.
    	String gradeReport = "";
        
    	// Double array to hold grades.
    	double[] unweightedAverage = new double[CATEGORY.length];
        
    	// Double to hold the grade.
    	double totalWeightedAverage = 0.0;

        for(int i = 0; i < CATEGORY.length; i++) {
        
        	ScoreIterator iterator = new ScoreIterator(list, CATEGORY[i].charAt(0) + "");
            
        	double totalPercent = 0;
            
        	int totalScores = 0;

            while(iterator.hasNext()) {
            
            	Score curr = iterator.next();
                
            	gradeReport += String.format("%s%8.2f\n", curr.getName(), curr.getPercent());
                
            	totalPercent += curr.getPercent();
                
            	totalScores++;
            }
            
            if(totalScores > 0) {
            	unweightedAverage[i] = totalPercent / totalScores;
            } else {
            	//if there is no score for the category, then give 100 percent
            	unweightedAverage[i] = 100;
            }
        
        }
        
        gradeReport += "Grade estimate is based on " + list.size() + " scores\n";

        for(int i=0; i<CATEGORY.length; i++) {
        
        	double grade = ((WEIGHT[i] / 100) * unweightedAverage[i]);
        	
        	gradeReport += String.format("%7.2f%% = %5.2f%% of %2.0f%% for %s\n", grade, unweightedAverage[i], WEIGHT[i], CATEGORY[i]);
            
        	totalWeightedAverage += (WEIGHT[i] / 100) * unweightedAverage[i];
        
        }

        gradeReport += "--------------------------------\n";
        
        gradeReport += String.format("%7.2f%% weighted percent\n", totalWeightedAverage);
        
        // Makes sure the last threshold value is 0.
        if(THRESHOLD[THRESHOLD.length -1] != 0 && totalWeightedAverage < THRESHOLD[THRESHOLD.length-1]){
        	
        	gradeReport += String.format("Letter Grade Estimate: unable to estimate letter grade for " + totalWeightedAverage);
        
        }
        // Figures out the letter grade.
        else {
        	int index = -1;

            for(int i = 0; i < THRESHOLD.length; i++)  {
        
            	if(totalWeightedAverage >= THRESHOLD[i]) {
                
            		index = i;
                    
            		break;
                }
            }
            gradeReport += String.format("Letter Grade Estimate: " + LETTER[index]);
        
        }
           
        return gradeReport;
    }
}