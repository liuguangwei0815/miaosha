package com.im.miaosha.result;

import lombok.Data;

/**
 * @program: miaosha
 * @description: 错误代码和提示
 * @author: liu.wei
 * @create: 2020-04-17 10:37
 **/
@Data
public class MsgCode {
    private int code;
    private String msg;
    /**
     * 成功
     */
    public final static MsgCode SUCCESS = new MsgCode(0, "sucess");
    /**
     * 服务器错误
     */
    public final static MsgCode SERVER_ERROR = new MsgCode(500100, "服务端异常");
    ///登陆模块异常 5002xx
    public final static MsgCode MOBILEPHONENUMBERISEMPTY = new MsgCode(500101, "手机号码不能为空");
    public final static MsgCode PASSWORDCANNOTBEEMPTY = new MsgCode(500102, "密码不能为空");
    public final static MsgCode MOBILEPHONENUMBERFORMATISNOTCORRECT = new MsgCode(500103, "手机号码格式不正确");
    public final static MsgCode USERDOESNOTEXIST = new MsgCode(500104, "用户不存在");
    public final static MsgCode PASSWORDDOESNOTMATCH = new MsgCode(500104, "密码不匹配");
    public final static MsgCode ABNORMALPARAMETERBINDING = new MsgCode(500104, "参数绑定异常：%s");

    //商品模块异常 5003xx
    //订单模块异常 5004xx
    //秒杀模块异常 5005xx

    private MsgCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    /**
     * 填写参数类似再次组织了信息
     *
     * @param args 参数
     * @return {@link MsgCode}
     */
    public MsgCode fillArgs(Object... args){
        String msg = String.format(this.msg,args);
        return new MsgCode(this.code,msg);
    }

    /**
     * 私有不给其他人构造
     */
    private MsgCode() {
    }
}
