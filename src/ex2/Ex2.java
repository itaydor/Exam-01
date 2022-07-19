package ex2;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

public class Ex2 {

    private static final String API_URL = "https://api.themoviedb.org/3/find/{external_id}?api_key=fd5df7a3fa764a16803d72dfeeed4c93&language=en-US&external_source=imdb_id";
    private static final String BASE_URL = "http://image.tmdb.org/t/p/";

    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner sc = new Scanner(System.in);
        List<Movie> movies = new ArrayList<>();

        System.out.println("Please insert IMDb id.");
        String imdb_id = sc.nextLine(); //imdb id examples: tt0237539, tt0069113

        while (!imdb_id.equals("0")){

            HttpResponse<String> response = getStringHttpResponse(imdb_id);

            if(response.statusCode() > 299){
                System.out.println("Some error occur during the request, Response status code: " + response.statusCode());
            }
            else if(!isMovieResultEmpty(response.body())){
                Movie movie = parsToMovie(response);

                savePoster(movie);

                System.out.println(movie.getTitle());

                movies.add(movie);
            }
            else {
                //check only for movie results, tv series are not included.
                System.out.println("Could not get the wanted movie, please try again. (Response: " + response.body()  + ")");
            }

            System.out.println("Please insert IMDb id.");
            imdb_id = sc.nextLine();
        }

        movies.sort(Collections.reverseOrder(Comparator.comparingDouble(Movie::getPopularity)));

        if(movies.size() > 0){
            System.out.println("Most popular movie is: ");
            System.out.println(movies.get(0).getTitle());
        }
    }

    /**
     * Check if the body contains a movie result.
     * @param body
     * @return
     */
    private static boolean isMovieResultEmpty(String body) {
        return body.contains("\"movie_results\":[]");
    }

    /**
     * Make GET request
     * @param imdb_id - movie id.
     * @return the response of the GET request.
     * @throws IOException
     * @throws InterruptedException
     */
    private static HttpResponse<String> getStringHttpResponse(String imdb_id) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("accept", "application/json")
                .uri(URI.create(API_URL.replace("{external_id}", imdb_id)))
                .build();

        // use the client to send the request
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    /**
     * Save the poster at C:/temp/{movie title} image.jpg
     * @param movie
     * @throws IOException
     */
    private static void savePoster(Movie movie) throws IOException {
        if(movie.getPoster_path() == null){
            System.out.println("This movie doesn't contains poster, therefore nothing will be saved.");
            return;
        }
        String posterURL = BASE_URL + "original" + movie.getPoster_path();
        //create the folder
        new File("C:/temp").mkdirs();

        // get the image from the url and copy it to the file
        URL imageURL = new URL(posterURL);
        BufferedImage bi = ImageIO.read(imageURL);
        ImageIO.write(bi, "jpg", new File("C:/temp/" + movie.getTitle() + " image.jpg"));
        bi.flush();
    }

    /**
     * Parse the response to Movie Object
     * @param response the response
     * @return Movie object.
     */
    private static Movie parsToMovie(HttpResponse<String> response) {
        //couldn't use Gson\JSONParser\ObjectMapper, so I pars it in my way.
        String body = response.body();
        //look for movie results in the body.
        int indexOfMovieResults = body.indexOf("\"movie_results\":[{");
        String movieResult = body.substring(indexOfMovieResults, body.indexOf("}],\"", indexOfMovieResults));
        return new Movie(movieResult.substring(movieResult.indexOf("{")).substring(1));
    }
}
