package datastore;

import javax.persistence.*;
import org.hibernate.annotations.Immutable;

@Entity
@Immutable
public class playerRanking{
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private int id;
	
	@Column
	private String navn;
	
	@Column
	private float rangering;
	
    public  playerRanking() {
    }

    public playerRanking(String tnavn, int orgId, float ranks) {
       this.navn = tnavn;
       this.rangering = ranks;
       this.id = orgId;
    }
    
    public int getId() {
        return this.id;
    }   
    public void setId(int id) {
        this.id = id;
    }
    
    public float getRangering() {
    	return this.rangering;
    }    
    public void setRangering(float nyf) {
    	this.rangering = nyf;
    }
    
    public void setNavn(String nynavn) {
    	this.navn = nynavn;
    }
    
    public String getNavn() {
    	return this.navn;
    }
	

}
