package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

public class GroupDeletionTests extends TestBase {
  @BeforeMethod

  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (app.group().list().isEmpty()) {
      app.group().create(new GroupData("test2", null, "test3"));
    }
  }


  @Test
  public void testGroupDeletion() {

    //int before=app.group().getGroupCount();
    List<GroupData> before = new ArrayList<GroupData>();
    before = app.group().list();
    int index = before.size() - 1;
    app.group().delete(index);
    List<GroupData> after = new ArrayList<GroupData>();
    after = app.group().list();
    //int after =  app.group().getGroupCount();
    Assert.assertTrue(after.size() == before.size() - 1, "Количество групп не изменилось");
    before.remove(index);
       /* for(int i =0;i<after.size();i++){
            Assert.assertEquals(before.get(i),after.get(i));
           // Assert.assertTrue(after.equals(before),"Количество групп не изменилось");
        }*/

    Assert.assertEquals(after, before);
  }




}
