package com.SMSverification;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * ���ö��Žӿ���
 * @author sunsetRain
 *
 */
public class SMScode {

   
	/**
	 * ���ֻ�����������֤�봫�ݹ��� 
	 * @param phoneNumber 	���ն��ŵ��ֻ�����
	 * @param code �����֤��
	 * @return 
	 * @throws Exception
	 */
    public static boolean sendCode(String phoneNumber, String code) throws Exception {

        String str_code = URLEncoder.encode("#code#=" + code, "UTF-8");
        //��װ��URL���󣬽��ӿڵ�ַ��װ�ڴ˶�����
        //�����ṩ���ⲿ�ӿڣ� http://v.juhe.cn/sms/send
        /**
         * ��url������ �ӿڵ�ַ��	�������£�
         * mobile�� ���ն��ŵ��ֻ�����  	
         * tpl_id�� ����ģ��ID   
         * tpl_value�� �������ͱ���ֵ��  
         * key�� ����AppKey
         * ����***Ϊ��Ӧ��ֵ���˴�Ϊ��ȫ����***���棬�ۺ����ݹٷ�ƽ̨�п��Բ鿴
         */
        URL url = new URL("http://v.juhe.cn/sms/send?mobile=" + phoneNumber +
                "&tpl_id=****&tpl_value=" + str_code + "&key=****");
                /* ����ģ��id */                            /* ����Ӧ�ýӿڵ�key */
        //�����ӣ��õ����Ӷ���
        URLConnection connection = url.openConnection();
        //�������������������
        connection.connect();
        //��÷�������Ӧ������
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
        StringBuffer buffer = new StringBuffer();
        String lineDate = null;
        while((lineDate = bufferedReader.readLine()) != null) {
            buffer.append(lineDate);
        }
        bufferedReader.close();
        //error_code	int	������
        if(buffer.toString().indexOf("\"error_code\":0")>=0 ) {
            return true;
        }

        return false;
    }


}
