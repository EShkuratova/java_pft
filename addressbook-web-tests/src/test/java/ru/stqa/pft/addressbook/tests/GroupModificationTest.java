package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by eshkuratova on 31.07.2016.
 */
public class GroupModificationTest extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        if(app.db().groups().size() == 0){
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test2").withHeader("test3"));
        }

    }

    @Test

    public void testGroupModification() {

        Groups before = app.db().groups();
        GroupData modifiedGroup =  before.iterator().next();
        GroupData group = new GroupData().withId(modifiedGroup.getId()).withName("groupupdate1").withHeader("groupupdate2").withFooter("groupupdate3");
        app.goTo().groupPage();
        app.group().modify(group);
        assertThat(app.group().count(), equalTo(before.size()));
        Groups after = app.db().groups();
        assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));
        verifyGroupListInUI();

    }



}
