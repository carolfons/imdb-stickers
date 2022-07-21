import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class StickerFactory {

    void create(InputStream inputStream,String nomeArquivo) throws Exception {
        // lendo a imagem de uma URL a url vem da outra classe que chama a função
        //InputStream inputStream = new URL("https://m.media-amazon.com/images/M/MV5BMzE5MDM1NDktY2I0OC00YWI5LTk2NzUtYjczNDczOWQxYjM0XkEyXkFqcGdeQXVyMTQxNzMzNDI@.jpg").openStream();
        BufferedImage imagemOriginal = ImageIO.read(inputStream);

        // criar uma nova imagem com transparencia e novo size
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 200;

        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

        // copiar a imagem original para nova imagem (em memória)
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null);

        // setar a fonte
        Font font = new Font(Font.MONOSPACED, Font.BOLD, 64);
        graphics.setColor(Color.YELLOW);
        graphics.setFont(font);

        // escrever uma frase na nova imagem
        graphics.drawString("MUITO BÃO", largura-750, novaAltura - 100);
        // escrever a nova imagem em um arquivo
        String output = "output/"+ nomeArquivo + ".png";
        ImageIO.write(novaImagem, "png", new File(output));
    }

}
