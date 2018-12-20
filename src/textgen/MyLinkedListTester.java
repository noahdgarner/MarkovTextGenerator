/**
 * 
 */
package textgen;

import static org.junit.Assert.*;
import junit.framework.*;
import java.util.LinkedList;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;




public class MyLinkedListTester extends TestCase {

	private static final int LONG_LIST_LENGTH =10;

	//instantiated in setup
    MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;

	/**
	 * @throws java.lang.Exception
	 */

	@Before
	public void setUp() {
		// Feel free to use these lists, or add your own
	    shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");



		emptyList = new MyLinkedList<Integer>();



		longerList = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			longerList.add(i);
		}

		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);
		
	}

	
	/** Test if the get method is working correctly.
	 You should not need to add much to this method.
	 * We provide it as an example of a thorough test. */
	@Test
	public void testGet()
	{
		//test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) { //exception caught from MyLinkedList.java

		}
		
		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));

		assertEquals("Check second", "B", shortList.get(1));

		//these are out of bounds checks
		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) { //exception caught from MyLinkedList.java

		}
		try {
			shortList.get(2);
			fail("Check out of bounds");
		}

		catch (IndexOutOfBoundsException e) { //exception caught from MyLinkedList.java

		}
		// test longer list contents
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
		}
		
		// test off the end of the longer array
		try {
			longerList.get(-1);
			//asertion that it will fail
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) { //exception caught from MyLinkedList.java

		}
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) { //exception caught from MyLinkedList.java

		}
	}
	
	
	/** Test removing an element from the list.
	 * We've included the example from the concept challenge.
	 * You will want to add more tests.  */
	@Test
	public void testRemove()
	{

	    //list1 includes 65, 21, 42

		int a = list1.remove(0);

		assertEquals("Remove: check a is correct ", 65, a);
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());

        list1.add(0,65);

        assertEquals("Size correct", 3, list1.size());

        assertEquals("Remove: Check add back works correctly ", (Integer)65, list1.get(0));
        //assertEquals("Remove: Check add works correctly: ", ,)


        assertTrue("Size should now be 3", list1.size() == 3);

        assertEquals("Remove check: Last element in list ", (Integer)42,list1.get(2));



	}


	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */

	@Test
	public void testAddEnd()
	{

        list1.add(100);


        //a statement of something that MUST be true. If it is not, then program is broken
        //cannot recover from broken program, so fix the bugs
        assertEquals("Add(): check add 100 is correct ",list1.get(3),(Integer)100);

        list1.add(200);
        assertEquals("Add(): check add 100 is correct ",list1.get(4),(Integer)200);

        try {
            longerList.add(null);
            fail("Check add null to a list");
        }
	    catch (NullPointerException e) {
            //e.printStackTrace();
        }

        try {
            shortList.add(null);
            //says it should fail, asssert a failure in a try
            fail("Check add null to short list");
        }

        catch (NullPointerException e) {
            //thrownfrom previous
        }

        //two adds to the list1 list created in setup
        assertTrue(list1.add(-35));
        assertTrue(list1.add(0));


        try {
            int a = list1.get(6);
            assertEquals("test get last element", (Integer)a, list1.get(list1.size()-1));
        }
        catch (NullPointerException e){

        }

        //descrption, expected, actual
        assertEquals("Add to char list: ",0,emptyList.size);


	}

	
	/** Test the size of the list */
	@Test

	public void testSize()
	{
	    assertEquals("Test size: ",0,emptyList.size());
        assertEquals("Test size: ",10,longerList.size());
        assertEquals("Test size: ",2,shortList.size());

        //here is a great example of printStackTrace. This exception is caught in our linkedlistclass
        //and is supposed to fail, by the assertion, and a stacktrace is printed to show where in the
        //code the exxception was thrown.
        try {
            emptyList.add(null);
            fail("Check add null fails");
        }
        catch (NullPointerException e) {
            //e.printStackTrace();
        }

        assertEquals("Test size: ",0,emptyList.size());
        emptyList.add(1);
        assertTrue("emptylist size after add 1: ",emptyList.size() == 1);



        for (int i = 0; i <LONG_LIST_LENGTH; i++) {
            try {
                assertTrue("Test add elements to long list: ", longerList.add(i) == true);
            }
            catch (AssertionError e) {
                e.printStackTrace();
            }

            assertTrue("Test size inc by 1 each time added", longerList.size() == LONG_LIST_LENGTH + 1 + i);

        }



	}

	
	
	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test

	public void testAddAtIndex()
	{
	    shortList.add(0,"C");
	    //note, expected, actual
        //assertEquals("Add at first index: ","C",shortList.get(0));


        try {
            longerList.add(1,null);
            fail("Check bounds"); //should fail beca

        }
        catch (NullPointerException e) {

        }
        try {
            shortList.add(-1,null);
            fail("Check bounds"); //should fail beca

        }
        catch (NullPointerException e) {

        }





        assertEquals("Add at index 8: ", (Integer)8,longerList.get(8));

        assertEquals("Add at index 0: ", (Integer)0,longerList.get(0));

        assertEquals("Size should be 11",10, longerList.size);


        assertEquals("index 5: ", (Integer)5,longerList.get(5));

        longerList.add(5,100);

        assertEquals("index 4: ", (Integer)4,longerList.get(4));

        assertEquals("Add at index 5: ", (Integer)100,longerList.get(5));

        assertEquals("index 6: ", (Integer)5,longerList.get(6));

        assertEquals("Size should be 11",11, longerList.size);
        //test that 9 is now in 10th position
        assertEquals("Add at index 10: ", (Integer)9,longerList.get(10));

        longerList.add(10, 10000); //add to the end of the list
        //The list length is now 12, account for this
        assertEquals("Add at index 10(last): ", (Integer)10000,longerList.get(11));


	}
	
	/** Test setting an element in the list */
	@Test

	public void testSet()
	{
	    //65 21 42
        list1.set(0,0);

	    assertEquals("Set: 0 to 0",(Integer)0,list1.get(0));

	    assertEquals("Last is still 42",(Integer)42, list1.get(2));
	    int tester = list1.set(2,0); // old value is 42

        assertEquals("Old val", 42, tester);

        assertEquals("New list", (Integer)0, list1.get(2));

        try {
            list1.set(0,null);
            fail("Checking null");
        }
        catch (NullPointerException e) {

        }

        try {
            list1.set(-100,20);
            fail("Checking bounds");
        }
        catch (IndexOutOfBoundsException e) { //notice you need to specify the exception to catch

        }

        try {
            list1.set(-100,null);
            fail("Checking bounds");
        }
        catch (NullPointerException e) { //notice a null pointer exception is specified

        }


	}
	
	
	// TODO: Optionally add more test methods.
	
}
