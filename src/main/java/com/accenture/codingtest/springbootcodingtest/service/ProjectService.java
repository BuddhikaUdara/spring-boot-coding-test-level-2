package com.accenture.codingtest.springbootcodingtest.service;

import com.accenture.codingtest.springbootcodingtest.entity.Project;
import com.accenture.codingtest.springbootcodingtest.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProjectService {
	
    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<Project> getAllProjects() {
    	return projectRepository.findAll();
    }

    public Project getProjectById(UUID id) throws Exception {
        Optional<Project> currentProject = projectRepository.findById(id);
        if (currentProject.isPresent())
            return currentProject.get();
        else throw new Exception("Project Not Found");
    }

    public Project createProject(Project project) throws Exception {
       Optional<Project> projectByName = projectRepository.findProjectByName(project.getName());
        if (projectByName.isPresent())
            throw new Exception("Project Name Already Used");
        else {
            Project _project = new Project();
            _project.setName(project.getName());           
            projectRepository.save(_project);
            return _project;
        }
    }

    public Project updateProject(UUID id, Project project) throws Exception {
        Optional<Project> _project = projectRepository.findById(id);
        if (_project.isPresent()) {
                Project currentProject = _project.get();
                currentProject.setName(project.getName());                
                projectRepository.save(currentProject);
                return currentProject;
        } else throw new Exception("Project Not Found");
    }

    public Project patchProject(UUID id, Project project) throws Exception {
        boolean qualifyUpdate = false;
        Project currentProject = null;
        Optional<Project> _project = projectRepository.findById(id);
        if (_project.isPresent()) {
        	currentProject = _project.get();
            	if (StringUtils.hasLength(project.getName())) {
                Optional<Project> projectByName = projectRepository.findProjectByName(project.getName());
                if (projectByName.isPresent()) throw new Exception("Project Name Already Used");
                else {
                	currentProject.setName(project.getName());
                    qualifyUpdate = true;
                	}
            	}
        }
        if (qualifyUpdate)
            projectRepository.save(currentProject);
            return currentProject;
    }

    public void deleteProject(UUID id) throws Exception {
        Optional<Project> _project = projectRepository.findById(id);
        if (_project.isPresent())
            projectRepository.deleteById(id);
        else throw new Exception("Project Not Found");
    }
}