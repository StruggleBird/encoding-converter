package org.conversion.form;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
			setTitle("Encode Convertor");
			getContentPane().setLayout(null);
			{
				btnSource = new JButton();
				btnSource.setName("btnSource");
				getContentPane().add(btnSource, null);
				btnSource.setText("source Dic");
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
				btnTarget.setText("target Dic");
				btnTarget.setBounds(374, 95, 86, 29);
				btnTarget.addActionListener(alChooseFile);
			}
			{
				btnConvert = new JButton();
				getContentPane().add(btnConvert);
				btnConvert.setText("Convert");
				btnConvert.setBounds(77, 252, 93, 27);
				btnConvert.addActionListener(alConvert);
			}
			{
				btnExit = new JButton();
				getContentPane().add(btnExit);
				btnExit.setText("Exit");
				btnExit.setBounds(241, 252, 93, 27);
				btnExit.setLocale(new java.util.Locale("sq"));
				btnExit.addActionListener(alExit);
			}
			{
				Object[] charsetArray = Utils.getCharSetList();
				List list = Arrays.asList(charsetArray);
				ArrayList allList = new ArrayList();
				allList.add(Utils.AUTO_DETECT);
				allList.addAll(list);
				ComboBoxModel cboCharsetListModel = new DefaultComboBoxModel(
						allList.toArray());
				cboSourceCharsetList = new JComboBox();
				getContentPane().add(cboSourceCharsetList);
				cboSourceCharsetList.setModel(cboCharsetListModel);
				cboSourceCharsetList.setBounds(201, 146, 188, 22);
				cboSourceCharsetList.setLocale(new java.util.Locale("sq"));
			}
			{
				ComboBoxModel cboCharsetListModel = new DefaultComboBoxModel(
						Utils.getCharSetList());
				cboTargetCharsetList = new JComboBox();
				getContentPane().add(cboTargetCharsetList);
				cboTargetCharsetList.setModel(cboCharsetListModel);
				cboTargetCharsetList.setBounds(201, 176, 188, 22);
				cboTargetCharsetList.setLocale(new java.util.Locale("sq"));
			}
			
			{
				final JLabel lbSourceEncoding = new JLabel();
				getContentPane().add(lbSourceEncoding);
				lbSourceEncoding
						.setText("source encoding(optional):");
				lbSourceEncoding.setBounds(24, 150, 165, 15);
			}
			{
				final JLabel lblTargetEncoding = new JLabel();
				getContentPane().add(lblTargetEncoding);
				lblTargetEncoding.setText("target encoding:");
				lblTargetEncoding.setBounds(24, 177, 153, 15);
			}
			{
				final JLabel lblFilter = new JLabel();
				getContentPane().add(lblFilter);
				lblFilter.setText("extensions(semicolon):");
				lblFilter.setBounds(24, 213, 153, 15);
			}
			{
				tfFilter = new JTextField();
				getContentPane().add(tfFilter);
				tfFilter.setName("tfTarget");
				tfFilter.setBounds(202, 210, 187, 22);

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
	private JComboBox cboSourceCharsetList;
	private JComboBox cboTargetCharsetList;

	ActionListener alConvert = new ActionListener() {
		
		public void actionPerformed(ActionEvent e) {
			sourceFolder = new File(tfSource.getText());
			targetFolder = new File(tfTarget.getText());
			if (validatePass()) {
				try {
					EncodingConverter.convert(sourceFolder, targetFolder, cboSourceCharsetList.getSelectedItem()
							.toString(), cboTargetCharsetList.getSelectedItem()
							.toString(), tfFilter.getText());

					showMessageDialog("The conversion is complete!");
				} catch (Exception e1) {
					e1.printStackTrace();
					showMessageDialog(e1.getMessage());
				}
			}
		}
	};


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
		if (sourceFolder == null || !sourceFolder.exists() || sourceFolder.isFile()) {
			errorMsg.append("Source directory is invalid.");
			return;
		}

		if (targetFolder == null) {
			errorMsg.append("Source directory is invalid.");
			return;
		}
	}

	public void showMessageDialog(String message) {
		JOptionPane.showMessageDialog(getContentPane(), message);
	}

}
