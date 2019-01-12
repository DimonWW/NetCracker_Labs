import analyzer.Analyzers;
import output.Outputs;
import output.ResultHolder;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        Analyzers analyzer = new Analyzers();
        List<ResultHolder> result = analyzer.analyze(1_000, false, 10);
        Outputs.sort(result, Outputs.SORT_BY_TIME);
        Outputs.print(result);
    }
}
