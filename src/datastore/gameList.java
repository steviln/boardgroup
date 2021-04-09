package datastore;

import javax.persistence.*;
import org.hibernate.annotations.Immutable;

@Entity
@Immutable
public class gameList {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private int id;
	
	@Column
	private String navn;
	
	@Column
	private int spillinger;
	
	public gameList() {
		
	}
	
	public gameList(int nyid, String navne, int spillings) {
		this.navn = navne;
		this.id = nyid;
		this.spillinger = spillings;
	}
	
    public int getId() {
        return this.id;
    }   
    public void setId(int id) {
        this.id = id;
    }
    
    public float getSpillinger() {
    	return this.spillinger;
    }    
    public void setSpillinger(int nyf) {
    	this.spillinger = nyf;
    }
    
    public void setNavn(String nynavn) {
    	this.navn = nynavn;
    }
    
    public String getNavn() {
    	return this.navn;
    }
	
	

}
