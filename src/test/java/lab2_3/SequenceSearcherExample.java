package lab2_3;

import java.util.ArrayList;
import java.util.List;

import edu.iis.mto.search.SearchResult;
import edu.iis.mto.search.SearchResult.Builder;
import edu.iis.mto.search.SequenceSearcher;

public class SequenceSearcherExample implements SequenceSearcher {

    public static int counterCalls = 0;
    public static List<Integer> listOfKeys = new ArrayList<>();

    @Override
    public SearchResult search(int key, int[] seq) {
        Builder builder = SearchResult.builder();
        boolean isFaound = false;

        for (int i = 0; i < seq.length; i++) {
            if (seq[i] == key) {
                builder.withPosition(i);
                builder.withFound(true);
                isFaound = true;
            }
        }

        builder.withFound(isFaound);
        counterCalls++;
        listOfKeys.add(key);

        return builder.build();
    }

}
