package repository.impl;

import annotations.MysqlConn;
import domain.models.Grade;
import domain.models.Student;
import domain.models.Subject;
import domain.models.Teacher;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import mapping.dtos.GradeDto;
import mapping.mappers.GradeMapper;
import repository.GradeRepository;
import repository.Repository;
import singledomain.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@RequestScoped
@Named("gradeRep")
public class GradeRepositoryimpl implements Repository<GradeDto> {
    @Inject
    @MysqlConn
    private Connection conn;
    public GradeDto createGrade(ResultSet resultSet)throws SQLException{
        Grade grade = new Grade();

        grade.setId(resultSet.getLong("id"));
        Student student = new Student();
        student.setId(resultSet.getLong("studentid"));
        student.setName(resultSet.getString("name"));
        student.setEmail(resultSet.getString("email"));
        student.setSemester(resultSet.getString("semester"));
        grade.setStudent(student);
        Subject subject = new Subject();
        subject.setId(resultSet.getLong("subjectid"));
        subject.setName(resultSet.getString("subject"));
        Teacher teacher = new Teacher();
        teacher.setId(resultSet.getLong("teacherid"));
        teacher.setName(resultSet.getString("teachername"));
        teacher.setEmail(resultSet.getString("teachermail"));
        subject.setTeacher(teacher);
        grade.setSubject(subject);
        grade.setGrade(resultSet.getDouble("grade"));


        return GradeMapper.mapFrom(grade);
    }

    public List<GradeDto> list(){
        List<GradeDto> gradeList = new ArrayList<GradeDto>();
        try(Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery("SELECT gra.id, gra.studentid, stu.name, stu.email, stu.semester, " +
                    "gra.subjectid, sub.name AS subject, sub.teacherid, tea.name AS teachername, tea.email AS " +
                    "teachermail, gra.grade FROM grade AS gra, student AS stu, subject AS sub, teacher AS tea WHERE " +
                    "gra.studentid = stu.id AND gra.subjectid = sub.id AND sub.teacherid = tea.id;")){
            while(rs.next()){
                GradeDto grade = createGrade(rs);
                gradeList.add(grade);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gradeList;
    }
    public GradeDto byId(Long id){
        GradeDto grade = null;
        try(PreparedStatement preparedStatement = conn
                .prepareStatement("SELECT gra.id, gra.studentid, stu.name, stu.email, stu.semester, " +
                        "gra.subjectid, sub.name AS subject, sub.teacherid, tea.name AS teachername, tea.email AS " +
                        "teachermail, gra.grade FROM grade AS gra, student AS stu, subject AS sub, teacher AS " +
                        "tea WHERE gra.studentid = stu.id AND gra.subjectid = sub.id AND sub.teacherid = tea.id " +
                        "AND gra.id = ?;")){
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                grade = createGrade(resultSet);
            }
            resultSet.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return grade;
    }
    public void delete(Long id){
        try(PreparedStatement preparedStatement = conn
                .prepareStatement("DELETE FROM grade WHERE id =?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }
    public void save(GradeDto gradeDto){
        String sql;
        Grade grade = GradeMapper.mapFrom(gradeDto);
        if(grade.getId() != null && grade.getId()>0){
            sql = "UPDATE grade SET studentid=?, subjectid=?, grade=? WHERE id=?";
        }else{
            sql = "INSERT INTO grade (studentid, subjectid, grade) VALUES (?,?,?);";
        }
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setLong(1, grade.getStudent().getId());
            stmt.setLong(2, grade.getSubject().getId());
            stmt.setDouble(3, grade.getGrade());
            if(grade.getId() != null && grade.getId()>0){
                stmt.setLong(4, grade.getId());
            }
            stmt.executeUpdate();
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }
}
