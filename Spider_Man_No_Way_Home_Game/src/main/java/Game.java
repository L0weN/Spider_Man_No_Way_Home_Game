import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

class Web {
    private int x;
    private int y;

    public Web(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }}
    
public class Game extends JPanel implements KeyListener,ActionListener {
    Timer timer = new Timer(10,this);
    
    private int spend_time;
    private int spend_web;
    
    private BufferedImage image;
    private BufferedImage image2;
    private BufferedImage image3;
    private BufferedImage image4;
    private BufferedImage image5;
    
    private ArrayList<Web> webs = new ArrayList<Web>();
    private ArrayList<Web> webs2 = new ArrayList<Web>();
    
    private int webdirY = 2;
    
    private int web2dirX = 1;
    
    private int enemyX = 350;
    
    private int enemy2Y =400;
    
    private int enemydirX = 2;
    
    private int enemy2dirY = 1;
    
    private int spider_manX = 350;
    
    private int spider_manY = 470;
    
    private int dirMoveX = 10;
    
    private int dirMoveY = 10;
    public boolean check(){
        for(Web web : webs){
            if(new Rectangle(web.getX(),web.getY(),image3.getWidth()/25,image3.getHeight()/25).intersects(new Rectangle(enemyX,0,image2.getWidth()/10,image2.getHeight()/10))){
                return true;
            }
        }
        return false;
    }
    public boolean check2(){
        for(Web web2 : webs2){
            if(new Rectangle(web2.getX(),web2.getY(),image5.getWidth()/18,image5.getHeight()/18).intersects(new Rectangle(spider_manX,spider_manY,image.getWidth()/10,image.getHeight()/10))){
                return true;
            }
        }
        return false;
    }
    
    public Game() {
        try{
            image = ImageIO.read(new FileImageInputStream(new File("spider_man.png")));
        }catch (IOException ex){
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE,null, ex);
        }
        
        try{
            image2 = ImageIO.read(new FileImageInputStream(new File("dr_strange.jpg")));
        }catch (IOException ex){
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE,null, ex);
        }
        
        try{
            image4 = ImageIO.read(new FileImageInputStream(new File("goblin.jpg")));
        }catch (IOException ex){
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE,null, ex);
        }
        
        try{
            image3 = ImageIO.read(new FileImageInputStream(new File("web1.png")));
        }catch (IOException ex){
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE,null, ex);
        }
        
        try{
            image5 = ImageIO.read(new FileImageInputStream(new File("web2.png")));
        }catch (IOException ex){
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE,null, ex);
        }
        
        setBackground(Color.black);
        
        timer.start();
        
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        spend_time += 10;
        g.setColor(Color.black);
        
        g.drawImage(image4, 0,enemy2Y, image4.getWidth()/12, image4.getHeight()/12, this);
        
        g.drawImage(image2, enemyX,0,image2.getWidth()/10,image2.getHeight()/10,this);
        
        g.drawImage(image, spider_manX ,spider_manY,image.getWidth()/10,image.getHeight()/10,this);
        
        
        for(Web web : webs ){
            if(web.getY() < 0) {
                webs.remove(web);
            }
            
        }
        
        for(Web web : webs){
            g.drawImage(image3,web.getX(),web.getY(),image3.getWidth()/20,image3.getHeight()/20,this);
        }
        
        for(Web web2 : webs2 ){
            if(web2.getX() > 650) {
                webs2.remove(web2);
            }
            
        }
        
        for(Web web2 : webs2){
            g.drawImage(image5,web2.getX(),web2.getY(),image5.getWidth()/18,image5.getHeight()/18,this);
        }
        if(check()){
            timer.stop();
            String message = "Kazandınız...\n";
            JOptionPane.showMessageDialog(this, message);
            System.exit(0);
        }
        if(check2()){
            timer.stop();
            String message = "Kaybettiniz...\n";
            JOptionPane.showMessageDialog(this, message);
            System.exit(0);
        }
    }

    @Override
    public void repaint(Rectangle r) {
        super.repaint(r); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int moving = e.getKeyCode();
        int moving2 = e.getKeyCode();
        
        if(moving == KeyEvent.VK_LEFT){
            if(spider_manX <= 0){
                spider_manX = 0;
            }
            else{
                spider_manX -= dirMoveX;
            }
        }
        else if(moving == KeyEvent.VK_RIGHT){
            if(spider_manX >= 650){
                spider_manX = 650;
            }
            else{
                spider_manX += dirMoveX;
            }
        }
        if(moving2 == KeyEvent.VK_DOWN){
            if(spider_manY >= 530){
                spider_manY = 530;
            }
            else{
                spider_manY += dirMoveY;
            }
        }
        else if(moving2 == KeyEvent.VK_UP){
            if(spider_manY <= 350){
                spider_manY = 350;
            }
            else{
                spider_manY -= dirMoveY;
            }
        }
        else if(moving == KeyEvent.VK_SPACE){
            webs.add(new Web(spider_manX+20,spider_manY));
            spend_web++;
            
        }
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        for(Web web : webs){
            
            web.setY(web.getY() - webdirY);
        }
        for(Web web2 : webs2){
            
            web2.setX(web2.getX() + web2dirX);
        }
        
        
        enemyX += enemydirX;
        
        if(enemyX >= 650) {
            enemydirX = -enemydirX;  
        }
        if(enemyX <= 0) {
            enemydirX = -enemydirX;
        }
        
        enemy2Y += enemy2dirY;
        
        if(enemy2Y >= 500){
            enemy2dirY = -enemy2dirY;
            webs2.add(new Web(0,enemy2Y+10));
        }
        if(enemy2Y == 425){
            webs2.add(new Web(0,enemy2Y+10));
        }
        if(enemy2Y <= 350){
            enemy2dirY = -enemy2dirY;
            webs2.add(new Web(0,enemy2Y+10));
        }
        
        repaint();
    }
}
    