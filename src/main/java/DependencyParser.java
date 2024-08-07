import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;



/**
 * The DependencyParser class reads a text file and parses it into a list of
 * defined Dependency objects
 */
public class DependencyParser {

    // Logger instance for tracking messages
    private static final Logger logger = LoggerFactory.getLogger(DependencyParser.class);

    /**
     * Parses the specified file and returns a list of Dependency objects
     *
     * @param filePath the path to the text file to be parsed
     * @return a list of Dependency objects parsed from the file
     * @throws IOException if an I/O error occurs reading from the file
     */

    public List<Dependency> parse(String filePath) throws IOException {
        List<Dependency> dependencies = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            Dependency dependency = null;

            // Reads through each line within the file
            while ((line = reader.readLine()) != null) {
                dependency = processLine(line, dependency);
                if (dependency != null && isCompleteDependency(dependency)) {
                    dependencies.add(dependency);
                    logger.info("Added dependency: {}", dependency);
                    dependency = null;
                }
            }
        } catch (IOException e) {
            logger.error("Error reading file: {}", filePath, e);
            throw e;
        }
        return dependencies;
    }

    /**
     * Processes a line from the text file and updates the Dependency object accordingly
     *
     * @param line the line to process
     * @param dependency the current Dependency object being built
     * @return the newly updated Dependency object
     */

    private Dependency processLine(String line, Dependency dependency) {
        if (line.startsWith("1.groupId")) {

            // Initializes a new Dependency object and set its groupId
            dependency = new Dependency();
            dependency.setGroupId(line.split("=")[1].trim());
        } else if (line.startsWith("1.artifactId")) {

            // Set the artifactId of the current Dependency object
            if (dependency != null) {
                dependency.setArtifactId(line.split("=")[1].trim());
            }
        } else if (line.startsWith("1.version")) {
            // Sets the version of the current Dependency object
            if (dependency != null) {
                dependency.setVersion(line.split("=")[1].trim());
            }
        }
        return dependency;
    }

    /**
     * Checks if the Dependency object that's given has all required fields
     *
     * @param dependency dependency the given Dependency object to check
     * @return true if the Dependency object is complete, otherwise return false
     */

    private boolean isCompleteDependency(Dependency dependency) {
        return dependency.getGroupId() != null && dependency.getArtifactId() != null && dependency.getVersion() != null;
    }
}
