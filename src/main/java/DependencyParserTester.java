import java.io.IOException;
import java.util.List;

/**
 * The DependencyParserTester class is used to test the functionality of the DependencyParser class
 */

public class DependencyParserTester {

    /**
     * The main method here is used to execute the tests
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        DependencyParserTester tester = new DependencyParserTester();
        tester.testParse();
    }

    /**
     * Test the parse method of the DependencyParser class and validates the output
     */

    public void testParse() {
        DependencyParser parser = new DependencyParser();
        try {

            // Parse the given dependencies from the test file
            List<Dependency> dependencies = parser.parse("test.txt");

            // Validate the parsed dependencies
            validateDependencies(dependencies);
            System.out.println("All tests passed.");
        } catch (IOException e) {
            e.printStackTrace();
            throw new AssertionError("Parsing failed due to IOException");
        }
    }

    /**
     * Validates the list of parsed dependencies
     *
     * @param dependencies the list of dependncies to validate
     */

    private void validateDependencies(List<Dependency> dependencies) {
        if (dependencies == null) {
            throw new AssertionError("Dependencies list is null");
        }
        if (dependencies.size() != 2) {
            throw new AssertionError("Expected 2 dependencies, but got " + dependencies.size());
        }

        // Checking first dependency
        Dependency dep1 = dependencies.get(0);
        validateDependency(dep1, "org.projectlombok", "Lombok", "1.5");

        // Checking the second dependency
        Dependency dep2 = dependencies.get(1);
        validateDependency(dep2, "com.google.api-client", "google-api-client", "1.30.9");
    }

    /**
     * Validates a single dependency object against their given expected values
     *
     * @param dependency the dependency object to validate
     * @param expectedGroupId the expected groupId
     * @param expectedArtifactId the expected artifactId
     * @param expectedVersion the expected version
     */
    private void validateDependency(Dependency dependency, String expectedGroupId, String expectedArtifactId, String expectedVersion) {
        if (!expectedGroupId.equals(dependency.getGroupId())) {
            throw new AssertionError("Expected groupId '" + expectedGroupId + "', but got " + dependency.getGroupId());
        }
        if (!expectedArtifactId.equals(dependency.getArtifactId())) {
            throw new AssertionError("Expected artifactId '" + expectedArtifactId + "', but got " + dependency.getArtifactId());
        }
        if (!expectedVersion.equals(dependency.getVersion())) {
            throw new AssertionError("Expected version '" + expectedVersion + "', but got " + dependency.getVersion());
        }
    }
}
