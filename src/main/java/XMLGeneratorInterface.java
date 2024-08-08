import java.io.IOException;
import java.util.List;

public interface XMLGeneratorInterface {
    void generate(List<Dependency> dependencies, String filePath) throws IOException;
}
