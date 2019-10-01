package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.Course;
import entity.Student;
import util.DBConector;

public class DataService {
	
	public static int removeCourse(int courseId){
		Connection conn = null;
		PreparedStatement ps = null;
		int rs = 0;
		try {
			conn = DBConector.getConnection();
			conn.setAutoCommit(false);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		String sql = "delete from Course where id=?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, courseId);
			rs = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(rs==1){
				conn.commit();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			ps.close();
			DBConector.close(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rs;
	}
	public static int removeStudentCourse(int studentId, int courseId){
		Connection conn = null;
		PreparedStatement ps = null;
		int rs = 0;
		try {
			conn = DBConector.getConnection();
			conn.setAutoCommit(false);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		String sql = "delete from studentCourse where studentId=? and courseId=?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, studentId);
			ps.setInt(2, courseId);
			rs = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(rs==1){
				conn.commit();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			ps.close();
			DBConector.close(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rs;
	}
	public static int addStudentCourse(int studentId, int courseId){
		Connection conn = null;
		PreparedStatement ps = null;
		int rs = 0;
		try {
			conn = DBConector.getConnection();
			conn.setAutoCommit(false);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		String sql = "insert into StudentCourse (studentId,courseId) values (?,?)";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, studentId);
			ps.setInt(2, courseId);
			rs = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(rs==1){
				conn.commit();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			ps.close();
			DBConector.close(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rs;
	}
	public static int addStudent(Student student){
		
		Connection conn = null;
		PreparedStatement ps = null;
		int rs = 0;
		try {
			conn = DBConector.getConnection();
			conn.setAutoCommit(false);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		String sql = "insert into Student (id,firstname,lastname,age) values (?,?,?,?)";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, null);
			ps.setString(2, student.getFirstName());
			ps.setString(3, student.getLastName());
			ps.setInt(4, student.getAge());
			
			rs = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(rs==1){
				conn.commit();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			ps.close();
			DBConector.close(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rs;
	}
	public static int addCourse(Course course){
		
		Connection conn = null;
		PreparedStatement ps = null;
		int rs = 0;
		try {
			conn = DBConector.getConnection();
			conn.setAutoCommit(false);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		String sql = "insert into Course (id,name,startTime) values (?,?,?)";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, null);
			ps.setString(2, course.getName());
			ps.setString(3, course.getStartTime());
			rs = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(rs==1){
				conn.commit();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			ps.close();
			DBConector.close(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rs;
	}
	public static ArrayList<Course> getUnEnrollCourses(int id){
		ArrayList<Course> courses = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBConector.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		String sql = "select * from Course where id not in (select courseId from studentCourse where studentId = ?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
		    while(rs.next()){
		    	courses.add(new Course(rs.getInt(1), rs.getString(2), rs.getString(3)));	    	
		    }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			rs.close();
			ps.close();
			DBConector.close(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return courses;
	}
	public static ArrayList<Course> getAllCourses(){
		ArrayList<Course> courses = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBConector.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		String sql = "select * from Course";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
		    while(rs.next()){
		    	courses.add(new Course(rs.getInt(1), rs.getString(2), rs.getString(3)));	    	
		    }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			rs.close();
			ps.close();
			DBConector.close(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return courses;
	}
	
	public static ArrayList<Course> getCoursesByName(String fistName, String lastName){
		ArrayList<Course> courses = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBConector.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		String sql = "select c.* from Course c "
				+ "where c.id in (select sc.courseId from StudentCourse sc "
				+ "where sc.studentId = (select s.id from Student s "
				+ "where firstName=? and lastName= ?))";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, fistName);
			ps.setString(2, lastName);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
		    while(rs.next()){
		    	courses.add(new Course(rs.getInt(1), rs.getString(2), rs.getString(3)));	    	
		    }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			rs.close();
			ps.close();
			DBConector.close(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return courses;
	}
	public static ArrayList<Student> getAllStudents(){
		ArrayList<Student> students = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBConector.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		String sql = "select * from student ";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
		    while(rs.next()){
		    	students.add(new Student(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4)));	
		    }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			rs.close();
			ps.close();
			DBConector.close(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return students;
	}
	public static ArrayList<Student> getStudentsByCourse(String courseName){
		ArrayList<Student> students = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBConector.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		String sql = "select s.* from student s "
				+ "where s.id in (select sc.studentId from StudentCourse sc "
				+ "where sc.courseId= (select c.id from course c "
				+ "where c.name=?))";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, courseName);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
		    while(rs.next()){
		    	students.add(new Student(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4)));	
		    }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			rs.close();
			ps.close();
			DBConector.close(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return students;
	}
	public static void main(String[] args) {
		getStudentsByCourse("Swimming");
		getCoursesByName("John","Johnson");
	}
}
