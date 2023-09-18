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
    public List<Subject> listar() {
        return repository.listar();
    }

    @Override
    public Subject porId(Long id) {
        return repository.porId(id);
    }

    @Override
    public void guardar(Subject t) {
        repository.guardar(t);
    }

    @Override
    public void eliminar(Long id) {
        repository.eliminar(id);
    }
}
