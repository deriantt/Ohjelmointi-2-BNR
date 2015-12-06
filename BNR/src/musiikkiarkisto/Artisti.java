package 	musiikkiarkisto;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;


/**
 * @author Deniz Anttila
 * @version 15.4.2013
 *
 */
public class Artisti{ //extends Tietue{
	private String nimi 				= "";
	private int artistiNro		 		= 0;
	private static int seuraavaNro 		= 1;
	private int tunnusNro;
	
	/**
	 * Oletus muodostaja artistille
	 */
	public Artisti(){
		//this.nimi = getArtistiNimi();
		//this.artistiNro = getArtistiNro();
		//this.rekisteroi();
	}
	
	/**
	 * Asettaa artistille annettu nimi
	 * param String nimi
	 */
	public void setNimi(String uusinimi){
		this.nimi = uusinimi;
	}
	
	public void setArtistiNro(int nro){
		this.artistiNro = nro; 
        if (artistiNro >= seuraavaNro) seuraavaNro = artistiNro + 1; 
	}
	
	/**
	 * palauttaa artistin numeron
	 * @return artistiNro
	 */
	public int getArtistiNro(){
		return artistiNro;
	}

	
	public int getTunnusNro(){
		return tunnusNro;
	}
	
	
     /** 
     * Asettaa tunnusnumeron ja samalla varmistaa ett‰ 
     * seuraava numero on aina suurempi kuin t‰h‰n menness‰ suurin. 
     * @param nr asetettava tunnusnumero 
     */ 
     private static void setTunnusNro(int nr) { 
        //artistiNro = nr; 
        //if (artistiNro >= seuraavaNro) seuraavaNro = artistiNro + 1; 
     }
	

	/**
	 * palauttaa artistin nimen
	 * @return artistin nimi
	 */
	public String getArtistiNimi(){
		return nimi;
	}

	
	/**
	 * 
	 * @return artistiNro
	 */
	public int rekisteroi(){
		if(artistiNro != 0)return artistiNro; //??
		artistiNro = seuraavaNro;
		seuraavaNro++;
		return artistiNro;
	}
	
	
    /** 
     * Palauttaa j‰senen tiedot merkkijonona jonka voi tallentaa tiedostoon. 
     * @return j‰sen tolppaeroteltuna merkkijonona  
     * @example 
     * <pre name="test"> 
     *   Jasen jasen = new Jasen(); 
     *   jasen.parse("   3  |  Ankka Aku   | 030201-111C"); 
     *   jasen.toString().startsWith("3|Ankka Aku|030201-111C|") === true; // on enemm‰kin kuin 3 kentt‰‰, siksi loppu | 
     * </pre>   
     */ 
    @Override 
    public String toString() { 
        return  (getArtistiNro() + "/" + nimi + "/"); 
    } 
 	 
 	 
    /** 
     * Selvit‰‰ j‰senen tiedot | erotellusta merkkijonosta 
     * Pit‰‰ huolen ett‰ seuraavaNro on suurempi kuin tuleva tunnusNro. 
     * @param rivi josta j‰senen tiedot otetaan 
     *  
     * @example 
     * <pre name="test"> 
     *   Jasen jasen = new Jasen(); 
     *   jasen.parse("   3  |  Ankka Aku   | 030201-111C"); 
     *   jasen.getTunnusNro() === 3; 
     *   jasen.toString().startsWith("3|Ankka Aku|030201-111C|") === true; // on enemm‰kin kuin 3 kentt‰‰, siksi loppu | 
     * 
     *   jasen.rekisteroi(); 
     *   int n = jasen.getTunnusNro(); 
     *   jasen.parse(""+(n+20));       // Otetaan merkkijonosta vain tunnusnumero 
     *   jasen.rekisteroi();           // ja tarkistetaan ett‰ seuraavalla kertaa tulee yht‰ isompi 
     *   jasen.getTunnusNro() === n+20+1; 
     *      
     * </pre> 
     */ 
     public void parse(String rivi) { 
       StringBuffer sb = new StringBuffer(rivi);
       String prefix = "";
	   
	   String[] kentat = rivi.substring(prefix.length()).split("/");
       int foo = Integer.parseInt(kentat[0]);
       setArtistiNro(foo);
       setNimi(kentat[1]);
    } 
     
	
	/**
	 * tulostetaan artistin tiedot
	 * @param out
	 */
	public void tulosta(PrintStream out){
		out.println(String.format("%02d", artistiNro) + " " + nimi);
	}
	
	
	/**
	 * Apumetodi, jolla saadaan arvoja artistille.
	 */
		public void vastaa() {
		nimi = "Mike Davis"; //+ " " + rand(1000, 9999);	
	}
	
