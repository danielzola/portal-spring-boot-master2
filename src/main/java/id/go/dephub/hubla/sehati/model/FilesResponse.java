package id.go.dephub.hubla.sehati.model;
public class FilesResponse {
  private String status,message,location,name;

  public FilesResponse(String status, String message, String location, String name) {
    this.status = status;
    this.message = message;
    this.location = location;
    this.name = name;
  }

  public String getMessage() {
	  return message;
  }

  public void setMessage(String message) {
	  this.message = message;
  }

  public String getStatus() {
	  return status;
  }

  public void setStatus(String status) {
	  this.status = status;
  }

  public String getLocation() {
	  return location;
  }

  public void setLocation(String location) {
	  this.location = location;
  }

  public String getName() {
	  return name;
  }

  public void setName(String name) {
	  this.name = name;
  }
  
}