package pl._1024kb.stowarzyszenienaukijavy.simpletodo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.MailData;

public interface MailDataRepository extends JpaRepository<MailData, Long>
{

}
