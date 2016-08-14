package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

/**
 * Created by eshkuratova on 31.07.2016.
 */
public class GroupModificationTest extends TestBase{

  @Test

  public void testGroupModification(){
    app.getNavigationHelper().goToGroupPage();
    if(!app.getGroupHelper().isGroupExists()){
      app.getGroupHelper().createGroup(new GroupData("test2", null, "test3"));
    }
    List<GroupData> before; //= new ArrayList<GroupData>();
    //int before=app.getGroupHelper().getGroupCount();
    before = app.getGroupHelper().getGroupList();
    app.getGroupHelper().selectGroup(before.size()-1);
    app.getGroupHelper().initGroupModification();
    GroupData group = new GroupData(before.get(before.size()-1).getId(),"groupupdate1","groupupdate2","groupupdate3");
    app.getGroupHelper().fillGroupForm(group);
    app.getGroupHelper().submitGroupModification();
    app.getGroupHelper().returnToGroupPage();
    List<GroupData> after;
    //int after=app.getGroupHelper().getGroupCount();
    after=app.getGroupHelper().getGroupList();
    before.remove((before.size()-1));
    before.add((group));
    Assert.assertTrue(before.size() == after.size(), "После редактирования изменилось кол-во групп");
    Assert.assertEquals(new HashSet<Object>(before),new HashSet<Object>(after));

  }
}
