package vancanh1.model;

import vancanh1.entity.NguoiDungEntity;

import java.util.Map;

public class UserJsonRespone {
    private NguoiDungEntity employee;
    private boolean validated;
    private Map<String, String> errorMessages;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public NguoiDungEntity getEmployee() {
        return employee;
    }

    public void setEmployee(NguoiDungEntity employee) {
        this.employee = employee;
    }

    public Map<String, String> getErrorMessages() {
        return errorMessages;
    }

    public void setErrorMessages(Map<String, String> errorMessages) {
        this.errorMessages = errorMessages;
    }

    public boolean isValidated() {
        return validated;
    }

    public void setValidated(boolean validated) {
        this.validated = validated;
    }
}
