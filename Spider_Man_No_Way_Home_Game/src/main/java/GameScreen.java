import java.awt.HeadlessException;
import javax.swing.JFrame;

public class GameScreen extends JFrame{

    public GameScreen(String title) throws HeadlessException {
        super(title);
    }
    
    public static void main(String[] args){
        
        GameScreen window = new GameScreen("Window");
        
        window.setResizable(false);
        window.setFocusable(false);
        
        window.setSize(800,600);
        
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Game game = new Game();
        game.requestFocus();
        game.addKeyListener(game);
        
        game.setFocusable(true);
        game.setFocusTraversalKeysEnabled(false);
        
        window.add(game);
        window.setVisible(true);
        
    }
}
