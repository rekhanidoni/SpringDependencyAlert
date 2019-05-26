package com.dependencyalert.DAO;

import com.dependencyalert.service.Dependency;
import com.dependencyalert.service.DependencyInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;

import javax.annotation.PostConstruct;
import java.util.*;

@Dependency
@Service
public class UserDAOImpl implements DependencyInterface {

    @Autowired
    private UserMongoRepository userRepo;

    Set<String> usersList;
    Set<User> users;

    public UserDAOImpl() {
        this.usersList = new HashSet<String>();
    }

    @PostConstruct
    private void loadUsersFromDB() {
        System.out.println("loading users from DB");
        try {
            List<User> users = this.userRepo.findAll();
            for (User usr : users) {
                this.usersList.add(usr.getEmailId());
                this.users.add(usr);
            }
        } catch(Exception e) {
            System.out.println("Unable to load DB");
        }
    }

    public boolean isAlive() {
        try{
            this.userRepo.findAll();
        } catch(Exception e) {
            System.out.println("Database not reachable");
            return false;
        }
        return true;
    }

    public String insertUser(User usr) {
        try {
            this.usersList.add(usr.getEmailId());
            userRepo.insert(usr);
        } catch (Exception e) {
            return e.getMessage();
        }
        return null;
    }

    public void connectionBack() {
        System.out.println("DB is Back...Syncing memory users to DB");
        loadUsersFromDB();
        this.userRepo.saveAll(users);
    }

    public Set<String>  getAllEmailIds() {
        return this.usersList;
    }
}
