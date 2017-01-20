package com.nova.sd.service;

import com.nova.sd.util.EncyptUtil;
import com.nova.sd.util.HttpUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

/**
 * @author h.wang
 * @date 2017-01-18 下午 5:00
 */
@Service
public class EncyptUtilService {

    private static Logger logger = Logger.getLogger(EncyptUtilService.class);

    private static String transCode = "RTPM";	//交易代码,交易唯一
    private static String query_transCode = "ODQU";

    public String getDncryptRetData(String jsonStr){
        String str = "null";

        Resource resource = new ClassPathResource("SAND_PUBLIC_KEY.cer");
        String publicKeyPathFileName = resource.getFilename();
        logger.info(publicKeyPathFileName);

        //创建加解密辅助类
        EncyptUtil encyptUtil = new EncyptUtil("classpath:SAND_PUBLIC_KEY.cer", "classpath:MID_RSA_PRIVATE_KEY.pfx","123456");
        //设置商户号

        String merchId = "105951701170001";
        //发送加密数据
        String data = null;
        try {
            data = HttpUtil.post("http://61.129.71.103:7970/agent-main/openapi/agentpay",
                    encyptUtil.genEncryptData(merchId, transCode, jsonStr));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("retData:" + data);
        //解密返回数据并验签

        try {
            str = encyptUtil.dncryptRetData(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    public String getQueryData(String jsonStr){
        //创建加解密辅助类
        EncyptUtil encyptUtil = new EncyptUtil("classpath:SAND_PUBLIC_KEY.cer", "classpath:MID_RSA_PRIVATE_KEY.pfx","123456");
        //设置商户号
        String merchId = "888800000000001";
        //发送加密数据
        String data = null;
        try {
            data = HttpUtil.post("http://61.129.71.103:7970/agent-main/openapi/queryOrder",
                    encyptUtil.genEncryptData(merchId, query_transCode, jsonStr));
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(!StringUtils.isEmpty(data)){
            System.out.println("retData:" + data);
            return data;
            //解密返回数据并验签
            //encyptUtil.dncryptRetData(data);
        }else{
            System.out.println("null");
            return "null";
        }
    }
}
