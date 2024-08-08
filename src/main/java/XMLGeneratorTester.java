import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.IOException;

/**
 * The XMLGeneratorTester class is used to test the functionality of the XMLGenerator class
 */

public class XMLGeneratorTester {

    /**
     * The main method to execute the test
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        XMLGeneratorTester tester = new XMLGeneratorTester();
        tester.testGenerate();
    }

    /**
     * Tests the generate method of the XMLGenerator class
     */

    public void testGenerate() {

        // Create a list of Dependency objects to be written to the XML file
        List<Dependency> dependencies = new ArrayList<>();
        dependencies.add(new Dependency("org.projectlombok", "Lombok", "1.5"));
        dependencies.add(new Dependency("com.google.api-client", "google-api-client", "1.30.9"));

        // Generate the XML file with the given dependencies
        XMLGenerator generator = new XMLGenerator();
        try {
            generator.generate(dependencies, "output.xml");
            File outputFile = new File("output.xml");
            if (!outputFile.exists()) {
                throw new AssertionError("Output file does not exist");
            }

            // Prints a success message if all tests passed
            System.out.println("All tests passed");
        } catch (IOException e) {
            e.printStackTrace();
            throw new AssertionError("Generation failed due to IOException");
        }
    }
}
