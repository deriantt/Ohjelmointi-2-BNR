package musiikkiarkisto;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import musiikkiarkisto.Artisti;
import musiikkiarkisto.Artistit;
import musiikkiarkisto.Levyt;
import musiikkiarkisto.Musiikkiarkisto;

import fi.jyu.mit.gui.AbstractChooser;
import fi.jyu.mit.gui.GenListChooser;
import fi.jyu.mit.gui.IStringListChooser;
import fi.jyu.mit.gui.SelectionChangeListener;
import fi.jyu.mit.gui.TextAreaOutputStream;
import BNR.*;
import BNR.SailoException;
import musiikkiarkisto.*;

import javax.swing.JList;
import javax.swing.JTextArea;
import fi.jyu.mit.gui.TextAreaOutputStream;


/**
 * @author Deniz Anttila
 * @version 15.4.2013
 *
 */
public class Musiikkiarkisto implements Iterable<Artisti>{
	
	private static Artistit artistit 	= new Artistit();
	private static Levyt levyt 			= new Levyt();
	private static Kappaleet kappaleet 	= new Kappaleet();
    /** Kun halutaan haussa nykyj‰sen valituksi haun j‰lkeen */
    public static final int NYKY = -1;
	
	private AbstractChooser<Artisti> listArtistit;
	private Artisti artistiKohdalla;

    
    
    /**
     * Asettaa tiedostojen perusnimet
     * @param nimi uusi nimi
     */
    public static void setTiedosto(String nimi) {
        artistit.setTiedostonPerusNimi(nimi);
        levyt.setTiedostonPerusNimi(nimi);
        kappaleet.setTiedostonPerusNimi(nimi);
    }
    
    public static void setTiedosto() {
    	String nimi = aloitusIkkuna2.getTxtArkistoNimi().getText();
    	setTiedosto(nimi);
    }

	
    /**
     * @return lista johon j‰senet laitetaan
     */
    public AbstractChooser<Artisti> getListArtistit() {
        return listArtistit;
    }
	
    /**
     * @param listJasenet lista johon j‰senet laitetaan
     */
    public void setListArtistit(AbstractChooser<Artisti> listArtisti) {
        this.listArtistit = listArtisti;
    }
    
    /**
     * N‰ytt‰‰ listasta valitun artistin tiedot
     */
    protected void naytaArtisti() {
        int ind = listArtistit.getSelectedIndex();
        if (ind < 0) return;
        setArtistiKohdalla(listArtistit.getSelectedObject());
        laitaArtisti();
    }
    
    /**
     * Asetetaan editoitava artisti
     * @param j uusi viite editJasenelle
     */
    private void setArtistiKohdalla(Artisti j) {
        int anro = NYKY;
        if ( j != null ) anro = j.getArtistiNro();
        artistiKohdalla = j;
    }
    
    /**
     * N‰ytt‰‰ j‰senen tiedot j‰senen alueeseen
     */
    private void laitaArtisti() {
        if (artistiKohdalla == null) return;
    }
    
    
    /**
     * Alustus
     */
    public void alusta(){
    	
    
    listArtistit.addSelectionChangeListener(new SelectionChangeListener<Artisti>() {
        @Override
        public void selectionChange(IStringListChooser<Artisti> sender) { naytaArtisti(); }
    });
    
    
    }
    
    
    //--------------------------------------------------------------------Kehitett‰vi‰-----------------------------
	/**
	 * Poistetaan Artisti musiikkiarkistosta
	 */
    public boolean poista(Artisti poistettava) {
        //boolean ret = alkiot.remove(poistettava);
        //if (ret) muutettu = true;
        //return ret;
    	return false;
    }


    /**
     * Lis‰t‰‰n uusi artisti
     */
    public void uusiArtisti() {
        Artisti artisti = new Artisti();
		artisti.rekisteroi();
		artisti.vastaa();
		lisaa(artisti);
		
		listArtistit.clear();
    }
    
    
    /**
     * Haku ehto metodi
     * @param ehto
     */
    public void hae(String ehto){
    	artistit.hae(ehto);
    
    }
    
