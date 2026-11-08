/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GamePackage;

import javax.swing.JPanel;

/**
 *
 * @author ALEKSEY
 */
public class level1 extends baseLevel{
    
    public level1(game gui) {
        super(gui);
    }

    @Override
    public void setingLevel() {
        cof = 4;
        CELL_SIZE = 20 * cof;
        colCellWight = 10;
        colCellHeight = 7;
        
        cells = new JPanel[colCellHeight][colCellWight];
        heroesList = new java.util.ArrayList<>();
        blockList = new java.util.ArrayList<>();
        targetCells = new java.util.ArrayList<>();
        
        heroesList.add(new Hero(4 * cof + CELL_SIZE *2, 4 * cof + CELL_SIZE *2));
        heroesList.add(new Hero(4 * cof + CELL_SIZE *4, 4 * cof + CELL_SIZE *2));
        heroesList.add(new Hero(4 * cof + CELL_SIZE *6, 4 * cof + CELL_SIZE *2));
        
        blockList.add(new blockData(8 * cof + CELL_SIZE *2, 8 * cof + CELL_SIZE *4));
        blockList.add(new blockData(8 * cof + CELL_SIZE *4, 8 * cof + CELL_SIZE *4));
        blockList.add(new blockData(8 * cof + CELL_SIZE *6, 8 * cof + CELL_SIZE *4));
        
        targetCells.add(new java.awt.Point(2, 5));
        targetCells.add(new java.awt.Point(4, 5));
        targetCells.add(new java.awt.Point(6, 5));
    }

    @Override
    public void task() {
        boolean allTargetsCovered = true;
        for (java.awt.Point target : targetCells) {
            boolean blockFoundOnThisTarget = false;
            for (blockData block : blockList) {
                if (block.currentCellX == target.x && block.currentCellY == target.y) {
                    blockFoundOnThisTarget = true;
                    break;
                }
            }
            if (!blockFoundOnThisTarget) {
                allTargetsCovered = false;
                break;
            }
        }
        if (allTargetsCovered) {
            javax.swing.JOptionPane.showMessageDialog(this, 
                "Великолепно! Все целевые клетки заполнены блоками.", 
                "Успех", 
                javax.swing.JOptionPane.INFORMATION_MESSAGE);
            gui.openGame();
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, 
                "Задание не выполнено. Нужно закрыть блоками три целевые клетки!", 
                "Ошибка", 
                javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }
}
