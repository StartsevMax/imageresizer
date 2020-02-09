import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class ImageResizer {

    public static int newWidth = 300;

    public static void main(String[] args) {
        String srcFolder = "./src/images/original";
        String dstFolder = "./src/images/resized";

        File srcFolderFile = new File(srcFolder);

        File[] files = srcFolderFile.listFiles();

        try {
            assert files != null;
            for (File file : files){
                BufferedImage image = ImageIO.read(file);

                if (image == null) continue;


                int newHeight = (int) Math.round(
                        image.getHeight() / (image.getWidth() / (double) newWidth));

                BufferedImage newImage =
                        new BufferedImage(newWidth,newHeight,BufferedImage.TYPE_INT_RGB);

                int widthStep = image.getWidth() / newWidth;
                int heightStep = image.getHeight() / newHeight;

                for (int x = 0; x < newWidth; x++){
                    for (int y = 0; y < newHeight; y++){
                        int rgb = image.getRGB(x * widthStep, y * heightStep);
                        newImage.setRGB(x, y, rgb);
                    }
                }
                File newFile = new File(dstFolder + "/" + file.getName());
                ImageIO.write(newImage,"jpg", newFile);
            }


        }
        catch (Exception ex) {
            System.out.println("Error resizing the image.");
            ex.printStackTrace();
        }
    }

}