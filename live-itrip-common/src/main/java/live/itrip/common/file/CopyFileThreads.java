package live.itrip.common.file;

import live.itrip.common.Logger;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 * Created by Feng on 2016/8/25.
 * <p>
 * 多线程拷贝文件
 */
public final class CopyFileThreads extends Thread {
    /**
     * 源文件路径
     */
    private String sourceFilePath;
    /**
     * 目标文件路径
     */
    private String destFilePath;
    /**
     * 起始位置
     */
    private Long startIndex;
    /**
     * 长度
     */
    private Long length;

    public CopyFileThreads(String sourceFilePath, String destFilePath, Long startIndex, Long length) {
        this.sourceFilePath = sourceFilePath;
        this.destFilePath = destFilePath;
        this.startIndex = startIndex;
        this.length = length;
    }

    public void run() {
        try {
            RandomAccessFile inputFile = new RandomAccessFile(this.sourceFilePath, "r");
            RandomAccessFile outputFile = new RandomAccessFile(this.destFilePath, "rw");

            inputFile.seek(this.startIndex);
            outputFile.seek(this.startIndex);

            FileChannel inChannel = inputFile.getChannel();
            FileChannel outChannel = outputFile.getChannel();

            //锁住需要操作的区域
            FileLock lock = outChannel.lock(this.startIndex, this.length, false);

            outChannel.transferFrom(inChannel, this.startIndex, this.length);

            //释放锁
            lock.release();
            outputFile.close();
            inputFile.close();
        } catch (Exception ex) {
            Logger.error(ex.getMessage(), ex);
        }
    }

//    public static void main(String[] args) {
//        //要复制的源文件路径
//        String srcPath = "C:\\Users\\CM\\Desktop\\a.avi";
//        //
//        String destPath = "C:\\Users\\CM\\Desktop\\a_复件.avi";
//
//        // 获得源文件长度
//        File f = new File(srcPath);
//        long len = f.length();
//
//        int count = 3;// 需要的线程数
//        Long oneNum = (len / count);
//
//        for (int i = 0; i < count - 1; i++) {
//            Long start = oneNum * i;
//            CopyFileThreads ct = new CopyFileThreads(srcPath, destPath, start, oneNum);
//            ct.start();
//        }
//
//        CopyFileThreads ct = new CopyFileThreads(srcPath, destPath, oneNum * (count - 1), len - oneNum * (count - 1));
//        ct.start();
//    }
}
