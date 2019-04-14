package user.repo.impl.memory;


import cities.domain.City;
import common.buisness.application.sequencecreator.SequenceCreator;
import user.domain.User;
import user.repo.UserRepo;
import user.search.UserSearchCondition;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static storage.Storage.userList;

public class UserMemoryCollectionRepo implements UserRepo {
    private UserOrderingComponent orderingComponent = new UserOrderingComponent();
    private City[] cities;

    @Override
    public User insert(User user) {
        user.setId(SequenceCreator.getNextId());
        userList.add(user);
        return user;
    }

    @Override
    public void insert(Collection<User> items) {
        for (User user : items) {
            insert(user);
        }
    }

    @Override
    public User findById(Long id, City[] cities) {
        return findUserById(id);
    }

    @Override
    public List<User> search(UserSearchCondition searchCondition) {
        if (searchCondition.getId() != null) {
            return Collections.singletonList(findById(searchCondition.getId(), cities));
        } else {
            List<User> result = doSearch(searchCondition);
            boolean needOrdering = !result.isEmpty() && searchCondition.needOrdering();

            if (needOrdering) {
                orderingComponent.applyOrdering(result, searchCondition);
            }

            return result;
        }
    }

    private List<User> doSearch(UserSearchCondition searchCondition){

        List<User> result = new ArrayList<>();
        for (User user : userList) {
            if (user != null) {
                boolean found = true;
                if (searchCondition.searchByName()) {
                    found = searchCondition.getName().equals(user.getName());
                }

                if (found && searchCondition.searchByLast()) {
                    found = searchCondition.getLast().equals(user.getLast());
                }

                if (found) {
                   result.add(user);
                }
            }
        }
        return result;
    }

    @Override
    public void deleteById(Long id) {
        User found = findUserById(id);

        if (found != null) {
            userList.remove(found);
        }
    }

    @Override
    public void update(User user) {
        User found = findById(user.getId(), cities);
        found.setName(user.getName());
        found.setLast(user.getLast());
        found.setPassport(user.getPassport());
    }

    @Override
    public void printAll() {
        for(User user: userList){
            System.out.println(user);
        }
    }

    private User findUserById(long userId) {
        for (User user : userList) {
            if (Long.valueOf(userId).equals(user.getId())) {
                return user;
            }
        }
        return null;
    }
}
