package com.mettre.moduleclientb.service;

import cn.hutool.core.util.StrUtil;
import com.mettre.moduleclientb.mapper.FileMapper;
import com.mettre.moduleclientb.pojo.File;
import com.mettre.moduleclientb.utils.QiniuUtil;
import com.mettre.modulecommon.base.ReturnType;
import com.mettre.modulecommon.enums.CustomerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Transactional
public class FileService {

    private static final Logger logger = LoggerFactory.getLogger(FileService.class);

    /**
     * 在配置文件中配置的文件保存路径
     */
    @Value("${spring.img.location}")
    private String location;

    @Autowired
    private QiniuUtil qiniuUtil;

    @Autowired
    public FileMapper fileMapper;


    public long insert(MultipartFile multipartFile) {
        if (multipartFile.isEmpty() || StrUtil.isBlank(multipartFile.getOriginalFilename())) {
            throw new CustomerException("图片不存在");
        }
        String contentType = multipartFile.getContentType();
        if (!contentType.contains("")) {
            throw new CustomerException("图片格式不正确");
        }
        String root_fileName = multipartFile.getOriginalFilename();
        String prefix = root_fileName.substring(root_fileName.lastIndexOf("."));
        logger.info("上传图片:name={},type={}", root_fileName, contentType);

        //获取路径
        String return_path = "upload_image";
        String filePath = location + return_path;
        logger.info("图片保存路径={}", location);
        String file_name = null;
        try {
            file_name = qiniuUtil.saveImg(multipartFile, filePath, prefix);
            if (StrUtil.isBlank(file_name)) {
                throw new CustomerException("图片不存在");
            }
            File file = new File(return_path + java.io.File.separator + file_name);
            fileMapper.insert(file);
            return (long) ReturnType.ReturnType(file.getFileId(), "图片插入失败");
        } catch (IOException e) {
            throw new CustomerException("图片不存在");
        }
    }
}
