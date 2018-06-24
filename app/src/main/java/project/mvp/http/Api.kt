package project.mvp.http

public class Api{

    companion object {

       // public var ip:String="http://47.94.103.203:8003"

        //测试环境
        public var ip:String="http://ylj.tunnel.echomod.cn"

        public var loginUrl=ip+"/v1/api/farm/login";   //登录接口
        public var checkMessageUrl= ip+"/v1/api/farm/sms/code"  //获取验证码接口
        public var registerUrl=ip+"/v1/api/farm/register"  //注册接口

        public var codeUrl=ip+"/v1/api/farm/code/login";// 验证码登录


    }
}