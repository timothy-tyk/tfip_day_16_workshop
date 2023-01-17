package workshop16.workshop.model;

import java.io.Serializable;

import jakarta.json.Json;
import jakarta.json.JsonObjectBuilder;

public class Rulebook implements Serializable{
  private int total_count;
  private String file;

  // Getters and Setters
  public int getTotal_count() {
    return total_count;
  }
  public void setTotal_count(int total_count) {
    this.total_count = total_count;
  }
  public String getFile() {
    return file;
  }
  public void setFile(String file) {
    this.file = file;
  }

  // Methods
  public JsonObjectBuilder toJson(){
    return Json.createObjectBuilder()
    .add("total_count", this.getTotal_count())
    .add("file", this.getFile());
  }

  
}

