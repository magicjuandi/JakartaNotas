package services;

import domain.models.Subject;
import mapping.dtos.SubjectDto;

import java.util.List;

public interface SubjectService {
    List<SubjectDto> list();

    SubjectDto byId(Long id);

    void save(SubjectDto t);

    void delete(Long id);
}
