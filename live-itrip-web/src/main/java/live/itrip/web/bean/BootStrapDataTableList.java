package live.itrip.web.bean;

import java.util.List;

/**
 * Created by Feng on 2015/12/29.
 * <p/>
 * 用于bootstrap table 数据的返回
 */
public class BootStrapDataTableList<T> implements java.io.Serializable {

    private int iTotalDisplayRecords;//显示的行数,这个要和上面写的一样
    private int iTotalRecords;     //实际的行数
    private String sEcho = "";// // 记录操作的次数  每次加1

    private List<T> aaData;  // 要以JSON格式返回

    public int getiTotalDisplayRecords() {
        return iTotalDisplayRecords;
    }

    public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
        this.iTotalDisplayRecords = iTotalDisplayRecords;
    }

    public int getiTotalRecords() {
        return iTotalRecords;
    }

    public void setiTotalRecords(int iTotalRecords) {
        this.iTotalRecords = iTotalRecords;
    }

    public String getsEcho() {
        return sEcho;
    }

    public void setsEcho(String sEcho) {
        this.sEcho = sEcho;
    }

    public List<T> getAaData() {
        return aaData;
    }

    public void setAaData(List<T> aaData) {
        this.aaData = aaData;
    }
}
