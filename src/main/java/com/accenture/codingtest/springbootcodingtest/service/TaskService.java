package com.accenture.codingtest.springbootcodingtest.service;
import com.accenture.codingtest.springbootcodingtest.entity.Project;
import com.accenture.codingtest.springbootcodingtest.entity.Task;
import com.accenture.codingtest.springbootcodingtest.entity.User;
import com.accenture.codingtest.springbootcodingtest.enums.Status;
import com.accenture.codingtest.springbootcodingtest.repository.ProjectRepository;
import com.accenture.codingtest.springbootcodingtest.repository.TaskRepository;
import com.accenture.codingtest.springbootcodingtest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskService {
	
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository, UserRepository userRepository, ProjectRepository projectRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(UUID id) throws Exception {
        Optional<Task> currentTask = taskRepository.findById(id);
        if (currentTask.isPresent())
            return currentTask.get();
        else throw new Exception("Task Not Found");
    }

    public Task createTask(Task task) throws Exception {
         Optional<Project> _project = projectRepository.findById(task.getProject_id());
        if (_project.isPresent()) {            
                Optional<User> _user = userRepository.findById(task.getUser_id());
                if (_user.isPresent()) {
                    Task newTask = new Task();
                    newTask.setTitle(task.getTitle());
                    newTask.setDescription(task.getDescription());
                    newTask.setStatus(Status.NOT_STARTED.name()); //9. v. When a task is created, status will be NOT_STARTED
                    newTask.setProject_id(task.getProject_id());
                    newTask.setUser_id(task.getUser_id());
                    taskRepository.save(newTask);
                    return newTask;
                } else throw new Exception("User Not Found");
            } else throw new Exception("Project Not Found");
    }

    public Task updateTask(UUID id, Task task) throws Exception {
        Optional<Task> _task = taskRepository.findById(id);
        if (_task.isPresent()) {
            Optional<Project> _project = projectRepository.findById(task.getProject_id());
            if (_project.isPresent()) {
                Optional<User> _user = userRepository.findById(task.getUser_id());
                if (_user.isPresent()) {
                    
                            Task currentTask = _task.get();
                            currentTask.setTitle(task.getTitle());
                            currentTask.setDescription(task.getDescription());
                            currentTask.setStatus(task.getStatus());
                            currentTask.setProject_id(task.getProject_id());
                            currentTask.setUser_id(task.getUser_id());
                            taskRepository.save(currentTask);
                            return currentTask;
                          } else throw new Exception("User Not Found");
            } else throw new Exception("Project Not Found");
        } else throw new Exception("Task Not Found");
    }

    public Task patchTask(UUID id, Task task) throws Exception {
        Optional<Task> _task = taskRepository.findById(id);
        if (_task.isPresent()) {
            Optional<Project> _project = projectRepository.findById(task.getProject_id());
            if (_project.isPresent()) {
                Optional<User> _user = userRepository.findById(task.getUser_id());
                if (_user.isPresent()) {
                    
                			boolean qualifyUpdate = false;
                			Task currentTask = _task.get();
                            if (StringUtils.hasLength(task.getTitle())) {
                            	currentTask.setTitle(task.getTitle());
                            	qualifyUpdate = true;}
                            if (StringUtils.hasLength(task.getDescription())) {
                            	currentTask.setDescription(task.getDescription());
                            	qualifyUpdate = true;}
                            if (StringUtils.hasLength(task.getStatus())) {
                            	currentTask.setStatus(task.getStatus());
                            	qualifyUpdate = true;}
                            if (StringUtils.hasLength(task.getProject_id().toString())) {
                            	currentTask.setProject_id(task.getProject_id());
                            	qualifyUpdate = true;}
                            if (StringUtils.hasLength(task.getUser_id().toString())) {
                            	currentTask.setUser_id(task.getUser_id());
                            	qualifyUpdate = true;}
                            if (qualifyUpdate)
                                taskRepository.save(currentTask);
                            return currentTask;
                        } else throw new Exception("User Not Found");
            } else throw new Exception("Project Not Found");
        } else throw new Exception("Task Not Found");
    }

    public void deleteTask(UUID id) throws Exception {
        Optional<Task> _task = taskRepository.findById(id);
        if (_task.isPresent())
            taskRepository.deleteById(id);
        else throw new Exception("Task not found");
    }

   
}