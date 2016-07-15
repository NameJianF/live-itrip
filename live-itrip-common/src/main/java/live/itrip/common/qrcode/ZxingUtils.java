package live.itrip.common.qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Hashtable;
import java.io.File;

/**
 * Created by Feng on 2015/12/31.
 * <p>
 * zxing 二维码生成工具
 */
public class ZxingUtils {

    public static BufferedImage encode(String content, int width, int height) throws IOException, WriterException {
        Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
        // 指定纠错等级,纠错级别（L 7%、M 15%、Q 25%、H 30%）
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        // 内容所使用字符集编码
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        hints.put(EncodeHintType.MARGIN, 1);//设置二维码边的空度，非负数
        BitMatrix bitMatrix = null;
        bitMatrix = new MultiFormatWriter().encode(content,//要编码的内容
                //编码类型，目前zxing支持：Aztec 2D,CODABAR 1D format,Code 39 1D,Code 93 1D ,Code 128 1D,
                //Data Matrix 2D , EAN-8 1D,EAN-13 1D,ITF (Interleaved Two of Five) 1D,
                //MaxiCode 2D barcode,PDF417,QR Code 2D,RSS 14,RSS EXPANDED,UPC-A 1D,UPC-E 1D,UPC/EAN extension,UPC_EAN_EXTENSION
                BarcodeFormat.QR_CODE,
                width, //条形码的宽度
                height, //条形码的高度
                hints);//生成条形码时的一些配置,此项可选

        // 生成二维码
        MatrixToImageWriter matrixToImageWriter = new MatrixToImageWriter();
        BufferedImage image = matrixToImageWriter.toBufferedImage(bitMatrix);
        return image;
    }

    public static BufferedImage encodeWithLogo(String content, int width, int height, String logoPath) throws IOException, WriterException {
//        String contents = "ZXing 二维码内容1234!"; // 二维码内容
//        int width = 500; // 二维码图片宽度 300
//        int height = 500; // 二维码图片高度300

        Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
        // 指定纠错等级,纠错级别（L 7%、M 15%、Q 25%、H 30%）
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        // 内容所使用字符集编码
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
//      hints.put(EncodeHintType.MAX_SIZE, 350);//设置图片的最大值
//      hints.put(EncodeHintType.MIN_SIZE, 100);//设置图片的最小值
        hints.put(EncodeHintType.MARGIN, 1);//设置二维码边的空度，非负数

        BitMatrix bitMatrix = new MultiFormatWriter().encode(content,//要编码的内容
                //编码类型，目前zxing支持：Aztec 2D,CODABAR 1D format,Code 39 1D,Code 93 1D ,Code 128 1D,
                //Data Matrix 2D , EAN-8 1D,EAN-13 1D,ITF (Interleaved Two of Five) 1D,
                //MaxiCode 2D barcode,PDF417,QR Code 2D,RSS 14,RSS EXPANDED,UPC-A 1D,UPC-E 1D,UPC/EAN extension,UPC_EAN_EXTENSION
                BarcodeFormat.QR_CODE,
                width, //条形码的宽度
                height, //条形码的高度
                hints);//生成条形码时的一些配置,此项可选

        // 生成二维码
        MatrixToImageWriter matrixToImageWriter = new MatrixToImageWriter();
        return matrixToImageWriter.toBufferedImage(bitMatrix, logoPath);
    }

    /**
     * 二维码的生成需要借助MatrixToImageWriter类，该类是由Google提供的，可以将该类直接拷贝到源码中使用，当然你也可以自己写个
     * 生产条形码的基类
     */
    private static class MatrixToImageWriter {
        private static final int BLACK = 0xFF000000;//用于设置图案的颜色
        private static final int WHITE = 0xFFFFFFFF; //用于背景色

        private MatrixToImageWriter() {
        }

        /**
         * 二维码，无logo
         *
         * @param matrix
         * @return
         */
        public BufferedImage toBufferedImage(BitMatrix matrix) {
            int width = matrix.getWidth();
            int height = matrix.getHeight();
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    image.setRGB(x, y, (matrix.get(x, y) ? BLACK : WHITE));
                }
            }
            return image;
        }

        /**
         * @param matrix   二维码图片
         * @param logoPath logo 图片地址
         * @return BufferedImage  带图片的二维码
         * @throws IOException
         */
        public BufferedImage toBufferedImage(BitMatrix matrix, String logoPath) throws IOException {
            BufferedImage image = toBufferedImage(matrix);
            //设置logo图标
            LogoConfig logoConfig = new LogoConfig();
            image = logoConfig.LogoMatrix(image, logoPath);
            return image;
        }

        /**
         * @param matrix   二维码图片
         * @param format   图片格式  png/jpg/gif
         * @param logoPath logo 图片地址
         * @param stream
         * @return
         * @throws IOException
         */
        public void writeToStream(BitMatrix matrix, String format, String logoPath, OutputStream stream) throws IOException {
            BufferedImage image = toBufferedImage(matrix);
            //设置logo图标
            LogoConfig logoConfig = new LogoConfig();
            image = logoConfig.LogoMatrix(image, logoPath);

            if (!ImageIO.write(image, format, stream)) {
                throw new IOException("Could not write an image of format " + format);
            }
        }
    }

    /**
     * 二维码 添加 logo图标 处理的方法,
     * 模仿微信自动生成二维码效果，有圆角边框，logo和二维码间有空白区，logo带有灰色边框
     */
    private static class LogoConfig {
        /**
         * 设置 logo
         *
         * @param matrixImage 源二维码图片
         * @param logoPath    logo 图片地址
         * @return 返回带有logo的二维码图片
         * @throws IOException
         */
        public BufferedImage LogoMatrix(BufferedImage matrixImage, String logoPath) throws IOException {
            /**
             * 读取二维码图片，并构建绘图对象
             */
            Graphics2D g2 = matrixImage.createGraphics();

            int matrixWidth = matrixImage.getWidth();
            int matrixHeigh = matrixImage.getHeight();

            /**
             * 读取Logo图片
             */
            BufferedImage logo = ImageIO.read(new File(logoPath));

            //开始绘制图片
            g2.drawImage(logo, matrixWidth / 5 * 2, matrixHeigh / 5 * 2, matrixWidth / 5, matrixHeigh / 5, null);//绘制
            BasicStroke stroke = new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
            g2.setStroke(stroke);// 设置笔画对象
            //指定弧度的圆角矩形
            RoundRectangle2D.Float round = new RoundRectangle2D.Float(matrixWidth / 5 * 2, matrixHeigh / 5 * 2, matrixWidth / 5, matrixHeigh / 5, 20, 20);
            g2.setColor(Color.white);
            g2.draw(round);// 绘制圆弧矩形

            //设置logo 有一道灰色边框
            BasicStroke stroke2 = new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
            g2.setStroke(stroke2);// 设置笔画对象
            RoundRectangle2D.Float round2 = new RoundRectangle2D.Float(matrixWidth / 5 * 2 + 2, matrixHeigh / 5 * 2 + 2, matrixWidth / 5 - 4, matrixHeigh / 5 - 4, 20, 20);
            g2.setColor(new Color(128, 128, 128));
            g2.draw(round2);// 绘制圆弧矩形

            g2.dispose();
            matrixImage.flush();
            return matrixImage;
        }

    }
}
