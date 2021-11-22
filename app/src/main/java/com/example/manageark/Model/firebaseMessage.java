package com.example.manageark.Model;

import com.example.manageark.ENUMS.Status;

public class firebaseMessage {
    String message;
    Status status;
    String otherInfo;

    public firebaseMessage(Status status, String message) {
        this.message = message;
        this.status = status;
    }

    public firebaseMessage(Status status, String message, String otherInfo) {
        this.message = message;
        this.status = status;
        this.otherInfo = otherInfo;
    }

    public int getStatusInt() {
        return status.ordinal();
    }

    public String getMessage() {
        return this.message;
    }

    public String getOtherInfo() {
        return this.otherInfo;
    }
}
