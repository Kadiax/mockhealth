package lifeprotect.mock.dao;


import lifeprotect.mock.model.Person;
import lifeprotect.mock.model.PersonStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonDAO extends JpaRepository<Person, Long> {
    List<Person> findAllByResidenceId(long residenceId);

    Person findByLogin(String personLogin);

    int countByUserrole(PersonStatus userrole);

    List<Person> findByUserrole(PersonStatus personStatus);
}
