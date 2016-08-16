package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Set;

public class GroupDeletionTests extends TestBase {
    @BeforeMethod

    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.group().all().isEmpty()) {
            app.group().create(new GroupData().withName("test2").withHeader("test3"));
        }
    }


    @Test
    public void testGroupDeletion() {

        //int before=app.group().getGroupCount();
        Set<GroupData> before;
        before = app.group().all();
        GroupData deletedGroup = before.iterator().next();
        app.group().delete(deletedGroup);
        Set<GroupData> after;
        after = app.group().all();
        //int after =  app.group().getGroupCount();
        Assert.assertTrue(after.size() == before.size() - 1, "Количество групп не изменилось");
        before.remove(deletedGroup);
       /* for(int i =0;i<after.size();i++){
            Assert.assertEquals(before.get(i),after.get(i));
           // Assert.assertTrue(after.equals(before),"Количество групп не изменилось");
        }*/

        Assert.assertEquals(after, before);
    }


}
