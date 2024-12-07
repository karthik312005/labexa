package com.klef.jfsd.exam;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class ClientDemo {
    public static void main(String[] args) {
        insertRecords();
        applyCriteriaQueries();
    }

    private static void insertRecords() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Customer customer1 = new Customer();
        customer1.setName("John Doe");
        customer1.setEmail("john@example.com");
        customer1.setAge(28);
        customer1.setLocation("New York");

        Customer customer2 = new Customer();
        customer2.setName("Jane Smith");
        customer2.setEmail("jane@example.com");
        customer2.setAge(32);
        customer2.setLocation("California");

        session.save(customer1);
        session.save(customer2);

        transaction.commit();
        session.close();

        System.out.println("Records inserted successfully.");
    }

    private static void applyCriteriaQueries() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        // Equal Restriction
        Criteria criteria1 = session.createCriteria(Customer.class);
        criteria1.add(Restrictions.eq("location", "New York"));
        List<Customer> customers1 = criteria1.list();
        System.out.println("Customers in New York:");
        customers1.forEach(c -> System.out.println(c.getName()));

        // Between Restriction
        Criteria criteria2 = session.createCriteria(Customer.class);
        criteria2.add(Restrictions.between("age", 25, 30));
        List<Customer> customers2 = criteria2.list();
        System.out.println("Customers aged between 25 and 30:");
        customers2.forEach(c -> System.out.println(c.getName()));

        // Like Restriction
        Criteria criteria3 = session.createCriteria(Customer.class);
        criteria3.add(Restrictions.like("email", "%example.com"));
        List<Customer> customers3 = criteria3.list();
        System.out.println("Customers with email containing 'example.com':");
        customers3.forEach(c -> System.out.println(c.getName()));

        session.close();
    }
}
