package com.nova.sd;

import com.alibaba.fastjson.JSONObject;
import com.nova.sd.util.DateTimeUtils;
import com.nova.sd.util.EncyptUtil;
import com.nova.sd.util.HttpUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CharacterEncodingFilter;

@SpringBootApplication
public class SdApplication {

	private static String transCode = "RTPM";	//交易代码,交易唯一

	private static String transCode1 = "ODQU";	//交易代码,交易唯一

	public static String genRequest1() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("orderCode", "000000001051");
		jsonObject.put("version", "01");
		jsonObject.put("productId", "00000003");
		jsonObject.put("tranTime", DateTimeUtils.getCurrentDateToString("yyyyMMddHHmmss"));
		return jsonObject.toJSONString();
	}

	public static String genRequest() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("orderCode", "000000001062");

		jsonObject.put("version", "01");
		jsonObject.put("productId", "00000003");
		jsonObject.put("tranTime", DateTimeUtils.getCurrentDateToString("yyyyMMddHHmmss"));

		jsonObject.put("timeOut", "20161024120000");
		jsonObject.put("busiType", "1");
		jsonObject.put("tranAmt", "000000000001");
		jsonObject.put("currencyCode", "156");

//		jsonObject.put("accAttr", "1");
		jsonObject.put("accAttr", "0");
		jsonObject.put("accNo", "6216261000000000018");
		jsonObject.put("accType", "4");
		jsonObject.put("accName", "全渠道");
		//jsonObject.put("provNo", "sh");
		//jsonObject.put("cityNo", "sh");
		jsonObject.put("bankName", "cbc");
		jsonObject.put("bankType", "1");
		jsonObject.put("remark", "pay");
		System.out.println("json:"+ jsonObject.toJSONString());
		return jsonObject.toJSONString();
	}

	public static void main(String args[]) throws Exception{

		SpringApplication.run(SdApplication.class, args);

		//创建加解密辅助类
		EncyptUtil encyptUtil = new EncyptUtil("classpath:SAND_PUBLIC_KEY.cer", "classpath:MID_RSA_PRIVATE_KEY.pfx","123456");
		//设置商户号
		String merchId = "105951701170001";
		//发送加密数据
		String data = HttpUtil.post("http://61.129.71.103:7970/agent-main/openapi/agentpay",
				encyptUtil.genEncryptData(merchId, transCode, genRequest()));
		System.out.println("retData:" + data);
		//解密返回数据并验签
		encyptUtil.dncryptRetData(data);
	}
}
