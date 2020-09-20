package com.mettre.moduleclient.controller;

import com.mettre.moduleclient.pojo.AccountClassification;
import com.mettre.moduleclient.service.AccountClassificationService;
import com.mettre.modulecommon.base.Result;
import com.mettre.modulecommon.base.ResultBean;
import com.mettre.modulecommon.base.ResultList;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api("记账分类")
@CrossOrigin()
@RequestMapping("/accountClassification")
public class AccountClassificationController {

    @Autowired
    AccountClassificationService accountClassificationService;

    @ApiOperation(value = "添加记账分类")
    @PostMapping(value = "/add")
    public Result<ResultBean> addClassification(@Valid @RequestBody AccountClassification accountClassification) {
        accountClassificationService.insert(accountClassification);
        return Result.ok();
    }

    @ApiOperation(value = "修改记账分类")
    @PostMapping(value = "/update")
    public Result<ResultBean> updateClassification(@Valid @RequestBody AccountClassification accountClassification) {
        accountClassificationService.updateByPrimaryKeySelective(accountClassification);
        return Result.ok();
    }


    @ApiOperation(value = "记账分类列表")
    @GetMapping(value = "/list/{type}")
    public Result<Object> searchAccountList(@PathVariable Integer type) {
        return Result.ok(new ResultList(accountClassificationService.accountClassificationList(type)));
    }

    /**
     * 访问首页
     */
    @ApiOperation(value = "访问首页测试联通继续开发")
    @GetMapping("/index")
    public String index() {
        return "hello SpringBoot！";
    }

    /**
     * vue 测试
     */
    @ApiOperation(value = "vue测试")
    @PostMapping("/test/vue")
    public Result<Object> testVue(@RequestParam String fileName, @RequestParam String email, @RequestParam String coverPicture, @RequestParam String pdfFile) {

        Map<String, Object> testMap = new HashMap<>();
        testMap.put("fileName", fileName);
        testMap.put("email", email);
        testMap.put("coverPicture", coverPicture);
        testMap.put("pdfFile", pdfFile);
        return Result.ok(testMap);
    }


    /**
     * vue 列表测试
     */
    @ApiOperation(value = "列表测试")
    @PostMapping("/test/getListVue")
    public Map<String, Object> getListVue(@RequestBody Map<String, Object> map) {

        Map<String, Object> testMap = new HashMap<>();
        int length = (int) map.get("length");
        int start = (int) map.get("start");
        List<Map<String, Object>> accountList = new ArrayList<>();
        for (int i = start; i < (start + length); i++) {
            Map<String, Object> map1 = new HashMap<>();
            map1.put("id", i+1 );
            map1.put("fileName", "我的祖国" + i);
            map1.put("email", "1052461392@qq.com");
            map1.put("coverPicture", "封面封面.jpg");
            map1.put("pdfFile", "pdf文件.pdf");
            accountList.add(map1);
        }
        testMap.put("list", accountList);
        testMap.put("draw", 4);
        testMap.put("recordsTotal", 20);
        testMap.put("recordsFiltered", 20);
        return testMap;
    }

}
