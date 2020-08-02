package gatos;

public class Gatos {
    private String id;
    String url;
    String apikey = "6020f216-328e-4b1d-b64a-7f65aa166a18";
    String image;
    int width;
    int height;

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getApikey() {
        return apikey;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getImage() {
        return image;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
