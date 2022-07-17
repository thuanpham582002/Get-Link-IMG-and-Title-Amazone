package Model;

import java.util.List;

public class Product {
    private String title;
    private List<String> images;
    private String id;

    public Product(String id, String title, List<String> images) {
        this.title = title;
        this.images = images;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
