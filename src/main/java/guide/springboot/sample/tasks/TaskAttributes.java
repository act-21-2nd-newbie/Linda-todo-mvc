package guide.springboot.sample.tasks;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TaskAttributes {

    private String details;
    private String status;

    public TaskAttributes(String details, String status){
        this.details = details;
        this.status = status;
    }

    public String getDetails(){return details;}
    public String getStatus(){return status;}

    public void setDetails(String details) {
        this.details = details;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
