package lifeprotect.mock.dao;

import lifeprotect.mock.model.Residence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResidenceDAO extends JpaRepository<Residence, Long> {

}
