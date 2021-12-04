package service.manager;

import config.ConfigReadAndWriteFile;
import file.Path;
import model.Manager;

import java.io.IOException;
import java.util.List;

public class ManagerServiceImpl implements IManager{
    public String PATH_MANAGER;
    ConfigReadAndWriteFile<Manager> configReadAndWriteFile ;
    List<Manager> managerList;

    public ManagerServiceImpl() {
        this.PATH_MANAGER = Path.PATH+"manager.txt";
        this.configReadAndWriteFile = new ConfigReadAndWriteFile<>();
        this.managerList = configReadAndWriteFile.readFromFile(PATH_MANAGER);
    }
    @Override
    public List<Manager> findALl() throws IOException {
        this.configReadAndWriteFile.writeToFile(this.PATH_MANAGER, this.managerList);
        return this.managerList;

    }
    public int findIndexByUserManager(String userManager){
        for (int i = 0; i < managerList.size(); i++) {
            if(userManager.equals(managerList.get(i).getUserManager())){
                return i;
            }
        }
        return -1;
    }
    public Manager detailManagerByUser(String userManager){
        return managerList.get(findIndexByUserManager(userManager));
    }
    public boolean delete(String id){
        int index = findIndexByUserManager(id);
        if(index>-1){
            managerList.remove(index);
            return true;
        }else {
            return false;
        }
    }
    public void edit(int index,Manager manager){
        managerList.set(index,manager);
    }
    public void save(Manager manager) {
        managerList.add(manager);
    }
    public boolean loginStaff(String userManager, String passwords){
        int index = findIndexByUserManager(userManager);
        if(index>-1){
            if(passwords.equals(managerList.get(index).getPasswords())&&managerList.get(index).getRole().equals("STAFF")){
                return true;
            }
            return false;
        }
        return false;
    }
    public boolean loginUser(String userManager, String passwords){
        int index = findIndexByUserManager(userManager);
        if(index>-1){
            if(passwords.equals(managerList.get(index).getPasswords())&&managerList.get(index).getRole().equals("USER")){
                return true;
            }
            return false;
        }
        return false;
    }
    public boolean loginAdmin(String userManager, String passwords){
        int index = findIndexByUserManager(userManager);
        if(index>-1){
            if(passwords.equals(managerList.get(index).getPasswords())&&managerList.get(index).getRole().equals("ADMIN")){
                return true;
            }
            return false;
        }
        return false;
    }
}
