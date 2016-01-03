package com.xingfugo.business.outapi.nocardpay.pay;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.json.JSONObject;
import com.google.gson.Gson;
import com.xingfugo.business.outapi.nocardpay.utils.crypto.AES;
import com.xingfugo.business.outapi.nocardpay.utils.encoding.Base16;

public class NoCardPay {
	private String m_key = "", key = "", iv = "",username,pwd,token;
    private String post_login = "", post_pay = "";
    private static NoCardPay nocardpay = null;
    public String msg = "";
    boolean Is_Success = false;
    private NoCardPay(String m_key,String post_login,String post_pay,String username,String pwd)
    {
        this.m_key = m_key;
        this.post_login = post_login;
        this.post_pay = post_pay;
        this.username = username;
        this.pwd = pwd;
    }
    long tokenTime;

    public boolean GetTokenIsFailure() throws Exception
    {
    	boolean b = false;
        if (Is_Success)
        {
            long dt2=20*60*60*1000+tokenTime;
            long dt1 = System.currentTimeMillis();
            if (dt1<dt2) b = true;
            else
            {
                Is_Success = false;
                getToken();
                if (Is_Success)
                {
                    b = GetTokenIsFailure();
                }
            }
        }
        else {
            Is_Success = false;
            getToken();
            if (Is_Success)
            {
                b = GetTokenIsFailure();
            }
        }
        return b;
    }
   
    private void getToken()throws Exception
    {
    	Map<String, String> PostVars=new HashMap<String,String>();
        PostVars.put("username", username);
        PostVars.put("userpwd", pwd);
        StringBuilder result=new StringBuilder();
        int rst=HttpHelper.Post(PostVars, post_login, result);
        if (rst==200)
        {
            if (!result.toString().isEmpty())
            {
            	JSONObject jsonObj = new JSONObject(result.toString());
                String status = jsonObj.getString("status");
                if ("1".equals(status))
                {

                    token = jsonObj.getString("token");
                    AES aes=new AES();
                    aes.init(Base16.decode(m_key),Base16.decode(m_key));
                    key =new String(aes.decrypt(Base16.decode(jsonObj.getString("key"))),Sys.CHATSET).trim();
                    iv =new String(aes.decrypt(Base16.decode(jsonObj.getString("iv"))),Sys.CHATSET).trim();
                    tokenTime = System.currentTimeMillis();
                    Is_Success = true;
                }
                else
                {
                    Is_Success = false;
                    msg = jsonObj.getString("msg");
                }
                 

            }
        }
    }

    public static NoCardPay NewObj(String m_key, String post_login, String post_pay, String username, String pwd)
    {
        if (nocardpay == null)
        {
            nocardpay = new NoCardPay(m_key, post_login, post_pay, username, pwd);
            try {
				nocardpay.getToken();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        return nocardpay;
    }


    public boolean Pay(String cmd,String orderId,String cardno, String des_pin,String money, String cv2, String yymm, String phone, String sfzh, String name,String smsphone,String sms,StringBuilder result,String serialnumber) throws Exception
    {
        boolean rst = false;
        Map<String, String> map=new HashMap<String,String>();
        map.put("trk2", cardno);
        map.put("des_pin", des_pin);
        map.put("orderId", orderId);
        map.put("tranType", "021000");
        map.put("yymm", yymm);
        map.put("money", money);
        map.put("phone", phone);
        map.put("cv2", cv2);
        map.put("serialnumber", serialnumber);
        
        map.put("name", name);
        map.put("sfzh", sfzh);
        map.put("type", "BJ");
        map.put("smsphone", smsphone);
        map.put("sms", sms);
        Gson gson = new Gson();
        String jsonText = gson.toJson(map);
        
        Map<String, String> PostVars=new HashMap<String,String>();
        PostVars.put("token", token);
        PostVars.put("cmd", cmd);
        AES aes=new AES();
        aes.init(Base16.decode(key),Base16.decode(iv));
        jsonText = Base16.encode(aes.encrypt(jsonText.getBytes(Sys.CHATSET)));
        PostVars.put("jsonMap", jsonText);
        int rstnum=HttpHelper.Post(PostVars, post_pay, result);
        if (rstnum==200)
        {
            rst = true;
            JSONObject jsonObj = new JSONObject(result.toString());
            String status = jsonObj.getString("status");
            if ("-1".equals(status))
            {
                Is_Success = false;
            }
        }
        return rst;
    }

    
    public static String getUUID()
    {
    	return UUID.randomUUID().toString().replace("-","");
    }
}

