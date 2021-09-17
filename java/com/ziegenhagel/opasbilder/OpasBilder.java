package com.ziegenhagel.opasbilder;


import java.io.File;

import javax.swing.JFileChooser;

public class OpasBilder {
 public static void main(String args[]) {
   OpasBilder.setFolder();
 }
private static File[] listOfFiles;
 private static int i=-1;
  public static File folder;
 public static void setFolder() {
    JFileChooser fileChooser = new JFileChooser("/Users/domi/OpasBilder");
    fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    int option = fileChooser.showOpenDialog(fileChooser);
    if(option == JFileChooser.APPROVE_OPTION){
       OpasBilder.folder = fileChooser.getSelectedFile();

        listOfFiles = folder.listFiles();
       nextPicture();
    } else {setFolder();
 }
 }

public static void nextPicture() {
        i++;
        if(i > listOfFiles.length ) {return;}
        if (listOfFiles[i].isFile() && listOfFiles[i].length() > 50000) {
            System.out.println("File " + listOfFiles[i].getName() );
            ShowPicture window = new ShowPicture(listOfFiles[i]);
        } else {
            nextPicture();
        }
}

    }