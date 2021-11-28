package com.example.manageark.firebaseAuthUtil;

import android.os.AsyncTask;

import androidx.annotation.NonNull;

import com.example.manageark.ENUMS.Status;
import com.example.manageark.Model.UserModel;
import com.example.manageark.Model.firebaseMessage;
import com.example.manageark.firestoreUtil.userUtilsStore;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class userUtilsAuth {
    FirebaseAuth auth = FirebaseAuth.getInstance();

    // -------------
    // Signup user
    public firebaseMessage SignUp(String password, UserModel userModel){
        firebaseMessage[] firebaseMessage = {
                new firebaseMessage(Status.Unsuccessful, "Something went wrong.")
        };

        auth.createUserWithEmailAndPassword(userModel.getEmail(), password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            FirebaseUser user = auth.getCurrentUser();

                            assert user != null;
                            String uid = user.getUid();
                            userModel.setUID(uid);
                            userModel.setPhotoUrl("profilepic/"+uid+".png");

                            userUtilsStore userUtilsStore = new userUtilsStore();
                            firebaseMessage message = userUtilsStore.postSignup(userModel);

                            firebaseMessage[0] = message;
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        firebaseMessage[0] =  new firebaseMessage(
                                Status.Unsuccessful, "Something went wrong.", "Error while creating user."
                        );
                    }
                });
        return firebaseMessage[0];
    }
    // ---------------------------

    // ---------------------------
    // Login user
    public firebaseMessage Login(String email, String password) {
        firebaseMessage[] message = {
                new firebaseMessage(com.example.manageark.ENUMS.Status.Unsuccessful, "Something went wrong")
        };

        System.out.println(email + "\t "  +password);
        auth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        message[0] = new firebaseMessage(Status.Successful, "Logged in.", "Logged in successfully");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        message[0] = new firebaseMessage(Status.Unsuccessful, "Something went wrong.", "Error while login.");
                    }
                });
        return message[0];
    }
    // ---------------------------
}
