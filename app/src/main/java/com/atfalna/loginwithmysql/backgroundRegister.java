package com.atfalna.loginwithmysql;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

public class backgroundRegister extends AsyncTask <String,Void,String>
{
    @SuppressLint("StaticFieldLeak")

    private Context context;
    private AlertDialog alertDialog;

    backgroundRegister (Context ctx)
    {
        context = ctx;
    }

    @Override
    protected String doInBackground(String... params)
    {
        String type = params[0];
        String register_url = "http://apdoa0001-001-site1.dtempurl.com/register.php";

        if(type.equals("register"))
        {
            try
            {
                String name = params[1];
                String email = params[2];
                String password = params[3];
                String gender = params[4];
                String phone = params[5];
                URL url = new URL(register_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream , "UTF-8"));

                String post_data = URLEncoder.encode("Name","UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+"&"+
                        URLEncoder.encode("Email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")+"&"+
                        URLEncoder.encode("Password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8")+"&"+
                        URLEncoder.encode("Gender","UTF-8")+"="+URLEncoder.encode(gender,"UTF-8")+"&"+
                        URLEncoder.encode("Phone","UTF-8")+"="+URLEncoder.encode(phone,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while ((line = bufferedReader.readLine())!=null)
                {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            }
            catch (UnsupportedEncodingException e)
            {
                e.printStackTrace();
            }
            catch (ProtocolException e)
            {
                e.printStackTrace();
            }
            catch (MalformedURLException e)
            {
                e.printStackTrace();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPreExecute()
    {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Login Status");
    }

    @Override
    protected void onPostExecute(String result)
    {
        alertDialog.setMessage(result);
        alertDialog.show();
    }

    @Override
    protected void onProgressUpdate(Void... values)
    {
        super.onProgressUpdate(values);
    }

}