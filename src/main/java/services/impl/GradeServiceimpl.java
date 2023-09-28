package services.impl;

import mapping.dtos.GradeDto;
import repository.Repository;
import repository.impl.GradeRepositoryimpl;
import services.GradeService;

import java.sql.Connection;
import java.util.List;

public class GradeServiceimpl implements GradeService {
    private final Repository<GradeDto> gradeRepository;

    public GradeServiceimpl(Connection connection) {
        this.gradeRepository = new GradeRepositoryimpl(connection);
    }

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
