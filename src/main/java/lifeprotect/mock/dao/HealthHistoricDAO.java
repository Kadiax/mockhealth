package lifeprotect.mock.dao;

import lifeprotect.mock.model.HealthHistoric;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HealthHistoricDAO extends JpaRepository<HealthHistoric, Long> {
}
