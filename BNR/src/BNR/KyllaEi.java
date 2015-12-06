package BNR;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.Insets;
import javax.swing.UIManager;
import javax.swing.JSplitPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JInternalFrame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class KyllaEi extends JDialog {

	/**
	 * @author Deniz Anttila & Petri Miettinen
	 * @version 0.0.1
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final JLabel lblVarmistus = new JLabel("Haluatko varmasti poistaa Artistin/Kappaleen?");
	private final JSplitPane splitPaneNapit = new JSplitPane();
	private final JButton btnKyll = new JButton("Kyll\u00E4");
	private final JButton btnEi = new JButton("Peruuta");
	private final JPanel panel = new JPanel();
	private final JPanel panel_1 = new JPanel();
	private boolean uusi = false;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KyllaEi frame = new KyllaEi();
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
	public KyllaEi() {
		setModal(true);
		setTitle("Poista");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 419, 220);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 3;
		gbc_panel_1.gridy = 0;
		contentPane.add(panel_1, gbc_panel_1);
		
		GridBagConstraints gbc_lblVarmistus = new GridBagConstraints();
		gbc_lblVarmistus.insets = new Insets(0, 0, 5, 5);
		gbc_lblVarmistus.gridx = 3;
		gbc_lblVarmistus.gridy = 1;
		contentPane.add(lblVarmistus, gbc_lblVarmistus);
		
		GridBagConstraints gbc_splitPaneNapit = new GridBagConstraints();
		gbc_splitPaneNapit.insets = new Insets(0, 0, 5, 5);
		gbc_splitPaneNapit.fill = GridBagConstraints.VERTICAL;
		gbc_splitPaneNapit.gridx = 3;
		gbc_splitPaneNapit.gridy = 3;
		contentPane.add(splitPaneNapit, gbc_splitPaneNapit);
		btnKyll.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				//EiVielSkulaa();
				setUusi(true);
				setVisible(false);
			}
		});
		
		splitPaneNapit.setLeftComponent(btnKyll);
		btnEi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				//EiVielSkulaa();
				setUusi(false);
				setVisible(false);
			}
		});
		
		splitPaneNapit.setRightComponent(btnEi);
		
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 3;
		gbc_panel.gridy = 4;
		contentPane.add(panel, gbc_panel);
	}
	//============================================================

	/**
	 * avataaan huomautusikkuna
	 */
	protected void EiVielSkulaa(){
		JOptionPane.showMessageDialog(null,"tämä toiminto ei vielä toimi!!");
	}
	
	public void setUusi(boolean uusi) {
		this.uusi = uusi;
	}

	public boolean isUusi() {
		return uusi;
	}

}
