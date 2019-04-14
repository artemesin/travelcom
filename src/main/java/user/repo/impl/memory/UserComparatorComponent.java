package user.repo.impl.memory;

import user.domain.User;
import user.search.UserOrderByField;

import java.util.*;

import static common.buisness.repo.memory.CommonComparatorHolder.getComparatorForNullableStrings;
import static user.search.UserOrderByField.LAST;
import static user.search.UserOrderByField.NAME;

public class UserComparatorComponent {

    private static final UserComparatorComponent INSTANCE = new UserComparatorComponent();
    private static Map<UserOrderByField, Comparator<User>> comparatorsByField = new HashMap<>();

    private static Set<UserOrderByField> fieldComparePriorityOrder = new LinkedHashSet<>(Arrays.asList(NAME, LAST));

    static {
        comparatorsByField.put(NAME, getComparatorForNameField());
        comparatorsByField.put(LAST, getComparatorForLastField());
    }

    public UserComparatorComponent() {
    }

    public static UserComparatorComponent getInstance(){
        return INSTANCE;
    }

    private static Comparator<User> getComparatorForNameField(){
        return new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return getComparatorForNullableStrings().compare(o1.getName(), o2.getName());
            }
        };
    }

    private static Comparator<User> getComparatorForLastField(){
        return new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return getComparatorForNullableStrings().compare(o1.getLast(), o2.getLast());
            }
        };
    }

    public Comparator<User> getComparatorForField(UserOrderByField field) {
        return comparatorsByField.get(field);
    }

    public Comparator<User> getComplexComparator(UserOrderByField field){
        return new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                int result = 0;
                Comparator<User> userComparator = comparatorsByField.get(field);

                if(userComparator != null) {
                    result = userComparator.compare(o1, o2);

                    if(result == 0){
                        for(UserOrderByField otherField : fieldComparePriorityOrder){
                            if(!otherField.equals(field)){
                                result = comparatorsByField.get(otherField).compare(o1, o2);
                            }
                        }
                    }
                }
                return result;
            }
        };
    }
}
