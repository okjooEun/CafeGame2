package com.example.cafesimulatorgame;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Mainactivity extends AppCompatActivity {

    ImageView imgCalender, imgTime, guest;
    Button btnSetting,btnselect1, btnselect2,btnselect3;
   private FirebaseAuth mAuth;
   private static final String TAG = "AnonymousAuth";

    public void onCreate(Bundle savedInstanceState) {

        mAuth = FirebaseAuth.getInstance();

        imgCalender = (ImageView) findViewById(R.id.imgCalendar);
        imgTime = (ImageView) findViewById(R.id.imgTime);
        guest = (ImageView) findViewById(R.id.guest);


        btnSetting = (Button) findViewById(R.id.btnSetting);
        btnselect1 = (Button) findViewById(R.id.btnselect1);
        btnselect2 = (Button) findViewById(R.id.btnselect2);
        btnselect3 = (Button) findViewById(R.id.btnselect3);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //화면 가로 고정
       setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        //소프트키(네비게이션바) 없애기 시작
       View decorView = getWindow().getDecorView();

       int uiOption = getWindow().getDecorView().getSystemUiVisibility();

        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH )
            uiOption |= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN )
            uiOption |= View.SYSTEM_UI_FLAG_FULLSCREEN;
        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT )
            uiOption |= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;

        decorView.setSystemUiVisibility( uiOption );
        //소프트키(네비게이션바) 없애기 끝

  /*      btnselect1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(Mainactivity.this,"선택되었습니다.", Toast.LENGTH_SHORT);
                toast.show();
              // btnselect1.setVisibility(android.view.View.INVISIBLE);

            }
    });*/


    }
    public void onStart(){
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }
    private void signInAnonymously() {

        // [START signin_anonymously]
        mAuth.signInAnonymously()
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInAnonymously:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInAnonymously:failure", task.getException());
                            Toast.makeText(Mainactivity.this, "failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                    }
                });
        // [END signin_anonymously]
    }

    private void signOut() {
        mAuth.signOut();
        updateUI(null);
    }


    private void updateUI(FirebaseUser user) {

        boolean isSignedIn = (user != null);
    }


}