package user.repo.impl.memory;

import user.domain.User;
import user.search.UserOrderByField;
import user.search.UserSearchCondition;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class UserOrderingComponent {

    public void applyOrdering(List<User> users, UserSearchCondition searchCondition){
        Comparator<User> userComparator = null;

        UserOrderByField field = searchCondition.getOrderByField();

        switch (searchCondition.getOrderType()){

            case SIMPLE: {
                userComparator = UserComparatorComponent.getInstance().getComparatorForField(field);
                break;
            }
            case COMPLEX: {
                userComparator = UserComparatorComponent.getInstance().getComplexComparator(field);
                break;
            }
        }

        if(userComparator != null){
            switch (searchCondition.getOrderDirection()){
                case ASC: {
                    Collections.sort(users, userComparator);
                    break;
                }
                case DESC: {
                    Collections.sort(users, userComparator.reversed());
                    break;
                }
            }
        }
    }
}
