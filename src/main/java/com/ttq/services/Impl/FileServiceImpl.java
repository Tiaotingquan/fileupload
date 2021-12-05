package com.ttq.services.Impl;

import com.ttq.dao.FileMapper;
import com.ttq.pojo.File;
import com.ttq.services.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 业务逻辑层实现接口
 */
@Service
public class FileServiceImpl implements FileService {
    /**
     * 自动注入bean
     */
    @Autowired
    private FileMapper fileMapper;
    /**
     * 数据库中添加一条文件上传记录
     * @param record
     * @return
     */
    public int insert(File record) {
        // 返回添加文件数据结果
        Integer result = fileMapper.insert(record);
        return result;
    }
}
