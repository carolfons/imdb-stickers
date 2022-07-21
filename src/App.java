import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {

        // criando a conxão http para buscar filmes
        String url = "https://alura-imdb-api.herokuapp.com/movies";
        HttpClient client = HttpClient.newHttpClient();
        // criando o request
        URI endereco = URI.create(url);
        HttpRequest request = HttpRequest
                .newBuilder(endereco)
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        // extraindo os dados com o uso de Regex
        JsonParser jsonParser = new JsonParser();
        List<Map<String, String>> listaFilmes = jsonParser.parse(body);
        
        //exibindo os dados
        StickerFactory stickerFactory = new StickerFactory();
        for (Map<String,String> filme : listaFilmes) {
            String urlImage = filme.get("image");
            String titulo = filme.get("title");

            String nomeArquivo = titulo;
            InputStream inputStream = new URL(urlImage).openStream();
            
            stickerFactory.create(inputStream,nomeArquivo);

            System.out.println(titulo);
            System.out.println();
        }

    }
}
