package com.github.jflaherty.cardgames.poker.helpers;

import java.util.Collection;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.*;

import com.github.jflaherty.cardgames.playingcards.french.Card;

public class SequenceHelper {
    
    private SequenceHelper() {}

    /**
     * Finds and returns a collection of <Card> where Card property values occurred
     * a specified amount of times in the collection
     * 
     * @param collection
     *            - Input collection
     * @param comparisonBasis
     *            - property on which the comparison occurs
     * @param repeatsAmount
     *            - int value of searched occurrence amount
     * @return new collection of T items meeting the demands
     * @throws IllegalArgumentException
     *             arguments cannot be null
     */
    public static List<Card> findRepeats(Collection<Card> collection, Function<Card, ?> comparisonBasis, int repeatsAmount) throws IllegalArgumentException {
	if (collection == null || comparisonBasis == null) {
	    throw new IllegalArgumentException("Collection and/or compared property cannot be null!");
	}
	
	return collection
	.stream()
	.collect(Collectors.groupingBy(comparisonBasis))
	.values()
	.stream()
	.filter(repeats -> repeats.size() == repeatsAmount)
	.flatMap(List::stream)
	.collect(Collectors.toList());
	
//	Map<?, List<Card>> groups = collection
//	.stream()
//	.collect(Collectors.groupingBy(comparisonBasis));
//	
//	List<Card> repeatCards = groups.values()
//	.stream()
//	.filter(repeats -> repeats.size() == repeatsAmount)
//	.flatMap(List::stream)
//	.collect(Collectors.toList());
//	
//	return repeatCards;
    }
    
    /**
     * Analogical to findRepeats method, but only states if there are specified
     * repeats instead of returning them.
     * 
     * @param collection
     *            - input collection
     * @param comparisonBasis
     *            - property on which the comparison occurs
     * @param repeatsAmount
     *            - int value of searched occurrence amount
     * @return true if the collection meets the demands
     * @throws IllegalArgumentException
     *             arguments cannot be null
     * @see findRepeats
     */
    public static boolean hasRepeats(Collection<Card> collection, Function<Card, ?> comparisonBasis, int repeatsAmount) throws IllegalArgumentException {
	if (collection == null || comparisonBasis == null) {
	    throw new IllegalArgumentException("Collection and compared property cannot be null!");
	}
	return collection
		.stream()
		.collect(Collectors.groupingBy(comparisonBasis))
		.values()
		.stream()
		.anyMatch(repeats -> repeats.size() == repeatsAmount);
    }

    /**
     * Determines if specified items' Integer properties can form a strictly
     * monotonically increasing sequence where each consecutive value is
     * increased by one. Eg. 1, 2, 3 or 19, 20, 21, 22, 23 or 4, 5 are s.m.i.s.
     * by one.
     * 
     * @param collection
     *            - input collection to be checked
     * @param propertyToBeMonotonic
     *            - checked property. Should be a function of T that returns
     *            instance of Integer
     * @return defines if such sequence exists in collection
     * @throws IllegalArgumentException
     *             arguments cannot be null
     */
    public static <T> boolean isMonotonicByOne(Collection<Card> collection) throws IllegalArgumentException {
	if (collection == null) {
	    throw new IllegalArgumentException("Cards cannot be null!");
	}
	if (collection.isEmpty()) {
	    return false;
	}
	SortedSet<Integer> valuesDistinctSorted = 
		new TreeSet<Integer>(collection
		.stream()
		.mapToInt(c -> c.getRank().asInt())
		.distinct()
		.boxed()
		.collect(Collectors.toSet()));
	return ((valuesDistinctSorted.size() == collection.size()) // 5 cards in a straight
		&& ((valuesDistinctSorted.last() - valuesDistinctSorted.first()) == (collection.size() - 1))); 	// i.e  10 high straight (10,9,8,7,6) 
														// = 10 - 6 == 5 - 1
    }

}