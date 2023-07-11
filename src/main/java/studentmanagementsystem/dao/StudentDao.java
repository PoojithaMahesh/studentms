package studentmanagementsystem.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import studentmanagementsystem.dto.Student;

public class StudentDao {

	public EntityManager getEntityManager() {
		return Persistence.createEntityManagerFactory("vinod").createEntityManager();
	}

	public Student saveStudent(Student student) {
		EntityManager entityManager = getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.persist(student);
		entityTransaction.commit();
		return student;
	}

	public Student fetchStudentByEmail(String email) {
		EntityManager entityManager = getEntityManager();
		Query query = entityManager.createQuery("select s from Student s where s.email=?1");
		query.setParameter(1, email);
		Student student=(Student) query.getSingleResult();
		return student;
	}

	public List<Student> getAllStudents() {
		EntityManager entityManager = getEntityManager();
		Query query = entityManager.createQuery("select s from Student s");
		List<Student> list=query.getResultList();
		return list;
	}

	public Student deleteStudentById(int id) {
		EntityManager entityManager=getEntityManager();
		Student student=entityManager.find(Student.class, id);
	    EntityTransaction entityTransaction=entityManager.getTransaction();
	    entityTransaction.begin();
	    entityManager.remove(student);
	    entityTransaction.commit();
	    return student;
	}
	
	public Student findStudentById(int id) {
		EntityManager entityManager=getEntityManager();
		Student student=entityManager.find(Student.class, id);
		if(student!=null) {
			return student;
		}return null;
	}

	public Student updateStudent(int id, Student student) {
	EntityManager entityManager=getEntityManager();
	Student dbStudent=entityManager.find(Student.class, id);
	if(dbStudent!=null) {
		EntityTransaction entityTransaction=entityManager.getTransaction();
		entityTransaction.begin();
		student.setId(id);
		entityManager.merge(student);
	
		entityTransaction.commit();
		return student;
	}
	return null;
	}
}
