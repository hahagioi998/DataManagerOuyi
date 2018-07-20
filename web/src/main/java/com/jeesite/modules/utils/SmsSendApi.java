package com.jeesite.modules.utils;

import com.sun.deploy.net.URLEncoder;
import net.sf.json.JSONObject;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class SmsSendApi {

    /**
     * 龙卷风平台接口
     */
//"800004","8040423","61.129.57.37","7891"
    public static void main(String[] args) throws UnsupportedEncodingException {
        try {
//           Map m =  sendLJF("800004","8040423","http://61.129.57.37:7891/mt",
//                    "【快钱】验证码：086443，您正在通过小赢理财进行快捷支付账户绑定验证操作，" +
//                             "为保障您的交易安全，确保持卡人信息一致，快钱已将该卡号与您的手机号0730进行绑定。" +
//                            "如验证通过，可通过已绑账户直接进行支付。",
//                   "13288583653");

            Map m = sendMY("11147", "test2018", "ABC147258", "http://183.61.109.238:8888/sms.aspx",
                          "【测试】测试", "13288583653,15112997783");
            System.out.println(m.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public static Map sendLJF(String username,String password,String ipAddress,
                               String msgContent,String phones) throws UnsupportedEncodingException {
        Map m = new HashMap();

        Map<String, String> mapParam = new HashMap<String, String>();
        mapParam.put("dc", "15");
        mapParam.put("un",username);
        mapParam.put("pw", URLEncoder.encode(password, "utf-8"));
        mapParam.put("sm",  URLEncoder.encode(msgContent, "utf-8"));
        mapParam.put("da", phones);
        mapParam.put("tf", "3");
        mapParam.put("rf", "2");
        String pathUrl = ipAddress;
        String sendResult = HttpRequest.sendPost(pathUrl, mapParam);
        System.out.println(sendResult);
        JSONObject jso = JSONObject.fromObject(sendResult);
        String s = jso.get("success").toString();
        if(s.equals("true")) {
            m.put("result", 1);
            m.put("msg", "短信发送成功,请稍等");
        }else {
            m.put("result", 0);
            m.put("msg", "短信发送失败");
        }
        return m;
    }



    /**
     * 迈远平台接口
     */
//帐号：test2018  密码：ABC147258  老迈远   ID:11147   183.61.109.238:8888
    public static Map sendMY(String userid,String username,String password,String ipAddress,
                               String msgContent,String phones) throws UnsupportedEncodingException {

        Map m = new HashMap();
        Map<String, String> mapParam = new HashMap<String, String>();
        mapParam.put("userid",userid);
        mapParam.put("account", URLEncoder.encode(username, "utf-8"));
        mapParam.put("password",  URLEncoder.encode(password, "utf-8"));
        mapParam.put("mobile", phones);
        mapParam.put("content", msgContent);
        mapParam.put("action", "send");
        String pathUrl = ipAddress;
        String sendResult = HttpRequest.sendPost(pathUrl, mapParam);
        String node = "returnstatus";
        String returnstatus = getNodeByXml(sendResult,node);
//        System.out.println(returnstatus);
        if(returnstatus.equals("Success")) {
            m.put("result", 1);
            m.put("msg", "短信发送成功,请稍等");
        }else {
            m.put("result", 0);
            m.put("msg", "短信发送失败");
        }
        return m;
    }


    /**
     * 获取xml某个节点的值
     *
     * @param xml
     * @param node
     * @return
     */
    public static String getNodeByXml(String xml, String node) {
        Document doc = convertDocFromStr(xml);
        if (doc == null) {
            return null;
        } else {
            Node typeNode = doc.selectSingleNode(".//*[local-name()='" + node
                    + "']");
            if (typeNode == null) {
                return null;
            } else {
                return typeNode.getStringValue();
            }
        }
    }


    /**
     * 字符串转Document
     *
     * @param xml
     * @return
     */
    public static Document convertDocFromStr(String xml) {
        try {
            return DocumentHelper.parseText(xml);
        } catch (DocumentException e) {
            return null;
        }
    }
}

