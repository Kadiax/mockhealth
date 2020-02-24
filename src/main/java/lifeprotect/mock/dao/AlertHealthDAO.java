package lifeprotect.mock.dao;
import lifeprotect.mock.model.AlertHealth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlertHealthDAO extends JpaRepository<AlertHealth, Long> {
}
