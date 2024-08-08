/**
 * The Dependency class represents a Maven dependency
 * with groupId, artifactId and version attributes
 */

public class Dependency {

    // The groupId, artifactId and version of the dependency
    private String groupId;
    private String artifactId;
    private String version;

    /**
     *
     * @param groupId the groupId of the dependency
     * @param artifactId the artifactId of the dependency
     * @param version the version of the dependency
     */

    public Dependency(String groupId, String artifactId, String version) {
        this.groupId = groupId;
        this.artifactId = artifactId;
        this.version = version;
    }

    /**
     * Default Constructor
     */

    public Dependency() { }

    // Getters
    public String getGroupId() {
        return groupId;
    }
    public String getArtifactId() {
        return artifactId;
    }
    public String getVersion() {
        return version;
    }

    // Setters

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }
    public void setVersion(String version) {
        this.version = version;
    }
}
