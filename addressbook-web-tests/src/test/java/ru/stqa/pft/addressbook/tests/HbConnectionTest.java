package ru.stqa.pft.addressbook.tests;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

/**
 * Created by eshkuratova on 30.08.2016.
 */
public class HbConnectionTest {
    private SessionFactory sessionFactory;

    @BeforeClass
    protected void setUp() throws Exception {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            e.printStackTrace();
            StandardServiceRegistryBuilder.destroy( registry );
        }
    }
    @Test
    public void hbConnTest(){
     Session session= sessionFactory.openSession();
        session.beginTransaction();
        List result = session.createQuery( "from ContactData where deprecated = '0000-00-00'" ).list();
        GroupData groupData = new GroupData().withName("InsertName").withHeader("InsertHeader").withFooter("InsertFooter");
        session.save(groupData);
       // session.getTransaction().commit();
        session.close();
        for ( ContactData contact : (List<ContactData>) result ) {
            System.out.println(contact);
            System.out.println(contact.getGroups());
        }


    }
}
