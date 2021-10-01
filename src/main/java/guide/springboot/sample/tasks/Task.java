package guide.springboot.sample.tasks;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="tasks")
public class Task {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String details;
    private String status;

    public Task(){}

    public Task( final String details, final String status){

        this.details = details;
        this.status = status;
    }

    public String getIdentifier(){ return id;}

    public String getDetails() {return details;}

    public String getStatus() {return status;}

    public void setDetails(String details){this.details = details;}
    public void setStatus(String status){this.status = status;}

}
