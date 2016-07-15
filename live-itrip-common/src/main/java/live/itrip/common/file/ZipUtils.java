/*
 * Created on 2011-07-29
 *
 */
package live.itrip.common.file;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.zip.ZipFile;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

/**
 * @author Feng
 * @version 1.0 文件压缩解压工具类<br/>
 *          压缩指定的目录以及解压指定的压缩文件(仅限ZIP格式)<br/>
 *          压缩使用apache工具包中的ZIPOutputStream <br/>
 *          解压时使用的时jre自带的ZIPInputStream<br/>
 *          java解压时压缩的编码不能为gbk，只能为utf-8
 * 
 */
public class ZipUtils {
	/**
	 * 将指定的压缩文件解压到指定的目标目录下. 如果指定的目标目录不存在或其父路径不存在, 将会自动创建.
	 * 
	 * @param zip
	 *            将会解压的压缩文件
	 * @param dest
	 *            解压操作的目标目录
	 */
	public static void unzip(File zip, File dest) throws Exception {
		doUnzip(new ZipFile(zip), dest);
	}

	/**
	 * 说明：进行解压操作
	 * 
	 * @param zin
	 * @param dest
	 * @throws IOException
	 */
	private static void doUnzip(ZipFile zipFile, File dest) throws Exception {
		Enumeration<? extends ZipEntry> entries = zipFile.entries();
		while (entries.hasMoreElements()) {
			ZipEntry e = (ZipEntry) entries.nextElement();
			File file = new File(dest, e.getName());
			if (e.isDirectory()) {
				// LoggerUtils.debug(TAG," creating: {" + file + File.separator
				// + "}");
				FileUtils.forceMkdir(file);
			} else {
				// LoggerUtils.debug(TAG,"inflating: {" + file + "}");
				flushZip(zipFile.getInputStream(e),
						FileUtils.openOutputStream(file));
			}
		}
	}

	/**
	 * 说明：从压缩包中提前文件
	 * 
	 * @param zin
	 * @param out
	 * @throws IOException
	 */
	private static void flushZip(InputStream zin, OutputStream out)
			throws Exception {
		try {
			IOUtils.copy(zin, out);
		} finally {
			IOUtils.closeQuietly(out);
		}
	}

	public static void zip(ArrayList<File> files, File zip) throws Exception {
		ZipOutputStream zout = null;
		try {
			zout = new ZipOutputStream(FileUtils.openOutputStream(zip));
			for (File file : files) {
				String entryName = file.getName();
				zout.putNextEntry(new ZipEntry(entryName));
				fillZip(FileUtils.openInputStream(file), zout);
			}
		} finally {
			if (zout != null) {
				IOUtils.closeQuietly(zout);
			}
		}

	}

	/**
	 * 将指定目录下的所有文件压缩并生成指定路径的压缩文件. 如果压缩文件的路径或父路径不存在, 将会自动创建.
	 * 
	 * @param src
	 *            将要进行压缩的目录
	 * @param zip
	 *            最终生成的压缩文件的路径
	 */
	public static void zip(File src, File zip) throws Exception {
		zip(src, FileUtils.openOutputStream(zip));
	}

	/**
	 * 将指定目录下的所有文件压缩并将流写入指定的输出流中.
	 * 
	 * @param src
	 *            将要进行压缩的目录
	 * @param out
	 *            用于接收压缩产生的文件流的输出流
	 */
	public static void zip(File src, OutputStream out) throws Exception {
		zip(src, new ZipOutputStream(out));
	}

	/**
	 * 将指定目录下的所有文件压缩并将流写入指定的ZIP输出流中.
	 * 
	 * @param src
	 *            将要进行压缩的目录
	 * @param zout
	 *            用于接收压缩产生的文件流的ZIP输出流
	 */
	public static void zip(File src, ZipOutputStream zout) throws Exception {
		try {
			doZip(src, zout, "");
		} finally {
			IOUtils.closeQuietly(zout);
		}
	}

	/**
	 * @param src
	 * @param zout
	 * @param ns
	 */
	private static void doZip(File src, ZipOutputStream zout, String ns)
			throws Exception {
		if (src.isFile()) {
			String entryName = ns + src.getName();
			zout.putNextEntry(new ZipEntry(entryName));
			fillZip(FileUtils.openInputStream(src), zout);
		} else {
			File[] fs = src.listFiles();
			if (fs != null) {
				for (File file : fs) {
					String entryName = ns + file.getName();
					if (file.isDirectory()) {
						doZip(file, zout, entryName + "/");
					} else {
						zout.putNextEntry(new ZipEntry(entryName));
						fillZip(FileUtils.openInputStream(file), zout);
					}
				}
			}
		}

	}

	/**
	 * 说明：向压缩包中写入文件
	 * 
	 * @param in
	 * @param zout
	 * @throws IOException
	 */
	private static void fillZip(InputStream in, ZipOutputStream zout)
			throws Exception {
		try {
			IOUtils.copy(in, zout);
		} finally {
			IOUtils.closeQuietly(in);
		}
	}
}