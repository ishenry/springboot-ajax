package com.nova.sd.util;

import cn.com.sand.online.agent.service.sdk.*;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EncyptUtil {
	private String publicKeyPath;
	private String privateKeyPath;
	private String keyPassword;
	public EncyptUtil(String publicKeyPath, String privateKeyPath,String keyPassword){
		this.publicKeyPath = publicKeyPath;
		this.privateKeyPath = privateKeyPath;
		this.keyPassword=keyPassword;
	}
	
	public List<NameValuePair>  genEncryptData(String merchId, String transCode, String data) {
		// TODO Auto-generated method stub
		if (null == merchId || null == transCode ||null == data ) {
			return null;
		}
		
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		formparams.add(new BasicNameValuePair("merId", merchId));
		formparams.add(new BasicNameValuePair("transCode", transCode));
		try {
			// 加载公私钥
			CertUtil.init(publicKeyPath, privateKeyPath,keyPassword);		
			byte[] plainBytes = data.getBytes(Constants.DEFAULT_CHARSET);
			//生成AESKEY
			String aesKey = RandomStringGenerator.getRandomStringByLength(16);
			byte[] aesKeyBytes = aesKey.getBytes(Constants.DEFAULT_CHARSET);
			//报文加密
			String encryptData = new String(Base64.encodeBase64((CryptoUtil
					.AESEncrypt(plainBytes, aesKeyBytes, "AES",
							"AES/ECB/PKCS5Padding", null))),
					Constants.DEFAULT_CHARSET);
			//生成签名
			String sign = new String(Base64.encodeBase64(CryptoUtil
					.digitalSign(plainBytes, CertUtil.getPrivateKey(),
							"SHA1WithRSA")), Constants.DEFAULT_CHARSET);
			//AESKEY加密
			String encryptKey = new String(Base64.encodeBase64(CryptoUtil
					.RSAEncrypt(aesKeyBytes, CertUtil.getPublicKey(), 2048, 11,
							"RSA/ECB/PKCS1Padding")), Constants.DEFAULT_CHARSET);

			formparams.add(new BasicNameValuePair("encryptData", encryptData));
			formparams.add(new BasicNameValuePair("encryptKey", encryptKey));
			formparams.add(new BasicNameValuePair("sign", sign));
			System.out.println("encryptData:" + encryptData);
			System.out.println("encryptKey:" + encryptKey);
			System.out.println("sign:" + sign);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

		return formparams;
	}
	public String  dncryptRetData(String data) throws Exception {
		
		data = data.substring(1, data.length() - 1); // 返回报文时存在""去除掉
		// 解析响应报文到Map
		Map<String, String> responseMap = SDKUtil
				.convertResultStringToMap(data);
		// for(Map.Entry<String, String> e : responseMap.entrySet()){
		// System.out.println("key:" + e.getKey() +",value:"+ e.getValue());
		// }

		String retEncryptKey = responseMap.get("encryptKey");
		String retEncryptData = responseMap.get("encryptData");
		String retSign = responseMap.get("sign");
		System.out.println("retEncryptKey:" + retEncryptKey);
		System.out.println("retEncryptData:" + retEncryptData);
		System.out.println("retSign:" + retSign);

		byte[] decodeBase64KeyBytes = Base64.decodeBase64(retEncryptKey
				.getBytes(Constants.DEFAULT_CHARSET));
		// 解密encryptKey得到merchantAESKey
		byte[] merchantAESKeyBytes = CryptoUtil.RSADecrypt(
				decodeBase64KeyBytes, CertUtil.getPrivateKey(), 2048, 11,
				"RSA/ECB/PKCS1Padding");
		// 使用base64解码商户请求报文
		byte[] decodeBase64DataBytes = Base64.decodeBase64(retEncryptData
				.getBytes(Constants.DEFAULT_CHARSET));

		// 用解密得到的merchantAESKey解密encryptData
		byte[] retDataBytes = CryptoUtil.AESDecrypt(decodeBase64DataBytes,
				merchantAESKeyBytes, "AES", "AES/ECB/PKCS5Padding", null);

		System.out.println("retData:"
				+ new String(retDataBytes, Constants.DEFAULT_CHARSET));
		byte[] signBytes = Base64.decodeBase64(retSign
				.getBytes(Constants.DEFAULT_CHARSET));
		//验签
		boolean isValid = CryptoUtil.verifyDigitalSign(retDataBytes, signBytes,
				CertUtil.getPublicKey(), "SHA1WithRSA");

		String resultStr = "";
		if (!isValid) {
			resultStr = "报文验签不通过";
			System.out.println("报文验签不通过");
		} else {
			System.out.println("报文验签通过");
			String ret = new String(retDataBytes, Constants.DEFAULT_CHARSET);
			System.out.println("返回报文：" + ret);
			resultStr = "返回报文：" + ret;
		}

		return  resultStr;
	}
	
}
