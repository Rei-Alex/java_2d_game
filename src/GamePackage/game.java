/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package GamePackage;

import javax.swing.SwingUtilities;
import javax.swing.*;
import java.awt.*;
public class game extends JFrame{

    private CardLayout cardLayout;
    private JPanel mainPanel;
    private int heightWin = 720;
    private int widthWin = 1280;
    private String[] color = {"Тёмно-серый", "Синий", "Светло-серый"};
    private Color[] colorLinks ={Color.DARK_GRAY, Color.BLUE, Color.LIGHT_GRAY};
    private int currentIndexColor = 0;
    gameMenu menuPanel;
    gameSetting settingPanel;
    gamePanel gameLevel;
    baseLevel gameLevel1;
    baseLevel gameLevel2;
    baseLevel gameLevel3;
            
    public game() {
        setTitle("GAMEVER");
        setSize(getWidthWin(), getHeightWin());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 
        setLayout(new BorderLayout());
        setResizable(false);
       
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        
        this.menuPanel = new gameMenu(this);
        this.settingPanel = new gameSetting(this);
        this.gameLevel = new gamePanel(this);
        this.gameLevel1 = new level1(this);
        this.gameLevel2 = new level2(this);
        this.gameLevel3 = new level3(this);
        
        mainPanel.add(menuPanel, "MENU");
        mainPanel.add(settingPanel, "SETTING");
        mainPanel.add(gameLevel, "GAMELEVEL");
        mainPanel.add(gameLevel1, "LEVEL1");
        mainPanel.add(gameLevel2, "LEVEL2");
        mainPanel.add(gameLevel3, "LEVEL3");
        
        add(mainPanel);
        openMenu();
        
        
        setVisible(true);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(game::new);
    }
    public void openSetting(){
        cardLayout.show(mainPanel, "SETTING");
    }
    public void openGame(){
        cardLayout.show(mainPanel, "GAMELEVEL");
    }
    public void openMenu(){
        cardLayout.show(mainPanel, "MENU");
    }
    public void openLevel1() {
        cardLayout.show(mainPanel, "LEVEL1");
    }
    public void openLevel2() {
        cardLayout.show(mainPanel, "LEVEL2");
    }
    public void openLevel3() {
        cardLayout.show(mainPanel, "LEVEL3");
    }
    public int getHeightWin() {
        return heightWin;
    }
    public void setHeightWin(int heightWin) {
        this.heightWin = heightWin;
    }
    public int getWidthWin() {
        return widthWin;
    }
    public void setWidthWin(int widthWin) {
        this.widthWin = widthWin;
    }
    public String getCurrentColor() {
        return color[currentIndexColor];
    }
    public Color getCurrentColorLink() {
        return colorLinks[currentIndexColor];
    }
    public void next() {
        currentIndexColor = (currentIndexColor + 1) % color.length;
    }
    public void previous() {
        currentIndexColor = (currentIndexColor - 1 + color.length) % color.length;
    }
}