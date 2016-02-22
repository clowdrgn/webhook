package com.blueware.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.blueware.entity.PostJson;
import com.blueware.entity.WebHook;
import com.blueware.util.Verify;
import com.blueware.web.WebAdd;
import com.blueware.web.WebCore;
import com.blueware.web.WebQuey;
import com.blueware.web.WebUpdate;

/**
 * @author qinheng
 *
 * Date 2015��6��7��
 */
public class ServletTest extends HttpServlet{
	
	private static final String APPKEY="XdtATiOr6caIOPfW";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
//		StringBuffer param = new StringBuffer();
//		Map map=req.getParameterMap();
//	    Set keSet=map.entrySet();
	    PostJson postJson=PostJson.getInstance();
	    WebHook webHook=WebHook.getInstance();
//	    for(Iterator itr=keSet.iterator();itr.hasNext();){
//	        Map.Entry entry=(Map.Entry)itr.next();
//	        String paramName=(String)entry.getKey();
//	        String[] paramValue=(String[])entry.getValue();
//	        if(param.toString().equals("")){
//	        	param.append("?"+paramName+"="+paramValue[0]);
//	        }else{
//	        	param.append("&"+paramName+"="+paramValue[0]);
//	        	if(paramName.equals("event")){
//	        		postJson.setEvent(paramValue[0]);
//	        	}
//	        	if(paramName.equals("recipient")){
//	        		postJson.setRecipient(paramValue[0]);
//	        	}
//	        	if(paramName.equals("token")){
//	        		postJson.setToken(paramValue[0]);
//	        	}
//	        	if(paramName.equals("timestamp")){
//	        		postJson.setTimestamp(Long.parseLong(paramValue[0]));
//	        	}
//	        	if(paramName.equals("signature")){
//	        		postJson.setSignature(paramValue[0]);
//	        	}
//	        	if(paramName.equals("labelId")){
//	        		postJson.setLabelid(Integer.parseInt(paramValue[0]));
//	        	}
//	        	if(paramName.equals("url")){
//	        		postJson.setLabelid(Integer.parseInt(paramValue[0]));
//	        	}
//	        	
//	        }
//	    }
	    
		String event =req.getParameter("event");
//		String  request=;
//		String  click=;
		String  unsubscribe=req.getParameter("unsubscribe");
		String  recipient=req.getParameter("recipient");
		String  emailId=req.getParameter("emailId");
		Long  timestamp=Long.parseLong(req.getParameter("timestamp"));
		String url="www.baidu.com";
		postJson.setEvent(event);
		postJson.setRecipient(recipient);
		postJson.setUrl("www.bukaixin.com");
	    
		

		if(event.equals("open") && event!=null){
			boolean s=WebQuey.SearchByEmailId(emailId,event);
			if(s){
				System.out.println("--------------already exists");
			}else{
				webHook.setEvent("open");
				webHook.setEmailId(emailId);
				webHook.setTimeStamp(timestamp);
				webHook.setOpen(1);
				webHook.setClick(0);
				webHook.setUnsubscribe(0);
				webHook.setEmail(recipient);
				webHook.setInvalid(0);
				webHook.setDate("NOW()");
				webHook.setUrl(null);
				WebAdd.insert(webHook);
			}
			
		}else if(event.equals("click")){
//			boolean s=WebQuey.SeartEmail(recipient,labelid);
			boolean s=WebQuey.IsTrue(emailId, event, url);
			if(s){
				System.out.println("--------------already exists");
			}else{
				webHook.setEvent("click");
				webHook.setEmailId(emailId);
				webHook.setTimeStamp(timestamp);
				webHook.setOpen(0);
				webHook.setClick(1);
				webHook.setUnsubscribe(0);
				webHook.setEmail(recipient);
				webHook.setDate("NOW()");
				webHook.setInvalid(0);
				webHook.setUrl(url);
				WebAdd.insert(webHook);
			}
			
		}else if(event.equals("unsubscribe")){
			boolean s=WebQuey.SearchByEmailId(emailId,event);
			if(s){
				System.out.println("--------------already exists");
			}else{
				webHook.setEvent("unsubscribe");
				webHook.setEmailId(emailId);
				webHook.setTimeStamp(timestamp);
				webHook.setOpen(0);
				webHook.setClick(0);
				webHook.setUnsubscribe(1);
				webHook.setEmail(recipient);
				webHook.setDate("NOW()");
				webHook.setInvalid(0);
				WebAdd.insert(webHook);
			}
		}else if(event.equals("invalid")){
			boolean s=WebQuey.SearchByEmailId(emailId,event);
			if(s){
				System.out.println("--------------already exists");
			}else{
				webHook.setEvent("invalid");
				webHook.setEmailId(emailId);
				webHook.setTimeStamp(timestamp);
				webHook.setOpen(0);
				webHook.setClick(0);
				webHook.setUnsubscribe(0);
				webHook.setEmail(recipient);
				webHook.setDate("NOW()");
				webHook.setInvalid(1);
				webHook.setUrl(null);
				WebAdd.insert(webHook);
			}
		}else if(event.equals("report_spam")){
			boolean s=WebQuey.SearchByEmailId(emailId,event);
			if(s){
				System.out.println("--------------already exists");
			}else{
				webHook.setEvent("report_spam");	
				webHook.setEmailId(emailId);
				webHook.setTimeStamp(timestamp);
				webHook.setEmail(recipient);
				webHook.setDate("NOW()");					
				webHook.setUrl(null);
				WebAdd.insert(webHook);
			}
		}else if(event.equals("bounce")){
			boolean s=WebQuey.SearchByEmailId(emailId,event);
			if(s){
				System.out.println("--------------already exists");
			}else{
				webHook.setEvent("bounce");			
				webHook.setEmailId(emailId);
				webHook.setTimeStamp(timestamp);
				webHook.setEmail(recipient);
				webHook.setDate("NOW()");					
				webHook.setUrl(null);
				WebAdd.insert(webHook);
			}
		}else {
			PrintWriter writer = resp.getWriter();
	        writer.write("misssing");
		}
		PrintWriter writer = resp.getWriter();
		writer.write("OK");
		
		
		
		
		
//		String token=postJson.getToken();
//		long timestamp=postJson.getTimestamp();
//		String signature=postJson.getSignature();
//		int labelid=postJson.getLabelid();
	    
//	    try {
//	    	boolean s=Verify.verify(APPKEY, token, timestamp, signature);
//	    	if(s){
//	    	System.out.println("*********:"+postJson);
//	    		WebCore.getcCore(postJson,webHook);
//	    	}else{
//	    		PrintWriter writer = resp.getWriter();
//		        writer.write("verify fail!!!");
//	    	}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
	}
	

}
