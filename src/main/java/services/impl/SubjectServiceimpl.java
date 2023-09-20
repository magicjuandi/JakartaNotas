package services.impl;

import domain.models.Subject;
import reposistories.impl.SubjectRepositoryLogicimpl;
import services.SubjectService;

import java.util.List;

public class SubjectServiceimpl implements SubjectService {
    private final SubjectRepositoryLogicimpl repository;
    public SubjectServiceimpl(SubjectRepositoryLogicimpl repository){
        this.repository = repository;
    }
    @Override
    public List<Subject> list() {
        return repository.list();
    }

    @Override
    public Subject byId(Long id) {
        return repository.byId(id);
    }

    @Override
    public void save(Subject t) {
        repository.save(t);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }
}
