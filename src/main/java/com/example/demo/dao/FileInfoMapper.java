package com.example.demo.dao;

import com.example.demo.model.FileInfo;

public interface FileInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FileInfo record);

    int insertSelective(FileInfo record);

    FileInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FileInfo record);

    int updateByPrimaryKeyWithBLOBs(FileInfo record);

    int updateByPrimaryKey(FileInfo record);
}