package musiikkiarkisto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.io.*;

import java.util.NoSuchElementException; 

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import musiikkiarkisto.*;

import BNR.paaikku3;

/**
 * @author Deniz Anttila
 * @version 15.4.2013
 *
 */
public class Artistit implements Iterable<Artisti>{
	
	
	private static final int MAX_ARTISTEJA 			= 1;
	public static Artisti [] alkiot					= new Artisti [MAX_ARTISTEJA];
	private static int lkm 							= 0;
	private static Artisti apu 						= new Artisti();
	public static boolean muutettu 					= false;
	private static String tiedostonPerusNimi 		= "";
	private static String kokoNimi 					= ""; 
	public static final String arkistonNimi 		= "";
	private static JTextField textHaku;
	private static Artisti jasenKohdalla;
	private Artisti editArtisti;
	public static Musiikkiarkisto arkisto			= new Musiikkiarkisto(); 
	private final static ArrayList<Artisti> alkiotTesti = new ArrayList<Artisti>();
	
	/**
	 * oletus muodostaja
	 */
	public Artistit(){
		//super(new Artisti());
	}

	 /** 
	  * Lis‰‰ uuden artistin tietorakenteeseen.  Ottaa artistin omistukseensa. 
	  * @param ar lis‰tt‰v‰ artisti.  Huom tietorakenne muuttuu omistajaksi 
	  */ 
    public static void lisaa(Artisti ar) throws SailoException{ 
    	if( lkm > alkiotTesti.size()) 
    		throw new SailoException("Liikaa alkioita");
    	alkiotTesti.add(ar);
    	lkm++;
    	muutettu = true; 
	    } 

	/**
	 * palautetaan artistien m‰‰r‰
	 * @return lkm
	 */
	public int getLkm(){
		return lkm;
	}

	
	
	/**
	 * Lis‰t‰‰n yksi artisti
	 * @param artisti lis‰tt‰v‰ artisti
	 */
	public static void lisaaArtisti(Artisti artisti){
		if (lkm >= alkiot.length) lisaaAlkioita();
		alkiot [lkm] = artisti;
		lkm++;
	}
	


	public static void lueTiedostosta(String tied) throws SailoException { 
		setTiedostonPerusNimi(tied); 
        try { BufferedReader fi = new BufferedReader(new FileReader(getTiedostonNimi())) ;  
            kokoNimi = fi.readLine(); 
            if ( kokoNimi == null ) throw new SailoException("Arkiston nimi puuttuu"); 
		 	String rivi = fi.readLine(); 
		 	if ( rivi == null ) throw new SailoException("Maksimikoko puuttuu"); 
		 	// int maxKoko = Mjonot.erotaInt(rivi,10); // tehd‰‰n jotakin 
		 	while ( (rivi = fi.readLine()) != null ) { 
		 		rivi = rivi.trim(); 
		 	    if ( "".equals(rivi) || rivi.charAt(0) == ';' ) continue;
		 	    
		 	   String testiRivi = rivi; 
		 	   String prefix = "";
		 	   String[] kentat = testiRivi.substring(prefix.length()).split("/");
		       int tunnusNro = Integer.parseInt(kentat[0]);
		       
		       
		       
		       if(Musiikkiarkisto.lisataankoArtisti(tunnusNro)){
		    	   Artisti artisti = new Artisti(); 
		    	   artisti.parse(rivi);
		 	    
		    	   artisti.rekisteroi();
		    	   arkisto.lisaa(artisti);
		       }
		      
		 	    } 
		 	    muutettu = false; 
		 	    } catch ( FileNotFoundException e ) { 
		 	      throw new SailoException("Tiedosto " + getTiedostonNimi() + " ei aukea"); 
		 	    } catch ( IOException e ) { 
		 	     throw new SailoException("Ongelmia tiedoston kanssa: " + e.getMessage()); 
		 	    } 
		 	} 
	
	
	
