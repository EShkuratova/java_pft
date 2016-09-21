package ru.stqa.pft.mantis.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.Users;

import java.util.List;

/**
 * Created by eshkuratova on 31.08.2016.
 */
public class DbHelper {
    private final SessionFactory sessionFactory;

    public DbHelper(){

        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();

            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
          }

    public Users users() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<UserData> users = session.createQuery("from UserData where access_level = 25").list();
        session.getTransaction().commit();
        session.close();
        return new Users(users);
    }

}
