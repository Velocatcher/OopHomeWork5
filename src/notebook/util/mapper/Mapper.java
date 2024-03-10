package notebook.util.mapper;

import notebook.model.User;

import java.util.ArrayList;
import java.util.List;

public interface Mapper {
    String toInput(User e);
    User toOutput(String str);
    public default List<String> convertToListStr(List<User> users) {
        List<String> lines = new ArrayList<>();
        for (User u : users) {
            lines.add(toInput(u));
        }
        return lines;
    }
}
