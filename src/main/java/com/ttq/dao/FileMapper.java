package com.ttq.dao;

import com.ttq.pojo.File;
import org.springframework.stereotype.Repository;



// @Repository用于标注数据访问组件，即DAO组件,放入IOC容器中
@Repository
public interface FileMapper{
    /**
     * 根据文件的ID删除文件
     * @param fileid
     * @return
     */
    int deleteByPrimaryKey(String fileid);

    /**
     * 添加上传的文件放入数据库中，（信息为null也添加null）
     * @param record
     * @return
     */

    int insert(File record);

    /**
     * 添加文件信息（为null不添加信息到数据库中）
     * @param record
     * @return
     */

    int insertSelective(File record);

    /**
     * 根据文件ID查询文件
     * @param fileid
     * @return
     */

    File selectByPrimaryKey(String fileid);

    /**
     * 修改数据库中的文件信息（如果为null不修改）
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(File record);

    /**
     * 修改数据库中的文件信息（如果字段信息为null或空都修改）
     * @param record
     * @return
     */

    int updateByPrimaryKey(File record);
}
