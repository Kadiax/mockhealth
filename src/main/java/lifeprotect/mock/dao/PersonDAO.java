package lifeprotect.mock.dao;

import lifeprotect.mock.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonDAO extends JpaRepository<Person, Long> {
}
