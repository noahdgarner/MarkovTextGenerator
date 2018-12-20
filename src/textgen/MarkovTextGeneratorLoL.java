package textgen;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;



public class MarkovTextGeneratorLoL implements MarkovTextGenerator {

	// The list of words with their next words
	private List<ListNode> wordList; 
	
	// The starting "word"
	private String starter;
	
	// The random number generator
	private Random rnGenerator;
	
	public MarkovTextGeneratorLoL(Random generator)
	{
        wordList = new LinkedList<>();
		starter = "";
		rnGenerator = generator;
	}
	
	
	/** Train the generator by adding the sourceText */
	@Override
	public void train(String sourceText)
	{
	    if(sourceText.length() == 0){
            return;

        }
        else {
            String [] wordsArray = sourceText.split("\\s+"); //split on a space
            starter = wordsArray[0];

            String prevWord = starter;
            ListNode node;
            String w; //needed because we won't start at the first element in for loop

            for(int i = 1; i < wordsArray.length+1; i++) { //increase size of both i and words
                //whenever i is at the last element basically, we will add [0] to
                // the nodes' nextWords list
                if (i == wordsArray.length)
                    w = wordsArray[0];
                else
                    w = wordsArray[i];
                node = search(prevWord); //creates a ListNode with the prevWord string
                //the node was not null, meaning it , -> it existed in wordList, so append 'w' to nodes' nextwords
                if (node == null) {
                    //we need to make a new node with prevWord as the node's word
                    //then add
                    node = new ListNode(prevWord);
                    //auto append w to nodes' wordList
                    node.addNextWord(w);
                    //then add the node to the list of LisTNodes?? lol
                    wordList.add(node);
                }
                //the node was null, -> create
                else
                    node.addNextWord(w);

                //the next iteration uses prevWord = w
                prevWord = w;
            }
        }
	}
	
	/** 
	 * Generate the number of words requested.
	 */
	@Override
	public String generateText(int numWords) {

	    String output = "";

        if(wordList.isEmpty() || numWords == 0)
            return output;


        //set up for output string
        String currWord = starter;

        output+=currWord;

        //create a node to use when searching for currWord and generating a random nextWord
        ListNode aNode;

        while(numWords != 1){ //1 because 0 is taken care of above
            aNode = search(currWord);
            String w = aNode.getRandomNextWord(rnGenerator);
            output+=" "+w; //add a space to existing string, then 2nd string
            currWord = w; //moving on to next word
            numWords--;   //1 less word to take care of
        }

		return output;
	}

	// Can be helpful for debugging
	@Override
	public String toString()
	{
		String toReturn = "";
		for (ListNode n : wordList)
			toReturn += n.toString();

		return toReturn;
	}

	/** Retrain the generator from scratch on the source text */
	@Override
	public void retrain(String sourceText)
	{
	    wordList = new LinkedList<>(); //clear old data, and call train again
        starter = "";                  //clear the old data that starter held
        train(sourceText);              //call train() to retrain
	}
	
	// TODO: Add any private helper methods you need here.
    private ListNode search(String word) {
        for (ListNode node : wordList) {
            if (word.equals(node.getWord())){
                return node;
            }
        }
        return null;
    }



	/**
	 * This is a minimal set of tests.  Note that it can be difficult
	 * to test methods/classes with randomized behavior.   
	 * @param args
	 */
	public static void main(String[] args)
	{
		// feed the generator a fixed random value for repeatable behavior
		MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random());
		String textString = "hi there hi leo";
		System.out.println(textString);
		gen.train(textString);
		System.out.println(gen);
        System.out.println();
		System.out.println(gen.generateText(5));

		String textString2 = "You say yes, I say no, "+
				"You say stop, and I say go, go, go, "+
				"Oh no. You say goodbye and I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"I say high, you say low, "+
				"You say why, and I say I don't know. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"Why, why, why, why, why, why, "+
				"Do you say goodbye. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"You say yes, I say no, "+
				"You say stop and I say go, go, go. "+
				"Oh, oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello,";
		System.out.println(textString2);
		gen.retrain(textString2);
        System.out.println();
		System.out.println(gen);
		System.out.println(gen.generateText(20));

	}
}

/** Links a word to the next words in the list 
 * You should use this class in your implementation. */

class ListNode //wordNode
{
    // The word that is linking to the next words
	private String word;
	
	// The next words that could follow it
	private List<String> nextWords;
	
	ListNode(String word)
	{
		this.word = word;
		nextWords = new LinkedList<String>();
	}
	
	public String getWord()
	{
		return word;
	}

	public void addNextWord(String nextWord)
	{
		nextWords.add(nextWord);
	}
	
	public String getRandomNextWord(Random generator)
	{
		// TODO: Implement this method
        //generate a random int using generator, get that index from nextWords

        int listSize = nextWords.size();

        int randomIndex = generator.nextInt(listSize);

        String randomNextWord = nextWords.get(randomIndex);

        return randomNextWord;

	    // The random number generator should be passed from 
	    // the MarkovTextGeneratorLoL class
	    //return null;
	}


	//toString() method stub: visual representation of the printed class
	public String toString()
	{
		String toReturn = word + ": ";
		for (String s : nextWords) {
			toReturn += s + "->";
		}
		toReturn += "\n";
		return toReturn;
	}
	
}


