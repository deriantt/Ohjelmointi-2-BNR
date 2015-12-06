package BNR;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.Box;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.UIManager;

import musiikkiarkisto.Artistit;
import musiikkiarkisto.Kappaleet;
import musiikkiarkisto.Levyt;
import musiikkiarkisto.SailoException;

/**
 * @author Deniz Anttila
 * @version 0.0.1
 */

public class aloitusIkkuna2 extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final JSplitPane splitPane = new JSplitPane();
	private final JPanel panelYla = new JPanel();
	private final JPanel panelVasen = new JPanel();
	private final JPanel panelKeski = new JPanel();
	private final JPanel panelOikea = new JPanel();
	private final JPanel panelMusiikkiarkisto = new JPanel();
	private final JPanel panel_1Versio = new JPanel();
	private final JPanel panelTyhja = new JPanel();
	private final JPanel panelTekijat = new JPanel();
	private final JLabel lblMusiikkiarkisto = new JLabel("Musiikkiarkisto"); //$NON-NLS-1$
	private final JLabel lblVersio = new JLabel("Versio 0.0.1"); //$NON-NLS-1$
	private final JLabel lblTekijat = new JLabel("Deniz Anttila & Petri Miettinen"); //$NON-NLS-1$
	private final JPanel panelAla = new JPanel();
	private final JPanel panelAnna = new JPanel();
	private final JPanel panelArkistoNimi = new JPanel();
	private final JPanel panelNappi = new JPanel();
	private final JButton btnOk = new JButton("OK"); //$NON-NLS-1$
	private final static JTextField txtArkistoNimi = new JTextField();
	private final JLabel lblAnnaArkistonNimi = new JLabel("           "); //$NON-NLS-1$
	private final JLabel lblAnna = new JLabel("Anna arkiston nimi:"); //$NON-NLS-1$

	/**
	 * Launch the application.
	 * @param args 
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
					aloitusIkkuna2 naytto = new aloitusIkkuna2();
					naytto.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public aloitusIkkuna2() {
		getTxtArkistoNimi().setPreferredSize(new Dimension(10, 10));
		getTxtArkistoNimi().setMinimumSize(new Dimension(10, 10));
		getTxtArkistoNimi().setText("BNR"); //$NON-NLS-1$
		getTxtArkistoNimi().setColumns(10);
		setTitle("Musiikkiarkisto"); //$NON-NLS-1$
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 558, 303);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(30, 30, 30, 30));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		
		contentPane.add(splitPane, BorderLayout.CENTER);
		panelYla.setBorder(new EmptyBorder(40, 40, 20, 40));
		
		splitPane.setLeftComponent(panelYla);
		panelYla.setLayout(new BoxLayout(panelYla, BoxLayout.X_AXIS));
		panelVasen.setPreferredSize(new Dimension(40, 10));
		
		panelYla.add(panelVasen);
		
		panelYla.add(panelKeski);
		panelKeski.setLayout(new BoxLayout(panelKeski, BoxLayout.Y_AXIS));
		
		panelKeski.add(panelMusiikkiarkisto);
		
		panelMusiikkiarkisto.add(lblMusiikkiarkisto);
		
		panelKeski.add(panel_1Versio);
		
		panel_1Versio.add(lblVersio);
		
		panelKeski.add(panelTyhja);
		
		panelKeski.add(panelTekijat);
		
		panelTekijat.add(lblTekijat);
		panelOikea.setPreferredSize(new Dimension(40, 10));
		
		panelYla.add(panelOikea);
		
		splitPane.setRightComponent(panelAla);
		panelAla.setLayout(new BoxLayout(panelAla, BoxLayout.X_AXIS));
		panelAnna.setBorder(new EmptyBorder(0, 30, 0, 0));
		panelAnna.setPreferredSize(new Dimension(200, 10));
		
		panelAla.add(panelAnna);
		panelAnna.setLayout(new BoxLayout(panelAnna, BoxLayout.X_AXIS));
		
		panelAnna.add(lblAnnaArkistonNimi);
		
		panelAnna.add(lblAnna);
		panelArkistoNimi.setBorder(new EmptyBorder(15, 0, 15, 0));
		panelArkistoNimi.setPreferredSize(new Dimension(80, 15));
		
		panelAla.add(panelArkistoNimi);
		panelArkistoNimi.setLayout(new BoxLayout(panelArkistoNimi, BoxLayout.X_AXIS));
		
		panelArkistoNimi.add(getTxtArkistoNimi());
		
		panelAla.add(panelNappi);
		panelNappi.setLayout(new BoxLayout(panelNappi, BoxLayout.X_AXIS));
		btnOk.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				
					avaa();
				
				
			}
		});
		
		panelNappi.add(btnOk);

	}

	//================================================Omat ohjelmat======================================
	
	
	/**
	 * avaa halutun arkiston
	 * @throws SailoException 
	 */
	protected void avaa(){
		
		musiikkiarkisto.Musiikkiarkisto.setTiedosto();
		try {
			
			Artistit.lueTiedostosta(txtArkistoNimi.getText());
			Levyt.lueTiedostosta(txtArkistoNimi.getText());
			Kappaleet.lueTiedostosta(txtArkistoNimi.getText());
		
		} catch (SailoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		//JOptionPane.showMessageDialog(null,"Avasit arkiston: " + arkistoNimi);
		paaikku3 JFrame = new paaikku3();
		JFrame.setVisible(true);
		paaikku3.paivitaArtistiLista();
		setVisible(false);
	}

	public static JTextField getTxtArkistoNimi() {
		return txtArkistoNimi;
	}
	

}

