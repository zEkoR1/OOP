package Lab_2.services.files;


import Lab_2.services.files.abstracts.FileAttributes;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageFileAttributes extends FileAttributes {
    int imageWidth;
    int imageHeight;

    public ImageFileAttributes(File file) {
        super(file);
        calculateImageDimensions(file);
    }

    private void calculateImageDimensions(File file) {
        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            if (bufferedImage != null) {
                this.imageWidth = bufferedImage.getWidth();
                this.imageHeight = bufferedImage.getHeight();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean isImage(File file) {
        String extension = getFileExtension(file).toLowerCase();
        return extension.equals("png") || extension.equals("jpg") || extension.equals("jpeg");
    }

    public static String getFileExtension(File file) {
        String name = file.getName();
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return "";
        }
        return name.substring(lastIndexOf + 1);
    }

    public int getImageWidth() {
        return imageWidth;
    }

    public int getImageHeight() {
        return imageHeight;
    }

}
