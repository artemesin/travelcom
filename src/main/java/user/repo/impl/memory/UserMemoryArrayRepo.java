package user.repo.impl.memory;


import cities.domain.City;
import common.buisness.application.sequencecreator.SequenceCreator;
import common.solutions.utils.ArrayUtils;
import user.domain.User;
import user.repo.UserRepo;
import user.search.UserSearchCondition;

import java.util.*;

import static storage.Storage.users;


public class UserMemoryArrayRepo implements UserRepo {
    private static final User[] EMPTY_USER_ARR = new User[0];
    private UserOrderingComponent orderingComponent = new UserOrderingComponent();
    private int userIndex = -1;
    private City[] cities;

    @Override
    public User insert(User user) {
        if(userIndex == users.length - 1){
            User[] newArrUsers = new User[users.length * 2];
            System.arraycopy(users, 0, newArrUsers, 0, users.length);
            users = newArrUsers;
        }

        user.setId(SequenceCreator.getNextId());
        userIndex++;
        users[userIndex] = user;
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
        Integer userIndex = findUserIndexById(id);
        if(userIndex != null){
            return users[userIndex];
        }

        return null;
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
        User[] result = new User[users.length];
        int resultIndex = 0;

        for (User user : users) {
            if (user != null) {
                boolean found = true;

                if (searchCondition.searchByName()) {
                    found = searchCondition.getName().equals(user.getName());
                }

                if (found && searchCondition.searchByLast()) {
                    found = searchCondition.getLast().equals(user.getLast());
                }

                if (found) {
                    result[resultIndex] = user;
                    resultIndex++;
                }
            }
        }

        if (resultIndex > 0) {
            User[] toReturn = new User[resultIndex];
            System.arraycopy(result, 0, toReturn, 0, resultIndex);
            return new ArrayList<>(Arrays.asList(toReturn));
        }
        return Collections.emptyList();
    }

    @Override
    public void update(User user) {
        User found = findById(user.getId(), cities);
        found.setName(user.getName());
        found.setLast(user.getLast());
        found.setPassport(user.getPassport());
    }

    @Override
    public void deleteById(Long id) {
        Integer userIndex = findUserIndexById(id);
        if(userIndex != null){
            deleteUserByIndex(userIndex);
        }
    }

    private void deleteUserByIndex(int index){
        ArrayUtils.removeElement(users, index);
        userIndex--;
    }

    @Override
    public void printAll() {
        for(User user: users){
            System.out.println(user);
        }
    }

    private Integer findUserIndexById(Long userId){
        for (int i = 0; i < users.length; i++) {
            if(users[i].getId().equals(userId)){
                return i;
            }
        }
        return null;
    }
}
