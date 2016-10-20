package live.itrip.admin.model;

import java.io.Serializable;

public class AdminOperation implements Serializable {
    private Integer id;

    private String opName;

    private String opText;

    private Long createTime;

    private Long updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpName() {
        return opName;
    }

    public void setOpName(String opName) {
        this.opName = opName == null ? null : opName.trim();
    }

    public String getOpText() {
        return opText;
    }

    public void setOpText(String opText) {
        this.opText = opText == null ? null : opText.trim();
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
}