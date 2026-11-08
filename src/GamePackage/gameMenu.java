/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GamePackage;

/**
 *
 * @author ALEKSEY
 */
import javax.swing.*;
import java.awt.*;


public class gameMenu extends JPanel {
    JPanel buttonPanel;
    game gui;
    gameMenu(game gui){
        this.gui = gui;
        setBackground(gui.getCurrentColorLink());
        setLayout(new BorderLayout());
        JLabel titleLabel = new JLabel("GAMEVER", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setForeground(Color.GREEN);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
        add(titleLabel, BorderLayout.NORTH);
        
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1, 0, 20)); 
        buttonPanel.setBackground(gui.getCurrentColorLink());
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(100, 500, 200, 500));

        JButton levelButton = createStyledButton("ВЫБОР УРОВНЯ");
        JButton settingsButton = createStyledButton("НАСТРОЙКИ");
        JButton exitButton = createStyledButton("ВЫХОД");
        
        buttonPanel.add(levelButton);
        buttonPanel.add(settingsButton);
        buttonPanel.add(exitButton);
        
        levelButton.addActionListener(e -> gui.openGame()); 
        settingsButton.addActionListener(e -> gui.openSetting());
        exitButton.addActionListener(e -> System.exit(0)); 
        
        add(buttonPanel, BorderLayout.CENTER);
    }
    
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(50, 50, 50));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
        return button;
    }
    public void thisColorUpdate(){
        this.setBackground(gui.getCurrentColorLink());
        buttonPanel.setBackground(gui.getCurrentColorLink());
    }
}

