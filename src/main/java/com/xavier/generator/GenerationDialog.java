package com.xavier.generator;

import com.xavier.config.Configuration;
import com.xavier.config.model.TemplateElement;
import com.xavier.db.model.Table;
import com.xavier.generator.engine.EngineBuilder;
import com.xavier.generator.engine.TemplateEngine;
import com.xavier.generator.ui.component.CheckBoxList;
import com.xavier.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class GenerationDialog extends JDialog {

	private static final Logger LOGGER = LoggerFactory.getLogger(GenerationDialog.class);

	private final JPanel contentPanel = new JPanel();
	private JTextField textTargetProject;
	private JTextField textBasePackage;
	private JTextField textModuleName;
	private CheckBoxList templatesList;

	private Configuration configuration;
	private Table tableModel;
	private JTextField textTableName;
	private JTextField textTableAlias;
	private JTextField textAuthorName;
	private EngineBuilder engineBuilder;

	/**
	 * Create the dialog.
	 */
	public GenerationDialog(Configuration configuration, Table tableModel, String classPath) {

		setModal(true);
		setTitle("生成代码");
		setResizable(false);
		setBounds(100, 100, 419, 515);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblTargetProject = new JLabel("代码保存路径");
		lblTargetProject.setHorizontalAlignment(SwingConstants.TRAILING);
		lblTargetProject.setBounds(10, 13, 72, 15);
		contentPanel.add(lblTargetProject);

		textTargetProject = new JTextField();
		textTargetProject.setBounds(92, 10, 250, 21);
		contentPanel.add(textTargetProject);
		textTargetProject.setColumns(10);

		final JButton floder = new JButton("...");
		floder.setBounds(353, 10, 50, 21);
		contentPanel.add(floder);

		textBasePackage = new JTextField();
		textBasePackage.setBounds(92, 41, 311, 21);
		contentPanel.add(textBasePackage);
		textBasePackage.setColumns(10);

		textModuleName = new JTextField();
		textModuleName.setBounds(92, 72, 130, 21);
		contentPanel.add(textModuleName);
		textModuleName.setColumns(10);

		JLabel lblBasePackage = new JLabel("基准包");
		lblBasePackage.setHorizontalAlignment(SwingConstants.TRAILING);
		lblBasePackage.setBounds(28, 44, 54, 15);
		contentPanel.add(lblBasePackage);

		JLabel lblModuleName = new JLabel("模块名");
		lblModuleName.setHorizontalAlignment(SwingConstants.TRAILING);
		lblModuleName.setBounds(28, 75, 54, 15);
		contentPanel.add(lblModuleName);

		JPanel panel = new JPanel();
		panel.setBounds(92, 103, 311, 234);
		contentPanel.add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane);

		templatesList = new CheckBoxList();
		templatesList.setBounds(0, 0, 1, 1);
		scrollPane.setViewportView(templatesList);

		JLabel lblTemplates = new JLabel("代码模板");
		lblTemplates.setHorizontalAlignment(SwingConstants.TRAILING);
		lblTemplates.setBounds(28, 103, 54, 15);
		contentPanel.add(lblTemplates);

		textTableName = new JTextField();
		textTableName.setEditable(false);
		textTableName.setBounds(92, 347, 130, 21);
		contentPanel.add(textTableName);
		textTableName.setColumns(10);

		textTableAlias = new JTextField();
		textTableAlias.setBounds(92, 378, 130, 21);
		contentPanel.add(textTableAlias);
		textTableAlias.setColumns(10);

        /* 注释 'author' 值 */
		textAuthorName = new JTextField();
		textAuthorName.setBounds(92, 409, 130, 21);
		contentPanel.add(textAuthorName);
		textAuthorName.setColumns(10);

		JLabel lblTableName = new JLabel("表名");
		lblTableName.setHorizontalAlignment(SwingConstants.TRAILING);
		lblTableName.setBounds(28, 350, 54, 15);
		contentPanel.add(lblTableName);

		JLabel lblTableAlias = new JLabel("别名");
		lblTableAlias.setHorizontalAlignment(SwingConstants.TRAILING);
		lblTableAlias.setBounds(28, 381, 54, 15);
		contentPanel.add(lblTableAlias);
		JPanel buttonPane = new JPanel();
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

        /* 注释 'author' 字段 */
		JLabel lblAuthorName = new JLabel("作者");
		lblAuthorName.setHorizontalAlignment(SwingConstants.TRAILING);
		lblAuthorName.setBounds(28, 412, 54, 15);
		contentPanel.add(lblAuthorName);

		JButton btnOK = new JButton("生成");
		buttonPane.add(btnOK);
		btnOK.addActionListener(
				(ActionEvent e) -> okButtonClick()
		);
		btnOK.setMnemonic(KeyEvent.VK_ENTER);
		btnOK.setActionCommand("OK");
		getRootPane().setDefaultButton(btnOK);

		JButton btnCancel = new JButton("取消");
		buttonPane.add(btnCancel);
		btnCancel.addActionListener(
				(ActionEvent e) -> doClose()
		);
		btnCancel.setMnemonic(KeyEvent.VK_CANCEL);
		btnCancel.setActionCommand("Cancel");

		this.configuration = configuration;
		this.tableModel = tableModel;

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		centerScreen();
		loadConfiguration();

		engineBuilder = new EngineBuilder(classPath);

        /* 选择文件夹 */
		floder.addActionListener(
				(ActionEvent e) -> {
					JFileChooser jfc = new JFileChooser();
					jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
					jfc.showDialog(new JLabel(), "选择");
					File file = jfc.getSelectedFile();
					try {
						if (file.isDirectory()) {
							textTargetProject.setText(file.getAbsolutePath());
							//file.getAbsolutePath()
						} else if (file.isFile()) {
							textTargetProject.setText(file.getAbsolutePath().replace(file.getName(), ""));
							//file.getAbsolutePath()
						}
					} catch (NullPointerException ex) {
						textTargetProject.setText("");

					}
				}
		);
	}

	public void centerScreen() {
		Dimension dim = getToolkit().getScreenSize();
		Rectangle abounds = getBounds();
		setLocation((dim.width - abounds.width) / 2, (dim.height - abounds.height) / 2);
	}

	private void loadConfiguration() {

		templatesList.setListData(configuration.getTemplates().toArray());

		templatesList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		for (int i = 0; i < configuration.getTemplates().size(); i++) {
			TemplateElement templateElement = configuration.getTemplates().get(i);
			if (templateElement.isSelected()) {
				templatesList.addSelectionInterval(i, i);
			}
		}

		/**
		 * 去掉表前缀
		 * */
		String aliasStr = tableModel.getTableAlias();
		aliasStr = aliasStr.substring(aliasStr.indexOf("_") + 1, aliasStr.length());
		String javaModelName = StringUtil.getCamelCaseString(aliasStr, false);

		textTableAlias.setText(aliasStr);
		//textModuleName.setText(configuration.getModuleName());
		textModuleName.setText(javaModelName);
		textTableName.setText(tableModel.getTableName());
		textAuthorName.setText(configuration.getAuthor());
		textTargetProject.setText(configuration.getTargetProject());
		textBasePackage.setText(configuration.getBasePackage());

	}

	private void doClose() {
		setVisible(false);
		dispose();
	}

	private void okButtonClick() {
		Object[] selectedValues = templatesList.getSelectedValues();
		if (selectedValues.length == 0) {
			JOptionPane.showMessageDialog(this, "请选择模板.", "提示", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		String targetProject = textTargetProject.getText();
		if (StringUtil.isEmpty(targetProject)) {
			JOptionPane.showMessageDialog(this, "请输入代码保存路径.", "提示", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		String basePackage = textBasePackage.getText();
		if (StringUtil.isEmpty(basePackage)) {
			JOptionPane.showMessageDialog(this, "请输入基准包.", "提示", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		String moduleName = textModuleName.getText();
		if (StringUtil.isEmpty(moduleName)) {
			JOptionPane.showMessageDialog(this, "请输入模块名.", "提示", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		String tableAlias = textTableAlias.getText();
		if (StringUtil.isEmpty(moduleName)) {
			JOptionPane.showMessageDialog(this, "请输入表别名.", "提示", JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		String authorName = textAuthorName.getText();
		if (StringUtil.isEmpty(authorName)) {
			JOptionPane.showMessageDialog(this, "请输入代码作者.", "提示", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		processSelectedTemplates(selectedValues, targetProject, basePackage, moduleName, tableAlias, authorName);
	}

	private void processSelectedTemplates(Object[] selectedTemplateElements, String targetProject, String basePackage, String moduleName, String tableAlias, String authorName) {
		configuration.setTargetProject(targetProject);
		configuration.setBasePackage(basePackage);
		configuration.setModuleName(moduleName);
		configuration.setAuthor(authorName);

		tableModel.setTableAlias(tableAlias);

		Map<String, Object> model = new HashMap<>();/* String,Object */
		model.put("targetProject", configuration.getTargetProject());
		model.put("basePackage", configuration.getBasePackage());
		model.put("moduleName", configuration.getModuleName());
		model.put("author", configuration.getAuthor());
		model.put("table", tableModel);

		Map<String, Boolean> selectedTemplateMap = new HashMap<String, Boolean>();

		for (TemplateElement templateElement : configuration.getTemplates()) {
			templateElement.setSelected(false);
			selectedTemplateMap.put(templateElement.getTemplateId(), false);
		}
		for (int i = 0; i < selectedTemplateElements.length; i++) {
			TemplateElement templateElement = (TemplateElement) selectedTemplateElements[i];
			selectedTemplateMap.put(templateElement.getTemplateId(), true);
		}
		model.put("template", selectedTemplateMap);

		for (int i = 0; i < selectedTemplateElements.length; i++) {
			try {
				TemplateElement templateElement = (TemplateElement) selectedTemplateElements[i];
				templateElement.setSelected(true);

				if (templateElement.getTemplateFile().trim().length() > 0) {
					TemplateEngine templateEngine = engineBuilder.getTemplateEngine(templateElement.getEngine());
					if (templateEngine == null) {
						JOptionPane.showMessageDialog(this, "没有对应的模板引擎: " + templateElement.getEngine(), "错误", JOptionPane.ERROR_MESSAGE);
					} else {
						templateEngine.processToFile(model, templateElement);
					}
				}
			} catch (Exception e) {
				LOGGER.info(e.getMessage(), e);
				JOptionPane.showMessageDialog(this, e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
			}
		}
		configuration.save();
		JOptionPane.showMessageDialog(this, "生成完毕.", "提示", JOptionPane.INFORMATION_MESSAGE);
	}

}
