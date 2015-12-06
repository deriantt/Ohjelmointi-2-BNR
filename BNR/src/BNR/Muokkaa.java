package BNR;

//import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JDialog;
//import javax.swing.JMenuBar;
//import javax.swing.JMenu;
//import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Point;
//import java.awt.FlowLayout;
//import javax.swing.BoxLayout;
import javax.swing.UIManager;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;

import musiikkiarkisto.*;
import musiikkiarkisto.SailoException;

import javax.swing.AbstractListModel;
import fi.jyu.mit.gui.GenListChooser;

public class Muokkaa extends JDialog {

	/**
	 * @author Deniz Anttila
	 * @version 0.0.1
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final JPanel panel 				= new JPanel();
	private final JLabel lblLevy 			= new JLabel("Levy");
	private final JLabel lblKappaleet 		= new JLabel("Kappaleet");
	private final String[] genret 			= { "Genret", "House", "Rock", "Rap", "Country", "Classic", "Indie" };
	private final JComboBox genreListBox 	= new JComboBox(genret);
	private final JSplitPane splitPane 		= new JSplitPane();
	private final JSplitPane splitPane_1 	= new JSplitPane();
	private final JTextField textKappale 	= new JTextField();
	private final JButton btnLisaaLevy 		= new JButton("Lis\u00E4\u00E4 Levy");
	private final JButton btnPoistaLevy 	= new JButton("Poista Levy");
	private final JButton btnLisaaKappale 	= new JButton("Lis\u00E4\u00E4 Kappale");
	private final JButton btnPoistaKappale 	= new JButton("Poista Kappale");
	private final JSplitPane splitPane_2 	= new JSplitPane();
	private final JSplitPane splitPane_3 	= new JSplitPane();
	private final JScrollPane scrollPane_1 	= new JScrollPane();
	private final JScrollPane scrollPane 	= new JScrollPane();
	private final JTextField textLevy 		= new JTextField();
	private final JButton btnSulje 			= new JButton("Sulje");
	private final static GenListChooser<Levy> listLevyja 		= new GenListChooser<Levy>();
	private final static GenListChooser<Kappale> listKappaleita = new GenListChooser<Kappale>();
	private final JTextField textVuosi 		= new JTextField();
	public JTextField getTextLevy() {
		return textLevy;
	}
	public JTextField getTextKappale() {
		return textKappale;
	}
	
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
					Muokkaa frame = new Muokkaa();
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
	@SuppressWarnings("serial")
	public Muokkaa() {
		textVuosi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				textVuosi.setText(null);
			}
		});
		textVuosi.setToolTipText("vvvv");
		textVuosi.setText(" 2013");
		textVuosi.setColumns(10);
		setModal(true);
		textLevy.setToolTipText("Lis\u00E4\u00E4 uusi levy");
		textLevy.setColumns(10);
		textKappale.setToolTipText("Lis\u00E4\u00E4 uusi kappale");
		textKappale.setPreferredSize(new Dimension(10, 20));
		textKappale.setColumns(10);
		setTitle("Muokkaa");
		paivitaLevyLista();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 513, 516);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		contentPane.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		GridBagConstraints gbc_lblLevy = new GridBagConstraints();
		gbc_lblLevy.insets = new Insets(0, 0, 5, 5);
		gbc_lblLevy.gridx = 0;
		gbc_lblLevy.gridy = 0;
		panel.add(lblLevy, gbc_lblLevy);
		
		GridBagConstraints gbc_textLevy = new GridBagConstraints();
		gbc_textLevy.insets = new Insets(0, 0, 5, 5);
		gbc_textLevy.fill = GridBagConstraints.HORIZONTAL;
		gbc_textLevy.gridx = 0;
		gbc_textLevy.gridy = 1;
		panel.add(textLevy, gbc_textLevy);
		
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 0;
		gbc_scrollPane_1.gridy = 2;
		panel.add(scrollPane_1, gbc_scrollPane_1);
		listLevyja.getList().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				Levy valittu = listLevyja.getSelectedObject();
				if(valittu == null)return;
				listKappaleita.clear();
				paivitaKappaleLista();
				
				String vuosi = Integer.toString(valittu.getjulkaisuVuosi());
				textVuosi.setText(vuosi);
				
				String genre = valittu.getGenre();
				int index;
				for (int i = 0; i < genret.length; i++) {
				    if(genre.equals(genret[i])) {
				        index = i;
				    }
				}
				
				//TODO Valitse valmiiksi valitun levyn genre comboboxiin
				//genreListBox.select(index);
				//genreListBox.setModel(new DefaultComboBoxModel(new String[] {genre}));
			}
		});
		
		scrollPane_1.setViewportView(listLevyja);
		
		GridBagConstraints gbc_splitPane = new GridBagConstraints();
		gbc_splitPane.anchor = GridBagConstraints.NORTH;
		gbc_splitPane.insets = new Insets(0, 0, 5, 5);
		gbc_splitPane.fill = GridBagConstraints.HORIZONTAL;
		gbc_splitPane.gridx = 1;
		gbc_splitPane.gridy = 2;
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		panel.add(splitPane, gbc_splitPane);
		splitPane_2.setOrientation(JSplitPane.VERTICAL_SPLIT);
		
		
		
		splitPane.setLeftComponent(splitPane_2);
		
		splitPane_2.setRightComponent(textVuosi);
		splitPane_2.setLeftComponent(genreListBox);
		genreListBox.setPreferredSize(new Dimension(40, 20));
		genreListBox.setName("Genre");
		genreListBox.setToolTipText("Valitse genre");
		splitPane_3.setOrientation(JSplitPane.VERTICAL_SPLIT);
		
		splitPane.setRightComponent(splitPane_3);
		btnLisaaLevy.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				String uusilevy = textLevy.getText();
				
				Artisti muokattava = paaikku3.listArtistit.getSelectedObject();
				int anro = muokattava.getArtistiNro();
				Levy uusiLevy = new Levy(anro);
				uusiLevy.setTunnusNro(anro);
				String levynimi = getTextLevy().getText();

				if(uusilevy.equals(""))NaytaEiTekstia();
				if(textVuosi.getText().isEmpty()|| textVuosi.getText() == null){
					lisaaVuosi();
				}
				else{
					
					Levyt hylly = new Levyt();

					uusiLevy.setLevyNimi(levynimi);
					
					String genre = (String) genreListBox.getSelectedItem();
			        uusiLevy.setGenre(genre);
			        
			        String vuosi = textVuosi.getText();
			        //if(vuosi == "Lisää julkaisuvuosi")EiVielSkulaa();
			        //else{
			        	int Jvuosi = Integer.parseInt(vuosi);
			        uusiLevy.setJulkaisuVuosi(Jvuosi);
			        //}
			        
			        //uusiLevy.rekisteroi();
					hylly.lisaaLevy(uusiLevy);
					listLevyja.add(levynimi, uusiLevy);
					textLevy.setText(null);
				}
			}

			private void NaytaEiTekstia() {
				JOptionPane.showMessageDialog(null,"Lisää nimi tekstikenttään");
				
			}
		});
		splitPane_3.setLeftComponent(btnLisaaLevy);
		btnPoistaLevy.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				EiVielSkulaa();
			}
		});
		splitPane_3.setRightComponent(btnPoistaLevy);
		
		GridBagConstraints gbc_lblKappaleet = new GridBagConstraints();
		gbc_lblKappaleet.insets = new Insets(0, 0, 5, 5);
		gbc_lblKappaleet.gridx = 0;
		gbc_lblKappaleet.gridy = 3;
		panel.add(lblKappaleet, gbc_lblKappaleet);
		
		GridBagConstraints gbc_textKappale = new GridBagConstraints();
		gbc_textKappale.fill = GridBagConstraints.HORIZONTAL;
		gbc_textKappale.insets = new Insets(0, 0, 5, 5);
		gbc_textKappale.gridx = 0;
		gbc_textKappale.gridy = 5;
		panel.add(textKappale, gbc_textKappale);
		
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 6;
		panel.add(scrollPane, gbc_scrollPane);
		
		scrollPane.setViewportView(listKappaleita);
		
		GridBagConstraints gbc_splitPane_1 = new GridBagConstraints();
		gbc_splitPane_1.anchor = GridBagConstraints.NORTH;
		gbc_splitPane_1.insets = new Insets(0, 0, 5, 5);
		gbc_splitPane_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_splitPane_1.gridx = 1;
		gbc_splitPane_1.gridy = 6;
		splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		panel.add(splitPane_1, gbc_splitPane_1);
		btnLisaaKappale.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				String uusikappale = textKappale.getText();
				if(uusikappale.equals(""))NaytaEiTekstia();
				else{
					Levy muokattava = listLevyja.getSelectedObject();
					
					if(muokattava == null)return;
					
					Kappale uusiKappale = new Kappale(muokattava.getLevyNro());
					Kappaleet levy = new Kappaleet();
					
					String kappaleNimi = getTextKappale().getText();
					uusiKappale.setKappaleNimi(kappaleNimi);
					uusiKappale.rekisteroi();
					
					levy.lisaa(uusiKappale);
					listKappaleita.add(kappaleNimi, uusiKappale);
					textKappale.setText(null);
				}
			}
			
		private void NaytaEiTekstia() {
			JOptionPane.showMessageDialog(null,"Lisää nimi tekstikenttään");
			
		}
		});
		
		splitPane_1.setLeftComponent(btnLisaaKappale);
		btnPoistaKappale.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				EiVielSkulaa();
			}
		});
		
		splitPane_1.setRightComponent(btnPoistaKappale);
		
		GridBagConstraints gbc_btnSulje = new GridBagConstraints();
		gbc_btnSulje.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSulje.insets = new Insets(0, 0, 0, 5);
		gbc_btnSulje.gridx = 1;
		gbc_btnSulje.gridy = 7;
		btnSulje.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				paaikku3.listLevy.clear();
				paaikku3.paivitaLevyLista();
				paaikku3.paivitaKappaleLista();
				
				Artistit.muutettu = true;
				paaikku3.tallennus();
				
				setVisible(false);
				
			}
		});
		panel.add(btnSulje, gbc_btnSulje);
	}


	/**
	 * Päivittää levylistan pääikkunassa valitun artistin mukaan
	 * 
	 */
	public static void paivitaLevyLista() {
		listLevyja.clear();
		Artisti muokattu = paaikku3.listArtistit.getSelectedObject();
		if(muokattu == null)return;
		int anro = muokattu.getArtistiNro();
	
		ArrayList<Levy> valitunLevyt = (ArrayList<Levy>) Levyt.getlistaArtistinLevyista(anro);
		
        for (int i = 0; i < valitunLevyt.size(); i++) {
            Levy apulevy = valitunLevyt.get(i);
            listLevyja.add(apulevy.getLevyNimi(),apulevy);
        }
	}
	/**
	 * Päivittää kappalesta valitun levyn mukaan
	 * 
	 */
	public static void paivitaKappaleLista() {
		Levy valittu = listLevyja.getSelectedObject();
		if (valittu == null)return;
		ArrayList<Kappale> biisiLista = (ArrayList<Kappale>) Kappaleet.getListaKappaleista(valittu.getLevyNro());
		
		for (int i = 0; i < biisiLista.size() ; i++) {
            Kappale apuKpl = biisiLista.get(i);
            listKappaleita.add(apuKpl.getKappaleNimi(),apuKpl);
        }
	}
	
	/**
	 * avataaan huomautusikkuna
	 */
	protected void EiVielSkulaa(){
		JOptionPane.showMessageDialog(null,"tämä toiminto ei vielä toimi!!");
	}
	protected void LisaaLevy(String fak){
		JOptionPane.showMessageDialog(null,"Lisäät Levyn nimeltä: " + fak);
	}
	protected void LisaaKappale(String fak){
		JOptionPane.showMessageDialog(null,"Lisäät kappaleen nimeltä: " + fak);
	}
	
	/**
	 * avataaan huomautusikkuna
	 */
	protected void lisaaVuosi(){
		JOptionPane.showMessageDialog(null,"Lisää julkaisuvuosi");
	}
}
