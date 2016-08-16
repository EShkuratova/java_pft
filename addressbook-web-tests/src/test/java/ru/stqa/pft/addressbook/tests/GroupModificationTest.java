package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

/**
 * Created by eshkuratova on 31.07.2016.
 */
public class GroupModificationTest extends TestBase{
  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().groupPage();
    if(app.group().list().size()==0){
      app.group().create(new GroupData().withName("test2").withHeader("test3"));
    }

  }
  @Test

  public void testGroupModification(){

    List<GroupData> before = app.group().list();
    int index = before.size()-1;
    GroupData group = new GroupData().withId(before.get(index).getId()).withName("groupupdate1").withHeader("groupupdate2").withFooter("groupupdate3");
    app.group().modify(index, group);
    List<GroupData> after = app.group().list();
    before.remove(index);
    before.add(group);
    Comparator<? super GroupData> byId =(g1,g2) -> Integer.compare(g1.getId(),g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before,after);
    Assert.assertTrue(before.size() == after.size(), "После редактирования изменилось кол-во групп");
   // Assert.assertEquals(new HashSet<Object>(before),new HashSet<Object>(after));

  }


}
