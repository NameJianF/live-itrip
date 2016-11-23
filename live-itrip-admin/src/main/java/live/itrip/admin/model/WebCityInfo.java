package live.itrip.admin.model;

public class WebCityInfo {
    private Integer id;

    private String cityName;

    private String cityArea;

    private String cityTitle;

    private String isDelete;

    private Long createTime;

    private Long updateTime;

    private String cityContent;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }

    public String getCityArea() {
        return cityArea;
    }

    public void setCityArea(String cityArea) {
        this.cityArea = cityArea == null ? null : cityArea.trim();
    }

    public String getCityTitle() {
        return cityTitle;
    }

    public void setCityTitle(String cityTitle) {
        this.cityTitle = cityTitle == null ? null : cityTitle.trim();
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete == null ? null : isDelete.trim();
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public String getCityContent() {
        return cityContent;
    }

    public void setCityContent(String cityContent) {
        this.cityContent = cityContent == null ? null : cityContent.trim();
    }
}