package musiikkiarkisto;

import java.io.PrintStream;
import java.util.Iterator;

import fi.jyu.mit.ohj2.Mjonot;





/**
 * @author Deniz Anttila
 * @version 6.5.2013
 *
 */
public class Levy {
	
	private int tunnusNro;
	private String levyNimi 	= "";
	private int levyNro 		= 0;
	private String genre 		= "";
	private int julkaisuVuosi 	= 0;

	private int artistiNro;
	private static int seuraavaLevyNro = 1;
	


	public Levy(){
		
	}
	
	/**
	 * @param artistiNro
	 */
	public Levy(int kenenArtistin){
		this.artistiNro = kenenArtistin;
		
		//vapaaLevyNro(seuraavaLevyNro);
		this.levyNro = seuraavaLevyNro;
		seuraavaLevyNro++;
	
	}
	
	public void vapaaLevyNro(int levyNro){
		if(Levyt.varattuLevyNro(seuraavaLevyNro)){
			seuraavaLevyNro++;
			vapaaLevyNro(seuraavaLevyNro);
		}
	}

	public void setLevyNimi(String nimi){
		this.levyNimi = nimi;
	}
	
	public void setGenre(String tyyppi){
		this.genre = tyyppi;
	}
	
	public void setJulkaisuVuosi(int vuosi){
		this.julkaisuVuosi = vuosi;
	}

	/**
	 * palauttaa levyn numeron
	 * @return levyNro
	 */
	public int getLevyNro(){
		return levyNro;
	}

	/**
	 * Asetetaan levyn tunnus numero tiedoston mukaan
	 * @param nro
	 */
	public void setLevyNro(int nro){
		this.levyNro = nro;
		seuraavaLevyNro = levyNro + 1;
	}

	/**
	 * palauttaa julkaisuvuoden
	 * @return julkaisuVuosi
	 */
	public int getjulkaisuVuosi(){
		return julkaisuVuosi;
	}

	/**
	 * palauttaa nro:n kenelle artistille levy kuuluu
	 * @return kenenArtistin
	 */
	public int getLevynArtisti(){
		return artistiNro;
	}

	/**
	 * palauttaa levyn nimen
	 * @return lrvyNimi
	 */
	public String getLevyNimi(){
		return levyNimi;
	}

	/**
	 * palauttaa levyn genren
	 * @return genre
	 */
	public String getGenre(){
		return genre;
	}

	
	
    /**
     * @return levyNro
     */
    public int rekisteroi() {
        if ( levyNro != 0 ) return levyNro; // ???
        levyNro = seuraavaLevyNro;
        seuraavaLevyNro++;
        return levyNro;
    }
	
    /**
     * Apumetodi, jolla saadaan täytettyä testiarvot jäsenelle.
     */
    public void vastaaLevy() {
        levyNimi = "Vuosisadan parhaat vol." + rand(1000, 9999);
        genre = "FolkPop" + rand(10, 99);
        julkaisuVuosi = rand(1930, 2012);
        
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
    
    
    
    public int getTunnusNro(){
    	return tunnusNro;
    }
    
    public void setTunnusNro(int nro){
    	tunnusNro = nro;
    	if ( tunnusNro >= seuraavaLevyNro ) seuraavaLevyNro = tunnusNro + 1;
    }
    
    
    
    
    
    
    
    public String toString(){
    	return getLevyNro() + "/" + getLevyNimi() +"/" + genre + "/" + julkaisuVuosi + "/";
    }
    
    public void parse (String rivi){
    	StringBuffer sb = new StringBuffer(rivi);
    	
        String prefix = "";
 	   
 	   	String[] kentat = rivi.substring(prefix.length()).split("/");
 	   	
 	   	int levyNro = Integer.parseInt(kentat[2]);
 	   	setLevyNro(levyNro);
 	   	String levynNimi = kentat[3];
 	   	String genre = kentat[4];
 	   	setLevyNimi(levynNimi);
 	   	setGenre(genre);
 	   	int year = Integer.parseInt(kentat[5]);
 	   	setJulkaisuVuosi(year);
 	   	
    }
    
    
    
    
    
    
    
    
    /**
     * Tulostetaan Levyn tiedot
     * @param out tietovirta johon tulostetaan
     */
	public void tulosta(PrintStream out) {
        out.println(String.format("%03d", levyNro) + "  " + levyNimi + "\n" +
        		"Kuuluu artistille: " + String.format("%03d",artistiNro));
        out.println("Levyn genre: " + genre + " \n" +
        		"Julkaisuvuosi: " + julkaisuVuosi);
        out.println();
    }

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Artistit artistit = new Artistit();
		
		Artisti a1 = new Artisti();
		a1.rekisteroi();
		a1.vastaa();
		
		//artistit.lisaaArtisti(a1);
		
		Levy l1 = new Levy(a1.getArtistiNro());
		l1.rekisteroi();
		l1.vastaaLevy();
		
		Artisti apu =(artistit.etsiArtistiListasta(l1.getLevynArtisti()));
		apu.tulosta(System.out);

	}



}
