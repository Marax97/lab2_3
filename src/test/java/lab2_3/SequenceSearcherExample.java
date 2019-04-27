package lab2_3;

import java.util.Stack;

import edu.iis.mto.search.SearchResult;
import edu.iis.mto.search.SearchResult.Builder;
import edu.iis.mto.search.SequenceSearcher;

public class SequenceSearcherExample implements SequenceSearcher {

    public static int counterCalls = 0;
    public static Stack<Boolean> valuesStack = new Stack<>();
    public static Stack<Integer> listOfKeys = new Stack<>();

    public static void fillStack(boolean[] values) {
        for (boolean value : values) {
            valuesStack.push(value);
        }
    }

    @Override
    public SearchResult search(int key, int[] seq) {
        Builder builder = SearchResult.builder();
        builder.withFound(valuesStack.pop());
        counterCalls++;
        listOfKeys.push(key);
        return builder.build();
    }

}