	public static void talleta() throws SailoException, IOException{
		if ( !muutettu ) return;
		
		FileOutputStream erasor = new FileOutputStream(getTiedostonNimi());
		erasor.write((new String()).getBytes());
		erasor.close();
	
		
		String tiedNimi = getTiedostonNimi();
        
		
		try {PrintStream fo = new PrintStream(new FileOutputStream(tiedNimi, true)); {
			fo.println(getTiedostonNimi());
			fo.println(alkiot.length);
			for (int i=0; i < alkiot.length; i++ ){//Artisti artisti : this){
				Artisti apu = alkiot[i];
				List<Levy> artistinLevyt = Levyt.getlistaArtistinLevyista(apu.getArtistiNro());
					
					
					if(artistinLevyt.isEmpty()){
						fo.println(apu);
					}else{
					for(int iL = 0; iL < artistinLevyt.size(); iL++ ){
						Levy apuLevy = artistinLevyt.get(iL);
						List <Kappale> levynKappaleet = Kappaleet.getListaKappaleista(apuLevy.getLevyNro());
						if(artistinLevyt.isEmpty()){
							fo.print(apu);
							fo.println(apuLevy.toString());
						}else{
								fo.print(apu);
								fo.print(apuLevy.toString());
								fo.println(Kappale.toString(apuLevy.getLevyNro()));
							}
						}
					
					}
						
				}
			}
		} catch (FileNotFoundException ex) {
            System.err.println("Tiedosto ei aukea: " + ex.getMessage());
        }
    
			muutettu = false;
			
	}
	/**
	public static void poistaArtisti(int artistiNro){
		Artisti poistettava = Artistit.etsiArtistiListasta(artistiNro);
		poistettava = null;
		try {
			talleta();
		} catch (SailoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
		/**
		 * Palauttaa arkiston kokonimen
		 * @return
		 */
		public static String getKokoNimi(){
			return kokoNimi;
		}
	

	
    /** 
     * Asettaa tiedoston perusnimen ilan tarkenninta 
     * @param tied tallennustiedoston perusnimi 
     */ 
		public static String getTiedostonPerusNimi() { 
 	        return tiedostonPerusNimi; 
 	    } 
	 /** 
      * Asettaa tiedoston perusnimen ilman tarkenninta 
	  * @param tied tallennustiedoston perusnimi 
	  */ 
		public static void setTiedostonPerusNimi(String tied) { 
	        tiedostonPerusNimi = tied; 
	    } 
		
		
	 /** 
	  * Palauttaa tiedoston nimen, jota k‰ytet‰‰n tallennukseen 
	  * @return tallennustiedoston nimi 
	  */ 
	    public static String getTiedostonNimi() { 
	        return tiedostonPerusNimi + ".dat"; 
	    } 

	    
	    public static String getBakNimi(){
	    	return tiedostonPerusNimi + ".bak";
	    }
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    public class ArtistitIterator implements Iterator<Artisti>{
	    	private int kohdalla = 0;
	    
	    	@Override
	    	public boolean hasNext(){
	    		return kohdalla < getLkm();
	    	}
	    
	    	@Override
	    	public Artisti next() throws NoSuchElementException{
	    		if ( !hasNext() ) throw new NoSuchElementException("Ei oo");
	    		return annaArtisti(kohdalla++);
    			}
	    	@Override
	    	public void remove() throws UnsupportedOperationException { 
	    		throw new UnsupportedOperationException("Me ei poisteta");
	    	}
	    	
	   	
	   	}
	    
	    /**
	     * Tehd‰‰n uusi iteraattori
	     * return ArtistitIterator
	     */
		@Override
		public Iterator<Artisti> iterator() {
			// TODO Auto-generated method stub
			return new ArtistitIterator();
		} 
	    
	    /**
	     * @return edit jossa hakuehto
	     */
	    public JTextField getEditHaku() {
	        return textHaku;
	    }
	    
	    /**
	     * @param editHaku edit johon saa kirjoittaa hakuehdon
	     */
	    public static void setEditHaku(JTextField editHaku) {
	        textHaku = editHaku;
	    }
	    
	    
		public void displayAll(){
	      
	        Iterator<Artisti> iterator = alkiotTesti.iterator();
	       
	      
	        while(iterator.hasNext()){
	        	Artisti artisti = iterator.next();
	            paaikku3.listArtistit.add(artisti.getArtistiNimi(),artisti);
	        }
		}
	    
		
	    /**
	     * Suorittaa niiden artistien hakemisen, joiden valittu kentt‰ t‰ytt‰‰ hakuehdon
	     * @param jnro artistin numero, joka aktivoidaan haun j‰lkeen, NYKY = aktivoidaan nykyinen artisti
	     */
	    public void hae(String ehto) {
	        
	        ArrayList<Artisti> haunTulos = etsi(ehto);
	        paaikku3.listArtistit.clear();
	       
	       
	        int i = 0;
	        for (Artisti artisti : haunTulos ) {
	            paaikku3.listArtistit.add(artisti.getArtistiNimi(),artisti);
	            i++;
	        }
	    }
	    
	    public static ArrayList<Artisti> etsi(String ehto){
	    	ArrayList<Artisti> tulos = new ArrayList<Artisti>();
	    	for(int i = 0; i < alkiot.length; i++ ){
	    		 Artisti muuttuja = alkiot[i];
	    		 String nimi = muuttuja.getArtistiNimi();
	    		 int pituus = ehto.length();
	    		 String vertaa = nimi.substring(0, pituus);
	    		 if(vertaa.equals(ehto)){
	    			 tulos.add(muuttuja);
	    			
	    		 }
	    		
	    	}
	    	return tulos;
	    }
	    
	    
	    /**
	     * Asetetaan editoitava artisti
	     * @param j uusi viite editArtistille
	     */
	    private void setJasenKohdalla(Artisti j) {
	        int jnro = paaikku3.NYKY;
	        if ( j != null ) jnro = j.getTunnusNro();
	        tarkistaMuutos(jnro);
	        jasenKohdalla = j;
	        setEditJasen(null);
	    }
	    
