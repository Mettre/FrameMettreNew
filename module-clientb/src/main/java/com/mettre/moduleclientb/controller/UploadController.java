package com.mettre.moduleclientb.controller;

import cn.hutool.core.util.StrUtil;
import com.mettre.moduleclientb.service.FileService;
import com.mettre.moduleclientb.utils.QiniuUtil;
import com.mettre.modulecommon.base.Result;
import com.mettre.modulecommon.base.ResultBean;
import com.mettre.modulecommon.enums.CustomerException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;

@Slf4j
@RestController
@RequestMapping("/upload")
@Api(description = "文件上传")
public class UploadController {

    /**
     * 在配置文件中配置的文件保存路径
     */
    @Value("${spring.img.location}")
    private String location;

    @Autowired
    private QiniuUtil qiniuUtil;

    @Autowired
    public FileService fileService;

    private static final Logger logger = LoggerFactory.getLogger(UploadController.class);

    @RequestMapping(value = "/img/upload", method = RequestMethod.PUT)
    @ApiOperation(value = "本地上传文件")
    public Result<Object> uploadImg(@RequestParam("file") MultipartFile multipartFile) {

        return new Result().ok(new ResultBean("" + fileService.insert(multipartFile)));
    }

    @RequestMapping(value = "/qiniu/upload", method = RequestMethod.POST)
    @ApiOperation(value = "七牛云文件上传")
    public Result<Object> upload(@RequestParam("file") MultipartFile file) {

        // IP限流 在线Demo所需 5分钟限1个请求
//        String token1 = redisRaterLimiter.acquireTokenFromBucket("upload:"+IpInfoUtil.getIpAddr(request), 1, 300000);
//        if (StrUtil.isBlank(token1)) {
//            throw new XbootException("上传那么多干嘛，等等再传吧");
//        }

        String imagePath = null;
        String fileName = qiniuUtil.renamePic(file.getOriginalFilename());
        try {
            FileInputStream inputStream = (FileInputStream) file.getInputStream();
            //上传七牛云服务器
            imagePath = qiniuUtil.qiniuInputStreamUpload(inputStream, fileName);
            if (StrUtil.isBlank(imagePath)) {
                throw new CustomerException("上传失败，请检查七牛云配置");
            }
            if (imagePath.contains("error")) {
                throw new CustomerException(imagePath);
            }
        } catch (Exception e) {
            log.error(e.toString());
            throw new CustomerException(e.toString());
        }

        return new Result().ok(new ResultBean(imagePath));
    }
}
