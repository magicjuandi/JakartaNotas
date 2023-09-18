package repository.impl;

import domain.models.Grade;
import domain.models.Student;
import domain.models.Subject;
import domain.models.Teacher;
import repository.GradeRepository;
import singledomain.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GradeRepositoryimpl implements GradeRepository {
    private Connection getConnection() throws SQLException {
        return ConexionDB.getInstance();
    }
    public Grade createGrade(ResultSet resultSet)throws SQLException{
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


        return grade;
    }

    public List<Grade> list(){
        List<Grade> gradeList = new ArrayList<Grade>();
        try(Statement stat = getConnection().createStatement();
            ResultSet rs = stat.executeQuery("SELECT gra.id, gra.studentid, stu.name, stu.email, stu.semester, " +
                    "gra.subjectid, sub.name AS subject, sub.teacherid, tea.name AS teachername, tea.email AS " +
                    "teachermail, gra.grade FROM grade AS gra, student AS stu, subject AS sub, teacher AS tea WHERE " +
                    "gra.studentid = stu.id AND gra.subjectid = sub.id AND sub.teacherid = tea.id;")){
            while(rs.next()){
                Grade grade = createGrade(rs);
                gradeList.add(grade);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return gradeList;
    }
    public Grade byId(Long id){
        Grade grade = null;
        try(PreparedStatement preparedStatement = getConnection()
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
        } return grade;
    }
    public void delete(int id){
        try(PreparedStatement preparedStatement = getConnection()
                .prepareStatement("DELETE FROM grade WHERE id =?")) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }
    public void save(Grade grade){
        String sql;
        if(grade.getId() != null && grade.getId()>0){
            sql = "UPDATE grade SET studentid=?, subjectid=?, grade=? WHERE id=?";
        }else{
            sql = "INSERT INTO grade (studentid, subjectid, grade) VALUES (?,?,?);";
        }
        try(PreparedStatement stmt = getConnection().prepareStatement(sql)){
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
