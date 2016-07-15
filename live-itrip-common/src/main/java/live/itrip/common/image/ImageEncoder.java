package live.itrip.common.image;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * 图片格式转换工具类
 * 
 * @author Feng
 *
 */
public class ImageEncoder {

	private static ImageEncoder instance;

	private ImageEncoder() {
		instance = this;
	}

	public static ImageEncoder getInstance() {
		if (instance == null) {
			instance = new ImageEncoder();
		}
		return instance;
	}

	/**
	 * 缩小并转换格式
	 * 
	 * @param srcPath源路径
	 * @param destPath目标路径
	 * @param height目标高
	 * @param width
	 *            目标宽
	 * @param format
	 *            文件格式
	 * @return
	 */
	public boolean narrowAndFormateTransfer(String srcPath, String destPath,
			int height, int width, String format) {
		boolean flag = false;
		try {
			File file = new File(srcPath);
			File destFile = new File(destPath);
			if (!destFile.getParentFile().exists()) {
				destFile.getParentFile().mkdir();
			}
			BufferedImage src = ImageIO.read(file); // 读入文件
			Image image = src.getScaledInstance(width, height,
					Image.SCALE_DEFAULT);
			BufferedImage tag = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_RGB);
			Graphics g = tag.getGraphics();
			g.drawImage(image, 0, 0, null); // 绘制缩小后的图
			g.dispose();

			FileOutputStream fos = new FileOutputStream(destFile);

			flag = ImageIO.write(tag, format, fos);// 输出到文件流

			fos.close();
			fos = null;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return flag;
	}

	// 图片缩放。
	public BufferedImage reSize(BufferedImage srcBufImage, int width, int height) {
		BufferedImage bufImg = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		// 计算图片缩放比例
		float widthBo = (float) width / (float) srcBufImage.getWidth();
		float heightBo = (float) height / (float) srcBufImage.getHeight();
		AffineTransform transform = new AffineTransform();
		transform.setToScale(widthBo, heightBo);

		// 根据原始图片生成处理后的图片。
		// AffineTransformOp ato = new AffineTransformOp(transform, null);
		// ato.filter(srcBufImage, bufImg);
		Graphics2D g = (Graphics2D) bufImg.createGraphics();
		g.drawImage(srcBufImage, transform, null);
		g.dispose();
		return bufImg;
	}
}
