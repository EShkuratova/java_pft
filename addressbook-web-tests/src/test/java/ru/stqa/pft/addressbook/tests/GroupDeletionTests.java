package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

public class GroupDeletionTests extends TestBase {

    @Test
    public void testGroupDeletion() {
        app.getNavigationHelper().goToGroupPage();
        if(!app.getGroupHelper().isGroupExists()){
            app.getGroupHelper().createGroup(new GroupData("test2", null, "test3"));
        }
        //int before=app.getGroupHelper().getGroupCount();
        List<GroupData> before = new ArrayList<GroupData>();
        before = app.getGroupHelper().getGroupList();
        app.getGroupHelper().selectGroup(before.size()-1);
        app.getGroupHelper().deleteSelectedGroups();
        app.getGroupHelper().returnToGroupPage();
        List<GroupData> after = new ArrayList<GroupData>();
        after = app.getGroupHelper().getGroupList();
        //int after =  app.getGroupHelper().getGroupCount();
        Assert.assertTrue(after.size()==before.size()-1,"Количество групп не изменилось");
        before.remove(before.size()-1);
       /* for(int i =0;i<after.size();i++){
            Assert.assertEquals(before.get(i),after.get(i));
           // Assert.assertTrue(after.equals(before),"Количество групп не изменилось");
        }*/
        Assert.assertEquals(after,before);
    }




}
