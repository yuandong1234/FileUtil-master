package com.yuandong.savedirectory;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.yuandong.savedirectory.util.FileUtils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button bt_root,bt_write ,bt_delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView(){
        bt_root=(Button)findViewById(R.id.bt_root);
        bt_delete=(Button)findViewById(R.id.bt_delete);
        bt_write=(Button)findViewById(R.id.bt_write);
        bt_delete.setOnClickListener(this);
        bt_root.setOnClickListener(this);
        bt_write.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_root:
                FileUtils.crateFile(FileUtils.APP_LOG,"log.txt");
                break;
            case R.id.bt_delete:
                FileUtils.deleteFile(FileUtils.APP_LOG,"log.txt");
                break;
            case R.id.bt_write:
                for(int i=0;i<100;i++){
                    FileUtils.writeFileFromString(FileUtils.APP_LOG,"log.txt",String.valueOf(i));
                }
                break;
        }
    }
}
