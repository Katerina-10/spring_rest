package com.spring.rest.dao;

import com.spring.rest.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

    @Autowired                              // для подключения к БД DAO должен иметь доступ к sessionFactory
    private SessionFactory sessionFactory; //Dependenci Ingection с помощью поля класса (зависимость от sessionFactory бина)

    @Override
    //@Transactional //когда есть сервис - указывается в сервисе
    public List<Employee> getAllEmployees() {
        Session session = sessionFactory.getCurrentSession();

        //List<Employee> allEmps = session.createQuery("from Employee", Employee.class).getResultList();

        Query<Employee> query = session.createQuery("from Employee", Employee.class);
        List<Employee> allEmps = query.getResultList();


        return allEmps;
    }

    @Override
    public void saveEmpl(Employee employee) {
        Session session = sessionFactory.getCurrentSession();
        //session.save(employee);
        session.saveOrUpdate(employee);//если id = 0, то save, если id из бд, то update. метод hibernate
    }

    @Override
    public Employee getEmployee(int id)
    {
        Session session = sessionFactory.getCurrentSession();
        Employee employee = session.get(Employee.class, id);
        return employee;
    }

    @Override
    public void deleteEmployee(int id) {
        Session session = sessionFactory.getCurrentSession();
        //Query<Employee> query = session.createQuery("delete from Employee where id=:employeeId");
        Query<Employee> query = session.createQuery("delete from Employee where id="+id);
        //query.setParameter("employeeId", id);
        query.executeUpdate();
    }
}
