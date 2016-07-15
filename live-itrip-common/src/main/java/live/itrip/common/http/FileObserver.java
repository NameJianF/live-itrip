package live.itrip.common.http;

/**
 * 文件上传/下载进度
 *
 * @author Feng
 */
public interface FileObserver {

    /**
     * 已上传/下载字节数
     *
     * @param count
     */
    void transferred(long count);
}
