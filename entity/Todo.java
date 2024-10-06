package entity; 
 
public class Todo { 
  public static int total = 0; 
 
  private int id; 
  private String title; 
  private boolean finished; 
 
  private void setId() { 
    total++; 
    this.id = total; 
  } 
 
  public Todo() { 
    setId(); 
  } 
 
  public Todo(String title) { 
    setId(); 
    this.title = title; 
  } 
 
  public Todo(String title, boolean finished) { 
    setId(); 
    this.title = title; 
    this.finished = finished; 
  } 
 
  public int getId() { 
    return id; 
  } 
 
  public String getTitle() { 
    return title; 
  } 
 
  public void setTitle(String title) { 
    this.title = title; 
  } 
 
  public boolean isFinished() { 
    return finished; 
  } 
 
  public void setFinished(boolean finished) { 
    this.finished = finished; 
  } 
 
  @Override 
  public String toString() { 
    String status = (finished) ? "Selesai" : "Belum Selesai"; 
    return String.format("%d | %s | %s", id, title, status); 
  } 
 
} 