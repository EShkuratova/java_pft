package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Set;

/**
 * Created by eshkuratova on 31.07.2016.
 */
public class GroupModificationTest extends TestBase{
  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().groupPage();
    if(app.group().all().size()==0){
      app.group().create(new GroupData().withName("test2").withHeader("test3"));
    }

  }
  @Test

  public void testGroupModification(){

    Set<GroupData> before = app.group().all();
    GroupData modifiedGroup = before.iterator().next();
    GroupData group = new GroupData().withId(modifiedGroup.getId()).withName("groupupdate1").withHeader("groupupdate2").withFooter("groupupdate3");
    app.group().modify(group);
    Set<GroupData> after = app.group().all();
    before.remove(modifiedGroup);
    before.add(group);
    Assert.assertEquals(before,after);
    Assert.assertTrue(before.size() == after.size(), "После редактирования изменилось кол-во групп");
   // Assert.assertEquals(new HashSet<Object>(before),new HashSet<Object>(after));

  }


}
