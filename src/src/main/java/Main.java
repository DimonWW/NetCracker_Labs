import analyzer.Analyzers;
import output.Outputs;

public class Main {

    public static void main(String[] args) {
        Analyzers analyzer = new Analyzers("NetCracker lab's");
        Outputs output = new Outputs(analyzer.analyze(1_000, false, 10));
        output.print();
    }
}
