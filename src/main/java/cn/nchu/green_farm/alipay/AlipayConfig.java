package cn.nchu.green_farm.alipay;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {

    // ↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2016091400508294";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCIsVkZIFmbG1+kNW4HbFoNrola0NcIkQCMSOhDvth/dcjvSBtX7ga9z33ZOTgjaGnoS3286ngJn4bbFkQ0sqKHXMlczn+qhdOuM6NsvgXgbZiMC3y8Gl8H0jkk9l4Nmue4K8XCylNDff0+dHU2BbafVsJb46Yge22qTz6FTFEGX86kZFgRwq0AAt819DOLAzoBiFADX1RASgR6KGA+a3B3X8ycSO0V0U6AaUTwF46qLcPN8Sq3UXp1xa1ehnJz5Fw/Cm3fkNfQencMySkE/tjaOZqgq2bYaQYfNAZRoF54hrWgv7no2/YyEgmbyXl494AYoIv7+Y8fMfkVqD+g6417AgMBAAECggEBAIW8vmeO9xyCU7jdbaFlqVXphgmMelavR9H5IwXlGvzZyJYT/myxPXdIIuD80qgR6EyWT5cd8AO6SL7hsim8/LdjWL+sacv0036El0g1Qpqpn66lLoz7YC7Ox4oaYTr+a+gNK5fBTGWkRrlsR6FrRZnv0IkG2eq92DXhAlJ4sG2CsOeULaZRhSYlttzmTvIXHFqZs4nOkPiKwCYEcFpNpzoFJ9INAv/cPuAVqKfGqSghn04t0nk0NyHS3O5loZ6SyCb0WpRZ5u5t5pwaMvr3Y+MDOONmzsMzTy00XYd6he34q5udeCpB0sdXj38H25cNIM2xE6zuU6Ly0FomN2n7HIECgYEAwhBEDuzeK2vgLKdatJAWMdn55/4SChH87Qj6s/ABAyg8XCk6/NlGrO32lfCSRi09itohKgVWymZtyebLZ4JxxBPF4o7J7a0QeF3r4O6khW1x324CWl/Rjg9NvEEiMo5eeoNq4WmRDo0fwIdDUtRw9gYW5U6nQ0cSUgbgNmRHx/kCgYEAtFGrnpsbNKsOnnz63baEmAmzNXIMsh4Xt4XbdzoT7VOPhGpIl517l6dBPMIyxzMEkhDr4kKb++TCWZPDieuVw/vh8F8JThPfMegxk0DzlED2QJ4DJKRHCMfcR/kUnqxsKUZew5Papa7vMSF2XLCQaM++UJs8QVzfeMG3Moje5hMCgYEAv0h1LRkBLF+aiakz2QKlPeITWD6w3Ug5vBK7upz5AnmodVcWk6A+77lm8wg7xfxVywWS5u54S+yGjxp0p8SGCrVKujPwiduqflplZEMV24azVX4Dx7zmu1YOU18ILDj0m2Hp+eGqX2NvRsCM9Zn89iOucbwDVmc5gazRWAukTVECgYAl04wg9YC7h7bFYIpvwD4IduB1MbugEdf/08C6PiC4BHNy36jixVKL+bYhvMtSJk2rJomVKUv3h4GNsQWwBQ+h0VEQT59wFZMYsRDVRybRzt7Zp0wZf6y3FtYK8yF0N89oyS5P8NBJOW1L7S0bPJ7OneXamvYE8Cqg9mIr47J+qQKBgAzhYHi4HG+AecoIfnzH/17j9y4i63BH95yBkqhDg5EV8kkvwebvePoXk/VRwy4dwZ91+jkCXyb9/TwrZcCI/itprZ+c9XiUxOM2oSnQptnnLltJ5WDRqKjlCl5DxIChuoas6SCZwy/8x0LAshpEXviw2UfIukBO3dpbVqUfAFDM";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAv3QSmkqMwPBO+ur5/LKqPasrjSt+D27lKS4AaHE15OqK6QQtNcu+dpQPr7XQcTjEdqMaypdQPLjgitwdm3w2CcGpU2VgDf/KKtraMFx1UQxBviEdc4kV6JTal25ZHQtDbLcSOmKfTn8XcTYwH0GVJKrXjZCy2LvFVmLmFLSpembPydvKhLb5f82OoZ4RhLNGqDIcAQvmOc/R71jrMtj3qroZl8cAdkKCQPqO8JA7rQ9MahPiV5TmQ2pyzgD4hdCtt4LcnSh3pfUzOwPj3X9p5F4oVqZnpIlsS5hUbYujwTLoWEs7y+U7m88r2sA8Q4RUfmnaK3l3Epz+NhQdfINFUQIDAQAB";

    // 服务器异步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "https://www.baidu.com/?tn=62095104_2_oem_dg";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "https://www.baidu.com/?tn=62095104_2_oem_dg";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";


    // ↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑


}
