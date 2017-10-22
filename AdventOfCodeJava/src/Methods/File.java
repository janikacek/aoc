package Methods;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Jana on 04.12.2016.
 */
public class File {
    public static List<String> readFile(String filename)
    {
        Path path = Paths.get(filename);
        Charset charset = Charset.forName("UTF-8");
        List<String> content = new LinkedList<>();
        try {
            List<String> lines = Files.readAllLines(path, charset);
            for (String line : lines){
                try{
                    content.add(line);
                } catch (IllegalStateException ex){
                    ex.printStackTrace(System.err);
                }
            }
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }

        return content;
    }
}
