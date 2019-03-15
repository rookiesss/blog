package com.java1234.util;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class AddressUtil {

    public String getAddresses(String content, String encodingString) {
        String urlStr = "http://ip.taobao.com/service/getIpInfo.php";
        String returnStr = this.getResult(urlStr, content, encodingString);
        if (returnStr != null) {
            String[] temp = returnStr.split(",");
            System.out.println(returnStr);
            if (temp.length < 3) {
                return "δ֪����";
            }
            String province = (temp[4].split(":"))[1].replaceAll("\"", "");
            province = decodeUnicode(province);// ʡ��
            if(province.equals("XX")||province==""){
                province="";
            }else{
                province=province+"ʡ";
            }
            String country = (temp[2].split(":"))[1].replaceAll("\"", "");
            country = decodeUnicode(country);// ����
            if(country.equals("XX")||country==""){
                country="";
            }
            String city = (temp[5].split(":"))[1].replaceAll("\"", "");
            city = decodeUnicode(city);// ����
            if(city.equals("XX")||city==""||city.equals("����IP")){
                city="";
            }else{
                city=city+"��";
            }
            String isp = (temp[7].split(":"))[1].replaceAll("\"", "");
            isp = decodeUnicode(isp);// ISP
            if(isp.equals("XX")||isp==""){
                isp="";
            }
            String str=province + city + isp;
            if(str.equals("")||str==""){
                str=country;
            }else{
                str=province + city+ " "+isp;
            }
            return str;
        }
        return "δ֪����";
    }

    private String getResult(String urlStr,String content,String encoding) {
        URL url = null;
        HttpURLConnection connection = null;
        try {
            url = new URL(urlStr);
            connection = (HttpURLConnection) url.openConnection();// �½�����ʵ��
            connection.setConnectTimeout(2000);// �������ӳ�ʱʱ�䣬��λ����
            connection.setReadTimeout(2000);// ���ö�ȡ���ݳ�ʱʱ�䣬��λ����
            connection.setDoOutput(true);// �Ƿ������� true|false
            connection.setDoInput(true);// �Ƿ��������true|false
            connection.setRequestMethod("POST");// �ύ����POST|GET
            connection.setUseCaches(false);// �Ƿ񻺴�true|false
            connection.connect();// �����Ӷ˿�
            DataOutputStream out = new DataOutputStream(
                    connection.getOutputStream());// ����������Զ˷�����д����
            out.writeBytes(content);// д����,Ҳ�����ύ��ı� name=xxx&pwd=xxx
            out.flush();// ˢ��
            out.close();// �ر������
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(), encoding));// ���Զ�д�����ݶԶ˷�������������
            // ,��BufferedReader������ȡ
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            reader.close();
            return buffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();// �ر�����
            }
        }
        return "";
    }

    public static String decodeUnicode(String theString) {
        char aChar;
        int len = theString.length();
        StringBuffer outBuffer = new StringBuffer(len);
        for (int x = 0; x < len;) {
            aChar = theString.charAt(x++);
            if (aChar == '\\') {
                aChar = theString.charAt(x++);
                if (aChar == 'u') {
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = theString.charAt(x++);
                        switch (aChar) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                value = (value << 4) + aChar - '0';
                                break;
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                                value = (value << 4) + 10 + aChar - 'a';
                                break;
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                                value = (value << 4) + 10 + aChar - 'A';
                                break;
                            default:
                                throw new IllegalArgumentException(
                                        "Malformed      encoding.");
                        }
                    }
                    outBuffer.append((char) value);
                } else {
                    if (aChar == 't') {
                        aChar = '\t';
                    } else if (aChar == 'r') {
                        aChar = '\r';
                    } else if (aChar == 'n') {
                        aChar = '\n';
                    } else if (aChar == 'f') {
                        aChar = '\f';
                    }
                    outBuffer.append(aChar);
                }
            } else {
                outBuffer.append(aChar);
            }
        }
        return outBuffer.toString();
    }
//    public static void main(String[] args) {
//        AddressUtil addressUtils = new AddressUtil();
//        // ����ip 219.136.134.157 �й�=����=�㶫ʡ=������=Խ����=����
//        String ip = "116.255.132.11";
//        String address = "";
//        try {
//            address = addressUtils.getAddresses("ip="+ip, "utf-8");
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        System.out.println(address);
//        // ������Ϊ���㶫ʡ,������,Խ����
//    }Խ����
}
