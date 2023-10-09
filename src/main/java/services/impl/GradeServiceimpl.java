package services.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import mapping.dtos.GradeDto;
import repository.Repository;
import repository.impl.GradeRepositoryimpl;
import services.GradeService;

import java.sql.Connection;
import java.util.List;
@ApplicationScoped
public class GradeServiceimpl implements GradeService {
    @Inject
    @Named("gradeRep")
    private Repository<GradeDto> gradeRepository;


    @Override
    public List<GradeDto> list() {
        return gradeRepository.list();
    }

    @Override
    public GradeDto byId(Long id) {
        return gradeRepository.byId(id);
    }

    @Override
    public void save(GradeDto student) {
        gradeRepository.save(student);
    }

    @Override
    public void delete(Long id) {
        gradeRepository.delete(id);
    }
}
