package com.nova.sd.model;

import com.nova.sd.util.DateTimeUtils;

import java.io.UnsupportedEncodingException;

/**
 * @author h.wang
 * @date 2017-01-18 下午 2:58
 */
public class RequestInfo {

    private String orderCode;
    private String version;
    private String productId;
    private String tranTime;
    private String timeOut;
    private int busiType;
    private String tranAmt;
    private int currencyCode;
    private int accAttr;
    private String accNo;
    private int accType;
    private String accName;
    private String bankName;
    private int bankType;
    private String remark;

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

    public String getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(String timeOut) {
        this.timeOut = timeOut;
    }

    public int getBusiType() {
        return busiType;
    }

    public void setBusiType(int busiType) {
        this.busiType = busiType;
    }

    public String getTranAmt() {
        return tranAmt;
    }

    public void setTranAmt(String tranAmt) {
        this.tranAmt = tranAmt;
    }

    public int getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(int currencyCode) {
        this.currencyCode = currencyCode;
    }

    public int getAccAttr() {
        return accAttr;
    }

    public void setAccAttr(int accAttr) {
        this.accAttr = accAttr;
    }

    public String getAccNo() {
        return accNo;
    }

    public void setAccNo(String accNo) {
        this.accNo = accNo;
    }

    public int getAccType() {
        return accType;
    }

    public void setAccType(int accType) {
        this.accType = accType;
    }

    public String getAccName() {
        return accName;
    }

    public void setAccName(String accName) {
      this.accName = accName;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public int getBankType() {
        return bankType;
    }

    public void setBankType(int bankType) {
        this.bankType = bankType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        String str =  "{" +
                "orderCode:'" + orderCode + '\'' +
                ", version:'" + version + '\'' +
                ", productId:'" + productId + '\'' +
                ", tranTime:'" + tranTime + '\'' +
                ", timeOut:'" + timeOut + '\'' +
                ", busiType:" + busiType +
                ", tranAmt:'" + tranAmt + '\'' +
                ", currencyCode:" + currencyCode +
                ", accAttr:" + accAttr +
                ", accNo='" + accNo + '\'' +
                ", accType:" + accType +
                ", accName:'" + accName + '\'' +
                ", bankName:'" + bankName + '\'' +
                ", bankType:" + bankType +
                ", remark:'" + remark + '\'' +
                '}';
        return str;
    }
}
