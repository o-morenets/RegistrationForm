package ua.testing.demo_jpa.persistence;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ua.testing.demo_jpa.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.Optional;

@Slf4j
@Repository
@Component
public class UserDAO {

    @Autowired
	private EntityManager entityManager;

    public Optional<User> findByEmail(@NonNull String email) {
		User user;
		try {
			String sql = "Select e From " + User.class.getName() + " e "
					+ " Where e.username = :email ";

			Query query = entityManager.createQuery(sql, User.class);
			query.setParameter("email", email);

			user = (User) query.getSingleResult();

		} catch (NoResultException e) {
			user = null;
		}
		return Optional.ofNullable(user);
	}
}