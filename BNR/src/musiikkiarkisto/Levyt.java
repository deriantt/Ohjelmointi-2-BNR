package musiikkiarkisto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import BNR.paaikku3;

/**
 * @author Deniz Anttila
 * @version 6.5.2013
 *
 */
public class Levyt implements Iterable<Levy>{

		private final static int MAX_LEVYJA = 40;
		private static Levy [] alkiot = new Levy[MAX_LEVYJA];
		private static int lkm = 0;
		private String tiedostonNimi = "";
		private static String tiedostonPerusNimi = "";
		private static boolean muutettu = false;
		private static String kokoNimi 				= "";
		public static final Collection<Levy> alkiotLevyt = new ArrayList<Levy>();
		
		//private Levy apu = new Levy();
		
		
		/**
		 * 
		 */
		public Levyt(){
			//oletus muodostaja
		}

		/**
		 * palauttaa levyjen m‰‰r‰n
		 * @return lkm
		 */
		public int getLkm(){
			return lkm;
		}


		/**
		 * lis‰t‰‰n levy arkistoon
		 * @param Levy
		 */
		public void lisaaLevy(Levy levy){
			if (lkm >= MAX_LEVYJA) lisaaAlkioita();
			alkiot[lkm] = levy;
			lkm++;
			muutettu = true;
		}


		/**
		 * list‰‰n alkioihin lis‰‰ alkioita
		 */
		public void lisaaAlkioita(){
			Levy [] uusiAlkio = new Levy[MAX_LEVYJA * 2];
			for(int i = 0; i < alkiot.length; i++){
				uusiAlkio[i] = alkiot[i];
			}
			this.alkiot = uusiAlkio;
		}

		/**
		 * palauttaa halutun levyn
		 * @param levyNro
		 * @return  haluttu levy
		 */
		public static Levy annaLevy(int levyNro){
			if (levyNro >= lkm || levyNro < 0) return null;
			return alkiot[levyNro];
			
		}
		
		/**
		 * Varmistaa ett‰ levy numeroa ei ole k‰ytetty
		 * @param levyNro
		 * @return true jos varattu: false jos vapaa
		 */
		public static boolean varattuLevyNro(int levyNro){
			List<Levy> levyt = new ArrayList<Levy>();
			
			for(int i = 0; i < levyt.size(); i++){
				Levy koe = levyt.get(i);
				if (koe.getLevyNro() == levyNro){
					return true;
				}
			}
			
			return false;
			
		}


		/**
		 * palautetaan lista halutun artistin levyist‰
		 * @param artistiNro
		 * @return artistinLevyt palautettava lista
		 */
		public static List <Levy> getlistaArtistinLevyista(int artistiNro){
			List<Levy> artistinLevyt = new ArrayList<Levy>();
			
			for(int i = 0; i < lkm; i++){
				Levy koe = annaLevy(i);
				if (koe.getLevynArtisti() == artistiNro){
					artistinLevyt.add(koe);
					
				}
			}
			return artistinLevyt;
			
		}
		
		
		
		
		public static void getlistaArtistinLevyt(int artistiNro){
						
			 //List<Levy> levyList = new ArrayList<Levy>(alkiotLevyt);
			 while(alkiotLevyt.iterator().hasNext()){
				 Levy apuri = alkiotLevyt.iterator().next();
					if (apuri.getLevynArtisti() == artistiNro){
					paaikku3.listLevy.add(apuri.getLevyNimi(),apuri);
				}
			}
		}
		
		
		
		
		
		
		
		
		



		/**
		 * lis‰t‰‰n listaan yksi alkio
		 * @param artistinLevyt
		 * @return uusi lista yhden alkion pidemp‰n‰
		 */
		public static Levy [] lisaaListaanAlkio(Levy[] artistinLevyt){
			if (artistinLevyt.length == 0) return new Levy[1];
			Levy [] uusi = new Levy[artistinLevyt.length+1];
			for(int i = 0; i < artistinLevyt.length; i++){
				uusi[i] = artistinLevyt[i];
			}
			return uusi;
		}

		
		
		
		
		
		
		
		
		
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
			 	    
			 	   	if(kentat.length >= 5){
			 	   		int tunnusNro = Integer.parseInt(kentat[0]);
			 	   		Levy lev = new Levy(tunnusNro);
			 	   		lev.setTunnusNro(tunnusNro);
			 	   		
			 	   		Levyt hylly = new Levyt();
			 	   	
			 	   		lev.parse(rivi);
			 	   		lev.rekisteroi();
			 	   		hylly.lisaaLevy(lev);
			 	   	}
				
				}
	        
				muutettu = false;
					
	        } catch ( FileNotFoundException e ) { 
	        	throw new SailoException("Tiedosto " + getTiedostonNimi() + " ei aukea"); 
	        } catch ( IOException e ) { 
	        	throw new SailoException("Ongelmia tiedoston kanssa: " + e.getMessage()); 
	        } 
		
}
		




		
		public void talleta() throws SailoException{
			/**
			if ( !muutettu ) return;
			
			//FileOutputStream erasor = new FileOutputStream(getTiedostonNimi());
			//erasor.write((new String()).getBytes());
			//erasor.close();
		
			
			String tiedNimi = getTiedostonNimi();
	        
			
			try {PrintStream fo = new PrintStream(new FileOutputStream(tiedNimi, true)); {
				fo.println(getTiedostonNimi());
				fo.println(Artistit.alkiot.length);
				for (int i=0; i < alkiot.length; i++ ){//Artisti artisti : this){
					Artisti apu = alkiot[i].getlevynArtisti();
					fo.println(apu);
				}
			}} catch (FileNotFoundException ex) {
	            System.err.println("Tiedosto ei aukea: " + ex.getMessage());
	        }
	    
				muutettu = false;
		
		*/
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

		
	    public Iterator<Levy> iterator(){
	    	return alkiotLevyt.iterator();
	    }
	    
		
		
		
		
		
		
		
		
		
		
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Levyt hylly = new Levyt();
		
		Levy lp1 = new Levy(3);
		lp1.rekisteroi();
		lp1.vastaaLevy();
		
		hylly.lisaaLevy(lp1);
		
		
		Levy lp2 = new Levy(1);
		lp2.rekisteroi();
		lp2.vastaaLevy();
		
		hylly.lisaaLevy(lp2);
		
		
		Levy lp3 = new Levy(2);
		lp3.rekisteroi();
		lp3.vastaaLevy();
		
		hylly.lisaaLevy(lp3);
		
		
		for (int i = 0; i < hylly.getLkm(); i++){
			Levy apu = hylly.annaLevy(i);
			apu.tulosta(System.out);
		}
		
		
		
		
		
		
		
		
	
		
		
		
		
		
		
		
		
		
		
		

	}

}
