package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

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
    int before=app.getGroupHelper().getGroupCount();
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().initGroupModification();
    app.getGroupHelper().fillGroupForm(new GroupData("groupupdate1","groupupdate2","groupupdate3"));
    app.getGroupHelper().submitGroupModification();
    app.getGroupHelper().returnToGroupPage();
    int after=app.getGroupHelper().getGroupCount();
    Assert.assertTrue(before == after, "После редактирования изменилось кол-во групп");


  }
}
