package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupDeletionTests extends TestBase {

    @Test
    public void testGroupDeletion() {
        app.getNavigationHelper().goToGroupPage();
        if(!app.getGroupHelper().isGroupExists()){
            app.getGroupHelper().createGroup(new GroupData("test2", null, "test3"));
        }
        int before=app.getGroupHelper().getGroupCount();
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().deleteSelectedGroups();
        app.getGroupHelper().returnToGroupPage();
        int after =  app.getGroupHelper().getGroupCount();
        Assert.assertTrue(after==before-1,"Количество групп не изменилось");
    }




}
