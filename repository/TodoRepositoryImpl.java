package repository;

import entity.Todo;

public class TodoRepositoryImpl implements TodoRepository {

  public Todo[] data = new Todo[10];

  private int size = 0; 

  public boolean isEmpty() {
    return size == 0; 
  }

  @Override
  public Todo[] repoGetAllTodo() {
    return data;
  }

  @Override
  public void repoAddTodo(Todo newTodo) {
    
    if (isDuplicate(newTodo.getTitle())) {
      System.out.printf("[!] Todo %s telah tersedia\n", newTodo.getTitle());
      return;
    }

    resizeIfFull();
    
    // Tambahkan ke posisi yang data array-nya NULL
    for (int i = 0; i < data.length; i++) {
      if (data[i] == null) {
        data[i] = newTodo;
        size++; // Tambahkan ukuran ketika todo baru ditambahkan
        break;
      }
    }
  }

  @Override
  public boolean repoUpdateTodo(Integer idTodo, String newTitle, boolean newStatus) {
    for (int i = 0; i < size; i++) {
      if (data[i] != null && data[i].getId() == idTodo) {
        data[i].setTitle(newTitle);
        data[i].setFinished(newStatus);
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean repoRemoveTodo(Integer idTodo) {
    int position = -1;
    for (int i = 0; i < size; i++) { 
      if (data[i] != null && data[i].getId() == idTodo) {
        position = i;
        break;
      }
    }

    if (position == -1) {
      return false; 
    }

    for (int i = position; i < size - 1; i++) {
      data[i] = data[i + 1];
    }

    data[size - 1] = null; 
    size--; 
     
    System.out.printf("Dihapus: %d\n", idTodo);
    return true;
  
  }


  private boolean isDuplicate(String title) {
    String titleLowerCase = title.toLowerCase();  // Konversi judul ke huruf kecil
    for (Todo todo : data) {
      if (todo != null && todo.getTitle().toLowerCase().equals(titleLowerCase)) {
        return true;
      }
    }
    return false;
  }

  private boolean isFull() {
    return size >= data.length; 
  }

  private void resizeIfFull() {
    if (isFull()) {
      var temp = data;
      data = new Todo[data.length * 2]; 

      for (int i = 0; i < temp.length; i++) {
        data[i] = temp[i];
      }
    }
  }
  
}