package com.nova.sd.model;

import com.alibaba.fastjson.JSONObject;

/**
 * @author h.wang
 * @date 2017-01-19 下午 6:10
 */
public class QueryRequestInfo {
    private String orderCode;
    private String version;
    private String productId;
    private String tranTime;

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getTranTime() {
        return tranTime;
    }

    public void setTranTime(String tranTime) {
        this.tranTime = tranTime;
    }

    @Override
    public String toString() {
        String str =  "{" +
                "orderCode:'" + orderCode + '\'' +
                ", version:'" + version + '\'' +
                ", productId:'" + productId + '\'' +
                ", tranTime:'" + tranTime + '\'' +
                '}';
        return str;
    }
}
