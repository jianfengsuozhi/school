package common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式
 * @author Edward
 *
 */
public class Validate {
	 /**
	  * 验证普通点
	  * @param email
	  */
	 public static void validateDot(String email){
		 /**
		  * \\. 表示 .
		  * //. 表示 //a ，//b
		  */
		 String regex = "^\\.$";
		 Pattern pattern = Pattern.compile(regex);
		 Matcher matcher = pattern.matcher(email);
		 if(matcher.matches()){
			 System.out.println("普通点正确");
		 }else{
			 System.out.println("普通点错误");
		 }
	 }	
	 
	 /**
	  * 验证email
	  */
	 public static String validateEmail(String email){
		 // ?有没有(0,1)，+(从1开始)
		 //以字符开头，内容含有@和.
		 String regex = "^[0-9a-zA-Z]+@[0-9a-zA-Z]+\\.[0-9a-zA-Z]+$";
		 Pattern pattern = Pattern.compile(regex);
		 Matcher matcher = pattern.matcher(email);
		 if(matcher.matches()){
			  return "邮箱格式正确";
		 }else{
			 return "邮箱格式错误";
		 }
	 }
	 
	 /**
	  * 验证电话长度
	  * @param email
	  */
	 public static String validatePhone(String email){
		 String regex = "^[^0]([0-9]{10})$";
		 Pattern pattern = Pattern.compile(regex);
		 Matcher matcher = pattern.matcher(email);
		 if(matcher.matches()){
			 return "电话格式正确(11位)";
		 }else{
			 return "电话格式错误";
		 }
	 }
	 


}
