package BNR;


import musiikkiarkisto.*;
import musiikkiarkisto.SailoException;
import fi.jyu.mit.gui.GenListChooser;


import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;

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
import javax.swing.JTextArea;
import javax.swing.AbstractListModel;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class paaikku3 extends JFrame {

	/**
	 * @author Deniz Anttila
	 * @version 0.0.1
	 */
	private static final long serialVersionUID 		= 1L;
	public static final int NYKY 					= -1;
	private JPanel contentPane;
	private final JSplitPane splitPaneRunko 		= new JSplitPane();
	private final JSplitPane splitPaneOikea 		= new JSplitPane();
	private final JSplitPane splitPaneOikeaAla 		= new JSplitPane();
	private final JPanel panelArtisti 				= new JPanel();
	private final JPanel panelHaku 					= new JPanel();
	private final JScrollPane scrollArtisti 		= new JScrollPane();
	private final JPanel panelArtistiNapit 			= new JPanel();
	private final JLabel lblHakuehto 				= new JLabel("Hakuehto");
	private final JComboBox cbEhto 					= new JComboBox();
	private final JTextField textHaku 				= new JTextField();
	private final JButton btnPoistaArtisti 			= new JButton("Poista");
	private final JButton btnLisaaArtisti 			= new JButton("Lis\u00E4\u00E4");
	private final JPanel panelLevy 					= new JPanel();
	private final JLabel lblLevy 					= new JLabel("Levy");
	private final JPanel panelLevyOtsikko 			= new JPanel();
	private final JScrollPane scrollPane 			= new JScrollPane();
	private final JPanel panelKappale 				= new JPanel();
	private final JPanel panelKappaleOtsikko 		= new JPanel();
	private final JLabel lblKappaleet 				= new JLabel("Kappaleet");
	private final JScrollPane scrollKappaleet 		= new JScrollPane();
	private final JPanel panelMuutNapit 			= new JPanel();
	private final JPanel panelMuokkaus 				= new JPanel();
	private final JPanel panelNapit 				= new JPanel();
	private final JButton btnPoistaLtaiK 			= new JButton("Poista");
	private final JButton btnMuokkaa 				= new JButton("Muokkaa");
	private final JMenuBar menuBar 					= new JMenuBar();
	private final JPanel panelNapitKeski 			= new JPanel();
	private final JMenu mnFile 						= new JMenu("File");
	private final JMenu mnHelp 						= new JMenu("Help");
	private final JMenuItem mntmNew 				= new JMenuItem("New");
	private final JMenuItem mntmAbout 				= new JMenuItem("About");
	final static Musiikkiarkisto arkisto 			= new Musiikkiarkisto();
	public static final GenListChooser<Artisti> listArtistit = new GenListChooser<Artisti>();
	private static final Levyt levyt 				= new Levyt();
	private static final Levy levy 					= new Levy(0);
	public static final GenListChooser<Levy> listLevy = new GenListChooser<Levy>();
	public static final GenListChooser<Kappale> listKappale = new GenListChooser<Kappale>();
	private final static Musiikkiarkisto musiikkiarkisto 	= new Musiikkiarkisto();
	//private final String[] columnNames = { "Levy", "Genre", "Julkaisuvuosi" };
	//ArrayList<Levy> valitunLevyt = (ArrayList<Levy>) Levyt.getlistaArtistinLevyista(listArtistit.getSelectedObject().getArtistiNro());
	//private final JTable tableLevyt = new JTable();
	
 	/**
	 * Launch the application.
	 * @param args 
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					paaikku3 frame = new paaikku3();
					frame.setVisible(true);
					
					//paivitaArtistiLista();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public paaikku3() {
		Artistit.setEditHaku(textHaku);
		textHaku.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if(textHaku.getText().isEmpty()){
					listArtistit.clear();
					paivitaArtistiLista();
				}else{
				arkisto.hae(textHaku.getText());
				}
				
			}
		});
		textHaku.setColumns(10);
		setTitle("Musiikkiarkisto");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
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
		
		scrollKappaleet.setViewportView(listKappale);
		
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
		panelLevy.setPreferredSize(new Dimension(10, 170));
		
		splitPaneOikea.setLeftComponent(panelLevy);
		panelLevy.setLayout(new BoxLayout(panelLevy, BoxLayout.Y_AXIS));
		
		panelLevy.add(panelLevyOtsikko);
		panelLevyOtsikko.add(lblLevy);
		scrollPane.setPreferredSize(new Dimension(4, 150));
		
		panelLevy.add(scrollPane);
		listLevy.getList().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				listKappale.clear();
				paivitaKappaleLista();
			}
		});
		
		scrollPane.setViewportView(listLevy);
		
		
		
		splitPaneRunko.setLeftComponent(panelArtisti);
		panelArtisti.setLayout(new BorderLayout(0, 0));
		
		panelArtisti.add(panelHaku, BorderLayout.NORTH);
		panelHaku.setLayout(new BoxLayout(panelHaku, BoxLayout.Y_AXIS));
		lblHakuehto.setHorizontalAlignment(SwingConstants.CENTER);
		
		panelHaku.add(lblHakuehto);
		cbEhto.setModel(new DefaultComboBoxModel(new String[] {"Nimi"}));
		cbEhto.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				EiVielSkulaa();
			}
		});
		
		panelHaku.add(cbEhto);
		
		panelHaku.add(textHaku);
		scrollArtisti.setPreferredSize(new Dimension(80, 4));
		
		panelArtisti.add(scrollArtisti);
		listArtistit.getList().setToolTipText("Artisteja");
		listArtistit.getList().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				listLevy.clear();
				listKappale.clear();
				paivitaLevyLista();
				//paivitaKappaleLista();
			}
		});
		
		scrollArtisti.setViewportView(listArtistit);
		
		panelArtisti.add(panelArtistiNapit, BorderLayout.SOUTH);
		panelArtistiNapit.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		btnPoistaArtisti.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				AvaaKyllaEi();
			}
		});
		btnLisaaArtisti.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				try {
					AvaaArtisti();
				} catch (SailoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		
		panelArtistiNapit.add(btnLisaaArtisti);
		
		panelArtistiNapit.add(btnPoistaArtisti);
		

	}
    /**
     * Alustaa arkiston lukemalla sen valitun nimisest‰ tiedostosta
     * @param nimi tiedosto josta arkiston tiedot luetaan
     * @return true jos lukeminen onnistuu
     */
    protected boolean lueTiedosto(String nimi) {
        //arkistonnimi = nimi;
        //setTitle("Arkisto - " + arkistonnimi);
        //String virhe = Musiikkiarkisto.lueTiedosto(nimi);
        //if (virhe == null) return true;
       
        //JOptionPane.showMessageDialog(null, virhe);
        return false;
    }
	

	/**
	 * avataaan ikkuna jossa voi lis‰t‰ uuden artistin
	 * @throws SailoException 
	 */
   protected void AvaaArtisti() throws SailoException {
	   	
	   listArtistit.clear();
	   
		 LisaaArtisti dialog = new LisaaArtisti();
		 dialog.setVisible(true);
		 if(dialog.isUusi() == false){
			 arkisto.haeKaikki();
			 return;
		 }
		 String nimi = dialog.getTxtnimi().getText();
		 Artisti uusi = new Artisti();
		 uusi.setNimi(nimi);
		 uusi.rekisteroi();
		 Artistit.lisaaArtisti(uusi);
		 arkisto.haeKaikki();
		 try {
				Artistit.muutettu = true;
				musiikkiarkisto.talleta();
			} catch (BNR.SailoException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
	}

   
   

	/**
	 * avataaan ikkuna jossa voidaan muokata artistin kappaleita ja levyj‰
	 */
   protected void AvaaMuokkaa() {
		 Muokkaa dialog = new Muokkaa();
		 int index = -1;
		 index = listArtistit.getSelectedIndex();
		 if(index == -1){
			 dialog.setVisible(false);
			 ValitseArtisti();
		 }
		 else{
			 	dialog.setVisible(true);
		 }
		 
	}

	/**
	 * avataaan uusiikkuna
	 */
   protected void AvaaKyllaEi() {
		 KyllaEi dialog = new KyllaEi();
		 dialog.setVisible(true);
		 if(dialog.isUusi() == false){
			 return;
		 }
		 int poistettava = listArtistit.getSelectedObject().getArtistiNro();
		 //Artistit.poistaArtisti(poistettava);
		 //Artisti poistettava = listArtistit.getSelectedObject();
		 //poistettava = null;
		 listArtistit.clear();
		 try {
			Artistit.lueTiedostosta(Artistit.getTiedostonPerusNimi());
		} catch (SailoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 arkisto.haeKaikki();
	}
	 

	/**
	 * avataaan huomautusikkuna
	 */
	protected void ValitseArtisti(){
		JOptionPane.showMessageDialog(null,"Valitse artisti luettelosta");
	}
   
	/**
	 * avataaan huomautusikkuna
	 */
	protected void EiVielSkulaa(){
		JOptionPane.showMessageDialog(null,"t‰m‰ toiminto ei viel‰ toimi!!");
	}

	/**
	 * avataaan huomautusikkuna
	 */
	protected void tietoja() {
		JOptionPane.showMessageDialog(null,"T‰ll‰ ohjelmalla voit katsoa mit‰ eri levyj‰ ja\n" +
				"kappaleita artisteilta ");	
	}
	
	/**
	 * Alustetaan musiikkiarkisto
	 * @return arkisto
	 */
	protected Musiikkiarkisto getMusiikkiarkisto(){
		return arkisto;
	}

	/**
	 * P‰ivitt‰‰ ohjelman listan artisteista
	 */
	public static void paivitaArtistiLista(){
		arkisto.haeKaikki();
	}
	
	/**
	 * P‰ivitt‰‰ ohjelman listan levyist‰
	 */
	public static void paivitaLevyLista() {
		Artisti valittu = listArtistit.getSelectedObject();
		if(valittu == null)return;
		int anro = valittu.getArtistiNro();
	
		
		ArrayList<Levy> valitunLevyt = (ArrayList<Levy>) Levyt.getlistaArtistinLevyista(anro);
		
        for (int i = 0; i < valitunLevyt.size(); i++) {
            Levy apulevy = valitunLevyt.get(i);
            paaikku3.listLevy.add(apulevy.getLevyNimi(),apulevy);
        }
	}

	/**
	 * P‰ivitt‰‰ ohjelman listan kappaleista
	 */
	public static void paivitaKappaleLista() {
		Levy valittu = listLevy.getSelectedObject();
		if (valittu == null)return;
		
		ArrayList<Kappale> biisiLista = (ArrayList<Kappale>) Kappaleet.getListaKappaleista(valittu.getLevyNro());
		
		for (int i = 0; i < biisiLista.size() ; i++) {
            Kappale apuKpl = biisiLista.get(i);
            paaikku3.listKappale.add(apuKpl.getKappaleNimi(),apuKpl);
        }
		
	}	
	
	public static void tallennus(){
		try {
			Artistit.muutettu = true;
			musiikkiarkisto.talleta();
		} catch (BNR.SailoException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	}
}
