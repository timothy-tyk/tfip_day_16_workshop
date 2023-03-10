package workshop16.workshop.model;

import java.io.Serializable;

import jakarta.json.Json;
import jakarta.json.JsonObjectBuilder;

public class DecodingBoard implements Serializable{
  private int total_count;

  // Getters and Setters
  public int getTotal_count() {
    return total_count;
  }
  public void setTotal_count(int total_count) {
    this.total_count = total_count;
  }

  // Methods
  public JsonObjectBuilder toJson(){
    return Json.createObjectBuilder()
    .add("total_count", this.getTotal_count()); 
  }
}
