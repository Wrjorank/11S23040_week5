package view; 
 
import entity.Todo;
import service.TodoService; 
import util.InputUtil; 
 
public class TodoView { 
 
  private TodoService todoService; 
 
  public TodoView(TodoService todoService) { 
    this.todoService = todoService; 
  } 
 
  /** 
   * Menampilkan view todo 
   */ 
  public void viewShowTodo() { 
    while (true) { 
      todoService.serviceShowTodo(); 
 
      System.out.println("Menu:"); 
      System.out.println("1. Tambah"); 
      System.out.println("2. Ubah"); 
      System.out.println("3. Hapus"); 
      System.out.println("x. Keluar"); 
 
      var input = InputUtil.input("Pilih"); 
      var stop = false; 
      switch (input) { 
        case "1" -> viewAddTodo(); 
        case "2" -> viewUpdateTodo(); 
        case "3" -> viewRemoveTodo(); 
        case "x" -> stop = true; 
        default -> System.out.println("[!] Pilihan tidak dimengerti."); 
      } 
 
      if (stop) { 
        break; 
      } 
 
      System.out.println(); 
    } 
  } 
 
  /** 
   * Menampilkan view add todo 
   */ 
  public void viewAddTodo() { 
    System.out.println("[Menambah Todo]"); 
    var title = InputUtil.input("Judul (x Jika Batal)"); 
 
    if (!title.equals("x")) { 
      todoService.serviceAddTodo(title); 
    } 
  } 
 
  /** 
   * Menampilkan view remove todo 
   */ 
  public void viewRemoveTodo() { 
    System.out.println("[Menghapus Todo]"); 
 
    var strIdTodo = InputUtil.input("[ID Todo] yang dihapus (x Jika Batal)"); 
 
    if (!strIdTodo.equals("x")) { 
      int idTodo = Integer.valueOf(strIdTodo); 
      todoService.serviceRemoveTodo(idTodo); 
    } 
  } 
 
  /** 
   * Menampilkan view update todo 
   */ 
public void viewUpdateTodo() { 
    System.out.println("[Mengubah Todo]"); 

    var strIdTodo = InputUtil.input("[ID Todo] yang diubah (x Jika Batal)"); 

    if (!strIdTodo.equals("x")) { 
        int idTodo = Integer.valueOf(strIdTodo); 

        // Get the current Todo using the service
        Todo currentTodo = todoService.getTodoById(idTodo); 
        
        if (currentTodo == null) {
            System.out.printf("[!] Tidak ditemukan todo dengan ID: %d.\n", idTodo);
            return;
        }

        // Prompt for new title, if blank use old title
        System.out.printf("[!] Tekan enter untuk menggunakan judul lama.\n");
        var newTitle = InputUtil.input("Judul Baru [Judul Lama: " + currentTodo.getTitle() + "]");
        if (newTitle.isEmpty()) {
            newTitle = currentTodo.getTitle(); // Use old title if input is empty
        }

        // Prompt for new status
        var strStatus = InputUtil.input("Status Todo [0: Belum Selesai, 1: Selesai]");
        boolean newStatus = strStatus.equals("1"); // Convert input to boolean

        // Call the service to update the todo
        boolean updated = todoService.serviceUpdateTodo(idTodo, newTitle, newStatus);

        // if (updated) {
        //     System.out.println("[!] Todo berhasil diubah.");
        // } else {
        //     System.out.println("[!] Gagal mengubah Todo.");
        // }
    } 
  }
} 