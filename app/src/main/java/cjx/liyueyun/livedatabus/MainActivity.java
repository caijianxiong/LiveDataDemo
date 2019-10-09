package cjx.liyueyun.livedatabus;

import android.app.Activity;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button bt;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt = (Button) findViewById(R.id.bt);
        tv = (TextView) findViewById(R.id.tv);


        LiveDataBus.get().with(BusKey.StringKey.refreshMainActivity.toString(), Been.class).observe(this, new Observer<Been>() {
            @Override
            public void onChanged(@Nullable Been been) {
                assert been != null;
                tv.setText(been.getStr());
            }
        });

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Been been = new Been();
                been.setStr(bt.getText().toString());
                LiveDataBus.get().with(BusKey.StringKey.refreshMainActivity.toString(), Been.class).postValue(been);
            }
        });
    }
}