	    /**
	     * Tarkitetaan onko j‰senen tiedot muuttuneet ja jos on, kysyt‰‰n halutaanko tallentaa
	     * @param jnro mik‰ j‰sen aktiiviseksi muutoksne j‰lkeen
	     */
	    public void tarkistaMuutos(int jnro) {
	        if (muuttunut()) {
	            int vastaus = JOptionPane.showConfirmDialog(null, "Talletetaanko?", "Artisti muuttunut!", JOptionPane.YES_NO_OPTION);
	            if (vastaus == JOptionPane.YES_OPTION); //talleta(jnro);
	        }
	    }
	    
	    /**
	     * Tutkii onko editoitavaan j‰seneen tehty muutoksia, jotka kannattaa
	     * tallettaa.
	     * @return false jos ei muutoksia
	     */
	    private boolean muuttunut() {
	        if (jasenKohdalla == null) return false;
	        if (editArtisti == null) return false;
	        return !jasenKohdalla.equals(editArtisti);
	    }
	    
	    /**
	     * Asetetaan editoitava j‰sen
	     * @param j uusi viite editJasenelle
	     */
	    private void setEditJasen(Artisti j) {
	        editArtisti = j;
	    }

	/**
	 * lis‰t‰‰n alkiot mittaa, jotta voidaan tallentaa lis‰‰ astisteja
	 */
	public static void lisaaAlkioita(){
		Artisti [] uusiAlkiot = new Artisti[alkiot.length + MAX_ARTISTEJA];
		for(int i = 0; i < alkiot.length; i++){
			uusiAlkiot[i] = alkiot[i];
		}
		alkiot = uusiAlkiot;
	}


	/**
	 * palauttaa viitteen i:n artistiin
	 * @param i haluttu artisti
	 * @return alkiot[i] viite i:n artistiin
	 * @throws IndexOutOfBoundsException 
	 */
	public Artisti annaArtisti(int i){ //throws IndexOutOfBoundsException{
		if (i >= lkm || i < 0){
			return null;
			//throw  new IndexOutOfBoundsException("Ei ole artistia");
		}
		return alkiot[i];
	}

	
	/**
	 * etsit‰‰n Artistit luokan alkiosta haluttu artisti
	 * @param artistiNro etsitt‰v‰ artisti
	 * @return viite haluttuun artistiin
	 */
	public static Artisti etsiArtistiListasta(int artistiNro) {
		for (int i = 0; i < lkm; i++){
			Artisti apuri = alkiot[i];
			int apu = apuri.getArtistiNro();
			if (apu ==artistiNro) return apuri; //alkiot[i];
		}
		return null;
	}



	
	
	
	
	
	
	
	
	
	
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
				
        Musiikkiarkisto arkisto = new Musiikkiarkisto();    
		Artistit artistit = new Artistit();
		
		Artisti Miles = new Artisti();
		Miles.setNimi("Erkki");
		Miles.setArtistiNro(1);
		Miles.rekisteroi();
		arkisto.lisaa(Miles);
		
		Artisti Deniz = new Artisti();
		Deniz.setNimi("Deniz");
		Deniz.setArtistiNro(3);
		Deniz.rekisteroi();
		arkisto.lisaa(Deniz);
		
		Artisti jarkko = new Artisti();
		jarkko.setNimi("Jarkko");
		jarkko.setArtistiNro(2);
		jarkko.rekisteroi();
		arkisto.lisaa(jarkko);
		
		
	
		 String nimi = "Testi artisti";
		 Artisti uusi = new Artisti();
		 uusi.setNimi(nimi);
		 uusi.setArtistiNro(4);
		 uusi.rekisteroi();
		 arkisto.lisaa(uusi);
		 //Artistit.lisaaArtisti(uusi);
		
		System.out.println("Testataan jos saadaan haettua tunnuksen kanssa: artisti 3 ");
		//etsiArtistiListasta(1).tulosta(System.out);
		System.out.println("-----------------------------------------");
		
		int index = 0;
        for (int i = 0; i < artistit.getLkm(); i++) {
            //Artisti artisti = artistit.annaArtisti(i);
            //artisti.tulosta(System.out);
            artistit.annaArtisti(i).tulosta(System.out);
        }

      
        for (int i = 0; i < lkm; i++){
			//Artisti apu = alkiot[i];
			//return apu;
			if (alkiot[i].getArtistiNro() == jarkko.getArtistiNro()) System.out.println("Pit‰is tulostua vain kerran Jarkon numero " + alkiot[i].getArtistiNro());
  
        }
        
        try {
			talleta();
		} catch (SailoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
	}



}

