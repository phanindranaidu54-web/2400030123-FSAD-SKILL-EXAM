
package com.klef.fsad.exam;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.Date;
import java.util.List;

public class ClientDemo {

    public static void main(String[] args) {

        SessionFactory sf = new Configuration().configure().buildSessionFactory();
        Session session = sf.openSession();

        session.beginTransaction();

        // I. Insert record using persistent object
        Invoice inv = new Invoice("Laptop Purchase", new Date(), "PAID", 75000);
        session.save(inv);

        session.getTransaction().commit();

        // II. View all records using HQL
        session.beginTransaction();

        Query q = session.createQuery("from Invoice");
        List<Invoice> list = q.list();

        for(Invoice i : list)
        {
            System.out.println(i.getId()+" "+i.getName()+" "+i.getDate()+" "+i.getStatus()+" "+i.getAmount());
        }

        session.getTransaction().commit();

        session.close();
        sf.close();
    }
}
