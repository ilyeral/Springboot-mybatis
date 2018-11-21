package com.example.demo.service;

import com.example.demo.dao.FileInfoMapper;
import com.example.demo.model.FileInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class FileService {
    @Resource
    private FileInfoMapper fileInfoMapper;

    public String saveFile(FileInfo fileInfo){
        fileInfoMapper.insert(fileInfo);
        return "faild";


    }
}
