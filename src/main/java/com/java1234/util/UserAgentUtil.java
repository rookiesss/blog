package com.java1234.util;

import com.java1234.entity.Visit;

public class UserAgentUtil {
    public static Visit getUserAgent(String userAgent) {
        if(userAgent==""||userAgent==null){
            userAgent="";
        }
        if (userAgent.contains("Windows")) {//����Ӧ�ÿ�ǰ
            if (userAgent.contains("Windows NT 10.0")) {//Windows 10
                return judgeBrowser(userAgent, "Windows 10");//�ж������
            } else if (userAgent.contains("Windows NT 6.2")) {//Windows 8
                return judgeBrowser(userAgent, "Windows 8");//�ж������
            } else if (userAgent.contains("Windows NT 6.1")) {//Windows 7
                return judgeBrowser(userAgent, "Windows 7" );
            } else if (userAgent.contains("Windows NT 6.0")) {//Windows Vista
                return judgeBrowser(userAgent, "Windows Vista");
            } else if (userAgent.contains("Windows NT 5.2")) {//Windows XP x64 Edition
                return judgeBrowser(userAgent, "Windows XP");
            } else if (userAgent.contains("Windows NT 5.1")) {//Windows XP
                return judgeBrowser(userAgent, "Windows XP");
            } else if (userAgent.contains("Windows NT 5.01")) {//Windows 2000, Service Pack 1 (SP1)
                return judgeBrowser(userAgent, "Windows 2000");
            } else if (userAgent.contains("Windows NT 5.0")) {//Windows 2000
                return judgeBrowser(userAgent, "Windows 2000");
            } else if (userAgent.contains("Windows NT 4.0")) {//Microsoft Windows NT 4.0
                return judgeBrowser(userAgent, "Windows NT 4.0");
            } else if (userAgent.contains("Windows 98; Win 9x 4.90")) {//Windows Millennium Edition (Windows Me)
                return judgeBrowser(userAgent, "Windows ME");
            } else if (userAgent.contains("Windows 98")) {//Windows 98
                return judgeBrowser(userAgent, "Windows 98");
            } else if (userAgent.contains("Windows 95")) {//Windows 95
                return judgeBrowser(userAgent, "Windows 95");
            } else if (userAgent.contains("Windows CE")) {//Windows CE
                return judgeBrowser(userAgent, "Windows CE");
            }
        } else if (userAgent.contains("Mac OS X")) {
            if(userAgent.contains("iPhone")){
                return judgeBrowser(userAgent, "iPhone");
            }
            else if (userAgent.contains("iPad")) {
                return judgeBrowser(userAgent, "iPad");//�ж�ϵͳ
            }else{
                return judgeBrowser(userAgent, "Mac");//�ж�ϵͳ
            }
        }else if(userAgent.contains("Android")){
            return judgeBrowser(userAgent, "Android");//�ж�ϵͳ
        }else if(userAgent.contains("Linux")){
            return judgeBrowser(userAgent, "Linux");//�ж�ϵͳ
        }else if(userAgent.contains("FreeBSD")){
            return judgeBrowser(userAgent, "FreeBSD");//�ж�ϵͳ
        }else if(userAgent.contains("Solaris")){
            return judgeBrowser(userAgent, "Solaris");//�ж�ϵͳ
        }
        return judgeBrowser(userAgent, "����");
    }

    private static Visit judgeBrowser(String userAgent, String platformType) {
        if (userAgent.contains("Edge")) {
            return new Visit("Microsoft Edge", platformType);
        }else if(userAgent.contains("QQBrowser")){
            return new Visit("��Ѷ�����", platformType);
        }else if (userAgent.contains("Chrome")&&userAgent.contains("Safari")) {
            return new Visit("Chrome", platformType);
        } else if (userAgent.contains("Firefox")) {
            return new Visit("Firefox",platformType);
        }else if (userAgent.contains("360")) {//Internet Explorer 6
            return new Visit("360�����", platformType);
        }else if (userAgent.contains("Opera")) {//Internet Explorer 6
            return new Visit("Opera", platformType);
        }else if (userAgent.contains("Safari")&&!userAgent.contains("Chrome")) {//Internet Explorer 6
            return new Visit("Safari", platformType);
        }else if (userAgent.contains("MetaSr1.0")) {//Internet Explorer 6
            return new Visit("�ѹ������", platformType);
        }else if (userAgent.contains("TencentTraveler")) {//Internet Explorer 6
            return new Visit("��Ѷ�����", platformType);
        }else if (userAgent.contains("UC")) {//Internet Explorer 6
            return new Visit("UC�����", platformType);
        }else if (userAgent.contains("MSIE")) {
            if (userAgent.contains("MSIE 10.0")) {//Internet Explorer 10
                return new Visit("IE 10", platformType);
            } else if (userAgent.contains("MSIE 9.0")) {//Internet Explorer 9
                return new Visit("IE 9", platformType);
            } else if (userAgent.contains("MSIE 8.0")) {//Internet Explorer 8
                return new Visit("IE 8", platformType);
            } else if (userAgent.contains("MSIE 7.0")) {//Internet Explorer 7
                return new Visit("IE 7", platformType);
            } else if (userAgent.contains("MSIE 6.0")) {//Internet Explorer 6
                return new Visit("IE 6", platformType);
            }
        } else {//��ʱ֧��������������.���������,����...
            return new Visit("����", platformType);
        }
        return new Visit("����", platformType);
    }
}
