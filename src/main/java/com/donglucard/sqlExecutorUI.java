package com.donglucard;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.xml.crypto.Data;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.core.databinding.beans.BeanProperties;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.jface.databinding.swt.SWTObservables;

public class sqlExecutorUI {
	private DataBindingContext m_bindingContext;

	protected Shell shell;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_4;
	private Text text;
	private Text text_5;
	
	private AppConfigrator appConfigrator = new AppConfigrator();

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		Display display = Display.getDefault();
		Realm.runWithDefault(SWTObservables.getRealm(display), new Runnable() {
			public void run() {
				try {
					sqlExecutorUI window = new sqlExecutorUI();
					window.open();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Open the window.
	 */
	public void open() {
		appConfigrator.loadProperties();
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		Image image = ImageUtil.get("sql_48");
		
		shell = new Shell();
		shell.setImage(image);
		shell.setSize(321, 304);
		shell.setText("SWT Application");
		shell.setLayout(new GridLayout(2, false));
		
		Composite composite_1 = new Composite(shell, SWT.NONE);
		RowLayout rl_composite_1 = new RowLayout(SWT.HORIZONTAL);
		rl_composite_1.fill = true;
		composite_1.setLayout(rl_composite_1);
		composite_1.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 2, 1));
		
		Label lblNewLabel_6 = new Label(composite_1, SWT.NONE);
		lblNewLabel_6.setLayoutData(new RowData(48, 48));
		lblNewLabel_6.setImage(image);
		
		Composite composite_2 = new Composite(composite_1, SWT.NONE);
		composite_2.setLayout(new GridLayout(1, false));
		
		Label lblNewLabel_7 = new Label(composite_2, SWT.NONE);
		lblNewLabel_7.setFont(SWTResourceManager.getFont("楷体", 16, SWT.BOLD));
		lblNewLabel_7.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, true, 1, 1));
		lblNewLabel_7.setBounds(0, 0, 61, 17);
		lblNewLabel_7.setText("东陆脚本执行器");
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel.setText("ip 地 址");
		
		text = new Text(shell, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblNewLabel_1 = new Label(shell, SWT.NONE);
		lblNewLabel_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_1.setText("端 口 号");
		
		text_1 = new Text(shell, SWT.BORDER);
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblNewLabel_2 = new Label(shell, SWT.NONE);
		lblNewLabel_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_2.setText("登 录 名");
		
		text_2 = new Text(shell, SWT.BORDER);
		text_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblNewLabel_3 = new Label(shell, SWT.NONE);
		lblNewLabel_3.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_3.setText("密     码");
		
		text_3 = new Text(shell, SWT.BORDER);
		text_3.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblNewLabel_4 = new Label(shell, SWT.NONE);
		lblNewLabel_4.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_4.setText("数据库名");
		
		text_4 = new Text(shell, SWT.BORDER);
		text_4.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblNewLabel_5 = new Label(shell, SWT.NONE);
		lblNewLabel_5.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_5.setText("脚本路径");
		
		text_5 = new Text(shell, SWT.BORDER);
		text_5.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setLayout(new RowLayout(SWT.HORIZONTAL));
		composite.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 2, 1));
		
		Button btnNewButton = new Button(composite, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog fd = new FileDialog(shell);
				String open = fd.open();
				if(open == null || open.length() == 0){
					return;
				}
				text_5.setText(open);
			}
		});
		btnNewButton.setText("选择脚本");
		
		Button btnNewButton_1 = new Button(composite, SWT.NONE);
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Path path = Paths.get(text_5.getText());
				try {
					appConfigrator.writer();
					List<String> readAllLines = Files.readAllLines(path, Charset.defaultCharset());
					StringBuilder sb = new StringBuilder();
					for (String string : readAllLines) {
						sb.append(string);
					}
					DatabaseConnector.executeStringSQL(sb.toString(), new Object[]{});
					MessageBox mb = new MessageBox(shell,SWT.ICON_INFORMATION);
					mb.setText("提示");
					mb.setMessage("执行脚本成功");
					mb.open();
				} catch (Exception e1) {
					MessageBox mb = new MessageBox(shell,SWT.ICON_ERROR);
					mb.setText("执行脚本时发生错误");
					mb.setMessage(e1.getMessage());
					mb.open();
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setText("执行脚本");
		m_bindingContext = initDataBindings();

	}
	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		IObservableValue observeTextTextObserveWidget = WidgetProperties.text(SWT.Modify).observe(text);
		IObservableValue key_sqlserver_ipAppConfigratorObserveValue = BeanProperties.value("key_sqlserver_ip").observe(appConfigrator);
		bindingContext.bindValue(observeTextTextObserveWidget, key_sqlserver_ipAppConfigratorObserveValue, null, null);
		//
		IObservableValue observeTextText_1ObserveWidget = WidgetProperties.text(SWT.Modify).observe(text_1);
		IObservableValue key_sqlserver_portAppConfigratorObserveValue = BeanProperties.value("key_sqlserver_port").observe(appConfigrator);
		bindingContext.bindValue(observeTextText_1ObserveWidget, key_sqlserver_portAppConfigratorObserveValue, null, null);
		//
		IObservableValue observeTextText_2ObserveWidget = WidgetProperties.text(SWT.Modify).observe(text_2);
		IObservableValue key_usernameAppConfigratorObserveValue = BeanProperties.value("key_username").observe(appConfigrator);
		bindingContext.bindValue(observeTextText_2ObserveWidget, key_usernameAppConfigratorObserveValue, null, null);
		//
		IObservableValue observeTextText_3ObserveWidget = WidgetProperties.text(SWT.Modify).observe(text_3);
		IObservableValue key_passwordAppConfigratorObserveValue = BeanProperties.value("key_password").observe(appConfigrator);
		bindingContext.bindValue(observeTextText_3ObserveWidget, key_passwordAppConfigratorObserveValue, null, null);
		//
		IObservableValue observeTextText_4ObserveWidget = WidgetProperties.text(SWT.Modify).observe(text_4);
		IObservableValue key_sqlserver_databasenameAppConfigratorObserveValue = BeanProperties.value("key_sqlserver_databasename").observe(appConfigrator);
		bindingContext.bindValue(observeTextText_4ObserveWidget, key_sqlserver_databasenameAppConfigratorObserveValue, null, null);
		//
		return bindingContext;
	}
}
