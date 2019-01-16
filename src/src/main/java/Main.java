import analyzer.Analyzers;
import output.Outputs;

public class Main {

    public static void main(String[] args) {
        Analyzers analyzer = new Analyzers("NetCracker lab's");
        Outputs output = new Outputs(analyzer.analyze(Analyzers.DEFAULT_ELEMENT_COUNT_FOR_TESTS, false, 10));
        output.print();
        output.createExcel("results_table.xlsx");
    }
}
