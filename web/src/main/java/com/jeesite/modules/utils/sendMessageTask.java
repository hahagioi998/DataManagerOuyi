package com.jeesite.modules.utils;

import com.jeesite.modules.oy_data_cpa.entity.OyDataCpa;
import com.jeesite.modules.oy_data_cpa.service.OyDataCpaService;
import com.jeesite.modules.oy_order_management.entity.OyTaskAudit;
import com.jeesite.modules.oy_order_management.service.OyTaskAuditService;
import com.sun.org.apache.xerces.internal.xs.StringList;

import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * sendMessageTask
 * <p>
 * 用于发送信息的线程
 *
 * @author chf
 * @version 2018-07-12
 */
public class sendMessageTask implements Runnable {

    private OyTaskAuditService oyTaskAuditService=SpringContextAware.getBean("oyTaskAuditService");

    private OyDataCpaService oyDataCpaService=SpringContextAware.getBean("oyDataCpaService");

    private OyTaskAudit oyTaskAudit;

    private Map sendCondition=new HashMap();

    private List sendPhoneNum;

    public sendMessageTask(OyTaskAudit oyTaskAudit) {
        this.oyTaskAudit = ChangeCorp(oyTaskAudit);
        System.out.println("异步执行发送短信线程，发送的订单id为：" + oyTaskAudit.getId());
    }

