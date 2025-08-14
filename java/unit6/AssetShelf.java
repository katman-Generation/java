import java.util.*;


public class AssetShelf<T extends LibraryAsset> {
    private final Map<String, T> shelfMap;

    public AssetShelf() {
        shelfMap = new HashMap<>();
    }

    // Adds a new asset to the shelf
    public void insertAsset(T newAsset) {
        shelfMap.put(newAsset.getAssetID(), newAsset);
        System.out.println("Asset added to shelf.");
    }

    // Removes an asset from the shelf based on its ID
    public void deleteAsset(String assetCode) {
        if (shelfMap.containsKey(assetCode)) {
            shelfMap.remove(assetCode);
            System.out.println("Asset successfully removed.");
        } else {
            System.out.println("⚠ Error: No asset found with ID: " + assetCode);
        }
    }

    // Displays all assets currently in the shelf
    public void displayShelf() {
        if (shelfMap.isEmpty()) {
            System.out.println("Shelf is currently empty.");
        } else {
            for (T element : shelfMap.values()) {
                System.out.println(element);
            }
        }
    }
}

