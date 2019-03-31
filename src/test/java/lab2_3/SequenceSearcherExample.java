package lab2_3;

import edu.iis.mto.search.SearchResult;
import edu.iis.mto.search.SearchResult.Builder;
import edu.iis.mto.search.SequenceSearcher;

public class SequenceSearcherExample implements SequenceSearcher {

    public int counterCalls = 0;

    @Override
    public SearchResult search(int key, int[] seq) {
        Builder builder = SearchResult.builder();
        builder.withPosition(key);
        builder.withFound(seq.length - counterCalls++ > 0);
        return builder.build();
    }

}
