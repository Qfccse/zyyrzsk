package cn.edu.tongji.backend.test.pojo;

import lombok.Data;

@Data
public class ReportTemplate {
    private int rtId;
    private int lId;
    private int tId;
    private String icon;
    private String label;
    private String title;
    private String placeholder;
    private String required;
    private String type;
    private String typeName;
    private String content;
    @Override
    public String toString() {
        return "ReportTemplate{" +
                "rt_id=" + rtId +
                ", l_id=" + lId +
                ", t_id=" + tId +
                ", icon='" + icon + '\'' +
                ", label='" + label + '\'' +
                ", title='" + title + '\'' +
                ", placeholder='" + placeholder + '\'' +
                ", required='" + required + '\'' +
                ", type='" + type + '\'' +
                ", typeName='" + typeName + '\'' +
                ", content='" + content + '\'' +
                '}';
    }


}