    @Override
    public void run() {
        //发送短信数据量
        int dataSize=oyTaskAudit.getDataSize();
        getCorpMap(oyTaskAudit);
//        int ciCode= (int) corpMap.get("ciCode");
//        int corpCount=(int) corpMap.get("corpCount");
//
//        Map sendCondition=new HashMap();
//        sendCondition.put("offset",0);

//        if (corpCount==1){
//            //需要发送一个运营商的
//            if (ciCode==1){
//                sendCondition.put("crop","cmpp");
//                sendCondition.put("client_name",oyTaskAudit.getOyClient().getName());
//                sendCondition.put("update_date",7);
//                int total=oyDataCpaService.countTotal(sendCondition);
//
//                if (total >= dataSize){
//                    sendCondition.put("number",oyTaskAudit.getDataSize());
//                    Map result=sendMessage(oyTaskAudit,sendCondition);
//                    int rCode= (int) result.get("result");
//                    if (rCode==0){
//                        oyTaskAudit.setAuditReason("短信发送失败，发送失败的手机号码："+result.get("wrongPhone")+
//                        "，已经发送了"+result.get("sendSum")+"条");
//                        oyTaskAuditService.save(oyTaskAudit);
//                    }else {
//                        oyTaskAudit.setAuditReason("短信发送完毕");
//                        oyTaskAuditService.save(oyTaskAudit);
//                    }
//                }else {
//                    sendCondition.put("number",total);
//                    Map result=sendMessage(oyTaskAudit,sendCondition);
//                    int rCode= (int) result.get("result");
//                    if (rCode==0){
//                        oyTaskAudit.setAuditReason("短信发送失败，发送失败的手机号码："+result.get("wrongPhone")+
//                                "，已经发送了"+result.get("sendSum")+"条");
//                        oyTaskAuditService.save(oyTaskAudit);
//                    }else {
//                        oyTaskAudit.setAuditReason("数据量不足，还剩"+(dataSize-total)+"条未发送");
//                        oyTaskAuditService.save(oyTaskAudit);
//                    }
//                }
//            }else if (ciCode==2){
//                sendCondition.put("crop","sgip");
//                sendCondition.put("client_name",oyTaskAudit.getOyClient().getName());
//                sendCondition.put("update_date",7);
//                int total=oyDataCpaService.countTotal(sendCondition);
//                if (total >= dataSize){
//                    sendCondition.put("number",oyTaskAudit.getDataSize());
//                    Map result=sendMessage(oyTaskAudit,sendCondition);
//                    int rCode= (int) result.get("result");
//                    if (rCode==0){
//                        oyTaskAudit.setAuditReason("短信发送失败，发送失败的手机号码："+result.get("wrongPhone")+
//                                "，已经发送了"+result.get("sendSum")+"条");
//                        oyTaskAuditService.save(oyTaskAudit);
//                    }else {
//                        oyTaskAudit.setAuditReason("短信发送完毕");
//                        oyTaskAuditService.save(oyTaskAudit);
//                    }
//                }else {
//                    sendCondition.put("number",total);
//                    Map result=sendMessage(oyTaskAudit,sendCondition);
//                    int rCode= (int) result.get("result");
//                    if (rCode==0){
//                        oyTaskAudit.setAuditReason("短信发送失败，发送失败的手机号码："+result.get("wrongPhone")+
//                                "，已经发送了"+result.get("sendSum")+"条");
//                        oyTaskAuditService.save(oyTaskAudit);
//                    }else {
//                        oyTaskAudit.setAuditReason("数据量不足，还剩"+(dataSize-total)+"条未发送");
//                        oyTaskAuditService.save(oyTaskAudit);
//                    }
//                }
//            }else if (ciCode==3){
//                sendCondition.put("crop","smgp");
//                sendCondition.put("client_name",oyTaskAudit.getOyClient().getName());
//                sendCondition.put("update_date",7);
//                int total=oyDataCpaService.countTotal(sendCondition);
//                if (total >= dataSize){
//                    sendCondition.put("number",oyTaskAudit.getDataSize());
//                    Map result=sendMessage(oyTaskAudit,sendCondition);
//                    int rCode= (int) result.get("result");
//                    if (rCode==0){
//                        oyTaskAudit.setAuditReason("短信发送失败，发送失败的手机号码："+result.get("wrongPhone")+
//                                "，已经发送了"+result.get("sendSum")+"条");
//                        oyTaskAuditService.save(oyTaskAudit);
//                    }else {
//                        oyTaskAudit.setAuditReason("短信发送完毕");
//                        oyTaskAuditService.save(oyTaskAudit);
//                    }
//                }else {
//                    sendCondition.put("number",total);
//                    Map result=sendMessage(oyTaskAudit,sendCondition);
//                    int rCode= (int) result.get("result");
//                    if (rCode==0){
//                        oyTaskAudit.setAuditReason("短信发送失败，发送失败的手机号码："+result.get("wrongPhone")+
//                                "，已经发送了"+result.get("sendSum")+"条");
//                        oyTaskAuditService.save(oyTaskAudit);
//                    }else {
//                        oyTaskAudit.setAuditReason("数据量不足，还剩"+(dataSize-total)+"条未发送");
//                        oyTaskAuditService.save(oyTaskAudit);

//                    }
//                }
//            }
//        }else if (corpCount==2){
//            //需要发送两个运营商的
//            if (ciCode==4){
//                Map sendNum=getSendNum(dataSize,corpCount);
//                System.out.println("发送移动和联通的短信。");
//                System.out.println("移动发送："+sendNum.get("num1")+"条，联通发送："+sendNum.get("num2")+"条");
//                //先发移动的
//                sendCondition.put("crop","cmpp");
//                sendCondition.put("client_name",oyTaskAudit.getOyClient().getName());
//                sendCondition.put("update_date",7);
//                int total=oyDataCpaService.countTotal(sendCondition);
//                if (total >= (int)sendNum.get("num1")){
//                    sendCondition.put("number",(int)sendNum.get("num1"));
//                    Map result=sendMessage(oyTaskAudit,sendCondition);
//                    int rCode= (int) result.get("result");
//                    if (rCode==0){
//                        oyTaskAudit.setAuditReason("短信发送失败，发送失败【中国移动】的手机号码："+result.get("wrongPhone")+
//                                "，已经发送了【中国移动】的手机号码"+result.get("sendSum")+"条");
//                        oyTaskAuditService.save(oyTaskAudit);
//                    }else {
//                        //移动发送完毕
//                        sendCondition.put("crop","sgip");
//                        int total2=oyDataCpaService.countTotal(sendCondition);
//                        if (total2 >= (int)sendNum.get("num2")){
//                            sendCondition.put("number",(int)sendNum.get("num2"));
//                            Map result2=sendMessage(oyTaskAudit,sendCondition);
//                            int rCode2= (int) result2.get("result");
//                            if (rCode2==0){
//                                int sum= (int) result2.get("sendSum")+total;
//                                oyTaskAudit.setAuditReason("短信发送失败，发送失败【中国联通】的手机号码："+result2.get("wrongPhone")+
//                                        "，已经发送了【中国移动】的手机号码"+total+"条,【中国联通】的手机号码"+sum+"条");
//                                oyTaskAuditService.save(oyTaskAudit);
//                            }else {
//                                oyTaskAudit.setAuditReason("短信发送完毕");
//                                oyTaskAuditService.save(oyTaskAudit);
//                            }
//                        }else {
//                            sendCondition.put("number",total2);
//                            Map result2=sendMessage(oyTaskAudit,sendCondition);
//                            int rCode2= (int) result2.get("result");
//                            if (rCode2==0){
//                                int sum= (int) result2.get("sendSum")+total;
//                                oyTaskAudit.setAuditReason("短信发送失败，发送失败【中国联通】的手机号码："+result2.get("wrongPhone")+
//                                        "，已经发送了【中国移动】的手机号码"+total+"条,【中国联通】的手机号码"+sum+"条");
//                                oyTaskAuditService.save(oyTaskAudit);
//                            }else {
//                                int i2=(int)sendNum.get("num2")-total2;
//                                oyTaskAudit.setAuditReason("【中国联通】的数据量不足，还剩"+i2+"条未发送");
//                                oyTaskAuditService.save(oyTaskAudit);
//                            }
//                        }
//                    }
//                }else {
//                    sendCondition.put("number",total);
//                    Map result=sendMessage(oyTaskAudit,sendCondition);
//                    int rCode= (int) result.get("result");
//                    if (rCode==0){
//                        oyTaskAudit.setAuditReason("短信发送失败，发送失败【中国移动】的手机号码："+result.get("wrongPhone")+
//                                "，已经发送了"+result.get("sendSum")+"条");
//                        oyTaskAuditService.save(oyTaskAudit);
//                    }else {
//                        //移动发送完毕
//                        sendCondition.put("crop","sgip");
//                        int total2=oyDataCpaService.countTotal(sendCondition);
//                        int sum2=(int)sendNum.get("num2")+((int)sendNum.get("num1")-total);
//                        if (total2 >= sum2){
//                            sendCondition.put("number",sum2);
//                            Map result2=sendMessage(oyTaskAudit,sendCondition);
//                            int rCode2= (int) result2.get("result");
//                            if (rCode2==0){
//                                int sum= (int) result2.get("sendSum")+total;
//                                oyTaskAudit.setAuditReason("短信发送失败，发送失败【中国联通】的手机号码："+result2.get("wrongPhone")+
//                                        "，已经发送了【中国移动】的手机号码"+total+"条,【中国联通】的手机号码"+sum+"条");
//                                oyTaskAuditService.save(oyTaskAudit);
//                            }else {
//                                oyTaskAudit.setAuditReason("短信发送完毕");
//                                oyTaskAuditService.save(oyTaskAudit);
//                            }
//                        }else {
//                            sendCondition.put("number",total2);
//                            Map result2=sendMessage(oyTaskAudit,sendCondition);
//                            int rCode2= (int) result2.get("result");
//                            if (rCode2==0){
//                                int sum= (int) result2.get("sendSum")+total;
//                                oyTaskAudit.setAuditReason("短信发送失败，发送失败【中国联通】的手机号码："+result2.get("wrongPhone")+
//                                        "，已经发送了【中国移动】的手机号码"+total+"条,【中国联通】的手机号码"+sum+"条");
//                                oyTaskAuditService.save(oyTaskAudit);
//                            }else {
//                                int i2=(int)sendNum.get("num2")-total2;
//                                oyTaskAudit.setAuditReason("【中国联通】的数据量不足，还剩"+i2+"条未发送");
//                                oyTaskAuditService.save(oyTaskAudit);
//                            }
//                        }
//                    }
//                }
//            }else if (ciCode==5){
//                Map sendNum=getSendNum(dataSize,corpCount);
//                System.out.println("发送移动和电信的短信。");
//                System.out.println("移动发送："+sendNum.get("num1")+"条，电信发送："+sendNum.get("num2")+"条");
//                //先发移动的
//                sendCondition.put("crop","cmpp");
//                sendCondition.put("client_name",oyTaskAudit.getOyClient().getName());
//                sendCondition.put("update_date",7);
//                int total=oyDataCpaService.countTotal(sendCondition);
//                if (total >= (int)sendNum.get("num1")){
//                    sendCondition.put("number",(int)sendNum.get("num1"));
//                    Map result=sendMessage(oyTaskAudit,sendCondition);
//                    int rCode= (int) result.get("result");
//                    if (rCode==0){
//                        oyTaskAudit.setAuditReason("短信发送失败，发送失败【中国移动】的手机号码："+result.get("wrongPhone")+
//                                "，已经发送了"+result.get("sendSum")+"条");
//                        oyTaskAuditService.save(oyTaskAudit);
//                    }else {
//                        //移动发送完毕
//                        sendCondition.put("crop","smgp");
//                        int total2=oyDataCpaService.countTotal(sendCondition);
//                        if (total2 >= (int)sendNum.get("num2")){
//                            sendCondition.put("number",(int)sendNum.get("num2"));
//                            Map result2=sendMessage(oyTaskAudit,sendCondition);
//                            int rCode2= (int) result2.get("result");
//                            if (rCode2==0){
//                                int sum= (int) result2.get("sendSum")+total;
//                                oyTaskAudit.setAuditReason("短信发送失败，发送失败【中国电信】的手机号码："+result2.get("wrongPhone")+
//                                        "，已经发送了【中国移动】的手机号码"+total+"条,【中国电信】的手机号码"+sum+"条");
//                                oyTaskAuditService.save(oyTaskAudit);
//                            }else {
//                                oyTaskAudit.setAuditReason("短信发送完毕");
//                                oyTaskAuditService.save(oyTaskAudit);
//                            }
//                        }else {
//                            sendCondition.put("number",total2);
//                            Map result2=sendMessage(oyTaskAudit,sendCondition);
//                            int rCode2= (int) result2.get("result");
//                            if (rCode2==0){
//                                int sum= (int) result2.get("sendSum")+total;
//                                oyTaskAudit.setAuditReason("短信发送失败，发送失败【中国电信】的手机号码："+result2.get("wrongPhone")+
//                                        "，已经发送了【中国移动】的手机号码"+total+"条,【中国电信】的手机号码"+sum+"条");
//                                oyTaskAuditService.save(oyTaskAudit);
//                            }else {
//                                int i2=(int)sendNum.get("num2")-total2;
//                                oyTaskAudit.setAuditReason("【中国电信】数据量不足，还剩"+i2+"条未发送");
//                                oyTaskAuditService.save(oyTaskAudit);
//                            }
//                        }
//                    }
//                }else {
//                    sendCondition.put("number",total);
//                    Map result=sendMessage(oyTaskAudit,sendCondition);
//                    int rCode= (int) result.get("result");
//                    if (rCode==0){
//                        oyTaskAudit.setAuditReason("短信发送失败，发送失败【中国移动】的手机号码："+result.get("wrongPhone")+
//                                "，已经发送了"+result.get("sendSum")+"条");
//                        oyTaskAuditService.save(oyTaskAudit);
//                    }else {
//                        //移动发送完毕
//                        sendCondition.put("crop","smgp");
//                        int total2=oyDataCpaService.countTotal(sendCondition);
//                        int sum2=(int)sendNum.get("num2")+((int)sendNum.get("num1")-total);
//                        if (total2 >= sum2){
//                            sendCondition.put("number",sum2);
//                            Map result2=sendMessage(oyTaskAudit,sendCondition);
//                            int rCode2= (int) result2.get("result");
//                            if (rCode2==0){
//                                int sum= (int) result2.get("sendSum")+total;
//                                oyTaskAudit.setAuditReason("短信发送失败，发送失败【中国电信】的手机号码："+result2.get("wrongPhone")+
//                                        "，已经发送了【中国移动】的手机号码"+total+"条,【中国电信】的手机号码"+sum+"条");
//                                oyTaskAuditService.save(oyTaskAudit);
//                            }else {
//                                oyTaskAudit.setAuditReason("短信发送完毕");
//                                oyTaskAuditService.save(oyTaskAudit);
//                            }
//                        }else {
//                            sendCondition.put("number",total2);
//                            Map result2=sendMessage(oyTaskAudit,sendCondition);
//                            int rCode2= (int) result2.get("result");
//                            if (rCode2==0){
//                                int sum= (int) result2.get("sendSum")+total;
//                                oyTaskAudit.setAuditReason("短信发送失败，发送失败【中国电信】的手机号码："+result2.get("wrongPhone")+
//                                        "，已经发送了【中国移动】的手机号码"+total+"条,【中国电信】的手机号码"+sum+"条");
//                                oyTaskAuditService.save(oyTaskAudit);
//                            }else {
//                                int i2=sum2-total2;
//                                oyTaskAudit.setAuditReason("【中国电信】的数据量不足，还剩"+i2+"条未发送");
//                                oyTaskAuditService.save(oyTaskAudit);
//                            }
//                        }
//                    }
//                }
//            }else if (ciCode==6){
//                Map sendNum=getSendNum(dataSize,corpCount);
//                System.out.println("发送联通和电信的短信。");
//                System.out.println("联通发送："+sendNum.get("num1")+"条，电信发送："+sendNum.get("num2")+"条");
//                //先发联通的
//                sendCondition.put("crop","sgip");
//                sendCondition.put("client_name",oyTaskAudit.getOyClient().getName());
//                sendCondition.put("update_date",7);
//                int total=oyDataCpaService.countTotal(sendCondition);
//                if (total >= (int)sendNum.get("num1")){
//                    sendCondition.put("number",(int)sendNum.get("num1"));
//                    Map result=sendMessage(oyTaskAudit,sendCondition);
//                    int rCode= (int) result.get("result");
//                    if (rCode==0){
//                        oyTaskAudit.setAuditReason("短信发送失败，发送失败【中国联通】的手机号码："+result.get("wrongPhone")+
//                                "，已经发送了"+result.get("sendSum")+"条");
//                        oyTaskAuditService.save(oyTaskAudit);
//                    }else {
//                        //移动发送完毕
//                        sendCondition.put("crop","smgp");
//                        int total2=oyDataCpaService.countTotal(sendCondition);
//                        if (total2 >= (int)sendNum.get("num2")){
//                            sendCondition.put("number",(int)sendNum.get("num2"));
//                            Map result2=sendMessage(oyTaskAudit,sendCondition);
//                            int rCode2= (int) result2.get("result");
//                            if (rCode2==0){
//                                int sum= (int) result2.get("sendSum")+total;
//                                oyTaskAudit.setAuditReason("短信发送失败，发送失败【中国电信】的手机号码："+result2.get("wrongPhone")+
//                                        "，已经发送了【中国联通】的手机号码"+total+"条,【中国电信】的手机号码"+sum+"条");
//                                oyTaskAuditService.save(oyTaskAudit);
//                            }else {
//                                oyTaskAudit.setAuditReason("短信发送完毕");
//                                oyTaskAuditService.save(oyTaskAudit);
//                            }
//                        }else {
//                            sendCondition.put("number",total2);
//                            Map result2=sendMessage(oyTaskAudit,sendCondition);
//                            int rCode2= (int) result2.get("result");
//                            if (rCode2==0){
//                                int sum= (int) result2.get("sendSum")+total;
//                                oyTaskAudit.setAuditReason("短信发送失败，发送失败【中国电信】的手机号码："+result2.get("wrongPhone")+
//                                        "，已经发送了【中国联通】的手机号码"+total+"条,【中国电信】的手机号码"+sum+"条");
//                                oyTaskAuditService.save(oyTaskAudit);
//                            }else {
//                                int i2=(int)sendNum.get("num2")-total2;
//                                oyTaskAudit.setAuditReason("【中国电信】数据量不足，还剩"+i2+"条未发送");
//                                oyTaskAuditService.save(oyTaskAudit);
//                            }
//                        }
//                    }
//                }else {
//                    sendCondition.put("number",total);
//                    Map result=sendMessage(oyTaskAudit,sendCondition);
//                    int rCode= (int) result.get("result");
//                    if (rCode==0){
//                        oyTaskAudit.setAuditReason("短信发送失败，发送失败【中国联通】的手机号码："+result.get("wrongPhone")+
//                                "，已经发送了"+result.get("sendSum")+"条");
//                        oyTaskAuditService.save(oyTaskAudit);
//                    }else {
//                        //移动发送完毕
//                        sendCondition.put("crop","smgp");
//                        int total2=oyDataCpaService.countTotal(sendCondition);
//                        int sum2=(int)sendNum.get("num2")+((int)sendNum.get("num1")-total);
//                        if (total2 >= sum2){
//                            sendCondition.put("number",sum2);
//                            Map result2=sendMessage(oyTaskAudit,sendCondition);
//                            int rCode2= (int) result2.get("result");
//                            if (rCode2==0){
//                                int sum= (int) result2.get("sendSum")+total;
//                                oyTaskAudit.setAuditReason("短信发送失败，发送失败【中国电信】的手机号码："+result2.get("wrongPhone")+
//                                        "，已经发送了【中国联通】的手机号码"+total+"条,【中国电信】的手机号码"+sum+"条");
//                                oyTaskAuditService.save(oyTaskAudit);
//                            }else {
//                                oyTaskAudit.setAuditReason("短信发送完毕");
//                                oyTaskAuditService.save(oyTaskAudit);
//                            }
//                        }else {
//                            sendCondition.put("number",total2);
//                            Map result2=sendMessage(oyTaskAudit,sendCondition);
//                            int rCode2= (int) result2.get("result");
//                            if (rCode2==0){
//                                int sum= (int) result2.get("sendSum")+total;
//                                oyTaskAudit.setAuditReason("短信发送失败，发送失败【中国电信】的手机号码："+result2.get("wrongPhone")+
//                                        "，已经发送了【中国联通】的手机号码"+total+"条,【中国电信】的手机号码"+sum+"条");
//                                oyTaskAuditService.save(oyTaskAudit);
//                            }else {
//                                int i2=sum2-total2;
//                                oyTaskAudit.setAuditReason("【中国电信】的数据量不足，还剩"+i2+"条未发送");
//                                oyTaskAuditService.save(oyTaskAudit);
//                            }
//                        }
//                    }
//                }
//            }
//        }else if (corpCount==3){
//            //需要发送两个运营商的
//            if (ciCode==7){
//                Map sendNum=getSendNum(dataSize,corpCount);
//                System.out.println("发送移动，联通和电信的短信。");
//
//                sendCondition.put("crop","cmpp");
//                sendCondition.put("client_name",oyTaskAudit.getOyClient().getName());
//                sendCondition.put("update_date",7);
//                int total=oyDataCpaService.countTotal(sendCondition);
//                if (total >= dataSize){
//                    sendCondition.put("number",oyTaskAudit.getDataSize());
//                    Map result=sendMessage(oyTaskAudit,sendCondition);
//                    int rCode= (int) result.get("result");
//                    if (rCode==0){
//                        oyTaskAudit.setAuditReason("短信发送失败，发送失败的手机号码："+result.get("wrongPhone")+
//                                "，已经发送了"+result.get("sendSum")+"条");
//                        oyTaskAuditService.save(oyTaskAudit);
//                    }else {
//                        oyTaskAudit.setAuditReason("短信发送完毕");
//                        oyTaskAuditService.save(oyTaskAudit);
//                    }
//                }else {
//                    sendCondition.put("number",total);
//                    Map result=sendMessage(oyTaskAudit,sendCondition);
//                    int rCode= (int) result.get("result");
//                    if (rCode==0){
//                        oyTaskAudit.setAuditReason("短信发送失败，发送失败的手机号码："+result.get("wrongPhone")+
//                                "，已经发送了"+result.get("sendSum")+"条");
//                        oyTaskAuditService.save(oyTaskAudit);
//                    }else {
//                        oyTaskAudit.setAuditReason("数据量不足，还剩"+(dataSize-total)+"条未发送");
//                        oyTaskAuditService.save(oyTaskAudit);
//                    }
//                }
//            }
//        }

    }

