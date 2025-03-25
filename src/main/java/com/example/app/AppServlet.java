package com.example.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/words")
public class AppServlet extends HttpServlet {

    // Almacena las palabras en memoria (ArrayList)
    private List<String> wordsList;
    private static final String API_KEY = "8ff31c9eb88115786a67798b6a897a6c"; // Sustituye con tu API Key de OpenWeatherMap

    @Override
    public void init() throws ServletException {
        // Inicializa la lista en memoria
        wordsList = new ArrayList<>();
        System.out.println("Inicializando lista de palabras en memoria.");
    }

    // Método GET: Muestra las palabras guardadas y el clima
    //@Span(type = Span.Type.ENTRY, value = "GET_words_and_weather")
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Ejecutando método GET");

        // Obtener la ciudad del parámetro de la solicitud, si no está, usar "London" como predeterminada
        String city = request.getParameter("city");
        if (city == null || city.isEmpty()) {
            city = "London";  // Ciudad predeterminada
        }

        // Obtener los datos del clima para la ciudad
        String weatherData = getWeatherData(city);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Lista de palabras</h1>");
        out.println("<ul>");
        for (String word : wordsList) {
            out.println("<li>" + word + "</li>");
        }
        out.println("</ul>");

        // Mostrar los datos del clima
        out.println("<h1>Datos del Clima para " + city + "</h1>");
        out.println("<p>" + weatherData + "</p>");
        out.println("</body></html>");

        System.out.println("Palabras en memoria: " + wordsList.toString());
    }

    // Método POST: Agrega una palabra a la lista en memoria
    //@Span(type = Span.Type.ENTRY, value = "POST_add_word")
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Ejecutando método POST");

        String newWord = request.getParameter("word");
        if (newWord != null && !newWord.isEmpty()) {
            wordsList.add(newWord);
            System.out.println("Palabra agregada: " + newWord);
        }

        response.sendRedirect("/app/words");  // Redirigir para mostrar las palabras
    }

    // Método para obtener datos del clima de OpenWeatherMap
    //@Span(type = Span.Type.EXIT, value = "GET_weather")
    private String getWeatherData(String city) throws IOException {
        String urlString = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + API_KEY;
        URL url = new URL(urlString);

        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("192.168.170.151",3128));

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);

        int status = connection.getResponseCode();
        if (status != 200) {
            return "Error en la petición: " + status;
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        return content.toString();
    }
}