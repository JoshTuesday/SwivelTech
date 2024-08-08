import java.io.IOException;
import java.util.List;

public interface DependencyParserInterface {
    List<Dependency> parse(String filePath) throws IOException;
}
