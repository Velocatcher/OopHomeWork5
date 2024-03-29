package notebook.model.repository;

import notebook.model.User;

import java.util.List;
import java.util.Optional;

public interface GBRepository {
    List<User> findAll();
    User create(User user);
    Optional<User> findById(Long id);
    Optional<User> update(Long userId, User update);
    boolean delete(Long userIdDelete);


    /**
     * Data Access Object (DAO) слой, с методами для работы с БД
     */
    interface Operation {
        List<String> readAll();
        void saveAll(List<String> data);
    }
}
