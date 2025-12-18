package org.example;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ToJsonConverter {

    private File file;

    public  ToJsonConverter(File filePath){
        this.file = filePath;
    }
    public  ToJsonConverter(String filePath){
        this.file = new File(filePath);
    }

    private List<String> readUser()
    {
        List<String> buffer =null;

        try( FileReader reader = new FileReader(file)){
            buffer = reader.readAllLines();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }

        return buffer;
    }

    private ArrayList<User> setUsers(){

        List<String> usersList = readUser();
        ArrayList<User> users = new ArrayList<>();

        for (int i = 1; i < usersList.size(); i++) {

            String userData = usersList.get(i);
            int nameLength = 0;

            while (userData.charAt(nameLength) != ' '){
                nameLength++;
            }

            String username = userData.substring(0,nameLength);
            String age = userData.substring(nameLength + 1);

            users.add(new User(username, Integer.parseInt(age)));

        }

        return users;
    }

    public void parceToJSON(){

        ArrayList<User> users = setUsers();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

            String json = gson.toJson(users);

            try (FileWriter writer = new FileWriter("src/main/resources/user.json")){
                writer.write(json);

            }catch (IOException ex){
                System.out.println(ex.getMessage());
            }



    }
}
