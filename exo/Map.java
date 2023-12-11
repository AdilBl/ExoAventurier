package exo;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Files;
import java.io.BufferedReader;
import java.io.IOException;

public class Map {
    private String map[];

    public Map(String Path){
        Path file = Paths.get(Path);

        try {
            BufferedReader reader = Files.newBufferedReader(file);
            List<String> lines = new ArrayList<>();
            String line;

            while ((line = reader.readLine()) != null) {
                for (char c : line.toCharArray()) {
                    if (c != ' ' && c != '#' && c != '\n' && c != '\r') {
                        throw new RuntimeException("Caractère non valide dans le fichier map : '" + c + "'");
                    }
                }
                lines.add(line);
            }

            this.map = lines.toArray(new String[0]);
            reader.close();
        }
        catch(IOException e){
            System.err.println(e.getMessage());
        }
        catch(Exception e){
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

    public boolean isNavigatable(int x, int y) {
        if (y >= 0 && y < map.length) {
            String row = new String(map[y]);
    
            if (x >= 0 &&x < row.length()) {
                return row.charAt(x) == ' ';
            }
        }

        return false;
    }
}
