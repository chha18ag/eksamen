package main;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import controller.CustomerController;
import util.NetworkUtil;
import view.MainView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {

    public static void main(String[] args) {
        NetworkUtil util = new NetworkUtil();
        CustomerController controller = new CustomerController(util);
        MainView view = new MainView(controller);
        controller.setView(view);
        view.MainMenu().get();
    }
}
