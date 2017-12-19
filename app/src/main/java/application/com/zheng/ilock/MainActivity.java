package application.com.zheng.ilock;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import android.graphics.Rect;
import android.widget.ImageView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Toast;
import android.view.GestureDetector.OnGestureListener;

import android.os.Vibrator;

public class MainActivity extends AppCompatActivity implements OnGestureListener {

    GestureDetector detector;

    int Height;
    static int passLength = 6;
    static int[] sequence = new int[passLength];
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        detector = new GestureDetector(this, this);



        setContentView(R.layout.activity_main);

        SharedPreferences mSettings = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = mSettings.edit();

        if(!mSettings.contains("SavedCode")) {
            int id = 1;
            String savedcode = "121212";
            editor.putString("SavedCode", savedcode);
            editor.putInt("id", id);
            editor.apply();
        }

//        final Button button1 = (Button) findViewById(R.id.button1);
//        button1.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                sequence[count] = 1;
//                if (count+1 == passLength){
//                    done();
//                    count =0;
//                }else {
//                    count++;
//                }
//            }
//        });
//
//        final Button button2 = (Button) findViewById(R.id.button2);
//        button2.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                sequence[count] = 2;
//                if (count+1 == passLength){
//                    done();
//                    count =0;
//                }else {
//                    count++;
//                }
//            }
//        });

//        final ImageView image = (ImageView) findViewById(R.id.lockscreen);
//        image.setOnTouchListener(new View.OnTouchListener() {
//            private Rect rect;
//
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if(event.getAction() == MotionEvent.ACTION_DOWN){
//                    image.setColorFilter(Color.argb(50, 0, 0, 0));
//                    rect = new Rect(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());
//                }
//                if(event.getAction() == MotionEvent.ACTION_UP){
//                    image.setColorFilter(Color.argb(0, 0, 0, 0));
//                }
//                if(event.getAction() == MotionEvent.ACTION_MOVE){
//                    if(!rect.contains(v.getLeft() + (int) event.getX(), v.getTop() + (int) event.getY())){
//                        image.setColorFilter(Color.argb(0, 0, 0, 0));
//                    }
//                }
//                return false;
//            }
//        });
    }


    public void dummyClick(View view) {
        // Needed to enable Ripple effect on Views.
        // Without Click event, the Ripple effect is not visible on Views.
        // Remove onClick attribute on Views and see the difference. Try it yourself.
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        ImageView img = (ImageView) findViewById(R.id.lockscreen);
        Height = img.getHeight();
        System.out.println("onTouchEvent "+event);
        if(event.getAction()==MotionEvent.ACTION_DOWN){
            img.setColorFilter(Color.argb(50, 0, 0, 0));
        }else if(event.getAction()==MotionEvent.ACTION_UP){
            img.setColorFilter(Color.argb(0, 0, 0, 0));
        }
        return detector.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
//        Toast.makeText(getApplicationContext(), "OnDown Gesture", Toast.LENGTH_SHORT).show();
        System.out.println("onDown "+e);
        return false;
    }

//    @Override
//    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
//        Toast.makeText(getApplicationContext(), "Fling Gesture", Toast.LENGTH_SHORT).show();
//        System.out.println("onFling "+e1 +"; "+e2);
//        return true;
//    }

    @Override
    public void onLongPress(MotionEvent e) {
        System.out.println("onLongPress "+e);
//        Toast.makeText(getApplicationContext(), "Long Press Gesture", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
//        Toast.makeText(getApplicationContext(), "Scroll Gesture", Toast.LENGTH_SHORT).show();
        System.out.println("onScroll "+e1 +"; "+e2);
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        System.out.println("onShowPress "+e);
//        Toast.makeText(getApplicationContext(), "Show Press Gesture", Toast.LENGTH_SHORT).show();
    }



    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        System.out.println("onSingleTapUp "+e);
        if(e.getY()<Height*51/100){
            System.out.println("BUTTON 1");
            sequence[count] = 1;
            if (count+1 == passLength){
                done();
                count =0;
            }else {
                count++;
            }
        }else{
            System.out.println("BUTTON 2");
            sequence[count] = 2;
            if (count+1 == passLength){
                done();
                count =0;
            }else {
                count++;
            }
        }
        // Get instance of Vibrator from current Context
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

// Vibrate for 400 milliseconds
        v.vibrate(100);

//        Toast.makeText(getApplicationContext(), "Single Tap Gesture", Toast.LENGTH_SHORT).show();
        return true;
    }
    @Override
    public boolean onFling(MotionEvent motionEvent1, MotionEvent motionEvent2, float X, float Y) {

        // Get instance of Vibrator from current Context
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

// Vibrate for 400 milliseconds
        v.vibrate(100);

        if (motionEvent1.getY() - motionEvent2.getY() > 50) {

            System.out.println("Swipe up!");
            sequence[count] = 4;
            if (count + 1 == passLength) {
                done();
                count = 0;
            } else {
                count++;
            }

            return true;
        }

        if (motionEvent2.getY() - motionEvent1.getY() > 50) {

            System.out.println("Swipe down!");
            sequence[count] = 3;
            if (count + 1 == passLength) {
                done();
                count = 0;
            } else {
                count++;
            }
            return true;
        }

//        if(motionEvent1.getX() - motionEvent2.getX() > 50){
//
//            Toast.makeText(MainActivity.this , " Swipe Left " , Toast.LENGTH_LONG).show();
//
//            return true;
//        }
//
//        if(motionEvent2.getX() - motionEvent1.getX() > 50) {
//
//            Toast.makeText(MainActivity.this, " Swipe Right ", Toast.LENGTH_LONG).show();
//
//            return true;
//        }
        else {
            System.out.println("Swipe MORE");

            return true ;
        }

    }


    public void showSimplePopUp(String string) {

        AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
        helpBuilder.setTitle("");
        helpBuilder.setMessage(string);
        helpBuilder.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing but close the dialog
                    }
                });

        // Remember, create doesn't show the dialog
        AlertDialog helpDialog = helpBuilder.create();
        helpDialog.show();
    }

    public void done(){
        String passcode = "";
        for (int i = 0; i<passLength; i++){
            passcode = passcode.concat(String.valueOf(sequence[i]));
        }
        SharedPreferences check = PreferenceManager.getDefaultSharedPreferences(this);
        String realcode = check.getString("SavedCode", "missing");

        System.out.println(passcode);
        System.out.println(realcode);


        if (passcode.equals(realcode)){
//            showSimplePopUp("PASS");
            goToSecondActivity();
        }else{
            showSimplePopUp("Wrong Passcode, Please Try Again");
            Toast.makeText(MainActivity.this, realcode, Toast.LENGTH_LONG).show();
        }
    }


    public void goToSecondActivity() {

        Intent intent = new Intent(this, SecondActivity.class);

        startActivity(intent);

    }


}