    //获取对应运营商库中的总条数
    private void getCorpMap(OyTaskAudit oyTaskAudit) {
        String corp=oyTaskAudit.getCrop();
        //库里的总数据量
        int total=0;
        int total1=0;
        int total2=0;
        //随机分配的发送量
        int send=oyTaskAudit.getDataSize();
        int send1=0;
        int send2=0;

        String[] array=corp.split(",");
        sendCondition.put("client_name",oyTaskAudit.getOyClient().getName());
        sendCondition.put("update_date",7);

        for (int i=0;i<array.length;i++){
            if (array[i].equals("cmpp")){
                sendCondition.put("crop",array[i]);
                total=oyDataCpaService.countTotal(sendCondition);
            }
            if (array[i].equals("sgip")){
                sendCondition.put("crop",array[i]);
                total1=oyDataCpaService.countTotal(sendCondition);
            }
            if (array[i].equals("smgp")){
                sendCondition.put("crop",array[i]);
                total2=oyDataCpaService.countTotal(sendCondition);
            }
        }
        //分配发送量
        if (array.length==2){
            Map sendNum=getSendNum(oyTaskAudit.getDataSize(),array.length);
            send= (int) sendNum.get("num1");
            send1= (int) sendNum.get("num2");
        }
        if (array.length==3){
            Map sendNum=getSendNum(oyTaskAudit.getDataSize(),array.length);
            send= (int) sendNum.get("num1");
            send1= (int) sendNum.get("num2");
            send2=(int) sendNum.get("num3");
        }


        Map corpMap=new HashMap<String,Integer>();
        corpMap.put("total",total);
        corpMap.put("total1",total1);
        corpMap.put("total2",total2);

    }

