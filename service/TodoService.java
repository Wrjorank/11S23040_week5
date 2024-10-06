package service;

import entity.Todo;

public interface TodoService { 
  void serviceShowTodo(); 
 
  void serviceAddTodo(String title); 
 
  void serviceRemoveTodo(Integer id);
  
  boolean serviceUpdateTodo(Integer id, String newTitle, boolean newStatus);

  Todo getTodoById(Integer id);
  
}

