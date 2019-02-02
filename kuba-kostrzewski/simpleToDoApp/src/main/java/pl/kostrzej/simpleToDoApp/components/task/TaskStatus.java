package pl.kostrzej.simpleToDoApp.components.task;

public enum TaskStatus {

    DONE("Wykonane"),
    UNDONE("Niewykonane");

    private String status;

    TaskStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Status='" + status + '\'';
    }
}
