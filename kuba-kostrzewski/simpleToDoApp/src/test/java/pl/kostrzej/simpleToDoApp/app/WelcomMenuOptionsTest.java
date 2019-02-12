package pl.kostrzej.simpleToDoApp.app;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WelcomMenuOptionsTest {
    @Test
    public void chooseCorrectOption(){
        assertEquals(WelcomeMenuOptions.LOGIN,WelcomeMenuOptions.returnIfCorrect(1));
        assertEquals(WelcomeMenuOptions.REGISTER,WelcomeMenuOptions.returnIfCorrect(2));
        assertEquals(WelcomeMenuOptions.EXIT,WelcomeMenuOptions.returnIfCorrect(3));
    }

    @Test(expected = InvalidOptionException.class)
    public void choseTooHighOption(){
        WelcomeMenuOptions.returnIfCorrect(4);
    }

    @Test(expected = InvalidOptionException.class)
    public void chooseTooLowOption(){ WelcomeMenuOptions.returnIfCorrect(0); }

    @Test(expected = InvalidOptionException.class)
    public void chooseNegativeOption(){ WelcomeMenuOptions.returnIfCorrect(-1); }
}
