package com.example.personalhandbook;

import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class DataAccessLayer {

public String saveUserData(String[] params){
    StringBuilder stringBuilder = new StringBuilder();
    try {
        //Step 1
        URL url = new URL(AppSettings.URL_ADDRESS + params[0]);
        URLConnection connection = url.openConnection();

        //Step 2
        String aUser= URLEncoder.encode("name","UTF-8") + "=" + URLEncoder.encode(params[1],"UTF-8");
        aUser += "&" + URLEncoder.encode("email","UTF-8") + "=" + URLEncoder.encode(params[2],"UTF-8");
        aUser += "&" + URLEncoder.encode("phone","UTF-8") + "=" + URLEncoder.encode(params[3],"UTF-8");
        aUser += "&" + URLEncoder.encode("password","UTF-8") + "=" + URLEncoder.encode(params[4],"UTF-8");

        connection.setDoOutput(true);
        OutputStreamWriter writer=new OutputStreamWriter(connection.getOutputStream());
        writer.write(aUser);
        writer.flush();

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        String line = "";
        while((line = reader.readLine()) != null)
        {
            stringBuilder.append(line);
        }

    }
    catch(Exception e)
    {
    }
    return stringBuilder.toString();
}

public String loadUserData(String[] params){

    StringBuilder stringBuilder = new StringBuilder();
    try {
        URL url = new URL(AppSettings.URL_ADDRESS + params[0]);
        URLConnection connection = url.openConnection();
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        String line = "";
        while((line = reader.readLine()) != null)
        {
            stringBuilder.append(line);
        }

    }
    catch(Exception e)
    {}

    return stringBuilder.toString();
}


    public String loadEmailPasswordData(String[] params){

        StringBuilder stringBuilder = new StringBuilder();
        try {
            URL url = new URL(AppSettings.URL_ADDRESS + params[0]);
            URLConnection connection = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String line = "";
            while((line = reader.readLine()) != null)
            {
                stringBuilder.append(line);
            }

        }
        catch(Exception e)
        {}

        return stringBuilder.toString();
    }


    public String saveEmailPasswordData(String[] params){
        StringBuilder stringBuilder = new StringBuilder();
        try {
            //Step 1
            URL url = new URL(AppSettings.URL_ADDRESS + params[0]);
            URLConnection connection = url.openConnection();

            //Step 2
            String aUser= URLEncoder.encode("email","UTF-8") + "=" + URLEncoder.encode(params[1],"UTF-8");
            aUser += "&" + URLEncoder.encode("domain_name","UTF-8") + "=" + URLEncoder.encode(params[2],"UTF-8");
            aUser += "&" + URLEncoder.encode("save_email","UTF-8") + "=" + URLEncoder.encode(params[3],"UTF-8");
            aUser += "&" + URLEncoder.encode("save_password","UTF-8") + "=" + URLEncoder.encode(params[4],"UTF-8");

            connection.setDoOutput(true);
            OutputStreamWriter writer=new OutputStreamWriter(connection.getOutputStream());
            writer.write(aUser);
            writer.flush();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String line = "";
            while((line = reader.readLine()) != null)
            {
                stringBuilder.append(line);
            }

        }
        catch(Exception e)
        {
        }
        return stringBuilder.toString();
    }

    public String editEmailPasswordData(String[] params){
        StringBuilder stringBuilder = new StringBuilder();
        try {
            //Step 1
            URL url = new URL(AppSettings.URL_ADDRESS + params[0]);
            URLConnection connection = url.openConnection();

            //Step 2
            String aUser= URLEncoder.encode("email","UTF-8") + "=" + URLEncoder.encode(params[1],"UTF-8");
            aUser += "&" + URLEncoder.encode("domain_name","UTF-8") + "=" + URLEncoder.encode(params[2],"UTF-8");
            aUser += "&" + URLEncoder.encode("save_email","UTF-8") + "=" + URLEncoder.encode(params[3],"UTF-8");
            aUser += "&" + URLEncoder.encode("save_password","UTF-8") + "=" + URLEncoder.encode(params[4],"UTF-8");

            connection.setDoOutput(true);
            OutputStreamWriter writer=new OutputStreamWriter(connection.getOutputStream());
            writer.write(aUser);
            writer.flush();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String line = "";
            while((line = reader.readLine()) != null)
            {
                stringBuilder.append(line);
            }

        }
        catch(Exception e)
        {
        }
        return stringBuilder.toString();
    }

    public String deleteEmailPasswordData(String[] params){
        StringBuilder stringBuilder = new StringBuilder();
        try {
            //Step 1
            URL url = new URL(AppSettings.URL_ADDRESS + params[0]);
            URLConnection connection = url.openConnection();

            //Step 2
            String aUser= URLEncoder.encode("email","UTF-8") + "=" + URLEncoder.encode(params[1],"UTF-8");
            aUser += "&" + URLEncoder.encode("domain_name","UTF-8") + "=" + URLEncoder.encode(params[2],"UTF-8");

            connection.setDoOutput(true);
            OutputStreamWriter writer=new OutputStreamWriter(connection.getOutputStream());
            writer.write(aUser);
            writer.flush();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String line = "";
            while((line = reader.readLine()) != null)
            {
                stringBuilder.append(line);
            }

        }
        catch(Exception e)
        {
        }
        return stringBuilder.toString();
    }

       public String loadVisitingCardData(String[] params){

        StringBuilder stringBuilder = new StringBuilder();
        try {
            URL url = new URL(AppSettings.URL_ADDRESS + params[0]);
            URLConnection connection = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String line = "";
            while((line = reader.readLine()) != null)
            {
                stringBuilder.append(line);
            }

        }
        catch(Exception e)
        {}

        return stringBuilder.toString();
    }


    public String saveVisitingCardData(String[] params){
        StringBuilder stringBuilder = new StringBuilder();
        try {
            //Step 1
            URL url = new URL(AppSettings.URL_ADDRESS + params[0]);
            URLConnection connection = url.openConnection();

            //Step 2
            String aUser= URLEncoder.encode("email","UTF-8") + "=" + URLEncoder.encode(params[1],"UTF-8");
            aUser += "&" + URLEncoder.encode("vcard_name","UTF-8") + "=" + URLEncoder.encode(params[2],"UTF-8");
            aUser += "&" + URLEncoder.encode("address","UTF-8") + "=" + URLEncoder.encode(params[3],"UTF-8");
            aUser += "&" + URLEncoder.encode("vcard_picture","UTF-8") + "=" + URLEncoder.encode(params[4],"UTF-8");
            aUser += "&" + URLEncoder.encode("organization","UTF-8") + "=" + URLEncoder.encode(params[5],"UTF-8");

            connection.setDoOutput(true);
            OutputStreamWriter writer=new OutputStreamWriter(connection.getOutputStream());
            writer.write(aUser);
            writer.flush();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String line = "";
            while((line = reader.readLine()) != null)
            {
                stringBuilder.append(line);
            }

        }
        catch(Exception e)
        {
        }
        return stringBuilder.toString();
    }
    public String editVisitingCardData(String[] params){
        StringBuilder stringBuilder = new StringBuilder();
        try {
            //Step 1
            URL url = new URL(AppSettings.URL_ADDRESS + params[0]);
            URLConnection connection = url.openConnection();

            //Step 2
            String aUser= URLEncoder.encode("email","UTF-8") + "=" + URLEncoder.encode(params[1],"UTF-8");
            aUser += "&" + URLEncoder.encode("vcard_name","UTF-8") + "=" + URLEncoder.encode(params[2],"UTF-8");
            aUser += "&" + URLEncoder.encode("address","UTF-8") + "=" + URLEncoder.encode(params[3],"UTF-8");
            aUser += "&" + URLEncoder.encode("vcard_picture","UTF-8") + "=" + URLEncoder.encode(params[4],"UTF-8");
            aUser += "&" + URLEncoder.encode("organization","UTF-8") + "=" + URLEncoder.encode(params[5],"UTF-8");

            connection.setDoOutput(true);
            OutputStreamWriter writer=new OutputStreamWriter(connection.getOutputStream());
            writer.write(aUser);
            writer.flush();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String line = "";
            while((line = reader.readLine()) != null)
            {
                stringBuilder.append(line);
            }

        }
        catch(Exception e)
        {
        }
        return stringBuilder.toString();
    }

    public String deleteVisitingCardData(String[] params){
        StringBuilder stringBuilder = new StringBuilder();
        try {
            //Step 1
            URL url = new URL(AppSettings.URL_ADDRESS + params[0]);
            URLConnection connection = url.openConnection();

            //Step 2
            String aUser= URLEncoder.encode("email","UTF-8") + "=" + URLEncoder.encode(params[1],"UTF-8");
            aUser += "&" + URLEncoder.encode("vcard_name","UTF-8") + "=" + URLEncoder.encode(params[2],"UTF-8");

            connection.setDoOutput(true);
            OutputStreamWriter writer=new OutputStreamWriter(connection.getOutputStream());
            writer.write(aUser);
            writer.flush();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String line = "";
            while((line = reader.readLine()) != null)
            {
                stringBuilder.append(line);
            }

        }
        catch(Exception e)
        {
        }
        return stringBuilder.toString();
    }

    public String loadCreditDebitData(String[] params){

        StringBuilder stringBuilder = new StringBuilder();
        try {
            URL url = new URL(AppSettings.URL_ADDRESS + params[0]);
            URLConnection connection = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String line = "";
            while((line = reader.readLine()) != null)
            {
                stringBuilder.append(line);
            }

        }
        catch(Exception e)
        {}

        return stringBuilder.toString();
    }


    public String saveCreditDebitData(String[] params){
        StringBuilder stringBuilder = new StringBuilder();
        try {
            //Step 1
            URL url = new URL(AppSettings.URL_ADDRESS + params[0]);
            URLConnection connection = url.openConnection();

            //Step 2
            String aUser= URLEncoder.encode("email","UTF-8") + "=" + URLEncoder.encode(params[1],"UTF-8");
            aUser += "&" + URLEncoder.encode("bank","UTF-8") + "=" + URLEncoder.encode(params[2],"UTF-8");
            aUser += "&" + URLEncoder.encode("card_name","UTF-8") + "=" + URLEncoder.encode(params[3],"UTF-8");
            aUser += "&" + URLEncoder.encode("card_number","UTF-8") + "=" + URLEncoder.encode(params[4],"UTF-8");
            aUser += "&" + URLEncoder.encode("pin","UTF-8") + "=" + URLEncoder.encode(params[5],"UTF-8");
            aUser += "&" + URLEncoder.encode("card_picture","UTF-8") + "=" + URLEncoder.encode(params[6],"UTF-8");

            connection.setDoOutput(true);
            OutputStreamWriter writer=new OutputStreamWriter(connection.getOutputStream());
            writer.write(aUser);
            writer.flush();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String line = "";
            while((line = reader.readLine()) != null)
            {
                stringBuilder.append(line);
            }

        }
        catch(Exception e)
        {
        }
        return stringBuilder.toString();
    }

    public String deleteCreditDebitData(String[] params){
        StringBuilder stringBuilder = new StringBuilder();
        try {
            //Step 1
            URL url = new URL(AppSettings.URL_ADDRESS + params[0]);
            URLConnection connection = url.openConnection();

            //Step 2
            String aUser= URLEncoder.encode("email","UTF-8") + "=" + URLEncoder.encode(params[1],"UTF-8");
            aUser += "&" + URLEncoder.encode("card_number","UTF-8") + "=" + URLEncoder.encode(params[2],"UTF-8");

            connection.setDoOutput(true);
            OutputStreamWriter writer=new OutputStreamWriter(connection.getOutputStream());
            writer.write(aUser);
            writer.flush();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String line = "";
            while((line = reader.readLine()) != null)
            {
                stringBuilder.append(line);
            }

        }
        catch(Exception e)
        {
        }
        return stringBuilder.toString();
    }

    public String editCreditDebitData(String[] params){
        StringBuilder stringBuilder = new StringBuilder();
        try {
            //Step 1
            URL url = new URL(AppSettings.URL_ADDRESS + params[0]);
            URLConnection connection = url.openConnection();

            //Step 2
            String aUser= URLEncoder.encode("email","UTF-8") + "=" + URLEncoder.encode(params[1],"UTF-8");
            aUser += "&" + URLEncoder.encode("bank","UTF-8") + "=" + URLEncoder.encode(params[2],"UTF-8");
            aUser += "&" + URLEncoder.encode("card_name","UTF-8") + "=" + URLEncoder.encode(params[3],"UTF-8");
            aUser += "&" + URLEncoder.encode("card_number","UTF-8") + "=" + URLEncoder.encode(params[4],"UTF-8");
            aUser += "&" + URLEncoder.encode("pin","UTF-8") + "=" + URLEncoder.encode(params[5],"UTF-8");
            aUser += "&" + URLEncoder.encode("card_picture","UTF-8") + "=" + URLEncoder.encode(params[6],"UTF-8");

            connection.setDoOutput(true);
            OutputStreamWriter writer=new OutputStreamWriter(connection.getOutputStream());
            writer.write(aUser);
            writer.flush();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String line = "";
            while((line = reader.readLine()) != null)
            {
                stringBuilder.append(line);
            }

        }
        catch(Exception e)
        {
        }
        return stringBuilder.toString();
    }

}
