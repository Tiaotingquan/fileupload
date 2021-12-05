package com.ttq.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
// 声明是数据库中的那个表
@TableName("file")
public class File {
    // 声明是主键字段
    @TableId
    // 数据库导出页面时json格式化
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private String fileid;

    private String oldfilename;

    private String filetype;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private String filesize;
    // 页面写入数据库时格式化
    @DateTimeFormat(pattern="yyyy-MM-dd")
    // 数据库导出页面时json格式化
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Date createtime;

    private String fileaddress;

    private String filenmae;

    public String getFileid() {
        return fileid;
    }

    public void setFileid(String fileid) {
        this.fileid = fileid;
    }

    public String getOldfilename() {
        return oldfilename;
    }

    public void setOldfilename(String oldfilename) {
        this.oldfilename = oldfilename == null ? null : oldfilename.trim();
    }

    public String getFiletype() {
        return filetype;
    }

    public void setFiletype(String filetype) {
        this.filetype = filetype == null ? null : filetype.trim();
    }

    public String getFilesize() {
        return filesize;
    }

    public void setFilesize(String filesize) {
        this.filesize = filesize == null ? null : filesize.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getFileaddress() {
        return fileaddress;
    }

    public void setFileaddress(String fileaddress) {
        this.fileaddress = fileaddress == null ? null : fileaddress.trim();
    }

    public String getFilenmae() {
        return filenmae;
    }

    public void setFilenmae(String filenmae) {
        this.filenmae = filenmae == null ? null : filenmae.trim();
    }
}
