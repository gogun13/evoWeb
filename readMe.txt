*****Connect base Dev*******
Database name : CBCD
Alias : CBCD
Authentication : Server
Host Name : 10.2.224.24
Host Operating System : AIX
Node name : CBCD
TCP/IF port number : 50018

Login: ewstadba
Password : ewstadba

Login: ewstaadm
Password : ewstaadm

Login: ewscrdba
Password : ewscrdba

Login: ewscradm
Password : ewscradm
*********************************

****������к���ҹ����ͧ����ͧ*******
http://localhost:8080/TurnAround/jsp/login.jsp
http://localhost:8080/Renewal/jsp/login.jsp
**********************************

************User Dev***************
20528/password
17197/password
4055/password
**********

*******Internet Proxy****************
http://proxy.kcs/proxy.pac
zc0006@kcs.co.th
pass : kcszc0006
**********************************

***********SVN path***************
https://10.2.62.68:8443/svn/LoanProcess/LoanProj/
user3/password

ews-m
user:pratya
password:password
**********************************

************** Add Cer *************
- ������ CBS_UAT.cer �� �������������� F:\lib\CBS_UAT.cer
- �� Domains folder �ͧ Server (GlassFish Server 4.0) ���� ���� Window > Services
- expand "Servers" > ��ԡ��ҷ�� Server > Properties
- �� Path ��� Domains folder 
- ������� C:\Users\CIMB-POC\AppData\Roaming\NetBeans\7.4\config\GF_4.0
- ���˹�� Command {cmd}
- ����� cd\
- ��������价�� Java Home ��� cd C:\Program Files\Java\jdk1.7.0_21\bin
- �������� keytool -import -trustcacerts -keystore "C:\Users\CIMB-POC\AppData\Roaming\NetBeans\7.4\config\GF_4.0\domain1\config\cacerts.jks"  -storepass changeit -alias SIT_CBSWS_CERT -file "F:\lib\CBS_UAT.cer"
***********************************
����Ѻ JBOSS
keytool -import -trustcacerts -keystore "D:\jboss-as\server\default\conf\localhost.keystore"  -storepass changeit -alias SIT_CBSWS_CERT -file "C:\Users\PoUnDZaa\Desktop\ktbcs\LOAN\CBS_UAT.cer"

keytool -import -alias SIT_CBSWS_CERT -file "C:\Program Files\Java\jdk1.6.0_45\jre\lib\security\CBS_UAT.cer"

*******************************************************
��� keytool command
https://www.sslshopper.com/article-most-common-java-keytool-keystore-commands.html
����觴����������ʹ�� cer ����ŧ�� keytool ��ҧ
keytool -list

����觴��������ʹ cer ����ŧ�� keystore.jks ��ҧ
keytool -list -v -keystore "D:\key\keystore.jks"

********************************************************
����Ѻ putty remote batch turnaround
IP 10.2.224.24
Path /home/ewstaadm/TA_BATCH
user ewstaadm/ewstaadm@


*******************************************************
SME Web sit
IP : 10.9.224.103
DB Name : DIGITD 
 
Connection
Server Name : 10.9.224.103,1437
 
User/Pass (owner):  digitdba/digitdba@

*******************************************************
[EWS-CBC] Deploy
---------------JBOSS 6-------
DEV
   - Deployment Path: /app/ewsd/jboss-eap-6.2.0/ewsd/deployments
    - URL: http://10.2.154.77:9680/
    - User: ewsadm
    - Password: ewsadm@
 
UAT
   - Deployment Path: /app/ewsu/jboss-eap-6.2.0/ewsu/deployments
    - URL: http://10.2.154.77:9780/
    - URL: https://web1.uat.ktb:32001/
    - User: ewsdm
    - Password: ewsadm@


*******************************************************
[EWS-L , EWS-M: UAT] User/Pwd path deploy

ip : 10.2.153.248
user : ewsmadm
pwd :  ewsmadm@

EWS1UAT
    - Deployment Path: /app/EWS1UAT/jboss-as/server/EWS1UAT/deploy
    - URL: http://10.2.153.248:12680/EWSL


SIT  M L WS
/app/ews5/jboss-as/server/ews/tmp

UAT  M
/app/ewsu5/jboss-as/server/ewsu/deploy


UAT L
/app/EWS1UAT/jboss-as/server/EWS1UAT/deploy
*******************************************************
EWS ������
http://10.2.154.125:9480
10.2.154.125
jbsadm/jbsadm@
/app/ewscu

(22301 �� WebServer ������¡����ͧ�����)
ip : 10.2.154.72
instance name : ews
url : http://10.2.154.72:9180
user : jbsadm
pass : jbsadm@
ews1 --->SIT

grant for run shell script
chmod 555 /bin/ksh.new


