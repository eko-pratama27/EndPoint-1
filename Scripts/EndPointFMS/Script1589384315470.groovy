
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static org.assertj.core.api.Assertions.*

import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import internal.GlobalVariable as GlobalVariable

import groovy.json.JsonSlurper

try{
	/*
	 * Declare Variable
	 * */
	'session if user already login'
	def cookies = "username=root; path=/; domain=qa-interview.srcli.xyz"
	GlobalVariable.uname = username
	GlobalVariable.passWord = pass
	GlobalVariable.filterStart = filStrart
	GlobalVariable.filterEnd = filEnd
	GlobalVariable.type = type.toString()

	/**
	 * GET MAIN PAGE
	 * */
	'Obejct MainPage'
	ResponseObject mainPage = WS.sendRequest(findTestObject("MainPage"))
	'Validate response 200'
	WS.verifyResponseStatusCode(mainPage, 200)
	assertThat(mainPage.getStatusCode()).isEqualTo(200)
	'Mark pass'
	KeywordUtil.markPassed("Main page is present")
	
	
	/**
	 * GET LOGIN PAGE
	 * typesession is set session by data test
	 * */
	'GET LOGIN PAGE'
	def typesession = GlobalVariable.type
	'validate user already login using data test'
	if (typesession == "1"){
		'set session'
		GlobalVariable.session = cookies
		'Object Post Login'
		ResponseObject getLogin = WS.sendRequest(findTestObject("Object Repository/login"))
		'Validate response 200'
		WS.verifyResponseStatusCode(getLogin, 200)
		assertThat(getLogin.getStatusCode()).isEqualTo(200)
		'Get Response type'
		def getLoginResponse = getLogin.getResponseText()
		
		//println getLoginResponse
		'validate already login redirect to main page'
		if(getLoginResponse.contains("Welcome!")){
			'Mark success Login'
			KeywordUtil.markPassed("MainPage redirect")
		}else{
			'error Login Not found'
			KeywordUtil.markFailed("MainPage not redirect")
		}
		
	}else{
		'validate user not yet login' 
		
		'Object Get Login'
		ResponseObject getLogin = WS.sendRequest(findTestObject("Object Repository/login"))
		'Validate response 200'
		WS.verifyResponseStatusCode(getLogin, 200)
		assertThat(getLogin.getStatusCode()).isEqualTo(200)
		'Get response text'
		def getLoginResponse = getLogin.getResponseBodyContent()
		//println getLoginResponse
		'validate login page display'
		if(getLoginResponse.contains("Username") && getLoginResponse.contains("Password")){
			'Mark success Login Display'
			KeywordUtil.markPassed("Login Page Display")
		}else{
			'error Login Not found'
			KeywordUtil.markFailed("Login Not Page Display")
		}
	}
	

	
	/**
	 * POST LOGIN
	 * 
	 * */
	'Object Login Post'	
	ResponseObject postLogin = WS.sendRequest(findTestObject("Object Repository/LoginPost"))
	'Get Response text'
	def response = postLogin.getResponseText()
	'Get Response Code'
	def code = postLogin.getStatusCode()
	//	println response
	//	println code
	'validate user if success login redirect to mainpage'
	if (code == 200 && response.contains("Welcome!")){
		
		assertThat(postLogin.getStatusCode()).isEqualTo(200)
		'Mark pass redirect Mainpage'
		KeywordUtil.markPassed("Login Berhasil")
		
	}else{
	 'error user wrong'
		assertThat(postLogin.getStatusCode()).isEqualTo(code)
		'Mark pass'
		KeywordUtil.markFailed(response)
	}
	
	
	
	/**
	 * GET LOGOUT
	 * */
	ResponseObject logOut = WS.sendRequest(findTestObject("Object Repository/Logout"))
	'Validate response 200'
	WS.verifyResponseStatusCode(logOut, 200)
	assertThat(logOut.getStatusCode()).isEqualTo(200)
	def logoutResponse = logOut.getResponseBodyContent()
	'Mark pass'
	KeywordUtil.markPassed("LogOut Succesfully " + logoutResponse)

	/**
	 * GET DATA LIST
	 * typesessionData is set session by data test
	 * */
	
	def typesessionData= GlobalVariable.type
		if (typesessionData == "1"){
			'already login'
			GlobalVariable.session = cookies
			ResponseObject getData = WS.sendRequest(findTestObject("Object Repository/DataList"))
			'Validate response 200'
			WS.verifyResponseStatusCode(getData, 200)
			assertThat(getData.getStatusCode()).isEqualTo(200)
			'Get Response Body text'
			def getDatatxt = getData.getResponseText()
			//println getDatatxt
			'Validate datalist display'
			if(getDatatxt.contains("Pemasukan") && getDatatxt.contains("Pengeluaran")){
				'Mark Success data list display'
				KeywordUtil.markPassed("Datalist Displayed")
			}else{
				'error not found'
				KeywordUtil.markFailed("DataList Not Displayed")
			}
		
		}else{
			'Not yet Login'
			ResponseObject getData = WS.sendRequest(findTestObject("Object Repository/DataList"))
			'Validate response 200'
			WS.verifyResponseStatusCode(getData, 200)
			assertThat(getData.getStatusCode()).isEqualTo(200)
			'Get Response Body text'
			def getDatatxt = getData.getResponseBodyContent()
			'Validate redirect Login page display'
			if(getDatatxt.contains("Username") && getDatatxt.contains("Password")){
				'Mark Success Login Page Display'
				KeywordUtil.markPassed("Login Page Display Redirect GET Data List")
			}else{
				'error not found'
				KeywordUtil.markFailed("Login Not Page Display")
			}
		}
	/**
	 * POST DATA LIST
	 * typesessionDataPost is set session by data test
	 * */
	
	def typesessionDataPost= GlobalVariable.type
	println typesessionDataPost
		if (typesessionDataPost == "1"){
			'already login'
			GlobalVariable.session = cookies
			ResponseObject getFilter = WS.sendRequest(findTestObject("Object Repository/DataListPost"))
			'Get Response text'
			def filterRes = getFilter.getResponseText()
			'Get Response Code'
			def filterCode = getFilter.getStatusCode()	
			
			'Validate datalist display'
			if(filterCode == 200){
				'Mark Success data list display'
				KeywordUtil.markPassed(filterRes)
			}else{
				'Filter Param wrong'
				KeywordUtil.markFailed(filterRes)
			}
		
		}else{
			'Not yet Login'
			ResponseObject getData = WS.sendRequest(findTestObject("Object Repository/DataListPost"))
			'Validate response 200'
			WS.verifyResponseStatusCode(getData, 200)
			assertThat(getData.getStatusCode()).isEqualTo(200)
			'Get Response Body text'
			def getDatatxt = getData.getResponseBodyContent()
			'Validate redirect Login page display'
			if(getDatatxt.contains("Username") && getDatatxt.contains("Password")){
				'Mark Success redirect Login Page Display'
				KeywordUtil.markPassed("Login Page Display Redirect POST Data List")
			}else{
				'error not found'
				KeywordUtil.markFailed("Login Not Page Display")
			}
		}
		
}catch (Exception ex) {
	println ex
	
}