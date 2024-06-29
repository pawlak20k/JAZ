package pl.pjatk.pawkle;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pjatk.pawkle.model.Nbp;

public interface Repository extends JpaRepository<Nbp, Long> {
}