    //更新数据表中的数据
    private void updateDataCpa(String s, String name,String crop) {
        Map updateMap=new HashMap();
        List phone=new ArrayList<String>();
        phone.add(s);
        updateMap.put("crop",crop);
        updateMap.put("client_name",name);
        updateMap.put("phone",phone);

        int result=oyDataCpaService.batchUpdate(updateMap);
    }

    //随机分配短信发送量
    private Map getSendNum(int dataSize, int corpCount) {
        Map sendNum=new HashMap();
        Random rand = new Random();
        int a=0,b=0,c=0;
        switch (corpCount){
            case 2:
                a=rand.nextInt(dataSize)+1;
                b=dataSize-a;
                sendNum.put("num1",a);
                sendNum.put("num2",b);
                return sendNum;
            case 3:
                a=rand.nextInt(dataSize/2)+100;
                b=rand.nextInt(dataSize/2)+100;
                c=dataSize-a-b;

                sendNum.put("num1",a);
                sendNum.put("num2",b);
                sendNum.put("num3",c);
                return sendNum;
            default:
                break;
        }
        return sendNum;
    }

    //改变运营商值
    private OyTaskAudit ChangeCorp(OyTaskAudit oyTaskAudit){
        String corp=oyTaskAudit.getCrop();
        String copyCorp=null;
        String[] array=corp.split(",");
        if (array.length==1){
            if (array[0].equals("中国移动")){
                copyCorp="cmpp";
            }else if (array[0].equals("中国联通")){
                copyCorp="sgip";
            }else if (array[0].equals("中国电信")){
                copyCorp="smgp";
            }
        }
        if (array.length==3){
            copyCorp="cmpp,sgip,smgp";
        }
        if (array.length==2){
            if (array[0].equals("中国移动") && array[1].equals("中国联通")){
                copyCorp="cmpp,sgip";
            }else if (array[0].equals("中国移动")&& array[1].equals("中国电信")){
                copyCorp="cmpp,smgp";
            }else if (array[0].equals("中国联通")&& array[1].equals("中国电信")){
                copyCorp="sgip,smgp";
            }
        }
        oyTaskAudit.setCrop(copyCorp);
        return oyTaskAudit;
    }

