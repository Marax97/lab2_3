package lab2_3;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.Stack;

import org.junit.Before;
import org.junit.Test;

import edu.iis.mto.similarity.SimilarityFinder;

public class SimilarityFinderTest {

    private SimilarityFinder similarityFinder;

    @Before
    public void setUp() {
        similarityFinder = new SimilarityFinder(new SequenceSearcherExample());
        SequenceSearcherExample.counterCalls = 0;
        SequenceSearcherExample.valuesStack = new Stack<>();
        SequenceSearcherExample.listOfKeys = new Stack<>();
    }

    @Test
    public void testTheSameSequencesSimilarity() {
        int[] seq = {1, 2, 3, 4, 5, 6};
        int[] seq2 = {1, 2, 3, 4, 5, 6};
        SequenceSearcherExample.fillStack(new boolean[] {true, true, true, true, true, true, true});
        double jackardSimilarity = similarityFinder.calculateJackardSimilarity(seq, seq2);
        assertThat(jackardSimilarity, is(equalTo(1.0)));
    }

    @Test
    public void testEmptySequencesSimilarity() {
        int[] seq = {};
        int[] seq2 = {};
        double jackardSimilarity = similarityFinder.calculateJackardSimilarity(seq, seq2);
        assertThat(jackardSimilarity, is(equalTo(1.0)));
    }

    @Test
    public void testMixedElementInSecondSequenceSimilarity() {
        int[] seq = {1, -2, 3, -4, 5, 6};
        int[] seq2 = {3, -2, 6, 1, 5, -4};
        SequenceSearcherExample.fillStack(new boolean[] {true, true, true, true, true, true, true});
        double jackardSimilarity = similarityFinder.calculateJackardSimilarity(seq, seq2);
        assertThat(jackardSimilarity, is(equalTo(1.0)));
    }

    @Test
    public void testNoIntersectionSequencesSimilarity() {
        int[] seq = {1};
        int[] seq2 = {5};
        SequenceSearcherExample.fillStack(new boolean[] {false});
        double jackardSimilarity = similarityFinder.calculateJackardSimilarity(seq, seq2);
        assertThat(jackardSimilarity, is(equalTo(0.0)));
    }

    @Test
    public void testNoIntersectionRandomSequencesSimilarity() {
        int[] seq = {1, 5, 7, 3, -3};
        int[] seq2 = {2, 14, 64, 6};
        SequenceSearcherExample.fillStack(new boolean[] {false, false, false, false, false});
        double jackardSimilarity = similarityFinder.calculateJackardSimilarity(seq, seq2);
        assertThat(jackardSimilarity, is(equalTo(0.0)));
    }

    @Test
    public void testRandomIntersectionSequencesSimilarity() {
        int[] seq = {1, 2, 3, 4, 5, 6};
        int[] seq2 = {2, 6, 0, 10, 8, 1};
        SequenceSearcherExample.fillStack(new boolean[] {true, true, false, false, false, true});
        double jackardSimilarity = similarityFinder.calculateJackardSimilarity(seq, seq2);

        assertThat(Math.floor(jackardSimilarity * 100) / 100, is(equalTo(0.33)));
    }

    @Test
    public void testValueOfCallsSearchMethodFromSequneceSearcher1() {
        int[] seq = {1, 2, 3, 4, 5, 6};
        int[] seq2 = {};
        SequenceSearcherExample.fillStack(new boolean[] {false, false, false, false, false, false});
        similarityFinder.calculateJackardSimilarity(seq, seq2);
        assertThat(SequenceSearcherExample.counterCalls, is(equalTo(seq.length)));
    }

    @Test
    public void testValueOfCallsSearchMethodFromSequneceSearcher2() {
        int[] seq = {1, 2, 3, 4, 5, 6};
        int[] seq2 = {1, 4, 435, 32, 13, 43, 656, 8, 97};
        SequenceSearcherExample.fillStack(new boolean[] {true, false, false, true, false, false});
        similarityFinder.calculateJackardSimilarity(seq, seq2);
        assertThat(SequenceSearcherExample.counterCalls, is(equalTo(seq.length)));
    }

    @Test
    public void testValueOfCallsSearchMethodFromSequneceSearcher3() {
        int[] seq = {1};
        int[] seq2 = {1, 4, 435, 32, 13, 43, 656, 8, 97};
        SequenceSearcherExample.fillStack(new boolean[] {true});
        similarityFinder.calculateJackardSimilarity(seq, seq2);
        assertThat(SequenceSearcherExample.counterCalls, is(equalTo(seq.length)));
    }

    @Test
    public void testValueOfCallsSearchMethodFromSequneceSearcher4() {
        int[] seq = {};
        int[] seq2 = {1, 4, 435, 32, 13, 43, 656, 8, 97};
        similarityFinder.calculateJackardSimilarity(seq, seq2);
        assertThat(SequenceSearcherExample.counterCalls, is(equalTo(seq.length)));
    }

    @Test
    public void testIfMethodSearchFromSequneceSearcherIsCalledWithRightArgs() {
        int[] seq = {1, 2, 3, 4, 5, 6};
        int[] seq2 = {1, 4, 435, 32, 13, 43, 656, 8, 97};
        SequenceSearcherExample.fillStack(new boolean[] {true, false, false, true, false, false});
        similarityFinder.calculateJackardSimilarity(seq, seq2);
        assertThat(SequenceSearcherExample.listOfKeys.toArray(), is(equalTo(seq)));
    }

    @Test
    public void testIfMethodSearchFromSequneceSearcherIsCalledWithRightArgs2() {
        int[] seq = {1, 4, 435, 32, 13, 43, 656, 8, 97};
        int[] seq2 = {};
        SequenceSearcherExample.fillStack(new boolean[] {false, false, false, false, false, false, false, false, false});
        similarityFinder.calculateJackardSimilarity(seq, seq2);
        assertThat(SequenceSearcherExample.listOfKeys.toArray(), is(equalTo(seq)));
    }
}
