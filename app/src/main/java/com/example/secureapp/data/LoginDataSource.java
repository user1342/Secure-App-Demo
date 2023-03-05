package com.example.secureapp.data;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.secureapp.data.model.LoggedInUser;

import java.io.IOException;
import java.util.Objects;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public Result<LoggedInUser> login(String username, String password) {

        // Log in as test user, password is 'testtest'
        if (Objects.equals(username, "test") && Objects.equals(password, "testtest")){
            Log.v("","");

            LoggedInUser loggedInUser =
                    new LoggedInUser(
                            java.util.UUID.randomUUID().toString(),
                            username);
            return new Result.Success<>(loggedInUser);

        // Log in as admin user, password is a random UUID. Requires dynamic instrumentation / patching.
        }else if (Objects.equals(username, "admin") && Objects.equals(password, java.util.UUID.randomUUID().toString())){

            LoggedInUser loggedInUser =
                    new LoggedInUser(
                            java.util.UUID.randomUUID().toString(),
                            username);
            return new Result.Success<>(loggedInUser);

        }

        return new Result.Error(new IOException("Incorrect Credentials"));
        /*
        try {
            // TODO: handle loggedInUser authentication
            LoggedInUser fakeUser =
                    new LoggedInUser(
                            java.util.UUID.randomUUID().toString(),
                            "Jane Doe");
            return new Result.Success<>(fakeUser);
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
        */

    }

    public void logout() {
        // TODO: revoke authentication
    }
}