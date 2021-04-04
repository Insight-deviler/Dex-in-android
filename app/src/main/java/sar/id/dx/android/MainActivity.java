package sar.id.dx.android;
 
import android.app.Activity;
import android.app.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.content.*;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.webkit.*;
import android.animation.*;
import android.view.animation.*;
import java.util.*;
import java.util.regex.*;
import java.text.*;
import org.json.*;
import android.widget.Button;
import android.view.View;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.DialogFragment;
import android.Manifest;
import android.content.pm.PackageManager;
import java.io.File;
import java.nio.*;
import com.android.dx.command.Main;



public class MainActivity extends Activity {

    private String input = "";

    private Button button1;

    private ProgressDialog prog;

    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize(_savedInstanceState);
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
                || checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                requestPermissions(new String[] {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
            }
            else {
                initializeLogic();
            }
        }
        else {
            initializeLogic();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1000) {
            initializeLogic();
        }
    }

    private void initialize(Bundle _savedInstanceState) {
        button1 = (Button) findViewById(R.id.button1);

        button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View _view) {
                    new Ex().execute("strx");
                }
            });
    }

    private class Ex extends AsyncTask<String,Integer,String> {
        protected String doInBackground(String... str) {
            try {
                if (input.contains("jar")) {
                    int lastIndexOf = input.lastIndexOf(File.separator);
                    String substring = lastIndexOf > 0 ? input.substring(0, lastIndexOf) : "";
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(substring);
                    stringBuilder.append("/classes.dex");
                    File file = new File(stringBuilder.toString());
                    if (!file.exists()) {
                        file.createNewFile();
                    }


                    String[] arg = { "--dex" , new StringBuffer().append("--output=").append(file.getAbsolutePath()).toString(), input};

                    Main.main(arg);

                    if (FileUtil.getFileLength(file.getAbsolutePath()) > 1) {
                        prog.dismiss();
                    }
                }
                else {

                }
            } catch (Exception e) {

e.printStackTrace();


            }
            return "true" ;
        }
        protected void onPreExecute() {
            super.onPreExecute();
            prog = new ProgressDialog(MainActivity.this);
            prog.setTitle("Please Wait");
            prog.setMessage("Dx is running.....");
            prog.setIndeterminate(false);
            prog.setCancelable(false);
            prog.show();
        }
        protected void onPostExecute(Ex result) {
            prog.dismiss();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate();
            prog.setMessage("Dx is running..." + values[values.length - 1] +"%");

        }
    }



    private void initializeLogic() {

/*  you can also define your own jar file in the input string. Just replace "/AndroidBuilder/dalvik-dx-11.0.0_r3.jar" with your own path */




        input = FileUtil.getExternalStorageDir().concat("/AndroidBuilder/dalvik-dx-11.0.0_r3.jar");


	}
    }
