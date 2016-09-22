package ru.stqa.pft.mantis.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.Issue;
import ru.stqa.pft.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

/**
 * Created by eshkuratova on 22.09.2016.
 */
public class SoapTests extends TestBase {
    @Test

    public void testGetProjects() throws MalformedURLException, ServiceException, RemoteException {

        Set<Project> projects = app.soap().getProject();
        System.out.println(projects.size());

    }

    @Test
    public void testCreateIssue() throws MalformedURLException, ServiceException, RemoteException{
        Set<Project> projects = app.soap().getProject();
        Issue issue = new Issue().withSummary("new issue")
                .withDescription("bla-bla-bla").withProject(projects.iterator().next());
        Issue createdIssue = app.soap().addIssue(issue);
        Assert.assertEquals(issue.getSummary(),createdIssue.getSummary());


    }
}
