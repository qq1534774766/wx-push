package com.aguo.wxpush.entity;
 

/**
 * @Description:
 * @Author: lst
 * @Date 2020-08-19
 * 1、文本消息
 * <xml>
 *   <ToUserName>oANl56cC7d7JP88la43243WaA</ToUserName>
 *   <FromUserName>gh_a1821534134</FromUserName>
 *   <CreateTime>1597979297984</CreateTime>
 *   <MsgType>text</MsgType>
 *   <Content>你</Content>
 * </xml>
 *
 */
public class TextMessage {
    private String ToUserName;
    private String FromUserName;
    private Long CreateTime;
    private  String MsgType;
 
    private String Content;

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public Long getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Long createTime) {
        CreateTime = createTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}