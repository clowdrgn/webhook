package com.blueware.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



/**
 * @author qinheng
 *
 * Date 2015Äê6ÔÂ7ÈÕ
 */
public class CurSequence {
	public static int getSequence(){
		Connection conn=null;
		PreparedStatement pst=null;
		try {
			conn=DBManager.getConncection();
			String sql="SELECT NEXTVAL('Seq');";
			pst=conn.prepareStatement(sql);
			System.out.println("****PST:"+pst);
			ResultSet rs=pst.executeQuery();
			int count = 0;  
	        while (rs.next()) {  
	            count = rs.getInt(1);  
	        }  
	        System.out.println(count);
			return count;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 1;
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
