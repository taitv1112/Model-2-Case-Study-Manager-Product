package controller;

import model.Manager;
import service.manager.ManagerServiceImpl;

import java.io.IOException;
import java.util.List;

public class ManagerController {
    ManagerServiceImpl managerService = new ManagerServiceImpl();
    public List<Manager> showListManager() throws IOException {
        return this.managerService.findALl();
    }
    public void addManager(Manager manager) throws IOException {
        managerService.save(manager);
        showListManager();
    }
    public boolean deleteManager(String id){
        return managerService.delete(id);
    }
    public void editManager(int index, Manager manager){
        managerService.edit(index,manager);
    }
    public int findIndexByUserName(String id){return managerService.findIndexByUserManager(id);}
    public Manager detailManager(String id) throws IOException {
        return managerService.findALl().get(managerService.findIndexByUserManager(id));
    }
    public boolean loginStaff(String userManager, String password ){
        return managerService.loginStaff(userManager,password);
    }
    public boolean loginUser(String userManager, String password){
        return managerService.loginUser(userManager,password);
    }
    public boolean loginAdmin(String userManager, String password){
        return managerService.loginAdmin(userManager,password);
    }
    public Manager detailManagerByUser(String userManager){
        return managerService.detailManagerByUser(userManager);
    }
}
