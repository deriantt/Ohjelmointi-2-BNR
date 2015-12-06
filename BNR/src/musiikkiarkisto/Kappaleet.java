package musiikkiarkisto;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


/**
 * 
 *
 * @author Deniz Anttila
 * @version 1.0, 18.04.2013
 */
public class Kappaleet {//implements Iterator<Kappale> { //extends Tietue<Kappale> {

    private String	tiedostonNimi 					= "";						
	private static String tiedostonPerusNimi 		= "";
	private static boolean muutettu 				= false;
	private static String kokoNimi;
    /** Taulukko kappaleista */
    private static final Collection<Kappale> alkiot = new ArrayList<Kappale>();



    /**
     * Kappaleten alustaminen
     */
    public Kappaleet() {
    	//super(new Kappale(),".har",".hbak");
    }


    /**
     * Lis‰‰ uuden harrastuksen tietorakenteeseen.  Ottaa harrastuksen omistukseensa.
     * @param kappale lis‰tt‰v‰ Kappale.  Huom tietorakenne muuttuu omistajaksi 
     */
    public void lisaa(Kappale kappale) {
        alkiot.add(kappale);
    }





    /**
     * Tallentaa j‰senist‰n tiedostoon.  
     * TODO Kesken.
     * @throws SailoException jos talletus ep‰onnistuu
     */
    public void talleta() throws SailoException {
        throw new SailoException("Ei osata viel‰ tallettaa tiedostoa " + tiedostonNimi);
    }


    /**
     * Palauttaa kerhon Kappaleten lukum‰‰r‰n
     * @return Kappaleten lukum‰‰r‰
     */
    public static int getLkm() {
        return alkiot.size();
    }


    /**
     * Iteraattori kaikkien Kappaleten l‰pik‰ymiseen
     * @return Kappaleiteraattori
     * 
     * @example
     * <pre name="test">
     * #PACKAGEIMPORT
     * #import java.util.*;
     * 
     *  Kappaleet harrasteet = new Kappaleet();
     *  Kappale pitsi21 = new Kappale(2); harrasteet.lisaa(pitsi21);
     *  Kappale pitsi11 = new Kappale(1); harrasteet.lisaa(pitsi11);
     *  Kappale pitsi22 = new Kappale(2); harrasteet.lisaa(pitsi22);
     *  Kappale pitsi12 = new Kappale(1); harrasteet.lisaa(pitsi12);
     *  Kappale pitsi23 = new Kappale(2); harrasteet.lisaa(pitsi23);
     * 
     *  Iterator<Kappale> i2=harrasteet.iterator();
     *  i2.next() === pitsi21;
     *  i2.next() === pitsi11;
     *  i2.next() === pitsi22;
     *  i2.next() === pitsi12;
     *  i2.next() === pitsi23;
     *  i2.next() === pitsi12;  #THROWS NoSuchElementException  
     *  
     *  int n = 0;
     *  int jnrot[] = {2,1,2,1,2};
     *  
     *  for ( Kappale har:harrasteet ) { 
     *    har.getLevyNro() === jnrot[n]; n++;  
     *  }
     *  
     *  n === 5;
     *  
     * </pre>
     */
    //@Override
    public Iterator<Kappale> iterator() {
        return alkiot.iterator();
    }

    
    
    /**
     * @param tunnusNro
     * @return kappale viite haluttuun kappaleeseen
     */
    @SuppressWarnings("unchecked")
	public Iterator<Kappale> annaKappale(int tunnusNro){
        for (Kappale kappale : alkiot)
            if (kappale.getKappaleNro() == tunnusNro) return (Iterator<Kappale>) kappale;
		return null;
    }

    /**
     * Haetaan kaikki levyn Kappaleet
     * @param tunnusnro levyn tunnusnumero jolta kappaleita haetaan
     * @return tietorakenne jossa viiteet l‰ydetteyihin harrastuksiin
     * @example
     * <pre name="test">
     * #import java.util.*;
     * 
     *  Kappaleet harrasteet = new Kappaleet();
     *  Kappale pitsi21 = new Kappale(2); harrasteet.lisaa(pitsi21);
     *  Kappale pitsi11 = new Kappale(1); harrasteet.lisaa(pitsi11);
     *  Kappale pitsi22 = new Kappale(2); harrasteet.lisaa(pitsi22);
     *  Kappale pitsi12 = new Kappale(1); harrasteet.lisaa(pitsi12);
     *  Kappale pitsi23 = new Kappale(2); harrasteet.lisaa(pitsi23);
     *  Kappale pitsi51 = new Kappale(5); harrasteet.lisaa(pitsi51);
     *  
     *  List<Kappale> loytyneet;
     *  loytyneet = harrasteet.annaKappaleet(3);
     *  loytyneet.size() === 0; 
     *  loytyneet = harrasteet.annaKappaleet(1);
     *  loytyneet.size() === 2; 
     *  loytyneet.get(0) == pitsi11 === true;
     *  loytyneet.get(1) == pitsi12 === true;
     *  loytyneet = harrasteet.annaKappaleet(5);
     *  loytyneet.size() === 1; 
     *  loytyneet.get(0) == pitsi51 === true;
     * </pre> 
     */
    public static List<Kappale> getListaKappaleista(int tunnusnro) {
        List<Kappale> levynKappaleet = new ArrayList<Kappale>();
        for (Kappale har : alkiot){
            if (har.getLevyNro() == tunnusnro) levynKappaleet.add(har);
            }
        return levynKappaleet;
    }

    
    
    
    /**
     * Lukee j‰senist‰n tiedostosta.  
     * TODO Kesken.
     * @param tied tiedoston nimen alkuosa
     * @throws SailoException jos lukeminen ep‰onnistuu
     */

