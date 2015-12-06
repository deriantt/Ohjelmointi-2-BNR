package BNR;


import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JSplitPane;
import javax.swing.JButton;
import javax.swing.UIManager;


public class Huomautus extends JFrame {

	/**
	 * @author Deniz Anttila & Petri Miettinen
	 * @version 0.0.1
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final JLabel lblTiedostoEiAukea = new JLabel("Tiedosto ei aukea");
	private final JLabel lblHaluatkoLuodaUuden = new JLabel("Haluatko luoda uuden tiedoston?");
	private final JSplitPane splitPane = new JSplitPane();
	private final JButton btnKyll = new JButton("Peruuta");
	private final JButton btnKylla = new JButton("Kyll\u00E4");
	private final JPanel panel = new JPanel();
	private final JPanel panel_1 = new JPanel();
	private final JPanel panel_2 = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				try {
					Huomautus frame = new Huomautus();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Huomautus() {
		setTitle("Fail");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 377, 209);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 0.0, 1.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 5;
		gbc_panel.gridy = 0;
		contentPane.add(panel, gbc_panel);
		
		GridBagConstraints gbc_lblTiedostoEiAukea = new GridBagConstraints();
		gbc_lblTiedostoEiAukea.insets = new Insets(0, 0, 5, 0);
		gbc_lblTiedostoEiAukea.gridx = 5;
		gbc_lblTiedostoEiAukea.gridy = 1;
		contentPane.add(lblTiedostoEiAukea, gbc_lblTiedostoEiAukea);
		
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 5, 0);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 5;
		gbc_panel_2.gridy = 2;
		contentPane.add(panel_2, gbc_panel_2);
		
		GridBagConstraints gbc_lblHaluatkoLuodaUuden = new GridBagConstraints();
		gbc_lblHaluatkoLuodaUuden.insets = new Insets(0, 0, 5, 0);
		gbc_lblHaluatkoLuodaUuden.gridx = 5;
		gbc_lblHaluatkoLuodaUuden.gridy = 3;
		contentPane.add(lblHaluatkoLuodaUuden, gbc_lblHaluatkoLuodaUuden);
		
		GridBagConstraints gbc_splitPane = new GridBagConstraints();
		gbc_splitPane.insets = new Insets(0, 0, 5, 0);
		gbc_splitPane.gridx = 5;
		gbc_splitPane.gridy = 4;
		contentPane.add(splitPane, gbc_splitPane);
		
		splitPane.setLeftComponent(btnKyll);
		
		splitPane.setRightComponent(btnKylla);
		
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 5;
		gbc_panel_1.gridy = 5;
		contentPane.add(panel_1, gbc_panel_1);
	}

}
