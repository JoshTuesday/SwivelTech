import java.util.List;
import java.io.IOException;

/**
 * The DependencyParserTester class is used to test the functionality of the DependencyParser class
 */
public class DependencyParserTester {
    public static void main(String[] args) {
        DependencyParser parser = new DependencyParser();
        try {
            // Ensure the correct path to test.txt
            List<Dependency> dependencies = parser.parse("src/main/resources/test.txt");

            // Create XMLGenerator instance
            XMLGenerator generator = new XMLGenerator();

            // Generate the XML file
            generator.generate(dependencies, "output.xml");

            // Print success message
            System.out.println("XML file generated successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            throw new AssertionError("Parsing or generation failed due to IOException");
        }
    }
}