    /**
     * Hae metodi joka hakee kaikki artistit listaan
     */
    public void haeKaikki(){
    	
    	for (int i = 0; i < artistit.alkiot.length; i++) {
            Artisti artisti = annaArtisti(i);
            if(artisti == null){
             i++;   
            }
            else{
            paaikku3.listArtistit.add(//artisti.getArtistiNro() + " " +
            		artisti.getArtistiNimi(),artisti);
            }
        }
    }
 
    /**
     * Palauttaa listan artisteista
     * @return
     */
    public static List<Artisti> haeArtistit(){
    	List <Artisti> artistitLista = new ArrayList<Artisti>();
    
    	for (int i = 0; i < artistit.alkiot.length; i++) {
            Artisti artisti = annaArtisti(i);
            if(artisti == null){
             i++;   
            }else{
            	artistitLista.add(artisti);
            }
    	}
    	return artistitLista;
    }
 
    /**
     * Palauttaa false jos artisti on jo olemassa
     * @param tunnusNro
     * @return
     */
    public static boolean lisataankoArtisti(int tunnusNro){
    	List <Artisti> artistitLista = haeArtistit();
    	
    	for(int i = 0; i < artistitLista.size(); i++){
    		int listassa = artistitLista.get(i).getArtistiNro();
    		if(listassa == tunnusNro)return false;
    	}
    	
    	return true;
    	
    }
    
    
    /**
     * Tulostaa j‰senen tiedot
     * @param os tietovirta johon tulostetaan
     * @param jasen tulostettava j‰sen
     */
    public void tulosta(PrintStream os, Artisti artisti) {
        os.println("----------------------------------------------");
        artisti.tulosta(os);
        os.println("----------------------------------------------");
    }
 
    
	/**
	 * palauttaa musiikkiarkistossa olevien ertistien lkm:n
	 * @return artistien lkm:n
	 */
	public int getArtistitLkm(){
		return artistit.getLkm();
	}
	
	/**
	 * palauttaa arkistossa olevien levyjen m‰‰r‰n
	 * @return levyjen lukum‰‰r‰
	 */
	public int getLevytLkm(){
		return levyt.getLkm();
	}
	
	/**
	 * palauttaa musiikkiarkistossa olevien kappaleiden lukum‰‰r‰n
	 * @return kappaleiden lukum‰‰r‰n
	 */
	public int getKappaleetLkm(){
		return kappaleet.getLkm();
	}
	
	/**
	 * lis‰t‰‰n arkistoon yksi artisti
	 * @param artisti lis‰tt‰v‰ artisti
	 */
	public static void lisaa(Artisti artisti){
		artistit.lisaaArtisti(artisti);
	}
	
	/**
	 * lis‰t‰‰n arkistoon yksi levy
	 * @param levy lis‰tt‰v‰ levy
	 */
	public static void lisaa(Levy levy){
		levyt.lisaaLevy(levy);
	}

	/**
	 * lis‰t‰‰n arkistoon yksi kappale
	 * @param kappale lis‰tt‰v‰ kappale
	 */
	public void lisaa(Kappale kappale){
		kappaleet.lisaa(kappale);
	}
	
    /**
     * Palauttaa i:n artistin
     * @param i monesko artisti palautetaan
     * @return viite i:teen artistiin
     * @throws IndexOutOfBoundsException jos i v‰‰rin
     */
    public static Artisti annaArtisti(int i) throws IndexOutOfBoundsException {
        return artistit.annaArtisti(i);
    }
    
    
    
    /**
     * annetaan haluttu levy
     * @param HaluttuLevyNro
     * @return haluttu levy
     */
    public Levy annaLevy(int HaluttuLevyNro){
    	return levyt.annaLevy(HaluttuLevyNro);
    }
    
    /**
     * palauttaa i:n kappaleen
     * @param i monesko kappale palautetaan
     * @return viite i:teen kappaleeseen
     */
    public Iterator<Kappale> annaKappale(int i){
    	return kappaleet.annaKappale(i);
    }
    
    
    /**
     * palautetaan kaikki artistin levyt
     * @param artistiNro artisti jolta levyj‰ palautetaan
     * @return apu lista palautettavista levyist‰
     */
    public List<Levy> haeArtistinLevyt(int artistiNro){
    	List<Levy> apu = Levyt.getlistaArtistinLevyista(artistiNro);
    	return apu;
    }
    

