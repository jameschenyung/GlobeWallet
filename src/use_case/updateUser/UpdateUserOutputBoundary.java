package use_case.updateUser;

public interface UpdateUserOutputBoundary {
    void prepareFailView(String error);

    void prepareSuccessView(UpdateUserOutputData user);
}
