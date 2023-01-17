package workshop16.workshop.model;

import java.io.Serializable;
import java.util.List;
import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObjectBuilder;

public class Pegs implements Serializable{
  private int total_count;
  private List<Type> types;

  // Getters and Setters
  public int getTotal_count() {
    return total_count;
  }
  public void setTotal_count(int total_count) {
    this.total_count = total_count;
  }
  public List<Type> getTypes() {
    return types;
  }
  public void setTypes(List<Type> types) {
    this.types = types;
  }

  // Methods

  public JsonObjectBuilder toJson(){
   JsonArrayBuilder arraybuilder = Json.createArrayBuilder();
   List<JsonObjectBuilder> listOfTypes = this.getTypes().stream().map(t->t.toJson()).toList();
   for(JsonObjectBuilder job: listOfTypes) arraybuilder.add(job);

    return Json.createObjectBuilder()
    .add("total_count", this.getTotal_count())
    .add("types", arraybuilder);
  }

  
}

