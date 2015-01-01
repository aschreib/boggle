package boggle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class TimePanel extends JPanel{
 
 private ClientGUI gui;
 
 private JLabel timerLabel;
 private JButton startGame;
 
 public TimePanel(ClientGUI gui){
  this.gui = gui;
  
  timerLabel = new JLabel("Time Remaining:");
  startGame = new JButton("Start Game");
  
  startGame.addActionListener(new StartGameActionListener());
  
  add(timerLabel);
  add(startGame);
 }
 
 

 public JLabel getTimerLabel() {
  return timerLabel;
 }



 public JButton getStartGame() {
  return startGame;
 }



 private class StartGameActionListener implements ActionListener{

  @Override
  public void actionPerformed(ActionEvent e) {
   try {
    gui.getClient().startGame("start game/n");
   } catch (IOException e1) {
    // TODO Auto-generated catch block
    e1.printStackTrace();
   }
   startGame.setEnabled(false);
  }

 }
 
}