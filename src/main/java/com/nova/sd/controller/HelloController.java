package com.nova.sd.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nova.sd.model.QueryRequestInfo;
import com.nova.sd.model.RequestInfo;
import com.nova.sd.model.User;
import com.nova.sd.service.EncyptUtilService;
import com.nova.sd.util.DateTimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author h.wang
 * @date 2017-01-17 下午 6:32
 */
@RestController
@RequestMapping("/")
public class HelloController {
    private static Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    private EncyptUtilService encyptUtilService;

    @RequestMapping(value="/",method = RequestMethod.GET)
    public String index(){
        return "Hello World";
    }

    @RequestMapping(value="hello",method = RequestMethod.GET)
    public String hello(){
        return "Hello Spring Boot!";
    }

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public ModelAndView doView() {
        ModelAndView mv = new ModelAndView("test");
        return mv;
    }


    @RequestMapping(value = "/paramTest",produces="application/json")
    public String jsonParamTest(@RequestBody List<RequestInfo> requestInfos) {

        String jsonString = JSON.toJSONString(requestInfos.get(0));

        //logger输出中文乱码，System.out.println正常
        System.out.println(jsonString);
        logger.info(jsonString);
        System.out.println(requestInfos.get(0).getAccName());
        logger.info(requestInfos.get(0).getAccName());

        return encyptUtilService.getDncryptRetData(jsonString);
    }

    @RequestMapping("/sdtest")
    public void getUserInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("orderCode", "000000001062");

        jsonObject.put("version", "01");
        jsonObject.put("productId", "00000003");
        jsonObject.put("tranTime", DateTimeUtils.getCurrentDateToString("yyyyMMddHHmmss"));

        jsonObject.put("timeOut", "20161024120000");
        jsonObject.put("busiType", "1");
        jsonObject.put("tranAmt", "000000000001");
        jsonObject.put("currencyCode", "156");

        jsonObject.put("accAttr", "0");
        jsonObject.put("accNo", "6216261000000000018");
        jsonObject.put("accType", "4");
        jsonObject.put("accName", "全渠道");
        jsonObject.put("bankName", "cbc");
        jsonObject.put("bankType", "1");
        jsonObject.put("remark", "pay");
        logger.info("json:"+ jsonObject.toJSONString());

        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.write(encyptUtilService.getDncryptRetData(jsonObject.toJSONString()));
    }

    @RequestMapping(value = "/queryTest",method = RequestMethod.POST)
    public String queryTest(@RequestBody QueryRequestInfo queryRequestInfo) {
        return encyptUtilService.getQueryData(queryRequestInfo.toString());
    }


    @RequestMapping("/queryTest1")
    public void queryTest1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("orderCode", "000000001051");
        jsonObject.put("version", "01");
        jsonObject.put("productId", "00000003");
        jsonObject.put("tranTime", DateTimeUtils.getCurrentDateToString("yyyyMMddHHmmss"));
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.write(encyptUtilService.getQueryData(jsonObject.toJSONString()));
    }

    @RequestMapping(value="usertest",method={RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public void addColanderSeedAll(@RequestBody List<User> users){
        System.out.println(users.get(0).getName());
    }
}