	public static void lueTiedostosta(String tied) throws SailoException {
		setTiedostonPerusNimi(tied); 
        try { BufferedReader fi = new BufferedReader(new FileReader(getTiedostonNimi())) ;  
            kokoNimi = fi.readLine(); 
            if ( kokoNimi == null ) throw new SailoException("Kerhon nimi puuttuu"); 
		 	String rivi = fi.readLine(); 
		 	if ( rivi == null ) throw new SailoException("Maksimikoko puuttuu"); 
			while ( (rivi = fi.readLine()) != null ) { 
		 		rivi = rivi.trim(); 
		 	    if ( "".equals(rivi) || rivi.charAt(0) == ';' ) continue;
		 	    
		 	    String testiRivi = rivi; 
		 	   	String prefix = "";
		 	   	String[] kentat = testiRivi.substring(prefix.length()).split("/");
		 	   	
		 	   	
		 	   	if(5 < kentat.length){
		 	   		
		 	   		int levyNro = Integer.parseInt(kentat[2]);
		 	   		Kappaleet levy = new Kappaleet();
		    	
		 	   		for(int i = 6; i < kentat.length; i++){
		 	    		
		 	   			Kappale kpl = new Kappale(levyNro);
		 	    		
		 	   			kpl.parse(rivi, i);
		 	   			kpl.rekisteroi();
		 	   			levy.lisaa(kpl);
		 	   		}
				}
		 	   	
			}
        
			muutettu = false;
				
        } catch ( FileNotFoundException e ) { 
        	throw new SailoException("Tiedosto " + getTiedostonNimi() + " ei aukea"); 
        } catch ( IOException e ) { 
        	throw new SailoException("Ongelmia tiedoston kanssa: " + e.getMessage()); 
        } 
	
}


	public static void setTiedostonPerusNimi(String tied){
		tiedostonPerusNimi = tied;
	}
	
	
	public String getTiedostonPerusNimi(){
		return tiedostonPerusNimi;
	}
	
	
	public static String getTiedostonNimi(){
		return tiedostonPerusNimi + ".dat";
	}
	
	
	public String getBakNimi(){
		return tiedostonNimi + ".lbak";
	}

	
    
    
    
    
    
    
    
    
    

    /**
     * Testiohjelma harrastuksille
     * @param args ei k‰yt‰ss‰
     */
    public static void main(String[] args) {
        /**
    	Kappaleet kappaleet = new Kappaleet();
        
        Kappale kp = new Kappale(3); 
        Kappale kp2 = new Kappale(1), kp3 = new Kappale(3),
        		kp4 = new Kappale(3);

        
        
        kp.rekisteroi();
        kp.vastaaKappale();
        kp.tulosta(System.out);
        
        kp2.rekisteroi();
        kp2.vastaaKappale();
        kp2.tulosta(System.out);
        
        kp3.rekisteroi();
        kp3.vastaaKappale();
        kp3.tulosta(System.out);
        
        kp4.rekisteroi();
        kp4.vastaaKappale();
        kp4.tulosta(System.out);
        
        
        
        


        kappaleet.lisaa(kp);
        kappaleet.lisaa(kp2);
        kappaleet.lisaa(kp3);
        kappaleet.lisaa(kp4);


        System.out.println("============= Kappaleet testi =================");

        List<Kappale> Kappaleet2 = kappaleet.getListaKappaleista(3);

        
        for (Kappale kap : Kappaleet2) {
            System.out.println( + kap.getLevyNro() + " ");
            kap.tulosta(System.out);
        }
        */
        System.out.println("============= Tiedoston luku testi =================");
        
        try {
			lueTiedostosta("BNR");
		} catch (SailoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ArrayList<Kappale> biisiLista = (ArrayList<Kappale>) Kappaleet.getListaKappaleista(3);
		
		for (int i = 0; i < biisiLista.size() ; i++) {
            Kappale apuKpl = biisiLista.get(i);
            String teksti = apuKpl.getKappaleNimi();
            System.out.println(teksti);
		}
    }


}
