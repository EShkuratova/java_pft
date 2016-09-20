package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;

/**
 * Created by eshkuratova on 20.09.2016.
 */
public class RegistrationTests  extends TestBase{
    @Test
    public void testRegistration(){

        app.registration().start("user","email");
    }
}
