package com.ziegenhagel.opasbilder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;
class ShowPicture {

  JFrame frame = new JFrame();
  File file;
  

 public ShowPicture(File file) {
   this.file = file;

   String filename = file.getAbsolutePath();
  ImageIcon icon = new ImageIcon(filename);
  icon = ShowPicture.scaleImage(icon,600,600);

  JPanel vertical = new JPanel();
  vertical.setLayout(new BoxLayout(vertical, BoxLayout.PAGE_AXIS));

  JLabel label = new JLabel(icon);
  vertical.add(label);

  JPanel btns = new JPanel();

  JButton btn = new JButton("Gut");  
  ImageIcon gut = new ImageIcon("gut.png");
  gut = ShowPicture.scaleImage(gut,100,100);

  btn.setIcon(gut);

  ShowPicture that = this;
  btn.addActionListener(new ActionListener() {
       @Override
       public void actionPerformed(ActionEvent e) {
            that.rate(1);
       }
   });
  btns.add(btn);

  btn = new JButton("Schlecht");  
  ImageIcon schlecht = new ImageIcon("schlecht.png");
  schlecht = ShowPicture.scaleImage(schlecht,100,100);

  btn.setIcon(schlecht);
  btn.addActionListener(new ActionListener() {
       @Override
       public void actionPerformed(ActionEvent e) {
            that.rate(0);
       }
   });
  btns.add(btn);

  vertical.add(btns);

  frame.add(vertical);

  frame.pack();
  frame.setVisible(true);
 }

 private void rate(int isGood) {

    String[] folders = {"schlecht","gut"};
    String rating = folders[isGood];

    File newFolder = new File(OpasBilder.folder.getAbsolutePath()+"/"+rating+"/");
    newFolder.mkdir();

    System.out.println("Rating "+this.file.getName()+" as "+rating);
    String newPath = OpasBilder.folder.getAbsolutePath()+"/"+rating+"/"+this.file.getName();
    System.out.println("Moving it to "+newPath);
    this.file.renameTo( new File( newPath));

   frame.dispose();
   OpasBilder.nextPicture();
 }

 public static ImageIcon scaleImage(ImageIcon icon, int w, int h)
    {
        int nw = icon.getIconWidth();
        int nh = icon.getIconHeight();

        if(icon.getIconWidth() > w)
        {
          nw = w;
          nh = (nw * icon.getIconHeight()) / icon.getIconWidth();
        }

        if(nh > h)
        {
          nh = h;
          nw = (icon.getIconWidth() * nh) / icon.getIconHeight();
        }

        return new ImageIcon(icon.getImage().getScaledInstance(nw, nh, 0));
    }
}
