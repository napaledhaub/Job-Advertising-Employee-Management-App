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

    public ServiceMenu addServiceMenu(AddServiceMenuRequest addServiceMenuRequest) {
        ServiceMenu serviceMenu = new ServiceMenu();
        serviceMenu.setName(addServiceMenuRequest.getName());
        serviceMenu.setSchedule(addServiceMenuRequest.getSchedule());
        serviceMenu.setDurationInMinutes(addServiceMenuRequest.getDurationInMinutes());
        serviceMenu.setPricePerSession(addServiceMenuRequest.getPricePerSession());
        serviceMenu.setTotalSessions(addServiceMenuRequest.getTotalSessions());
        List<Exercise> exerciseList = new ArrayList<>();
        addServiceMenuRequest.getExerciseList().forEach(excerciseRequest -> {
            Exercise exercise = new Exercise();
            exercise.setName(excerciseRequest.getName());
            exercise.setDescription(excerciseRequest.getDescription());
            exercise.setDurationInMinutes(excerciseRequest.getDurationInMinutes());
            exercise.setServiceMenu(serviceMenu);
            exerciseList.add(exercise);
        });
        serviceMenu.setExerciseList(exerciseList);
        serviceMenuDAO.save(serviceMenu);

        return serviceMenu;
    }
}
