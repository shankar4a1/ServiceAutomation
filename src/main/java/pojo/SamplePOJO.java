package pojo;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SamplePOJO {
    private String firstName;
    private String lastName;
    private String [] arraySplit;



    public String getFirstName(){
        return firstName;
    }
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public String getLastName(){
        return lastName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public String [] getArraySplit(){
        return arraySplit;
    }

    public void setArraySplit(String arrays){
        this.arraySplit = arrays.split(",");
    }

}
