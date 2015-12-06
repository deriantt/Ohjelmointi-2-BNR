package musiikkiarkisto;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;





public class Tietue<TYPE> {

	private TYPE malli;
	private final List<TYPE> alkiot = new ArrayList<TYPE>();
    private String tark = ".dat";
    private String baktark = ".bak";
    private String                      tiedostonPerusNimi = "";
    
    /**
     * Rakenteen alustaminen.
     * @param malli malliolio, jonka kloonilla voi luoda uusia
     */
    public Tietue(TYPE malli) {
        this.malli = malli;
    }

    /**
     * Rakenteen alustaminen.
     * @param malli malliolio, jonka kloonilla voi luoda uusia
     * @param tark tiedoston tarkennin
     * @param baktark backuptiedoston tarkennin, jos null, ei tehd‰
     */
    public Tietue(TYPE malli,String tark, String baktark) {
        this(malli);
        this.tark = tark;
        this.baktark = baktark;
    }
    
		public void lueTiedostosta(String tied) throws SailoException {
	        //muutettu = true;
	        //setTiedostonPerusNimi(tied);
	        //try { BufferedReader fi = new BufferedReader(new FileReader(getTiedostonNimi())) ; {
		 try { BufferedReader fi = new BufferedReader(new FileReader("UusiArkisto")) ; {
	            String rivi;
	            lueAlkurivit(fi);
	            while ((rivi = fi.readLine()) != null) { 
	                rivi = rivi.trim();
	                if ("".equals(rivi) || rivi.charAt(0) == ';') continue;
	                TYPE tietue = luoUusi();
	                /// TYPE tietue = new TYPE(); /// nyyh
	                //tietue.parse(rivi); // voisi olla virhek‰sittely
	                lisaa(tietue);
	            }
	            //muutettu = false;
	        }} catch ( FileNotFoundException e ) {
	            throw new SailoException("Tiedosto " + "getTiedostonNimi()" + " ei aukea");
	        } catch (IOException e) {
	            throw new SailoException("Ongelmia tiedoston kanssa: " + e.getMessage());
	        }
	    }
		
	    /**
	     * Asettaa tiedoston perusnimen ilman tarkenninta
	     * @param tied tallennustiedoston perusnimi
	     */
	    //@Override
	    public void setTiedostonPerusNimi(String tied) {
	        tiedostonPerusNimi = tied;
	    }

	    public TYPE luoUusi() {
	   //     try {
	   //         Tietue uusi;// = malli.clone();
	   //         return (TYPE)uusi;
	   //     } catch (CloneNotSupportedException e) {
	           return null;
	   //     }
	    }
	    
	    /**
	     * Lis‰‰ uuden tietueen tietorakenteeseen.  Ottaa tietueen omistukseensa.
	     * @param har lis‰tt‰v‰ harrastus.  Huom tietorakenne muuttuu omistajaksi
	     */
	    //@Override
	    public void lisaa(TYPE har) {
	        alkiot.add(har);
	        //muutettu = true;
	    }
	    
	    /**
	     * Lukee rivit tiedoston alusta
	     * @param fi virta josta luetaan
	     * @throws IOException jos jokin menee vikaan
	     * @throws SailoException jos tieto virheellist‰
	     */
	    protected void lueAlkurivit(@SuppressWarnings("unused") BufferedReader fi) throws IOException, SailoException {
	       //       
	    }
	 
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
