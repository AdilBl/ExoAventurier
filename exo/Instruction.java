package exo;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Instruction {
    private int x,y;
    private String instructions;
    private int index = 0;

    public Instruction(String Path){
        Path file = Paths.get(Path);

        try {
            BufferedReader reader = Files.newBufferedReader(file);

            String firstLine = reader.readLine();

            if (firstLine != null) {
                String[] coordinates = firstLine.split(",");
                if (coordinates.length == 2) {
                    this.x = Integer.parseInt(coordinates[0]);
                    this.y = Integer.parseInt(coordinates[1]);
                } else {
                    throw new RuntimeException("Format de coordonnées invalide dans la première ligne");
                }
            } else {
                throw new RuntimeException("Le fichier est vide");
            }
            String secondLine = reader.readLine();
            if (secondLine != null && isValidInstruction(secondLine)) {
                this.instructions = secondLine;
            } else {
                throw new RuntimeException("Format d'instructions invalide dans la deuxième ligne");
            }
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

    private boolean isValidInstruction(String instruction) {
        return instruction.matches("[NSEO]+");
    }

    public void calculatePlayerPositionAfterInstructions(Map map) {
        if(!map.isNavigatable(this.x, this.y)){
            throw new RuntimeException("Les coordonnées du joueur ne se trouve pas dans un endroit naviguable");
        }
        else{
            while (this.index < instructions.length()) {
                char currentInstruction = instructions.charAt(this.index);
                int deltaX = 0;
                int deltaY = 0;
        
                switch (currentInstruction) {
                    case 'N':
                        deltaY = -1;
                        break;
                    case 'S':
                        deltaY = 1;
                        break;
                    case 'E':
                        deltaX = 1;
                        break;
                    case 'O':
                        deltaX = -1;
                        break;
                }      
                if (map.isNavigatable(x + deltaX, y + deltaY)) {
                    x += deltaX;
                    y += deltaY;
                }
                this.index++;
            }
            System.out.println("Le personnage se trouve en X:" + this.x + " Y:" + this.y);
            this.index = 0;
        }
    }
}
