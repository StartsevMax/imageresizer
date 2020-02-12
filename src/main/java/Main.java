import java.io.File;

public class Main {

    public static int newWidth = 300;

    public static void main(String[] args) {

        String srcFolder = "./src/images/original";
        String dstFolder = "./src/images/resized";

        File srcFolderFile = new File(srcFolder);
        File[] files = srcFolderFile.listFiles();

        int processorsCount = Runtime.getRuntime().availableProcessors();

        //primalFilesAmount shows the amount of files per thread
        //primalFilesAmount кол-во файлов в каждом потоке
        int primalFilesAmount = files.length / 8;

        //threadsWithAdditionalFile shows amount of threads to be executed with extra file in it (primal + 1)
        //threadsWithAdditionalFile кол-во потоков которое будет запущено с дополнительным файлом (primal + 1)
        int threadsWithAdditionalFile = files.length % 8;

        for (int i = 0; i < processorsCount + 1; i++){

            ImageResizer ir;

            if (i < threadsWithAdditionalFile) {

                File[] filesForThread = new File[primalFilesAmount + 1];
                int index = i * (primalFilesAmount + 1);
                System.arraycopy(files,index,filesForThread,0,filesForThread.length);
                ir = new ImageResizer(filesForThread,newWidth,dstFolder);

            } else {

                File[] filesForThread = new File[primalFilesAmount];
                int index = threadsWithAdditionalFile * (primalFilesAmount + 1)// = 8
                        + (i - threadsWithAdditionalFile - 1) * primalFilesAmount;// + (i - 4 - 1) * 1
                System.arraycopy(files, index, filesForThread, 0, filesForThread.length);
                ir = new ImageResizer(filesForThread,newWidth,dstFolder);

            }

            new Thread(ir).start();

        }
    }
}