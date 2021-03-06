package com.ys.memoryleak;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ys.memoryleak.activity.AnonymousClassLeakActivity;
import com.ys.memoryleak.activity.HandlerLeakActivity;
import com.ys.memoryleak.activity.SingletonLeakActivity;
import com.ys.memoryleak.fragment.PresenterLeakActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_handler:
                goToActivity(HandlerLeakActivity.class);
                break;
            case R.id.btn_singleton:
                goToActivity(SingletonLeakActivity.class);
                break;
            case R.id.btn_anonymous:
                goToActivity(AnonymousClassLeakActivity.class);
                break;
            case R.id.btn_presenter:
                goToActivity(PresenterLeakActivity.class);
                break;
            default:
                break;
        }
    }

    private void goToActivity(Class cls){
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }

}
