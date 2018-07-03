package bookmatcher.data;
/**
 * This interface represents a data source for user information.
 */
public interface UserRepository {
    public String getUserJSONBlob();
    public String getUsername();
}