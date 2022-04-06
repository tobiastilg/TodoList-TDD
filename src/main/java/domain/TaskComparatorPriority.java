package domain;

import java.util.Comparator;

public class TaskComparatorPriority implements Comparator<Task> {

    @Override
    public int compare(Task o1, Task o2) {
        if (o1 == null && o2 != null) return 1;
        if (o1 != null && o2 == null) return -1;
        if (o1 == o2) return 0;
        if (o1.getTaskPriority().priority() > o2.getTaskPriority().priority()) {
            return -1;
        } else if (o1.getTaskPriority().priority() < o2.getTaskPriority().priority()) {
            return 1;
        } else {
            return 0;
        }
    }
}
