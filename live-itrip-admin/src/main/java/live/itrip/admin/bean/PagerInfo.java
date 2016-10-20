package live.itrip.admin.bean;

import com.alibaba.fastjson.JSONArray;
import com.sun.tools.javac.util.List;

/**
 * Created by Feng on 2015/12/30.
 */
public class PagerInfo {

    private String sEcho = "";// // 记录操作的次数  每次加1
    private int iDisplayStart = 0;// 起始
    private int iDisplayLength = 0;// size

    private int draw; // 数据查询次数
    private int start = 0; // 查询数据起始索引
    private int length = 100; // 查询数据长度
    private JSONArray columns; // 表格列信息
    private JSONArray order; // 表格排序信息
    private String search = ""; // 表格过滤条件（search）

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public JSONArray getColumns() {
        return columns;
    }

    public void setColumns(JSONArray columns) {
        this.columns = columns;
    }

    public JSONArray getOrder() {
        return order;
    }

    public void setOrder(JSONArray order) {
        this.order = order;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getsEcho() {
        return sEcho;
    }

    public void setsEcho(String sEcho) {
        this.sEcho = sEcho;
    }

    public int getiDisplayStart() {
        return iDisplayStart;
    }

    public void setiDisplayStart(int iDisplayStart) {
        this.iDisplayStart = iDisplayStart;
    }

    public int getiDisplayLength() {
        return iDisplayLength;
    }

    public void setiDisplayLength(int iDisplayLength) {
        this.iDisplayLength = iDisplayLength;
    }
}
