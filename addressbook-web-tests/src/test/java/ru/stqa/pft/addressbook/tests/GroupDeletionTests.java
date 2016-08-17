package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

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
        //Set<GroupData> before;
        Groups before = app.group().all();
        GroupData deletedGroup = (GroupData) before.iterator().next();
        app.group().delete(deletedGroup);
       // Set<GroupData> after;
        Groups after = app.group().all();
        assertThat(after.size(), equalTo(before.size()-1));
       /* for(int i =0;i<after.size();i++){
            Assert.assertEquals(before.get(i),after.get(i));
           // Assert.assertTrue(after.equals(before),"Количество групп не изменилось");
        }*/
        assertThat(after,equalTo(before.without(deletedGroup)));

    }


}
