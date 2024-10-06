import repository.TodoRepository; 
import repository.TodoRepositoryImpl; 
import service.TodoService; 
import service.TodoServiceImpl; 
import view.TodoView; 
 
public class App { 
  public static void main(String[] args) { 
    TodoRepository todoRepository = new TodoRepositoryImpl(); 
    TodoService todoService = new TodoServiceImpl(todoRepository); 
    TodoView todoView = new TodoView(todoService); 
 
    todoView.viewShowTodo(); 
  } 
} 