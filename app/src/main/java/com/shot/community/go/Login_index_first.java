package com.shot.community.go;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by god on 2017/12/3.
 */

public class Login_index_first extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_index_first);
        textView = (TextView) findViewById(R.id.login_index_first_text);
        thread thread = new thread();
        thread.start();

    }
    class asyncTask extends AsyncTask {
        TextView textView;
        String s;
        public asyncTask(TextView textView ,String s) {
            this.textView = textView;
            this.s = s;
        }

        /**
         * Override this method to perform a computation on a background thread. The
         * specified parameters are the parameters passed to {@link #execute}
         * by the caller of this task.
         * <p>
         * This method can call {@link #publishProgress} to publish updates
         * on the UI thread.
         *
         * @param objects The parameters of the task.
         * @return A result, defined by the subclass of this task.
         * @see #onPreExecute()

         * @see #onPostExecute
         * @see #publishProgress
         */
        @Override
        protected Object doInBackground(Object[] objects) {
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
//            this.textView .setText(s);
            this.textView.append(s);
        }
    }

    class thread extends Thread{
        /**
         * If this thread was constructed using a separate
         * <code>Runnable</code> run object, then that
         * <code>Runnable</code> object's <code>run</code> method is called;
         * otherwise, this method does nothing and returns.
         * <p>
         * Subclasses of <code>Thread</code> should override this method.
         *
         * @see #start()
         * @see #stop()
         * @see #Thread(ThreadGroup, Runnable, String)
         */
        @Override
        public void run() {
            super.run();
            try {
                int i = 0;
                StringBuilder sb = new StringBuilder();
                while (i<6)
                {
                    sleep(300);
                    i++;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                Intent intent = new Intent(Login_index_first.this , login_index.class);
                startActivity(intent);
                finish();
            }
        }
    }
}
