package a7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * This class reads in a file of movie reviews. From that it "learns" what words
 * are associated with good reviews and what are associated with poor reviews.
 * It can then predict the score for a new review that contains those words.
 * 
 * @author Preston Malen
 *
 */
public class MovieReviewPredictor {

	// The wordValue maps a word to its average score from the reviews.
	HashMap<String, Double> wordValue = new HashMap<String, Double>();

	/**
	 * Construct a new MovieReviewPredictor by reading in a file of reviews with
	 * scores and using that to create a mapping between a word and its score. At
	 * the end of the constructor, the wordValue HashMap should be filled in and
	 * ready to be used.
	 * 
	 * A helpful message should be printed and then System exit called if the file
	 * is not found.
	 */
	public MovieReviewPredictor(String filename) {
		// Read in the lines from the filename.
		ArrayList<String> reviews = new ArrayList<String>();
		File file = new File(filename);
		Scanner s;
		try {
			s = new Scanner(file);
			while(s.hasNextLine()) {
				String line = s.nextLine();
				reviews.add(line);
			}	
		} 
			catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	
		computeWordValue(reviews);
		// Get the word values from the lines and store them in wordValue
		// using the appropriate class method.
	}

	/**
	 * Read the lines from the file and stores each in an ArrayList. Each line
	 * should be processed as follows: - the line is set to all lower case using the
	 * String toLowerCase method - punctuation is removed by removing all characters
	 * that are not a through z or 0 through 9. This can be done using the String
	 * replaceAll method, like 
	 * String newString = string.replaceAll("[^a-z0-9 ]", "");
	 * 
	 * @param filename the name of the file to read
	 * @return an ArrayList of the lines with punctuation removed and made all
	 *         lowercase.
	 * @throws FileNotFoundException
	 */
	public static ArrayList<String> linesFromFile(String filename) throws FileNotFoundException {
		ArrayList<String> reviews = new ArrayList<>();
		File file = new File(filename);
		Scanner s = new Scanner(file);
		while (s.hasNextLine()) {
			String line = s.nextLine().toLowerCase();
			line = line.replaceAll("[^a-z0-9 ]", "");
			reviews.add(line);
		}
		s.close();
		return reviews;
	}

	/**
	 * Given an ArrayList of review Strings, compute the total score associated with
	 * each word and the number of times each word appears.
	 * 
	 * @param reviews     An ArrayList of lines representing reviews. The first word
	 *                    in each line is a score.
	 * @param totalScores a HashMap of each word as a key and its total score as the
	 *                    value
	 * @param wordCount   a HashMap of each word as a key and the number of times it
	 *                    appears in the reviews as a value
	 */
	public void computeScoreAndCounts(ArrayList<String> reviews, HashMap<String, Integer> totalScores,
			HashMap<String, Integer> wordCount) {
		
		
		// Loop over all the reviews.
		for (String line : reviews) {
		// Split line into words
			String[] items = line.split(" ");
			
		// Looping over remaining words and populating wordCount	
			for(int i = 1; i < items.length; i++) {	
				if(wordCount.containsKey(items[i])) {
					int currentCount = wordCount.get(items[i]);
					wordCount.put(items[i], currentCount + 1);					
				}
				
				else {
					wordCount.put(items[i], 1);
				}				
			}
			
		// Looping over remaining words and populating totalScores	
			for(int i = 1; i < items.length; i++) {
				if(totalScores.containsKey(items[i])) {
					int currentCount = totalScores.get(items[i]);
					totalScores.put(items[i], currentCount + Integer.parseInt(items[0]));				
				}
				else {
					totalScores.put(items[i], Integer.parseInt(items[0]));
				}
			}			
		}
	}

	/**
	 * Given a list of reviews from the file, this method computes the average score
	 * for each word in the reviews and stores that score in the wordValue HashMap,
	 * where the key is the word and the value is the average score.
	 * 
	 * To get the average score, first compute the total score for a word and the
	 * number of times it appears.
	 * 
	 * As a slight improvement, only store the word in wordValue if the score is not
	 * an average word - if the score is less than 1.75 or greater than 2.25.
	 * 
	 * @param reviews An ArrayList of lines representing reviews. The first word in
	 *                each line is a score.
	 */
	public void computeWordValue(ArrayList<String> reviews) {
		

		// Initialize any needed HashMaps
		HashMap<String, Integer> totalScores = new HashMap<String, Integer>();
		HashMap<String, Integer> wordCount = new HashMap<String, Integer>();
		
		// Compute the word total scores and counts using the appropriate method.
		computeScoreAndCounts(reviews, totalScores, wordCount);
		
		// Build a HashMap of average scores
		for(String key : totalScores.keySet()) {
			double avg = (double) totalScores.get(key) / wordCount.get(key);
				if(avg < 1.75 || avg > 2.25) {
					wordValue.put(key, avg);
				}
		}
	}

	
	
	/**
	 * This method predicts a review score given a review. A review is of the form
	 * "some text all in lower case and no punctuation". The predicted score is the
	 * average wordValue of any non-null word.
	 * 
	 * @param review a String of numbers and lowercase letters.
	 * @return the predicted score, or -1 if the review has no words in wordValue.
	 */
	public double predictScore(String review) {
		// Break the review into words
		String[] items = review.split(" ");
		double word = 0;
		double score = 0;
		/*
		 * Find the total score for all words in the review that are in the wordValue
		 * HashMap. Count up how many words added to the score.
		 */
		for(int i = 0; i < items.length; i++) {
			if(wordValue.containsKey(items[i])) {
				word ++;
				score += wordValue.get(items[i]);
			}			
		}
		// returning -1 if the word did not exist in wordValue
		if(word == 0) {
			return -1;
		}
		else {
			double avg = (double) score / word;
			return avg;
		}
		
	}
	
	
	/**
	 * Predict movie reviews by first learning about words in review. (This does not
	 * need to be modified).
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// First write and test your program with the small set of reviews. Then,
		// learn with the big file and try different tests.
		String learningFilename = "src/a7/MovieReviews.txt";
//		String learningFilename = "src/a7/smallReviews.txt";

		// Make an instance of the predictor based on the filename.
		MovieReviewPredictor predictor = new MovieReviewPredictor(learningFilename);
		System.out.println(predictor.wordValue);

		// Test the predictor.
		String testFilename = "src/a7/smallReviews.txt";
		// Read the file of test reviews.
		ArrayList<String> lines = null;
		try {
			lines = linesFromFile(testFilename);
		} catch (FileNotFoundException e) {
			System.out.println("Could not find the file for testing");
			System.exit(-1);
		}

		// Loop over each review. Compare the predicted score with the real score.
		for (int index = 0; index < lines.size(); index++) {
			String line = lines.get(index);
			String[] words = line.split(" ");
			String noScoreLine = "";
			for (int wordIndex = 1; wordIndex < words.length; wordIndex++)
				noScoreLine += words[wordIndex] + " ";
			String prediction = String.format("%.1f", predictor.predictScore(noScoreLine));
			System.out.print("Predicted: " + prediction + " Actual: ");
			System.out.println(line);
		}
	}
}

/* The movie predictor was actually more accurate than I anticipated. I did not expect 100% accuracy
 * but the method returned results that were surprising to me. I would say that the predictions are generally
 * effective. If this was a method used by a small newspaper or something to collect data, I would definitely
 * trust it to produce good results. Would I feel comfortable using this for a huge multi-million dollar company
 * that was collecting data on movie reviews? Probably not. But I learned a lot in this assignment and feel
 * like I am getting better at the object oriented methods
 */

