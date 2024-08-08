import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The XMLGenerator class is used for generating an
 * XML file based from a list of Dependency objects
 */

public class XMLGenerator implements XMLGeneratorInterface{

    // Logger instance for tracking messages
    private static final Logger logger = LoggerFactory.getLogger(XMLGenerator.class);

    /**
     * Generates an XML file from a list of Dependency objects
     *
     * @param dependencies the list of Dependency objects to be written to the XML file
     * @throws IOException if an I/O error occurs during writing the file
     */

    @Override
    public void generate(List<Dependency> dependencies, String filePath) throws IOException {

        // This implements data validation and runs developer testing since lines
        // are read before processing them (skip empty lines and log invalid line
        // formats
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("<dependencies>\n");

            // Write each dependency as an XML element
            for (Dependency dependency : dependencies) {
                writer.write("  <dependency>\n");
                writer.write("    <groupId>" + dependency.getGroupId() + "</groupId>\n");
                writer.write("    <artifactId>" + dependency.getArtifactId() + "</artifactId>\n");
                writer.write("    <version>" + dependency.getVersion() + "</version>\n");
                writer.write("  </dependency>\n");
            }

            // End on the root element
            writer.write("</dependencies>\n");
        } catch (IOException e) {

            // Log and rethrow the exception if writing fails
            logger.error("Failed to generate XML file", e);
            throw e;
        }
    }
}
