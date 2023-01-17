package workshop16.workshop.model;

import java.io.Serializable;
import java.util.Random;

import jakarta.json.Json;
import jakarta.json.JsonValue;

public class Mastermind implements Serializable{
  private String name;
  private Pieces pieces;
  private String id;
  private int insertCount;
  private int updateCount;
  private boolean isUpsert;
  
  // Constructor
  public Mastermind(){
    this.id=generateId(8);
  }
  
  // Getters and Setters
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public Pieces getPieces() {
    return pieces;
  }
  public void setPieces(Pieces pieces) {
    this.pieces = pieces;
  }
  
  public int getInsertCount() {
    return insertCount;
  }
  public void setInsertCount(int insertCount) {
    this.insertCount = insertCount;
  }
  public int getUpdateCount() {
    return updateCount;
  }
  public void setUpdateCount(int updateCount) {
    this.updateCount = updateCount;
  }
  public boolean isUpsert() {
    return isUpsert;
  }
  public void setUpsert(boolean isUpsert) {
    this.isUpsert = isUpsert;
  }
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  

  // Methods
  private synchronized String generateId(int numChars){
        Random r = new Random();
        StringBuilder strBuilder = new StringBuilder();
        while(strBuilder.length() < numChars){
            strBuilder.append(Integer.toHexString(r.nextInt()));
        }
        return strBuilder.toString().substring(0, numChars);
    }

  public JsonValue toJson() {
    return Json.createObjectBuilder()
    .add("name", this.getName())
    .add("pieces", this.getPieces().toJson())
    .add("id",this.getId())
    .build();
  }

  public JsonValue toJsonInsert(){
    return Json.createObjectBuilder()
    .add("id", this.getId())
    .add("insert_count", this.getInsertCount())
    .build();
  }

  public JsonValue toJsonUpdate(){
    return Json.createObjectBuilder()
    .add("id", this.getId())
    .add("update_count", this.getUpdateCount())
    .build();
  }

}



