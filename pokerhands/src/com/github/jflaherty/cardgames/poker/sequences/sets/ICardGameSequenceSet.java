package com.github.jflaherty.cardgames.poker.sequences.sets;

import com.github.jflaherty.cardgames.poker.sequences.ISequence;

public interface ICardGameSequenceSet extends Comparable<ICardGameSequenceSet> {
    public ISequence getPrimary();

    public ISequence getAuxillary();

    public int compareTo(ICardGameSequenceSet other);
}