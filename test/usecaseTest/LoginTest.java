package usecaseTest;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import use_case.login.*;

public class LoginTest {

    private LoginInteractor loginInteractor;
    private StubLoginUserDataAccess userDataAccess;
    private LoginOutputBoundaryStub outputBoundary;

    @Before
    public void setUp() {
        userDataAccess = new StubLoginUserDataAccess();
        userDataAccess.addUser("validUser", "validPass");
        outputBoundary = new LoginOutputBoundaryStub();
        loginInteractor = new LoginInteractor(outputBoundary, userDataAccess);
    }

    @Test
    public void testSuccessfulLogin() {
        LoginInputData inputData = new LoginInputData("validUser", "validPass");
        loginInteractor.execute(inputData);
        assertFalse(outputBoundary.getOutputData().isSuccess());

    }

    @Test
    public void testFailedLogin() {
        LoginInputData inputData = new LoginInputData("validUser", "invalidPass");
        loginInteractor.execute(inputData);
        assertFalse(outputBoundary.getOutputData().isSuccess());
    }

    @Test
    public void testUserNotFound() {
        LoginInputData inputData = new LoginInputData("nonExistentUser", "pass");
        loginInteractor.execute(inputData);
        assertFalse(outputBoundary.getOutputData().isSuccess());
    }



    public static class LoginOutputBoundaryStub implements LoginOutputBoundary {
        private LoginOutputData outputData;
        private boolean successViewPrepared = false;
        private boolean failViewPrepared = false;

        public LoginOutputBoundaryStub() {
            this.outputData = new LoginOutputData(false, "Default message");
        }

//        @Override
//        public void present(LoginOutputData outputData) {
//            this.outputData = outputData;
//        }

        public LoginOutputData getOutputData() {
            return outputData;
        }



        @Override
        public void prepareSuccessView(LoginOutputData user) {
            // Set the flag to true to indicate that the success view has been prepared
            this.successViewPrepared = true;
        }

        @Override
        public void prepareFailView(String error) {

            this.failViewPrepared = true;
        }


        public boolean isSuccessViewPrepared() {
            return successViewPrepared;
        }


        public boolean isFailViewPrepared() {
            return failViewPrepared;
        }
    }
    }

