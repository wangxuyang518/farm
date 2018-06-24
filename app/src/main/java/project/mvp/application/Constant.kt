package project.mvp.application

class Constant {
    companion object {
        public var SELLER: Int = 1
        public var BUYER: Int = 2
        public var Distributor: Int = 3

        public var SUCCESS = 0
        public var FAIl = 1
        public var USERSTOP = 2
        public var USERFAILURE = 3
        public var CODEFAILURE = 4
        public var SMSSENDFAILURE = 5
        public var ERRORTOKEN = 6

        public var SECRET = "1234567890123456";

        //registerActivity
        //public var PHONETIPS = "请检查手机号格式"
        public var LOADFAIL = "网络请求失败"
        public var NOPESSPERMISSION = "没有权限"
        public var FORBIDDEN = "权限禁止"
        public var NOTFOUND = "Not Found"
        public var UNKOWN = "未知错误"
        public var USERSTOPTXT="账户不存在或者账户已经停用"
        public var USERFAILURETXT="账户或者密码错误,请重新输入" //CODE_FAILURE
        public var CODEFAILURETXT="验证码错误"
        public var SMSSENDFAILURETXT="发送短信验证码失败"
        public var ERRORTOKENTXT="Token过期请重新登录"
    }

}