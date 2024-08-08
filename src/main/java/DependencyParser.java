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
            Dependency dependency = new Dependency();

            // Reads through each line within the file
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split("=");
                if (parts[0].contains("groupId")) {
                    dependency.setGroupId(parts[1].replace(";", "").trim());
                } else if (parts[0].contains("artifactId")) {
                    dependency.setArtifactId(parts[1].replace(";", "").trim());
                } else if (parts[0].contains("version")) {
                    dependency.setVersion(parts[1].replace(";", "").trim());
                    dependencies.add(dependency);
                    dependency = new Dependency();
                }
            }
        } catch (IOException e) {
            logger.error("Error reading file: {}", filePath, e);
            throw e;
        }
        return dependencies;
    }
}
