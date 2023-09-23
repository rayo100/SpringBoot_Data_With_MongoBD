package org.adaschool.api.service.user;

import org.adaschool.api.repository.user.User;
import org.adaschool.api.repository.user.UserMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersServiceMongoDb implements UsersService {

    private final UserMongoRepository userMongoRepository;

    @Autowired
    public UsersServiceMongoDb(UserMongoRepository userMongoRepository) {
        this.userMongoRepository = userMongoRepository;
    }

    @Override
    public User save(User user) {
        Optional<User> product = findById(user.getId());
        if(product.isEmpty()){
            this.userMongoRepository.save(user);
        }
        return user;
    }

    @Override
    public Optional<User> findById(String id) {
        return this.userMongoRepository.findById(id);
    }

    @Override
    public List<User> all() {
        return this.userMongoRepository.findAll();
    }

    @Override
    public void deleteById(String id) {
        this.userMongoRepository.deleteById(id);
    }

    @Override
    public User update(User user, String userId) {
        Optional<User> userToUpdated = findById(userId);
        if(userToUpdated.isPresent()){
            userToUpdated.get().setEmail(user.getEmail());
            userToUpdated.get().setPasswordHash(user.getPasswordHash());
        }
        save(userToUpdated.get());
        return userToUpdated.get();
    }
}
