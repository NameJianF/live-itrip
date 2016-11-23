package live.itrip.admin.common;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.*;

/**
 * Created by Feng on 2016/11/23.
 */
public class HtmlUtils {

    /**
     * 创建html静态文件
     *
     * @param servletContext
     * @param request
     * @param response
     * @param jspPath
     * @param htmlPath
     * @return
     * @throws IOException
     * @throws ServletException
     */
    public static String createHtmlFile(ServletContext servletContext
            , HttpServletRequest request
            , HttpServletResponse response
            , String jspPath
            , String htmlPath) throws IOException, ServletException {

        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(jspPath);

        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        final ServletOutputStream stream = new ServletOutputStream() {
            public void write(byte[] data, int offset, int length) {
                byteArrayOutputStream.write(data, offset, length);
            }

            public void write(int b) throws IOException {
                byteArrayOutputStream.write(b);
            }
        };

        final PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(byteArrayOutputStream, "utf-8"));

        HttpServletResponse rep = new HttpServletResponseWrapper(response) {
            public ServletOutputStream getOutputStream() {
                return stream;
            }

            public PrintWriter getWriter() {
                return printWriter;
            }
        };

        requestDispatcher.include(request, rep);
        printWriter.flush();

        File htmlFile = new File(htmlPath);
        if (!htmlFile.getParentFile().exists()) {
            htmlFile.getParentFile().mkdirs();
        }
        if (!htmlFile.exists()) {
            htmlFile.createNewFile();
        }
        FileOutputStream fos = new FileOutputStream(htmlPath); // 把jsp 输出的内容写到xxx.htm
        byteArrayOutputStream.writeTo(fos);
        fos.close();
        return jspPath;
    }
}