    /**
     * palautetaan kaikki levyll‰ olevat kappaleet
     * @param levyNro levy jolta kappaleet palautetaan
     * @return apu palautettavista kappaleista
     */
    public static List<Kappale> haeLevynKappaleet(int levyNro){
    	List<Kappale> apu = kappaleet.getListaKappaleista(levyNro);
		return apu;
    }
    
    
    /** 
     * Kaikien j‰senten iteraattori 
     * @return j‰seniteraattori 
     */ 
    @Override 
    public Iterator<Artisti> iterator() { 
        return artistiIterator(); 
    } 
	 
	 
    /** 
     * Kaikien j‰senten iteraattori 
     * @return j‰seniteraattori 
     */ 
    public Iterator<Artisti> artistiIterator() { 
        return artistit.iterator(); 
    } 

    /** 
     * Kaikkien harrastusten iteraattori 
     * @return harrastusiteraattori 
     */ 
    public Iterator<Levy> levyIterator() { 
        return levyt.iterator(); 
    }
    
    
    
    
    /**
     * Asetetaan tiedoston nimi ja k‰skytet‰‰n luokat lukemaan tiedosto 
     * @param nimi
     * @throws SailoException
     * @throws musiikkiarkisto.SailoException
     */
    public static void lueTiedostosta(String nimi) throws SailoException, musiikkiarkisto.SailoException { 
        artistit = new Artistit(); // jos luetaan olemassa olevaan niin helpoin tyhjent‰‰ n‰in 
        levyt = new Levyt(); 
    
        artistit.setTiedostonPerusNimi(nimi); 
     	levyt.setTiedostonPerusNimi(nimi); 
        artistit.lueTiedostosta(nimi); 
        levyt.lueTiedostosta(nimi); 
        artistit.displayAll();
        }     
    
    
    public void talleta() throws SailoException{
    	String virhe = "";
    
    	try {
			artistit.talleta();
		} catch (musiikkiarkisto.SailoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    	if ( !"".equals(virhe))throw new SailoException(virhe);
    }
    
    
    
    
    
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/**
		Musiikkiarkisto arkisto = new Musiikkiarkisto();
		
		
		Artisti Miles = new Artisti(), Davis = new Artisti(), 
				bob = new Artisti();
		
		Miles.rekisteroi();
		Davis.rekisteroi();
		bob.rekisteroi();
		
		Miles.vastaa();
		Davis.vastaa();
		bob.vastaa();
		
		arkisto.lisaa(Miles);
		arkisto.lisaa(Davis);
		arkisto.lisaa(bob);
		
		
		
		Levy l1 = new Levy(bob.getArtistiNro());
		l1.rekisteroi();
		l1.vastaaLevy();
		
		Levy l2 = new Levy(bob.getArtistiNro());
		l2.rekisteroi();
		l2.vastaaLevy();
		
		
		Levy l3 = new Levy(bob.getArtistiNro());
		l3.rekisteroi();
		l3.vastaaLevy();
		
		
		Levy l4 = new Levy(Miles.getArtistiNro());
		l4.rekisteroi();
		l4.vastaaLevy();
		
		arkisto.lisaa(l1);
		arkisto.lisaa(l2);
		arkisto.lisaa(l3);
		arkisto.lisaa(l4);
		
		
		Kappale kp = new Kappale(l4.getLevyNro());
		Kappale kp2 = new Kappale(l4.getLevyNro()), kp3 = new Kappale(l4.getLevyNro()), kp4 = new Kappale(l3.getLevyNro());

		kp.rekisteroi();
		kp.vastaaKappale();
		//kp.tulosta(System.out);
		kp2.rekisteroi();
		kp2.vastaaKappale();
		//kp2.tulosta(System.out);

		kp3.rekisteroi();
		kp3.vastaaKappale();
		//kp3.tulosta(System.out);
		kp4.rekisteroi();
		kp4.vastaaKappale();
		//kp4.tulosta(System.out);		
		arkisto.lisaa(kp);
		arkisto.lisaa(kp2);
		arkisto.lisaa(kp3);
		arkisto.lisaa(kp4);
		
		
		
		//Levy[] apu = arkisto.haeArtistinLevyt(bob.getArtistiNro());
		//for (int i = 0; i < apu.length ; i++){
		//	apu[i].tulosta(System.out);
		//}
		
		System.out.println("======================");
		
		
		
		//Levy[] apu2 = arkisto.haeArtistinLevyt(Miles.getArtistiNro());
		//for (int i = 0; i < apu2.length ; i++){
		//	apu2[i].tulosta(System.out);
		//}
		
		l4.tulosta(System.out);
		
		System.out.println("==========Kappale testi============");
		/**
		List<Kappale> kappaleet = arkisto.haeLevynKappaleet(l4.getlevyNro());
		for (int i = 0; i < kappaleet.size(); i++){
			List<Kappale>kappaleet[i].tulosta
		}
		*/
		/**
        List<Kappale> Kappaleet2 = arkisto.haeLevynKappaleet(l4.getLevyNro());

        
        for (Kappale kap : Kappaleet2) {
            System.out.println( + kap.getLevyNro() + " ");
            kap.tulosta(System.out);
        }
		System.out.println("==========Artistin Levyt testi============");
		
		Artisti Brian = new Artisti();
		Brian.setNimi("Adams");
		Brian.rekisteroi();
		
		arkisto.lisaa(Brian);
		
		Levy Testi1 = new Levy(Brian.getArtistiNro());
		Testi1.setLevyNimi("Parhaat");
        Testi1.setGenre("Rock");
        Testi1.setJulkaisuVuosi(2012);
		Testi1.rekisteroi();
		
		Levy Testi2 = new Levy(Brian.getArtistiNro());
		Testi2.setLevyNimi("Ei niin hyv‰t");
        Testi2.setGenre("Rock");
        Testi2.setJulkaisuVuosi(2012);
		Testi2.rekisteroi();
		
		Levy Testi3 = new Levy(Brian.getArtistiNro());
		Testi3.setLevyNimi("Surkeimmat");
        Testi3.setGenre("Rock");
        Testi3.setJulkaisuVuosi(2012);
		Testi3.rekisteroi();
		
		arkisto.lisaa(Testi1);
		arkisto.lisaa(Testi2);
		arkisto.lisaa(Testi3);
		

		List<Levy> testi = Levyt.getlistaArtistinLevyista(Brian.getArtistiNro());

		System.out.println("Levyj‰: " + testi.size());
		
		System.out.println("T‰ss‰ Brianin Levyt: ");
		
		for (int i = 0; i < testi.size() ; i++){
			testi.get(i).tulosta(System.out);
		}
		
		System.out.println("==========================================");
		
		System.out.println("================Kappale testi=============");
		
		Kappale testiBiisi = new Kappale(Testi1.getLevyNro());
		testiBiisi.setKappaleNimi("Paras biisi ikin‰");
		testiBiisi.rekisteroi();
		arkisto.lisaa(testiBiisi);
		
		Kappale testiBiisi2 = new Kappale(Testi1.getLevyNro());
		testiBiisi2.setKappaleNimi("Tokaksi Paras biisi ikin‰");
		testiBiisi2.rekisteroi();
		arkisto.lisaa(testiBiisi2);
		
		Kappale testiBiisi3 = new Kappale(Testi1.getLevyNro());
		testiBiisi3.setKappaleNimi("Kolmas Paras biisi ikin‰");
		testiBiisi3.rekisteroi();
		arkisto.lisaa(testiBiisi3);
		
		List<Kappale> testikpl = Kappaleet.getListaKappaleista(testiBiisi.getLevyNro());
		
		for (int i = 0; i < testikpl.size() ; i++){
			testikpl.get(i).tulosta(System.out);
		} 
		System.out.println("==========================================");
		
		
		*/
		System.out.println("============Tiedosto luku testi===================");
		
		setTiedosto("BNR");
		 List<Artisti> android = new ArrayList<Artisti>();
	       
	       
	        //Getting Iterator from List
	        Iterator<Artisti> iterator = android.iterator();
	       
	        System.out.println("Size of List before Iteratation " + android.size());
	       
	        //Using Iterator to iterate over List, acces object one by one
	        while(iterator.hasNext()){
	            Artisti version = iterator.next();
	            
	            System.out.println(version);
		
		
		System.out.println("==========================================");

	}}

}
