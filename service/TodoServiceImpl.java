package service; 

import entity.Todo; 
import repository.TodoRepository; 

public class TodoServiceImpl implements TodoService { 
    private TodoRepository todoRepository; 

    public TodoServiceImpl(TodoRepository todoRepository) { 
        this.todoRepository = todoRepository; 
    } 

    @Override 
    public void serviceShowTodo() { 
        Todo[] todos = todoRepository.repoGetAllTodo(); 
        System.out.println("Daftar Todo:"); 
        int counter = 0; 
        for (Todo todo : todos) { 
            if (todo != null) { 
                counter++; 
                System.out.println(todo); 
            } 
        } 

        if (counter <= 0) { 
            System.out.println("- Data todo belum tersedia!"); 
        } 
    } 

    @Override 
    public void serviceAddTodo(String title) { 
        Todo newTodo = new Todo(title); 
        todoRepository.repoAddTodo(newTodo); 
    } 

    @Override 
    public boolean serviceUpdateTodo(Integer id, String newTitle, boolean newStatus) {
        boolean success = todoRepository.repoUpdateTodo(id, newTitle, newStatus);
        if (!success) {
            System.out.printf("[!] Gagal mengubah todo dengan ID: %d.\n", id);
        }
        return success; // Return success or failure
    }

    @Override 
    public void serviceRemoveTodo(Integer id) { 
        boolean success = todoRepository.repoRemoveTodo(id); 
        if (!success) { 
            System.out.printf("[!] Tidak ditemukan todo dengan ID: %d.\n", id); 
        } 
    }

    public Todo getTodoById(Integer id) {
        Todo[] todos = todoRepository.repoGetAllTodo();
        for (Todo todo : todos) {
            if (todo != null && todo.getId() == id) {
                return todo; // Return the found Todo
            }
        }
        return null; // Return null if not found
    }
} 
