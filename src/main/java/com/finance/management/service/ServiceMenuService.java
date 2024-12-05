package com.finance.management.service;

import com.finance.management.dao.ServiceMenuDAO;
import com.finance.management.entity.Exercise;
import com.finance.management.entity.ServiceMenu;
import com.finance.management.util.AddServiceMenuRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceMenuService {
    @Autowired
    private ServiceMenuDAO serviceMenuDAO;

    public List<ServiceMenu> getAllServiceMenus() {
        return serviceMenuDAO.findAll();
    }

    public ServiceMenu addServiceMenu(AddServiceMenuRequest dtoAddServiceMenuRequest) {
        ServiceMenu serviceMenu = new ServiceMenu();
        serviceMenu.setName(dtoAddServiceMenuRequest.getName());
        serviceMenu.setSchedule(dtoAddServiceMenuRequest.getSchedule());
        serviceMenu.setDurationInMinutes(dtoAddServiceMenuRequest.getDurationInMinutes());
        serviceMenu.setPricePerSession(dtoAddServiceMenuRequest.getPricePerSession());
        serviceMenu.setTotalSessions(dtoAddServiceMenuRequest.getTotalSessions());
        List<Exercise> exerciseList = new ArrayList<>();
        dtoAddServiceMenuRequest.getExerciseList().forEach(dtoExercise -> {
            Exercise exercise = new Exercise();
            exercise.setName(dtoExercise.getName());
            exercise.setDescription(dtoExercise.getDescription());
            exercise.setDurationInMinutes(dtoExercise.getDurationInMinutes());
            exercise.setServiceMenu(serviceMenu);
            exerciseList.add(exercise);
        });
        serviceMenu.setExerciseList(exerciseList);
        serviceMenuDAO.save(serviceMenu);

        return serviceMenu;
    }
}
