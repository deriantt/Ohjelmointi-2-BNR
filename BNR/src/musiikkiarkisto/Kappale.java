package musiikkiarkisto;


import java.io.PrintStream;
import java.util.List;

/**
 * @author Deniz Anttila
 * @version 18.3.2012
 *
 */
public class Kappale {
	
	private String nimi = "";
	private int kappaleNro;
	private int levyNro;
	
	
	
	private static int  KappaleSeuraavaNro = 1;
	
	
	/**
	 * muodostoja kappale oliolle
	 * @param levyNumero levy johon kappale kuuluu
	 */
	public Kappale(int levyNumero){
		levyNro = levyNumero;
		kappaleNro = KappaleSeuraavaNro;
		KappaleSeuraavaNro++;
		
	}
	
	/**
	 * palauttaa kappaleen nron
	 * @return kappaleNro
	 */
	public int getKappaleNro(){
		return kappaleNro;
	}
	
	/**
	 * palauttaa levyn numeron johon kappale kuuluu
	 * @return levyNro
	 */
	public int getLevyNro(){
		return levyNro;
	}
	
	/**
	 * palauttaa kappaleen nimen
	 * @return nimi
	 */
	public String getKappaleNimi(){
		return nimi;
	}
	
	public void setKappaleNimi(String kplNimi){
		this.nimi = kplNimi;
	}
	
    /**
     * antaa kappaleelle seuraavan kappalenumeron
     * @return kappaleNro
     */
    public int rekisteroi() {
        if ( kappaleNro != 0 ) return kappaleNro; // ???
        kappaleNro = KappaleSeuraavaNro;
        KappaleSeuraavaNro++;
        return kappaleNro;
    }
    
    /**
     * Apumetodi, jolla saadaan täytettyä testiarvot jäsenelle.
     */
    public void vastaaKappale() {
        nimi = "AamuYo" + rand(20000, 30000);
    }
    
    
    /**
     * Tulostetaan Levyn tiedot
     * @param out tietovirta johon tulostetaan
     */
    public void tulosta(PrintStream out) {
        out.println(String.format("%03d", kappaleNro) + "  " + nimi + "\nLˆytyy levylt‰ : " + levyNro);
        out.println();
    }
    
    public void parse (String rivi, int i){
    	
    	String prefix = "";
    	String[] kentat = rivi.substring(prefix.length()).split("/");
    	
    	setKappaleNimi(kentat[i]);
    	
    }
    
    
    /**
     * Arvotaan satunnainen kokonaisluku välille [ala,yla[
     * @param ala arvonnan alaraja
     * @param yla arvonnan yläraja
     * @return satunnainen luku väliltä [ala,yla[
     */
    public static int rand(int ala, int yla) {
        double n = (yla - ala) * Math.random() + ala;
        return (int) Math.round(n);
    }
    
	/**
	 * @param args
	 */
	public static void main(String[] args) {
        
		Kappale kp = new Kappale(3); Kappale kp2 = new Kappale(1);

        
        
        kp.rekisteroi();
        kp.vastaaKappale();
        kp.tulosta(System.out);
        kp2.rekisteroi();
        kp2.vastaaKappale();
        kp2.tulosta(System.out);
        

	}

	public static String toString(int levyNro) {
		List <Kappale> levynKappaleet = Kappaleet.getListaKappaleista(levyNro);
		
		String listString = "";

		for (Kappale s : levynKappaleet)
		{
		    listString += s.getKappaleNimi() + "/";
		}
		
		return listString;
	}
    

}
