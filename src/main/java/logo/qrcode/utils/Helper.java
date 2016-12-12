package logo.qrcode.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Date;
import java.util.Map;


public class Helper {
	
	
	public static final int AIR_FILTER_ID = 698 ; 
	public static final int PLACE_BUY = 0 ; 
	
	public static final String YYYY_MM_DD_HH_MM_SS_WORD = "yyyy年MM月dd日 HH时mm分ss秒" ;
	public static final String YYYY_MM_DD_WORD = "yyyy年MM月dd日" ;
	public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss" ;  
	public static final String YYYY_MM_DD = "yyyy-MM-dd" ;   
	
	
	public static final String WXOPENIDKEY = "WXOPENIDKEY";
	
	public static final String WXNEWTHEOWNER = "WXNEWTHEOWNER";
	
	
	public static final String ACCESS_TOKEN = "ACCESS_TOKEN";
	
	public static final String ACCESS_TOKEN_FILE = "access_token.so";
	
	public static final String SERVER_HOT_LINE = "4006562230";
	
//	public static String getBasepath(HttpServletRequest request) {
//		String path = request.getContextPath();
//		String basePath = request.getScheme() + "://" + request.getServerName()
//				+ ":" + request.getServerPort() + path + "/";
//
//		return basePath ;
//	}
	
	
	
	
	/**
	 * 判断String类型是否为空 
	 * */
	public static boolean isNull(String value){
		if(value == null){
			return true ;
		}
		if(value.trim().length() == 0){
			return true ;
		}
		return false ;
	}
	
	
	public static boolean isNull(Object value){
		if(value == null){
			return true ; 
		}
		if(value.getClass().isArray()){
			if(Array.getLength(value) == 0){
				return true ; 
			}
		}
		if(value instanceof Collection<?>){
			Collection<?> collection = (Collection<?>) value ;
			if(collection.isEmpty()){
				return true ;
			}
		}else if(value instanceof Map<?, ?>){
			Map<?, ?> map = (Map<?, ?>) value ;
			if(map.isEmpty()){
				return true ;
			}
		}else if(value instanceof String){
			String string = (String) value ;
			return isNull(string) ;
		}
		return false; 
	}
	
	
	public static String checkNull(Object val){
		if(val == null){
			return "" ;
		}
		return val.toString().trim() ; 
	}
	
	public static int toInt(Object value){
		if(value == null){
			return 0 ;
		}
		if(value instanceof Number){
			Number number = (Number) value ;
			return number.intValue() ;
		}
		try {
			return Integer.parseInt(value.toString()) ;
		} catch (Exception e) {
			return 0;
		}
	}
	
	public static String format(int num , int length){
		StringBuffer buffer = new StringBuffer(Integer.toString(num));
		while(buffer.length() < length){
			buffer.insert(0, "0") ;
		}
		return buffer.toString(); 
	}
	
	
	
	
	public static String readFile(){
		try {
			File f = new File(ACCESS_TOKEN_FILE);
			if(!f.exists()){
				try {
					f.createNewFile();
					System.out.println(f.getAbsolutePath());
				} catch (IOException e) {
				}
			}
			InputStreamReader br1 = new InputStreamReader(new FileInputStream(f));
			BufferedReader br2 = new BufferedReader(br1);
			String s = "";
			String str = br2.readLine();
			while(str!=null){
				//System.out.println(str);
				s = s + str;
				str = br2.readLine();
			}
			br2.close();
			br1.close();
			return s;
		} catch (FileNotFoundException e) {
			return "";
		} catch (IOException e) {
			return "";
		}
	}
	public static void writeFile(String text){
		try {
			
			File f = new File(ACCESS_TOKEN_FILE);
			if(!f.exists()){
				try {
					f.createNewFile();
				} catch (IOException e) {
				}
			}
			OutputStreamWriter bw1 = new OutputStreamWriter(new FileOutputStream(f));
			BufferedWriter bw2 = new BufferedWriter(bw1);
			bw2.write(text);
			bw2.flush();
			bw2.close();
			bw1.close();
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}
	}

	
	public static java.sql.Timestamp geTimestamp(){
		return new java.sql.Timestamp(new Date().getTime() ) ;
	}
	
//
//	public static void showMessage(javax.servlet.http.HttpServletRequest req,javax.servlet.http.HttpServletResponse resp , String msg , String button){
//		req.setAttribute("message", msg ) ;
//		req.setAttribute("button", button ) ;
//
//		String href = Helper.getBasepath(req) + req.getServletPath() + "?" +  Helper.checkNull(req.getQueryString() ) ;
//
//		req.setAttribute("href", href ) ;
//
//		forword(req , resp , "messages/messages.jsp" ) ;
//	}
	
	
//	public static void forword(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response,String page){
//		try {
//			page = page.startsWith("/") ? page : "/" + page ;
//
//			request.getRequestDispatcher( page ).forward(request, response ) ;
//		} catch (Exception e) {
//		}
//	}
	
//	public static void sendRedirect(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response,String page){
//		try {
//			page = page.startsWith("/") ? page : "/" + page ;
//
//			String contextPath = request.getContextPath() ;
//			if(!"/".equals(contextPath)){
//				page = contextPath + page ;
//			}
//			response.sendRedirect(page);
//		} catch (Exception e) {
//		}
//	}
	
//	public static boolean isPC(HttpServletRequest request){
//		try{
//		String user_agent = request.getHeader("user-agent") ;
//		if(user_agent == null){
//			return false ;
//		}
//
//		if(user_agent.indexOf("Opera") != -1){
//			if(user_agent.indexOf("Android") != -1){
//				return false ;
//			}
//		}
//		if(user_agent.indexOf("WPDesktop") != -1){
//			return false ;
//		}
//		if(user_agent.indexOf("Nokia") != -1){
//			return false ;
//		}
//
//		if(user_agent.indexOf("iPad") != -1){
//			return true ;
//		}
//
//		if(user_agent.indexOf("iPhone") != -1){
//			return false ;
//		}
//		if(user_agent.indexOf("Mobile") != -1){
//			return false ;
//		}
//
//		if(user_agent.indexOf("WAP") != -1){
//			return false ;
//		}
//
//		}catch(Exception e){
//		}
//		return true ;
//	}
}
