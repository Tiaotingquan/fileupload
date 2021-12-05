package com.ttq.services.service;


import com.ttq.pojo.File;
import org.springframework.stereotype.Repository;


/**
 * 文件上传
 * 业务逻辑层接口
 */
// 基本注解，标识这是一个业务组件，放入IOC容器中
@Repository
public interface FileService {
    /**
     * 数据库中添加一条文件上传记录
     * @param record
     * @return
     */
    int insert(File record);
}
