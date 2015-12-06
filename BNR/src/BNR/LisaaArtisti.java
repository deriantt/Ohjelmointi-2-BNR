package BNR;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.EventQueue;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.Action;
import javax.swing.AbstractAction;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.UIManager;

import musiikkiarkisto.Musiikkiarkisto;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;


public class LisaaArtisti extends JDialog {

	/**
	 * @author Deniz Anttila
	 * @version 0.0.1
	 */
	private static final long serialVersionUID = 1L;
	public static Object java;
	private JPanel contentPane;
	private final JLabel lblNimi = new JLabel("Nimi");
	private final JTextField txtNimi = new JTextField();
	public JTextField getTxtnimi() {
		return txtNimi;
	}

	private final JSplitPane splitPane = new JSplitPane();
	private final JButton btnPeruuta = new JButton("Peruuta");
	private final JButton btnLisaa = new JButton("Lis\u00E4\u00E4");
	private final JPanel panel = new JPanel();
	private final JPanel panel_1 = new JPanel();
	private final JPanel panel_2 = new JPanel();
    private final Musiikkiarkisto musiikkiarkisto = new Musiikkiarkisto();
    private boolean uusi = false;
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
					LisaaArtisti frame = new LisaaArtisti();
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
	public LisaaArtisti() {
		//getMusiikkiarkisto().setTextArtisti(textArtisti);
		setModal(true);
		txtNimi.setToolTipText("Lis\u00E4\u00E4 artistin nimi");
		txtNimi.setColumns(10);
		setTitle("Lis\u00E4\u00E4 Artisti");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 477, 161);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 6;
		gbc_panel.gridy = 0;
		contentPane.add(panel, gbc_panel);
		
		GridBagConstraints gbc_lblNimi = new GridBagConstraints();
		gbc_lblNimi.insets = new Insets(0, 0, 5, 5);
		gbc_lblNimi.gridx = 6;
		gbc_lblNimi.gridy = 1;
		contentPane.add(lblNimi, gbc_lblNimi);
		
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 5;
		gbc_panel_1.gridy = 2;
		contentPane.add(panel_1, gbc_panel_1);
		
		GridBagConstraints gbc_txtNimi = new GridBagConstraints();
		gbc_txtNimi.insets = new Insets(0, 0, 5, 5);
		gbc_txtNimi.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNimi.gridx = 6;
		gbc_txtNimi.gridy = 2;
		contentPane.add(txtNimi, gbc_txtNimi);
		
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 5, 0);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 7;
		gbc_panel_2.gridy = 2;
		contentPane.add(panel_2, gbc_panel_2);
		
		GridBagConstraints gbc_splitPane = new GridBagConstraints();
		gbc_splitPane.insets = new Insets(0, 0, 0, 5);
		gbc_splitPane.gridx = 6;
		gbc_splitPane.gridy = 4;
		contentPane.add(splitPane, gbc_splitPane);
		btnPeruuta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
			// SetVisible(false) sulkee ikkunan mutta pit‰‰ sen taustalla auki 
			//setUusi(false);
				setVisible(false);
			}
		});
		
		splitPane.setLeftComponent(btnPeruuta);
		btnLisaa.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseReleased(MouseEvent e){
				if(txtNimi.getText().isEmpty()){
				EiVielSkulaa();
				}else{
					setUusi(true);
					setVisible(false);
				}
			}
		});
		
		splitPane.setRightComponent(btnLisaa);
	}
	

	/**
	 * avataaan huomautusikkuna
	 */
	protected void EiVielSkulaa(){
		JOptionPane.showMessageDialog(null,"Lis‰‰ nimi tekstikentt‰‰n");
	}
	
	protected void EiVielSkulaa(String fak){
		JOptionPane.showMessageDialog(null,"Valitsit artistin nimeksi: " + fak);
	}
	
	/**
	 * Lis‰‰ kommentit
	 * @return
	 */
	protected Musiikkiarkisto getMusiikkiarkisto(){
		return musiikkiarkisto;
	}

	public void setUusi(boolean uusi) {
		this.uusi = uusi;
	}

	public boolean isUusi() {
		return uusi;
	}
   

}
