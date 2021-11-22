package com.example.manageark.firestoreUtil;

import androidx.annotation.NonNull;

import com.example.manageark.ENUMS.Status;
import com.example.manageark.Model.UserModel;
import com.example.manageark.Model.firebaseMessage;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

public class userUtilsStore {
    private final FirebaseFirestore firestore = FirebaseFirestore.getInstance();

    public firebaseMessage postSignup(UserModel userModel){
        final firebaseMessage[] message = {
                new firebaseMessage(Status.Successful, "User Created Successfully")
        };

        firestore
                .collection("users")
                .document(userModel.getUID())
                .set(userModel)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        message[0] = new firebaseMessage(
                                Status.Successful, "User Created Successfully"
                        );
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        message[0] = new firebaseMessage(
                                Status.Unsuccessful, "Something went wrong.", "Error while creating document"
                        );
                    }
                });

        return  message[0];
    }
}
