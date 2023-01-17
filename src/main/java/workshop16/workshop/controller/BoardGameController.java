package workshop16.workshop.controller;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import workshop16.workshop.model.Mastermind;
import workshop16.workshop.service.BoardGameService;

@RestController
@RequestMapping(path = "/api/boardgame", produces = MediaType.APPLICATION_JSON_VALUE ,consumes = MediaType.APPLICATION_JSON_VALUE)
public class BoardGameController {
  
  @Autowired
  BoardGameService bgService;

  @PostMapping()
  public ResponseEntity<String> createBoardGame(@RequestBody Mastermind ms){
    // Check if can post Json Object to endpoint
    // JsonObjectBuilder builder = Json.createObjectBuilder();
    // builder.add("mastermind", ms.toJson());
    // JsonObject res = builder.build();
    // return ResponseEntity.status(HttpStatus.CREATED).body(res.toString());
    int insertCount = bgService.saveGame(ms);
    Mastermind result = new Mastermind();
    result.setId(ms.getId());
    result.setInsertCount(insertCount);
    if(insertCount==0){
      // Failed to insert into redis
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON).body(result.toJsonInsert().toString());
    }
    return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(result.toJsonInsert().toString());
  }

  @GetMapping(path = "{msId}")
  public ResponseEntity<String> getBoardGameById(@PathVariable String msId){
    Mastermind result = bgService.findById(msId);
    if(result == null){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON).body("Not Found");
    }
    return ResponseEntity.status(HttpStatus.FOUND).contentType(MediaType.APPLICATION_JSON).body(result.toJson().toString());
  }
  // Why require toJson().toString()?
}
