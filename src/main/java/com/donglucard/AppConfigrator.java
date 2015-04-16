package com.donglucard;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author panmingzhi
 * @createTime 2015年4月3日
 * @content 配置文件
 */
public class AppConfigrator{

	private String key_sqlserver_ip = "";
	private String key_sqlserver_port = "";
	private String key_sqlserver_databasename = "";
	private String key_username = "";
	private String key_password = "";
	private String propertiesFilePath = "AppConfigrator.properties";
	
	final protected PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		if(this.pcs != null)this.pcs.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		if(this.pcs != null)this.pcs.removePropertyChangeListener(listener);
	}
	
	public void loadProperties(){
		File file = new File(propertiesFilePath);
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		try (
			FileInputStream fis = new FileInputStream(file);
			InputStreamReader inputStreamReader = new InputStreamReader(fis,Charset.forName("UTF-8"))){
			Properties properties = new Properties();
			properties.load(inputStreamReader);
			
			setKey_password(properties.getProperty("key_password", "1"));
			setKey_sqlserver_databasename(properties.getProperty("key_sqlserver_databasename","onecard"));
			setKey_sqlserver_ip(properties.getProperty("key_sqlserver_ip", "127.0.0.1"));
			setKey_sqlserver_port(properties.getProperty("key_sqlserver_port", "1433"));
			setKey_username(properties.getProperty("key_username", "sa"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void writer() {
		try (
			FileOutputStream fos = new FileOutputStream(propertiesFilePath , false);
			PrintWriter out = new PrintWriter(fos);
			) {
			out.println("#sqlserver数据库ip地址");
			out.println(String.format("%s=%s", key_sqlserver_ip,getKey_sqlserver_ip()));
			out.println("#sqlserver数据库端口");
			out.println(String.format("%s=%s", key_sqlserver_port,getKey_sqlserver_port()));
			out.println("#sqlserver数据库名称");
			out.println(String.format("%s=%s", key_sqlserver_databasename,getKey_sqlserver_databasename()));
			out.println("#sqlserver数据库登录名");
			out.println(String.format("%s=%s", key_username,getKey_username()));
			out.println("#sqlserver数据库登录密码");
			out.println(String.format("%s=%s", key_password,getKey_password()));
			out.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getKey_sqlserver_ip() {
		return key_sqlserver_ip;
	}

	public void setKey_sqlserver_ip(String key_sqlserver_ip) {
		this.key_sqlserver_ip = key_sqlserver_ip;
		pcs.firePropertyChange("key_sqlserver_ip", null, null);
	}

	public String getKey_sqlserver_port() {
		return key_sqlserver_port;
	}

	public void setKey_sqlserver_port(String key_sqlserver_port) {
		this.key_sqlserver_port = key_sqlserver_port;
		pcs.firePropertyChange("key_sqlserver_port", null, null);
	}

	public String getKey_sqlserver_databasename() {
		return key_sqlserver_databasename;
	}

	public void setKey_sqlserver_databasename(String key_sqlserver_databasename) {
		this.key_sqlserver_databasename = key_sqlserver_databasename;
		pcs.firePropertyChange("key_sqlserver_databasename", null, null);
	}

	public String getKey_username() {
		return key_username;
	}

	public void setKey_username(String key_username) {
		this.key_username = key_username;
		pcs.firePropertyChange("key_username", null, null);
	}

	public String getKey_password() {
		return key_password;
	}

	public void setKey_password(String key_password) {
		this.key_password = key_password;
		pcs.firePropertyChange("key_password", null, null);
	}

}
