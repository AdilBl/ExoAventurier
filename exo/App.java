package exo;
class App
{
    public static void main(String []args)
    {
        if (args.length != 2){
            System.err.println("Nombre d'argument invalide\nVeuillez Entrer [Map.File] [Instruction.File]");
            return;
        }
        Map map = new Map(args[0]);
        Instruction test =  new Instruction(args[1]);
        test.calculatePlayerPositionAfterInstructions(map);
    }
}