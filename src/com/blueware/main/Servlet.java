package com.blueware.main;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

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
 * Date 2015Äê6ÔÂ7ÈÕ
 */
public class Servlet extends HttpServlet{
	
//	private static final String APPKEY="kpq61m0m-ps7w-bxqn-vfbe-n3cycz1182";
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
		
		StringBuffer param = new StringBuffer();
		Map map=req.getParameterMap();
	    Set keSet=map.entrySet();
	    PostJson postJson=PostJson.getInstance();
	    WebHook webHook=WebHook.getInstance();
	    for(Iterator itr=keSet.iterator();itr.hasNext();){
	        Map.Entry entry=(Map.Entry)itr.next();
	        String paramName=(String)entry.getKey();
	        String[] paramValue=(String[])entry.getValue();
	        if(param.toString().equals("")){
	        	param.append("?"+paramName+"="+paramValue[0]);
	        }else{
	        	param.append("&"+paramName+"="+paramValue[0]);
	        	if(paramName.equals("event")){
	        		postJson.setEvent(paramValue[0]);
	        	}
	        	if(paramName.equals("recipient")){
	        		postJson.setRecipient(paramValue[0]);
	        	}
	        	if(paramName.equals("token")){
	        		postJson.setToken(paramValue[0]);
	        	}
	        	if(paramName.equals("timestamp")){
	        		System.out.println("***+++timestamp-START+++***:"+paramValue[0]);
	        		postJson.setTimestamp(Long.parseLong(paramValue[0]));
	        		System.out.println("***+++timestamp-END+++++***:"+postJson.getTimestamp());
	        	}
	        	if(paramName.equals("signature")){
	        		postJson.setSignature(paramValue[0]);
	        	}
	        	if(paramName.equals("labelId")){
	        		postJson.setLabelid(Integer.parseInt(paramValue[0]));
	        	}
	        	if(paramName.equals("emailId")){
	        		postJson.setEmailId(paramValue[0]);
	        	}
	        	if(paramName.equals("url")){
	        		System.out.println("****URL****:"+paramValue[0]);
	        		postJson.setUrl(paramValue[0]);
	        		System.out.println("****URL-psotJson****:"+postJson.getUrl());
	        	}
	        	
	        }
	    }
	    
	    System.out.println("*****11111111111111111111111111111111111***");
	    System.out.println("*************START*****************");
	    
		System.out.println("POST-PARAM:"+param.toString());
		System.out.println("event:"+postJson.getEvent());
		System.out.println("emailId:"+postJson.getEmailId());
		System.out.println("recipient:"+postJson.getRecipient());
		System.out.println("token:"+postJson.getToken());
		System.out.println("timestamp:"+postJson.getTimestamp());
		System.out.println("signature:"+postJson.getSignature());
		System.out.println("labelId:"+postJson.getLabelid());
		System.out.println("url:"+postJson.getUrl());
		System.out.println("TOKEN:"+postJson.getToken());
		System.out.println("TIMESTAMP:"+postJson.getTimestamp());
		System.out.println("SIGNATURE:"+postJson.getSignature());
		
		System.out.println("**************END******************");
		
		
		
		
		
		String token=postJson.getToken();
		String signature=postJson.getSignature();
		String event=postJson.getEvent();
		String recipient=postJson.getRecipient();
		int labelid=postJson.getLabelid();
		String url=postJson.getUrl();
		long timestamp=postJson.getTimestamp();
		String emailId = postJson.getEmailId();
		
		
		
		
		
		

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
				webHook.setLabelid(labelid);
				webHook.setInvalid(0);
				webHook.setDate("NOW()");
				webHook.setUrl(null);
				WebAdd.insert(webHook);
			}
			
		}else if(event.equals("click")){
//			boolean s=WebQuey.SeartEmail(recipient,labelid);
			boolean s=WebQuey.IsTrue(emailId,event,url);
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
				webHook.setLabelid(labelid);
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
				webHook.setLabelid(labelid);
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
				webHook.setLabelid(labelid);
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
				webHook.setLabelid(labelid);
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
				webHook.setLabelid(labelid);
				webHook.setUrl(null);
				WebAdd.insert(webHook);
			}
		}else {
			PrintWriter writer = resp.getWriter();
	        writer.write("misssing");
		}
		PrintWriter writer = resp.getWriter();
		writer.write("OK");
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	    
//	    try {
//	    	boolean s=Verify.verify(APPKEY, token, timestamp, signature);
//	    	System.out.println("**********VERIFY-START************");
//	    	System.out.println("APPKEY:"+APPKEY);
//	    	System.out.println("token:"+token);
//	    	System.out.println("timestamp:"+timestamp);
//	    	System.out.println("signature:"+signature);
//	    	System.out.println("VERIFY_IS:"+s);
//	    	System.out.println("**********VERIFY-END************");
//	    	if(s){
//	    		WebCore.getcCore(postJson,webHook);
//	    	}else{
//	    		System.out.println("STATUS:VERIFY FAIL");
//	    		PrintWriter writer = resp.getWriter();
//		        writer.write("verify fail!!!");
//	    	}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
	}
	

}
