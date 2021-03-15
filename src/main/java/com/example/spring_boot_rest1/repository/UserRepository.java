package com.example.spring_boot_rest1.repository;

import com.example.spring_boot_rest1.exceptions.UserNotFoundException;
import com.example.spring_boot_rest1.model.Authorities;
import com.example.spring_boot_rest1.model.User;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UserRepository implements Repo {
    private Map<String, User> repository = new ConcurrentHashMap<>();//key=nick+password; value=User

    public UserRepository() {
        fillDB();
    }

    public void save(User user) {
        String identifier = user.getNickname()+user.getPassword();
        repository.put(identifier, user);
    }

    public String show() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, User> entry : repository.entrySet()) {
            sb.append(entry.getKey() + ": " + entry.getValue().toString() + "\n");
        }
        return sb.toString();
    }

    @Override
    public void deleteItem(String identifier) {
        repository.remove(identifier);
    }

    public User getByIdentifier(String identifier) {
        User user = repository.get(identifier);
        if (user == null){
            throw new UserNotFoundException("User not found");
        }
        return user;
    }

    public List<Authorities> getUserAuthorities(String user, String password) {
        return null;
    }

    public void fillDB(){
        repository.put("Maximqwerty",new User("Maxim", "qwerty",List.of(Authorities.READ,Authorities.WRITE, Authorities.DELETE)));
        repository.put("Alexasdfgh",new User("Alexandr", "asdfgh",List.of(Authorities.READ)));
        repository.put("Svetlanazxcvbn",new User("Svetlana", "zxcvbn",List.of(Authorities.READ)));
        repository.put("Vsevolodqazws",new User("Vsevolod", "qazwsx",List.of(Authorities.READ,Authorities.WRITE, Authorities.DELETE)));
        repository.put("Richardwubbalubbadubdub",new User("Richard", "wubbalubbadubdub",List.of(Authorities.READ,Authorities.WRITE, Authorities.DELETE)));
    }
}
