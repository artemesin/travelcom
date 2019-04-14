package common.buisness.repo.memory;

import java.util.Comparator;

public final class CommonComparatorHolder {

    private static final CommonComparatorHolder INSTANCE = new CommonComparatorHolder();

    private static Comparator<String> comparatorForNullableStrings = new Comparator<String>() {
        @Override
        public int compare(String o1, String o2) {

            if(o1 == null && o2 == null){
                return 0;
            }else if(o1 != null){
                return o1.compareTo(o2);
            }else {
                return -1;
            }
        }
    };

    private static Comparator<Integer> integerComparator = new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }
    };

    private CommonComparatorHolder(){}

    public static Comparator<String> getComparatorForNullableStrings(){
        return comparatorForNullableStrings;
    }

    public static Comparator<Integer> getIntegerComparator(){
        return integerComparator;
    }
}