	/**
	 * Arvotaan satunnainen kokonaisluku v‰lille [ala, yla]
	 * @param ala arvonnan alaraja
	 * @param yla yl‰raja
	 * @return satunnainen luku v‰lilt‰ [ala, yla]
	 */
	public static int rand (int ala, int yla){
		double n = (yla-ala)*Math.random() + ala;
		return (int)Math.round(n);
	}
	//----------------------------------------------Testej‰ t‰st‰ eteenp‰in-----------------------
	 

	 
	/**
     * @param s Tulostettava merkkijono
     */
    public static void tulosta(String s) {
	        System.out.println(s);
    }
    
	
	/**
	 * @param args
	 *  
	 */
	public static void main(String[] args) {
		
		
		String Def = ("1/Deniz/parhaat/rock/1998");

		String sick = "pre/fix/dir1/dir2/dir3/dir4/..";
	    String prefix = "";
	    //String[] tokens = Def.substring(prefix.length()).split("|");
	    String[] tokens = Def.substring(prefix.length()).split("/");
	    for (int i=0; i<tokens.length; i++) {
	    	System.out.println(tokens[i]);
	    }
	    
		
	
		
		
		BufferedReader lukija;
		
		try {
			 lukija = new BufferedReader( new FileReader("BNR.dat"));
		} catch (FileNotFoundException ex) {
			System.err.println("Tiedosto ei aukea: " + ex.getMessage());
			return;
		}

		String s;
		try {
			while((s = lukija.readLine()) != null){
				String[] kentat = s.substring(prefix.length()).split("/");
				
			try{
				StringBuilder rivi = new StringBuilder(s);

				 if ("".equals(rivi) || rivi.charAt(0) == ';') continue;
				
				 
				 
				 
				 //s = rivi.toString();
				//String test = s.substring(0,2);
				
				//for(int i = -1; (i = s.indexOf("/", i + 1)) != -1;){
				//System.out.println(i); 
				//kentat[a] = i;
				//a++;
				//}
				//String nimi = s.substring((kentat[0]+1), kentat[1]);
				String nimi = kentat[1];
				int nro = Integer.parseInt(kentat[0]);
				Artisti artisti = new Artisti();
				artisti.nimi = nimi;
				artisti.setArtistiNro(nro);
				//System.out.println("T‰s on kent‰n rajat: " + kentat[0] + " ja " + kentat[1]);
				//String nimi = s.substring((kentat[0]+1), kentat[1]);
				System.out.println("Artistin nimi on: " + nimi);
				artisti.tulosta(System.out);
				System.out.println();
			
			  
			} catch (NumberFormatException ex){
				// ei noteerata
			}
			}
		} catch (IOException ex) {
			System.err.println("Virhe luettaessa: " + ex.getMessage());
		
		}finally{
			try {
				lukija.close();
			} catch (IOException ex) {
			// Jos ei sulkeudu, t‰m‰ teksti tulostuu
			System.err.println("Ongelmia suljettaessa: " + ex.getMessage());
		}
	}
		

	

		
		//Artisti Miles = new Artisti(), 
		//Davis = new Artisti(),
		//bob = new Artisti();
		//Miles.rekisteroi();
		//Miles.vastaa();
		//Miles.tulosta(System.out);
		//Davis.rekisteroi();
		//Davis.vastaa();
		//Davis.tulosta(System.out);
		//bob.rekisteroi();
		//bob.vastaa();
		//bob.tulosta(System.out);

	



	}
}




