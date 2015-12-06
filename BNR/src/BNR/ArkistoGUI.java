package BNR;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;


public class ArkistoGUI {



	

		/**
		 * @author Deniz Anttila & Petri Miettinen
		 * @version 0.0.1
		 */
		private static final long serialVersionUID = 1L;
		
		private JPanel contentPane;
		private final JSplitPane splitPaneRunko = new JSplitPane();
		private final JSplitPane splitPaneOikea = new JSplitPane();
		private final JSplitPane splitPaneOikeaAla = new JSplitPane();
		private final JPanel panelArtisti = new JPanel();
		private final JPanel panelHaku = new JPanel();
		private final JScrollPane scrollArtisti = new JScrollPane();
		private final JPanel panelArtistiNapit = new JPanel();
		private final JLabel lblHakuehto = new JLabel("Hakuehto");
		private final JComboBox cbEhto = new JComboBox();
		private final JTextField textHaku = new JTextField();
		private final JLabel lblArtisti = new JLabel("Artisti");
		private final JList listArtistit = new JList();
		private final JButton btnPoista = new JButton("Poista");
		private final JButton btnLis = new JButton("Lis\u00E4\u00E4");
		private final JPanel panelLevy = new JPanel();
		private final JLabel lblLevy = new JLabel("Levy");
		private final JPanel panelLevyOtsikko = new JPanel();
		private final JScrollPane scrollPane = new JScrollPane();
		private final JPanel panelKappale = new JPanel();
		private final JPanel panelKappaleOtsikko = new JPanel();
		private final JLabel lblKappaleet = new JLabel("Kappaleet");
		private final JScrollPane scrollKappaleet = new JScrollPane();
		private final JPanel panelMuutNapit = new JPanel();
		private final JPanel panelMuokkaus = new JPanel();
		private final JPanel panelNapit = new JPanel();
		private final JButton btnPoistaLtaiK = new JButton("Poista");
		private final JButton btnMuokkaa = new JButton("Muokkaa");
		private final JButton btnLisaaLtaiK = new JButton("Lis\u00E4\u00E4");
		private final JMenuBar menuBar = new JMenuBar();
		private final JPanel panelNapitKeski = new JPanel();
		private final JList listKappaleet = new JList();
		private final JList listLevy = new JList();
		private final JMenu mnFile = new JMenu("File");
		private final JMenu mnHelp = new JMenu("Help");
		private final JMenuItem mntmNew = new JMenuItem("New");
		private final JMenuItem mntmAbout = new JMenuItem("About");

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
						ArkistoGUI frame = new ArkistoGUI();
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}

		protected static void setVisible(boolean b) {
			// TODO Auto-generated method stub
			
		}

		/**
		 * Create the frame.
		 */
		public ArkistoGUI() {
			textHaku.setColumns(10);
			setTitle("Musiikkiarkisto");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 800, 600);
			
			setJMenuBar(menuBar);
			
			menuBar.add(mnFile);
			mntmNew.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) {
					EiVielSkulaa();
				}
			});
			
			mnFile.add(mntmNew);
			
			menuBar.add(mnHelp);
			mntmAbout.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) {
					tietoja();
				}
			});
			
			mnHelp.add(mntmAbout);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			contentPane.setLayout(new BorderLayout(0, 0));
			setContentPane(contentPane);
			
			contentPane.add(splitPaneRunko, BorderLayout.CENTER);
			splitPaneOikea.setOrientation(JSplitPane.VERTICAL_SPLIT);
			
			splitPaneRunko.setRightComponent(splitPaneOikea);
			splitPaneOikeaAla.setOrientation(JSplitPane.VERTICAL_SPLIT);
			
			splitPaneOikea.setRightComponent(splitPaneOikeaAla);
			panelKappale.setPreferredSize(new Dimension(10, 282));
			
			splitPaneOikeaAla.setLeftComponent(panelKappale);
			panelKappale.setLayout(new BorderLayout(0, 0));
			
			panelKappale.add(panelKappaleOtsikko, BorderLayout.NORTH);
			panelKappaleOtsikko.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			
			panelKappaleOtsikko.add(lblKappaleet);
			scrollKappaleet.setPreferredSize(new Dimension(4, 270));
			
			panelKappale.add(scrollKappaleet);
			
			scrollKappaleet.setViewportView(listKappaleet);
			
			splitPaneOikeaAla.setRightComponent(panelMuutNapit);
			panelMuutNapit.setLayout(new BorderLayout(0, 0));
			panelMuokkaus.setPreferredSize(new Dimension(100, 10));
			
			panelMuutNapit.add(panelMuokkaus, BorderLayout.WEST);
			panelMuokkaus.setLayout(new BorderLayout(0, 0));
			
			panelMuutNapit.add(panelNapit, BorderLayout.EAST);
			panelNapit.setLayout(new BorderLayout(0, 0));
			
			panelNapit.add(panelNapitKeski, BorderLayout.CENTER);
			panelNapitKeski.setLayout(new BoxLayout(panelNapitKeski, BoxLayout.X_AXIS));
			btnPoistaLtaiK.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) {
					AvaaKyllaEi();
				}
			});
			panelNapitKeski.add(btnPoistaLtaiK);
			btnMuokkaa.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) {
					AvaaMuokkaa();
				}
			});
			panelNapitKeski.add(btnMuokkaa);
			btnLisaaLtaiK.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) {
					AvaaMuokkaa();
				}
			});
			panelNapitKeski.add(btnLisaaLtaiK);
			panelLevy.setPreferredSize(new Dimension(10, 170));
			
			splitPaneOikea.setLeftComponent(panelLevy);
			panelLevy.setLayout(new BoxLayout(panelLevy, BoxLayout.Y_AXIS));
			
			panelLevy.add(panelLevyOtsikko);
			panelLevyOtsikko.add(lblLevy);
			scrollPane.setPreferredSize(new Dimension(4, 150));
			
			panelLevy.add(scrollPane);
			
			scrollPane.setViewportView(listLevy);
			
			splitPaneRunko.setLeftComponent(panelArtisti);
			panelArtisti.setLayout(new BorderLayout(0, 0));
			
			panelArtisti.add(panelHaku, BorderLayout.NORTH);
			panelHaku.setLayout(new BoxLayout(panelHaku, BoxLayout.Y_AXIS));
			
			panelHaku.add(lblHakuehto);
			cbEhto.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					EiVielSkulaa();
				}
			});
			
			panelHaku.add(cbEhto);
			
			panelHaku.add(textHaku);
			
			panelHaku.add(lblArtisti);
			scrollArtisti.setPreferredSize(new Dimension(80, 4));
			
			panelArtisti.add(scrollArtisti);
			
			scrollArtisti.setViewportView(listArtistit);
			
			panelArtisti.add(panelArtistiNapit, BorderLayout.SOUTH);
			panelArtistiNapit.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			btnPoista.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) {
					AvaaKyllaEi();
				}
			});
			
			panelArtistiNapit.add(btnPoista);
			btnLis.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) {
					 //BNR.EiVielSkulaa();
					AvaaArtisti();
				}
			});
			
			
			panelArtistiNapit.add(btnLis);
			//===================================================    omat ohjelmat   ==========================================

		}
		

		private void setTitle(String string) {
			// TODO Auto-generated method stub
			
		}

		private void setDefaultCloseOperation(int exitOnClose) {
			// TODO Auto-generated method stub
			
		}

		private void setBounds(int i, int j, int k, int l) {
			// TODO Auto-generated method stub
			
		}

		private void setJMenuBar(JMenuBar menuBar2) {
			// TODO Auto-generated method stub
			
		}

		private void setContentPane(JPanel contentPane2) {
			// TODO Auto-generated method stub
			
		}

		/**
		 * avataaan ikkuna jossa voi lisätä uuden artistin
		 */
	   protected void AvaaArtisti() {
			 LisaaArtisti dialog = new LisaaArtisti();
			 //kerhoswing.tulostaValitut(dialog.getTextArea());
			 dialog.setVisible(true);
		}

	   
	   

		/**
		 * avataaan ikkuna jossa voidaan muokata artistin kappaleita ja levyjä
		 */
	   protected void AvaaMuokkaa() {
			 Muokkaa dialog = new Muokkaa();
			 //kerhoswing.tulostaValitut(dialog.getTextArea());
			 dialog.setVisible(true);
		}

		/**
		 * avataaan uusiikkuna
		 */
	   protected void AvaaKyllaEi() {
			 KyllaEi dialog = new KyllaEi();
			 //kerhoswing.tulostaValitut(dialog.getTextArea());
			 dialog.setVisible(true);
		}
		 

		/**
		 * avataaan huomautusikkuna
		 */
		protected void EiVielSkulaa(){
			JOptionPane.showMessageDialog(null,"tämä toiminto ei vielä toimi!!");
		}

		/**
		 * avataaan huomautusikkuna
		 */
		protected void tietoja() {
			JOptionPane.showMessageDialog(null,"Tällä ohjelmalla voit katsoa mitä eri levyjä ja\n" +
					"kappaleita artisteilta ");
			
		}

		

	}
