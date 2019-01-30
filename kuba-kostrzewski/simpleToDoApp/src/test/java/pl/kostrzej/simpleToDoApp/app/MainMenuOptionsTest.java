package pl.kostrzej.simpleToDoApp.app;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MainMenuOptionsTest {

    @Test
    public void chooseCorrectOption(){
        assertEquals(MainMenuOptions.SHOW_ALL_TASKS,MainMenuOptions.returnIfCorrect(1));
        assertEquals(MainMenuOptions.ADD_NEW_TASK,MainMenuOptions.returnIfCorrect(2));
        assertEquals(MainMenuOptions.EXIT,MainMenuOptions.returnIfCorrect(3));
    }

    @Test(expected = InvalidOptionException.class)
    public void choseTooHighOption(){
        MainMenuOptions.returnIfCorrect(4);
    }

    @Test(expected = InvalidOptionException.class)
    public void chooseTooLowOption(){
        MainMenuOptions.returnIfCorrect(0);
    }

    @Test(expected = InvalidOptionException.class)
    public void chooseNegativeOption(){
        MainMenuOptions.returnIfCorrect(-1);
    }
}
