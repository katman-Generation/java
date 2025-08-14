/**
 * A generic class representing a library asset.
 * Attributes: title, creator (can be author, director, etc.), and unique assetID.
 */
public class LibraryAsset {
    private String assetTitle;
    private String assetCreator;
    private String assetID;

    public LibraryAsset(String assetTitle, String assetCreator, String assetID) {
        this.assetTitle = assetTitle;
        this.assetCreator = assetCreator;
        this.assetID = assetID;
    }

    public String getAssetID() {
        return assetID;
    }

    @Override
    public String toString() {
        return "Asset Title: " + assetTitle + " | Creator: " + assetCreator + " | ID: " + assetID;
    }
}

