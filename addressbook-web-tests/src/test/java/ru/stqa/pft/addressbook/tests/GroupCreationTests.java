package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {

    app.goTo().groupPage();
    List<GroupData> before = app.group().list();
    GroupData group = new GroupData("test1", "test2", "test3");
    app.group().create(group);
    List<GroupData> after = app.group().list();

    Assert.assertTrue(after.size() == before.size() + 1, "Количество групп не увеличилось");
   /* int max =0;
    for(GroupData gr: after){
      if(gr.getId()>max) max=gr.getId();

    }*/
    Comparator<? super GroupData> byId = (o1, o2) -> Integer.compare(o1.getId(), o2.getId()); //лямбда выражение
    /*Comparator<? super GroupData> byId = new Comparator<GroupData>() {
      @Override
      public int compare(GroupData o1, GroupData o2) {
        return Integer.compare(o1.getId(),o2.getId());
      }
    };*/
    group.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
    before.add(group);
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(after, before);
    Assert.assertEquals(new HashSet(before), new HashSet(after));
  }

}
