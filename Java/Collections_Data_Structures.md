#Data Structures
Data structures are ways to store and organize data so you can use it efficiently.

An array is an example of a data structure, which allows multiple elements to be stored in a single variable.

Java includes many other data structures as well, in the java.util package. Each is used to handle data in different ways.
Some of the most common are:

#Java Collections Framework provides a set of interfaces (like List, Set, and Map) and a set of classes (ArrayList, HashSet, HashMap, etc.) that implement those interfaces.

List-> ArrayList
Set	-> HashSet
Map -> HashMap

#All of these are part of the java.util package.
They are used to store, search, sort, and organize data more easily - all using standardized methods and patterns.

#Interface	Common Classes				Description
List	ArrayList, LinkedList			Ordered collection that allows duplicates
Set		HashSet, TreeSet, LinkedHashSet	Collection of unique elements
Map		HashMap, TreeMap, LinkedHashMap	Stores key-value pairs with unique keys



Overview of Classes
The table below gives an overview of the common data structure classes and their characteristics:

Interface	Class	Description
List	
	ArrayList	Resizable array that maintains order and allows duplicates
	LinkedList	List with fast insert and remove operations
Set	
	HashSet		Unordered collection of unique elements
	TreeSet		Sorted set of unique elements (natural order)
	LinkedHashSet	Maintains the order in which elements were inserted
Map	
	HashMap			Stores key/value pairs with no specific order
	TreeMap			Sorted map based on the natural order of keys
	LinkedHashMap	Maintains the order in which keys were inserted
	
***USAGE which collections ?
#  When to Use: List  classes when you care about order, you may have duplicates, and want to access elements by index.
---------------
	You can access elements by their index, 
	Add duplicates, and 
	Maintain the insertion order.
	
#List vs. Array
Array									List
Fixed size								Dynamic size
Faster performance for raw data			More flexible and feature-rich
Not part of Collections Framework		Part of the Collections Framework

	
#How the ArrayList works
The ArrayList class has a regular array inside it. When an element is added, it is placed into the array. 
If the array is not big enough, a new, larger array is created to replace the old one and the old one is removed.

#How the LinkedList works
The LinkedList stores its elements in "containers." The list has a link to the first container and each container has a link to the next container in the list.
To add an element to the list, the element is placed into a new container and that container is linked to one of the other containers in the list.




# When to Use: Set classes when you need to store unique values only.
------------
The Set interface is part of the Java Collections Framework and is used to store a collection of unique elements.

	Set does not allow duplicates and 
	It does not preserve the order of elements (unless you're using TreeSet or LinkedHashSet).

Common classes that implement Set:

	***HashSet - fast and unordered
	***TreeSet - sorted set
	***LinkedHashSet - ordered by insertion

#Set vs. List
List						Set
Allows duplicates			Does not allow duplicates
Maintains order				Does not guarantee order
Access by index				No index-based access	

#Common Set Methods
Method		Description
add()		Adds an element if it's not already in the set
remove()	Removes the element from the set
contains()	Checks if the set contains the element
size()		Returns the number of elements
clear()		Removes all elements	

#HashSet
A HashSet is a collection of elements where every element is unique.
Dose not preserve the order.


#TreeSet
A TreeSet is a collection that stores unique elements in sorted order.
Treeset stores in sorted order.

#Use HashSet when you care about speed, and TreeSet when you need sorted elements. 

#HashSet vs TreeSet
Feature		HashSet					TreeSet
Order		No guaranteed order		Sorted (natural order)
Duplicates	Not allowed				Not allowed
Performance	Faster (no sorting)		Slower (due to sorting)

#LinkedHashSet
A LinkedHashSet is a collection that stores unique elements and remembers the order they were added.
LinkedHashSet preserve the order.





#  When to Use: Map classes when you need to store pairs of keys and values, like a name and its phone number.

#Map Interface
The Map interface is a part of the Java Collections Framework and is used to store key-value pairs. 
	Each key must be unique, but values can be duplicated.

A Map is useful when you want to associate a key (like a name or ID) with a value (like an age or description).

Common classes that implement Map:

	**HashMap - fast and unordered
	**TreeMap - sorted by key
	**LinkedHashMap - ordered by insertion



#Use a Map when you want to associate values with unique keys, like storing user IDs with names.

Common Map Methods
Method		Description
put()		Adds or updates a key-value pair
get()		Returns the value for a given key
remove()	Removes the key and its value
containsKey()	Checks if the map contains the key
keySet()	Returns a set of all keys

# HashMap
A HashMap stores items in key/value pairs, where each key maps to a specific value.

HashMap accessing using a key to retrieve its associated value.

HashMap does not maintain order,

#TreeMap
A TreeMap is a collection that stores key/value pairs in sorted order by key.

It is part of the java.util package and implements the Map interface.

Unlike HashMap, which does not maintain order, TreeMap keeps its keys sorted (Keys will be displyed in sorted order).


#HashMap vs	TreeMap	
Feature		HashMap						TreeMap
Order		No guaranteed order			Sorted by keys
Null Keys	Allows one null key			Does not allow null keys
Performance	Faster (no sorting)			Slower (maintains sorted order)



#LinkedHashMap
A LinkedHashMap stores keys and values, and keeps them in the same order you put them in.

It is part of the java.util package and implements the Map interface.
LinkedHashMap preserves the order we put them in.

#Use LinkedHashMap when you want predictable iteration order (insertion order).


#HashMap vs LinkedHashMap
Feature		HashMap						LinkedHashMap
Order		No guaranteed order			Insertion order preserved
Performance	Faster for random access	Slightly slower due to ordering
Duplicates	Keys must be unique			Keys must be unique


# Iterator
An Iterator is an object that can be used to loop through collections, like ArrayList and HashSet.

It is called an "iterator" because "iterating" is the technical term for looping.

Note: Trying to remove items using a for loop or a for-each loop would not work correctly because the 
		collection is changing size at the same time that the code is trying to loop.



#Algorithms

Useful Algorithms
The Collections class contains many more algorithms, such as:

Collections.max() - find the largest element
Collections.min() - find the smallest element
Collections.shuffle() - randomly shuffle elements
Collections.frequency() - count how many times an element appears
Collections.swap() - swap two elements in a list
In this example, we use Collections.max() and Collections.min() to find the largest and smallest element in an ArrayList:

