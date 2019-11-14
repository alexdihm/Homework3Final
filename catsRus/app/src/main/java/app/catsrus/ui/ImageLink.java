package app.catsrus.ui;

import android.graphics.Bitmap;

public class ImageLink {
    public String url;
    public Bitmap image;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
