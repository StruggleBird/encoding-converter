package org.conversion.form;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import org.conversion.encodingconversion.EncodingConverter;
import org.conversion.encodingconversion.IEncodingConverter;
import org.conversion.utils.Utils;


/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class MainForm extends javax.swing.JFrame {

	/**
     * 
     */
	private static final long serialVersionUID = 1L;

	private JTextField tfSource;
	private JButton btnConvert;
	private JButton btnExit;
	private JButton btnTarget;
	private JTextField tfTarget;
	private JButton btnSource;
	private File sourceFolder;
	private File targetFolder;

	/**
	 * Auto-generated main method to display this JFrame
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				MainForm inst = new MainForm();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
				inst.setResizable(false);

			}
		});
	}

	public MainForm() {
		super();
		initGUI();
	}

	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			setTitle("文件编码转换器");
			getContentPane().setLayout(null);
			{
				btnSource = new JButton();
				btnSource.setName("btnSource");
				getContentPane().add(btnSource, null);
				btnSource.setText("\u6e90\u76ee\u5f55");
				btnSource.setBounds(373, 38, 86, 29);
				btnSource.addActionListener(alChooseFile);

			}
			{
				tfSource = new JTextField();
				tfSource.setName("tfSource");
				getContentPane().add(tfSource);
				tfSource.setBounds(23, 41, 329, 22);
			}
			{
				tfTarget = new JTextField();
				tfTarget.setName("tfTarget");
				getContentPane().add(tfTarget);
				tfTarget.setBounds(24, 98, 329, 22);
			}
			{
				btnTarget = new JButton();
				btnTarget.setName("btnTarget");
				getContentPane().add(btnTarget);
				btnTarget.setText("\u76ee\u6807\u76ee\u5f55");
				btnTarget.setBounds(374, 95, 86, 29);
				btnTarget.addActionListener(alChooseFile);
			}
			{
				btnConvert = new JButton();
				getContentPane().add(btnConvert);
				btnConvert.setText("\u5f00\u59cb\u8f6c\u6362");
				btnConvert.setBounds(77, 238, 93, 27);
				btnConvert.addActionListener(alConvert);
			}
			{
				btnExit = new JButton();
				getContentPane().add(btnExit);
				btnExit.setText("\u9000  \u51fa");
				btnExit.setBounds(241, 238, 93, 27);
				btnExit.setLocale(new java.util.Locale("sq"));
				btnExit.addActionListener(alExit);
			}
			{
				ComboBoxModel cboCharsetListModel = new DefaultComboBoxModel(
						Utils.getCharSetList());
				cboCharsetList = new JComboBox();
				getContentPane().add(cboCharsetList);
				cboCharsetList.setModel(cboCharsetListModel);
				cboCharsetList.setBounds(165, 146, 188, 22);
				cboCharsetList.setLocale(new java.util.Locale("sq"));
			}
			{
				final JLabel lblTargetEncoding = new JLabel();
				getContentPane().add(lblTargetEncoding);
				lblTargetEncoding
						.setText("\u8f6c\u6362\u4e3a\u5b57\u7b26\u7f16\u7801\uff1a");
				lblTargetEncoding.setBounds(24, 150, 114, 15);
			}
			{
				final JLabel lblFilter = new JLabel();
				getContentPane().add(lblFilter);
				lblFilter
						.setText("\u6587\u4ef6\u8fc7\u6ee4\u540e\u7f00\u8bbe\u7f6e\uff1a");
				lblFilter.setBounds(24, 189, 124, 15);
			}
			{
				tfFilter = new JTextField();
				getContentPane().add(tfFilter);
				tfFilter.setName("tfTarget");
				tfFilter.setBounds(166, 186, 274, 22);
				final String filterText = "默认不过滤文件后缀,每一个后缀用分号隔开";
				tfFilter.setToolTipText(filterText);

			}
			pack();
			this.setSize(500, 329);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	ActionListener alChooseFile = new ActionListener() {

		
		public void actionPerformed(ActionEvent e) {
			JButton btnSource = (JButton) e.getSource();
			Container container = btnSource.getParent();
			JTextField tField = (JTextField) findComponent(container, "tf"
					+ btnSource.getName().substring(3));
			JFileChooser fileChooser = new JFileChooser();

			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int res = fileChooser.showOpenDialog(getContentPane());
			if (res == JFileChooser.OPEN_DIALOG) {
				tField.setText(fileChooser.getSelectedFile().getAbsolutePath());
			}

		}
	};
	private JTextField tfFilter;

	ActionListener alExit = new ActionListener() {
		
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	};
	private JComboBox cboCharsetList;

	ActionListener alConvert = new ActionListener() {
		
		public void actionPerformed(ActionEvent e) {
			sourceFolder = new File(tfSource.getText());
			targetFolder = new File(tfTarget.getText());
			if (validatePass()) {
				try {
					convert(sourceFolder, targetFolder, tfFilter.getText());

					showMessageDialog("转换完成!");
				} catch (Exception e1) {
					e1.printStackTrace();
					showMessageDialog(e1.getMessage());
				}
			}

		}

	};

	private void convert(File source, File targetFolder, String filter)
			throws Exception {
		if (source.isDirectory()) {
			for (File subFile : source.listFiles()) {
				convert(subFile, targetFolder, filter);
			}
		} else {
			if (filter != null && !filter.isEmpty()) {
				String fileName = source.getName();
				if (fileName.lastIndexOf('.') == -1) {
					return;
				}
				String[] suffixList = filter.split(";");
				String fileSuffix = fileName.substring(fileName
						.lastIndexOf('.') + 1);
				boolean find = false;
				for (String suffix : suffixList) {
					if (fileSuffix.equalsIgnoreCase(suffix)) {
						find = true;
						break;
					}
				}
				if (!find) {
					return;
				}

			}

			IEncodingConverter encodingConverter = new EncodingConverter();
			encodingConverter.setSource(source);
			String suffixPath = source.getAbsolutePath().substring(
					sourceFolder.getAbsolutePath().length());
			String targetFilePath = targetFolder.getAbsoluteFile() + suffixPath;
			File targetFile = new File(targetFilePath);
			File parentFolder = targetFile.getParentFile();
			if (!parentFolder.exists()) {
				parentFolder.mkdirs();
			}
			encodingConverter.setTarget(targetFile);
			encodingConverter.setTargetEncoding(cboCharsetList
					.getSelectedItem().toString());
			encodingConverter.encode();
		}
	}

	private Component findComponent(Container container, String name) {
		for (Component component : container.getComponents()) {
			if (component.getName().equals(name)) {
				return component;
			}
		}
		return null;
	}

	private boolean validatePass() {
		StringBuilder errorMsg = new StringBuilder();
		getErrMsg(sourceFolder, targetFolder, errorMsg);

		if (errorMsg.length() != 0) {
			showMessageDialog(errorMsg.toString());
			return false;
		}

		return true;
	}

	private void getErrMsg(File sourceFolder, File targetFolder,
			StringBuilder errorMsg) {
		if (sourceFolder == null || !sourceFolder.exists()) {
			errorMsg.append("源目录为空或不存在");
			return;
		}

		if (sourceFolder.isFile()) {
			errorMsg.append("源目录路径必须为一个目录");
			return;
		}

		if (targetFolder == null) {
			errorMsg.append("目标目录不能为空");
			return;
		}

		if (!targetFolder.exists() && !targetFolder.mkdirs()) {
			errorMsg.append("目标目录是一个错误的路径");
			return;
		}

	}

	public void showMessageDialog(String message) {
		JOptionPane.showMessageDialog(getContentPane(), message);
	}

}
