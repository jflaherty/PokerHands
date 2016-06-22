package com.github.wsppan.cardgames.poker.hands;

import java.util.ArrayList;
import java.util.Collection;

public class PokerHandTestDataSet {

    public static Collection<PokerHandTestData> getTestData() {
	Collection<PokerHandTestData> testSet = new ArrayList<>();

	testSet.add(PokerHandTestData.testBuilderFactory().withPlayerOneCards("2S 3D 4C 8D 8S").withPlayerTwoCards("JC AC QC TC 9D").andPlayerOneWins(true)
		.withTestName("Pair of eights over high ace").build());
	testSet.add(PokerHandTestData.testBuilderFactory().withPlayerOneCards("2S 3D 4C 8D 8S").withPlayerTwoCards("JC AC QC TC JD").andPlayerOneWins(false)
		.withTestName("Pair of jacks over pair of eights").build());
	testSet.add(PokerHandTestData.testBuilderFactory().withPlayerOneCards("2S 3D 8C 8D 8S").withPlayerTwoCards("JC AC QC TC JD").andPlayerOneWins(true)
		.withTestName("Three eights over pair of jacks").build());
	testSet.add(PokerHandTestData.testBuilderFactory().withPlayerOneCards("2S 3D 3C 3S 8S").withPlayerTwoCards("JC QC QH TC JD").andPlayerOneWins(true)
		.withTestName("Three threes over two pairs of jacks and queens").build());
	testSet.add(PokerHandTestData.testBuilderFactory().withPlayerOneCards("TS JS QS KS AS").withPlayerTwoCards("AD 2D 3D 4D 5D").andPlayerOneWins(true)
		.withTestName("Royal flush over straight flush to five").build());
	testSet.add(PokerHandTestData.testBuilderFactory().withPlayerOneCards("2S 2H 2C 3H 3C").withPlayerTwoCards("2D 4D 6D 8D TD").andPlayerOneWins(true)
		.withTestName("Full House over flush to ten").build());

	return testSet;
    }
}