    //发送短信
    private Map sendMessage(OyTaskAudit oyTaskAudit, Map sendCondition) {
        Map resultMap = new HashMap();
        String platform = oyTaskAudit.getOySendAccount().getPlatform();
        String username = oyTaskAudit.getOySendAccount().getAccount();
        String password = oyTaskAudit.getOySendAccount().getPwd();
        String ipAdress = oyTaskAudit.getOySendAccount().getIpAddress();
        String msgContent = oyTaskAudit.getMessageText();

        sendPhoneNum = oyDataCpaService.findData(sendCondition);
        try {
            if (platform.equals("龙卷风")) {
                for (int i = 0; i < sendPhoneNum.size(); i++) {
                    Map m = SmsSendApi.sendLJF(username, password, ipAdress, msgContent, (String) sendPhoneNum.get(i));
                    if ((int) m.get("result") == 0) {
                        resultMap.put("result", 0);
                        resultMap.put("sendSum",i);
                        resultMap.put("wrongPhone", sendPhoneNum.get(i));
                        return resultMap;
                    }
                    updateDataCpa((String) sendPhoneNum.get(i), oyTaskAudit.getOyClient().getName(), (String) sendCondition.get("crop"));
                }
                resultMap.put("result", 1);
                return resultMap;
            } else if (platform.equals("迈远")) {
                String[] arry = username.split(",");
                for (int i = 0; i < sendPhoneNum.size(); i++) {
                    Map m = SmsSendApi.sendMY(arry[0], arry[1], password, ipAdress, msgContent, (String) sendPhoneNum.get(i));
                    if ((int) m.get("result") == 0) {
                        resultMap.put("result", 0);
                        resultMap.put("sendSum",i);
                        resultMap.put("wrongPhone", sendPhoneNum.get(i));
                        return resultMap;
                    }
                    updateDataCpa((String) sendPhoneNum.get(i), oyTaskAudit.getOyClient().getName(), (String) sendCondition.get("crop"));
                }
                resultMap.put("result", 1);
                return resultMap;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return resultMap;
    }

}
