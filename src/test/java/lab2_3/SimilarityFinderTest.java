package lab2_3;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import edu.iis.mto.similarity.SimilarityFinder;

public class SimilarityFinderTest {

    private SimilarityFinder similarityFinder;

    @Before
    public void setUp() {
        similarityFinder = new SimilarityFinder(new SequenceSearcherExample());
        SequenceSearcherExample.counterCalls = 0;
    }

    @Test
    public void testTheSameSequencesSimilarity() {
        int[] seq = {1, 2, 3, 4, 5, 6};
        int[] seq2 = {1, 2, 3, 4, 5, 6};
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
        double jackardSimilarity = similarityFinder.calculateJackardSimilarity(seq, seq2);
        assertThat(jackardSimilarity, is(equalTo(1.0)));
    }

    @Test
    public void testNoIntersectionSequencesSimilarity() {
        int[] seq = {1};
        int[] seq2 = {5};
        double jackardSimilarity = similarityFinder.calculateJackardSimilarity(seq, seq2);
        assertThat(jackardSimilarity, is(equalTo(0.0)));
    }

    @Test
    public void testNoIntersectionRandomSequencesSimilarity() {
        int[] seq = {1, 5, 7, 3, -3};
        int[] seq2 = {2, 14, 64, 6};
        double jackardSimilarity = similarityFinder.calculateJackardSimilarity(seq, seq2);
        assertThat(jackardSimilarity, is(equalTo(0.0)));
    }

    @Test
    public void testRandomIntersectionSequencesSimilarity() {
        int[] seq = {1, 2, 3, 4, 5, 6};
        int[] seq2 = {2, 6, 0, 10, 8, 1};
        double jackardSimilarity = similarityFinder.calculateJackardSimilarity(seq, seq2);

        assertThat(Math.floor(jackardSimilarity * 100) / 100, is(equalTo(0.33)));
    }

    @Test
    public void testValueOfCallsSearchMethodFromSequneceSearcher1() {
        int[] seq = {1, 2, 3, 4, 5, 6};
        int[] seq2 = {};
        similarityFinder.calculateJackardSimilarity(seq, seq2);
        assertThat(SequenceSearcherExample.counterCalls, is(equalTo(seq.length)));
    }

    @Test
    public void testValueOfCallsSearchMethodFromSequneceSearcher2() {
        int[] seq = {1, 2, 3, 4, 5, 6};
        int[] seq2 = {1, 4, 435, 32, 13, 43, 656, 8, 97};
        similarityFinder.calculateJackardSimilarity(seq, seq2);
        assertThat(SequenceSearcherExample.counterCalls, is(equalTo(seq.length)));
    }

    @Test
    public void testValueOfCallsSearchMethodFromSequneceSearcher3() {
        int[] seq = {1};
        int[] seq2 = {1, 4, 435, 32, 13, 43, 656, 8, 97};
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
}
