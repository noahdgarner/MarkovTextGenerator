package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 *
 *
 * @author UC San Diego Intermediate Programming MOOC team
 * @param <E> The type of the elements stored in the list
 *
 */

public class MyLinkedList<E> extends AbstractList<E> {

    //sentinel nodes for the beginning and end of linked list
	LLNode head; //
	LLNode tail;
	int size;


	/** Create a new empty LinkedList with a sentinel head and sentinel tail node */
	public MyLinkedList() {

	    //set constructor params
		head = new LLNode(null);
		tail = new LLNode(null);
	    size = 0;

	    //sentinel nodes point to each other
	    head.next = tail; //points to tail sentinel (1) on creation of LL before added data
	    tail.prev = head; //points to head sentinel (0) on creation of LL before added data
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
    //list.addback
    //addEnd

	public boolean add(E element ) 
	{

	    if (element == null) {
	        //really helpful to throw exceptions
            //when using generics
            //because we can check for strings or integers or doubles
            throw new NullPointerException("cannot add null elements to list. Program Kill");
	    }
        else {
	        //we make a new Node with 'element' data
            LLNode<E> newNode = new LLNode(element); //takes element which is data, and creates node

            //give that nodes prev and next
            newNode.prev = tail.prev;   //what was the last tail pointing to?
            newNode.next = tail.prev.next; //same thing as saying newNode.next= tail // null;


            tail.prev.next = newNode;      //the tail now points to n
            newNode.next.prev = newNode;

            //increment size because we increased the LL list size by 1 after an add
            size++;
        }
        //all is good
		return true;
	}

    public boolean addFront(E element )
    {

        if (element == null) {
            //really helpful to throw exceptions
            //when using generics
            //because we can check for strings or integers or doubles
            throw new NullPointerException("cannot add null elements to list. Program Kill");
        }

        else {
            //we make a new Node with 'element' data
            LLNode<E> newNode = new LLNode(element); //takes element which is data, and creates node

            //whatever the current head was referencing
            newNode.next = head.next;
            newNode.prev = head;    //new node is now set up

            head.next = newNode;
            newNode.next.prev = newNode;

            //increment size because we increased the LL list size by 1 after an add
            size++;
        }
        //all is good
        return true;
    }

	/** Get the element at position index
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index)
	{
	    //you cannot get an element from an empty list duh, so return null
        if (size == 0)
            throw new IndexOutOfBoundsException("Check out of bounds, size is 0 cannot get.");

        if (index < 0 || index > size-1) {
            //really helpful to throw exceptions
            //when using generics
            //because we can check for strings or integers or doubles
            throw new IndexOutOfBoundsException(index+" is out of bounds, enter a value in bound.");
        }

        LLNode<E> getNode = head.next;

        //another way to write is

        while (index > 0) {
            getNode = getNode.next;
            index--;
        }
        return getNode.data;
	}

	//get node at specific index
    public LLNode getNode(int index)
    {
        //you cannot get an element from an empty list duh, so return null
        if (size == 0)
            throw new IndexOutOfBoundsException("Check out of bounds, size is 0 cannot get.");
            //really helpful to throw exceptions
            //when using generics
            //because we can check for strings or integers or doubles
        if (index < 0 || index > size-1)
            throw new IndexOutOfBoundsException(index+" is out of bounds, enter a value in bound.");

        LLNode<E> getNode = head.next;

        //another way to write is

        while (index > 0) {
            getNode = getNode.next;
            index--;
        }
        return getNode;
    }


	//add element at a specific index
	public void add(int index, E element ) 
	{
        if (element == null)
            throw new NullPointerException("Cannot add null elements to the list");

        if(index == 0) {
            addFront(element);
            return;
        }
        if(index == size-1) {
            add(element);
            return;
        }

        LLNode<E>currNode = getNode(index);
        LLNode nodeToInsert = new LLNode(element); //create the node with data


        //link in our new node
        nodeToInsert.prev = currNode.prev;
        nodeToInsert.next = currNode;    //new node is now set up


        //nodeToInsert is now linked in, so link it's previous to it, and its head to it
        nodeToInsert.next.prev = nodeToInsert;
        nodeToInsert.prev.next = nodeToInsert;


        size++;
	}

	/** Return the size of the list */
	public int size() 
	{
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */

	public E remove(int index) 
	{
	    LLNode<E> rmNode = getNode(index);

	    rmNode.next.prev = rmNode.prev;
	    rmNode.prev.next = rmNode.next;
        size--;

		return rmNode.data;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
	    if(element == null) throw new NullPointerException("Cannot set null data");

	    LLNode<E> setNode = getNode(index);
	    E old = setNode.data;
	    setNode.data = element;


		// TODO: Implement this method
		return old;
	}   
}

class LLNode<E>
{
    //every Node will have two nodes that it points to, a prev and a next
    //and it will have data
    LLNode next;
	LLNode prev;
	E data;


	public LLNode(E theData) {

		this.prev = null;
		this.next = null;
        this.data = theData;

    }

}
