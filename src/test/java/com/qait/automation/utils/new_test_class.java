package com.qait.automation.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Pattern;
import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.params.HttpParams;

@SuppressWarnings({ "unused", "deprecation" })

public class new_test_class {

	public static CloseableHttpClient httpclient = HttpClients.createDefault();
	
	public static void main(String[] args) throws IllegalStateException, IOException, InterruptedException {
		String getUrl="http://beta.ops.politico.com/cms/";

		String cookieStr = "";
		Thread.sleep(1000); 
		HttpGet httpGet = new HttpGet(getUrl);

		HttpParams params = httpGet.getParams();
		params.setParameter(ClientPNames.HANDLE_REDIRECTS, Boolean.TRUE);
		httpGet.setParams(params);



		CloseableHttpResponse res=httpclient.execute(httpGet);

		System.out.println("response goes here: ".toUpperCase());

		System.out.println("RESPONSE STATUS:- "  + res.getStatusLine().getStatusCode());

		Header[] headers = res.getHeaders("Set-Cookie");

		for (Header h : headers) {
			System.out.println(h.getValue().toString());
			cookieStr = cookieStr + h.getValue().toString() + ";";
		}

		System.out.println("COOKIES ARE----"+cookieStr);
		String csrfToken=getDelimitedValueFromCookie(cookieStr, "bsp.csrf").replaceAll("bsp.csrf=", "");
		System.out.println("bsp.csrf--"+csrfToken);
		httpclient.close();
		
// Post login
		
		Thread.sleep(2000);
		httpclient = HttpClients.createDefault();
		String postUrl="http://beta.ops.politico.com/cms/logIn.jsp?returnPath=%2Fcms%2Findex.jsp&username=BetaAutomationTestUser001@40yopmail.com&password=Welcome1!&_csrf="+csrfToken;
		System.out.println("POST URL: "+postUrl);
		HttpPost login=new HttpPost(postUrl);

		login.addHeader("Cookie", "bsp.csrf="+csrfToken);
		HttpParams paramsLogin = login.getParams();
		paramsLogin.setParameter(ClientPNames.HANDLE_REDIRECTS, Boolean.TRUE);
		login.setParams(paramsLogin);

		cookieStr="";
		CloseableHttpResponse resLogin=httpclient.execute(login);

		System.out.println("response goes here: ".toUpperCase());

		System.out.println("RESPONSE STATUS:- "  + resLogin.getStatusLine().getStatusCode());

		Header[] headersLogin = resLogin.getHeaders("Set-Cookie");

		for (Header h : headersLogin) {
			System.out.println(h.getValue().toString());
			cookieStr = cookieStr + h.getValue().toString() + ";";
		}

		System.out.println("Cookies from Login:"+cookieStr);
		
		String loc=getLocationFromRedirectingTransaction(resLogin);
		
		System.out.println("LOC---"+loc);
		
		printOnConsole(resLogin);
		
		httpclient.close();
		
// Index page
		

		Thread.sleep(1000);
		httpclient = HttpClients.createDefault();
		System.out.println("GET INDEX URL: "+loc);
		HttpGet getIndex=new HttpGet(loc);
		
		getIndex.addHeader("Cookie", "bsp.csrf="+csrfToken+"; "+cookieStr);

		CloseableHttpResponse resGetIndex=httpclient.execute(getIndex);

		System.out.println("response goes here: ".toUpperCase());

		System.out.println("RESPONSE STATUS:- "  + resGetIndex.getStatusLine().getStatusCode());

		Header[] headersgetIndex = resGetIndex.getHeaders("Set-Cookie");

		cookieStr="";
		
		for (int i=5;i<headersgetIndex.length;i++) {
			System.out.println(headersgetIndex[i].getValue().toString());
			cookieStr = cookieStr + headersgetIndex[i].getValue().toString() + ";";
		}
		
		cookieStr=cookieStr
				.replaceAll(" Path=/; HttpOnly;", " ")
				.replaceAll(" Domain=beta.ops.politico.com;", " ")
				.replaceAll("; Path=/;", "");

		System.out.println("COOKIES ARE----"+cookieStr);

//		printOnConsole(resGetIndex);
		
		httpclient.close();
		
// Breaking News Story Page
		
		Thread.sleep(1000);
		httpclient = HttpClients.createDefault();
		String breakingNewsUrl="http://beta.ops.politico.com/cms/content/edit.jsp?typeId=0000014d-90ac-d9bc-a36f-94ae410e0000";
		System.out.println("GET Breaking news page URL: "+breakingNewsUrl);
		
		HttpGet getStoryEditPage=new HttpGet(breakingNewsUrl);
		
		getStoryEditPage.addHeader("Cookie",cookieStr);
		
		System.out.println("COOKIES FOR STORY-------"+cookieStr);
		
		CloseableHttpResponse resGetStoryPage=httpclient.execute(getStoryEditPage);
		

		System.out.println("response goes here: ".toUpperCase());

		System.out.println("RESPONSE STATUS:- "  + resGetStoryPage.getStatusLine().getStatusCode());

		printOnConsole(resGetStoryPage);
		
		httpclient.close();
	}
	
	
	public static String getLocationFromRedirectingTransaction(CloseableHttpResponse res){
		Header[] locationHeader=res.getHeaders("Location");
		String location="";
		for(Header h:locationHeader){
			location=location+h.getValue().toString();
		}return location;
	}
	public static String getDelimitedValueFromCookie(String cookie, String token){
		final String[] tokensFromCookie = cookie.split(Pattern.quote(";"));
		for(String s:tokensFromCookie){
			if(s.contains(token)){
				return s;
			}
		}return "*****NOT FOUND******";
	}
	
	public static void printOnConsole(CloseableHttpResponse res) throws IllegalStateException, IOException{
		String line;
		InputStream input = res.getEntity().getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(input));
		while ((line = br.readLine()) != null) {
			System.out.println(line);
		}
		System.out.println("RESPONSE STATUS:- "  + res.getStatusLine().getStatusCode());
	}

}
