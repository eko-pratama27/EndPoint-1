<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>LoginPost</name>
   <tag></tag>
   <elementGuidId>fc7fe855-b056-4294-83ec-1aaffe2ab481</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <followRedirects>true</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent>{
  &quot;text&quot;: &quot;&quot;,
  &quot;contentType&quot;: &quot;text/plain&quot;,
  &quot;charset&quot;: &quot;UTF-8&quot;
}</httpBodyContent>
   <httpBodyType>text</httpBodyType>
   <migratedVersion>5.4.1</migratedVersion>
   <restRequestMethod>POST</restRequestMethod>
   <restUrl>http://qa-interview.srcli.xyz/login?username=${user}&amp;password=${pass}</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceFunction></soapServiceFunction>
   <variables>
      <defaultValue>'${GlobalVariable.uname}'</defaultValue>
      <description></description>
      <id>48f01bfb-f7e8-4055-954d-8ce870b26ac8</id>
      <masked>false</masked>
      <name>user</name>
   </variables>
   <variables>
      <defaultValue>'${GlobalVariable.passWord}'</defaultValue>
      <description></description>
      <id>5f876bd2-b962-4f02-9685-b41837dedc5d</id>
      <masked>false</masked>
      <name>pass</name>
   </variables>
   <verificationScript>import static org.assertj.core.api.Assertions.*

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webservice.verification.WSResponseManager

import groovy.json.JsonSlurper
import internal.GlobalVariable as GlobalVariable

RequestObject request = WSResponseManager.getInstance().getCurrentRequest()

ResponseObject response = WSResponseManager.getInstance().getCurrentResponse()






</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
