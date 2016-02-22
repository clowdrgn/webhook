package com.blueware.web;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.blueware.entity.WebHook;
import com.blueware.util.CurSequence;
import com.blueware.util.DBManager;

/**
 * @author qinheng
 *
 * Date 2015Äê6ÔÂ7ÈÕ
 */
public class WebAdd {
	
	public static void insert(WebHook webHook){
		Connection conn=null;
		PreparedStatement pst=null;
		int id;
		try {
			conn=DBManager.getConncection();
			id=CurSequence.getSequence();
			String sql="insert into webhook(event,email_id,timestamp,url)"
					+ "values('"+webHook.getEvent()+"','"+webHook.getEmailId()+"',"+webHook.getTimeStamp()+",'"+webHook.getUrl()+"')";
			System.out.println("SQL:"+sql);
			pst=conn.prepareStatement(sql);
			pst.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				pst.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
