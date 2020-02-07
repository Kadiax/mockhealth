package lifeprotect.mock.dao;
import lifeprotect.mock.model.Alert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlertDAO extends JpaRepository<Alert, Long> {
}
