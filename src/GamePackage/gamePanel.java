/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GamePackage;
import javax.swing.*;
import java.awt.*;
/**
 *
 * @author ALEKSEY
 */
public class gamePanel extends JPanel{
    JPanel levelChooserPanel;
    JPanel buttonPanel;
    JLabel levelValue;
    game gui;
    private int currentIndexLevel = 0;
    private String[] level = {"Уровень 1", "Уровень 2", "Уровень 3"};
    public gamePanel(game gui){
        this.gui = gui;
        setBackground(gui.getCurrentColorLink());
        setLayout(new BorderLayout());
        JLabel titleLabel = new JLabel("Выбор уровня", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setForeground(Color.GREEN);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
        add(titleLabel, BorderLayout.NORTH);
         
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1, 0, 20)); 
        buttonPanel.setBackground(gui.getCurrentColorLink());
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(100, 400, 200, 400));
        
        JLabel themeLabel = createStyledLebel("Выберите уровень:");
        themeLabel.setHorizontalAlignment(JLabel.RIGHT);
        levelValue = createStyledLebel(getCurrentLevel());
        levelValue.setHorizontalAlignment(JLabel.CENTER);
        levelChooserPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        levelChooserPanel.setBackground(gui.getCurrentColorLink());

        JButton leftArrow = createStyledButton("<");
        JButton rightArrow = createStyledButton(">");
        JButton startButton = createStyledButton("Начать");
        JButton exitButton = createStyledButton("Назад");
        
        levelChooserPanel.add(themeLabel);
        levelChooserPanel.add(leftArrow);
        levelChooserPanel.add(levelValue);
        levelChooserPanel.add(rightArrow);
        buttonPanel.add(levelChooserPanel);
        buttonPanel.add(startButton);
        buttonPanel.add(exitButton);
        
        leftArrow.addActionListener(e ->{
            previous();
            levelValue.setText(getCurrentLevel());
        });
        rightArrow.addActionListener(e ->{
            next();
            levelValue.setText(getCurrentLevel());
        });
        startButton.addActionListener(e -> openLevel()); 
        exitButton.addActionListener(e -> gui.openMenu()); 
        
        add(buttonPanel, BorderLayout.CENTER);
    }
    public JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(50, 50, 50));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
        button.setPreferredSize(new Dimension(20,20));
        return button;
    }
    
    public JLabel  createStyledLebel(String text) {
        JLabel lebel = new JLabel (text);
        lebel.setFont(new Font("Arial", Font.BOLD, 18));
        lebel.setForeground(Color.WHITE);
        lebel.setOpaque(true);
        lebel.setBackground(new Color(50, 50, 50));
        lebel.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
        lebel.setPreferredSize(new Dimension(200,50));
        return lebel;
    }
    public void next() {
        currentIndexLevel = (currentIndexLevel + 1) % level.length;
    }
    public void previous() {
        currentIndexLevel = (currentIndexLevel - 1 + level.length) % level.length;
    }
    public String getCurrentLevel() {
        return level[currentIndexLevel];
    }
    public void openLevel(){
        
        switch (currentIndexLevel) {
            case 0 -> gui.openLevel1();
            case 1 -> gui.openLevel2();
            case 2 -> gui.openLevel3();
            default -> throw new AssertionError();
        }
    }
}
