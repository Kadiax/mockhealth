package lifeprotect.mock.dao;


import lifeprotect.mock.model.Shutter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShutterDAO extends JpaRepository<Shutter, Long> {
}
