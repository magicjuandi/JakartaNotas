package services.impl;

import domain.models.Subject;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import mapping.dtos.SubjectDto;
import reposistories.impl.SubjectRepositoryLogicimpl;
import repository.Repository;
import repository.impl.SubjectRepositoryimpl;
import services.SubjectService;

import java.sql.Connection;
import java.util.List;
@ApplicationScoped
public class SubjectServiceimpl implements SubjectService {
    @Inject
    @Named("subjectRep")
    private Repository<SubjectDto> subjectRepository;

    @Override
    public List<SubjectDto> list() {
        return subjectRepository.list();
    }

    @Override
    public SubjectDto byId(Long id) {
        return subjectRepository.byId(id);
    }

    @Override
    public void save(SubjectDto t) {
        subjectRepository.save(t);
    }

    @Override
    public void delete(Long id) {
        subjectRepository.delete(id);
    }
}
