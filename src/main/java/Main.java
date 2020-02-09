import java.io.File;

public class Main {

    public static int newWidth = 300;

    public static void main(String[] args) {
        String srcFolder = "./src/images/original";
        String dstFolder = "./src/images/resized";

        File srcFolderFile = new File(srcFolder);

        File[] files = srcFolderFile.listFiles();
        int processorsCount = Runtime.getRuntime().availableProcessors();
        System.out.println(processorsCount);
        int middle = files.length / 2;
        File[] files1 = new File[middle];
        System.arraycopy(files,0,files1,0,files1.length);
        ImageResizer ir1 = new ImageResizer(files1,newWidth,dstFolder);
        ir1.start();

        File[] files2 = new File[files.length - middle];
        System.arraycopy(files,middle,files2,0,files2.length);
        ImageResizer ir2 = new ImageResizer(files2,newWidth,dstFolder);
        ir2.start();
    }

}