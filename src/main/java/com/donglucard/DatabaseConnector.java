package com.donglucard;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatabaseConnector {
	
	private static Logger LOGGER = LoggerFactory.getLogger(DatabaseConnector.class);
	
	public static Connection connection;
	
	public static void checkConnect(){
		if(connection != null){
			return;
		}
		
		try {
			AppConfigrator appConfigrator = new AppConfigrator();
			appConfigrator.loadProperties();
			String ip = appConfigrator.getKey_sqlserver_ip();
			String port = appConfigrator.getKey_sqlserver_port();
			String databasename = appConfigrator.getKey_sqlserver_databasename();
			String username = appConfigrator.getKey_username();
			String password = appConfigrator.getKey_password();
			
			String url = String.format("jdbc:jtds:sqlserver://%s:%s;DatabaseName=%s",ip,port,databasename); 
			connection = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			LOGGER.error("获取数据库连接失败！",e);
		}
	}
	
	public static String executeStringSQL(String sql,Object...parameters) throws Exception{
		LOGGER.debug("查询sql:{} ,传入参数:{}",sql,Arrays.toString(parameters));
		PreparedStatement prepareStatement = null;
		ResultSet executeQuery = null;
		try{
			checkConnect();
			prepareStatement = connection.prepareStatement(sql);
			for (int i=0;i<parameters.length;i++) {
				Object object = parameters[i];
				if(object instanceof Integer){
					prepareStatement.setInt(i+1, (Integer)parameters[i]);
				}else if(object instanceof String){
					prepareStatement.setString(i+1, (String)parameters[i]);
				}
			}
			executeQuery = prepareStatement.executeQuery();
			if(executeQuery.next()){
				return executeQuery.getString(1);
			}
			return "";
		}catch(Exception e){
			throw e;
		}finally{
			if(prepareStatement != null){
				try {prepareStatement.close();} catch (SQLException e) {}
			}
			if(executeQuery != null){
				try {executeQuery.close();} catch (SQLException e) {}
			}
		}
		
	}
	
}
